package Model;

/**
 * Class for the FirstLevelDivision Object.
 */
public class FirstLevelDivision {

    private Integer divisionID;
    private Integer countryID;
    private String division;

    /**
     * Constructor for the FirstLevelDivision Object.
     * @param fDivisionID
     * @param fCountryID
     * @param fDivision
     */
    public FirstLevelDivision(int fDivisionID, int fCountryID, String fDivision){
        this.divisionID = fDivisionID;
        this.countryID = fCountryID;
        this.division = fDivision;
    }

    /**
     * Sets the Division ID.
     * @param divisionID
     */
    public void setDivisionID(Integer divisionID) {
        this.divisionID = divisionID;
    }

    /**
     * Sets the Country ID.
     * @param countryID
     */
    public void setCountryID(Integer countryID) {
        this.countryID = countryID;
    }

    /**
     * Sets the Division Name.
     * @param division
     */
    public void setDivision(String division) {
        this.division = division;
    }

    /**
     * Returns the Division ID.
     * @return
     */
    public Integer getDivisionID() {
        return divisionID;
    }

    /**
     * Returns the Country ID.
     * @return
     */
    public Integer getCountryID() {
        return countryID;
    }

    /**
     * Returns the Division Name.
     * @return
     */
    public String getDivision() {
        return division;
    }
}
