package day5;

import java.util.List;

public class Task2 {


    private void findMySeat() {
        Task1 task1 = new Task1();
        List<Integer> allSetIds = task1.getAllSetsIds();
        allSetIds.sort(Integer::compare);
        allSetIds.forEach(number -> {
            if(!allSetIds.contains(number + 1 )){
                System.out.println(number + 1);
            }
        });
    }

    public static void main(String[] args) {
        Task2 task2 = new Task2();
        task2.findMySeat();
    }
}
