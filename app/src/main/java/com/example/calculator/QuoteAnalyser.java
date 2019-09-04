package com.example.calculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QuoteAnalyser {
    static boolean CheckQuotes (String Example){
        if((QuoteAnalyser.getNumberOfQuotes(Example) & 1) != 0){
            return false;
        }
        int count_left_quotes = 0;
        int count_right_quotes = 0;
        for (int i = 0 ; i< Example.length(); i++){
            if (Example.charAt(i) == '('){
                count_left_quotes++;
            }
            if (Example.charAt(i) == ')'){
                count_right_quotes++;
            }
        }
        return count_left_quotes == count_right_quotes;
    }

    static int getNumberOfLeftQuotes (String Example){
        int count_left_quotes = 0;
        for (int i = 0 ; i< Example.length(); i++){
            if (Example.charAt(i) == '('){
                count_left_quotes++;
            }
        }
        return count_left_quotes;
    }

    static int getNumberOfRightQuotes (String Example){
        int count_right_quotes = 0;
        for (int i = 0 ; i< Example.length(); i++){
            if (Example.charAt(i) == ')'){
                count_right_quotes++;
            }
        }
        return count_right_quotes;
    }

    static int getNumberOfQuotes(String Example){
        int count_left_quotes = 0;
        int count_right_quotes = 0;
        for (int i = 0 ; i< Example.length(); i++){
            if (Example.charAt(i) == '('){
                count_left_quotes++;
            }
            if (Example.charAt(i) == ')'){
                count_right_quotes++;
            }
        }
        return count_left_quotes + count_right_quotes;                                            // Return general count of quotes
    }

    static int[] getCouplesOfQuotes(String Example){
        Pattern pattern = Pattern.compile("\\([0-9a-zA-Z\\-+*/![^()]]*\\)");
        Matcher matcher = pattern.matcher(Example);
        int BeginOfExpression;
        int EndOfExpression;
        int[] Quotes = new int[2];
        if(matcher.find()){
            String Expression = matcher.group(0);
            BeginOfExpression = Example.indexOf(Expression);
            EndOfExpression = BeginOfExpression + Expression.length() - 1;
        } else return null;
        Quotes[0] = BeginOfExpression;
        Quotes[1] = EndOfExpression;
        return Quotes;
    }
}
