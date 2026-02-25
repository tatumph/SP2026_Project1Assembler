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
        String result = "";
        if (!set.contains("0x")) {
            switch (set) {
                case "STBA":
                    hexSet.add("store byte at");
                case "LDBA":
                    hexSet.add("load byte at");
                case "STWA":
                    hexSet.add("store word at");
                case "LDWA":
                    hexSet.add("load word at");
                case "ANDA":
                    hexSet.add("Bitwise and");
                case "ASLA":
                    hexSet.add("shift left");
                case "ASRA":
                    hexSet.add("shift right");
                case "STOP":
                    hexSet.add("stop execution");
                case "CPBA":
                    hexSet.add("compare byte at");
                case "BRNE":
                    hexSet.add("branch if not equal");
            }
        }
        return result;
    }
}
