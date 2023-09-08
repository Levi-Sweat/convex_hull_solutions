public class WorkContact extends Contact{

    private String title;       // Job title
    private String company;     // Company contact works for
    private String department;  // Contact’s department
                                                  //status or string??
    public WorkContact(String last, String first, String status_string, String street_address,
                       String city, String state, int zip_code, String phone, String email, 
                       String title, String company, String department){
        
        super(last, first, Status.valueOf(status_string.toUpperCase()), street_address, city, state, zip_code, phone, email);

        this.title = title;
        this.company = company;
        this.department = department;
        
    }

    public String toString(){
        String string = "Job title: " + this.title + "\nCompany: " + this.company + 
                        "\nDepartment: " + this.department + "\n";
        
        string += super.toString();

        return string;
    }

}

// Job Title: Jedi
// Company: Rebel Alliance
// Department: Force Users