package model;

public class Partido {
    private String equipoLocal;
    private String equipoVisitante;
    private int golesVisitante;
    private int golesLocal;
    private String fecha;

    public Partido(String equipoLocal, String equipoVisitante, int golesVisitante, int golesLocal, String fecha) {
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
        this.golesVisitante = golesVisitante;
        this.golesLocal = golesLocal;
        this.fecha = fecha;
    }
    //getters
    public String getEquipoLocal() {return equipoLocal;}
    public String getEquipoVisitante() {return equipoVisitante;}
    public int getGolesVisitante() {return golesVisitante;}
    public int getGolesLocal() {return golesLocal;}
    public String getFecha() {return fecha;}
    public String getLlave(){return equipoLocal + " vs " + equipoVisitante.replaceAll("\\s", "");}
    //setters
    public void setEquipoLocal(String equipoLocal) {this.equipoLocal = equipoLocal;}
    public void setEquipoVisitante(String equipoVisitante) {this.equipoVisitante = equipoVisitante;}
    public void setGolesVisitante(int golesVisitante) {this.golesVisitante = golesVisitante;}
    public void setGolesLocal(int golesLocal) {this.golesLocal = golesLocal;}
    public void setFecha(String fecha) {this.fecha = fecha;}

    @Override
    public String toString() {
        String exit = "----------" + "\n" + "Equipo local: " + equipoLocal + "\n" + "Equipo visitante: " + equipoVisitante
                        + "\n" + "Goles del visitante: " + golesVisitante + "\n" + "Goles del local: " + golesLocal + "\n" +
                        "Fecha: " + fecha + "\n" + "----------" + "\n";
        return exit;
    }
}
