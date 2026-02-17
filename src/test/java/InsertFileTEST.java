import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InsertFileTEST {
    @Test
    public void getFileNameTESR() {
        GetFile getFile = new GetFile();
        String filename = GetFile.getFileName;
        Assertions.assertNotNull(filename);
    }
}
