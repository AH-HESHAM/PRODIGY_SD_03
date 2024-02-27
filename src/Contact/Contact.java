package Contact;

public class Contact implements Comparable<Contact> {
    private String name;
    private String email;
    private String phone;

    public Contact(){
        
    }

    public Contact(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public int compareTo(Contact otherContact) {
        String thisName = this.name.toLowerCase();
        String otherName = otherContact.getName().toLowerCase();
        int nameComparisonResult = thisName.compareTo(otherName);
        return nameComparisonResult;
    }

}
