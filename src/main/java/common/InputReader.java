package common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputReader {

    public List<String> readInputLines(String fileName) {
        List<String> inputLines = new ArrayList<>();
        try {
            URL url = getClass().getResource("/" + fileName);
            File myObj = new File(url.getPath());
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                inputLines.add(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } finally {
            return inputLines;
        }
    }

    public String readInputAsOneString(String fileName) {
        String input = "";
        try {
            URL url = getClass().getResource("/" + fileName);
            File myObj = new File(url.getPath());
            FileInputStream fis = new FileInputStream(myObj);
            byte[] data = new byte[(int) myObj.length()];
            fis.read(data);
            fis.close();

            input = new String(data, "UTF-8");
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } finally {
            return input;
        }
    }
}
