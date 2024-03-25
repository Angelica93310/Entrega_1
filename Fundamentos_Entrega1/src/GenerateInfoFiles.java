import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GenerateInfoFiles {
    public void read(String fileName) {
        StringBuilder data = new StringBuilder();
        Path filePath = Paths.get("C:\\Users\\Angie Téllez\\eclipse-workspace\\Fundamentos_Entrega1\\" + fileName);
        try {
            BufferedReader tx = Files.newBufferedReader(filePath);
            String line;
            while ((line = tx.readLine()) != null) {
                data.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Data from " + fileName + ":\n" + data);
    }
}