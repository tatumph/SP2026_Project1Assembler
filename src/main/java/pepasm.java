import java.io.*;
import java.util.Scanner;

public class pepasm {
    public static String getFileName() {
        return "program1.pep";
    }


    public void readFile(String s) throws FileNotFoundException {
        File file = new File("code/" + s);
        try (Scanner reader = new Scanner(file)) {
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                System.out.println(line);
            }
        }
    }
}
