package resources;

public class Pila<B>{
    Nodo<B> top;

    public Pila(){
        top = null;
    }

    public void insert(B dato){
        Nodo<B> newNode = new Nodo<>(dato);
        newNode.next = top;
        top = newNode;
    }

    public Nodo<B> pop(){
        if (isEmpty()) {
            throw new RuntimeException("La pila está vacía.");
        }
        Nodo<B> temp = top;
        top = top.next;
        return temp;
    }

    public void delete(){
        if (isEmpty()) {
            throw new RuntimeException("La pila está vacía.");
        }
        top = top.next;
    }

    public Nodo<B> top(){return top;}

    public boolean isEmpty(){
        if(top == null){
            return true;
        }
        return false;
    }
}
