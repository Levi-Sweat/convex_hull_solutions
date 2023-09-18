/**
 * Builds the first, last, and status of a person
 * 
 * @authors Levi Sweat & Alex Charlot
 * @version 09/18/2023
 */
public class PersonalInfo {
    /**
     * first name of the person
     */
    private String first;
    /**
     * last name of the person
     */
    private String last;
    /**
     * marital status of the person
     */
    private Status status;

    /**
     * Constructor for objects of class PersonalInfo
     * 
     * @param last last name of the person
     * @param first first name of the person
     * @param status marital status of the person
     */
    public PersonalInfo(String last, String first, Status status){
        this.first = first;
        this.last = last;
        this.status = status;
    }

    /**
     * Returns the first name of the person
     * 
     * @return first name of the person
     */
    public String getFirst(){
        return this.first;
    }

    /**
     * Returns the last name of the person
     * 
     * @return last name of the person
     */
    public String getLast(){
        return this.last;
    }

    /**
     * Returns the marital status of the person
     * 
     * @return marital status of the person
     */
    public String getStatus(){
        return this.status.getStatus();
    }

    /**
     * Checks if two personal infos are equal. Does not check the status as personalContacts always
     * have a status of NA.
     * 
     * @param info
     * @return
     */
    public boolean equals(PersonalInfo info){
        boolean result = false;
        if(this.first.equals(info.first) && this.last.equals(info.last)){
            result = true;
        }
        return result;
    }

}