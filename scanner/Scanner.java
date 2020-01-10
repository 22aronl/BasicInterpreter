package scanner;

import java.io.*;

/**
 * Scanner is a simple scanner for Compilers and Interpreters 
 * @author Aaron Lo
 * @version 9-5-19
 *  
 * Usage: 
 *  This is constructed when an InputStream is passed in (or a string). This will
 *      then use the method nextToken() to parse the next String in the file
 * 
 */
public class Scanner
{
    private BufferedReader in;
    private char currentChar;
    private boolean eof;
    /**
     * Scanner constructor for construction of a scanner that 
     * uses an InputStream object for input.  
     * Usage: 
     * FileInputStream inStream = new FileInputStream(new File(<file name>);
     * Scanner lex = new Scanner(inStream);
     * @param inStream the input stream to use
     */
    public Scanner(InputStream inStream)
    {
        in = new BufferedReader(new InputStreamReader(inStream));
        eof = false;
        getNextChar();
    }

    /**
     * Scanner constructor for constructing a scanner that 
     * scans a given input string.  It sets the end-of-file flag an then reads
     * the first character of the input string into the instance field currentChar.
     * Usage: Scanner lex = new Scanner(input_string);
     * @param inString the string to scan
     */
    public Scanner(String inString)
    {
        in = new BufferedReader(new StringReader(inString));
        eof = false;
        getNextChar();
    }

    /**
     * Method: getNextChar
     * This iterates the currentCharacter
     *  forward a character and catches all exceptions
     */
    private void getNextChar()
    {
        try
        {
            int i = in.read();
            if(i == -1)
                eof = true;
            currentChar = (char)i;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Method: eat
     * This iterates the function with the next char and then sets the currentChar to the 
     *  next character in the order
     * @param expected the expected char
     * @throws IOException if the expected and currentchar don't match up
     */

    private void eat(char expected) throws IOException
    {
        if(currentChar != expected)
            throw new IOException("Illegal Character : expected " + currentChar
                + " but found " + expected);
        getNextChar();
    }

    /**
     * Method: hasNext
     * @return true if it has next; otherwise false
     */
    public boolean hasNext()
    {
        return !eof;
    }

    /**
     * This checks in the given char is a digit
     * @param unknown the given char
     * @return true if unknown is a digit; otherwise false
     */
    public static boolean isDigit(char unknown)
    {
        try
        {
            Integer.parseInt(String.valueOf(unknown));
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }

    /**
     * Checks if the given unknown is a letter
     * @param unknown the given letter
     * @return true if it is a letter; otherwise false
     */
    public static boolean isLetter(char unknown)
    {
        return isLowerCase(unknown) || isUpperCase(unknown);
    }

    /**
     * Checks is the letter is lower case
     * @param unknown the given letter
     * @return true if hte letter is lower case; false otherwise
     */
    private static boolean isLowerCase(char unknown)
    {
        return !String.valueOf(unknown).toUpperCase().equals(String.valueOf(unknown));
    }

    /**
     * Checks is the letter is upper case
     * @param unknown the given letter
     * @return true if the letter is upper case; false otherwise
     */
    private static boolean isUpperCase(char unknown)
    {
        return !String.valueOf(unknown).toLowerCase().equals(String.valueOf(unknown));
    }

    /**
     * Checks is this is a white space
     * @param unknown the given char
     * @return true if it is a white space; otherwise false
     */
    public static boolean isWhiteSpace(char unknown)
    {
        String a = unknown + "";
        return a.equals(" ") || a.equals("\t") || a.equals("\r") || a.equals("\n");
    }

    /**
     * This scans the number
     * @return the number
     * @throws IOException this is thrown when there is a scanning error
     */
    private String scanNumber() throws IOException
    {
        String temp = "";
        while(!eof && isDigit(currentChar))
        {
            temp += currentChar;
            eat(currentChar);
        }
        return temp;
    }

    /**
     * This scans the identifier
     * @return the next identifier
     * @throws IOException this is thrown when there is a scanning error
     */
    private String scanIdentifier() throws IOException
    {
        String temp = "";
        while(!eof && (isLetter(currentChar) || isDigit(currentChar)))
        {
            temp += currentChar;
            eat(currentChar);
        }
        return temp;
    }

    /**
     * This scans the operator
     * @return the next operator
     * @throws IOException this is thrown when there is a scanning error
     */
    private String scanOperator() throws IOException
    {
        String temp = String.valueOf(currentChar);
        eat(currentChar);
        if(currentChar == '=')
        {
            temp+=currentChar;
            eat(currentChar);
        }
        else if(temp.equals("<") && currentChar == '>')
        {
            temp+=currentChar;
            eat(currentChar);
        }
        return temp;
    }

    /**
     * Iterates through the whiteSpaces
     * @return the nextToken
     * @throws IOException this is thrown when there is a scanning error
     */
    private String scanWhiteSpaces() throws IOException
    {
        while(!eof && isWhiteSpace(currentChar))
            eat(currentChar);
        return nextToken();
    }

    /**
     * Skips the line
     * @throws IOException this is thrown when there is a scanning error
     */
    private String scanSingleLine() throws IOException
    {
        String s = "";
        try 
        {
            s =  in.readLine();
        }
        catch(IOException e) 
        {
            e.printStackTrace();
        }
        eat(currentChar);
        return s;
    }

    /**
     * Scans multiple lines until it reaches end of line
     * @throws IOException  this is thrown when there is a scanning error
     */
    private void scanMultipleLines() throws IOException
    {
        eat(currentChar);
        while(!eof)
        {
            while(!eof && currentChar != '*')
                eat(currentChar);
            eat(currentChar);
            if(currentChar == '/')
                break;
        }
        eat(currentChar);
    }

    /**
     * Scans the comment and or divide
     * @return the string of the next possible thing
     * @throws IOException this is thrown when there is a scanning error
     */
    private String scanComment() throws IOException
    {
        eat(currentChar);
        if(currentChar == '/')
            scanSingleLine();
        else if(currentChar == '*')
            scanMultipleLines();
        else
            return "/";
        return nextToken();
    }

    /**
     * Checks if this is a special character
     * @param unknown the given character
     * @return true if the special character is known; otherwise false
     */
    public static boolean isSpecialCharacter(char unknown)
    {
        String a = String.valueOf(unknown);
        return a.equals("=") || a.equals("+") || a.equals("-") || a.equals("*")
            || a.equals("/") || a.equals("%") || a.equals("(") || a.equals(")")
            || a.equals("<") || a.equals(">") || a.equals(":") || a.equals(";")
            || a.equals(",");
    }
    
    public String skipLine() throws IOException
    {
        return scanSingleLine();
    }

    /**
     * Method: nextToken of the file
     * @return the nextToken in String
     * @throws IOException this is thrown when there is a scanning error
     */
    public String nextToken() throws IOException
    {
        if(eof)
        {
            return "END OF FILE UNIQUEBYTECODETHATSHOULDNOTBEREPLICATED ERROR3506618469";
        }

        if(isDigit(currentChar)) return scanNumber();
        else if(isLetter(currentChar)) return scanIdentifier();
        else if(currentChar == '/') return scanComment();
        else if(isWhiteSpace(currentChar)) return scanWhiteSpaces();
        else if(isSpecialCharacter(currentChar)) return scanOperator();
        else if(currentChar == '.') return ".";
        throw new IOException("Does Not Know This Character: " + currentChar);
    }    
}
