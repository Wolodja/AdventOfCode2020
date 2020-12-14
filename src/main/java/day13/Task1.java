package day13;

import common.InputReader;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Task1 {

    public void findNearestBus(){
        InputReader inputReader = new InputReader();
        List<String> notes = inputReader.readInputLines("day13.txt");
        int requiredTime = Integer.parseInt(notes.get(0));
        List<Integer> buses = getBBusesFromInput(notes.get(1));
        AtomicInteger minimalTimeStamp = new AtomicInteger(Integer.MAX_VALUE);
        AtomicInteger myBus = new AtomicInteger(-1);
        buses.forEach(bus ->{
            if(requiredTime%bus == 0){
                System.out.println("Bing! " + bus);
            }
            int lastDeparture = requiredTime / bus;
            int nextDeparture = bus * lastDeparture + bus;
            if(nextDeparture < minimalTimeStamp.get()){
                minimalTimeStamp.set(nextDeparture);
                myBus.set(bus);
            }
        });
        System.out.println((minimalTimeStamp.get()-requiredTime)*myBus.get());
    }

    private List<Integer> getBBusesFromInput(String busesLine) {
        busesLine = busesLine.replaceAll("x,", "");
        List<String> buses = Arrays.asList(busesLine.split(","));
        return buses.stream().map(Integer::parseInt).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        Task1 task1 = new Task1();
        task1.findNearestBus();
    }
}
