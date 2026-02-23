import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class pepasm {
    public List<String> charSet = new ArrayList<String>();
    public List<String> hexSet = new ArrayList<String>();


    public static void  main(String[] args) {


    }

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

    public void outputHex() {
        String set = null;
        for (int i = 0; i < charSet.size(); i++) {
            set = charSet.get(i);
            hexSet.add(translateAssembly(set));
        }
    }

    private String translateAssembly(String set) {
        return set;
    }
}
