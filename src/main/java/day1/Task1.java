package day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Task1 {

    public void findNumber() {
        List<Integer> numbers = readTheListOfNumbers();
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

    private List<Integer> readTheListOfNumbers() {
        List<Integer> numbers = new ArrayList<>();
        try {
            URL url = getClass().getResource("/1.txt");
            File myObj = new File(url.getPath());
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                numbers.add(Integer.valueOf(data));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } finally {
            return numbers;
        }
    }

    public static void main(String[] args) {
        Task1 task1 = new Task1();
        task1.findNumber();
    }
}
