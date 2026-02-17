package PACKAGE_NAME;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ParseTEST {
    @Test
    public void testParseInstructionSpecifier(){
        Parser parser = new Parser();
        String assemblyCode = "LDBA 0x0048, i";
        String instructionSpecifier = parser.parseInstructionSpecifier();
        Assertions.assertEquals("LDBA", instructionSpecifier);
    }
}
