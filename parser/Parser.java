package parser;

import ast.*;
import environment.*;
import scanner.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;
import java.io.*;

/**
 * This is the parser for the BASIC interpreter, modified from the parser lab.
 * This parser is able to parse
 * statements (LET, REM, PRINT, INPUT, GOTO, IF, END)
 * 
 * @author Shray Alag and Aaron Lo
 * @version January 6, 2020
 */
public class Parser
{
    private scanner.Scanner scanner; // scanner instance var.
    private String currentToken; // is the current token
    private int lineNumber;

    /**
     * Parser constructor that takes in a scanner and instantiates instance
     * variables Scanner scanner, String currentToken, and Map<String, Integer>
     * mapVar
     * 
     * @param scan is a scanner which will feed tokens into the parser
     */
    public Parser(scanner.Scanner scan)
    {
        scanner = scan;
        currentToken = "";
        try
        { // need to try and catch these things as there may be an error
            currentToken = scanner.nextToken();
        } 
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        lineNumber = 10;
    }

    /**
     * eat method "eats" the current String and transfers the currentToken to
     * the next String. This method calls on a scanner method.
     * 
     * @param expected is the string that is expected
     * @throws IOException if the expected string 
     * and the real string are different
     */
    private void eat(String expected) throws IOException
    {
        if (expected.equals(currentToken))
        {
            currentToken = scanner.nextToken();
        } else
        {
            throw new IllegalArgumentException(expected
                + " was the expected token, but " + currentToken + "");
        }
    }

    /**
     * 
     */
    private Statement parseLine() throws IOException
    {
        int lineNum = 0;
        if(Integer.parseInt(currentToken) > 0) 
        { //checks if currentToken is a line number which it should be
            lineNum = Integer.parseInt(currentToken);
            eat(currentToken);
            if(currentToken.equals("LET"))
            {
                eat(currentToken);
                String varName = currentToken;
                eat(currentToken);
                eat("=");
                Expression exp = parseExpression();
                return new Statement(lineNum, new Let(varName, exp));
            }
            else if(currentToken.equals("PRINT"))
            {
                eat(currentToken);
                Expression exp = parseExpression();
                return new Statement(lineNum, new Print(exp));
            }
            else if(currentToken.equals("INPUT"))
            {
                eat(currentToken);
                String var = currentToken;
                eat(currentToken);
                return new Statement(lineNum, new Input(var));
            }
            else if(currentToken.equals("GOTO"))
            {
                eat(currentToken);
                int lineNumber = Integer.parseInt(currentToken);
                eat(currentToken);
                return new Statement(lineNum, new Goto(lineNumber));
            }
            else if(currentToken.equals("IF"))
            {
                eat(currentToken);
                Expression exp1 = parseExpression();
                String operand = currentToken;
                eat(currentToken);
                Expression exp2 = parseExpression();
                eat("THEN");
                int lineNumber = Integer.parseInt(currentToken);
                eat(currentToken);
                return new Statement(lineNum, new 
                    If(exp1, operand, exp2, lineNumber));
            }
            else if(currentToken.equals("END"))
            {
                eat(currentToken);
                return new Statement(lineNum, new End());
            }
            else if(currentToken.equals("REM"))
            {
                return new Statement(lineNum, new Rem(scanner.skipLine()));
            }
        }
        else
        {
            if(currentToken.equals("LET"))
            {
                eat(currentToken);
                String varName = currentToken;
                eat(currentToken);
                eat("=");
                Expression exp = parseExpression();
                return new Statement(lineNum, new Let(varName, exp));
            }
            else if(currentToken.equals("PRINT"))
            {
                eat(currentToken);
                Expression exp = parseExpression();
                return new Statement(lineNum, new Print(exp));
            }
            else if(currentToken.equals("INPUT"))
            {
                eat(currentToken);
                String var = currentToken;
                eat(currentToken);
                return new Statement(lineNum, new Input(var));
            }
        }
        return null;
    }

    public void parseLineByLine(Program program) throws IOException
    {
        if(currentToken.equals("RUN"))
        {
            program.run();
        }
        else if(currentToken.equals("LIST"))
        {
            program.list();
        }
        else if(currentToken.equals("CLEAR"))
        {
            program.clear();
        }
        else if(currentToken.equals("HELP"))
        {
            System.out.println("TOOO BAD");
        }
        else if(currentToken.equals("QUIT"))
            System.exit(0);
        else if(isDigit(currentToken))
        {
            Statement st = parseLine();
            if(st!=null)
                program.addStatement(st);
        }
    }

    public Program parseProgram() throws IOException
    {
        Program program = new Program();
        while(true)
        {
            if(currentToken.equals("RUN"))
            {
                eat(currentToken);
                program.run();
            }
            else if(currentToken.equals("LIST"))
            {
                eat(currentToken);
                program.list();
            }
            else if(currentToken.equals("CLEAR"))
            {
                eat(currentToken);
                program = new Program();
            }
            else if(currentToken.equals("HELP"))
            {
                eat(currentToken);
                System.out.println("TOOO BAD");
            }
            else if(currentToken.equals("QUIT"))
                System.exit(0);
            else
            {
                Statement st = parseLine();
                if(st!=null)
                    program.addStatement(st);
            }
        }
    }
    
