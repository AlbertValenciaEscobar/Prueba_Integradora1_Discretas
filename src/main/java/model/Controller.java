package model;

import resources.Cola;
import resources.ColaPrioridad;
import resources.TablaHash;
import java.util.ArrayList;

public class Controller {
    private ColaPrioridad<Equipo> colaEquipos;
    private Cola<Partido> colaPartidos;
    private TablaHash<String, Object> tabla;
    private String lastAction;
    private String action;
    private String [][] equipos;
    private String [][] clasificacion;

    public Controller() {
        this.colaEquipos = new ColaPrioridad<>(Equipo.class);
        this.colaPartidos = new Cola<>();
        this.tabla = new TablaHash<>();
        lastAction  = "";
        action = "";
        equipos = new String[37][4];
        clasificacion = new String[37][4];
    }

    public void addEquipo(String name, String pais, int cantidadTitulos, double coeficienteUEFA) {
        Equipo equipo = new Equipo(name, pais, cantidadTitulos, coeficienteUEFA);
        tabla.insert(name.replaceAll("\\s", ""), equipo);
        colaEquipos.insert(equipo);
        guardarReferencia();
        action  = name.replaceAll("\\s", "");
    }

    public String validarEquipo(String nombreEquipo) {
        String exit = "No se ha encontrado el equipo";
        for(int i = 1; i < equipos.length; i++){
            if(equipos[i][0].equals(nombreEquipo)){
                exit = "Se a registrado el equipo";
                addEquipo(equipos[i][0], equipos[i][1], Integer.parseInt(equipos[i][2].toString()), Double.parseDouble(equipos[i][3]));
                break;
            }
        }
        return exit;
    }

    public void addPartido(String equipoLocal, String equipoVisitante, int golesVisitante, int golesLocal, String fecha) {
        Partido partido = new Partido(equipoLocal, equipoVisitante, golesVisitante, golesLocal, fecha);
        tabla.insert(equipoLocal + " vs " + equipoVisitante.replaceAll("\\s", ""), partido);
        colaPartidos.insert(partido);
        guardarReferencia();
        action  = equipoLocal + " vs " + equipoVisitante.replaceAll("\\s", "");
    }

    public String actualizarClasificacion(){
        colaEquipos.cola = colaEquipos.getCola();
        String exit = mostrarClasificacion();
        tabla.insert();
        guardarReferencia();
        action  = ;
        return exit;
    }

    public String mostrarClasificacion(){
        String exit = "";
        int count = 1;
        for(int i=1; i<colaEquipos.size; i++){
            exit = exit + count + ") " +colaEquipos.cola[i].getNombre() + "\n";
            count++;
        }
        return exit;
    }

    public String desacerAccion(String llave){
        String exit = "No hay acciones registradas";
        B dato = (B) tabla.borrarUltimaAccion(llave);
        if(dato != null){
            if(dato instanceof Equipo){
                Equipo equipo = (Equipo) dato;
                colaEquipos.cola.remove(equipo);
            }else if (dato instanceof Partido){
                Partido partido = (Partido) dato;
                colaPartidos.cola.remove(partido);
            }else if (dato instanceof String){

            }
            exit = "se a borrado";
        }
        return exit;
    }

    public void guardarReferencia(){lastAction = action;}

}
