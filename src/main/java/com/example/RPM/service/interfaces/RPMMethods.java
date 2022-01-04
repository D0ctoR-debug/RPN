package com.example.RPM.service.interfaces;

public interface RPMMethods {
    double calculate(String input);

    String getExpression(String input);

    double counting(String input);

    boolean isDelimiter(char c);

    boolean isOperator(char c);

    int getPriority(char s);

    boolean isMathExpression(String input);

}
