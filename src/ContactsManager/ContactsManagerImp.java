package ContactsManager;

import java.util.Collections;
import java.util.List;

import Contact.Contact;
import FileManager.FileManager;

public class ContactsManagerImp implements ContactsManager {
    private List<Contact> allContacts;
    private FileManager fileManager;

    public ContactsManagerImp() {
        fileManager = new FileManager();
        allContacts = getAllContacts();
    }

    @Override
    public List<Contact> getAllContacts() {
        updateFile();
        return fileManager.readItemsFromFile();
    }

    private void updateFile() {
        if (allContacts != null && !allContacts.isEmpty()) {
            fileManager.writeItemsToFile(allContacts);
        }
    }

    @Override
    public String editContact(int oldIndex, Contact editedContact) {
        if(isValidData(editedContact))
        {
            removeContact(oldIndex);
            return addContact(editedContact);
        }
        return "Contacts Rule Violation";
    }

    @Override
    public void removeContact(int contactIndexToRemove) {
        allContacts.remove(contactIndexToRemove);
    }

    @Override
    public String addContact(Contact newContact) {
        if (alreadyExists(newContact))
            return "Already exists contact";
        if (!isValidData(newContact))
            return "Contacts Rule Violation";
        addToList(newContact);
        return "Added successfully";
    }

    private boolean alreadyExists(Contact contact) {
        for (Contact current : allContacts) 
            if (current.getName().equals(contact.getName()))
                return true;
        return false;
    }

    private boolean isValidData(Contact contact) {
        if (!emailContainsAtChar(contact.getEmail()))
            return false;
        if (!reasonablePhoneLength(contact.getPhone()))
            return false;
        if(!phoneOnlyNumbers(contact.getPhone()))
            return false;
        return true;
    }

    private boolean emailContainsAtChar(String email) {
        return email.contains("@");
    }

    private boolean reasonablePhoneLength(String phone) {
        return phone.length() >= 8 && phone.length() <= 25;
    }

    private boolean phoneOnlyNumbers(String phone){
        for(int i = 0 ; i < phone.length(); i++){
            if(phone.charAt(i) - '0' < 0 || phone.charAt(i) - '0' > 9)
                return false;
        }
        return true;
    }

    private void addToList(Contact newContact) {
        allContacts.add(newContact);
        Collections.sort(allContacts);
        updateFile();
    }

}

