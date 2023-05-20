package Model;

/**
 * Class for the Country Object.
 */
public class Country {

    private Integer ID;
    private String name;

    /**
     * Constructor for the Country Object.
     * @param cID
     * @param cName
     */
    public Country(Integer cID, String cName){
        this.ID = cID;
        this.name = cName;
    }

    /**
     * Sets ID.
     * @param cID
     */
    public void setID(Integer cID){
        this.ID = cID;
    }

    /**
     * Sets Name.
     * @param cName
     */
    public void setName(String cName){
        this.name = cName;
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
}
