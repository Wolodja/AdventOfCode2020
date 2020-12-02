package day1;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Task2 {

    FileReader fileReader = new FileReader();

    public void findNumber() {
        List<Integer> numbers = fileReader.readTheListOfNumbers("1.txt");
        AtomicInteger multiplication = findThreeNumbersMultiplicationThatSumIs2020(numbers);
        System.out.println("The result is : " + multiplication);
    }

    public AtomicInteger findThreeNumbersMultiplicationThatSumIs2020(List<Integer> numbers) {
        AtomicInteger result = new AtomicInteger(-1);
       numbers.forEach(number -> {
           int leftTo2020 = 2020 - number;
           AtomicInteger twoNumbersMultiplicationForRequiredSum = findTwoNumbersMultiplicationForRequiredSum(numbers, leftTo2020);
           if(twoNumbersMultiplicationForRequiredSum.get() != -1){
               result.set(twoNumbersMultiplicationForRequiredSum.get() * number);
           }
       });
       return result;
    }

    public AtomicInteger findTwoNumbersMultiplicationForRequiredSum(List<Integer> numbers, int requiredSum) {
        AtomicInteger result = new AtomicInteger(-1);
        numbers.forEach(number -> {
            int diff = requiredSum - number;
            if (numbers.contains(diff)) {
                result.set(number * diff);
            }
        });
        return result;
    }

    public static void main(String[] args) {
        Task2 task2 = new Task2();
        task2.findNumber();
    }
}
