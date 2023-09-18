public class Contact implements ContactInterface, Cloneable{
    
    private PersonalInfo person;   // Personal information
    private Address address;       // Current address
    private String phone;          // Phone number
    private String email;          // Email  
    
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
    
    //interface method
    public boolean hasValue(String attribute, String value) throws IllegalArgumentException {
        boolean result = false;
        if(attribute.equals("phone")){
            if(this.phone.toLowerCase().equals(value)) result = true;
        }
        if(attribute.equals("email")){
            if(this.email.toLowerCase().equals(value)) result = true;
        }
        //first last status
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
        //streetAddress, city, state, zipCode
        return result;  
    }
   
    //interface method
    public void setValue(String attribute, String value) throws IllegalArgumentException {
    }

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

    public String getFirst(){
        return this.person.getFirst();
    }

    public String getLast(){
        return this.person.getLast();
    }


    public boolean equals(Contact other){
        boolean result = false;
        if(this.person.equals(other.person) && this.address.equals(other.address) &&
           this.phone.equals(other.phone) && this.email.equals(other.email)){
            result = true;
        }
        return result;
    }

    //Said this should be an inner class, should personal info also be an inner class
    public class Address{
        
        private String streetAddress;
        private String city;
        private String state;
        private int zipCode; //could be an int or a string

        public Address(String streetAddress, String city, String state, int zipCode){
            this.streetAddress = streetAddress;
            this.city = city;
            this.state = state;
            this.zipCode = zipCode;
        }

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
