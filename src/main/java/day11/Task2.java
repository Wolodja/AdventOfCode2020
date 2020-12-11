package day11;

import common.InputReader;

import java.util.List;

public class Task2 {

    public void countSeats() {
        InputReader inputReader = new InputReader();
        List<String> seatLines = inputReader.readInputLines("day11.txt");
        SeatState[][] seats = transformSeatLines(seatLines);
        //printState(seats);
        boolean changed = true;
        while (changed) {
            SeatState[][] newSeats = makeSeatIteration(seats);
            //printState(newSeats);
            if (areTheSame(seats, newSeats)) {
                changed = false;
            }
            seats = newSeats;
        }
        int freeSetsCount = countFreeSeats(seats);
        System.out.println(freeSetsCount);
    }

    private void printState(SeatState[][] newSeats) {
        for (int i = 0; i < newSeats.length; i++) {
            for (int j = 0; j < newSeats[0].length; j++) {
                if (newSeats[i][j] == SeatState.FLOOR) {
                    System.out.print(".");
                }
                if (newSeats[i][j] == SeatState.OCCUPIED) {
                    System.out.print("#");
                }
                if (newSeats[i][j] == SeatState.FREE) {
                    System.out.print("L");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private boolean areTheSame(SeatState[][] seats, SeatState[][] newSeats) {
        for (int i = 0; i < newSeats.length; i++) {
            for (int j = 0; j < newSeats[0].length; j++) {
                if (seats[i][j] != newSeats[i][j]) {
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
        int maxLength = seats.length;
        int maxHeight = seats[0].length;
        int adjacentSets = 0;
        if (i > 0) {
            int temp = i - 1;
            while (temp >= 0) {
                SeatState state = seats[temp][j];
                if (state == SeatState.OCCUPIED) {
                    adjacentSets++;
                    temp = -1;
                }
                if (state == SeatState.FREE) {
                    temp = -1;
                }
                temp--;
            }
        }
        if (j > 0) {
            int temp = j - 1;
            while (temp >= 0) {
                SeatState state = seats[i][temp];
                if (state == SeatState.OCCUPIED) {
                    adjacentSets++;
                    temp = -1;
                }
                if (state == SeatState.FREE) {
                    temp = -1;
                }
                temp--;
            }
        }
        if (i > 0 && j > 0) {
            int tempI = i - 1;
            int tempJ = j - 1;
            while (tempI >= 0 && tempJ >= 0) {
                SeatState state = seats[tempI][tempJ];
                if (state == SeatState.OCCUPIED) {
                    adjacentSets++;
                    tempI = -1;
                }
                if (state == SeatState.FREE) {
                    tempI = -1;
                }
                tempI--;
                tempJ--;
            }
        }
        if (i < maxLength) {
            int temp = i + 1;
            while (temp < maxLength) {
                SeatState state = seats[temp][j];
                if (state == SeatState.OCCUPIED) {
                    adjacentSets++;
                    temp = maxLength;
                }
                if (state == SeatState.FREE) {
                    temp = maxLength;
                }
                temp++;
            }
        }
        if (j < maxHeight) {
            int temp = j + 1;
            while (temp < maxHeight) {
                SeatState state = seats[i][temp];
                if (state == SeatState.OCCUPIED) {
                    adjacentSets++;
                    temp = maxHeight;
                }
                if (state == SeatState.FREE) {
                    temp = maxHeight;
                }
                temp++;
            }
        }
        if (i < maxLength && j < maxHeight) {
            int tempI = i + 1;
            int tempJ = j + 1;
            while (tempI < maxLength && tempJ < maxHeight) {
                SeatState state = seats[tempI][tempJ];
                if (state == SeatState.OCCUPIED) {
                    adjacentSets++;
                    tempI = maxLength;
                }
                if (state == SeatState.FREE) {
                    tempI = maxLength;
                }
                tempI++;
                tempJ++;
            }
        }
        if (i > 0 && j < maxHeight) {
            int tempI = i - 1;
            int tempJ = j + 1;
            while (tempI >= 0 && tempJ < maxHeight) {
                SeatState state = seats[tempI][tempJ];
                if (state == SeatState.OCCUPIED) {
                    adjacentSets++;
                    tempI = -1;
                }
                if (state == SeatState.FREE) {
                    tempI = -1;
                }
                tempI--;
                tempJ++;
            }
        }
        if (i < maxLength && j > 0) {
            int tempI = i + 1;
            int tempJ = j - 1;
            while (tempI < maxLength && tempJ >= 0) {
                SeatState state = seats[tempI][tempJ];
                if (state == SeatState.OCCUPIED) {
                    adjacentSets++;
                    tempI = maxLength;
                }
                if (state == SeatState.FREE) {
                    tempI = maxLength;
                }
                tempI++;
                tempJ--;
            }
        }
        if (currentState == SeatState.OCCUPIED && adjacentSets >= 5) {
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
        Task2 task2 = new Task2();
        task2.countSeats();
    }
}
