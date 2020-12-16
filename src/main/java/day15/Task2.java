package day15;


import common.InputReader;

import java.util.HashMap;
import java.util.Map;

public class Task2 {

    public void t2() {
        InputReader inputReader = new InputReader();
        String input = inputReader.readInputAsOneString("day15.txt");
        String[] firstNumbers = input.split(",");
        int twoOtwoO = calculateNumber(firstNumbers);
        System.out.println(twoOtwoO);
    }

    private int calculateNumber(String[] firstNumbers) {
        int[] allNumbers = new int[30000000];
        Map<Integer, Integer> lastIndexes = new HashMap<>();
        for (int i = 0; i < firstNumbers.length; i++) {
            allNumbers[i] = Integer.parseInt(firstNumbers[i]);
            lastIndexes.put(allNumbers[i], i);
        }
        for (int i = 0; i < firstNumbers.length - 1; i++) {
            allNumbers[i] = Integer.parseInt(firstNumbers[i]);
            lastIndexes.put(allNumbers[i], i);
        }
        for (int i = firstNumbers.length; i < allNumbers.length; i++) {
            int prevNumber = allNumbers[i - 1];
            Integer lastIndex = lastIndexes.get(prevNumber);
            if (lastIndex == null) {
                allNumbers[i] = 0;
            } else {
                allNumbers[i] = i - 1 - lastIndex;
            }
            lastIndexes.put(allNumbers[i - 1], i - 1);
        }
        return allNumbers[30000000 - 1];
    }

    public static void main(String[] args) {
        Task2 task2 = new Task2();
        task2.t2();
    }
}
