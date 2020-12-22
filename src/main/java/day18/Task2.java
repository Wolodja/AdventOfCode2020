package day18;

import common.InputReader;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Task2 {

    public void t2() {
        InputReader inputReader = new InputReader();
        List<String> expressions = inputReader.readInputLines("day18.txt");
        AtomicReference<Long> sum = new AtomicReference<>(0L);
        expressions.forEach(e -> sum.updateAndGet(v -> v + Long.parseLong(calculateResult(e))));
        System.out.println(sum);
    }

    private String calculateResult(String expresionInput) {
        while (expresionInput.contains(")")) {
            int closedParentheses = expresionInput.indexOf(")");
            int openParentheses = closedParentheses - new StringBuilder(expresionInput.substring(0, closedParentheses)).reverse().toString().indexOf("(") - 1;
            expresionInput = expresionInput.substring(0, openParentheses) +
                calculateSimple(expresionInput.substring(openParentheses + 1, closedParentheses)) +
                expresionInput.substring(closedParentheses + 1);
        }
        return calculateSimple(expresionInput);
    }

    private String calculateSimple(String expresionInput) {
        while (expresionInput.contains("+")) {
            boolean found = false;
            String newExpression = "";
            String[] expression = expresionInput.split(" ");
            for (int i = 0; i < expression.length; i++) {
                if (found || !expression[i + 1].equals("+")) {
                    newExpression += expression[i] + " ";
                } else {
                    newExpression += Long.valueOf(expression[i]) + Long.valueOf(expression[i + 2]);
                    newExpression+=" ";
                    found = true;
                    i = i + 2;
                }
            }
            expresionInput = newExpression;
        }
        return calculateMultiplications(expresionInput);

    }


    private String calculateMultiplications(String expresionInput) {
        String[] expression = expresionInput.split(" ");
        Long current = Long.valueOf(expression[0]);
        for (int i = 1; i < expression.length; i += 2) {
            String currentChar = expression[i];
            if (currentChar.equals("*")) {
                current *= Long.valueOf(expression[i + 1]);
            }
        }
        return current.toString();
    }


    public static void main(String[] args) {
        Task2 task2 = new Task2();
        task2.t2();
    }
}
