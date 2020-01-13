package ast;

import environment.*;
/**
 *  The end value of a sequence
 * 
 * @author Aaron Lo
 * @version 1-12-19
 */
public class End extends StateNode
{
    /**
     * Executes the code
     * @param env the environment
     */
    public void exec(Environment env)
    {
        env.setContinue(false);
    }
    
    /**
     * The to string of the class
     * @return "End"
     */
    public String toString()
    {
        return "End";
    }
}
