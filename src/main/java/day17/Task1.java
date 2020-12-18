package day17;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import common.InputReader;
import lombok.Data;
import lombok.Value;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class Task1 {

    public static void main(String[] args) {
        Task1 task1 = new Task1();
        task1.part1();
    }



    public void part1() {
        Set<Pos> pos = new HashSet<>();
        InputReader inputReader = new InputReader();
        List<String> strings = inputReader.readInputLines("day17.txt");
        AtomicInteger currentIndex = new AtomicInteger();
        Set<Pos> finalPos = pos;
        strings.forEach(line -> {
            char[] chars = line.toCharArray();
            for(int i=0; i<chars.length; i++){
                if(chars[i] == '#'){
                    finalPos.add(new Pos(currentIndex.get(), i, 0));
                }
            }
            currentIndex.getAndIncrement();
        });

        for(int i = 0; i<6; i++){
            Set<Pos> newPos = new HashSet<>();
            Set<Pos> checkedPos = new HashSet<>();
            for(Pos p : pos) {
                addNeighbors(pos, newPos, checkedPos, p, true);
            }
            pos = newPos;
        }
        System.out.println(pos.size());
    }

    public void addNeighbors(Set<Pos> pos, Set<Pos> newPos, Set<Pos> checkedPos, Pos p, boolean active){
        if(!checkedPos.contains(p)) {
            long neighbours = active ? -1 : 0;
            checkedPos.add(p);
            for (int a = -1; a <= 1; a++) {
                for (int b = -1; b <= 1; b++) {
                    for (int c = -1; c <= 1; c++) {
                        Pos x = new Pos(p.x + a, p.y + b, p.z + c);
                        if (pos.contains(x)) {
                            neighbours++;
                        } else if(active) {
                            addNeighbors(pos, newPos, checkedPos, x, false);
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
    public static class Pos {
        long x;
        long y;
        long z;
    }
}
