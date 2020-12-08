package day8;

public class Instruction {

    private InstructionType instructionType;

    private int number;

    public Instruction(InstructionType instructionType, int number) {
        this.instructionType = instructionType;
        this.number = number;
    }

    public void setInstructionType(InstructionType instructionType) {
        this.instructionType = instructionType;
    }

    public InstructionType getInstructionType() {
        return instructionType;
    }

    public int getNumber() {
        return number;
    }

    public enum InstructionType {

        nop, acc, jmp
    }
}


