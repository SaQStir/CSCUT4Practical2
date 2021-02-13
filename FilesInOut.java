import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import javax.swing.*;
import java.lang.Number;
import java.io.File;

/**
 * 
 * CSCU9T4 Java strings and files exercise.
 *
 */
public class FilesInOut {

    //scenario tells us to output in all caps or not
    public static boolean scenario = false;


    public static void main(String[] args)
    {
        //file locations
        String fileIn = "";
        String fileOut = "";


        Scanner sc = new Scanner(System.in);

        //checking through args for flags
        int argI = 0;
        if (args[0].equals("-u"))
        {
            argI = 1;
            scenario = true;
        }

        //checking if file locations are valid
        if (args[argI].equals(null))
        {
            System.out.println("No output file location input");
        }
        else if(checkFile(args[argI]))
        {
            fileIn = args[argI];
            System.out.println("File successfully located");
        }
        else
        {
            System.out.println("Error: please makes sure you input the right input file name");
        }

        if (args[argI+1].equals(null))
        {
            System.out.println("No output file location input");
        }
        else if(checkFile(args[argI+1]))
        {
            fileOut = args[argI+1];
            System.out.println("File successfully located");
        }
        else
        {
            System.out.println("Error: please makes sure you input the right output file name");
        }

        //update file
        updateFile(fileIn, fileOut);


    }
    // main

    public static Boolean checkFile(String inputFileName)
    {
        try
        {
                File inputFile = new File(inputFileName);
                Scanner inFile = new Scanner (inputFile);
        }
            catch (IOException e)
            {
                System.err.println("IOException: " + e.getMessage() + "not found");
                return false;
            }
        return true;
    }
    //checkInputFile

    //scanner for inputfile
    public static void updateFile(String inputLoc, String outputLoc)
    {
        File inputFile = new File(inputLoc);
        try (Scanner sc = new Scanner(inputFile, StandardCharsets.UTF_8.name()))
        {
            while(sc.hasNextLine())
            {
                updateFiledWrite(outputLoc,sc.nextLine());
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();;
        }
    }
    //updateFile

    //writer for outputFile
    public static void updateFiledWrite(String outputloc, String input)
    {
        File outputFile = new File(outputloc);
        try (FileWriter fw = new FileWriter(outputFile, true))
        {
            PrintWriter pw = new PrintWriter(fw);
            pw.write(updateString(input) + "\r\n");
            pw.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();;
        }
    }
    //UpdateFileWrite

    //Method to change line from input file
    public static String updateString(String input)
    {
        String update = "";
        StringBuffer change = new StringBuffer(input);

        for(int i = 0; i < input.length(); i ++)
        {
            //To format date
            if (tryParseInt(change.substring(i)))
            {
                update = update + input.substring(i, i+2) + "/" + input.substring(i+3 , i+5) + "/" + input.substring(i+4);
                change.replace(i, input.length(), update);
                break;
            }
            //to upper case
            else
            {
                if( i == 0 || Character.isWhitespace(change.charAt((i-1))))
                {
                    if(Character.isWhitespace(change.charAt((i+1))))
                    {
                        update = input.charAt(i) + ".";
                        change.replace(i, i+1, update);
                    }
                    char temp = Character.toUpperCase(change.charAt(i));
                    change.replace(i, i+1, Character.toString(temp));
                }
            }
        }
        //checking for flags
        if (scenario)
        {
            return change.toString().toUpperCase();
        }
        else
            return change.toString();
    }
    //updateString

    //method to check if string is number or not
    public static boolean tryParseInt(String intChar)
    {
        try
        {
            Integer.parseInt(intChar);
            return true;
        }
        catch (NumberFormatException e)
        {
            return false;
        }
    }
    //tryParseInt

} // FilesInOut
