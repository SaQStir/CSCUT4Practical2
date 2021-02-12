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

    public static void main(String[] args)
    {
        File inputFile = new File("input.txt");

        if(args[0].equals("this"))
        {
            System.out.println(update1(test));
        }
        if(args[1].equals("is"))
        {
            System.out.println(update2(test));
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

    public void updateFile(File inputFile, File outputFile)
    {
        try (Scanner sc = new Scanner(inputFile, StandardCharsets.UTF_8.name()))
        {
            while(sc.hasNextLine())
            {
                System.out.println(sc.nextLine());
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();;
        }
    }

    public static String updateString1(String input)
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
        return change.toString();
    }

    public static String updateString2(String input)
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
        }

        return change.toString().toUpperCase();
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
