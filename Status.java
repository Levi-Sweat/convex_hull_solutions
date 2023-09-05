
public enum Status {
    
    MARRIED("married"),
    SINGLE("single"),
    DIVORCED("divorced"),
    WIDOWED("widowed"),
    NA("na");


    private String marital_status;

    private Status(String marital_status){
        this.marital_status = marital_status;
    }

    private String getStatus(){
        return this.marital_status;
    }
}
