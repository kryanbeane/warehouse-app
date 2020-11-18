package warehouseApp;

import java.util.Iterator;

public class MyList<F> implements Iterable<F> {

    public Node<F> head=null, tail=null;
    private int numberOfContents = 0;

    public void addElement(F e) {
        // We want to add it in at the end, not the start
        // Create a new node
        Node<F> nn = new Node<>();
        nn.setContents(e);

        // If the head equals null then the list is empty
        if(head == null) {
            head = tail = nn;           // Both head and tail are the newNode; The first, last, and only item
            head.previous = null;       // Now head and tail will be linked to null from previous and next
            tail.next = null;
        }
        else {
            head.previous=nn;           // nn is the head's previous
            nn.next = head;             // The next after nn is current head node, two are now linked
            head=nn;                    // We can now move the head to newNode
            head.previous=null;         // The one before head is null, as it is the head of the list.(Line may not be needed)
        }
        numberOfContents++;
    }

    public  String printList() {
        Node<F> currentNode = head;
        String fullList ="";

        while (currentNode != null) {                       // Traverse through the List
            fullList += currentNode.getContents() + "\n";   // Print the data at current nod
            currentNode = currentNode.next;                 // Go to next node
        }
        return fullList;
    }

    public String printList2(){
        String fullList = "";
        for(Node temp = head; temp!=null; temp=temp.next){
            fullList += temp.getContents() + "\n";
        }
        return fullList;
    }

    public boolean isEmpty(){
        return head==null;
    }

    public int length(){
        return numberOfContents;
    }

    public Node<F> accessFirst(){
        return head;
    }

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

    public Node<F> accessSecond(){
        if(head.next!=null) {
            return head.next;
        }else{
            return null;
        }
    }

    public Node<F> accessAtIndex(int index){
        if(index<0)return null;

        Node<F> temp = head;
        if(head!=null){
        }
        for(int i = 0; i<index;i++){
            temp = temp.next;
        }
        return temp;
    }

    public void clear() {
        head=null;
    }

    public void emptyList() {
        head=null;
    }

    @Override
    public Iterator<F> iterator() {
        return new listIterator<F>(head);
    }

}
