package day2;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Task2 {

    public void findAllValidPasswords() {
        AtomicInteger totalNumberOfValidPasswords = new AtomicInteger(0);
        FileReader fileReader = new FileReader();
        List<String> passwords = fileReader.readTheListOfNumbers("day2.txt");
        passwords.forEach(passwordLine -> {
            if (validPasswordLine(passwordLine)) {
                totalNumberOfValidPasswords.getAndIncrement();
            }
        });
        System.out.println("The total number of valid passwords is " + totalNumberOfValidPasswords);

    }

    private boolean validPasswordLine(String passwordLine) {
        String[] passwordParts = passwordLine.split(" ");

        String countSplit[] = passwordParts[0].split("-");
        int minCount = Integer.parseInt(countSplit[0]);
        int maxCount = Integer.parseInt(countSplit[1]);

        String[] letterSplit = passwordParts[1].split(":");
        char passwordLetter = letterSplit[0].charAt(0);

        String passwordWord = passwordParts[2];

        return checkPassword(minCount, maxCount, passwordLetter, passwordWord);
    }

    private boolean checkPassword(int minCount, int maxCount, char passwordLetter, String passwordWord) {
        boolean firstIndex = passwordWord.charAt(minCount - 1) == passwordLetter && passwordWord.charAt(maxCount - 1) != passwordLetter;

        boolean secondIndex = passwordWord.charAt(minCount - 1) != passwordLetter && passwordWord.charAt(maxCount - 1) == passwordLetter;
        return (!firstIndex && secondIndex) || (firstIndex && !secondIndex);
    }

    public static void main(String[] args) {
        Task2 task2 = new Task2();
        task2.findAllValidPasswords();
    }
}
