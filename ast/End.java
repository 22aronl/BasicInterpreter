package ast;

import environment.*;
/**
 *  
 * 
 * @author Aaron Lo
 * @version 
 */
public class End extends StateNode
{
    public void exec(Environment env)
    {
        env.setContinue(false);
    }
    
    public String toString()
    {
        return "End";
    }
}
