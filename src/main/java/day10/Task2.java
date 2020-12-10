package day10;

import common.InputReader;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Task2 {

    public void countAdapterCombinations() {
        InputReader inputReader = new InputReader();
        List<String> allAdapters = inputReader.readInputLines("day10.txt");
        List<Integer> adapters = allAdapters.stream().map(Integer::parseInt).collect(Collectors.toList());
        adapters.add(0);
        adapters.add(Collections.max(adapters)+3);
        Collections.sort(adapters);
        devideAdapters(adapters);
    }


    private void devideAdapters(List<Integer> adapters) {
        Long result = 1L;
        int startIndex= 0;
        int currIndex = 0;
        int nextIndex = currIndex + 1;
        while (startIndex != adapters.size()-1) {
            while (adapters.get(nextIndex) == adapters.get(currIndex) + 1) {
                nextIndex++;
                currIndex++;
            }
            int diff = nextIndex - startIndex;
            if(diff == 3){
                result*=2;
            } else if(diff == 4){
                result*=4;
            } else if(diff == 5){
                result*=7;
            }
            currIndex++;
            nextIndex++;
            startIndex = currIndex;


        }
        System.out.println(result);


    }


    public static void main(String[] args) {
        Task2 task2 = new Task2();
        task2.countAdapterCombinations();
    }
}
