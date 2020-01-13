package ast;

import environment.*;

import java.util.*;
import java.io.*;
/**
 *  The program
 * 
 * @author Aaron Lo
 * @version 1-12-19
 */
public class Program
{
    private TreeMap<Integer, Statement> map;
    private int currentLineNumber;
    private boolean shouldContinue;
    /**
     * Constructor for objects of class Program
     */
    public Program()
    {
        map = new TreeMap<Integer, Statement>();
        shouldContinue = true;
    }
    
    /**
     * sets the current line number to k
     * @param k the line number to be set at
     */
    public void setLineNumber(int k)
    {
        currentLineNumber = k;
    }
    
    /**
     * sets if the program should continue
     * @param s the boolean of whether to continue
     */
    public void setContinue(boolean s)
    {
        shouldContinue = s;
    }
    
    /**
     * lists all methods
     */
    public void list()
    {
        Set<Map.Entry<Integer, Statement>> set = map.entrySet();
        for(Map.Entry<Integer, Statement> m : set)
        {
            System.out.println(m.getValue());
        }
    }
    
    /**
     * adds a statement to the program
     * @param state the statement to be added
     */
    public void addStatement(Statement state)
    {
        map.put(state.getLine(), state);
    }
    
    /**
     * clears the program for a new run
     */
    public void clear()
    {
        currentLineNumber = 0;
        map = new TreeMap<Integer, Statement>();
        shouldContinue = true;
    }
    
    /**
     * runs the program
     * 
     */
    public void run()
    {
        Environment env = new Environment(this);
        Map.Entry<Integer, Statement> m = map.firstEntry();
        currentLineNumber = m.getKey()+1;
        m.getValue().getStateNode().exec(env);
        int k = map.size();
        for(int i = 1;shouldContinue; i++)
        {
            Map.Entry<Integer, Statement> p = map.ceilingEntry(currentLineNumber);
            if(p == null)
                break;
            currentLineNumber = p.getKey()+1;
            p.getValue().getStateNode().exec(env);
        }
    }
}
