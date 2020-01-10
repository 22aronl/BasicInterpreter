package ast;

import java.util.List;

import environment.*;

/**
 * Expression class for a list of expressions with relops.
 * Evaluates each pair of expressions with the corresponding relop.
 * 
 * @author Shray Alag
 * @version December 19, 2019
 */
public class ExpressionExp extends Expression
{
    private List<Expression> addExpressions; // holds AddExpression (s)
    private List<String> relops; // holds strings

    /**
     * Constructor, instantiates instance variables
     * 
     * @param addExpressions list of expressions to be combined.
     * @param relops operators used to combine expressions
     */
    public ExpressionExp(List<Expression> addExpressions, List<String> relops)
    {
        this.addExpressions = addExpressions;
        this.relops = relops;
    }

    @Override
    /**
     * Executes the expression(s) joined by relative operators.
     * 
     * If there are no relops, then the first expression is evaluated. 
     * 
     * If there are relops, the second expression is the one that matches the
     * index of the operand.
     * 
     * @param env the Environment
     * @return the result of the execution: -2 if True, -1 if False
     */
    public int exec(Environment env)
    {
        Expression firstExpression = addExpressions.get(0);
        if (relops == null || relops.size() == 0)
        { // simply evaluates the solitairy expression
            return firstExpression.exec(env);
        }

        // one or more relops: boolean expression
        for (int i = 0; i < relops.size(); i++)
        {
            // Make sure there are enough expressions
            if (addExpressions.size() > i + 1)
            {
                Expression secondExpression = addExpressions.get(i + 1);
                String relop = relops.get(i);

                // Check each case
                if (relop.equals(">"))
                {
                    if (firstExpression.exec(env) > secondExpression.exec(env))
                        return -2;
                    return -1; // -2 True, -1 False
                }
                if (relop.equals("<"))
                {
                    if (firstExpression.exec(env) < secondExpression.exec(env))
                        return -2;
                    return -1;
                }
                if (relop.equals("="))
                {
                    if (firstExpression.exec(env) == secondExpression.exec(env))
                        return -2;
                    return -1;
                }
                if (relop.equals(">="))
                {
                    if (firstExpression.exec(env) >= secondExpression.exec(env))
                        return -2;
                    return -1;
                }
                if (relop.equals("<="))
                {
                    if (firstExpression.exec(env) <= secondExpression.exec(env))
                        return -2;
                    return -1;
                }
                if (relop.equals("<>"))
                {
                    if (firstExpression.exec(env) != secondExpression.exec(env))
                        return -2;
                    return -1;
                }
            }
        }
        return -1; // all conditions are FALSE
    }

    @Override
    /**
     * Print for debugging
     */
    public String toString()
    {
        String s = "";
        if(relops!=null)
        {
            for(int i = 0; i < relops.size(); i++)
            {
                s+= addExpressions.get(i) + " " + relops.get(i) + " ";
            }
        }
        s += addExpressions.get(addExpressions.size() - 1);
        return s;
    }
}
