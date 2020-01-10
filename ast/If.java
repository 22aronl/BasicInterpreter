package ast;

import environment.*;

/**
 *  
 * 
 * @author Aaron Lo
 * @version 
 */
public class If extends StateNode
{
    private Expression exp1;
    private Expression exp2;
    private String op;
    private int lineNumber;
    /**
     * Constructor for objects of class If
     */
    public If(Expression e1,String op, Expression e2, int line)
    {
        exp1 = e1;
        exp2 = e2;
        this.op = op;
        lineNumber = line;
    }

    public void exec(Environment env)
    {
        if(op.equals("="))
        {
            if(exp1.exec(env) == exp2.exec(env))
                env.changeLine(lineNumber);
        }
        else if(op.equals("<"))
        {
            if(exp1.exec(env) < exp2.exec(env))
                env.changeLine(lineNumber);
        }
        else
        {
            if(exp1.exec(env) > exp2.exec(env))
                env.changeLine(lineNumber);
        }
    }
    
    public String toString()
    {
        return "If " + exp1 + " " + op + " " +exp2 + " Then " + lineNumber; 
    }
}
