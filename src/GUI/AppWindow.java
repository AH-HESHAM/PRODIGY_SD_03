package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;

import Contact.Contact;
import ContactsManager.ContactsManager;

public class AppWindow extends JFrame implements ActionListener {
    private JPanel mainPanel, buttonPanel;
    private JLabel title;
    private JButton addContactButton, delelteButton;
    private JTable table;
    private Color backgroundColor = new Color(51, 102, 255);
    private ContactsManager contactsManager;
    private int selectedRow;

    public void refresh() {
        this.setVisible(false);
        new AppWindow().showWindow(contactsManager);
    }

    public void showWindow(ContactsManager contactsManager) {
        this.contactsManager = contactsManager;
        createPanel();
        createScroller();
        addTitle();
        addList();
        additionButton();
        adjustFont();
        setWindowSettings();
    }

    private void createPanel() {
        mainPanel = new JPanel(new BorderLayout());
    }

    private void createScroller() {
        JScrollPane scroller = new JScrollPane(mainPanel);
        scroller.setWheelScrollingEnabled(true);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.add(scroller);
    }

    private void addTitle() {
        title = new JLabel("MY contacts");
        mainPanel.add(title, BorderLayout.NORTH);
    }

    private void addList() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Column 1");
        model.addColumn("Column 2");
        model.addColumn("Column 3");
        model.addRow(new Object[] { "Name", "Phone", "E-mail" });
        java.util.List<Contact> allList = contactsManager.getAllContacts();
        for (Contact contact : allList) {
            model.addRow(new Object[] { contact.getName(), contact.getPhone(), contact.getEmail() });
        }
        table = new JTable(model);
        table.setRowHeight(30);
        table.getModel().addTableModelListener(e -> {
            handleUpdate(e);
        });
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (event.getValueIsAdjusting())
                    return;
                selectedRow = table.getSelectedRow();
            }
        });
        mainPanel.add(table, BorderLayout.CENTER);
    }

    private void handleUpdate(TableModelEvent e) {
        if (e.getType() == TableModelEvent.UPDATE) {
            int row = e.getFirstRow();
            Contact editedContact = new Contact((String) table.getValueAt(row, 0),
                    (String) table.getValueAt(row, 2),
                    (String) table.getValueAt(row, 1));
            contactsManager.editContact(row - 1, editedContact);
            refresh();
        }
    }

    private void additionButton() {
        buttonPanel = new JPanel(new GridLayout(1,2));
        
        addContactButton = new JButton("+");
        buttonPanel.add(addContactButton);
        addContactButton.addActionListener(this);

        delelteButton = new JButton("Del (Select row then click to delete)");
        buttonPanel.add(delelteButton);
        delelteButton.addActionListener(this);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void adjustFont() {
        title.setFont(new Font("Serif", Font.BOLD, 30));
        table.setFont(new Font("Serif", Font.PLAIN, 20));
        table.setBackground(backgroundColor);
        mainPanel.setBackground(backgroundColor);
    }

    private void setWindowSettings() {
        this.setTitle("My Contacts");
        this.setSize(800, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addContactButton)
            new AdditionWindow(contactsManager, this).showAddWindow();
        else if(e.getSource() == delelteButton)
            handleDelete();
    }

    private void handleDelete(){
        if(selectedRow != 0){
            contactsManager.removeContact(selectedRow - 1);
            refresh();
        }
    }

}

