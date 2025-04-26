package resources;

public class Cola<B>{
    private B [] cola;
    private int size;

    public Cola() {
        cola = (B[]) new Object[288];
        size = 0;
    }
    public void insert(B dato){
        cola[size] = dato;
        size++;
    }

    public boolean isEmpty(){
        if(size == 0){
            return true;
        }
        return false;
    }

    public B fisrt(){
        if (isEmpty()) {
            throw new RuntimeException("La cola está vacía.");
        }
        return cola[0];
    }

    public B outFirst(){
        if (isEmpty()) {
            throw new RuntimeException("La cola está vacía.");
        }
        B exit = cola[0];
        cola[0] = null;
        organizar();
        return exit;
    }

    public void organizar(){
        for(int i = 0; i<size; i++){
            if(i < cola.length-1 && cola[i+1] != null){
                cola[i] = cola[i+1];
            }else{
                cola[i] = null;
                break;
            }
        }
        size--;
    }

    public void retroceder(B dato){
        for(int i = size-1; i>=0; i--){
            if(i < cola.length-1){
                cola[i+1] = cola[i];
            }
        }
        cola[0] = dato;
        size++;
    }
}
