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
        cargarDatos();
        condition = true;
        while(condition){
            System.out.println("1. Jugar partido");
            System.out.println("2. Retroceder una accion");
            System.out.println("3. Mostrar clasificacion");
            option = sc.nextInt();
            sc.nextLine();
            switch (option){
                case 1:

                    break;
                case 2:
                    String message = objMain.deshacerAccion();
                    System.out.println(message);
                    if(message.equalsIgnoreCase("Se ha desecho un equipo, porfavor inscribe todos los equipos faltantes para iniciar el torneo")){
                        cargarDatos();
                    }
                    break;
                case 3:

                    break;
            }
        }
    }

    private static void cargarDatos(){
        while(condition){
            System.out.println(objMain.getNumTeams() + "/36 equipos inscripcion");
            System.out.println("1. Inscribir equipo");
            System.out.println("2. precargar datos");
            System.out.println("3. Retroceder una accion");
            option = sc.nextInt();
            sc.nextLine();
            switch (option){
                case 1:
                    System.out.println("Escribe el equipo que clasefica");
                    String contMessage = objMain.registrarEquipo(sc.nextLine());
                    System.out.println(contMessage);
                    if(contMessage.equalsIgnoreCase("Ya todos los equipos posibles fueron registrados")){
                        condition = false;
                    }
                    break;
                case 2:
                    objMain.precargar();
                    condition = false;
                    break;
                case 3:
                    System.out.println(objMain.deshacerAccion());
                    break;
            }
        }
        System.out.println("Iniciando Torneo...");
    }
}
