import java.io.*;
import java.util.*;

public class Pepasm {
    static Map<String, Map<String, Integer>> opcodeTable = new HashMap<>();

    static {
        //Includes addressing modes
        opcodeTable.put("LDBA", Map.of("i", 0xD0, "d", 0xD1));
        opcodeTable.put("STBA", Map.of("d", 0xF1));
        opcodeTable.put("LDWA", Map.of("i", 0xC0, "d", 0xC1));
        opcodeTable.put("STWA", Map.of("d", 0xE1));
        opcodeTable.put("ANDA", Map.of("i", 0x80, "d", 0x81));
        opcodeTable.put("CPBA", Map.of("i", 0xB0, "d", 0xB1));
        opcodeTable.put("BRNE", Map.of("i", 0x1A, "d", 0x1A));

        opcodeTable.put("ADDA", Map.of("i", 0x70, "d", 0x71));
        opcodeTable.put("SUBA", Map.of("i", 0x78, "d", 0x79));

        opcodeTable.put("ASLA", Map.of("", 0x0A));
        opcodeTable.put("ASRA", Map.of("", 0x0C));
        opcodeTable.put("STOP", Map.of("", 0x00));
    }

    static Map<String, Integer> labelTable = new HashMap<>();
    static int programCounter = 0;

    public static void  main(String[] args) throws Exception {
        if(args.length != 1){
            System.out.println("Runtime: Use java pepasm file.pep");
            return;
        }

        String fileName = args[0]; //get first argument (ie, file.pep)
        System.out.println("Filename "+ fileName);
        readFile(fileName);
    }

//    public String getFileName() {
//        return "program1.pep";
//    }


    public static void readFile(String file) throws FileNotFoundException {
        StringBuilder output = new StringBuilder();
        List<String> lines = new ArrayList<>();
        try (Scanner reader = new Scanner(new File(file))) {
            while(reader.hasNextLine()){
                String line = reader.nextLine();
                if(line.equals(".END")) break;
                lines.add(line);
            }

            programCounter = 0;
            labelTable.clear(); //Prevent unwanted things 'clogging' up the map

            for(String line : lines){
                line = stripComments(line);
                if(line.isEmpty()) continue;

                if(line.contains(":")){
                    String label = line.substring(0, line.indexOf(":")).trim();
                    labelTable.put(label, programCounter);

                    line = line.substring(line.indexOf(":")+1).trim();
                    if (line.isEmpty()) continue;
                }

                programCounter += instructionSize(line);
            }

            programCounter = 0;
            for(String rawLine : lines){
                String line = stripComments(rawLine);
                if(line.isEmpty()) continue;

                if(line.contains(":")){
                    line = line.substring(line.indexOf(":")+1).trim();
                    if(line.isEmpty()) continue;
                }

                assembleLine(line, output);
            }
            System.out.println(output.toString().trim());
        }
    }

    private static String stripComments(String line) {
        if(line.contains(";")){
            line = line.substring(0, line.indexOf(";"));
        }
        return line.trim();
    }

    private static int instructionSize(String line) {
        String[] parts = line.split("\\s+", 2); //At most into 2 pieces
        String opcode = parts[0].toUpperCase();

        if(parts.length == 1){
            return 1; //opcode only
        }
        return 3; //Opcode + 2-byte operand
    }

    public static void assembleLine(String line, StringBuilder output){
        /*Split line at one or more whitespace characters using regex
          Splits into at most 2 pieces to allow us to parse the opcode and the operand
         */
        String[] parts = line.split("\\s+", 2);
        String opcode = parts[0].toUpperCase();

        String operand = null;
        String mode = "";
        if(parts.length > 1){
            //Recognizes operand & addressing mode
            //Operand and mode will be stored in separate entries in opParts, which we can access via indices
            String[] opParts = parts[1].split(",");
            operand = opParts[0].trim();
            mode = opParts[1].trim();
        }

        outputHex(opcode, operand, mode, output);
    }

    public static void outputHex(String opcode, String operand, String mode, StringBuilder output) {
        //Check to see if opcode is one of the ones in HashMap
        if(!opcodeTable.containsKey(opcode)){
            throw new RuntimeException("Invalid opcode: "+ opcode);
        }

        Map<String, Integer> modeMap = opcodeTable.get(opcode);

        //Anticipate that the .pep file will be wrong that way you can diagnose
        if (!modeMap.containsKey(mode)){
            throw new RuntimeException("Invalid addressing mode for " + opcode);
        }

        int opcodeByte = modeMap.get(mode);

        appendByte(output, opcodeByte);

        if(operand != null){
            int value = parseOperand(operand);
            //Pep9 is big endian
            //For the high, shift 8 bits to the right to get the high and use 0xFF to ensure that we only get the lower 8 bits
            int high = (value >> 8) & 0xFF;
            int low = value & 0xFF;

            appendByte(output, high);
            appendByte(output, low);
        }
    }

    public static int parseOperand(String operand){
        if(labelTable.containsKey(operand)){
            return labelTable.get(operand);
        }

        if(operand.startsWith("0x") || operand.startsWith("0X")){
            //Specifies to look at the string after 'Ox' and specifies that FC16 is base-16
            return Integer.parseInt(operand.substring(2), 16);
        }
        return Integer.parseInt(operand);
    }

    public static void appendByte(StringBuilder output, int value){
        //I love regex. Formats integer as uppercase hex with 2 digit padding (leading 0s ok)
        //Space after separates bytes
        output.append(String.format("%02X ", value));
    }
}