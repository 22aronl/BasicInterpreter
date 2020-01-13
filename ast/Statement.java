package ast;


/**
 *  A statement
 * 
 * @author Aaron Lo
 * @version 1-12-19
 */
public class Statement
{
    private int lineNumber;
    private StateNode node;
    /**
     * this is the constructor
     * @param line the line number the statment is at
     * @param node the state the node is at
     */
    public Statement(int line, StateNode node)
    {
        lineNumber = line;
        this.node = node;
    }
    
    /**
     * gets the line number
     * @return the number
     */
    public int getLine()
    {
        return lineNumber;
    }
    
    /**
     * this gets the state node
     * @return the statenode
     */
    public StateNode getStateNode()
    {
        return node;
    }
    
    /**
     * the to string of the entire thing
     * @return the string
     */
    public String toString()
    {
        return lineNumber + " " + node.toString();
    }
}
