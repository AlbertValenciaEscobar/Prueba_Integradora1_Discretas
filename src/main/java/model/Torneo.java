package model;

import resources.*;

public class Torneo {
    public ColaPrioridad equipos;
    private Cola<Partido> partidos;
    public String[][] fixture;

    // Añadimos una lista de equipos precargados
    public String[][] equiposPrecargados = {
            {"Real Madrid", "España", "14", "143.500"},
            {"Manchester City", "Inglaterra", "1", "137.750"},
            {"Bayern Múnich", "Alemania", "6", "134.250"},
            {"Paris Saint-Germain", "Francia", "0", "125.500"},
            {"Liverpool", "Inglaterra", "6", "123.000"},
            {"Inter de Milán", "Italia", "3", "109.250"},
            {"Borussia Dortmund", "Alemania", "1", "98.000"},
            {"RB Leipzig", "Alemania", "0", "94.000"},
            {"FC Barcelona", "España", "5", "90.000"},
            {"Atlético de Madrid", "España", "0", "88.000"},
            {"Chelsea", "Inglaterra", "2", "91.000"},
            {"Sevilla", "España", "0", "83.000"},
            {"Atalanta", "Italia", "0", "81.000"},
            {"Bayer Leverkusen", "Alemania", "0", "79.000"},
            {"AC Milan", "Italia", "7", "77.000"},
            {"Benfica", "Portugal", "2", "75.000"},
            {"Shakhtar Donetsk", "Ucrania", "0", "73.000"},
            {"Villarreal", "España", "0", "71.000"},
            {"Lazio", "Italia", "0", "69.000"},
            {"Ajax", "Países Bajos", "4", "67.000"},
            {"Olympique de Lyon", "Francia", "0", "65.000"},
            {"Dinamo Zagreb", "Croacia", "0", "63.000"},
            {"Red Bull Salzburgo", "Austria", "0", "61.000"},
            {"Celtic", "Escocia", "1", "59.000"},
            {"Rangers", "Escocia", "0", "57.000"},
            {"Sporting CP", "Portugal", "0", "55.000"},
            {"PSV Eindhoven", "Países Bajos", "1", "53.000"},
            {"Feyenoord", "Países Bajos", "1", "51.000"},
            {"Galatasaray", "Turquía", "1", "49.000"},
            {"Olympique de Marsella", "Francia", "1", "47.000"},
            {"Club Brujas", "Bélgica", "0", "45.000"},
            {"Besiktas", "Turquía", "0", "43.000"},
            {"Fenerbahçe", "Turquía", "0", "41.000"},
            {"Sparta Praga", "República Checa", "0", "39.000"},
            {"Union Berlin", "Alemania", "0", "37.000"},
            {"Newcastle United", "Inglaterra", "0", "35.000"}
    };

    public Torneo() {
        this.equipos = new ColaPrioridad();
        this.partidos = new Cola<>();
        this.fixture = new String[32][2]; // 32 equipos para la fase de grupos
    }

    // Agregar un equipo al torneo
    public void agregarEquipo(String name, String pais, int cantidadTitulos, double coeficienteUEFA) {
        Equipo equipo = new Equipo(name, pais, cantidadTitulos, coeficienteUEFA);
        equipos.insert(equipo);
    }

    // Crear el fixture para los partidos
    public void generarFixture() {
        // Organizar los partidos en el orden en que los equipos han sido registrados
        int index = 0;
        for (int i = 0; i < 16; i++) {
            String equipoLocal = ((Equipo) equipos.getCola()[i]).getName();
            String equipoVisitante = ((Equipo) equipos.getCola()[i + 16]).getName();
            fixture[index][0] = equipoLocal;
            fixture[index][1] = equipoVisitante;
            index++;
        }
    }

    // Mostrar el fixture
    public void mostrarFixture() {
        for (int i = 0; i < fixture.length; i++) {
            System.out.println(fixture[i][0] + " vs " + fixture[i][1]);
        }
    }

    // Mostrar clasificación
    public void mostrarClasificacion() {
        System.out.println("Clasificación actual:");
        // Ordenar la cola de equipos según la puntuación
        equipos.ordenarColaPorBurbuja();  // Aseguramos que los equipos estén ordenados

        // Recorremos la cola de equipos y mostramos su posición, nombre y puntos
        for (int i = 0; i < equipos.size(); i++) {
            Equipo equipo = (Equipo) equipos.getCola()[i];  // Obtenemos el equipo de la cola
            System.out.println((i + 1) + ". " + equipo.getName() + " - Puntos: " + equipo.getPuntuacion());
        }
    }
}
