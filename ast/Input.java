package ast;

import java.util.*;
import environment.*;

/**
 *  
 * 
 * @author Aaron Lo
 * @version 
 */
public class Input extends StateNode
{
    private String name;
    /**
     * Constructor for objects of class Input
     */
    public Input(String name)
    {
        this.name = name;
    }
    
    public void exec(Environment env)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("? ");
        int k = sc.nextInt();
        env.setVariable(name, k);
    }
    
    public String toString()
    {
        return "Input " + name;
    }
}