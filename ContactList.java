import java.io.*;
import java.util.*;

//example javadoc comment
/**
 * Constructs a set with the elements in list
 * 
 * @param list collection of elements to be added to set
 * @returns result
 */

public class ContactList {

    private Table<Contact> table; // table that includes linked list of contacts

    // private static ContactList cl1;
    // private static ContactList cl2;

    /**
     * 
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter filename for contact list 1> ");
        String filename1 = sc.nextLine(); // name of first file

        System.out.print("Enter filename for contact list 2> ");
        String filename2 = sc.nextLine(); // name of second file

        ContactList cl1 = new ContactList(); // used to create first database
        ContactList cl2 = new ContactList(); // udsed to create second database

        // only keep going if database is successfullly created
        if (cl1.createDatabase(filename1) && cl2.createDatabase(filename2)) {
            System.out.println(cl1.table.toString());
            System.out.println(cl2.table.toString());

        }

        // display menu syntax (not in the right place yet)
        System.out.println("Welcome to database display\n");

        // starts the display menu loop
        displayMenu(cl1, cl2, sc);

        // close scanner
        sc.close();

    }

    public boolean createDatabase(String filename) {
        boolean result = true;
        try {
            // scanner of the file
            Scanner filesc = new Scanner(new File(filename));
            String contact_type = filesc.nextLine();

            if (contact_type.equals("P")) {
                System.out.println("personal contact");
                table = new Table<Contact>("Personal Contacts");

                // first contact is the head of the list
                Table<Contact>.Node<Contact> current = createPersonalContact(filesc.nextLine());

                table.setHead(current);

                Table<Contact>.Node<Contact> next;

                while (filesc.hasNextLine()) { // iterate through remaining contacts
                    next = createPersonalContact(filesc.nextLine());
                    current.setNext(next);
                    current = next;
                }

                // set tail
                table.setTail(current);

            } else if (contact_type.equals("W")) {
                System.out.println("work contact");

                table = new Table<Contact>("Work Contacts");

                // first contact is the head of the list
                Table<Contact>.Node<Contact> current = createWorkContact(filesc.nextLine());

                table.setHead(current);

                Table<Contact>.Node<Contact> next;

                while (filesc.hasNextLine()) { // iterate through remaining contacts
                    next = createWorkContact(filesc.nextLine());
                    current.setNext(next);
                    current = next;
                }

                // set tail
                table.setTail(current);
            } else {
                System.out.println("Problem, not personal or work contact");
            }

            // close the scanner
            filesc.close();

        } catch (FileNotFoundException e) {
            System.out.println("Incorrect file name.");
            result = false;
        }
        return result;
    }

    public Table<Contact>.Node<Contact> createPersonalContact(String info) {
        System.out.println("INFO_> " + info);
        String[] infoArray = info.split(",");

        // remove the whitespace from each element in the array,
        // and trim whitespace from address and city
        for (int i = 0; i < infoArray.length; i++) {
            if (i == 4 || i == 5) {
                infoArray[i] = infoArray[i].trim();
            } else {
                infoArray[i] = infoArray[i].replaceAll("\\s", "");
            }
        }
        PersonalContact pc = new PersonalContact(infoArray[0], infoArray[1], infoArray[2],
                infoArray[3], infoArray[4], infoArray[5],
                infoArray[6], Integer.parseInt(infoArray[7]), infoArray[8]);

        Table<Contact>.Node<Contact> node = table.new Node<Contact>(pc);

        return node;
    }

    public Table<Contact>.Node<Contact> createWorkContact(String info) {
        System.out.println("INFO_> " + info);
        String[] infoArray = info.split(",");

        // remove the whitespace from each element in the array,
        // and trim whitespace from address and city, title, company, and department
        for (int i = 0; i < infoArray.length; i++) {
            if (i == 5 || i == 6 || i == 9 || i == 10 || i == 11) {
                infoArray[i] = infoArray[i].trim();
            } else {
                infoArray[i] = infoArray[i].replaceAll("\\s", "");
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
                Integer.parseInt(infoArray[8]), infoArray[4], infoArray[3], infoArray[9], 
                    infoArray[10], infoArray[11]);

        Table<Contact>.Node<Contact> node = table.new Node<Contact>(wc);

        return node;
    }

    public static void displayMenu(ContactList cl1, ContactList cl2, Scanner sc) {
        // initialize input
        int input = 1;
        // while loop to keep displaying menu until user quits
        while (input != 0) {
            // print menu
            System.out.println("Please make a choice:");
            String choice = "\t0) Quit\n\t1) Intersect\n\t2) Difference\n\t3) Union\n\t4) Select" +
                    "\n\t5) Remove\n\t6) Print both tables";
            System.out.println(choice);
            System.out.print(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ");
            input = sc.nextInt();
            // switch statement to determine what to do based on input
            switch (input) {
                case 0:
                    System.out.println("Goodbye!");
                    break;
                case 1:
                    intersect(cl1, cl2);
                    break;
                case 2:
                    System.out.println("Difference"); // TODO
                    break;
                case 3:
                    union(cl1, cl2);
                    break;
                case 4:
                    select(cl1, cl2);
                    break;
                case 5:
                    remove(cl1, cl2);
                    break;
                case 6:
                    printBoth(cl1, cl2);
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            }
        }
    }

    public static void union(ContactList cl1, ContactList cl2){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Contact List > ");
        String list = sc.nextLine();
        if(list.equals("1")){
            String str = "===========================Contact List 1, Contact List 2" +
                    "============================";
            System.out.println(str);
            System.out.println(cl1.table.union(cl2.table));
            System.out.println(str);
        }else if(list.equals("2")){
            String str = "===========================Contact List 2, Contact List 1" +
                    "============================";
            System.out.println(str);
            System.out.println(cl2.table.union(cl1.table));
            System.out.println(str);
        }else{
            System.out.println("Invalid contact list number");
        }
    }


    public static void intersect(ContactList cl1, ContactList cl2) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Group > ");
        String group = sc.nextLine();
        System.out.print("Enter attribute > ");
        String attribute = sc.nextLine().toLowerCase();
        System.out.print("Enter value > ");
        String value = sc.nextLine().toLowerCase();
        if (group.equals("1")) {
            String str = "===========================Contact List 1, Contact List 2" +
                    "============================";
            System.out.println(str);
            System.out.println(cl1.table.intersect(attribute, value, cl2.table));
            System.out.println(str);
        } else if (group.equals("2")) {
            String str = "===========================Contact List 2, Contact List 1" +
                    "============================";
            System.out.println(str);
            System.out.println(cl2.table.intersect(attribute, value, cl1.table));
            System.out.println(str);
        } else {
            System.out.println("Invalid group");
        }
    }

    public static void remove(ContactList cl1, ContactList cl2) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter attribute > ");
        String attribute = sc.nextLine().toLowerCase();
        System.out.print("Enter value > ");
        String value = sc.nextLine().toLowerCase();
        cl1.table.remove(attribute, value);
        cl2.table.remove(attribute, value);
    }

    public static void select(ContactList cl1, ContactList cl2) {
        Scanner selectScan = new Scanner(System.in);

        System.out.print("Enter table (1/2) > ");
        String tableNum = selectScan.nextLine().toLowerCase();
        System.out.print("Enter attribute > ");
        String attribute = selectScan.nextLine().toLowerCase();
        System.out.print("Enter value > ");
        String value = selectScan.nextLine().toLowerCase();
        
        if(tableNum.equals("1")){
            String header1 = "===========================Contact List 1" +
                "============================";
            System.out.println(header1);
            System.out.println(cl1.table.select(attribute, value).toString());
            System.out.println(header1);
        }else if(tableNum.equals("2")){
            String header2 = "===========================Contact List 2" +
                "============================";
            System.out.println(header2);
            System.out.println(cl2.table.select(attribute, value));
            System.out.println(header2);
        }else{
            System.out.println("Invalid table number");
        }
    }

    // prints out both tables with a header
    public static void printBoth(ContactList table1, ContactList table2) {
        System.out.println("\n===========================Contact List 1============================");
        System.out.println(table1.table.toString());
        System.out.println("===========================Contact List 1============================\n");
        System.out.println("===========================Contact List 2============================");
        System.out.println(table2.table.toString());
        System.out.println("===========================Contact List 2============================\n");

    }

    

}