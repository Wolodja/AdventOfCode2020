package day2;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Task1 {

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

        long contOfLetterInPassword = countLetterAppearanceInPassword(passwordLetter, passwordWord);
        return contOfLetterInPassword >= minCount && contOfLetterInPassword <= maxCount;
    }

    private long countLetterAppearanceInPassword(char passwordLetter, String passwordWord) {
        int count = 0;

        for (int i = 0; i < passwordWord.length(); i++) {
            if (passwordWord.charAt(i) == passwordLetter)
                count++;
        }
        return count;
    }

    public static void main(String[] args) {
        Task1 t = new Task1();
        t.findAllValidPasswords();
    }
}

