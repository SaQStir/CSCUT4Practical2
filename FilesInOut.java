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

    //int scenario tells us to output in all caps or not
    public static boolean scenario = false;


    public static void main(String[] args)
    {

        String test = "allison wesley 28011990";
        System.out.println(updateString(test));

        String fileIn = "input.txt";
        String fileOut = "output.txt";

        updateFile(fileIn, fileOut);

        if (args[0].equals("~u"))
        {
            scenario = true;
        }

        // Replace this with statements to set the file name (input) and file name (output).
        // Initially it will be easier to hardcode suitable file names.

        // Set up a new Scanner to read the input file.
        // Processing line by line would be sensible here.
        // Initially, echo the text to System.out to check you are reading correctly.
        // Then add code to modify the text to the output format.

        // Set up a new PrintWriter to write the output file.
        // Add suitable code into the above processing (because you need to do this line by line also.
        // That is, read a line, write a line, loop.

        // Finally, add code to read the filenames as arguments from the command line.

        System.out.println("You need to add your own code to do anything");

    } // main

    public static void updateFile(String inputLoc, String outputLoc)
    {
        File inputFile = new File(inputLoc);
        try (Scanner sc = new Scanner(inputFile, StandardCharsets.UTF_8.name()))
        {
            while(sc.hasNextLine())
            {
                updateFiledWrite(outputLoc,sc.next());
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();;
        }
    }
    public static void updateFiledWrite(String outputloc, String input)
    {
        File outputFile = new File(outputloc);
        try (FileWriter fw = new FileWriter(outputFile, true))
        {
            fw.write(updateString(input) + "\r\n");
            fw.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();;
        }
    }

    public static String updateString(String input)
    {
        String update = "";
        StringBuffer change = new StringBuffer(input);

        for(int i = 0; i < input.length(); i ++)
        {

            if (tryParseInt(change.substring(i)))
            {
                update = update + input.substring(i, i+2) + "/" + input.substring(i+3 , i+5) + "/" + input.substring(i+4);
                change.replace(i, input.length(), update);
                break;
            }
            else
            {
                if( i == 0 || Character.isWhitespace(change.charAt((i-1))))
                {
                    char temp = Character.toUpperCase(change.charAt(i));
                    change.replace(i, i+1, Character.toString(temp));
                }
            }
        }
        if (!scenario)
        {
            return change.toString().toUpperCase();
        }
        else
            return change.toString();
    }

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

} // FilesInOut
