package day6;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Task2 {

    public void countAllUniqueAnswers() {
        DeclarationsReader declarationsReader = new DeclarationsReader();
        String allDeclarations = declarationsReader.readDeclarations("day6.txt");
        List<String> declarationsList = Arrays.asList(allDeclarations.split("(?m)^\\s*$"));
        AtomicInteger sum = new AtomicInteger(0);
        declarationsList.forEach(d -> {
                List<String> declarationsAnswers = new ArrayList(Arrays.asList(d.split("\r\n")));
                declarationsAnswers.removeAll(Collections.singleton(""));

                HashSet<Character> characters = new HashSet<>();
                for (char c : declarationsAnswers.get(0).toCharArray()) {
                    characters.add(c);
                }
                HashSet<Character> iterationCharacters = new HashSet<>(characters);
                declarationsAnswers.forEach(answer ->{
                    for(char ch : iterationCharacters){
                        if(!answer.contains("" + ch)){
                            characters.remove(ch);
                        }
                    }
                });

                sum.addAndGet(characters.size());
            }
        );
        System.out.println("Sum is " + sum);
    }

    public static void main(String[] args) {
        Task2 task2 = new Task2();
        task2.countAllUniqueAnswers();
    }
}
