package day12;

import common.InputReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Task1 {

    public void countDistance() {

        InputReader inputReader = new InputReader();
        List<String> commandsInput = inputReader.readInputLines("day12.txt");
        List<Command> directions = convertInput(commandsInput);
        int totalCount = countTotal(directions);
        System.out.println(totalCount);
    }

    private int countTotal(List<Command> directions) {
        final AtomicInteger countN = new AtomicInteger(0);
        final AtomicInteger countE = new AtomicInteger(0);
        final AtomicInteger countW = new AtomicInteger(0);
        final AtomicInteger countS = new AtomicInteger(0);
        final Direction[] current = {Direction.E};
        directions.forEach(dir -> {
            if (dir.direction == Direction.N) {
                countN.addAndGet(dir.number);
            }
            if (dir.direction == Direction.E) {
                countE.addAndGet(dir.number);
            }
            if (dir.direction == Direction.W) {
                countW.addAndGet(dir.number);
            }
            if (dir.direction == Direction.S) {
                countS.addAndGet(dir.number);
            }
            if (dir.direction == Direction.F) {
                if (current[0] == Direction.N) {
                    countN.addAndGet(dir.number);
                }
                if (current[0] == Direction.E) {
                    countE.addAndGet(dir.number);
                }
                if (current[0] == Direction.W) {
                    countW.addAndGet(dir.number);
                }
                if (current[0] == Direction.S) {
                    countS.addAndGet(dir.number);
                }
            }
            if (dir.direction == Direction.R) {
                List<Direction> directionsTable = new ArrayList<>(Arrays.asList(Direction.N, Direction.E, Direction.S, Direction.W));
                int currentIndex = directionsTable.indexOf(current[0]);
                int addNumber = dir.number / 90;
                currentIndex = (currentIndex + addNumber) % 4;
                current[0] = directionsTable.get(currentIndex);
            }
            if (dir.direction == Direction.L) {
                List<Direction> directionsTable = new ArrayList<>(Arrays.asList(Direction.N, Direction.W, Direction.S, Direction.E));
                int currentIndex = directionsTable.indexOf(current[0]);
                int addNumber = dir.number / 90;
                currentIndex = (currentIndex + addNumber) % 4;
                current[0] = directionsTable.get(currentIndex);
            }
        });
        return Math.abs(countN.get() - countS.get()) + (Math.abs(countE.get() - countW.get()));
    }

    private List<Command> convertInput(List<String> commandsInput) {
        List<Command> directions = new ArrayList<>();
        commandsInput.forEach(inp -> {
            Command command = new Command();
            command.direction = Direction.valueOf(inp.substring(0, 1).toUpperCase());
            command.number = Integer.valueOf(inp.substring(1));
            directions.add(command);
        });
        return directions;
    }


    public static void main(String[] args) {
        Task1 task1 = new Task1();
        task1.countDistance();
    }

}
