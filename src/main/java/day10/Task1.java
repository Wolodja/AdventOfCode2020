package day10;

import common.InputReader;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Task1 {

    public void countVoltageDifference(){
        InputReader inputReader = new InputReader();
        List<String> allAdapters = inputReader.readInputLines("day10.txt");
        List<Integer> adapters = allAdapters.stream().map(Integer::parseInt).collect(Collectors.toList());
        adapters.add(0);
        Collections.sort(adapters);
        countDifferences(adapters);

    }

    private void countDifferences(List<Integer> adapters) {
        int diff1 = 0;
        int diff3 = 1;
        for (int i =1; i<adapters.size(); i++){
            int diff = adapters.get(i) - adapters.get(i-1);
            switch (diff){
                case 1:
                    diff1++;
                    break;
                case 3:
                    diff3++;
                    break;
                case 2:
                    break;
                default:
                    System.out.println("Diff is incorrect " + diff);
            }
        }

        System.out.println("Diff1 " + diff1 + " Diff3 " + diff3 + " multipl " + diff1*diff3);
    }

    public static void main(String[] args) {
        Task1 task1 = new Task1();
        task1.countVoltageDifference();
    }
}
