import parser.*;
import scanner.*;
import environment.*;
import ast.*;
import java.util.*;
import java.io.*;
/**
 *  Executes the program
 * 
 * @author Aaron Lo
 * @version 1-12-19
 */
public class ProgramExecutor
{
    private static java.util.Scanner sc;
    /**
     * runs the program
     * @param args nothing
     * @throws IOException unknown char
     */
    public static void main(String[] args) throws IOException
    {
        System.out.println("Would you like to type in to console or load a file?");
        String string = "";
        while(true)
        {
            System.out.println("Type in 'Y' for type in and 'N' for loading a file");
            sc = new java.util.Scanner(System.in);
            string = sc.next();
            if(string.equalsIgnoreCase("Y") || string.equalsIgnoreCase("N"))
                break;
            System.out.println("Invalid Input: Please try again");
        }
        if(string.equalsIgnoreCase("quit"))
            System.exit(0);
        else if(string.equalsIgnoreCase("Y"))
            createProgram();
        else
            loadFile();
    }

    /**
     * THis can create a file in which consol commands are logged
     * @throws IOException unknown
     */
    public static void createProgram() throws IOException
    {
        System.out.print("Would you like to create a new file or add into a currrent file?");
        System.out.println(" 'Y' for Yes and 'N' for No");
        String s = "";
        do
        {
            s = sc.next();
            if(s.equals("N"))
            {
                typeIn(null);
                break;
            }
            else if(s.equals("Y"))
            {
                System.out.println("What is the file name?");
                String fileName = sc.next();
                File a = new File("testFile/" +fileName + ".txt");
                PrintWriter out = null;
                try
                {
                    out = new PrintWriter(a);
                    if(a.exists())
                    {
                        BufferedReader br = new BufferedReader(new FileReader(a));
                        String apple = "";
                        do
                        {
                            out.println(apple);
                            apple = br.readLine();
                        }
                        while(apple != null);
                    }
                }
                catch(IOException e)
                {
                    break;
                }
                typeIn(out);
                break;
            }
            else
            {
                System.out.print("Invalid Input. Please try again.");
                System.out.println(" 'Y' for a new file and 'N' for no");
            }
        }
        while(true);
    }

    /**
     * this allows the user to type into the program
     * @param out the printwriter-> can be null
     * @throws IOException unreachable file
     */
    public static void typeIn(PrintWriter out) throws IOException
    {
        System.out.println("Please type in your program here: 'HELP' for more instructions");
        Program program = new Program();
        String s = "100";
        while(true)
        {
            s= sc.nextLine();
            if(out != null)
                out.println(s);
            if(out != null && s.equals("QUIT"))
                out.close();
            scanner.Scanner scan = new scanner.Scanner(s+ "\n");
            Parser parser = new Parser(scan);
            parser.parseLineByLine(program);
            s = "100";
        }
        
    }

    /**
     * this loads a file into the program
     * @throws IOException if the file can't be found
     */
    public static void loadFile() throws IOException
    {
        String s = "";
        boolean flag = true;
        do
        {
            flag = true;
            System.out.print("Which file would you like to open?");
            System.out.println(" --Help for available files --Quit to quit");
            s = sc.next();
            if(s.equalsIgnoreCase("QUIT"))
            {
                System.exit(0);
            }
            if(s.equalsIgnoreCase("Help"))
            {
                File[] fileList = new File("testFile").listFiles();
                for(int i = 0; i < fileList.length; i++)
                    System.out.println(fileList[i].getName());
                flag = false;
            }

            File file = new File("testFile/"+s);
            if(flag && !file.exists())
            {
                System.out.println("This file does not exist. Please try again:");
                flag = false;
            }
        }
        while(!flag);
        scanner.Scanner scan = new scanner.Scanner(new FileInputStream("testFile/"+s));
        Parser parser = new Parser(scan);
        parser.parseProgram();
    }
}
