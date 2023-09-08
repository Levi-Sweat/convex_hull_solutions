import java.io.*;
import java.util.*;

//example javadoc comment
    /**
     * Constructs a set with the elements in list
     * 
     * @param list collection of elements to be added to set
     * @returns result 
     */



public class ContactList{

    private Table table; //table that includes linked list of contacts

    /**
     * 
     * @param args
     */
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter filename for contact list 1> ");
        String filename1 = sc.nextLine(); //name of first file
        
        System.out.println("Enter filename for contact list 2>");
        String filename2 = sc.nextLine(); //name of second file

        ContactList cl1 = new ContactList(); //used to create first database
        ContactList cl2 = new ContactList(); //udsed to create second database


        //only keep going if database is successfullly created
        if(cl1.createDatabase(filename1) && cl2.createDatabase(filename2)){ 
            System.out.println(cl1.table.toString());
            System.out.println(cl2.table.toString());
        
        }

    }

    public boolean createDatabase(String filename){
        boolean result = true;
        try{
            //scanner of the file
            Scanner filesc = new Scanner(new File(filename));
            String contact_type = filesc.nextLine();

            if(contact_type.equals("P")){
                System.out.println("personal contact");
                table = new Table("Personal Contacts");

                //first contact is the head of the list
                Table.Node current = createPersonalContact(filesc.nextLine());
                
                table.setHead(current);
                
                Table.Node next;

                while(filesc.hasNextLine()){ //iterate through remaining contacts
                    next = createPersonalContact(filesc.nextLine());
                    current.setNext(next);
                    current = next;
                }

                //set tail
                table.setTail(current);

            }
            else if(contact_type.equals("W")){
                System.out.println("work contact");

                table = new Table("Work Contacts");

                //first contact is the head of the list
                Table.Node current = createWorkContact(filesc.nextLine());
                
                table.setHead(current);
                
                Table.Node next;

                while(filesc.hasNextLine()){ //iterate through remaining contacts
                    next = createWorkContact(filesc.nextLine());
                    current.setNext(next);
                    current = next;
                }

                //set tail
                table.setTail(current);
            }
            else{
                System.out.println("Problem, not personal or work contact");
            }

            //close the scanner
            filesc.close();

        } catch(FileNotFoundException e){
            System.out.println("Incorrect file name.");
            result = false;
        }
        return result;
    }

    public Table.Node createPersonalContact(String info){
        System.out.println("INFO_> " + info);
        String[] infoArray = info.split(",");

        //remove the whitespace from each element in the array, 
        //and trim whitespace from address and city
        for(int i = 0; i < infoArray.length; i++){
            if(i == 4 || i == 5){
                infoArray[i] = infoArray[i].trim();
            }
            else{
                infoArray[i] = infoArray[i].replaceAll("\\s","");
            }
        }
        PersonalContact pc = new PersonalContact(infoArray[0], infoArray[1], infoArray[2], 
                                 infoArray[3], infoArray[4], infoArray[5],
                                 infoArray[6], Integer.parseInt(infoArray[7]), infoArray[8]);
        
        Table.Node node = table.new Node(pc);
        
        return node;
    }

    public Table.Node createWorkContact(String info){
        System.out.println("INFO_> " + info);
        String[] infoArray = info.split(",");

        //remove the whitespace from each element in the array, 
        //and trim whitespace from address and city, title, company, and department
        for(int i = 0; i < infoArray.length; i++){
            if(i == 5 || i == 6 || i == 9 || i == 10 || i == 11){
                infoArray[i] = infoArray[i].trim();
            }
            else{
                infoArray[i] = infoArray[i].replaceAll("\\s","");
            }
        }
// infoArray[0] last
// infoArray[1] first
// infoArray[2] status
// infoArray[3] email
// infoArray[4] phone
// infoArray[5] street address
// infoArray[6] city
// infoArray[7] state
// infoArray[8] zip
// infoArray[9] title
// infoArray[10] company
// infoArray[11] department


        WorkContact wc = new WorkContact(infoArray[0], infoArray[1], infoArray[2], 
                                 infoArray[5], infoArray[6], infoArray[7],
                                 Integer.parseInt(infoArray[8]), infoArray[4], infoArray[3], infoArray[9], infoArray[10], infoArray[11]);
        
        Table.Node node = table.new Node(wc);
        
        return node;
    }
}