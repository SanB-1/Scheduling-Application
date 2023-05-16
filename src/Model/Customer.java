package Model;

import Database.JDBC;
import java.sql.*;

public class Customer {

    private Integer ID;
    private Integer divID;
    private String name;
    private String address;
    private String zip;
    private String phone;
    private Timestamp createDate;
    private String createdBy;
    private Timestamp lastUpdate;
    private String lastUpdateBy;

    public Customer(int cID, String cName, String cAddress, String cZip, String cPhone, Timestamp cDate,
                    String cBy, Timestamp cLastUpdate, String cLUpdateBy, int cDivID){
        this.ID = cID;
        this.divID = cDivID;
        this.name = cName;
        this.address = cAddress;
        this.zip = cZip;
        this.phone = cPhone;
        this.createDate = cDate;
        this.createdBy = cBy;
        this.lastUpdate = cLastUpdate;
        this.lastUpdateBy = cLUpdateBy;
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

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
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

    public Timestamp getCreateDate() {
        return createDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public String getLastUpdateBy() {
        return lastUpdateBy;
    }
}
