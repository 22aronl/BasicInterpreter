package ast;

import environment.*;

/**
 *  
 * 
 * @author Aaron Lo
 * @version 
 */
public class Let extends StateNode
{
    private String variable;
    private Expression expression;
    /**
     * Constructor for objects of class Let
     */
    public Let(String varName, Expression exp)
    {
        variable = varName;
        expression = exp;
    }
    
    public void exec(Environment env)
    {
        env.setVariable(variable, expression.exec(env));
    }
    
    public String toString()
    {
        return "Let " + variable + " = " + expression.toString();
    }
}
