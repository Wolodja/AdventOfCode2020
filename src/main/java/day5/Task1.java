package day5;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class Task1 {

    public void findHighestSeadId() {
        List<String> tickets = getTickets();
        AtomicInteger max = new AtomicInteger(Integer.MIN_VALUE);
        tickets.forEach(ticket -> {
            Integer seatId = getSeatId(ticket);
            if(seatId > max.get()){
                max.set(seatId);
            }
        });
        System.out.println("Max seadId is :" + max);
    }

    //This method is for Task 2
    public List<Integer> getAllSetsIds(){
        return getTickets().stream().map(this::getSeatId).collect(Collectors.toList());
    }

    private List<String> getTickets() {
        TicketReader ticketReader = new TicketReader();
        List<String> tickets = ticketReader.readTickets("day5.txt");
        return tickets;
    }

    private Integer getSeatId(String ticket) {
        int row = calculateRowOrSeat(ticket.substring(0,7), 127, 'F', 'B');
        int seat = calculateRowOrSeat(ticket.substring(7), 7, 'L', 'R');
        return row * 8 + seat;
    }

    private int calculateRowOrSeat(String letters, int max, char minLetter, char maxLetter){
        AtomicReference<Integer> minIndex = new AtomicReference<>(0);
        AtomicReference<Integer> maxIndex = new AtomicReference<>(max);
        letters.chars().forEach(c -> {
            if (c == minLetter) {
                maxIndex.set((maxIndex.get() - minIndex.get()) / 2 + minIndex.get());
            } else if (c == maxLetter) {
                minIndex.set((maxIndex.get() - minIndex.get()) / 2 + 1 + minIndex.get());
            }
        });
        return minIndex.get();
    }


    public static void main(String[] args) {
        Task1 task1 = new Task1();
        task1.findHighestSeadId();
    }
}
