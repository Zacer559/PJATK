import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ExpressionTest {

    @Test
    public void testConstant() {
        Expression e = new Expression(-43);
        assertEquals(e.evaluate(), -43);
    }

    @Test
    public void testAddition() {
        Expression e = new Expression('+', new Expression(100), new Expression(
                -100));
        assertEquals(e.evaluate(), 0);
    }

    @Test
    public void testSubtraction() {
        Expression e = new Expression('-', new Expression(100), new Expression(
                -100));
        assertEquals(e.evaluate(), 200);
    }

    @Test
    public void testMultiplication() {
        Expression e = new Expression('*', new Expression(100), new Expression(
                -100));
        assertEquals(e.evaluate(), -10000);
    }

    @Test
    public void testDivision() {
        Expression e = new Expression('/', new Expression(100), new Expression(
                -100));
        assertEquals(e.evaluate(), -1);
    }

    @Test
    public void testComplexExpression() {
        // 1+2-3*4/5
        Expression e = new Expression('-', new Expression('+',
                new Expression(1), new Expression(2)), new Expression('/',
                new Expression('*', new Expression(3), new Expression(4)),
                new Expression(5)));
        assertEquals(e.evaluate(), 1);
    }

}
