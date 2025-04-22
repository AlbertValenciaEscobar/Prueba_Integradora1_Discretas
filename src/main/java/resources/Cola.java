package resources;

import model.*;
import java.util.ArrayList;

public class Cola<B> implements Interfase{
    private ArrayList<B> cola;
    private int size;

    public Cola() {
        cola = new ArrayList<B>();
        cola.add(null);
        size = 0;
    }
    public void insert(B dato){
        B temp = (B) dato;
        cola.add(temp);
        size++;
    }

    public boolean isEmpty(){
        if(size == 0){
            return true;
        }
        return false;
    }

    public B fisrt(){
        if (cola.isEmpty()) {
            throw new RuntimeException("La cola está vacía.");
        }
        return cola.get(1);
    }

    public B outFirst(){
        if (cola.isEmpty()) {
            throw new RuntimeException("La cola está vacía.");
        }
        B exit = cola.get(1);
        cola.remove(1);
        return exit;
    }
}
