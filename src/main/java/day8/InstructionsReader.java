package day8;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InstructionsReader {

    public List<String> readInstructions(String fileName) {
        List<String> instructions = new ArrayList<>();
        try {
            URL url = getClass().getResource("/" + fileName);
            File myObj = new File(url.getPath());
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                instructions.add(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } finally {
            return instructions;
        }
    }
}
