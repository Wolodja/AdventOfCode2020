package day4;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

public class Task2 {

    public void countAllValidPassports() {
        AtomicInteger validPassCount = new AtomicInteger(0);
        PassportReader passportReader = new PassportReader();
        String allPassports = passportReader.readPassports("day4.txt");
        List<String> passportList = Arrays.asList(allPassports.split("(?m)^\\s*$"));
        passportList.forEach(passport -> {
            if (isValidPassport(passport)) {
                validPassCount.incrementAndGet();
            }
        });
        System.out.println("The total number of valid passports is " + validPassCount.get());

    }

    private boolean isValidPassport(String passport) {
        passport = passport.replaceAll("\n", " ");
        passport = passport.replaceAll("\r", " ");
        passport = passport.replaceAll("  ", " ");
        List<String> allAttributesWithValues = Arrays.asList(passport.split(" "));
        AtomicInteger numberOfValidAttributes = new AtomicInteger(0);
        allAttributesWithValues.stream().map(att -> att.split(":"))
            .forEach(att -> {
            if (!att[0].isEmpty() && validateSAttribute(att[0], att[1])) {
                numberOfValidAttributes.incrementAndGet();
            }
        });
        return numberOfValidAttributes.get() == 7;
    }

    private boolean validateSAttribute(String attribute, String value) {
        switch (attribute) {
            case "byr": {
                return Pattern.matches("[0-9]{4}", value) && Integer.valueOf(value) >= 1920 && Integer.valueOf(value) <= 2002;
            }
            case "iyr": {
                return Pattern.matches("[0-9]{4}", value) && Integer.valueOf(value) >= 2010 && Integer.valueOf(value) <= 2020;
            }
            case "eyr": {
                return Pattern.matches("[0-9]{4}", value) && Integer.valueOf(value) >= 2020 && Integer.valueOf(value) <= 2030;
            }
            case "hgt": {
                return Pattern.matches("^(1[5-8][0-9]|19[0-3])(cm)", value) || Pattern.matches("^(59|6[0-9]|7[0-6])(in)", value);
            }
            case "hcl": {
                return Pattern.matches("^(#)[0-9a-f]{6}", value);
            }
            case "ecl": {
                return Pattern.matches("^(amb|blu|brn|gry|grn|hzl|oth)", value);
            }
            case "pid": {
                return Pattern.matches("^[0-9]{9}", value);
            }
            default:
                return false;
        }
    }

    public static void main(String[] args) {
        Task2 task2 = new Task2();
        task2.countAllValidPassports();
    }
}
