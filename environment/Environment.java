package environment;
import ast.*;
import java.util.*;
/**
 *  
 * 
 * @author Aaron Lo
 * @version 
 */
public class Environment
{
    private HashMap<String, Integer> varMap;
    public Program program;
    /**
     * Constructor for objects of class Environment
     */
    public Environment(Program p)
    {
        varMap = new HashMap<String, Integer>();
        program = p;
    }
    
    public void changeLine(int line)
    {
        program.setLineNumber(line);
    }
    
    public void setVariable(String name, int number)
    {
        varMap.put(name, number);
    }
    
    public boolean hasVariable(String name)
    {
        return varMap.containsKey(name);
    }
    
    public int getVariable(String name)
    {
        return varMap.get(name);
    }
    
    public void setContinue(boolean s)
    {
        program.setContinue(s);
    }
}
