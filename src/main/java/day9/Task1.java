package day9;

import common.InputReader;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class Task1 {


    public void findFirstXMASNumber(){
        InputReader inputReader = new InputReader();
        List<String> stringNumbers = inputReader.readInputLines("day9.txt");
        List<Long> numbers = stringNumbers.stream().map(Long::parseLong).collect(Collectors.toList());
        Long number = findNumberThatIsNotSum(numbers, 25);
        System.out.println(number);
    }

    private Long findNumberThatIsNotSum(List<Long> numbers, int preamble) {
        for(int i = preamble; i<numbers.size(); i++){
            Long current = numbers.get(i);
            List<Long> currentPreamble = new ArrayList<>();
            for(int j = i-preamble; j<preamble+i; j++){
                currentPreamble.add(numbers.get(j));
            }
            AtomicBoolean valid = new AtomicBoolean(false);
            currentPreamble.forEach(n -> {
                if(current - n != n && currentPreamble.contains(current - n)){
                    valid.set(true);
                }
            });
            if(!valid.get()){
                return current;
            }
        }
        return -1L;

    }

    public static void main(String[] args) {
        Task1 task1 = new Task1();
        task1.findFirstXMASNumber();

    }
}
