package resources;

public class TablaHash<T, B> {
    private Pila<B>[] table;
    private int size;

    public TablaHash() {
        table = (Pila<B>[]) new Object[97];
        size = 0;
        for (int i = 0; i < table.length; i++) {
            table[i] = new Pila<>();
        }
    }

    public void insert(T llave, B valor){
        int posicion = indexTabla(llave);
        table[posicion].insert(valor);
        size++;
    }

    public B borrarUltimaAccion(T llave){
        B exit = null;
        int posicion = indexTabla(llave);
        if(!table[posicion].isEmpty()){
            exit = table[posicion].pop().data;
        }
        return exit;
    }

    public int indexTabla(T llave){
        return Math.abs(llave.hashCode() % table.length);
    }
}
