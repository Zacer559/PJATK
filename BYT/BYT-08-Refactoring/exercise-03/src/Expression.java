import java.util.HashMap;
import java.util.Map;

public class Expression {
    //changed to final
    private final char op;

    private Expression left;

    private Expression right;

    private int constant;
    //map of expressions declaration, using lambdas
    private final Map<Character, IOperation> map;
    {
        map = new HashMap<>();
        map.put('c', () -> constant);
        map.put('+', () -> left.evaluate() + right.evaluate());
        map.put('-', () -> left.evaluate() - right.evaluate());
        map.put('/', () -> left.evaluate() / right.evaluate());
        map.put('*', () -> left.evaluate() * right.evaluate());
    }

    public Expression(int constant) {
        this.op = 'c';
        this.constant = constant;
    }

    public Expression(char op, Expression left, Expression right) {
        this.op = op;
        this.left = left;
        this.right = right;
    }

    public int evaluate() {
        return map.get(op).calculate();
    }
}
