package warehouseApp;

import java.util.Iterator;

public class listIterator<K> implements Iterator<K> {

    private Node<K> pos;

    public listIterator(Node<K> node){pos=node;}

    /**
     * Returns a boolean response to whether there is a node in the next slot or not.
     * @return - True if next node isn't null, false otherwise.
     */
    @Override
    public boolean hasNext() {
        return pos!=null;
    }

    /**
     * Method to call and return the next node.
     * @return - The next node
     */
    @Override
    public K next() {
        Node<K> temp = pos;
        pos = pos.next;
        return temp.getContents();
    }

}
