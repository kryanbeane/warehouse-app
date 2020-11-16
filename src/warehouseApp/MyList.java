package warehouseApp;

import java.util.Iterator;

public class MyList<F> implements Iterable<F> {

    public Node<F> head=null, tail=null;

    public void addElement(F e) {
        Node<F> nn=new Node<>();
        nn.setContents(e);
        nn.next=head;
        head=nn;
    }

    public String printList() {
        Node currentNode = head;
        String fullList = " LinkedList: \n";

        // Traverse through the LinkedList
        while (currentNode != null) {
            // Print the data at current node
            fullList += currentNode.getContents() + "\n";

            // Go to next node
            currentNode = currentNode.next;
        }
        return fullList;
    }

    public void clearList() {
        head=null;
    }

    @Override
    public Iterator<F> iterator() {
        return new listIterator<F>(head);
    }

}
