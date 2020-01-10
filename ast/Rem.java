package ast;

import environment.*;
/**
 *  
 * 
 * @author Aaron Lo
 * @version 
 */
public class Rem extends StateNode
{
    private String s;
    /**
     * Constructor for objects of class Rem
     */
    public Rem(String s)
    {
        this.s = s;
    }
    
    public String toString()
    {
        return "Rem " + s;
    }
    
    public void exec(Environment env)
    {
    }
}
