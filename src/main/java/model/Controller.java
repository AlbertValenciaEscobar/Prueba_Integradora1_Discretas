package model;

import resources.*;

public class Controller {
    private ColaPrioridad colaEquipos;
    private Cola<Partido> colaPartidos;
    private TablaHash<String, Object> tabla;
    private Pila<Object> actions;
    private String[][] equipos;
    private String [][] clasificacion;

    public Controller() {
        this.colaEquipos = new ColaPrioridad();
        this.colaPartidos = new Cola<>();
        this.tabla = new TablaHash<>();
        this.actions = new Pila<>();
        equipos = new ChampionsLeagueEquipos().getEquiposData();
        clasificacion = new String[37][5];
        clasificacion[0][0]="Equipo";clasificacion[0][1]="Pais";
        clasificacion[0][2]="Titulos";clasificacion[0][3]="Coeficiente FIFA";clasificacion[0][4]="puntuacion";
    }

    public void addEquipo(String name, String pais, int cantidadTitulos, double coeficienteUEFA) {
        Equipo equipo = new Equipo(name, pais, cantidadTitulos, coeficienteUEFA);
        tabla.insert(name.replaceAll("\\s", ""), equipo);
        colaEquipos.insert(equipo);
        actions.insert(equipo);
    }

    public void addPartido(String equipoLocal, String equipoVisitante, int golesVisitante, int golesLocal, String fecha) {
        Partido partido = new Partido(equipoLocal, equipoVisitante, golesVisitante, golesLocal, fecha);
        tabla.insert(equipoLocal + " vs " + equipoVisitante.replaceAll("\\s", ""), partido);
        colaPartidos.insert(partido);
    }

    public void ejecutarPartido(int local, int visitor) {
        Partido temp = (Partido) colaPartidos.outFirst();
        Equipo teamLocal = (Equipo) tabla.search(temp.getEquipoLocal());
        Equipo teamVisitor = (Equipo) tabla.search(temp.getEquipoVisitante());
        if(local > visitor){
            teamLocal.setPuntuacion(2);
            temp.setResult("local");
        } else if(local < visitor){
            teamVisitor.setPuntuacion(2);
            temp.setResult("visitor");
        }else{
            teamVisitor.setPuntuacion(1);
            teamLocal.setPuntuacion(1);
            temp.setResult("draw");
        }
        actions.insert(temp);
        colaEquipos.ordenarColaPorBurbuja();
    }

    public String actualizarClasificacion(){
        String exit = "";
        return exit;
    }

    public String mostrarClasificacion(){
        String exit = "";
        return exit;
    }

    public String deshacerAccion(){
        String exit = "No se puede deshacer";
        if(!actions.isEmpty()) {
            exit = "Se ha deshacido una accion";
            Object lastAction = actions.pop().getData();
            if (lastAction instanceof Equipo) {
                Equipo temp = (Equipo) lastAction;
                String llave = temp.getName();
                tabla.borrarUltimaAccion(llave, "equipo");
                colaEquipos.delete(llave);
                Equipo.numTeams--;
                exit = "Se ha desecho un equipo, porfavor inscribe todos los equipos faltantes para iniciar el torneo";
            } else {
                Partido temp = (Partido) lastAction;
                String llave = temp.getLlave();
                tabla.borrarUltimaAccion(llave, "partido");
                Equipo teamLocal = (Equipo) tabla.search(temp.getEquipoLocal());
                Equipo teamVisitor = (Equipo) tabla.search(temp.getEquipoVisitante());
                if(temp.getResult().equals("local")){
                    teamLocal.setPuntuacion(-2);
                } else if(temp.getResult().equals("visitor")){
                    teamVisitor.setPuntuacion(-2);
                }else{
                    teamVisitor.setPuntuacion(-1);
                    teamLocal.setPuntuacion(-1);
                }
                colaPartidos.retroceder(temp);
            }
        }
        return exit;
    }

    // Logica del codigo

    public int getNumTeams(){return Equipo.numTeams;}

    public String registrarEquipo(String nameTeam){
        String exit = "No se pudo registrar";
        for(int i = 1; i<equipos.length; i++){
            if(equipos[i][0].equals(nameTeam)){
                addEquipo(nameTeam, equipos[i][1], Integer.parseInt(equipos[i][2]),
                        Double.parseDouble(equipos[i][3]));
                if(Equipo.numTeams == 36){
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
            if(!equipos[i][0].equalsIgnoreCase("") && Equipo.numTeams<36){
                addEquipo(equipos[i][0], equipos[i][1], Integer.parseInt(equipos[i][2]),
                        Double.parseDouble(equipos[i][3]));
            }
        }
    }

}
