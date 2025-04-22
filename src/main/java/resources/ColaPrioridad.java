package resources;

import model.Equipo;
import java.lang.reflect.Array;

public class ColaPrioridad<B> implements Interfase {
    public B[] cola;
    public int size;
    private int positionLastData;

    public ColaPrioridad(Class<B> tipo) {
        cola = (B[]) Array.newInstance(tipo, 36);
        size = 0;
        positionLastData = 0;
    }


    public void insert(B dato){
        for(int i = 1; i < size; i++){
            if(cola[i] == null){
                cola[i] = dato;
                positionLastData = i;
                break;
            }
        }
        size++;
        ordenarColaPorBurbuja();
    }

    public void ordenarColaPorBurbuja() {
        for(int i=0; i<size; i++){
            for(int j = 1; j<size-i; j++){
                Equipo temp = (Equipo) cola[j];
                Equipo temp1 = (Equipo) cola[j + 1];
                if(cola[j + 1] != null && temp.compareTo(temp1) == -1){
                    cola[j] = (B) temp1;
                    cola[j + 1] = (B) temp;
                }
            }
        }
    }

    public void ordenar(int position){
        if(cola[position] == null){
            for(int i = position; i < size; i++){
                if(cola[i+1] != null){
                    cola[i] = cola[i+1];
                }
            }
        }
    }

    public boolean isEmpty(){
        if(size == 0){
            return true;
        }
        return false;
    }

    public B[] getCola() {return cola;}

    public void delete(String name){
        for(int i = 1; i < size; i++){
            Equipo temp = (Equipo) cola[i];
            if(temp.getNombre().equals(name)){
                cola[i] = null;
                size--;
                if(i == positionLastData){
                    positionLastData = 0;
                }
                ordenar(i);
                break;
            }
        }
    }

    public void deleteLastData(){
        cola[positionLastData] = null;
        size--;
        ordenar(positionLastData);
        positionLastData = 0;
    }
}
