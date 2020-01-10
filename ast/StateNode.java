package ast;

import environment.*;

/**
 *  
 * 
 * @author Aaron Lo
 * @version 
 */
public abstract class StateNode
{
    /**
     * Constructor for objects of class StateNode
     */
    public StateNode()
    {

    }
    
    public abstract void exec(Environment env);
    
    public abstract String toString();
}
