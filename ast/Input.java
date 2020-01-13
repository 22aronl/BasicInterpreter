package ast;

import java.util.*;
import environment.*;

/**
 *  Inputs in a number and sets the variable to that number
 * 
 * @author Aaron Lo
 * @version 1-12-19
 */
public class Input extends StateNode
{
    private String name;
    /**
     * Constructor for objects of class Input
     * @param name the variable name
     */
    public Input(String name)
    {
        this.name = name;
    }
    
    /**
     * Executes the input
     * @param env the environment
     */
    public void exec(Environment env)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("? ");
        int k = sc.nextInt();
        env.setVariable(name, k);
    }
    
    /**
     * The to string method
     * @return the string 
     */
    public String toString()
    {
        return "Input " + name;
    }
}