package day3;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Task1 {

    public void countTrees() {
        TreeReader treeReader = new TreeReader();
        List<String> map = treeReader.readMap("day3.txt");
        int treeCount = countAllTreesOnMap(map);
        System.out.println("Total tree count is: " + treeCount);
    }

    private int countAllTreesOnMap(List<String> map) {
        int maxIndex = map.get(0).length();
        AtomicInteger index = new AtomicInteger(0);
        AtomicInteger treeCount = new AtomicInteger(0);
        map.forEach(row -> {
            if (row.charAt(index.get()) == '#') {
                treeCount.getAndIncrement();
            }
            index.set(incrementIndex(index, maxIndex));
        });

        return treeCount.get();
    }

    private int incrementIndex(AtomicInteger index, int maxIndex) {
        int newIndex = index.get() + 3;
        return newIndex < maxIndex ? newIndex : newIndex % maxIndex;
    }

    public static void main(String[] args) {
        Task1 task1 = new Task1();
        task1.countTrees();
    }
}
