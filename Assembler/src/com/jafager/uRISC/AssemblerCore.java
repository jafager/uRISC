package com.jafager.uRISC;



import java.util.*;



public class AssemblerCore
{
    
    public static List<String> Assemble(List<String> source_code)
    {
        List<String> object_code = new ArrayList<>();
        // TODO: actually implement the assembler core
        for (int index = 0; index < source_code.size(); index++)
            object_code.add("*" + source_code.get(index));
        return object_code;
    }
    
}
