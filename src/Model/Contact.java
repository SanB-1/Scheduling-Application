package Model;

public class Contact {

    private Integer ID;
    private String name;
    private String email;

    public Contact(int cID, String cName, String cEmail){
        this.ID = cID;
        this.name = cName;
        this.email = cEmail;
    }

    //Setter Methods.

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //Getter Methods.

    public Integer getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
