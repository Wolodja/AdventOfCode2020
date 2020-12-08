package day8;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Task1 {


    private void run() {
        InstructionsReader instructionsReader = new InstructionsReader();
        List<String> allInstructions = instructionsReader.readInstructions("day8.txt");
        List<Instruction> instructions = reformatInstructions(allInstructions);
        //Task 1
        int accumulator = getAccumulator(instructions);
        System.out.println("Task 1 result: " + accumulator);

        //Task 2
        instructions.forEach(instruction -> {
            if(instruction.getInstructionType().equals(Instruction.InstructionType.jmp)){
                instruction.setInstructionType(Instruction.InstructionType.nop);
                getAccumulator(instructions);
                instruction.setInstructionType(Instruction.InstructionType.jmp);
            }
            if(instruction.getInstructionType().equals(Instruction.InstructionType.nop)){
                instruction.setInstructionType(Instruction.InstructionType.jmp);
                getAccumulator(instructions);
                instruction.setInstructionType(Instruction.InstructionType.nop);
            }

        });
    }

    private int getAccumulator(List<Instruction> instructions) {
        int accumulator = 0;
        Set<Integer> visitedInstructions = new HashSet<>();
        int currentInstruction = 0;
        int accDiff = 0;
        try {
            while (!visitedInstructions.contains(currentInstruction)) {
                accumulator += accDiff;
                accDiff = 0;
                visitedInstructions.add(currentInstruction);
                Instruction curr = instructions.get(currentInstruction);

                switch (curr.getInstructionType()) {
                    case nop:
                        currentInstruction++;
                        break;
                    case acc:
                        accDiff = curr.getNumber();
                        currentInstruction++;
                        break;
                    case jmp:
                        currentInstruction += curr.getNumber();
                        break;
                    default:
                        throw new RuntimeException();
                }
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Task 2 result : " + accumulator);
            return accumulator;
        }
        return accumulator;
    }

    private List<Instruction> reformatInstructions(List<String> allInstructions) {
        List<Instruction> instructions = new ArrayList<>();
        allInstructions.forEach(i -> {
            Instruction.InstructionType instructionType = Instruction.InstructionType.valueOf(i.substring(0, 3));
            int number = Integer.parseInt(i.substring(4));
            Instruction newInstruction = new Instruction(instructionType, number);
            instructions.add(newInstruction);
        });
        return instructions;
    }

    public static void main(String[] args) {
        Task1 task1 = new Task1();
        task1.run();
    }
}
