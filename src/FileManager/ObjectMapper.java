package FileManager;

import java.util.ArrayList;
import java.util.List;

import Contact.Contact;

public class ObjectMapper {

    public String mapContactsToString(List<Contact> contacts){
        StringBuilder stringBuilder = new StringBuilder();
        for(Contact contact : contacts){
            stringBuilder.append(contact.getName());
            stringBuilder.append(",");
            stringBuilder.append(contact.getPhone());
            stringBuilder.append(",");
            stringBuilder.append(contact.getEmail());
            stringBuilder.append(",");
        }
        stringBuilder.delete(stringBuilder.length()-1, stringBuilder.length());
        return stringBuilder.toString();
    }

    public List<Contact> mapStringToContacts(String string){
        List<Contact> list = new ArrayList<Contact>();
        String[] arr = string.split(",");
        for(int i = 0; i < arr.length -1;){
            Contact contact = new Contact();
            contact.setName(arr[i++]);
            contact.setPhone(arr[i++]);
            contact.setEmail(arr[i++]);
            list.add(contact);
        }
        return list;
    }
    
    
}
