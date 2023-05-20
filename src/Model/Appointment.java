package Model;

import java.sql.Timestamp;

/**
 * Class for the Appointment Object.
 */
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

    /**
     * Constructor for the Appointment Object.
     * @param aID
     * @param aTitle
     * @param aDescription
     * @param aLocation
     * @param aType
     * @param aStart
     * @param aEnd
     * @param aCreateDate
     * @param acreatedBy
     * @param alastUpdate
     * @param aLastUpdateBy
     * @param aCustomerID
     * @param aUserID
     * @param aContactID
     */
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

    /**
     * Sets ID.
     * @param ID
     */
    public void setID(Integer ID) {
        this.ID = ID;
    }

    /**
     * Sets Customer ID.
     * @param customerID
     */
    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

    /**
     * Sets User ID.
     * @param userID
     */
    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    /**
     * Sets Contact ID.
     * @param contactID
     */
    public void setContactID(Integer contactID) {
        this.contactID = contactID;
    }

    /**
     * Sets Title.
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Sets Description.
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets Location.
     * @param location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Sets Type.
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Sets Starting Date/Time.
     * @param start
     */
    public void setStart(Timestamp start) {
        this.start = start;
    }

    /**
     * Sets Ending Date/Time.
     * @param end
     */
    public void setEnd(Timestamp end) {
        this.end = end;
    }

    /**
     * Sets Creation Date/Time.
     * @param createDate
     */
    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    /**
     * Sets author.
     * @param createdBy
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Sets the author of the last update.
     * @param lastUpdateBy
     */
    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    /**
     * Sets last Update Date/Time.
     * @param lastUpdate
     */
    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * Returns ID.
     * @return
     */
    public Integer getID() {
        return ID;
    }

    /**
     * Returns Customer ID.
     * @return
     */
    public Integer getCustomerID() {
        return customerID;
    }

    /**
     * Returns User ID.
     * @return
     */
    public Integer getUserID() {
        return userID;
    }

    /**
     * Returns Contact ID.
     * @return
     */
    public Integer getContactID() {
        return contactID;
    }

    /**
     * Returns Title.
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns Description.
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns Location.
     * @return
     */
    public String getLocation() {
        return location;
    }

    /**
     * Returns Type.
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * Returns Starting Date/Time.
     * @return
     */
    public Timestamp getStart() {
        return start;
    }

    /**
     * Returns Ending Date/Time.
     * @return
     */
    public Timestamp getEnd() {
        return end;
    }

    /**
     * Returns Creation Date/Time.
     * @return
     */
    public Timestamp getCreateDate() {
        return createDate;
    }

    /**
     * Returns author.
     * @return
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Returns last update Date/Time.
     * @return
     */
    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    /**
     * Returns the author of the last update.
     * @return
     */
    public String getLastUpdateBy() {
        return lastUpdateBy;
    }
}
