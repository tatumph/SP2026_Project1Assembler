import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class InsertFileTEST {
    @Test
    public void getFileNameTEST() {
        GetFile getFile = new GetFile();
        String filename = GetFile.getFileName();
        Assertions.assertNotNull(filename);
    }

    @Test
    public void readFileTEST() throws IOException {
        GetFile getFile = new GetFile();
        getFile.readFile("program1.pep");
    }
}
