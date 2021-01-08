package warehouseApp;
import java.util.Iterator;

public class MyList<F> implements Iterable<F> {

    public Node<F> head=null, tail=null;
    private int numberOfContents = 0;

   /**
    * Adds node to a list.
    * @param e - User specified element to add.
    */
    public void addElement(F e) {
        // Creates a new node.
        Node<F> newNode = new Node<>();
        // Sets the contents of the new node to the input e.
        newNode.setContents(e);

        // If the head equals null then the list is empty and:
        if(head == null) {
            // Both head and tail are the newNode.
            head = tail = newNode;
            // Sets previous and next values both to null.
            head.next = null;
            tail.previous = null;
        }
        // If the head isn't null:
        else {
            tail.next=newNode;
            newNode.previous=tail;
            tail=newNode;
            tail.next=null;
        }
        // Adds one to the number of contents as one new node now exists
        numberOfContents++;
    }

    /**
     * Prints list from tail to head, so is displayed correctly in GUI.
     * @return - String form of list.
     */
    public String printList() {
        // Creates a temporary currentNode and sets it to tail.
        Node<F> currentNode = head;
        // Creates an empty string.
        String fullList ="";

        // Traverses through the List.
        while (currentNode != null) {
            // Prints the data at current node.
            fullList += currentNode.getContents() + "\n";
            // Goes to next node.
            currentNode = currentNode.next;
        }
        // Returns the fullList string once the currentNode = null.
        return fullList;
    }

    /**
     * Method to return whether list is empty or not.
     * @return - True if list is empty, false otherwise.
     */
    public boolean isEmpty(){
        return head==null;
    }

    /**
     * Returns current length of a list.
     * @return - INT value of numberOfContents.
     */
    public int length(){
        return numberOfContents;
    }

    /**
     * Removes node at a chosen index.
     * @param index - Index of node to delete.
     */
    public void removeNode(int index) {
        // If the list is not empty.
        if(head!=null) {
            // Create a temporary node.
            Node<F> temp;
            // If the head is to be deleted
            if(index == 0) {
                // If the list contains only a head.
                if(head.next==null) {
                    // Delete it.
                    head=tail=null;
                } else {
                    head=head.next;
                }
                // Decrease number of contents as one node is now removed.
                numberOfContents--;
            }

            // Otherwise if the index > 0 and less than the length of the list:
            else if (index > 0 && index <numberOfContents) {
                // Set the temp node to head
                temp=head;
                // Loop through until temp is the node before the index
                for(int i=1;i<index;i++) {
                    temp=temp.next;
                }
                // The node after temp is now equal to the node after that, so the node has now been deleted.
                temp.next = temp.next.next;
                // Decrease number of contents as one node is now removed.
                numberOfContents--;
            }
            else System.out.println("Invalid Index.");
        }
        else System.out.println("Empty list entered.");
    }

    /**
     * Accesses a node of a chosen index.
     * @param index - Index of node to access.
     * @return - Sought node.
     */
    public Node<F> accessAtIndex(int index){
        // Returns null if negative index is entered.
        if(index<0){
            return null;
        }
        // Sets temp node to the head of the list.
        Node<F> temp = head;
        // Loops through the list while the temp node isn't null.
        if(head!=null){
            // Loop through list until current temp node is the sough node.
            for(int i = 0; i<index;i++){
                temp = temp.next;
            }
        }
        // Return the sought node once found.
        return temp;
    }

    /**
     * Empties a list of its contents.
     */
    public void emptyList() {
        head=tail=null;
        numberOfContents=0;
    }

    /**
     * Iterator to make traversing through lists easier.
     * @return - Head of listIterator.
     */
    @Override
    public Iterator<F> iterator() {
        return new listIterator<F>(head);
    }
}
