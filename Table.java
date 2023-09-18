
public class Table<T extends Contact> {
    
    /**
     * First record in the table
     */
    private Node<T> head;
    
    /**
     * Last record in the table
     */
    private Node<T> tail;
    
    /**
     * Label for the table
     */
    private String title;

    /**
     * Constructor that initializes fields
     * 
     * @param title string to initializes title field
     */
    public Table(String title) {
        this.title = title;
        this.head = null;
        this.tail = null;
    }

    /**
     * Sets the head of the table
     * 
     * @return head, the head of the table
     */
    public void setHead(Node<T> head) {
        this.head = head;
    }

    /**
     * Sets the tail of the table
     * 
     * @param tail the tail to set
     */
    public void setTail(Node<T> tail) {
        this.tail = tail;
    }

    /**
     * Adds a new record to the end of the this table.
     * 
     * @param data the data to be added
     */
    void insert(T data) {
        Node<T> newNode = new Node<T>(data);
        if (head == null) { // if list is empty, set head and tail to new node
            head = newNode;
            tail = newNode;
        } else { // set tail's next to new node, then update tail to the new node
            tail.setNext(newNode);
            tail = newNode;
        }
    }

    /**
     * Creates a new table comprised of nodes having a value for a specific
     * attribute, created from both tables.
     * 
     * @param attribute the attribute to search for
     * @param value    the value to search for
     * @param table    the table to compare to
     * @return result, the table of intersections
     */
    Table<T> intersect(String attribute, String value, Table<T> table) {
        Table<T> result = new Table<T>("result"); // table to return

        // boolean that says if the sought value has been found already
        boolean found = false;

        Node<T> current = head;
        if (current.data.hasValue(attribute, value)) { // check the head
            result.insert(current.data);
            found = true;
        } else {
            while (current.hasNext()) { // check rest of list
                current = current.next;
                if (current.data.hasValue(attribute, value)) {
                    result.insert(current.data);
                    found = true;
                }
            }
        }

        if (!found) { // if the sought value hasn't been found in this table, check the other table
            current = table.head;
            if (current.data.hasValue(attribute, value)) { // check the head
                result.insert(current.data);
            } else {
                while (current.hasNext()) { // check rest of list
                    current = current.next;
                    if (current.data.hasValue(attribute, value)) {
                        result.insert(current.data);
                    }
                }
            }

        }
        return result; // return created table to be printed
    }

    /**
     * Removes a node from the table that has a specific value for a specific
     * attribute.
     * 
     * @param attribute the attribute to search for
     * @param value    the value to search for
     */
    void remove(String attribute, String value) {

        Node<T> current = head.next;
        Node<T> previous = head;
        boolean found = false;

        if (previous.data.hasValue(attribute, value)) {
            head = current;
            found = true;
        }
        while (previous.hasNext() && !found) {

            if (current.data.hasValue(attribute, value)) {
                previous.next = current.next;
                found = true;
            } else {
                previous = current;
                current = current.next;
            }
        }

    }

    /**
     * Creates a new table comprised of nodes having a value for a specific
     * attribute.
     * 
     * @param attribute the attribute to search for
     * @param value     the value to search for
     * @return result, the table of selected nodes
     */
    Table<T> select(String attribute, String value) {
        Table<T> result = new Table<T>("result"); // table to return
        boolean found = false;
        Node<T> current = head;
        if (current.data.hasValue(attribute, value)) { // check the head
            result.insert(current.data);
            found = true;
        }
        while (current.hasNext() && !found) { // check rest of list
            current = current.next;
            if (current.data.hasValue(attribute, value)) {
                result.insert(current.data);
                found = true;
            }
        }
        if(!found){
            System.out.println("No records found.");
        }
        return result; // return created table to be printed
    }

    /**
     * Creates a new table comprised of nodes that occur in either table(s). No
     * duplicates allowed.
     * 
     * @param table the table to compare to
     * @return result, the table of unions
     */
    Table<T> union(Table<T> table){
        Table<T> result = new Table<T>("result"); // table to return
        //determines if the element should be added to the result table
        boolean addElement = true;
        
        Node<T> current = head;
        Node<T> current2 = table.head;

        result.insert(current.data);
        while(current.hasNext()){
            current = current.next;
            result.insert(current.data);
        }

        while(current2 != null){
            addElement = true;
            while(current != null){
                if(!(current.data.getFirst().equals(current2.data.getFirst()) && 
                   current.data.getLast().equals(current2.data.getLast()))){
                    addElement = false;
                }
                current = current.next;
            }
            if(addElement){
                result.insert(current2.data);
            }
            current2 = current2.next;
        }

        return result; // return created table to be printed
    }

    /**
     * Creates a new table comprised of nodes that occur in this table, but not in
     * the other table.
     * 
     * @param table the table to compare to
     * @return result, the table of differences
     */
    Table<T> difference (Table<T> table){
        Table<T> result = new Table<T>("result"); // table to return
        //determines if the element should be added to the result table
        boolean addElement = true;
        Node<T> current = head; // 1ST LIST
        Node<T> current2 = table.head; //2ND LIST

        while(current != null){
            addElement = true;
            while(current2 != null){
                if((current.data.getFirst().equals(current2.data.getFirst()) && 
                   current.data.getLast().equals(current2.data.getLast()))){
                    addElement = false;
                }
                current2 = current2.next;
            }
            if(addElement){
                result.insert(current.data);
            }
            current = current.next;
        }
        return result; // return created table to be printed
    }

    /**
     * Converts the table to a string
     * 
     * @return string, the table as a string
     */
    public String toString() {
        String string = "";
        string = head.data.toString();

        Node<T> current = head;
        while (current.hasNext()) {
            current = current.next;
            string += current.data.toString();
        }
        return string;
    }

    public class Node<T> {

        private T data;

        private Node<T> next;

        /**
         * Constructor that initializes fields
         * 
         * @param data initializes data field
         */
        public Node(T data) {
            this.data = data;
            this.next = null;
        }

        /**
         * Constructor that initializes fields
         * 
         * @param data     initializes data field
         * @param nextNode initializes next field
         */
        private Node(T data, Node<T> nextNode) {
            this.data = data;
            this.next = nextNode;
        }

        /**
         * Gets the data in a given node
         * 
         * @return data, the data at a given node
         */
        public T getData() {
            return data;
        }

        /**
         * Sets the next field
         * 
         * @param next to set the next field
         */
        public void setNext(Node<T> next) {
            this.next = next;
        }

        /**
         * Sets the data field
         * 
         * @param data to set the data field
         */
        public void setData(T data) {
            this.data = data;
        }

        /**
         * Gets the next node
         * 
         * @return next, the next node
         */
        public boolean hasNext() {
            return this.next != null;
        }
    }
}
