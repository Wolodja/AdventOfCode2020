package day10;

import common.InputReader;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Task1 {

    public void countVoltageDifference() {
        InputReader inputReader = new InputReader();
        List<String> allAdapters = inputReader.readInputLines("day10.txt");
        List<Integer> adapters = allAdapters.stream().map(Integer::parseInt).collect(Collectors.toList());
        adapters.add(0);
        adapters.add(Collections.max(adapters) + 3);
        Collections.sort(adapters);
        int diff1 = 0;
        int diff3 = 0;
        for (int i = 1; i < adapters.size(); i++) {
            int diff = adapters.get(i) - adapters.get(i - 1);
            if (diff == 1) {
                diff1++;
            } else if (diff == 3) {
                diff3++;
            }
        }
        System.out.println("Diff1 " + diff1 + " Diff3 " + diff3 + " multiplication " + diff1 * diff3);

    }

    public static void main(String[] args) {
        Task1 task1 = new Task1();
        task1.countVoltageDifference();
    }
}
