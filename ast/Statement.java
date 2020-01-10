package ast;


/**
 *  
 * 
 * @author Aaron Lo
 * @version 
 */
public class Statement
{
    private int lineNumber;
    private StateNode node;
    public Statement(int line, StateNode node)
    {
        lineNumber = line;
        this.node = node;
    }
    
    public int getLine()
    {
        return lineNumber;
    }
    
    public StateNode getStateNode()
    {
        return node;
    }
    
    public String toString()
    {
        return lineNumber + " " + node.toString();
    }
}
