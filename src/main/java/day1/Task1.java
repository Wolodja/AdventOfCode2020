package day1;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Task1 {

    FileReader fileReader = new FileReader();

    public void findNumber() {
        List<Integer> numbers = fileReader.readTheListOfNumbers("day1.txt");
        AtomicInteger multiplication = findTwoNumbersMultiplicationThatSumIs2020(numbers);
        System.out.println("The result is : " + multiplication);
    }

    public AtomicInteger findTwoNumbersMultiplicationThatSumIs2020(List<Integer> numbers) {
        AtomicInteger result = new AtomicInteger(-1);
        numbers.forEach(number -> {
            int diff = 2020 - number;
            if (numbers.contains(diff)) {
                result.set(number * diff);
            }
        });
        return result;
    }

    public static void main(String[] args) {
        Task1 task1 = new Task1();
        task1.findNumber();
    }
}
