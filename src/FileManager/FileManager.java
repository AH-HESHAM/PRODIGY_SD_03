package FileManager;

import java.io.File;
import java.io.IOException;
import java.util.List;

import Contact.Contact;
import ReaderWriter.ReaderWriter;

public class FileManager {
    
    private final String fileName = "contacts.txt";
    private ReaderWriter readerWriter;
    private ObjectMapper objectMapper;

    public FileManager() {
        readerWriter = new ReaderWriter();
        objectMapper = new ObjectMapper();
        createFileIfNotExist();
    }

    private void createFileIfNotExist() {
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Contact> readItemsFromFile() {
        String content = readerWriter.read(fileName);
        return objectMapper.mapStringToContacts(content);  
    }

    public void writeItemsToFile(List<Contact> contacts) {
        String s = objectMapper.mapContactsToString(contacts);
        readerWriter.write(fileName, s);
    }

}

