package day14;

import common.InputReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Task2 {

    public void t2() {
        InputReader inputReader = new InputReader();
        List<String> input = inputReader.readInputLines("day14.txt");
        List<Decoder> decoders = convertInput(input);
        HashMap<String, Long> result = new HashMap<>();
        for (int i = 0; i < decoders.size(); i++) {
            result = decodeDecoder(result, decoders.get(i));
        }
        System.out.println(result.values().stream().reduce(0L, (a, b) -> a + b));
    }

    private HashMap<String, Long> decodeDecoder(HashMap<String, Long> memory, Decoder decoder) {
        for (int i = 0; i < decoder.updateMemories.size(); i++) {
            String result = addBinary(decoder.mask, decoder.updateMemories.get(i).memory);
            int countX = 0;
            for (int j = 0; j < result.length(); j++) {
                if (result.charAt(j) == 'X') {
                    countX++;
                }
            }
            String initValue = "";
            String maxValue = "";
            while (initValue.length() != countX) {
                initValue += "0";
                maxValue += "1";
            }
            do{
                String r1 = result;
                for (int a = 0; a < initValue.length(); a++) {
                    r1 = r1.replaceFirst("X", String.valueOf(initValue.charAt(a)));
                }
                memory.put(String.valueOf(Long.parseLong(r1, 2)),
                    Long.valueOf(decoder.updateMemories.get(i).value));
                initValue = incrementInitValue(initValue);
            }while(Integer.parseInt(initValue) <= Integer.parseInt(maxValue));
        }
        return memory;
    }

    private String incrementInitValue(String initValue) {
        Long decimal = Long.parseLong(initValue, 2);
        decimal++;
        String newValue = Long.toBinaryString(decimal);
        while (newValue.length() < initValue.length()) {
            newValue = "0" + newValue;
        }
        return newValue;

    }

    private String addBinary(String mask, String memoryNumber) {
        String binaryValue = Integer.toBinaryString(Integer.valueOf(memoryNumber));
        while (binaryValue.length() != 36) {
            binaryValue = "0" + binaryValue;
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 36; i++) {
            if (mask.charAt(i) == 'X' || mask.charAt(i) == '1') {
                result.append(mask.charAt(i));
            } else {
                result.append(binaryValue.charAt(i));
            }
        }
        return result.toString();
    }

    private List<Decoder> convertInput(List<String> input) {
        String mask = "";
        List<UpdateMemory> updateMemories = new ArrayList<>(0);
        List<Decoder> decoders = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            String inp = input.get(i);
            if (inp.startsWith("mask")) {
                mask = inp.split(" = ")[1];
            } else if (inp.startsWith("mem")) {
                inp = inp.replace("mem[", "");
                String[] updateMemoryArray = inp.split("] = ");
                UpdateMemory updateMemory = new UpdateMemory();
                updateMemory.memory = updateMemoryArray[0];
                updateMemory.value = updateMemoryArray[1];
                updateMemories.add(updateMemory);
            }
            if (i == input.size() - 1 || input.get(i + 1).startsWith("mask")) {
                Decoder decoder = new Decoder();
                decoder.mask = mask;
                decoder.updateMemories = updateMemories;
                decoders.add(decoder);
                updateMemories = new ArrayList<>();
            }
        }
        return decoders;
    }

    public static void main(String[] args) {
        Task2 task2 = new Task2();
        task2.t2();
    }
}
