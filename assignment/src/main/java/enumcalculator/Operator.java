package enumcalculator;

import java.util.HashMap;
import java.util.Map;
import java.util.function.IntBinaryOperator;

/**
 * Enum with operations. Example of how to use lambda expressions in an (enum)
 * constructor. This calculator works with an IntBinaryOperator, which means
 * that the result is always an integer; divisions are INTEGER-DIVISIONS. Don't 
 * worry about calculation results that are too big to fit the int data type.
 * (overflow and underflow).
 *
 * @author Pieter van den Hombergh / Richard van den Ham
 */
public enum Operator {
    ADD("+", ( a, b ) -> a + b),
    SUBSTRACT("-", ( a, b ) -> a - b),
    MULTIPLY("*", ( a, b ) -> a * b),
    DIVIDE("/", ( a, b ) -> a / b),
    POWER("**", ( a, b ) -> (int) Math.pow(a, b));

    /**
     * The symbol that identifies the operation.
     */
    private final String symbol;
    /**
     * The operation is a IntBinaryOperator.
     *
     */
    private final IntBinaryOperator computation;

    private static final Map<String, Operator> lookup = new HashMap<>();

    static {
        for (Operator op : Operator.values()) {
            lookup.put(op.getSymbol(), op);
        }
    }

    /**
     * The constructor to create the Operation instances.
     *
     * @param symbol of this operator
     * @param computation the actual method
     */

    Operator(String symbol, IntBinaryOperator computation) {
        this.symbol = symbol;
        this.computation = computation;
    }

    /**
     * Get the operator using its symbol. This method does a linear search
     * through the values of this enum.
     *
     * @param symbol to search
     * @return operation when found, null otherwise.
     */
    public static Operator get(String symbol) {
        return lookup.get(symbol);
    }

    public String getSymbol() {
        return symbol;
    }

    /**
     * Execute the calculation.
     *
     * @param a first param
     * @param b second param
     * @return result of the computation.
     */
    public int compute(int a, int b) {
        if (this == DIVIDE && b == 0) {
            throw new IllegalArgumentException("Cannot be divided by 0");
        }
        return this.computation.applyAsInt(a, b);
    }

    /**
     * Get the symbols of supported operators.
     *
     * @return String array containing all supported operations.
     */
    public static String[] supportedSymbols() {
        //TODO Implement method
        return lookup.keySet().toArray(new String[lookup.size()]);
    }
}
