package day7;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BagsRulesReader {

    public List<String> readBagsRules(String fileName) {
        List<String> bagRules = new ArrayList<>();
        try {
            URL url = getClass().getResource("/" + fileName);
            File myObj = new File(url.getPath());
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                bagRules.add(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } finally {
            return bagRules;
        }
    }
}
