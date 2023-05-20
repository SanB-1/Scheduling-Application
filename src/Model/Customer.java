package Model;

import java.sql.*;

/**
 * Class for the Customer Object.
 */
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

    /**
     * Constructor method for the Customer Object.
     * @param cID
     * @param cName
     * @param cAddress
     * @param cZip
     * @param cPhone
     * @param cDate
     * @param cBy
     * @param cLastUpdate
     * @param cLUpdateBy
     * @param cDivID
     */
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


    /**
     * Sets ID.
     * @param ID
     */
    public void setID(Integer ID) {
        this.ID = ID;
    }

    /**
     * Sets First Level Division ID.
     * @param divID
     */
    public void setDivID(Integer divID) {
        this.divID = divID;
    }

    /**
     * Sets Name.
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets Address.
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Sets Zip Code.
     * @param zip
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     * Sets Phone Number.
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Sets Creation Date/Time.
     * @param createDate
     */
    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    /**
     * Sets Author.
     * @param createdBy
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Sets last update Date/Time.
     * @param lastUpdate
     */
    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * Sets the author of the last update.
     * @param lastUpdateBy
     */
    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    /**
     * Returns ID.
     * @return
     */
    public Integer getID() {
        return ID;
    }

    /**
     * Returns First Level Division ID.
     * @return
     */
    public Integer getDivID() {
        return divID;
    }

    /**
     * Returns Name.
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Returns Address.
     * @return
     */
    public String getAddress(){
        return address;
    }

    /**
     * Returns ZIP Code.
     * @return
     */
    public String getZip() {
        return zip;
    }

    /**
     * Returns Phone Number.
     * @return
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Returns Creation Date/Time.
     * @return
     */
    public Timestamp getCreateDate() {
        return createDate;
    }

    /**
     * Returns Author.
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
