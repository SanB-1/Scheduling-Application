package Model;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class Appointment {

    private Integer ID;
    private String title;
    private String description;
    private String location;
    private String type;
    private Timestamp start;
    private Timestamp end;
    private Timestamp createDate;
    private String createdBy;
    private Timestamp lastUpdate;
    private String lastUpdateBy;
    private Integer customerID;
    private Integer userID;
    private Integer contactID;

    public Appointment(Integer aID, String aTitle, String aDescription, String aLocation, String aType,
                       Timestamp aStart, Timestamp aEnd, Timestamp aCreateDate, String acreatedBy,
                       Timestamp alastUpdate, String aLastUpdateBy, Integer aCustomerID, Integer aUserID,
                       Integer aContactID){
        this.ID = aID;
        this.title = aTitle;
        this.description = aDescription;
        this.location = aLocation;
        this.type = aType;
        this.start = aStart;
        this.end = aEnd;
        this.createDate = aCreateDate;
        this.createdBy = acreatedBy;
        this.lastUpdate = alastUpdate;
        this.lastUpdateBy = aLastUpdateBy;
        this.customerID = aCustomerID;
        this.userID = aUserID;
        this.contactID = aContactID;
    }

    //Setter methods.

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public void setContactID(Integer contactID) {
        this.contactID = contactID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setStart(Timestamp start) {
        this.start = start;
    }

    public void setEnd(Timestamp end) {
        this.end = end;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    //Getter methods

    public Integer getID() {
        return ID;
    }

    public Integer getCustomerID() {
        return customerID;
    }

    public Integer getUserID() {
        return userID;
    }

    public Integer getContactID() {
        return contactID;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public String getType() {
        return type;
    }

    public Timestamp getStart() {
        return start;
    }

    public Timestamp getEnd() {
        return end;
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
