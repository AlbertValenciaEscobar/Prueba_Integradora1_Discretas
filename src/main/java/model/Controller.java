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
    private String[][] equipos;
    private String [][] clasificacion;
    private int numTeams = 0;

    public Controller() {
        this.colaEquipos = new ColaPrioridad<>(Equipo.class);
        this.colaPartidos = new Cola<>();
        this.tabla = new TablaHash<>();
        lastAction  = "";
        action = "";
        equipos = new ChampionsLeagueEquipos().getEquiposData();
        clasificacion = new String[37][5];
        clasificacion[0][0]="Equipo";clasificacion[0][1]="Pais";
        clasificacion[0][2]="Titulos";clasificacion[0][3]="Coeficiente FIFA";clasificacion[0][4]="puntuacion";
    }

    public void addEquipo(String name, String pais, int cantidadTitulos, double coeficienteUEFA) {
        Equipo equipo = new Equipo(name, pais, cantidadTitulos, coeficienteUEFA);
        tabla.insert(name.replaceAll("\\s", ""), equipo);
        colaEquipos.insert(equipo);
        guardarReferencia();
        action  = name.replaceAll("\\s", "");
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

    // Logica del codigo

    public String registrarEquipo(String nameTeam){
        String exit = "No se pudo registrar";
        for(int i = 1; i<equipos.length; i++){
            if(equipos[i][0].equals(nameTeam)){
                addEquipo(nameTeam, equipos[i][1], Integer.parseInt(equipos[i][2]),
                        Double.parseDouble(equipos[i][3]));
                numTeams++;
                if(numTeams == 37){
                    exit = "Ya todos los equipos posibles fueron registrados";
                }else {
                    equipos[i][0] = "";
                    System.out.println("Se pudo registrar");
                    break;
                }
            }
        }
        return exit;
    }

    public void precargar(){
        for(int i = 1; i<equipos.length; i++){
            if(!equipos[i][0].equalsIgnoreCase("") && numTeams<37){
                addEquipo(equipos[i][0], equipos[i][1], Integer.parseInt(equipos[i][2]),
                        Double.parseDouble(equipos[i][3]));
                numTeams++;
            }
        }
    }

}
