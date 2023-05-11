package Model;

import java.sql.Date;

public class Appointment {

    private Integer ID;
    private Integer userID;
    private Integer contactID;
    private String title;
    private String description;
    private String location;
    private String type;
    private Date start;
    private Date end;

    public Appointment(Integer aID, Integer aUserID, Integer aContactID, String aTitle, String aDescription,
                       String aLocation, String aType, Date aStart, Date aEnd){
        this.ID = aID;
        this.userID = aUserID;
        this.contactID = aContactID;
        this.title = aTitle;
        this.description = aDescription;
        this.location = aLocation;
        this.type = aType;
        this.start = aStart;
        this.end = aEnd;
    }

    //Setter methods.

    public void setID(Integer ID) {
        this.ID = ID;
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

    public void setStart(Date start) {
        this.start = start;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    //Getter methods

    public Integer getID() {
        return ID;
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

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }
}
