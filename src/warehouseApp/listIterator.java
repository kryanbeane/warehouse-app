package warehouseApp;

import java.util.Iterator;

public class listIterator<K> implements Iterator<K> {

    private Node<K> pos;

    /**
     *
     * @param node
     */
    public listIterator(Node<K> node){pos=node;}

    /**
     *
     * @return
     */
    @Override
    public boolean hasNext() {
        return pos!=null;
    }

    /**
     *
     * @return
     */
    @Override
    public K next() {
        Node<K> temp = pos;
        pos = pos.next;
        return temp.getContents();
    }

}
