package day17;

import common.InputReader;
import lombok.Data;
import lombok.Value;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class Task2 {

    public void part2() {
        Set<Pos4> pos = new HashSet<>();
        InputReader inputReader = new InputReader();
        List<String> strings = inputReader.readInputLines("day17.txt");
        AtomicInteger currentIndex = new AtomicInteger();
        Set<Pos4> finalPos = pos;
        strings.forEach(line -> {
            char[] chars = line.toCharArray();
            for(int i=0; i<chars.length; i++){
                if(chars[i] == '#'){
                    finalPos.add(new Pos4(currentIndex.get(), i, 0, 0));
                }
            }
            currentIndex.getAndIncrement();
        });
        for(int i = 0; i<6; i++){
            Set<Pos4> newPos = new HashSet<>();
            Set<Pos4> checkedPos = new HashSet<>();
            for(Pos4 p : pos) {
                addNeighbors(pos, newPos, checkedPos, p, true);
            }
            pos = newPos;
        }
        System.out.println(pos.size());
    }

    public void addNeighbors(Set<Pos4> pos, Set<Pos4> newPos, Set<Pos4> checkedPos, Pos4 p, boolean active){
        if(!checkedPos.contains(p)) {
            long neighbours = active ? -1 : 0;
            checkedPos.add(p);
            for (int a = -1; a <= 1; a++) {
                for (int b = -1; b <= 1; b++) {
                    for (int c = -1; c <= 1; c++) {
                        for (int d = -1; d <= 1; d++) {
                            Pos4 x = new Pos4(p.x + a, p.y + b, p.z + c, p.w + d);
                            if (pos.contains(x)) {
                                neighbours++;
                            } else if (active) {
                                addNeighbors(pos, newPos, checkedPos, x, false);
                            }
                        }
                    }
                }
            }
            if((active && (neighbours == 2 || neighbours == 3)) ||
                (!active && neighbours == 3)){
                newPos.add(p);
            }
        }
    }

    @Data
    @Value
    public static class Pos4 {
        long x;
        long y;
        long z;
        long w;
    }

    public static void main(String[] args) {
        Task2 task2 = new Task2();
        task2.part2();
    }
}
