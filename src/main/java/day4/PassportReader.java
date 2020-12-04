package day4;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;

public class PassportReader {

    public String readPassports(String fileName) {
        String passports = "";
        try {
            URL url = getClass().getResource("/" + fileName);
            File myObj = new File(url.getPath());
            FileInputStream fis = new FileInputStream(myObj);
            byte[] data = new byte[(int) myObj.length()];
            fis.read(data);
            fis.close();

            passports = new String(data, "UTF-8");
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } finally {
            return passports;
        }
    }
}
