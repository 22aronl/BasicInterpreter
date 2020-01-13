package ast;

import environment.*;

/**
 *  An If statement
 * 
 * @author Aaron Lo
 * @version 1-12-19
 */
public class If extends StateNode
{
    private Expression exp1;
    private Expression exp2;
    private String op;
    private int lineNumber;
    /**
     * Constructor for objects of class If
     * @param e1 the first expression
     * @param op the operator
     * @param e2 the second expression
     * @param line the line number to jump to
     */
    public If(Expression e1,String op, Expression e2, int line)
    {
        exp1 = e1;
        exp2 = e2;
        this.op = op;
        lineNumber = line;
    }

    /**
     * Exectutes the program
     * if( e1 op e2) then it jumps to linenumber line
     * @param env the environment
     */
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
    
    /**
     * The toString of the method
     * @return the to string of the IFstatement
     */
    public String toString()
    {
        return "If " + exp1 + " " + op + " " +exp2 + " Then " + lineNumber; 
    }
}
