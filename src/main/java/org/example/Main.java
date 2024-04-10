package org.example;

import java.util.Scanner;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите арифметическое выражение:");
        String input = scanner.nextLine();
        input = removeSpaces(input);
        if (!correctExpression(input)) {
            System.out.println("Ошибка: Неправильный формат ввода!");
            return;
        }
        String operator = " ";
        for (int i = 0; i < input.length(); i++) {
            if (!Character.isDigit(input.charAt(i)) && input.charAt(i) != '.') {
                operator = String.valueOf(input.charAt(i));
                break;
            }
        }
        String[] numbers = input.split("\\" + operator);
        BigDecimal num1 = new BigDecimal(numbers[0]);
        BigDecimal num2 = new BigDecimal(numbers[1]);
        BigDecimal result = BigDecimal.ZERO;

        switch (operator) {
            case "+":
                result = num1.add(num2);
                break;
            case "-":
                result = num1.subtract(num2);
                break;
            case "*":
                result = num1.multiply(num2);
                break;
            case "/":
                if (num2.compareTo(BigDecimal.ZERO) == 0) {
                    System.out.println("Ошибка: Деление на ноль!");
                    return;
                }
                result = num1.divide(num2, RoundingMode.HALF_EVEN);
                break;
            case "%":
                result = num1.remainder(num2);
                break;
        }
        System.out.println(input + " = " + result);
    }

    private static boolean correctExpression(String expression) {
        String pattern = "^\\d+(\\.\\d+)?([+*%/-]\\d+(\\.\\d+)?)";
        return expression.matches(pattern);
    }

    private static String removeSpaces(String str) {
        return str.replaceAll("\\s", "");
    }
}