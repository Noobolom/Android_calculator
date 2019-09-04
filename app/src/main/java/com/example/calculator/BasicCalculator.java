package com.example.calculator;

import java.util.List;

public class BasicCalculator {

    private static String prepareExample (String Example){
        char[] PreparedExample = new char[Example.length() + 2];
        PreparedExample[0] = '(';
        PreparedExample[PreparedExample.length - 1] = ')';
        for (int i = 1; i < PreparedExample.length - 1; i++){
            PreparedExample[i] = Example.charAt(i - 1);
        }
        Example = new String(PreparedExample);
        return Example;
    }

    static String substituteString(String Example, int Begin, int End, String Result) {     // Ready
        int LengthOfNewString = Example.length();
        int ChangeOfLenght = (End - Begin) + 1;
        LengthOfNewString -= ChangeOfLenght;
        int count = 0;
        String StringOfDouble = Result;
        ChangeOfLenght = StringOfDouble.length();
        LengthOfNewString += ChangeOfLenght;
        int j = 0;
        char[] NewExample = new char[LengthOfNewString];
        for (int i = 0; i < Example.length(); i++) {
            if (i == Begin) {
                while (count != StringOfDouble.length()) {
                    NewExample[i] = StringOfDouble.charAt(count);
                    count++;
                    i++;
                }
                j = i;
                i = End;
            } else if (i > End) {
                NewExample[j] = Example.charAt(i);
                j++;
            } else NewExample[i] = Example.charAt(i);
        }
        return new String(NewExample);
    }

    static String Calculate(String Example){
        double ResultOfOperation = 0.0;
        Example = prepareExample(Example);
        int[] Quotes;
        List<Double> Numbers;
        List<Character> Signs;
        int i = 0;
        int NumberOfCycles = QuoteAnalyser.getNumberOfQuotes(Example) / 2;
        while(NumberOfCycles != 0) {
            Example = Example.replaceAll("\\-\\-", "+");
            Quotes = QuoteAnalyser.getCouplesOfQuotes(Example);
            Example = Operations.calculateSquare(Example, Quotes[i], Quotes[i + 1]);
            Quotes = QuoteAnalyser.getCouplesOfQuotes(Example);
            Example = Operations.calculateSquareRoot(Example, Quotes[i], Quotes[i + 1]);
            Quotes = QuoteAnalyser.getCouplesOfQuotes(Example);
            Example = Operations.calculateFactorials(Example, Quotes[i], Quotes[i + 1]);
            Example = Operations.calculateTrigonometry(Example);
            Quotes = QuoteAnalyser.getCouplesOfQuotes(Example);
            Example = Operations.calculateExp(Example, Quotes[i], Quotes[i + 1]);
            Quotes = QuoteAnalyser.getCouplesOfQuotes(Example);
            Example = Operations.calculateNaturalLogarithm(Example, Quotes[i], Quotes[i + 1]);
            Quotes = QuoteAnalyser.getCouplesOfQuotes(Example);
            Example = Operations.calculateDecimalLogarithm(Example, Quotes[i], Quotes[i + 1]);
            Quotes = QuoteAnalyser.getCouplesOfQuotes(Example);
            Signs = Reader.readSigns(Example, Quotes[i], Quotes[i + 1]);
            Numbers = Reader.readNumbers(Example, Quotes[i], Quotes[i + 1]);
            if (!Signs.isEmpty()) {
                for (int j = 0; j < Signs.size(); j++) {
                    if (Signs.get(j) == '*') {
                        ResultOfOperation = Numbers.get(j) * Numbers.get(j + 1);
                        Numbers.remove(j);
                        Numbers.remove(j);
                        Numbers.add(j, ResultOfOperation);
                        Signs.remove(j);
                        j--;
                    } else if (Signs.get(j) == '/') {
                        ResultOfOperation = Numbers.get(j) / Numbers.get(j + 1);
                        Numbers.remove(j);
                        Numbers.remove(j);
                        Numbers.add(j, ResultOfOperation);
                        Signs.remove(j);
                        j--;
                    }
                }
                for (int j = 0; j < Signs.size(); j++) {
                    if (Signs.get(j) == '+') {
                        ResultOfOperation = Numbers.get(j) + Numbers.get(j + 1);
                        Numbers.remove(j);
                        Numbers.remove(j);
                        Numbers.add(j, ResultOfOperation);
                        Signs.remove(j);
                        j--;
                    } else if (Signs.get(j) == '-') {
                        ResultOfOperation = Numbers.get(j) - Numbers.get(j + 1);
                        Numbers.remove(j);
                        Numbers.remove(j);
                        Numbers.add(j, ResultOfOperation);
                        Signs.remove(j);
                        j--;
                    }
                }
                Example = substituteString(Example, Quotes[i], Quotes[i + 1], String.valueOf(ResultOfOperation));
                NumberOfCycles--;
            }
            else {
                Example = substituteString(Example, Quotes[i], Quotes[i + 1], String.valueOf(Numbers.get(0)));
                NumberOfCycles--;
            }
        }

        return Example;
    }
}
