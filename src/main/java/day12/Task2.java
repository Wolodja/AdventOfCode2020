package day12;

import common.InputReader;

import java.util.*;

public class Task2 {

    public void countDistance() {

        InputReader inputReader = new InputReader();
        List<String> commandsInput = inputReader.readInputLines("day12.txt");
        List<Command> directions = convertInput(commandsInput);
        int totalCount = countTotal(directions);
        System.out.println(totalCount);
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

    private int countTotal(List<Command> directions) {
        Map<Direction, Integer> wavePoints = new EnumMap<>(Direction.class);
        wavePoints.put(Direction.N, 1);
        wavePoints.put(Direction.E, 10);
        wavePoints.put(Direction.S, 0);
        wavePoints.put(Direction.W, 0);
        Map<Direction, Integer> position = new EnumMap<>(Direction.class);
        position.put(Direction.N, 0);
        position.put(Direction.E, 0);
        position.put(Direction.S, 0);
        position.put(Direction.W, 0);
        directions.forEach(dir -> {
            if (wavePoints.containsKey(dir.direction)) {
                wavePoints.put(dir.direction, wavePoints.get(dir.direction) + dir.number);
            }
            if (dir.direction == Direction.F) {
                wavePoints.forEach((k, v) -> position.put(k, position.get(k) + dir.number * v));
            }
            if (dir.direction == Direction.R) {
                List<Direction> directionsTable = new ArrayList<>(Arrays.asList(Direction.N, Direction.W, Direction.S, Direction.E));
                List<Integer> currentValue = new ArrayList<>();
                directionsTable.forEach(d -> currentValue.add(wavePoints.get(d)));
                int addNumber = dir.number / 90;
                for (int i = 0; i < 4; i++) {
                    int nextIndex = (i + addNumber) % 4;
                    wavePoints.put(directionsTable.get(i), currentValue.get(nextIndex));
                }
            }
            if (dir.direction == Direction.L) {
                List<Direction> directionsTable = new ArrayList<>(Arrays.asList(Direction.N, Direction.E, Direction.S, Direction.W));
                List<Integer> currentValue = new ArrayList<>();
                directionsTable.forEach(d -> currentValue.add(wavePoints.get(d)));
                int addNumber = dir.number / 90;
                for (int i = 0; i < 4; i++) {
                    int nextIndex = (i + addNumber) % 4;
                    wavePoints.put(directionsTable.get(i), currentValue.get(nextIndex));
                }
            }
        });
        return Math.abs(position.get(Direction.N) - position.get(Direction.S)) + (Math.abs(position.get(Direction.E) - position.get(Direction.W)));
    }


    public static void main(String[] args) {
        Task2 task2 = new Task2();
        task2.countDistance();
    }
}
