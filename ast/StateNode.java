package ast;

import environment.*;

/**
 *  An abstract class of a given state
 * 
 * @author Aaron Lo
 * @version 1-12-19
 */
public abstract class StateNode
{
    /**
     * Constructor for objects of class StateNode
     */
    public StateNode()
    {

    }
    
    /**
     * executes the state
     * @param env the environment in which it executes
     */
    public abstract void exec(Environment env);
    
    /**
     * the to string method
     * @return string of the state
     */
    public abstract String toString();
}
