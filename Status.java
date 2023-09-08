
public enum Status {
    
    MARRIED("Married"),
    SINGLE("Single"),
    DIVORCED("Divorced"),
    WIDOWED("Widowed"),
    NA("NA");


    private String marital_status;

    private Status(String marital_status){
        this.marital_status = marital_status;
    }

    public String getStatus(){
        return this.marital_status;
    }
}
