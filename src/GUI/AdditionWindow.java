package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import Contact.Contact;
import ContactsManager.ContactsManager;

public class AdditionWindow extends JFrame implements ActionListener {
    private ContactsManager contactsManager;
    private AppWindow appWindow;
    private JPanel addWindow;
    private JButton addButton;
    private JLabel nameLabel, emailLabel, phonLabel, additionMessage;
    private JTextArea nameTextArea, emailTextArea, phonTextArea;
    private Border border = BorderFactory.createLineBorder(Color.BLACK);

    public AdditionWindow(ContactsManager contactsManager, AppWindow appWindow){
        this.contactsManager = contactsManager;
        this.appWindow = appWindow;
    }

    public void showAddWindow() {
        createPanel();
        addComponents();
        setAddWindowSettings();
    }

    private void createPanel() {
        addWindow = new JPanel(new GridLayout(3, 2));
        this.add(addWindow, BorderLayout.NORTH);
    }

    private void addComponents() {
        labelCreator(nameLabel, "Name");
        nameTextArea = new JTextArea();
        textAreaCreator(nameTextArea);

        labelCreator(emailLabel, "E-mail (must contain '@')");
        emailTextArea = new JTextArea();
        textAreaCreator(emailTextArea);

        labelCreator(phonLabel, "Phone (length must be between 8 and 25)");
        phonTextArea = new JTextArea();
        textAreaCreator(phonTextArea);

        additionMessage = new JLabel("");
        this.add(additionMessage, BorderLayout.CENTER);

        buttonCreator();
    }

    private void labelCreator(JLabel label, String lableTitle) {
        label = new JLabel(lableTitle);
        label.setBorder(border);
        addWindow.add(label);
        label.setFont(new Font("Serif", Font.PLAIN, 20));
    }

    private void textAreaCreator(JTextArea textArea) {
        textArea.setBorder(border);
        textArea.setFont(new Font("Serif", Font.PLAIN, 20));
        addWindow.add(textArea);
    }

    private void buttonCreator(){
        addButton = new JButton("Save");
        addButton.setFont(new Font("Serif", Font.PLAIN, 20));
        addButton.addActionListener(this);
        this.add(addButton, BorderLayout.SOUTH);
    }
    
    private void setAddWindowSettings() {
        this.setTitle("Add Contact");
        this.setSize(800, 500);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == addButton)
        {
            Contact newContact = createContact();
            String message = contactsManager.addContact(newContact);
            additionMessage.setText(message);
            if("Added successfully".equals(message))
            {
                this.setVisible(false);
                appWindow.refresh();
            }
        }
    }

    private Contact createContact(){
        String name = nameTextArea.getText();
        String email = emailTextArea.getText();
        String phone = phonTextArea.getText();
        return new Contact(name, email, phone);
    }

}

