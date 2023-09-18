public class WorkContact extends Contact{

    private String title;       // Job title
    private String company;     // Company contact works for
    private String department;  // Contact’s department
                                                  //status or string??
    public WorkContact(String last, String first, String status_string, String street_address,
                       String city, String state, int zipCode, String phone, String email, 
                       String title, String company, String department){
        
        super(last, first, Status.valueOf(status_string.toUpperCase()), street_address, city, state, zipCode, phone, email);

        this.title = title;
        this.company = company;
        this.department = department;
        
    }
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

    public String getFirst(){
        return super.getFirst();
    }

    public String getLast(){
        return super.getLast();
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