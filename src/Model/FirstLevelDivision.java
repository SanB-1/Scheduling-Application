package Model;

public class FirstLevelDivision {

    private Integer divisionID;
    private Integer countryID;
    private String division;

    public FirstLevelDivision(int fDivisionID, int fCountryID, String fDivision){
        this.divisionID = fDivisionID;
        this.countryID = fCountryID;
        this.division = fDivision;
    }

    //Setter Methods.

    public void setDivisionID(Integer divisionID) {
        this.divisionID = divisionID;
    }

    public void setCountryID(Integer countryID) {
        this.countryID = countryID;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    //Getter Methods.

    public Integer getDivisionID() {
        return divisionID;
    }

    public Integer getCountryID() {
        return countryID;
    }

    public String getDivision() {
        return division;
    }
}
