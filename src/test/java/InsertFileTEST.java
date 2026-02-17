import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InsertFileTEST {
    @Test
    public void getFileNameTEST() {
        GetFile getFile = new GetFile();
        String filename = GetFile.getFileName();
        Assertions.assertNotNull(filename);
    }

    @Test
    public void readFileTEST() {
        GetFile getFile = new GetFile();
        getFile.readFile("program1.pep");
    }
}
