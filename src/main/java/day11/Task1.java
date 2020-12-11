package day11;

import common.InputReader;

import java.util.List;

public class Task1 {

    public void countSeats() {
        InputReader inputReader = new InputReader();
        List<String> seatLines = inputReader.readInputLines("day11.txt");
        SeatState[][] seats = transformSeatLines(seatLines);
        boolean changed = true;
        while (changed) {
            SeatState[][] newSeats = makeSeatIteration(seats);
            if(areTheSame(seats, newSeats)){
                changed = false;
            }
            seats = newSeats;
        }
        int freeSetsCount = countFreeSeats(seats);
        System.out.println(freeSetsCount);
    }

    private boolean areTheSame(SeatState[][] seats, SeatState[][] newSeats) {
        for (int i = 0; i < newSeats.length; i++) {
            for (int j = 0; j < newSeats[0].length; j++) {
                if(seats[i][j]!=newSeats[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    private SeatState[][] makeSeatIteration(SeatState[][] seats) {
        SeatState[][] nextIterationSeats = new SeatState[seats.length][seats[0].length];
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[0].length; j++) {
                nextIterationSeats[i][j] = calculateState(seats, i, j);
            }
        }
       return nextIterationSeats;
    }

    private SeatState calculateState(SeatState[][] seats, int i, int j) {
        SeatState currentState = seats[i][j];
        if (currentState == SeatState.FLOOR) {
            return SeatState.FLOOR;
        }
        int maxLength = seats.length - 1;
        int maxHeight = seats[0].length - 1;
        int adjacentSets = 0;
        if (i > 0) {
            SeatState state = seats[i - 1][j];
            if (state == SeatState.OCCUPIED) {
                adjacentSets++;
            }
        }
        if (j > 0) {
            SeatState state = seats[i][j - 1];
            if (state == SeatState.OCCUPIED) {
                adjacentSets++;
            }
        }
        if (i > 0 && j > 0) {
            SeatState state = seats[i - 1][j - 1];
            if (state == SeatState.OCCUPIED) {
                adjacentSets++;
            }
        }
        if (i < maxLength) {
            SeatState state = seats[i + 1][j];
            if (state == SeatState.OCCUPIED) {
                adjacentSets++;
            }
        }
        if (j < maxHeight) {
            SeatState state = seats[i][j + 1];
            if (state == SeatState.OCCUPIED) {
                adjacentSets++;
            }
        }
        if (i < maxLength && j < maxHeight) {
            SeatState state = seats[i + 1][j + 1];
            if (state == SeatState.OCCUPIED) {
                adjacentSets++;
            }
        }
        if (i > 0 && j < maxHeight) {
            SeatState state = seats[i - 1][j + 1];
            if (state == SeatState.OCCUPIED) {
                adjacentSets++;
            }
        }
        if (i < maxLength && j > 0) {
            SeatState state = seats[i + 1][j - 1];
            if (state == SeatState.OCCUPIED) {
                adjacentSets++;
            }
        }
        if (currentState == SeatState.OCCUPIED && adjacentSets >= 4) {
            return SeatState.FREE;
        }
        if (currentState == SeatState.FREE && adjacentSets == 0) {
            return SeatState.OCCUPIED;
        }
        return currentState;

    }

    private int countFreeSeats(SeatState[][] seats) {
        int count = 0;
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[0].length; j++) {
                if (seats[i][j] == SeatState.OCCUPIED) {
                    count++;
                }
            }
        }
        return count;
    }


    private SeatState[][] transformSeatLines(List<String> seatLines) {
        SeatState[][] seatStates = new SeatState[seatLines.size()][seatLines.get(0).length()];
        for (int i = 0; i < seatLines.size(); i++) {
            for (int j = 0; j < seatLines.get(0).length(); j++) {
                seatStates[i][j] = convertState(seatLines.get(i).charAt(j));
            }
        }
        return seatStates;
    }

    private SeatState convertState(char charAt) {
        if (charAt == '.') {
            return SeatState.FLOOR;
        } else if (charAt == 'L') {
            return SeatState.FREE;
        } else if (charAt == '#') {
            return SeatState.OCCUPIED;
        }
        return null;
    }


    public enum SeatState {
        FREE, OCCUPIED, FLOOR
    }

    public static void main(String[] args) {
        Task1 task1 = new Task1();
        task1.countSeats();
    }
}