    public boolean isDigit(String s)
    {
        try
        {
            Integer.parseInt(s);
        }
        catch(Exception e)
        {
            return false;
        }
        return true;
    }

    ////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////

    /**
     * parseNegExpression builds and evaluates the value of NegExpressions,
     * namely those that have either a positive or negative value.
     * NegExpressions are the base of all expression types.
     * 
     * @precondition current token is a negExpression 
     * @postcondition the expression is eaten and its value is returned
     * 
     * @return an Expression object holding the parsed NegExpression
     * @throws IOException if currentToken is not a number
     */
    public Expression parseNegExpression() throws IOException
    {
        Expression expression = null;
        String s = this.currentToken;
        while (s.equals("-"))
        {
            eat(currentToken);
            // want to build tree from left side
            expression = new Evaluator(parseValue(), null, "-");
            s = currentToken;
        }
        return new Evaluator(parseValue(), expression, "+");
    }

    /**
     * parseValue extracts either an id, a number, or a parenthesis-enclosed Expression
     * and returns that Expression.
     * @precondition current token is a value (either id, number, or Expression) 
     * @postcondition the Value term has been eaten and is returned
     * 
     * @return an Expression object from the parsed value
     * @throws IOException if currentToken is not a number
     */
    public Expression parseValue() throws IOException
    {
        if (currentToken.equals("("))
        {
            eat("(");
            Expression x = parseExpression();
            // same as parse Expression since only one parameter
            eat(")");
            return x;
        }
        if (scanner.isDigit(currentToken.charAt(0)))
        { // is a number
            return parseNumber();
        }
        char c = currentToken.charAt(0);
        int ascii = (int) c;
        if (((ascii) > 64 && (ascii) < 91)
        || (((ascii) > 96 && (ascii) < 123)) && !isKeyword())
        { // is an ID
            String x = currentToken;
            eat(currentToken);
            return new Value(x);
        }
        return null;
    }

    public Value parseNumber() throws IOException
    {
        int k = Integer.parseInt(currentToken);
        eat(currentToken);
        return new Value(k);
    }

    /**
     * Check to see if the currentToken is one of the keywords that
     * implies an id.
     * 
     * @return true if it is a keyword
     */
    private boolean isKeyword()
    {
        return ((currentToken.equals("LET"))
            || (currentToken.equals("PRINT"))
            || (currentToken.equals("INPUT"))
            || (currentToken.equals("GOTO")) 
            || (currentToken.equals("IF"))
            || (currentToken.equals("END"))
            || (currentToken.equals("RUN"))
            || (currentToken.equals("LIST"))
            || (currentToken.equals("CLEAR"))
            || (currentToken.equals("HELP"))
            || (currentToken.equals("QUIT")));
    }

    /**
     * parseMultExpression() parses expressions involving * and /.
     * Deals with multiple operand terms through a
     * while loop that creates nested expressions.
     *  
     * @precondition currentToken is an Expression that may involve * and /
     * @postcondition the expression has been eaten and its value returned in an 
     * Expression object
     * 
     * @return Expression object that can evaluate the expression
     * @throws IOException if the currentToken is not the expected token
     */
    public Expression parseMultExpression() throws IOException
    {
        Expression expression = parseNegExpression();
        String r = this.currentToken;
        while (r.equals("*") || r.equals("/"))
        {
            eat(currentToken);
            // want to build tree from left side
            expression = new Evaluator(parseNegExpression(), expression, r);
            r = currentToken;
        }
        return expression;
    }

    /**
     * parseAddExpression() parses expressions involving + and -.
     * Deals with multiple operand terms through a
     * while loop that creates nested expressions.
     *  
     * @precondition currentToken is an Expression that may involve + and -
     * @postcondition the expression has been eaten and its value returned in an 
     * Expression object
     * 
     * @return Expression object that can evaluate the expression
     * @throws IOException if the currentToken is not the expected token
     */
    public Expression parseAddExpression() throws IOException
    {
        Expression expression = parseMultExpression();
        String r = this.currentToken;
        while (r.equals("+") || r.equals("-"))
        {
            eat(currentToken);
            // want to build tree from left side
            expression = new Evaluator(parseMultExpression(), expression, r);
            r = currentToken;
        }
        return expression;
    }

    /**
     * parseExpression deals with +,-,*, and / in expressions. It also deals
     * with multiple operators through while loops. AddExps contain nested MultExps due
     * to order precedence.
     * 
     * @precondition currentToken is an expression 
     * @postcondition all of the elements of the expression have been eaten
     * 
     * @return an Expression object that can evaluate the given expression
     * @throws IOException if currentToken does not match with the expected token
     */
    public Expression parseExpression() throws IOException
    {
        List<Expression> addExps = new ArrayList<Expression>();
        List<String> relops = new ArrayList<String>();

        addExps.add(parseAddExpression());
        if (!isRelop())
        {
            relops = null; // just in case
        }
        while (isRelop())
        {
            relops.add(currentToken);
            eat(currentToken);
            addExps.add(parseAddExpression());
        }
        // ExpressionExp is a concrete class that extends abstract Expression
        return new ExpressionExp(addExps, relops);
    }

    /**
     * Returns if the current token is a relative operator. Functionality to
     * check for <>, <=, and >=.
     * 
     * @return if the currentToken is a relop
     */
    public boolean isRelop()
    {
        return false;
    }

    /////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////   

}
