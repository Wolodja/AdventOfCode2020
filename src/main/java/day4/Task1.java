package day4;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Task1 {

    public void countAllValidPassports(){
        AtomicInteger validPassCount = new AtomicInteger(0);
        PassportReader passportReader = new PassportReader();
        String allPassports = passportReader.readPassports("day4.txt");
        List<String> passportList = Arrays.asList(allPassports.split("(?m)^\\s*$"));
        passportList.forEach(passport ->{
            if(isValidPassport(passport)){
                validPassCount.incrementAndGet();
            }
        });
        System.out.println("The total number of valid passports is " + validPassCount.get());

    }

    private boolean isValidPassport(String passport) {
        List<String> allRequiredAttributes = Arrays.asList("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid");
        passport = passport.replaceAll("\n", " ");
        passport = passport.replaceAll("\r", " ");
        List<String> allAttributesWithValues = Arrays.asList(passport.split(" "));
        HashSet<String> attributes = new HashSet<>();
        allAttributesWithValues.stream().map(att -> att.split(":")).forEach(att -> attributes.add(att[0]));
        return attributes.containsAll(allRequiredAttributes);
    }

    public static void main(String[] args) {
        Task1 task1 = new Task1();
        task1.countAllValidPassports();
    }

}
