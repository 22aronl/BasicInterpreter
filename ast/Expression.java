package ast;

import environment.*;

/**
 * Abstract class for all types of Expressions.
 * 
 * @author Shray Alag
 * @version December 19, 2019
 */
public abstract class Expression
{
    /**
     * The execution method that each Expression class needs to implement
     * 
     * @param env the environment for execution
     * @return the result of the Expression
     */
    public abstract int exec(Environment env);
}