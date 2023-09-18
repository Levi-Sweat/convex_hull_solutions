/**
 * Builds a work contact object that contains personal information for work contacts
 * 
 * @authors Levi Sweat & Alex Charlot
 * @version 09/18/2023
 */
public class WorkContact extends Contact{

    /**
     * Job title
     */
    private String title;
    /**
     * Company contact works for
     */
    private String company;
    /**
     * Contact’s department
     */
    private String department;

    /**
     * Constructor for objects of class WorkContact
     * 
     * @param last the last name of the contact
     * @param first the first name of the contact
     * @param status_string the marital status of the contact
     * @param street_address the street address of the contact
     * @param city the city of the contact
     * @param state the state of the contact
     * @param zipCode the zip code of the contact
     * @param phone the phone number of the contact
     * @param email the email address of the contact
     * @param title the job title of the contact
     * @param company the company the contact works for
     * @param department the department the contact works in
     */
    public WorkContact(String last, String first, String status_string, String street_address,
                       String city, String state, int zipCode, String phone, String email, 
                       String title, String company, String department){
        
        super(last, first, Status.valueOf(status_string.toUpperCase()), 
              street_address, city, state, zipCode, phone, email);

        this.title = title;
        this.company = company;
        this.department = department;
        
    }

    /**
     * Check to see if a record has an attribute. Is not case sensitive.
     * 
     * @param attribute possible attribute within a record.
     * @param value desired value of the attribute.
     * @return true if the value is contained in the object, false otherwise.
     */
    public boolean hasValue(String attribute, String value){
        boolean result = super.hasValue(attribute, value);
        //if the value hasn't already been found, check these fields
        if(!result){
            if(attribute.equals("title")){
                if(this.title.toLowerCase().equals(value)){
                    result = true;
                }
            }
            if(attribute.equals("company")){
                if(company.toLowerCase().equals(value)){
                    result = true;
                }
            }
            if(attribute.equals("department")){
                if(department.toLowerCase().equals(value)){
                    result = true;
                }
            }
        }
        return result;
    }

    /**
     * Gets the value of a the contacts first name
     * 
     * @return the first name of the contact
     */
    public String getFirst(){
        return super.getFirst();
    }

    /**
     * Gets the value of a the contacts last name
     * 
     * @return the last name of the contact
     */
    public String getLast(){
        return super.getLast();
    }
    
    /**
     * Builds the string representation of the work contact
     * 
     * @return the string representation of the work contact
     */
    public String toString(){
        String string = "Job title: " + this.title + "\nCompany: " + this.company + 
                        "\nDepartment: " + this.department + "\n";
        
        string += super.toString();

        return string;
    }


}