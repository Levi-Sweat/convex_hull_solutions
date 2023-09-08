public class PersonalInfo {

    private String first;  // First name
    private String last;   // Last name
    private Status status; // Marital Status
    //...

    public PersonalInfo(String last, String first, Status status){
        this.first = first;
        this.last = last;
        this.status = status;
    }

    public String getFirst(){
        return this.first;
    }

    public String getLast(){
        return this.last;
    }

    public String getStatus(){
        return this.status.getStatus();
    }

}