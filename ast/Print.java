package ast;

import environment.*;

/**
 *  
 * 
 * @author Aaron Lo
 * @version 
 */
public class Print extends StateNode
{
    private Expression expression;
    /**
     * Constructor for objects of class Print
     */
    public Print(Expression exp)
    {
        expression = exp;
    }
    
    public void exec(Environment env)
    {
        System.out.println(expression.exec(env));
    }
    
    public String toString()
    {
        return "Print " + expression.toString();
    }
}
