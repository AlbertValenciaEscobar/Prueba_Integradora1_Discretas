package model;

import resources.*;
import java.util.Scanner;

public class Controller {
    private ColaPrioridad colaEquipos;
    private Cola<Partido> colaPartidos;
    private TablaHash<String, Object> tabla;
    private Pila<Object> actions;
    private Torneo torneo;

    public Controller() {
        this.colaEquipos = new ColaPrioridad();
        this.colaPartidos = new Cola<>();
        this.tabla = new TablaHash<>();
        this.actions = new Pila<>();
        this.torneo = new Torneo();
    }

    public Torneo getTorneo() {
        return this.torneo;
    }

    // Método para registrar un nuevo equipo
    public void addEquipo(String name, String pais, int cantidadTitulos, double coeficienteUEFA) {
        Equipo equipo = new Equipo(name, pais, cantidadTitulos, coeficienteUEFA);
        tabla.insert(name.replaceAll("\\s", ""), equipo);
        colaEquipos.insert(equipo);
        actions.insert(equipo);
    }

    // Método para precargar equipos
    public void precargarEquipos(int numEquipos) {
        if (numEquipos < 1 || numEquipos > 36) {
            System.out.println("Número de equipos inválido. Debe ser entre 1 y 36.");
            return;
        }

        for (int i = 0; i < numEquipos; i++) {
            String nombre = torneo.equiposPrecargados[i][0];
            String pais = torneo.equiposPrecargados[i][1];
            int titulos = Integer.parseInt(torneo.equiposPrecargados[i][2]);
            double coeficiente = Double.parseDouble(torneo.equiposPrecargados[i][3]);
            addEquipo(nombre, pais, titulos, coeficiente);
        }

        System.out.println(numEquipos + " equipos precargados con éxito.");
    }


    // Método para iniciar el torneo
    public void iniciarTorneo() {
        if (colaEquipos.size() < 16) {
            System.out.println("No hay suficientes equipos registrados para iniciar el torneo. Debe haber al menos 16 equipos.");
            return;
        }

        // 1. Generamos el Fixture
        torneo.generarFixture();  // Crea los partidos según el orden de los equipos

        // 2. Mostramos el Fixture
        torneo.mostrarFixture();  // Mostramos los partidos que se han generado

        // 3. Ingresar resultados de los partidos
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 16; i++) {
            String equipoLocal = torneo.fixture[i][0];
            String equipoVisitante = torneo.fixture[i][1];
            System.out.println("Partido: " + equipoLocal + " vs " + equipoVisitante);

            // Solicitamos los goles a ambos equipos
            System.out.println("Ingrese los goles de " + equipoLocal + ": ");
            int golesLocal = scanner.nextInt();

            System.out.println("Ingrese los goles de " + equipoVisitante + ": ");
            int golesVisitante = scanner.nextInt();

            // 4. Ejecutamos el partido y actualizamos la clasificación
            ejecutarPartido(equipoLocal, equipoVisitante, golesLocal, golesVisitante);

            // Mostrar la clasificación después de cada partido
            torneo.mostrarClasificacion();
        }
    }

    // Ejecutar partido y actualizar puntos
    public void ejecutarPartido(String equipoLocal, String equipoVisitante, int golesLocal, int golesVisitante) {
        // Creamos el partido
        Partido temp = new Partido(equipoLocal, equipoVisitante, golesVisitante, golesLocal, "");

        // Insertamos el partido en la tabla de partidos
        tabla.insert(equipoLocal + " vs " + equipoVisitante, temp);
        colaPartidos.insert(temp); // Insertamos el partido en la cola

        // actualizacion dep untos
        Equipo teamLocal = (Equipo) tabla.search(equipoLocal);
        Equipo teamVisitor = (Equipo) tabla.search(equipoVisitante);

        // asignacion de puntos
        if (golesLocal > golesVisitante) {
            teamLocal.setPuntuacion(3); // Local gana
            temp.setResult("local");
        } else if (golesLocal < golesVisitante) {
            teamVisitor.setPuntuacion(3); // Visitante gana
            temp.setResult("visitor");
        } else {
            teamLocal.setPuntuacion(1); // Empate
            teamVisitor.setPuntuacion(1);
            temp.setResult("draw");
        }

        // Registramos la acción para poder deshacerla
        actions.insert(temp);

        // Actualizcion
        torneo.mostrarClasificacion(); // Mostramos la clasificación después de cada partido
    }

    // Deshacer la última acción registrada
    // Método para deshacer la última acción registrada
    public String deshacerAccion() {
        String exit = "No se puede deshacer";
        if (!actions.isEmpty()) {
            Object lastAction = actions.pop().getData();
            if (lastAction instanceof Equipo) {
                // Deshacer la acción de registrar un equipo
                Equipo temp = (Equipo) lastAction;
                String llave = temp.getName();
                tabla.borrarUltimaAccion(llave, "equipo");
                colaEquipos.delete(llave);
                Equipo.numTeams--;
                exit = "Se ha deshecho un equipo. Por favor, inscribe todos los equipos faltantes para iniciar el torneo.";
            } else if (lastAction instanceof Partido) {
                // Deshacer la acción de registrar un partido
                Partido temp = (Partido) lastAction;
                String llave = temp.getLlave();
                tabla.borrarUltimaAccion(llave, "partido");
                Equipo teamLocal = (Equipo) tabla.search(temp.getEquipoLocal());
                Equipo teamVisitor = (Equipo) tabla.search(temp.getEquipoVisitante());
                // Restar los puntos que se habían asignado
                if (temp.getResult().equals("local")) {
                    teamLocal.setPuntuacion(-2);
                } else if (temp.getResult().equals("visitor")) {
                    teamVisitor.setPuntuacion(-2);
                } else {
                    teamVisitor.setPuntuacion(-1);
                    teamLocal.setPuntuacion(-1);
                }
                colaPartidos.retroceder(temp); // Restaurar el partido
                exit = "Se ha deshecho un partido. Los puntos han sido revertidos.";
            } else {
                exit = "No hay acción para deshacer.";
            }
        }
        return exit;
    }

}
