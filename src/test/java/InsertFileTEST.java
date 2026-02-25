import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class InsertFileTEST {

    @Test
    public void readFileTEST() throws IOException {
        pepasm pepasm = new pepasm();
        pepasm.readFile("program1.pep");
    }
}
