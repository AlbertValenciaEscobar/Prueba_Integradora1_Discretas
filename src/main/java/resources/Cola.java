package resources;

import model.*;
import java.util.ArrayList;

public class Cola<B> {
    private ArrayList<B> cola;
    private int size;

    public Cola() {
        cola = new ArrayList<B>();
        cola.add(null);
        size = 0;
    }

    public void insert(Equipo dato){
        B temp = (B) dato;
        cola.add(temp);
        size++;
        cola = ordenarColaPorBurbuja();
    }
    public void insert(Partido dato){
        B temp = (B) dato;
        cola.add(temp);
        size++;
    }

    public ArrayList<B> ordenarColaPorBurbuja() {
        for(int i=0; i<cola.size(); i++){
            for(int j = 0; j<cola.size()-i; j++){
                Equipo temp = (Equipo) cola.get(j);
                Equipo temp1 = (Equipo) cola.get(j + 1);
                if(temp.compareTo(temp1) == -1){
                    cola.set(j, (B) temp1);
                    cola.set(j + 1, (B) temp);
                }
            }
        }
        return cola;
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
