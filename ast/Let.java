package ast;

import environment.*;

/**
 *  Variable assignment
 * 
 * @author Aaron Lo
 * @version 1-12-19
 */
public class Let extends StateNode
{
    private String variable;
    private Expression expression;
    /**
     * Constructor for objects of class Let
     * @param varName the variable name
     * @param exp the expression
     */
    public Let(String varName, Expression exp)
    {
        variable = varName;
        expression = exp;
    }
    
    /**
     * Sets the variable to expression
     * @param env the environment
     */
    public void exec(Environment env)
    {
        env.setVariable(variable, expression.exec(env));
    }
    
    /**
     * the to string
     * @return the string 
     */
    public String toString()
    {
        return "Let " + variable + " = " + expression.toString();
    }
}
