package day6;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;

public class DeclarationsReader {

    public String readDeclarations(String fileName) {
        String declarations = "";
        try {
            URL url = getClass().getResource("/" + fileName);
            File myObj = new File(url.getPath());
            FileInputStream fis = new FileInputStream(myObj);
            byte[] data = new byte[(int) myObj.length()];
            fis.read(data);
            fis.close();

            declarations = new String(data, "UTF-8");
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } finally {
            return declarations;
        }
    }
}
