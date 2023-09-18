/**
 * Enum that represents the marital status of a person.
 * 
 * @authors Levi Sweat & Alex Charlot
 * @version 09/18/2023
 */
public enum Status {
    
    MARRIED("Married"),
    SINGLE("Single"),
    DIVORCED("Divorced"),
    WIDOWED("Widowed"),
    NA("NA");

    /**
     * Marital status of a person
     */
    private String marital_status;

    /**
     * Constructor for objects of class Status
     * 
     * @param marital_status marital status of a person
     */
    private Status(String marital_status){
        this.marital_status = marital_status;
    }

    /**
     * Returns the marital status of a person
     * 
     * @return marital status of a person
     */
    public String getStatus(){
        return this.marital_status;
    }
}
