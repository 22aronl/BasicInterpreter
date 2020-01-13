package environment;
import ast.*;
import java.util.*;
/**
 * The environment class of the the program
 * 
 * @author Aaron Lo
 * @version 1-12-19
 */
public class Environment
{
    private HashMap<String, Integer> varMap;
    private Program program;
    /**
     * Constructor for objects of class Environment
     * @param p the program
     */
    public Environment(Program p)
    {
        varMap = new HashMap<String, Integer>();
        program = p;
    }
    
    /**
     * Changes the current line number in the program
     * @param line the line number to be changed
     */
    public void changeLine(int line)
    {
        program.setLineNumber(line);
    }
    
    /**
     * Sets a variable to given number
     * @param name the name of the variable
     * @param number the number to be set
     */
    public void setVariable(String name, int number)
    {
        varMap.put(name, number);
    }
    
    /**
     * Checks if the variable exists
     * @param name the name of the variable
     * @return true if it contains a variable
     */
    public boolean hasVariable(String name)
    {
        return varMap.containsKey(name);
    }
    
    /**
     * Gets the variable with the given name
     * @param name the name of the variable
     * @return the number of the variable
     */
    public int getVariable(String name)
    {
        return varMap.get(name);
    }
    
    /**
     * Sets if the program should continue
     * @param s the boolean of whether to continue
     */
    public void setContinue(boolean s)
    {
        program.setContinue(s);
    }
}
