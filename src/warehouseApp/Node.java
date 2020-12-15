package warehouseApp;

public class Node<N> {

    public Node<N> next=null, previous=null;

    private N contents; // ADT reference

    /**
     * Gets the contents of the referenced node.
     * @return - Contents of node.
     */
    public N getContents()  {return contents;}

    /**
     * Sets the contents of the referenced node.
     * @param c - The node's new contents.
     */
    public void setContents(N c)  {this.contents = c;}

}
