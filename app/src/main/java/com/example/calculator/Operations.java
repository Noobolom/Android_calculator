package com.example.calculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Operations {
    private static String calculateSin (String Example, int Begin, int End) {
        String SubExample = Example.substring(Begin, End);
        SubExample = SubExample.replace(',', '.');
        SubExample = SubExample.toLowerCase();
        Pattern pattern = Pattern.compile("sin\\-?[0-9]+(\\.([0-9])*)?(E[+\\-]?[0-9]+)?");
        Matcher matcher= pattern.matcher(SubExample);
        while (matcher.find()) {
            String Result = matcher.group();
            int BeginToInsert = Example.indexOf(Result);
            int EndToInsert = BeginToInsert + Result.length() - 1;
            char[] str = Result.toCharArray();
            str[0] = ' ';
            str[1] = ' ';
            str[2] = ' ';
            Result = new String(str);
            double DResult = Math.sin(Double.parseDouble(Result));
            Example = BasicCalculator.substituteString(Example, BeginToInsert, EndToInsert, String.valueOf(DResult));
        }
        return Example;
    }

    private static String calculateCos (String Example, int Begin, int End) {
        String SubExample = Example.substring(Begin, End);
        SubExample = SubExample.replace(',', '.');
        SubExample = SubExample.toLowerCase();
        Pattern pattern = Pattern.compile("cos\\-?[0-9]+(\\.([0-9])*)?(E[+\\-]?[0-9]+)?");
        Matcher matcher= pattern.matcher(SubExample);
        while (matcher.find()) {
            String Result = matcher.group();
            int BeginToInsert = Example.indexOf(Result);
            int EndToInsert = BeginToInsert + Result.length() - 1;
            char[] str = Result.toCharArray();
            str[0] = ' ';
            str[1] = ' ';
            str[2] = ' ';
            Result = new String(str);
            double DResult = Math.cos(Double.parseDouble(Result));
            Example = BasicCalculator.substituteString(Example, BeginToInsert, EndToInsert, String.valueOf(DResult));
        }
        return Example;
    }

    private static String calculateTan (String Example, int Begin, int End) {
        String SubExample = Example.substring(Begin, End);
        SubExample = SubExample.replace(',', '.');
        SubExample = SubExample.toLowerCase();
        Pattern pattern = Pattern.compile("tan\\-?[0-9]+(\\.([0-9])*)?(E[+\\-]?[0-9]+)?");
        Matcher matcher = pattern.matcher(SubExample);
        while (matcher.find()) {
            String Result = matcher.group();
            int BeginToInsert = Example.indexOf(Result);
            int EndToInsert = BeginToInsert + Result.length() - 1;
            char[] str = Result.toCharArray();
            str[0] = ' ';
            str[1] = ' ';
            str[2] = ' ';
            Result = new String(str);
            double DResult = Math.tan(Double.parseDouble(Result));
            Example = BasicCalculator.substituteString(Example, BeginToInsert, EndToInsert, String.valueOf(DResult));
        }
        return Example;
    }

    private static double Factorial(int n){
        double result = 1;
        if (n == 0) return 0;
        else {
            for (int i = 1; i <= n; i++) {
                result = result * i;
            }
        }
        return result;
    }

    static String calculateFactorials (String Example, int Begin, int End) {
        String SubExample = Example.substring(Begin, End);
        Pattern pattern = Pattern.compile("[0-9]+(\\.([0-9])*)?!");
        Matcher matcher= pattern.matcher(SubExample);
        while (matcher.find()) {
            String Result = matcher.group();
            int BeginToInsert = Example.indexOf(Result);
            int EndToInsert = BeginToInsert + Result.length() - 1;
            int Length = Result.length();
            char[] str = Result.toCharArray();
            str[Length - 1] = ' ';
            Result = new String(str);
            double DResult = Factorial((int)Double.parseDouble(Result));
            Example = BasicCalculator.substituteString(Example, BeginToInsert, EndToInsert, String.valueOf(DResult));
        }
        return Example;
    }

    static String calculateTrigonometry(String Example){
        int[] Quotes = QuoteAnalyser.getCouplesOfQuotes(Example);
        Example = calculateSin(Example, Quotes[0], Quotes[1]);
        Quotes = QuoteAnalyser.getCouplesOfQuotes(Example);
        Example = calculateCos(Example, Quotes[0], Quotes[1]);
        Quotes = QuoteAnalyser.getCouplesOfQuotes(Example);
        Example = calculateTan(Example, Quotes[0], Quotes[1]);
        return Example;
    }

    static String calculateSquare (String Example, int Begin, int End) {
        String SubExample = Example.substring(Begin, End);
        SubExample = SubExample.replace(',', '.');
        Pattern patternAll = Pattern.compile("\\-?[0-9]+(\\.([0-9])*)?(E[+\\-]?[0-9]+)?\\^\\-?[0-9]+(\\.([0-9])*)?(E[+\\-]?[0-9]+)?");
        Pattern patternLeft = Pattern.compile("\\-?([0-9]+(\\.([0-9])*)?(E[+\\-]?[0-9]+)?)\\^");
        Pattern patternRight = Pattern.compile("\\^\\-?([0-9]+(\\.([0-9])*)?(E[+\\-]?[0-9]+)?)");
        Matcher matcherAll = patternAll.matcher(SubExample);
        Matcher matcherLeft = patternLeft.matcher(SubExample);
        Matcher matcherRight = patternRight.matcher(SubExample);
        while (matcherLeft.find() && matcherRight.find() && matcherAll.find()) {
            String Number = matcherLeft.group();
            String Power = matcherRight.group();
            String AllExpression = matcherAll.group();
            int BeginToInsert = Example.indexOf(AllExpression);
            int EndToInsert = BeginToInsert + AllExpression.length() - 1;
            char[] strN = Number.toCharArray();
            strN[strN.length - 1] = ' ';
            Number = new String(strN);
            char[] strP = Power.toCharArray();
            strP[0] = ' ';
            Power = new String(strP);
            double DResult = Math.pow(Double.parseDouble(Number), Double.parseDouble(Power));
            Example = BasicCalculator.substituteString(Example, BeginToInsert, EndToInsert, String.valueOf(DResult));
        }
        return Example;
    }

    static String calculateSquareRoot (String Example, int Begin, int End) {
        String SubExample = Example.substring(Begin, End);
        SubExample = SubExample.replace(',', '.');
        SubExample = SubExample.toLowerCase();
        Pattern pattern = Pattern.compile("sqrt\\-?[0-9]+(\\.([0-9])*)?(E[+\\-]?[0-9]+)?");
        Matcher matcher= pattern.matcher(SubExample);
        while (matcher.find()) {
            String Result = matcher.group();
            int BeginToInsert = Example.indexOf(Result);
            int EndToInsert = BeginToInsert + Result.length() - 1;
            char[] str = Result.toCharArray();
            str[0] = ' ';
            str[1] = ' ';
            str[2] = ' ';
            str[3] = ' ';
            Result = new String(str);
            double DResult = 0;
            if (Double.parseDouble(Result) >= 0) {
                DResult = Math.sqrt(Double.parseDouble(Result));
            }
            Example = BasicCalculator.substituteString(Example, BeginToInsert, EndToInsert, String.valueOf(DResult));
        }
        return Example;
    }

    static String calculateNaturalLogarithm (String Example, int Begin, int End) {
        String SubExample = Example.substring(Begin, End);
        SubExample = SubExample.replace(',', '.');
        SubExample = SubExample.toLowerCase();
        Pattern pattern = Pattern.compile("ln\\-?[0-9]+(\\.([0-9])*)?(E[+\\-]?[0-9]+)?");
        Matcher matcher= pattern.matcher(SubExample);
        while (matcher.find()) {
            String Result = matcher.group();
            int BeginToInsert = Example.indexOf(Result);
            int EndToInsert = BeginToInsert + Result.length() - 1;
            char[] str = Result.toCharArray();
            str[0] = ' ';
            str[1] = ' ';
            Result = new String(str);
            double DResult = Math.log(Double.parseDouble(Result));
            Example = BasicCalculator.substituteString(Example, BeginToInsert, EndToInsert, String.valueOf(DResult));
        }
        return Example;
    }

    static String calculateDecimalLogarithm (String Example, int Begin, int End){
        String SubExample = Example.substring(Begin, End);
        SubExample = SubExample.replace(',', '.');
        SubExample = SubExample.toLowerCase();
        Pattern pattern = Pattern.compile("log\\-?[0-9]+(\\.([0-9])*)?(E[+\\-]?[0-9]+)?");
        Matcher matcher= pattern.matcher(SubExample);
        while (matcher.find()) {
            String Result = matcher.group();
            int BeginToInsert = Example.indexOf(Result);
            int EndToInsert = BeginToInsert + Result.length() - 1;
            char[] str = Result.toCharArray();
            str[0] = ' ';
            str[1] = ' ';
            str[2] = ' ';
            Result = new String(str);
            double DResult = 0;
            if (Double.parseDouble(Result) > 0){
                DResult = Math.log10(Double.parseDouble(Result));
            }
            Example = BasicCalculator.substituteString(Example, BeginToInsert, EndToInsert, String.valueOf(DResult));
        }
        return Example;
    }

    static String calculateExp (String Example, int Begin, int End) {
        String SubExample = Example.substring(Begin, End);
        SubExample = SubExample.replace(',', '.');
        Pattern pattern = Pattern.compile("exp\\-?[0-9]+(\\.([0-9])*)?(E[+\\-]?[0-9]+)?");
        Matcher matcher= pattern.matcher(SubExample);
        while (matcher.find()) {
            String Result = matcher.group();
            int BeginToInsert = Example.indexOf(Result);
            int EndToInsert = BeginToInsert + Result.length() - 1;
            char[] str = Result.toCharArray();
            str[0] = ' ';
            str[1] = ' ';
            str[2] = ' ';
            Result = new String(str);
            double DResult = Math.exp(Double.parseDouble(Result));
            Example = BasicCalculator.substituteString(Example, BeginToInsert, EndToInsert, String.valueOf(DResult));
        }
        return Example;
    }

}
