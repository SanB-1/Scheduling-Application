package Model;

import java.sql.Date;
import java.sql.Timestamp;

public class User {

    private Integer ID;
    private String username;
    private String password;

    public User(int uID, String uUsername, String uPassword, Date uCreatedDate, String uCreatedBy, Timestamp uLastUpdate,
                String uLastUpdateBy){
        this.ID = uID;
        this.username = uUsername;
        this.password = uPassword;
    }

    //Setter Methods.

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //Getter Methods.

    public Integer getID() {
        return ID;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
