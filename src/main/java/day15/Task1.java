package day15;

import common.InputReader;

public class Task1 {

    public void t1() {
        InputReader inputReader = new InputReader();
        String input = inputReader.readInputAsOneString("day15.txt");
        String[] firstNumbers = input.split(",");
        int twoOtwoO = calculateNumber(firstNumbers);
        System.out.println(twoOtwoO);
    }

    private int calculateNumber(String[] firstNumbers) {
        int[] allNumbers = new int[2020];
        for (int i = 0; i < firstNumbers.length; i++) {
            allNumbers[i] = Integer.parseInt(firstNumbers[i]);
        }
        for (int i = firstNumbers.length; i < allNumbers.length; i++) {
            int prevNumber = allNumbers[i - 1];
            int lastIndex = findLastIndex(prevNumber, allNumbers, i - 1);
            if (lastIndex == -1) {
                allNumbers[i] = 0;
            } else {
                allNumbers[i] = i - 1 - lastIndex;
            }
        }
        return allNumbers[2019];
    }

    private int findLastIndex(int prevNumber, int[] allNumbers, int i) {
        for (int j = i-1; j >= 0; j--) {
            if (allNumbers[j] == prevNumber) {
                return j;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Task1 task1 = new Task1();
        task1.t1();
    }
}
