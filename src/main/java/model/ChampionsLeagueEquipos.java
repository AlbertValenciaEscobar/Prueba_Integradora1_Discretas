package model;

public class ChampionsLeagueEquipos {
    private Object[][] equiposData;

    public ChampionsLeagueEquipos() {
        equiposData = new Object[][]{
                {"Equipo", "Pais", "Titulos", "Coeficiente FIFA", "Puntuacion"},
                {"Real Madrid", "España", 14, 143.500, 0},
                {"Manchester City", "Inglaterra", 1, 137.750, 0},
                {"Bayern Múnich", "Alemania", 6, 134.250, 0},
                {"Paris Saint-Germain", "Francia", 0, 125.500, 0},
                {"Liverpool", "Inglaterra", 6, 123.000, 0},
                {"Inter de Milán", "Italia", 3, 109.250, 0},
                {"Borussia Dortmund", "Alemania", 1, 98.000, 0},
                {"RB Leipzig", "Alemania", 0, 94.000, 0},
                {"FC Barcelona", "España", 5, 90.000, 0},
                {"Atlético de Madrid", "España", 0, 88.000, 0},
                {"Chelsea", "Inglaterra", 2, 91.000, 0},
                {"Sevilla", "España", 0, 83.000, 0},
                {"Atalanta", "Italia", 0, 81.000, 0},
                {"Bayer Leverkusen", "Alemania", 0, 79.000, 0},
                {"AC Milan", "Italia", 7, 77.000, 0},
                {"Benfica", "Portugal", 2, 75.000, 0},
                {"Shakhtar Donetsk", "Ucrania", 0, 73.000, 0},
                {"Villarreal", "España", 0, 71.000, 0},
                {"Lazio", "Italia", 0, 69.000, 0},
                {"Ajax", "Países Bajos", 4, 67.000, 0},
                {"Olympique de Lyon", "Francia", 0, 65.000, 0},
                {"Dinamo Zagreb", "Croacia", 0, 63.000, 0},
                {"Red Bull Salzburgo", "Austria", 0, 61.000, 0},
                {"Celtic", "Escocia", 1, 59.000, 0},
                {"Rangers", "Escocia", 0, 57.000, 0},
                {"Sporting CP", "Portugal", 0, 55.000, 0},
                {"PSV Eindhoven", "Países Bajos", 1, 53.000, 0},
                {"Feyenoord", "Países Bajos", 1, 51.000, 0},
                {"Galatasaray", "Turquía", 1, 49.000, 0},
                {"Olympique de Marsella", "Francia", 1, 47.000, 0},
                {"Club Brujas", "Bélgica", 0, 45.000, 0},
                {"Besiktas", "Turquía", 0, 43.000, 0},
                {"Fenerbahçe", "Turquía", 0, 41.000, 0},
                {"Sparta Praga", "República Checa", 0, 39.000, 0},
                {"Union Berlin", "Alemania", 0, 37.000, 0},
                {"Newcastle United", "Inglaterra", 0, 35.000, 0}
        };
    }

    public void mostrarEquipos() {
        for (Object[] equipo : equiposData) {
            System.out.println("Equipo: " + equipo[0] + " | País: " + equipo[1] + " | Títulos UCL: " + equipo[2] + " | Coeficiente UEFA: " + equipo[3]);
        }
    }

    public Object[][] getEquiposData() {
        return equiposData;
    }
}

