package Model;

import java.sql.Date;
import java.sql.Timestamp;

public class Country {

    private Integer ID;
    private String name;

    public Country(Integer cID, String cName){
        this.ID = cID;
        this.name = cName;
    }

    //Setter Methods.

    public void setID(Integer cID){
        this.ID = cID;
    }

    public void setName(String cName){
        this.name = cName;
    }

    //Getter Methods.

    public Integer getID() {
        return ID;
    }

    public String getName() {
        return name;
    }
}
