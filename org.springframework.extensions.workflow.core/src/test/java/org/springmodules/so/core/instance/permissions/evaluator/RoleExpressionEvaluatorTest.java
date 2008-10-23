package org.springmodules.so.core.instance.permissions.evaluator;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import org.junit.Test;

/**
 * @author janm
 */
public class RoleExpressionEvaluatorTest {
    private RoleExpressionEvaluator evaluator = new AntlrRoleExpressionEvaluator();
    private static final String A = "A";
    private static final String B = "B";
    private static final String C = "C";
    private static final String NOT_A = "!A";
    private static final String NOT_B = "!B";

    @Test
    public void testSimple() {
        assertThat(this.evaluator.evaluate(A, A), equalTo(true));
        assertThat(this.evaluator.evaluate("[Chief executive officer]", "Chief executive officer"), equalTo(true));
        assertThat(this.evaluator.evaluate(A, B), equalTo(false));
        assertThat(this.evaluator.evaluate(NOT_A, B), equalTo(true));
        assertThat(this.evaluator.evaluate(NOT_A, C), equalTo(true));
        assertThat(this.evaluator.evaluate(NOT_A, "His royal highness"), equalTo(true));
        assertThat(this.evaluator.evaluate("publisher && author"), equalTo(false));
    }

    @Test
    public void testConjunction() {
        assertThat(this.evaluator.evaluate(A + "&&" + B, A, B), equalTo(true));
        assertThat(this.evaluator.evaluate(A + "&&" + B, A, C), equalTo(false));
        assertThat(this.evaluator.evaluate(A + "&&" + NOT_B, A, C), equalTo(true));
    }

    @Test
    public void testDisjunction() {
        assertThat(this.evaluator.evaluate(A + "||" + B, A), equalTo(true));
        assertThat(this.evaluator.evaluate(A + "||" + B, B), equalTo(true));
        assertThat(this.evaluator.evaluate(A + "||" + NOT_B, C, B), equalTo(false));
    }

    @Test
    public void testContradiction() {
        assertThat(this.evaluator.evaluate(A + "&&" + NOT_A, A), equalTo(false));
        assertThat(this.evaluator.evaluate(A + "&&" + NOT_A, B), equalTo(false));
    }
    
    @Test
    public void testTautology() {
        assertThat(this.evaluator.evaluate(A + "||" + NOT_A, A), equalTo(true));
        assertThat(this.evaluator.evaluate(A + "||" + NOT_A, B), equalTo(true));
    }

    @Test
    public void testComplex() {
        // A || (B && C)
        String expression = A + " || " + "( " + B + " && " + C + ")";
        assertThat(this.evaluator.evaluate(expression, A), equalTo(true));
        assertThat(this.evaluator.evaluate(expression, B, C), equalTo(true));
        assertThat(this.evaluator.evaluate(expression, B), equalTo(false));
    }

}
