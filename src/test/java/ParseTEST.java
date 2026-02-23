import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ParseTEST {
    @Test
    public void testParseInstructionSpecifier(){
        pepasm pepToMachine = new pepasm();
        String assemblyCode = "LDBA 0x0048, i";
        String instructionSpecifier = pepToMachine.parseInstructionSpecifier(assemblyCode);
        Assertions.assertEquals("LDBA", instructionSpecifier);
    }
}
