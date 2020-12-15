package day14;

import common.InputReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Task1 {

    public void t1(){
        InputReader inputReader = new InputReader();
        List<String> input = inputReader.readInputLines("day14.txt");
        List<Decoder> decoders = convertIput(input);
        HashMap<String, Long> result = new HashMap<>();
        for(int i=0; i< decoders.size(); i++) {
            result = decodeDecoder(result, decoders.get(i));
        }
        System.out.println(result.values().stream().reduce(0L, (a, b) -> a + b));
    }

    private HashMap<String, Long> decodeDecoder(HashMap<String, Long> memory, Decoder decoder) {
        for(int i=0; i< decoder.updateMemories.size(); i++){
            Long result = addBinary(decoder.mask, decoder.updateMemories.get(i).value);
            memory.put(decoder.updateMemories.get(i).memory, result);
        }
        return memory;
    }

    private Long addBinary(String mask, String value) {
        String binaryValue = Integer.toBinaryString(Integer.valueOf(value));
        while(binaryValue.length()!=36){
            binaryValue = "0" + binaryValue;
        }
        StringBuilder result = new StringBuilder();
        for(int i=0; i<36; i++){
            if(mask.charAt(i) == 'X' || mask.charAt(i) == binaryValue.charAt(i)){
                result.append(binaryValue.charAt(i));
            } else {
                result.append(mask.charAt(i));
            }
        }
        return Long.parseLong(result.toString(),2);
    }

    private List<Decoder> convertIput(List<String> input) {
        String mask ="";
        List<UpdateMemory> updateMemories = new ArrayList<>(0);
        List<Decoder> decoders = new ArrayList<>();
        for(int i=0; i<input.size(); i++){
            String inp = input.get(i);
            if(inp.startsWith("mask")){
                mask = inp.split(" = ")[1];
            } else if(inp.startsWith("mem")){
                inp = inp.replace("mem[", "");
                String [] updateMemoryArray = inp.split("] = ");
                UpdateMemory updateMemory = new UpdateMemory();
                updateMemory.memory = updateMemoryArray[0];
                updateMemory.value = updateMemoryArray[1];
                updateMemories.add(updateMemory);
            }
            if(i == input.size()-1 || input.get(i+1).startsWith("mask")){
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
        Task1 task1 = new Task1();
        task1.t1();
    }
}
