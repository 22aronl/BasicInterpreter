package ast;

import environment.*;

/**
 *  This directs to program to a certain line number
 * 
 * @author Aaron Lo
 * @version 1-12-19
 */
public class Goto extends StateNode
{
    private int line;
    /**
     * Constructor for objects of class Goto
     * @param lineNumber the line number to move to
     */
    public Goto(int lineNumber)
    {
        line = lineNumber;
    }
    
    /**
     * This changes the programs line number to its given line number
     * @param env the environment 
     */
    public void exec(Environment env)
    {
        env.changeLine(line);
    }
    
    /**
     * The to string of the method
     * @return "Goto" lineNumber
     */
    public String toString()
    {
        return "Goto " + line;
    }
}
