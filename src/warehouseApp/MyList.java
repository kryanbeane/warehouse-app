package warehouseApp;
import java.util.Iterator;

public class MyList<F> implements Iterable<F> {

    public Node<F> head=null, tail=null;
    private int numberOfContents = 0;

    /**
     *
     * @param e - User specified element to add.
     */
    public void addElement(F e) {
        // We want to add it in at the end, not the start.
        // Create a new node.
        Node<F> newNode = new Node<>();
        newNode.setContents(e);

        // If the head equals null then the list is empty.
        if(head == null) {
            // Both head and tail are the newNode; The first, last, and only item.
            head = tail = newNode;
            // Now head and tail will be linked to null from previous and next.
            head.previous = null;
            tail.next = null;
        }
        else {
            // newNode is the head's previous
            head.previous=newNode;
            // The next after nn is current head node, two are now linked.
            newNode.next = head;
            // We can now move the head to newNode.
            head=newNode;
            // The one before head is null, as it is the head of the list.
            head.previous=null;
        }
        numberOfContents++;
    }

    /**
     * Prints list from tail to head, so is displayed correctly in GUI.
     * @return - String form of list.
     */
    public String printList() {
        //
        Node<F> currentNode = tail;
        //
        String fullList ="";

        // Traverse through the List
        while (currentNode != null) {
            // Print the data at current node
            fullList += currentNode.getContents() + "\n";
            // Go to next node
            currentNode = currentNode.previous;
        }
        return fullList;
    }

    /**
     *
     * @return -
     */
    public boolean isEmpty(){
        return head==null;
    }

    /**
     *
     * @return
     */
    public int length(){
        return numberOfContents;
    }

    /**
     *
     * @return
     */
    public Node<F> accessFirst(){
        return head;
    }

    /**
     *
     * @param index
     */
    public void removeNode(int index){

        if (head!=null){                                    // Check to make sure not empty
            Node<F> temp;                                   // Update, now using two nodes

            if (index == 0){                                // If you want to delete the first item
                if(head.next==null){                        // And if the list contains only one item
                    head=tail=null;                         // -from skydiver example.
                }else{
                    head=head.next;                         // Else, set the head equal to the next node(original head no longer using memory).
                }
                numberOfContents--;
                System.out.println("Deleted node at position: 0");
            }

            else if (index > 0 && index <numberOfContents){
                temp=head;

                for(int i=1;i<index;i++) temp=temp.next;    // Makes temp the one BEFORE the item to be deleted.('<index')
                temp.next = temp.next.next;                 // The one after temp is now equal to the one after that.
                numberOfContents--;                         // Then the last node is equal to the one before the one to be deleted.
                //(thus deleting the desired node)
                System.out.println("Deleted node at position: " + index);
            }
            else System.out.println("Index was beyond range!");
        }
        else System.out.println("The list was empty!");
    }

    /**
     *
     * @return
     */
    public Node<F> accessSecond(){
        if(head.next!=null) {
            return head.next;
        }else{
            return null;
        }
    }

    /**
     *
     * @param index
     * @return
     */
    public Node<F> accessAtIndex(int index){
        if(index<0){
            return null;
        }
        Node<F> temp = head;
        if(head!=null){
            for(int i = 0; i<index;i++){
                temp = temp.next;
            }
        }
        return temp;
    }

    // Empties a list
    public void emptyList() {
        head=null;
        tail=null;
        numberOfContents=0;
    }

    /**
     *
     * @return
     */
    @Override
    public Iterator<F> iterator() {
        return new listIterator<F>(head);
    }
}
