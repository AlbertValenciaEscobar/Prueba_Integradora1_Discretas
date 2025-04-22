package ui;
import model.Controller;

import java.util.Scanner;

public class Main {
    private static Controller objMain;
    private static boolean condition = true;
    private static Scanner sc = new Scanner(System.in);

    public Main(){
        objMain = new Controller();
    }

    public static void main(String[] args) {
        new Main();
        cargarDatos();
        condition = true;
    }

    private static void cargarDatos(){
        int option = 0;
        while(condition){
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
                    break;
            }
        }
    }
}
