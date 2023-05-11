package Model;

import java.sql.Date;
import java.sql.Timestamp;

public class Customer {

    private Integer ID;
    private Integer divID;
    private String name;
    private String address;
    private String zip;
    private String phone;

    public Customer(int cID, int cDivID, String cName, String cAddress, String cZip, String cPhone){
        this.ID = cID;
        this.divID = cDivID;
        this.name = cName;
        this.address = cAddress;
        this.zip = cZip;
        this.phone = cPhone;
    }

    //Setter methods.

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public void setDivID(Integer divID) {
        this.divID = divID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    //Getter methods.

    public Integer getID() {
        return ID;
    }

    public Integer getDivID() {
        return divID;
    }

    public String getName() {
        return name;
    }

    public String getAddress(){
        return address;
    }

    public String getZip() {
        return zip;
    }

    public String getPhone() {
        return phone;
    }
}
