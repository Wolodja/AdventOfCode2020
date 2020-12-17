package day16;

import common.InputReader;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class Task2 {

    public void t2() {
        InputReader inputReader = new InputReader();
        List<String> ticketsLines = inputReader.readInputLines("day16.txt");
        TicketInput ticketInput = convertInput(ticketsLines);
        List<TicketGroup> ticketGroups = findTicketGroups(ticketInput);
        List<Integer> requiredIndexes = ticketGroups.stream().filter(g -> g.name.startsWith("depart")).map(g -> g.number).collect(Collectors.toList());
        String[] myTicketNumbers = ticketInput.myTicket.split(",");
        Long multiplication = 1L;
        for (int i = 0; i < myTicketNumbers.length; i++) {
            if (requiredIndexes.contains(i)) {
                multiplication *= Long.parseLong(myTicketNumbers[i]);
            }
        }
        System.out.println(multiplication);
    }

    private List<TicketGroup> findTicketGroups(TicketInput ticketInput) {
        ticketInput.nearbyTickets = filterNearbyTickets(ticketInput);
        int groupSize = ticketInput.nearbyTickets.get(0).split(",").length;
        List<TicketGroup> ticketGroups = new ArrayList<>();
        for (int i = 0; i < groupSize; i++) {
            TicketGroup ticketGroup = new TicketGroup();
            ticketGroup.number = i;
            ticketGroups.add(ticketGroup);
        }
        ticketInput.nearbyTickets.forEach(nearbyTicket -> {
            String[] numbers = nearbyTicket.split(",");
            for (int i = 0; i < numbers.length; i++) {
                ticketGroups.get(i).numbers.add(Integer.parseInt(numbers[i]));
            }
        });
        boolean allAssigned = false;
        List<String> assignedNames = new ArrayList<>();
        while (!allAssigned) {

            ticketGroups.forEach(group -> {
                AtomicInteger count = new AtomicInteger();
                AtomicReference<String> currentName = new AtomicReference<>("");
                ticketInput.bigRanges.forEach(bigRange -> {
                    if (!assignedNames.contains(bigRange.name) && bigRange.numbers.containsAll(group.numbers)) {
                        count.getAndIncrement();
                        currentName.set(bigRange.name);

                    }
                });
                if (count.get() == 1) {
                    assignedNames.add(currentName.get());
                    group.name = currentName.get();
                }
            });
            long countNotAssigned = ticketGroups.stream().filter(g -> g.name.equals("")).count();
            if (countNotAssigned == 0L) {
                allAssigned = true;
            }
        }
        return ticketGroups;
    }

    private List<String> filterNearbyTickets(TicketInput ticketInput) {
        List<String> filteredTickets = new ArrayList<>();
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
            boolean valid = true;
            for (int i = 0; i < ticketNumbers.length; i++) {
                if (!rangeSet.contains(Integer.parseInt(ticketNumbers[i]))) {
                    valid = false;
                }
            }
            if (valid) {
                filteredTickets.add(nearByTicket);
            }
        });
        return filteredTickets;
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
        ticketInput.ranges = convertRanges2(ticketsLines.subList(0, emptyLines.get(0)));
        ticketInput.bigRanges = convertRanges(ticketsLines.subList(0, emptyLines.get(0)));
        ticketInput.myTicket = ticketsLines.subList(emptyLines.get(0) + 2, emptyLines.get(1)).get(0);
        ticketInput.nearbyTickets = ticketsLines.subList(emptyLines.get(1) + 2, ticketsLines.size());
        return ticketInput;
    }

    private List<BigRange> convertRanges(List<String> subList) {
        List<BigRange> bigRanges = new ArrayList<>();
        subList.forEach(range -> {
            List<Range> ranges = new ArrayList<>();
            BigRange bigRange = new BigRange();
            bigRange.name = range.split(": ")[0];
            String bearRange = range.split(": ")[1];
            String[] twoRanges = bearRange.split(" or ");
            for (int i = 0; i < twoRanges.length; i++) {
                Range range1 = new Range();
                String[] fromTo = twoRanges[i].split("-");
                range1.from = Integer.parseInt(fromTo[0]);
                range1.to = Integer.parseInt(fromTo[1]);
                ranges.add(range1);
            }
            HashSet<Integer> rangeSet = new HashSet<>();
            ranges.forEach(range2 -> {
                int start = range2.from;
                while (start != range2.to) {
                    rangeSet.add(start);
                    start++;
                }
                rangeSet.add(range2.to);
            });
            bigRange.numbers = rangeSet;
            bigRanges.add(bigRange);
        });
        return bigRanges;
    }

    private List<Range> convertRanges2(List<String> subList) {
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
        Task2 task2 = new Task2();
        task2.t2();
    }
}
