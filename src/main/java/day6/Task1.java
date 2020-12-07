package day6;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Task1 {

    public void countAllUniqueAnswers() {
        DeclarationsReader declarationsReader = new DeclarationsReader();
        String allDeclarations = declarationsReader.readDeclarations("day6.txt");
        List<String> declarationsList = Arrays.asList(allDeclarations.split("(?m)^\\s*$"));
        AtomicInteger sum = new AtomicInteger(0);
        declarationsList.forEach(d -> {
            String declarationsAnswers = d.replaceAll("\r\n", "");
                HashSet<Character> characters = new HashSet<>();
                for (char c : declarationsAnswers.toCharArray()) {
                    if (c != ' ') {
                        characters.add(c);
                    }
                }
                sum.addAndGet(characters.size());
            }
        );
        System.out.println("Sum is " + sum);
    }

    public static void main(String[] args) {
        Task1 task1 = new Task1();
        task1.countAllUniqueAnswers();
    }
}
