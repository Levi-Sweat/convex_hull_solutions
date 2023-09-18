/**
 * Builds a contact object that contains personal information
 * 
 * @authors Levi Sweat & Alex Charlot
 * @version 09/18/2023
 */
public class Contact implements ContactInterface, Cloneable{
    
    /**
     * Personal information
     */
    private PersonalInfo person;
    
    /**
     * current Address
     */
    private Address address;
    /**
     * Phone number
     */
    private String phone;
    
    /**
     * Email address
     */
    private String email;
    
    /**
     * Constructor for objects of class Contact
     * 
     * @param last last name
     * @param first first name
     * @param status marital status
     * @param streetAddress street address
     * @param city city
     * @param state state
     * @param zipCode zip code
     * @param phone phone number
     * @param email email address
     */
    public Contact(String last, String first, Status status, String streetAddress, String city,
                   String state, int zipCode, String phone, String email){

        this.person = new PersonalInfo(last, first, status);
        this.address = new Address(streetAddress, city, state, zipCode);
        this.phone = phone;
        this.email = email;

    }
    
    /**
     * Check to see if a record has an attribute. Should not be case sensitive.
     * 
     * @param attribute possible attribute within a record.
     * @return true if the value is contained in the object, false otherwise.
     */
    public boolean exists(String attribute) {
        boolean result = false;
        attribute = attribute.toLowerCase();
        if(attribute.equals("first") || attribute.equals("last") || attribute.equals("status") ||
           attribute.equals("streetAddress") || attribute.equals("city") || attribute.equals("state") ||
           attribute.equals("zipCode") || attribute.equals("phone") || attribute.equals("email")){
            result = true;
           }
        return result;
    }
    
    /**
     * Check to see if a record has an attribute containing a specific value. Should not be case sensitive.
     * 
     * @param attribute possible attribute within a record.
     * @param value desired value of the attribute.
     * @return true if the value is contained in the object, false otherwise.
     * @throws IllegalArgumentException if the attribute is invalid.
     */
    public boolean hasValue(String attribute, String value) throws IllegalArgumentException {
        boolean result = false;
        if(attribute.equals("phone")){
            if(this.phone.toLowerCase().equals(value)) result = true;
        }
        if(attribute.equals("email")){
            if(this.email.toLowerCase().equals(value)) result = true;
        }
        if(attribute.equals("first")){
            if(this.person.getFirst().toLowerCase().equals(value)) result = true;
        }
        if(attribute.equals("last")){
            if(this.person.getLast().toLowerCase().equals(value)) result = true;
        }
        if(attribute.equals("status")){
            if(this.person.getStatus().toLowerCase().equals(value)) result = true;
        }
        if(attribute.equals("streetAddress")){
            if(this.address.streetAddress.toLowerCase().equals(value)) result = true;
        }
        if(attribute.equals("city")){
            if(this.address.city.toLowerCase().equals(value)) result = true;
        }
        if(attribute.equals("state")){
            if(this.address.state.toLowerCase().equals(value)) result = true;
        }
        if(attribute.equals("zipCode")){
            if(this.address.zipCode == Integer.parseInt(value)) result = true;
        }
        return result;  
    }
   
    /**
     * Change the value of a specific attribute.
     * 
     * @param attribute possible attribute within a record.
     * @param value new value of the attribute.
     * @throws IllegalArgumentException if the attribute is invalid.
     */
    public void setValue(String attribute, String value) throws IllegalArgumentException {
            //TODO
            //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    }

    /**
     * Returns a copy of the object
     * 
     * @return string, a copy of the object
     */
    public String toString(){
        String string = "        " + person.getFirst() + ", " + person.getLast() + ": (" + 
                                     person.getStatus() + "):";
        while(string.length() < 40){
            string += " ";
        }

        string += "Phone: " + this.phone + "\n        " + address.streetAddress + "\n        " +
                  address.city + ", " + address.state + " " + address.zipCode;
        string += "\n--------------------------------------------------------------\n";
        return string;
    }

    /**
     * Gets the first name of the contact
     * 
     * @return string, the first name of the contact
     */
    public String getFirst(){
        return this.person.getFirst();
    }

    /**
     * Gets the last name of the contact
     * 
     * @return string, the last name of the contact
     */
    public String getLast(){
        return this.person.getLast();
    }

    /**
     * Compares two contact objects and returns true if they are equal
     * 
     * @param other the contact to compare to
     * @return true if the contacts are equal, false otherwise
     */
    public boolean equals(Contact other){
        boolean result = false;
        if(this.person.equals(other.person) && this.address.equals(other.address) &&
           this.phone.equals(other.phone) && this.email.equals(other.email)){
            result = true;
        }
        return result;
    }

    /**
     * Inner class that creates the address of a contact
     */
    public class Address{
        
        private String streetAddress;
        private String city;
        private String state;
        private int zipCode; //could be an int or a string

        /**
         * Constructor for objects of class Address
         * 
         * @param streetAddress street address 
         * @param city city
         * @param state state
         * @param zipCode zip code
         */
        public Address(String streetAddress, String city, String state, int zipCode){
            this.streetAddress = streetAddress;
            this.city = city;
            this.state = state;
            this.zipCode = zipCode;
        }

        /**
         * Compares two address objects and returns true if they are equal
         * 
         * @param other the address to compare to
         * @return true if the addresses are equal, false otherwise
         */
        public boolean equals(Address other){
            boolean result = false;
            if(this.streetAddress.equals(other.streetAddress) && this.city.equals(other.city) &&
               this.state.equals(other.state) && this.zipCode == other.zipCode){
                result = true;
            }
            return result;
        }
    }
}
