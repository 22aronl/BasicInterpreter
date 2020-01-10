package ast;

import environment.*;

/**
 * An Expression that is also a Value. 
 * The value could be an expression, int, or a String representing an id.
 * 
 * @author Shray Alag
 * @version December 19, 2019
 */
public class Value extends Expression
{
    private int value;
    private boolean varName = false;
    private Expression exp = null;
    private String idName;

    /**
     * Constructor
     * 
     * @param numberVal the integer value of the Value term
     */
    public Value(int numberVal)
    {
        value = numberVal;
    }

    /**
     * Constructor
     *
     * @param expression the Expression passed in
     */
    public Value(Expression expression)
    {
        exp = expression;
    }

    /**
     * Constructure
     * 
     * @param idName the String name of the id
     */
    public Value(String idName)
    {
        varName = true;
        this.idName = idName;
    }

    @Override
    /**
     * Executes the Value. If there exists a variable name, the value associated with
     * that variable is returned. If there is an expression, it is executed
     * and its value is returned.
     * 
     * @param env the Environment
     * @return the integer result of the execution
     */
    public int exec(Environment env)
    {
        if (varName)
        {
            if (env.hasVariable(idName))
            {
                value = env.getVariable(idName);
            }
        }
        if (exp != null)
        {
            value = exp.exec(env);
        }

        return value;
    }

    @Override
    /**
     * Print for debugging
     */
    public String toString()
    {
        if(!varName && exp == null)
            return value + "";
        else if(exp == null)
            return idName;
        else
            return exp.toString();
    }

}
