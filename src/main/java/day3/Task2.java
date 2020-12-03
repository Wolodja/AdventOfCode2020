package day3;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Task2 {
    public void countTrees() {
        TreeReader treeReader = new TreeReader();
        List<String> map = treeReader.readMap("day3.txt");
        int treeCount1 = countAllTreesOnMap(map, 1);
        int treeCount2 = countAllTreesOnMap(map, 3);
        int treeCount3 = countAllTreesOnMap(map, 5);
        int treeCount4 = countAllTreesOnMap(map, 7);
        int treeCount5 = countAllTreesOnMapWithDoubleStep(map, 1);
        System.out.println("Total tree count 1 is: " + treeCount1);
        System.out.println("Total tree count 2 is: " + treeCount2);
        System.out.println("Total tree count 3 is: " + treeCount3);
        System.out.println("Total tree count 4 is: " + treeCount4);
        System.out.println("Total tree count 5 is: " + treeCount5);
        System.out.println("Total of Total : " + treeCount1 * treeCount2 * treeCount3 * treeCount4 * treeCount5);
    }

    private int countAllTreesOnMap(List<String> map, int steRight) {
        int maxIndex = map.get(0).length();
        AtomicInteger index = new AtomicInteger(0);
        AtomicInteger treeCount = new AtomicInteger(0);
        map.forEach(row -> {
            if (row.charAt(index.get()) == '#') {
                treeCount.getAndIncrement();
            }
            index.set(incrementIndex(index, maxIndex, steRight));
        });

        return treeCount.get();
    }

    private int countAllTreesOnMapWithDoubleStep(List<String> map, int steRight) {
        int maxIndex = map.get(0).length();
        AtomicInteger index = new AtomicInteger(0);
        AtomicInteger treeCount = new AtomicInteger(0);
        AtomicBoolean countThisStep = new AtomicBoolean(true);
        map.forEach(row -> {
            if (countThisStep.get() && row.charAt(index.get()) == '#') {
                treeCount.getAndIncrement();
            }
            if (countThisStep.get()) {
                index.set(incrementIndex(index, maxIndex, steRight));
            }
            countThisStep.set(!countThisStep.get());
        });

        return treeCount.get();
    }

    private int incrementIndex(AtomicInteger index, int maxIndex, int stepRight) {
        int newIndex = index.get() + stepRight;
        return newIndex < maxIndex ? newIndex : newIndex % maxIndex;
    }

    public static void main(String[] args) {
        Task2 task2 = new Task2();
        task2.countTrees();
    }
}
