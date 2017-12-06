package com.jafager.uRISC;



import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;



public class Assembler
{
    
    private static final int RETURN_CODE_ASSEMBLER_EXCEPTION            = 1;
    private static final int RETURN_CODE_INTERNAL_ASSEMBLER_EXCEPTION   = 2;
    private static final int RETURN_CODE_INVALID_ARGUMENTS              = 3;
    
    public static void main(String[] arguments)
    {
              
        try
        {
            
            // Read in source code
            List<String> source_code = new ArrayList<>();
            switch(arguments.length)
            {
                case 0:
                    source_code = ReadSourceCodeFromStandardInput();
                    break;
                case 1:
                case 2:
                    source_code = ReadSourceCodeFromFile(arguments[0]);
                    break;
                default:
                    System.err.println("Usage: uasm [source file] [object file]");
                    System.exit(RETURN_CODE_INVALID_ARGUMENTS);
            }
            
            // Assemble source code
            List<String> object_code = AssemblerCore.Assemble(source_code);
            
            // Write out object code
            switch(arguments.length)
            {
                case 0:
                case 1:
                    WriteObjectCodeToStandardOutput(object_code);
                    break;
                case 2:
                    WriteObjectCodeToFile(object_code, arguments[1]);
                    break;
                default:
                    System.err.println("Usage: uasm [source file] [object file]");
                    System.exit(RETURN_CODE_INVALID_ARGUMENTS);
            }
            
        }
        catch (AssemblerException exception)
        {
            System.err.println(exception.getMessage());
            System.exit(RETURN_CODE_ASSEMBLER_EXCEPTION);
        }
        catch (Exception exception)
        {
            System.err.println("There was a problem with the assembler.  This is probably the result of a bug in the assembler itself.");
            System.err.println(exception.getMessage());
            System.exit(RETURN_CODE_INTERNAL_ASSEMBLER_EXCEPTION);
        }
    }
    
    private static List<String> ReadSourceCodeFromStandardInput() throws AssemblerException
    {
        List<String> source_code = new ArrayList<>();
        BufferedReader console_input = new BufferedReader(new InputStreamReader(System.in));
        while (true)
        {
            try
            {
                String line = console_input.readLine();
                if (line == null)
                    break;
                else
                    source_code.add(line);
            }
            catch (IOException exception)
            {
                throw new AssemblerException("Error reading source code from console: " + exception.getMessage());
            }
        }
        return source_code;
    }
    
    private static List<String> ReadSourceCodeFromFile(String filename) throws AssemblerException
    {
        try
        {
            return Files.readAllLines(Paths.get(filename), Charset.defaultCharset());
        }
        catch (IOException exception)
        {
            throw new AssemblerException("Error reading source code from file '" + filename + "': " + exception.getMessage());
        }    
    }
    
    private static void WriteObjectCodeToStandardOutput(List<String> object_code)
    {
        for (int index = 0; index < object_code.size(); index++)
            System.out.println(object_code.get(index));
    }
    
    private static void WriteObjectCodeToFile(List<String> object_code, String filename) throws AssemblerException
    {
        try
        {
            Files.write(Paths.get(filename), object_code, Charset.defaultCharset());
        }
        catch (IOException exception)
        {
            throw new AssemblerException("Error writing object code to file '" + filename + "': " + exception.getMessage());
        }    }
    
}
