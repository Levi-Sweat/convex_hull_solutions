public class Table<T extends Contact> {
    private Node head;     // First record in the table
    private Node tail;     // Last record in the table 
    private String title;  // Label for the table

    public Table(String title){
        this.title = title;
        this.head = null;
        this.tail = null;
    }

    public void setHead(Node head){
        this.head = head;
    }

    public void setTail(Node tail){
        this.tail = tail;
    }
    
    // Table<T> difference(Table<T> table){
    // //Creates a new table comprised of nodes in this table, but not in table. 
    
    // }

    //Adds a new record to the end of the this table.
    void insert(T data){
        Node<T> newNode = new Node(data);
        if(head == null){ //if list is empty, set head and tail to new node
            head = newNode;
            tail = newNode;
        } else{ //set tail's next to new node, then update tail to the new node
            tail.setNext(newNode);
            tail = newNode; 
        }   
    }

    //Creates a new table comprised of nodes having a value for a specific attribute, created from both tables.
    Table<T> intersect(String attribute, String value, Table<T> table){
        Table<T> result = new Table("result"); //table to return

        //boolean that says if the sought value has been found already
        boolean found = false;

        Node<T> current = head;
        if(current.data.hasValue(attribute, value)){ //check the head
            result.insert(current.data);
            found = true;
        } else{
            while(current.hasNext()){ //check rest of list
                current = current.next;
                if(current.data.hasValue(attribute, value)){
                    result.insert(current.data);
                    found = true;
                }
            }
        }

        if(!found){ //if the sought value hasn't been found in this table, check the other table
            current = table.head;
            if(current.data.hasValue(attribute, value)){ //check the head
                result.insert(current.data);
            } else{
                while(current.hasNext()){ //check rest of list
                    current = current.next;
                    if(current.data.hasValue(attribute, value)){
                        result.insert(current.data);
                    }
                }
            }
        
        }
        return result; //return created table to be printed
    }

    void remove(String attribute, String value) {
        
        Node<T> current = head.next;
        Node<T> previous = head;
        boolean found = false;

        if(previous.data.hasValue(attribute, value)){
            head = current;
            found = true;
        }
        while(previous.hasNext() && !found){
            
            if(current.data.hasValue(attribute,value)){
                previous.next = current.next;
                found = true;
            }else{
                previous = current;
                current = current.next;
            }
        }

    }
    // Table<T> select(String attribute, String value){
        
    // //Creates a new table comprised of nodes having a value for a specific attribute. 
    // }

    // Table<T> union(Table<T> table){
        
    // //Creates a new table comprised of nodes that occur in either table(s). No duplicates allowed.
    // }  

    public String toString(){
        String string = "";
        string = head.data.toString();

        Node<T> current = head;
        while(current.hasNext()){
            current = current.next;
            string += current.data.toString();
        }
        return string;
    }

    public class Node<T>{

        private T data;

        private Node<T> next;

        /**
         * Constructor that initializes fields
         * 
         * @param data initializes data field
         */
        public Node(T data){
            this.data = data;
            this.next = null;
        }

        /**
         * Constructor that initializes fields
         * 
         * @param data initializes data field
         * @param nextNode initializes next field
         */
        private Node(T data, Node<T> nextNode){
            this.data = data;
            this.next = nextNode;
        }

        /**
         * Gets the data in a given node
         * 
         * @return data the data at a given node
         */
        public T getData(){
            return data;
        }

        /**
         * Sets the next field
         * 
         * @param next to set the next field
         */
        public void setNext(Node<T> next){
            this.next = next;
        }

        /**
         * Sets the data field
         * 
         * @param data to set the data field
         */
        public void setData(T data){
            this.data = data;
        }

        public boolean hasNext(){
            return this.next != null;
        }
    }
}
