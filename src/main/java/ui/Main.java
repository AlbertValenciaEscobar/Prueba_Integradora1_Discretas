package ui;

import model.Controller;
import java.util.Scanner;

public class Main {
    private static Controller objMain;
    private static boolean condition = true;
    private static Scanner sc = new Scanner(System.in);
    private static int option = 0;

    public Main(){
        objMain = new Controller();
    }

    public static void main(String[] args) {
        new Main();
        condition = true;

        while(condition){
            System.out.println("Seleccione una opción:");
            System.out.println("1. Agregar equipo");
            System.out.println("2. Precargar equipos");
            System.out.println("3. Iniciar torneo");
            System.out.println("4. Mostrar fixture");
            System.out.println("5. Mostrar clasificación");
            System.out.println("6. Deshacer última acción");
            System.out.println("7. Salir");

            option = sc.nextInt();
            sc.nextLine();

            switch (option){
                case 1:
                    agregarEquipo();
                    break;
                case 2:
                    precargarEquipos();
                    break;
                case 3:
                    iniciarTorneo();
                    break;
                case 4:
                    mostrarFixture();
                    break;
                case 5:
                    mostrarClasificacion();
                    break;
                case 6:
                    deshacerAccion();
                    break;
                case 7:
                    System.out.println("Saliendo del programa.");
                    condition = false;  // Termina el ciclo
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }

    // Método para agregar un equipo
    private static void agregarEquipo() {
        System.out.println("Ingrese el nombre del equipo:");
        String name = sc.nextLine();
        System.out.println("Ingrese el país del equipo:");
        String pais = sc.nextLine();
        System.out.println("Ingrese la cantidad de títulos del equipo:");
        int cantidadTitulos = sc.nextInt();
        System.out.println("Ingrese el coeficiente UEFA del equipo:");
        double coeficienteUEFA = sc.nextDouble();
        sc.nextLine();  // Limpiar el buffer

        objMain.addEquipo(name, pais, cantidadTitulos, coeficienteUEFA);
        System.out.println("Equipo registrado con éxito.");
    }

    // Método para precargar equipos
    private static void precargarEquipos() {
        System.out.println("¿Cuántos equipos desea precargar? (Debe ser entre 1 y 36): ");
        int numEquipos = sc.nextInt();
        sc.nextLine();  // Limpiar el buffer

        if (numEquipos < 1 || numEquipos > 36) {
            System.out.println("Número de equipos inválido. Debe ser entre 1 y 36.");
            return;
        }

        objMain.precargarEquipos(numEquipos);  // Se precargan los equipos según la opción del usuario
    }

    private static void iniciarTorneo() {
        objMain.iniciarTorneo();
    }

    private static void mostrarFixture() {
        objMain.getTorneo().mostrarFixture(); // Mostramos el fixture generado
    }

    private static void mostrarClasificacion() {
        objMain.getTorneo().mostrarClasificacion(); // Mostramos la clasificación actualizada
    }

    // Método para deshacer la última acción
    private static void deshacerAccion() {
        String message = objMain.deshacerAccion();  // Llamamos al método deshacerAccion() en el Controller
        System.out.println(message);  // Imprimimos el mensaje de acción deshecha

        // Si la acción deshecha fue un equipo, preguntamos si quiere agregar más equipos
        if (message.contains("Se ha deshecho un equipo")) {
            System.out.println("¿Desea agregar más equipos? (S/N)");
            String respuesta = sc.nextLine();
            if (respuesta.equalsIgnoreCase("S")) {
                // Llamamos al método de precargar equipos para que el usuario ingrese más equipos
                System.out.println("¿Cuántos equipos desea precargar? (Debe ser entre 1 y 36): ");
                int numEquipos = sc.nextInt();
                sc.nextLine();  // Limpiar el buffer

                if (numEquipos < 1 || numEquipos > 36) {
                    System.out.println("Número de equipos inválido. Debe ser entre 1 y 36.");
                } else {
                    objMain.precargarEquipos(numEquipos);  // Se precargan los equipos según el número proporcionado
                }
            }
        }

        // Si la acción deshecha fue un partido, mostramos el fixture actualizado
        if (message.contains("Se ha deshecho un partido")) {
            System.out.println("¿Desea modificar el fixture o continuar? (Modificar/Continuar)");
            String opcion = sc.nextLine();
            if (opcion.equalsIgnoreCase("Modificar")) {
                objMain.getTorneo().mostrarFixture();  // Mostrar el fixture actualizado
            }
        }
    }
}
