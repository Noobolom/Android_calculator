package com.example.calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Reader {
    static List<Character> readSigns(String Example, int Begin, int End) {
        List<Character> Signs = new ArrayList<>();
        String SubExample = Example.substring(Begin, End);
        Pattern pattern = Pattern.compile(".[+*\\-/]");
        Matcher matcher = pattern.matcher(SubExample);
        while (matcher.find()) {
            String Sign = matcher.group();
            if (Character.isDigit(Sign.charAt(0))) {
                Signs.add(Sign.charAt(1));
            }
        }
        return Signs;
    }

    static List<Double> readNumbers(String Example, int Begin, int End){
        List <Double> Numbers = new ArrayList<>();
        String SubExample = Example.substring(Begin, End);
        SubExample = SubExample.replace(',','.');
        Pattern pattern = Pattern.compile("(([(+\\-*/^]\\-)?)[0-9]+(\\.([0-9])*)?(E[+\\-]?[0-9]+)?"); // Symbols at 0 and 1 indexes may be [+-*/]
        Matcher matcher = pattern.matcher(SubExample);
        while(matcher.find()){
            String Number = matcher.group();
            if (Number.length() == 1) {
                Numbers.add(Double.parseDouble(Number));
            }
            else if (Character.isDigit(Number.charAt(1)) || Number.charAt(1) == '.') {
                Numbers.add(Double.parseDouble(Number));
            } else {
                char[] str = Number.toCharArray();
                str[0] = ' ';
                Number = new String(str);
                Numbers.add(Double.parseDouble(Number));
            }
        }
        return Numbers;
    }
}
