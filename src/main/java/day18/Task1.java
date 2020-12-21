package day18;

import common.InputReader;

public class Task1 {

    public void t1(){
        InputReader inputReader = new InputReader();
        String expresionInput = inputReader.readInputAsOneString("day18.txt");
        Long result = calculateResult(expresionInput);
        System.out.println(result);
    }

    private Long calculateResult(String expresionInput) {
        String expressionWithoutParentheses  = removePrentheses(expresionInput);
        return 1L;
    }

    private String removePrentheses(String expresionInput) {
        return "";
    }


    public static void main(String[] args) {
        Task1 task1 = new Task1();
        task1.t1();
    }
}
