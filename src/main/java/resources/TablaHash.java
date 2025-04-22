package resources;

import model.Equipo;
import model.Partido;

public class TablaHash<T, B> {
    private B[] table;
    private int size;

    public TablaHash() {
        table = (B[]) new Object[97];
        size = 0;
    }

    public void insert(T llave, B valor){
        int posicion = indexTabla(llave);
        for(int i = 0; i< table.length; i++){
            if(table[posicion] != null){
                posicion++;
                break;
            }
        }
        table[posicion] = valor;
        size++;
    }

    public void borrarUltimaAccion(T llave){
        int posicion = indexTabla(llave);
        while(true) {
            if(table[posicion] instanceof Equipo){
                Equipo temp= (Equipo) table[posicion];
                if (temp != null && temp.getName().equalsIgnoreCase((String) llave)) {
                    table[posicion] = null;
                    break;
                }else{
                    posicion++;
                }
            }else{
                Partido temp= (Partido) table[posicion];
                if (temp != null && temp.getLlave().equalsIgnoreCase((String)llave)) {
                    table[posicion] = null;
                    break;
                }else{
                    posicion++;
                }
            }
        }
    }

    public int indexTabla(T llave){
        return Math.abs(llave.hashCode()) % table.length;
    }
}
