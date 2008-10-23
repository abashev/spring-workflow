package org.springframework.extensions.workflow.instance.permissions.evaluator;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.Tree;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author janm
 */
public class AntlrRoleExpressionEvaluator implements RoleExpressionEvaluator {
    private static final Pattern ROLE_PATTERN = Pattern.compile("^\\[?([^]]*)\\]?$");
    private static final long serialVersionUID = -1199626460973229852L;

    private boolean evaluateTree(CommonTree tree, String[] grantedRoles) {
        switch (tree.getType()) {
            case RoleExpressionLexer.OR_DEF:
                // we expect two children
                return evaluateTree((CommonTree)tree.getChild(0), grantedRoles) ||
                        evaluateTree((CommonTree)tree.getChild(1), grantedRoles);
            case RoleExpressionLexer.AND_DEF:
                return evaluateTree((CommonTree)tree.getChild(0), grantedRoles) &&
                        evaluateTree((CommonTree)tree.getChild(1), grantedRoles);
            case RoleExpressionLexer.NOT_DEF:
                return !evaluateTree((CommonTree)tree.getChild(0), grantedRoles);
            case RoleExpressionLexer.ROLE_DEF:
                if (tree.getChildCount() == 2) {
                    String role = getRole(tree.getChild(1));
                    // role should not be in grantedRoles
                    for (String grantedRole : grantedRoles) {
                        if (role.equalsIgnoreCase(grantedRole)) return false;
                    }
                    return true;
                } else {
                    String role = getRole(tree.getChild(0));
                    // role should be in grantedRoles
                    for (String grantedRole : grantedRoles) {
                        if (role.equalsIgnoreCase(grantedRole)) return true;
                    }
                    return false;
                }
            default:
                throw new IllegalStateException("Cannot evaluate expression.");
        }
    }

    private String getRole(Tree tree) {
        String role = tree.toString();
        Matcher matcher = ROLE_PATTERN.matcher(tree.toString());
        if (matcher.matches()) return matcher.group(1);
        return role;
    }

    public boolean doEvaluate(String expression, String[] grantedRoles) throws RecognitionException {
        RoleExpressionLexer lexer = new RoleExpressionLexer(new ANTLRStringStream(expression));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        RoleExpressionParser parser = new RoleExpressionParser(tokens);
        RoleExpressionParser.expr_return r = parser.expr();

        CommonTree tree = (CommonTree)r.getTree();
        return evaluateTree(tree, grantedRoles);
    }

    public boolean evaluate(String expression, String... grantedRoles) {
        try {
            return doEvaluate(expression, grantedRoles);
        } catch (RecognitionException ex) {
            throw new RuntimeException(ex);
        }
    }
}
