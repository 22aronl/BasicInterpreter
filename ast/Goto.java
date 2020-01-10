package ast;

import environment.*;

/**
 *  
 * 
 * @author Aaron Lo
 * @version 
 */
public class Goto extends StateNode
{
    private int line;
    /**
     * Constructor for objects of class Goto
     */
    public Goto(int lineNumber)
    {
        line = lineNumber;
    }
    
    public void exec(Environment env)
    {
        env.changeLine(line);
    }
    
    public String toString()
    {
        return "Goto " + line;
    }
}
