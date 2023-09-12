public class Contact implements ContactInterface, Cloneable{
    
    private PersonalInfo person;   // Personal information
    private Address address;       // Current address
    private String phone;          // Phone number
    private String email;          // Email  
    
    public Contact(String last, String first, Status status, String street_address, String city,
                   String state, int zip_code, String phone, String email){

        this.person = new PersonalInfo(last, first, status);
        this.address = new Address(street_address, city, state, zip_code);
        this.phone = phone;
        this.email = email;

    }
    
    //interface method
    public boolean exists(String attribute) {
        return true;
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
        if(attribute.equals("street_address")){
            if(this.address.street_address.toLowerCase().equals(value)) result = true;
        }
        if(attribute.equals("city")){
            if(this.address.city.toLowerCase().equals(value)) result = true;
        }
        if(attribute.equals("state")){
            if(this.address.state.toLowerCase().equals(value)) result = true;
        }
        if(attribute.equals("zip_code")){
            if(this.address.zip_code == Integer.parseInt(value)) result = true;
        }
        //street_address, city, state, zip_code
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

        string += "Phone: " + this.phone + "\n        " + address.street_address + "\n        " +
                  address.city + ", " + address.state + " " + address.zip_code;
        string += "\n--------------------------------------------------------------\n";
        return string;
    }
    //Said this should be an inner class, should personal info also be an inner class
    public class Address{
        
        private String street_address;
        private String city;
        private String state;
        private int zip_code; //could be an int or a string

        public Address(String street_address, String city, String state, int zip_code){
            this.street_address = street_address;
            this.city = city;
            this.state = state;
            this.zip_code = zip_code;
        }
    }
}
