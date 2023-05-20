package Model;

/**
 * Class for the User Object.
 */
public class User {

    private Integer ID;
    private String username;
    private String password;

    /**
     * Constructor for the User Object.
     * @param uID
     * @param uUsername
     * @param uPassword
     */
    public User(int uID, String uUsername, String uPassword){
        this.ID = uID;
        this.username = uUsername;
        this.password = uPassword;
    }


    /**
     * Sets the ID.
     * @param ID
     */
    public void setID(Integer ID) {
        this.ID = ID;
    }

    /**
     * Sets the User's Username.
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Sets the User's Password.
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the ID.
     * @return
     */
    public Integer getID() {
        return ID;
    }

    /**
     * Returns the User's Username.
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the User's Password.
     * @return
     */
    public String getPassword() {
        return password;
    }
}
