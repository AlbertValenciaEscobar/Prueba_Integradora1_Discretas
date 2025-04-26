package resources;

import model.Equipo;

public class ColaPrioridad{
    public Equipo[] cola;
    public int size;

    public ColaPrioridad() {
        cola = new Equipo[36];
        size = 0;
    }


    public void insert(Equipo dato){
        for(int i = 1; i < size; i++){
            if(cola[i] == null){
                cola[i] = dato;
                break;
            }
        }
        size++;
        ordenarColaPorBurbuja();
    }

    public void ordenarColaPorBurbuja() {
        for(int i=0; i<size; i++){
            for(int j = 1; j<size-i-1; j++){
                Equipo temp = cola[j];
                Equipo temp1 = cola[j + 1];
                if(temp1 != null && temp.compareTo(temp1) == -1){
                    cola[j] = temp1;
                    cola[j + 1] = temp;
                }else if(temp1 != null && temp.compareTo(temp1) == 0){
                    if(temp.getCoeficienteUEFA() < temp1.getCoeficienteUEFA()) {
                        cola[j] = temp1;
                        cola[j + 1] = temp;
                    }
                }
            }
        }
    }

    public void ordenar(int position){
        for(int i = position; i < size; i++){
            if(cola[i+1] != null){
                cola[i] = cola[i+1];
            }
        }
        size--;
    }

    public boolean isEmpty(){
        if(size == 0){
            return true;
        }
        return false;
    }

    public Equipo[] getCola() {return cola;}

    public void delete(String name){
        for(int i = 1; i < size; i++){
            Equipo temp = cola[i];
            if(temp.getName().equals(name)){
                cola[i] = null;
                ordenar(i);
                break;
            }
        }
    }
    public int size() {
        return size;
    }

}
