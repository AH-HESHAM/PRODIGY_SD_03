package ReaderWriter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ReaderWriter {
    public String read(String path) {
        String content = "";
        Path filePath = Path.of(path);
        try {
            content = Files.readString(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    public void write(String path, String s) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write(s);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
