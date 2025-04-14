package resources;

public class Nodo<B> {
    Nodo<B> next;
    B data;

    public Nodo(B dato) {
        this.data = dato;
        this.next = null;
    }
}
