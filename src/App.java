import ContactsManager.ContactsManager;
import ContactsManager.ContactsManagerImp;
import GUI.AppWindow;

public class App {
    public static void main(String[] args) throws Exception {
        AppWindow appWindow = new AppWindow();
        ContactsManager contactsManager = new ContactsManagerImp();
        appWindow.showWindow(contactsManager);
    }
}
