package warehouseApp;

public class Node<N> {

    public Node<N> next=null, previous=null;

    private N contents; // ADT reference

    /**
     *
     * @return
     */
    public N getContents()  {return contents;}

    /**
     *
     * @param c
     */
    public void setContents(N c)  {this.contents = c;}

}
