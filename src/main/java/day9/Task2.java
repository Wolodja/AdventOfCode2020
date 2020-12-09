package day9;

import common.InputReader;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Task2 {

    public void findFirstXMASEncryptedNumber(){
        InputReader inputReader = new InputReader();
        List<String> stringNumbers = inputReader.readInputLines("day9.txt");
        List<Long> numbers = stringNumbers.stream().map(Long::parseLong).collect(Collectors.toList());
        Long number = findNumbersThatSumsTo(numbers, 105950735);
        System.out.println(number);
    }

    private Long findNumbersThatSumsTo(List<Long> numbers, int sum) {
        int currentFirstIndex = 0;
        while(currentFirstIndex<numbers.size()) {
            int currentIndex = currentFirstIndex;
            int currentSum = 0;
            while (currentSum < sum) {
                currentSum += numbers.get(currentIndex);
                currentIndex++;
            }
            if (currentSum == sum) {
                return findSumOfSmallestAndBigestInRange(numbers, currentFirstIndex, currentIndex-1);
            }
            currentFirstIndex++;
        }
        return -1L;

    }

    private Long findSumOfSmallestAndBigestInRange(List<Long> allNumbers, int min, int max) {
        List<Long> rangeNumbers = new ArrayList<>();
        for(int i = min; i<=max; i++){
            rangeNumbers.add(allNumbers.get(i));
        }
        return rangeNumbers.stream().max(Long::compareTo).get() + rangeNumbers.stream().min(Long::compareTo).get();
    }

    public static void main(String[] args) {
        Task2 task2 = new Task2();
        task2.findFirstXMASEncryptedNumber();
    }
}
