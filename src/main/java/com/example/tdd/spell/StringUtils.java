package com.example.tdd.spell;

import java.util.Locale;

public class StringUtils {

    public static String reverseString(String input)
    {
        StringBuilder backwards = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            backwards.append(
                input.charAt(input.length() - 1 - i));
        }
        return backwards.toString();
    }
    public static String capitalizeString(String input)
    {
        return input.toUpperCase(Locale.ROOT);
    }

    public static String subString(String input, int start,
                                   int end)
    {
        return input.substring(start, end);
    }

    public static String subString(String input, int start)
    {
        return input.substring(start);
    }

    public static String joinString(String input1,
                                    String input2)
    {
        return input1.concat(input2);
    }
}