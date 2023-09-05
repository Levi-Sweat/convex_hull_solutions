public class Contact implements ContactInterface, Cloneable{
    
    private PersonalInfo person;   // Personal information
    private Address address;       // Current address
    private String phone;          // Phone number
    private String email;          // Email  
    
    //interface method
    public boolean exists(String attribute) {
        return true;
    }
    
    //interface method
    public boolean hasValue(String attribute, String value) throws IllegalArgumentException {
        return true;
    }
   
    //interface method
    public void setValue(String attribute, String value) throws IllegalArgumentException {
    }

    //Said this should be an inner class, should personal info also be an inner class
    public class Address{
        private String street_address;
        private String city;
        private String state;
        private int zip_code; //could be an int or a string
    }
}
