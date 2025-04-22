package model;

public class Equipo implements Comparable<Equipo>{
    private String nombre;
    private String pais;
    private int cantidadTitulos;
    private double coeficienteUEFA;
    private int puntuacion;

    public Equipo(String name, String pais, int cantidadTitulos, double coeficienteUEFA) {
        this.nombre = name;
        this.pais = pais;
        this.cantidadTitulos = cantidadTitulos;
        this.coeficienteUEFA = coeficienteUEFA;
        this.puntuacion = 0;
    }
    //getters
    public String getName() {return this.nombre;}
    public String getPais() {return this.pais;}
    public int getCantidadTitulos() {return this.cantidadTitulos;}
    public double getCoeficienteUEFA() {return this.coeficienteUEFA;}
    public int getPuntuacion() {return this.puntuacion;}
    //setters
    public void setNombre(String nombre) {this.nombre = nombre;}
    public void setPais(String pais) {this.pais = pais;}
    public void setCantidadTitulos(int cantidadTitulos){this.cantidadTitulos = cantidadTitulos;}
    public void setCoeficienteUEFA(int coeficienteUEFA){this.coeficienteUEFA = coeficienteUEFA;}
    public void setPuntuacion(int puntuacion){this.puntuacion = puntuacion;}

    public int compareTo(Equipo o) {
        int exit = 0;
        if(coeficienteUEFA > o.getCoeficienteUEFA()){
            exit = 1;
        }else if(coeficienteUEFA < o.getCoeficienteUEFA()){
            exit = -1;
        }
        return exit;
    }

    @Override
    public String toString() {
        String exit = "----------" + "\n" + "Nombre: " + nombre + "\n" + "Pais: " + pais
                + "\n" + "Cantidad de titulos: " + cantidadTitulos + "\n" + "Coeficiente UEFA: " + coeficienteUEFA + "\n" +
                "----------" + "\n";;
        return exit;
    }
}
