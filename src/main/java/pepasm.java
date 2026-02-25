import java.io.*;
import java.util.*;

public class pepasm {
    public List<String> charSet = new ArrayList<String>();
    public List<String> hexSet = new ArrayList<String>();


    public static void  main(String[] args) throws Exception {
        if(args.length != 1){
            System.out.println("Runtime: Use java pepasm file.pep");
            return;
        }

        HashMap<> opcodeToHex = getOpcodeToHexHash();

        String fileName = args[0]; //get first argument (ie, file.pep)
        readFile(fileName);
    }

    protected static HashMap<String, String> getOpcodeToHexHash(){
        HashMap<String, String> opcodeToHex = new HashMap<>();
        opcodeToHex.put("STOP", "00");
        opcodeToHex.put("LDWA", "C0");
        opcodeToHex.put("LDBA", "D0");
        opcodeToHex.put("STWA", "E1");
        opcodeToHex.put("STBA", "F1");
        opcodeToHex.put("ADDA", "60");
        opcodeToHex.put("SUBA", "70");
        opcodeToHex.put("ANDA", "80");
        opcodeToHex.put("ASLA", "0A");
        opcodeToHex.put("ASRA", "0C");
        opcodeToHex.put("CPBA", "B0");
        opcodeToHex.put("BRNE", "1A");
        return opcodeToHex;
    }

    public String getFileName() {
        return "program1.pep";
    }


    public static void readFile(String file) throws FileNotFoundException {
       // File file = new File("code/" + s);
       // System.out.println("Original Assembly code: ");
        StringBuilder output = new StringBuilder();
        try (Scanner reader = new Scanner(file)) {
            while (reader.hasNextLine()) {
                String line = reader.nextLine();

                if (line.equals(".END")) {
                    break;
                }
                if (line.isEmpty()) {
                    continue;
                }
//                charSet.add(Arrays.toString(line.split(" ")));
//                System.out.println(line);
                this.assembleLine(line, output);
            }
        }
    }

    public void assembleLine(String line, StringBuilder output){

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
