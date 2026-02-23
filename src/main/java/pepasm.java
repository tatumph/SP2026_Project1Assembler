import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class pepasm {
    public List<String> charSet = new ArrayList<String>();

    public static String getFileName() {
        return "program1.pep";
    }


    public void readFile(String s) throws FileNotFoundException {
        File file = new File("code/" + s);
        System.out.println("Original Assembly code: ");
        try (Scanner reader = new Scanner(file)) {
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                charSet.add(Arrays.toString(line.split(" ")));
                System.out.println(line);
            }
        }
    }
}
