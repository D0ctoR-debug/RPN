package com.example.RPM.service;

import com.example.RPM.service.interfaces.RPMMethods;
import org.springframework.stereotype.Service;

import java.util.ArrayDeque;
import java.util.Deque;

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
        Deque<Character> deque = new ArrayDeque<>();
        for (int i = 0; i < input.length(); i++) {
            if (isDelimiter(input.charAt(i))) {
                continue;
            }
            if (getPriority(input.charAt(i)) == 0) {
                output.append(input.charAt(i));
            } else if (getPriority(input.charAt(i)) == 1) {
                deque.push(input.charAt(i));
            } else if (getPriority(input.charAt(i)) > 1) {
                output.append(" ");
                while (!deque.isEmpty()) {
                    if (getPriority(deque.peek()) >= getPriority(input.charAt(i))) {
                        output.append(deque.pop()).append(" ");
                    } else {
                        break;
                    }
                }
                deque.push(input.charAt(i));
            } else if (getPriority(input.charAt(i)) == -1) {
                output.append(" ");
                    while (getPriority(deque.peek()) != 1) {
                        output.append(deque.pop());
                    }

                deque.pop();
            }
        }
        while (!deque.isEmpty()) {
            output.append(deque.pop());
        }
        return output.toString();
    }

    @Override
    public double counting(String input) {
        double result = 0;
        Deque<Double> temp = new ArrayDeque<>();
        for (int i = 0; i < input.length(); i++) {
            if (Character.isDigit(input.charAt(i))) {
                StringBuilder a = new StringBuilder(" ");
                while (!isDelimiter(input.charAt(i)) && !isOperator(input.charAt(i))) {
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
    public boolean isDelimiter(char c) {
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

    public boolean isMathExpression(String input) {
        char[] symbols = input.toCharArray();
        for (int i = 0; i < input.length(); i++) {
            if (!Character.isDigit(symbols[i]) && !isDelimiter(symbols[i]) && !isOperator(symbols[i])) {
                return false;
            }
        }
        return true;
    }
}
