package com.jafager.uRISC;



import java.util.*;



public class AssemblerCore
{
    
    public static List<String> Assemble(List<String> source_code)
    {
        HashMap<String, Long> symbol_table = FirstPass(source_code);
        List<String> object_code = SecondPass(source_code, symbol_table);
        return object_code;
    }
    
    private static HashMap<String, Long> FirstPass(List<String> source_code)
    {
        // TODO: Implement assembler first pass
        return new HashMap<>();
    }
    
    private static List<String> SecondPass(List<String> source_code, HashMap<String, Long> symbol_table)
    {
        // TODO: Implement assembler second pass
        return new ArrayList<>();
    }
    
}
