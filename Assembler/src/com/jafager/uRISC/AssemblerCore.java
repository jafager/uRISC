package com.jafager.uRISC;



import java.util.*;



public class AssemblerCore
{
    
    private static final String BLANK_LINE_REGEX = "^\\s*$";
    private static final String COMMENT_REGEX = "^\\s*#.*$";
    private static final String INSTRUCTION_LABEL_DEFINITION_REGEX = "^\\s*([A-Za-z0-9_]+)\\s*:\\s*(#.*$|$)";
    
    public static List<String> Assemble(List<String> source_code)
    {
        HashMap<String, Long> symbol_table = FirstPass(source_code);
        List<String> object_code = SecondPass(source_code, symbol_table);
        return object_code;
    }
    
    private static HashMap<String, Long> FirstPass(List<String> source_code) throws AssemblerException
    {
        HashMap<String, Long> symbol_table = new HashMap<>();
        Long instruction_address = new Long(0);
        for (int index = 0; index < source_code.size(); index++)
        {
            String line = source_code.get(index);
            if (IsBlankLine(line))
                continue;
            else if (IsComment(line))
                continue;
            else if (IsInstructionLabelDefinition(line))
            {
                String label = GetLabel(line);
                if (!symbol_table.containsKey(label))
                    symbol_table.put(label, instruction_address);
                else
                    throw new AssemblerException("Duplicate label '" + GetAddress(line) + "' on line" + (index + 1) + ".");
            }
            // TODO: keep going
            else
                throw new AssemblerException("Syntax error on line " + (index + 1) + ".");
            
        }
        return symbol_table;
    }
    
    private static List<String> SecondPass(List<String> source_code, HashMap<String, Long> symbol_table)
    {
        // TODO: Implement assembler second pass
        return new ArrayList<>();
    }
    
    private static boolean IsBlankLine(String line)
    {
        return line.matches(BLANK_LINE_REGEX);
    }
    
    private static boolean IsComment(String line)
    {
        return line.matches(COMMENT_REGEX);
    }
    
}
