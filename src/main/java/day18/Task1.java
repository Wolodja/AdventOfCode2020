package day18;

import common.InputReader;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Task1 {

    public void t1() {
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
        String[] expression = expresionInput.split(" ");
        Long current = Long.valueOf(expression[0]);
        for (int i = 1; i < expression.length; i += 2) {
            String currentChar = expression[i];
            if (currentChar.equals("+")) {
                current += Long.valueOf(expression[i + 1]);
            } else if (currentChar.equals("*")) {
                current *= Long.valueOf(expression[i + 1]);
            }
        }
        return current.toString();
    }


    public static void main(String[] args) {
        Task1 task1 = new Task1();
        task1.t1();
    }
}
