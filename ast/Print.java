package ast;

import environment.*;

/**
 * Prints out an expresion 
 * 
 * @author Aaron Lo
 * @version 1-12-19
 */
public class Print extends StateNode
{
    private Expression expression;
    /**
     * Constructor for objects of class Print
     * @param exp the expression
     */
    public Print(Expression exp)
    {
        expression = exp;
    }
    
    /**
     * This prints out the expression
     * @param env the environment
     */
    public void exec(Environment env)
    {
        System.out.println(expression.exec(env));
    }
    
    /**
     * the to string of the class
     * @return the string
     */
    public String toString()
    {
        return "Print " + expression.toString();
    }
}
