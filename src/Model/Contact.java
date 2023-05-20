package Model;

/**
 * Class for the Contact Object.
 */
public class Contact {

    private Integer ID;
    private String name;
    private String email;

    /**
     * Constructor for the Contact Object.
     * @param cID
     * @param cName
     * @param cEmail
     */
    public Contact(int cID, String cName, String cEmail){
        this.ID = cID;
        this.name = cName;
        this.email = cEmail;
    }

    /**
     * Sets ID.
     * @param ID
     */
    public void setID(Integer ID) {
        this.ID = ID;
    }

    /**
     * Sets Name.
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets Email.
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns ID.
     * @return
     */
    public Integer getID() {
        return ID;
    }

    /**
     * Returns Name.
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Returns Email.
     * @return
     */
    public String getEmail() {
        return email;
    }
}
