import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class InsertFileTEST {
    @Test
    public void getFileNameTEST() {
        pepasm pepasm = new pepasm();
        String filename = pepasm.getFileName();
        Assertions.assertNotNull(filename);
    }

    @Test
    public void readFileTEST() throws IOException {
        pepasm pepasm = new pepasm();
        pepasm.readFile("program1.pep");
    }
}
