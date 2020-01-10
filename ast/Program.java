package ast;

import environment.*;

import java.util.*;
import java.lang.*;
import java.io.*;
/**
 *  
 * 
 * @author Aaron Lo
 * @version 
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
    
    public void setLineNumber(int k)
    {
        currentLineNumber = k;
    }
    
    public void setContinue(boolean s)
    {
        shouldContinue = s;
    }
    
    public void list()
    {
        Set<Map.Entry<Integer, Statement>> set = map.entrySet();
        for(Map.Entry<Integer, Statement> m : set)
        {
            System.out.println(m.getValue());
        }
    }
    
    public void addStatement(Statement state)
    {
        map.put(state.getLine(), state);
    }
    
    public void clear()
    {
        currentLineNumber = 0;
        map = new TreeMap<Integer, Statement>();
        shouldContinue = true;
    }
    
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
