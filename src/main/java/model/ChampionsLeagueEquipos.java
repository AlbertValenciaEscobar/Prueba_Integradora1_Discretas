package model;

public class ChampionsLeagueEquipos {
    private String[][] equiposData;

    public ChampionsLeagueEquipos() {
        equiposData = new String[][]{
                {"Equipo", "Pais", "Titulos", "Coeficiente FIFA"},
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
    }

    public void mostrarEquipos() {
        for (Object[] equipo : equiposData) {
            System.out.println("Equipo: " + equipo[0] + " | País: " + equipo[1] +
                    " | Títulos UCL: " + equipo[2] + " | Coeficiente UEFA: " +
                    equipo[3]);
        }
    }

    public String[][] getEquiposData() {
        return equiposData;
    }
}

