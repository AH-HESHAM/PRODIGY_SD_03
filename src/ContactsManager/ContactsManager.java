package ContactsManager;

import java.util.List;

import Contact.Contact;

public interface ContactsManager {
    public String addContact(Contact newContact);
    public void removeContact(int ContactIndexToRemove);
    public String editContact(int oldIndex, Contact editedContact);
    public List<Contact> getAllContacts();
}
