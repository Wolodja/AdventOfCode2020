package day16;

import common.InputReader;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Task1 {

    public void t1() {
        InputReader inputReader = new InputReader();
        List<String> ticketsLines = inputReader.readInputLines("day16.txt");
        TicketInput ticketInput = convertInput(ticketsLines);
        int sumOfInvalidNumbers = countSumOfNearbyInvalidNumbers(ticketInput);
        System.out.println(sumOfInvalidNumbers);
    }

    private int countSumOfNearbyInvalidNumbers(TicketInput ticketInput) {
        AtomicInteger sum = new AtomicInteger();
        HashSet<Integer> rangeSet = new HashSet<>();
        ticketInput.ranges.forEach(range -> {
            int start = range.from;
            while (start != range.to) {
                rangeSet.add(start);
                start++;
            }
            rangeSet.add(range.to);
        });
        ticketInput.nearbyTickets.forEach(nearByTicket -> {
            String[] ticketNumbers = nearByTicket.split(",");
            for (int i = 0; i < ticketNumbers.length; i++) {
                if (!rangeSet.contains(Integer.parseInt(ticketNumbers[i]))) {
                    sum.addAndGet(Integer.parseInt(ticketNumbers[i]));
                }
            }
        });
        return sum.get();
    }

    private TicketInput convertInput(List<String> ticketsLines) {
        TicketInput ticketInput = new TicketInput();
        List<Integer> emptyLines = new ArrayList<>();
        while (emptyLines.size() != 2) {
            for (int i = 0; i < ticketsLines.size(); i++) {
                if (ticketsLines.get(i).equals("")) {
                    emptyLines.add(i);
                }
            }
        }
        ticketInput.ranges = convertRanges(ticketsLines.subList(0, emptyLines.get(0)));
        ticketInput.myTicket = ticketsLines.subList(emptyLines.get(0) + 2, emptyLines.get(1)).get(0);
        ticketInput.nearbyTickets = ticketsLines.subList(emptyLines.get(1) + 2, ticketsLines.size());
        return ticketInput;
    }

    private List<Range> convertRanges(List<String> subList) {
        List<Range> ranges = new ArrayList<>();
        subList.forEach(range -> {
            String bearRange = range.split(": ")[1];
            String[] twoRanges = bearRange.split(" or ");
            for (int i = 0; i < twoRanges.length; i++) {
                Range range1 = new Range();
                String[] fromTo = twoRanges[i].split("-");
                range1.from = Integer.parseInt(fromTo[0]);
                range1.to = Integer.parseInt(fromTo[1]);
                ranges.add(range1);
            }
        });
        return ranges;
    }

    public static void main(String[] args) {
        Task1 task1 = new Task1();
        task1.t1();
    }

}
