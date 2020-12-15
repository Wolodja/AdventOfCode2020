package day13;

import common.InputReader;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Task2 {

    static long part2() {
        InputReader inputReader = new InputReader();
        List<String> lines = inputReader.readInputLines("day13.txt");
        List<String> rawDepartures = Arrays.stream(lines.get(1).split(",")).collect(Collectors.toList());
        Pattern pattern = Pattern.compile("[0-9]+");
        List<Integer> departures = new ArrayList<>();
        List<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < rawDepartures.size(); i++) {
            String departure = rawDepartures.get(i);
            if (pattern.matcher(departure).matches()) {
                departures.add(Integer.valueOf(departure));
                indexes.add(Integer.parseInt(departure) - i);
            }
        }
        return chineseReminder(departures, indexes);
    }

    static long chineseReminder(List<Integer> reminders, List<Integer> moduli) {
        long product = reminders.stream()
            .mapToLong(i -> i)
            .reduce(1, (a, b) -> a * b);
        long sum = 0;

        for (int i = 0; i < reminders.size(); i++) {
            long partialProduct = product / reminders.get(i);
            long inverse = BigInteger.valueOf(partialProduct)
                .modInverse(BigInteger.valueOf(reminders.get(i)))
                .longValue();
            sum += partialProduct * inverse * moduli.get(i);
        }

        return sum % product;
    }

    public static void main(String[] args) {
        System.out.println(Task2.part2());
    }
}
