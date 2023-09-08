public class PersonalContact extends Contact{

    private Label label;

    // first, last, email, phone, street_address, city, state, zip_code, label
    public PersonalContact(String first, String last, String email, String phone, String street_address, 
                           String city, String state, int zip_code, String label){

        super(first, last, Status.NA, street_address, city, state, zip_code, phone, email);

        this.label = new Label(label);


    }

    public class Label{
        private String label;

        public Label(String label){
            this.label = label.toUpperCase();
        }

        public String toString(){
            return this.label;
        }
    }

    public String toString(){
        String string = "Category: " + label + "\n";
        string += super.toString();


        return string;
    }
    
}
