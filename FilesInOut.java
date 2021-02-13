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
        String fileIn = "";
        String fileOut = "";

        boolean fileCheck = false;

        Scanner sc = new Scanner(System.in);

        System.out.println("supply filename for input:");
        do
            {
                String input = sc.nextLine();
                if(checkFile(input))
                {
                    fileIn = input;
                    scenario = true;
                    System.out.println("File successfully located");
                }
                else
                {
                    System.out.println("Error: please makes sure you input the right file name");
                }
        }
        while (!scenario);
        scenario = false;

        System.out.println("supply filename for output:");
        do
            {
            String input = sc.nextLine();
            if(checkFile(input))
            {
                fileOut = input;
                scenario = true;
                System.out.println("File successfully located");
            }
            else
            {
                System.out.println("Error: please makes sure you input the right file name");
            }
        }
        while (!scenario);

        updateFile(fileIn, fileOut);

        if (args[0].equals("~u"))
        {
            scenario = true;
        }

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
        if (scenario)
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
