package ast;

import environment.*;
/**
 * This is a comment 
 * 
 * @author Aaron Lo
 * @version 1-12-19
 */
public class Rem extends StateNode
{
    private String s;
    /**
     * Constructor for objects of class Rem
     * @param s what is in the comment
     */
    public Rem(String s)
    {
        this.s = s;
    }
    
    /**
     * the to string of this
     * @return the string version of this
     */
    public String toString()
    {
        return "Rem " + s;
    }
    
    /**
     * nothing
     * @param env the environment
     */
    public void exec(Environment env)
    {
    }
}
