/**
 * Builds a personal contact object that contains personal information for a personal contact
 * 
 * @authors Levi Sweat & Alex Charlot
 * @version 09/18/2023
 */
public class PersonalContact extends Contact{

    /**
     * Label for the contact (friend, mom, etc.)
     */
    private Label label;

    /**
     * constructor for objects of class PersonalContact
     * 
     * @param first first name of the contact
     * @param last last name of the contact
     * @param email email address of the contact
     * @param phone phone number of the contact
     * @param streetAddress street address of the contact
     * @param city city of the contact 
     * @param state state of the contact
     * @param zipCode zip code of the contact
     * @param label label for the contact
     */
    public PersonalContact(String first, String last, String email, String phone, 
                           String streetAddress, String city, String state, 
                           int zipCode, String label){

        super(first, last, Status.NA, streetAddress, city, state, zipCode, phone, email);

        this.label = new Label(label);


    }

    /**
     * Inner class that builds the label for the contact
     */
    public class Label{
        private String label;

        /**
         * Constructor for objects of class Label
         * 
         * @param label label for the contact
         */
        public Label(String label){
            this.label = label.toUpperCase();
        }

        /**
         * Returns the label for the contact as a string
         * 
         * @return label for the contact
         */
        public String toString(){
            return this.label;
        }
    }

    /**
     * Gets the first name of the contact
     * 
     * @return string, the first name of the contact
     */
    public String getFirst(){
        return super.getFirst();
    }

    /**
     * Gets the last name of the contact
     * 
     * @return string, the last name of the contact
     */
    public String getLast(){
        return super.getLast();
    }
    
    /**
     * Checks to see if a record has an attribute. Is not case sensitive.
     * 
     * @param attribute possible attribute within a record.
     * @param value desired value of the attribute.
     * @return true if the value is contained in the object, false otherwise.
     */
    public boolean hasValue(String attribute, String value){
        boolean result = super.hasValue(attribute, value);
        //if the value hasn't already been found, check this field
        if(!result){
            if(attribute.equals("label")){
                if(this.label.toString().toLowerCase().equals(value)){
                    result = true;
                }
            }
        }
        return result;
    }

    /**
     * Builds the string representation of the personal contact
     * 
     * @return the string representation of the personal contact
     */
    public String toString(){
        String string = "Category: " + this.label + "\n";
        string += super.toString();


        return string;
    }
    
}
