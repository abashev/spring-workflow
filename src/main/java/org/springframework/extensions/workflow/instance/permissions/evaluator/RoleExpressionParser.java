// $ANTLR 3.0.1 RoleExpression.g 2008-04-01 15:36:30
 
package org.springframework.extensions.workflow.instance.permissions.evaluator;


import org.antlr.runtime.*;

import java.io.Serializable;


import org.antlr.runtime.tree.*;

public class RoleExpressionParser extends Parser implements Serializable{
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "EXPR_DEF", "NOT_DEF", "ROLE_DEF", "OR_DEF", "AND_DEF", "ID", "NOT", "AND", "OR", "IDPART", "BID", "WS", "'('", "')'"
    };
    public static final int AND=11;
    public static final int AND_DEF=8;
    public static final int OR_DEF=7;
    public static final int WS=15;
    public static final int EOF=-1;
    public static final int EXPR_DEF=4;
    public static final int NOT_DEF=5;
    public static final int IDPART=13;
    public static final int NOT=10;
    public static final int ROLE_DEF=6;
    public static final int OR=12;
    public static final int BID=14;
    public static final int ID=9;

        public RoleExpressionParser(TokenStream input) {
            super(input);
        }
        
    protected TreeAdaptor adaptor = new CommonTreeAdaptor();

    public void setTreeAdaptor(TreeAdaptor adaptor) {
        this.adaptor = adaptor;
    }
    public TreeAdaptor getTreeAdaptor() {
        return adaptor;
    }

    public String[] getTokenNames() { return tokenNames; }
    public String getGrammarFileName() { return "RoleExpression.g"; }


    public static class rexpr_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start rexpr
    // RoleExpression.g:22:1: rexpr : expr -> ^( EXPR_DEF expr ) ;
    public final rexpr_return rexpr() throws RecognitionException {
        rexpr_return retval = new rexpr_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        expr_return expr1 = null;


        RewriteRuleSubtreeStream stream_expr=new RewriteRuleSubtreeStream(adaptor,"rule expr");
        try {
            // RoleExpression.g:22:7: ( expr -> ^( EXPR_DEF expr ) )
            // RoleExpression.g:22:9: expr
            {
            pushFollow(FOLLOW_expr_in_rexpr71);
            expr1=expr();
            _fsp--;

            stream_expr.add(expr1.getTree());

            // AST REWRITE
            // elements: expr
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 22:14: -> ^( EXPR_DEF expr )
            {
                // RoleExpression.g:22:17: ^( EXPR_DEF expr )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(adaptor.create(EXPR_DEF, "EXPR_DEF"), root_1);

                adaptor.addChild(root_1, stream_expr.next());

                adaptor.addChild(root_0, root_1);
                }

            }



            }

            retval.stop = input.LT(-1);

                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end rexpr

    public static class expr_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start expr
    // RoleExpression.g:24:1: expr : conjExpr ( or conjExpr )* ;
    public final expr_return expr() throws RecognitionException {
        expr_return retval = new expr_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        conjExpr_return conjExpr2 = null;

        or_return or3 = null;

        conjExpr_return conjExpr4 = null;



        try {
            // RoleExpression.g:24:6: ( conjExpr ( or conjExpr )* )
            // RoleExpression.g:24:10: conjExpr ( or conjExpr )*
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_conjExpr_in_expr89);
            conjExpr2=conjExpr();
            _fsp--;

            adaptor.addChild(root_0, conjExpr2.getTree());
            // RoleExpression.g:24:19: ( or conjExpr )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==OR) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // RoleExpression.g:24:20: or conjExpr
            	    {
            	    pushFollow(FOLLOW_or_in_expr92);
            	    or3=or();
            	    _fsp--;

            	    root_0 = (CommonTree)adaptor.becomeRoot(or3.getTree(), root_0);
            	    pushFollow(FOLLOW_conjExpr_in_expr95);
            	    conjExpr4=conjExpr();
            	    _fsp--;

            	    adaptor.addChild(root_0, conjExpr4.getTree());

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end expr

    public static class conjExpr_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start conjExpr
    // RoleExpression.g:26:1: conjExpr : atom ( and atom )* ;
    public final conjExpr_return conjExpr() throws RecognitionException {
        conjExpr_return retval = new conjExpr_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        atom_return atom5 = null;

        and_return and6 = null;

        atom_return atom7 = null;



        try {
            // RoleExpression.g:26:9: ( atom ( and atom )* )
            // RoleExpression.g:26:14: atom ( and atom )*
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_atom_in_conjExpr107);
            atom5=atom();
            _fsp--;

            adaptor.addChild(root_0, atom5.getTree());
            // RoleExpression.g:26:19: ( and atom )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==AND) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // RoleExpression.g:26:20: and atom
            	    {
            	    pushFollow(FOLLOW_and_in_conjExpr110);
            	    and6=and();
            	    _fsp--;

            	    root_0 = (CommonTree)adaptor.becomeRoot(and6.getTree(), root_0);
            	    pushFollow(FOLLOW_atom_in_conjExpr113);
            	    atom7=atom();
            	    _fsp--;

            	    adaptor.addChild(root_0, atom7.getTree());

            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end conjExpr

    public static class atom_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start atom
    // RoleExpression.g:28:1: atom : ( ( negative )? ID -> ^( ROLE_DEF ( negative )? ID ) | ( negative )? '(' expr ')' );
    public final atom_return atom() throws RecognitionException {
        atom_return retval = new atom_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token ID9=null;
        Token char_literal11=null;
        Token char_literal13=null;
        negative_return negative8 = null;

        negative_return negative10 = null;

        expr_return expr12 = null;


        CommonTree ID9_tree=null;
        CommonTree char_literal11_tree=null;
        CommonTree char_literal13_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleSubtreeStream stream_negative=new RewriteRuleSubtreeStream(adaptor,"rule negative");
        try {
            // RoleExpression.g:28:6: ( ( negative )? ID -> ^( ROLE_DEF ( negative )? ID ) | ( negative )? '(' expr ')' )
            int alt5=2;
            switch ( input.LA(1) ) {
            case NOT:
                {
                int LA5_1 = input.LA(2);

                if ( (LA5_1==ID) ) {
                    alt5=1;
                }
                else if ( (LA5_1==16) ) {
                    alt5=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("28:1: atom : ( ( negative )? ID -> ^( ROLE_DEF ( negative )? ID ) | ( negative )? '(' expr ')' );", 5, 1, input);

                    throw nvae;
                }
                }
                break;
            case ID:
                {
                alt5=1;
                }
                break;
            case 16:
                {
                alt5=2;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("28:1: atom : ( ( negative )? ID -> ^( ROLE_DEF ( negative )? ID ) | ( negative )? '(' expr ')' );", 5, 0, input);

                throw nvae;
            }

            switch (alt5) {
                case 1 :
                    // RoleExpression.g:28:10: ( negative )? ID
                    {
                    // RoleExpression.g:28:10: ( negative )?
                    int alt3=2;
                    int LA3_0 = input.LA(1);

                    if ( (LA3_0==NOT) ) {
                        alt3=1;
                    }
                    switch (alt3) {
                        case 1 :
                            // RoleExpression.g:28:10: negative
                            {
                            pushFollow(FOLLOW_negative_in_atom126);
                            negative8=negative();
                            _fsp--;

                            stream_negative.add(negative8.getTree());

                            }
                            break;

                    }

                    ID9=(Token)input.LT(1);
                    match(input,ID,FOLLOW_ID_in_atom129); 
                    stream_ID.add(ID9);


                    // AST REWRITE
                    // elements: ID, negative
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 28:23: -> ^( ROLE_DEF ( negative )? ID )
                    {
                        // RoleExpression.g:28:26: ^( ROLE_DEF ( negative )? ID )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(adaptor.create(ROLE_DEF, "ROLE_DEF"), root_1);

                        // RoleExpression.g:28:37: ( negative )?
                        if ( stream_negative.hasNext() ) {
                            adaptor.addChild(root_1, stream_negative.next());

                        }
                        stream_negative.reset();
                        adaptor.addChild(root_1, stream_ID.next());

                        adaptor.addChild(root_0, root_1);
                        }

                    }



                    }
                    break;
                case 2 :
                    // RoleExpression.g:29:5: ( negative )? '(' expr ')'
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    // RoleExpression.g:29:13: ( negative )?
                    int alt4=2;
                    int LA4_0 = input.LA(1);

                    if ( (LA4_0==NOT) ) {
                        alt4=1;
                    }
                    switch (alt4) {
                        case 1 :
                            // RoleExpression.g:29:13: negative
                            {
                            pushFollow(FOLLOW_negative_in_atom146);
                            negative10=negative();
                            _fsp--;

                            root_0 = (CommonTree)adaptor.becomeRoot(negative10.getTree(), root_0);

                            }
                            break;

                    }

                    char_literal11=(Token)input.LT(1);
                    match(input,16,FOLLOW_16_in_atom150); 
                    pushFollow(FOLLOW_expr_in_atom153);
                    expr12=expr();
                    _fsp--;

                    adaptor.addChild(root_0, expr12.getTree());
                    char_literal13=(Token)input.LT(1);
                    match(input,17,FOLLOW_17_in_atom155); 

                    }
                    break;

            }
            retval.stop = input.LT(-1);

                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end atom

    public static class negative_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start negative
    // RoleExpression.g:31:1: negative : NOT -> ^( NOT_DEF ) ;
    public final negative_return negative() throws RecognitionException {
        negative_return retval = new negative_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token NOT14=null;

        CommonTree NOT14_tree=null;
        RewriteRuleTokenStream stream_NOT=new RewriteRuleTokenStream(adaptor,"token NOT");

        try {
            // RoleExpression.g:31:9: ( NOT -> ^( NOT_DEF ) )
            // RoleExpression.g:31:11: NOT
            {
            NOT14=(Token)input.LT(1);
            match(input,NOT,FOLLOW_NOT_in_negative167); 
            stream_NOT.add(NOT14);


            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 31:15: -> ^( NOT_DEF )
            {
                // RoleExpression.g:31:18: ^( NOT_DEF )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(adaptor.create(NOT_DEF, "NOT_DEF"), root_1);

                adaptor.addChild(root_0, root_1);
                }

            }



            }

            retval.stop = input.LT(-1);

                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end negative

    public static class and_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start and
    // RoleExpression.g:32:1: and : AND -> ^( AND_DEF ) ;
    public final and_return and() throws RecognitionException {
        and_return retval = new and_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token AND15=null;

        CommonTree AND15_tree=null;
        RewriteRuleTokenStream stream_AND=new RewriteRuleTokenStream(adaptor,"token AND");

        try {
            // RoleExpression.g:32:5: ( AND -> ^( AND_DEF ) )
            // RoleExpression.g:32:7: AND
            {
            AND15=(Token)input.LT(1);
            match(input,AND,FOLLOW_AND_in_and180); 
            stream_AND.add(AND15);


            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 32:11: -> ^( AND_DEF )
            {
                // RoleExpression.g:32:14: ^( AND_DEF )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(adaptor.create(AND_DEF, "AND_DEF"), root_1);

                adaptor.addChild(root_0, root_1);
                }

            }



            }

            retval.stop = input.LT(-1);

                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end and

    public static class or_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start or
    // RoleExpression.g:33:1: or : OR -> ^( OR_DEF ) ;
    public final or_return or() throws RecognitionException {
        or_return retval = new or_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token OR16=null;

        CommonTree OR16_tree=null;
        RewriteRuleTokenStream stream_OR=new RewriteRuleTokenStream(adaptor,"token OR");

        try {
            // RoleExpression.g:33:4: ( OR -> ^( OR_DEF ) )
            // RoleExpression.g:33:6: OR
            {
            OR16=(Token)input.LT(1);
            match(input,OR,FOLLOW_OR_in_or193); 
            stream_OR.add(OR16);


            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 33:10: -> ^( OR_DEF )
            {
                // RoleExpression.g:33:13: ^( OR_DEF )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(adaptor.create(OR_DEF, "OR_DEF"), root_1);

                adaptor.addChild(root_0, root_1);
                }

            }



            }

            retval.stop = input.LT(-1);

                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end or


 

    public static final BitSet FOLLOW_expr_in_rexpr71 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conjExpr_in_expr89 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_or_in_expr92 = new BitSet(new long[]{0x0000000000010600L});
    public static final BitSet FOLLOW_conjExpr_in_expr95 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_atom_in_conjExpr107 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_and_in_conjExpr110 = new BitSet(new long[]{0x0000000000010600L});
    public static final BitSet FOLLOW_atom_in_conjExpr113 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_negative_in_atom126 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_ID_in_atom129 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_negative_in_atom146 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_atom150 = new BitSet(new long[]{0x0000000000010600L});
    public static final BitSet FOLLOW_expr_in_atom153 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_atom155 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOT_in_negative167 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AND_in_and180 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OR_in_or193 = new BitSet(new long[]{0x0000000000000002L});

}