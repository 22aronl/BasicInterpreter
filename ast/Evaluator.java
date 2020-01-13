package ast;

import environment.*;

/**
 * This is the class for evaluating two expressions using an operand
 * 
 * @author Shray Alag
 * @version December 19, 2019
 */
public class Evaluator extends Expression
{
    private String operand = null;
    private Expression expression1 = null;
    private Expression expression2 = null;

    /**
     * Constructor
     * 
     * @param expression1 firstExpression
     * @param expression2 secondExpression
     * @param operand the operand to be applied
     */
    public Evaluator(Expression expression1, Expression expression2,
    String operand)
    {
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.operand = operand;
    }

    @Override
    /**
     * Executes the Expression. Each of the two expressions are first evaluated.
     * Next, the operand is used to compute the result. 
     * Supported operands include +, -, *, and /.
     * 
     * @param env the Environment holding the variables
     * @return the integer result from the expression
     */
    public int exec(Environment env)
    {
        int sum = 0;
        int value1 = this.expression1.exec(env);
        if(expression2 == null)
            return value1;
        int value2 = this.expression2.exec(env);

        if (operand.equals("+"))
        {
            sum = value1 + value2;
        }
        if (operand.equals("-"))
        {
            sum = value1 - value2;
        }
        if (operand.equals("*"))
        {
            sum = value1 * value2;
        }
        if (operand.equals("/"))
        {
            sum = value1 / value2;
        }
        return sum;
    }

    @Override
    /**
     * Print the details for debugging
     * 
     * @return the String value
     */
    public String toString()
    {
        String a = this.expression1.toString();
        if(expression2 != null)
            a += " " + this.operand + " "+ this.expression2.toString();
        return a;
    }

}
