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
                posicion = (posicion + 1) % table.length;
                break;
            }
        }
        table[posicion] = valor;
        size++;
    }

    public void borrarUltimaAccion(T llave, String typeObject){
        int posicion = indexTabla(llave);
        int inicial = posicion;
        do {
            Object obj = table[posicion];
            if (obj == null) {
                posicion = (posicion + 1) % table.length;
            }else {
                if (typeObject.equalsIgnoreCase("equipo") && obj instanceof Equipo) {
                    Equipo equipo = (Equipo) table[posicion];
                    if (equipo.getName().equalsIgnoreCase((String) llave)) {
                        table[posicion] = null;
                        break;
                    }
                } else {
                    Partido partido = (Partido) table[posicion];
                    if (partido.getLlave().equalsIgnoreCase((String) llave)) {
                        table[posicion] = null;
                        break;
                    }
                }
                posicion = (posicion + 1) % table.length;
            }
        }while(posicion != inicial);
    }

    public Object search(T llave){
        int posicion = indexTabla(llave);
        for(int i = 0; i< table.length; i++){
            if(table[posicion] instanceof Equipo){
                Equipo temp= (Equipo) table[posicion];
                if(temp.getName().equals(llave)){
                    break;
                }
                posicion = (posicion + 1) % table.length;;
            }else{
                Partido temp= (Partido) table[posicion];
                if(temp.getLlave().equals(llave)){
                    break;
                }
                posicion = (posicion + 1) % table.length;;
            }
        }
        return table[posicion];
    }

    public int indexTabla(T llave){
        return Math.abs(llave.hashCode()) % table.length;
    }
}
