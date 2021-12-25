package com.example.RPM.service;

import com.example.RPM.service.interfaces.RPMMethods;
import org.springframework.stereotype.Service;

import java.util.Stack;

@Service
public class RPMMethodsImpl implements RPMMethods {
    @Override
    public double calculate(String input) {
        String output = getExpression(input);
        return counting(output);
    }

    //Преобразование
    @Override
    public String getExpression(String input) {
        StringBuilder output = new StringBuilder();
        Stack<Character> stack = new Stack<>();
            for (int i = 0; i < input.length(); i++) {
                if (isDelimeter(input.charAt(i))) {
                    continue;
                }
                if (getPriority(input.charAt(i)) == 0) {
                    output.append(input.charAt(i));
                } else if (getPriority(input.charAt(i)) == 1) {
                    stack.push(input.charAt(i));
                } else if (getPriority(input.charAt(i)) > 1) {
                    output.append(" ");
                    while (!stack.empty()) {
                        if (getPriority(stack.peek()) >= getPriority(input.charAt(i))) {
                            output.append(stack.pop()).append(" ");
                        } else {
                            break;
                        }
                    }
                    stack.push(input.charAt(i));
                } else if (getPriority(input.charAt(i)) == -1) {
                    output.append(" ");
                    while (getPriority(stack.peek()) != 1) {
                        output.append(stack.pop());
                    }
                    stack.pop();
                }
            }
            while (!stack.empty()) {
                output.append(stack.pop());
            }
        return output.toString();
    }

    @Override
    public double counting(String input) {
        double result = 0;
        Stack<Double> temp = new Stack<>();
        for (int i = 0; i < input.length(); i++) {
            if (Character.isDigit(input.charAt(i))) {
                StringBuilder a = new StringBuilder(" ");
                while (!isDelimeter(input.charAt(i)) && !isOperator(input.charAt(i))) {
                    a.append(input.charAt(i));
                    i++;
                    if (i == input.length()) {
                        break;
                    }
                }
                temp.push(Double.parseDouble(a.toString()));
                i--;
            } else if (isOperator(input.charAt(i))) {
                double a = temp.pop();
                double b = temp.pop();

                switch (input.charAt(i)) {
                    case '+':
                        result = b + a;
                        break;
                    case '-':
                        result = b - a;
                        break;
                    case '*':
                        result = b * a;
                        break;
                    case '/':
                        result = b / a;
                        break;
                    case '^':
                        result = Double.parseDouble(String.valueOf(Math.pow(b, a)));
                        break;
                }
                temp.push(result);
            }
        }
        return temp.peek();
    }

    @Override
    public boolean isDelimeter(char c) {
        return " =".indexOf(c) != -1;
    }

    @Override
    public boolean isOperator(char c) {
        return "+-/*^)(".indexOf(c) != -1;
    }

    @Override
    public int getPriority(char s) {
        switch (s) {
            case ')':
                return -1;
            case '(':
                return 1;
            case '+':
            case '-':
                return 2;
            case '*':
            case '/':
                return 3;
            case '^':
                return 4;
            default:
                return 0;
        }
    }
}
