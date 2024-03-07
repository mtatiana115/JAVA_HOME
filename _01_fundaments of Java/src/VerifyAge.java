import javax.swing.*;
import java.util.Scanner;

public class VerifyAge {
    public void ageVerifier(){
        Scanner objScanner = new Scanner(System.in);

        System.out.println("Ingresa tu nombre: ");
        String nombre = objScanner.nextLine();

        System.out.println("Ingresa tu edad: ");
        int edad = objScanner.nextInt();

        System.out.println("Ingresa tu estatura: ");
        double altura = objScanner.nextDouble();

        //Shortcut para imprimir en consola = sout
        System.out.println("Datos " + " nombre: "+ nombre + " edad: "+ edad+ " altura:"+ altura);

        //cerrar la clase Scanner
        objScanner.close();

        if (edad >= 18){
            System.out.println(nombre + " Eres mayor de edad");
            if (altura > 1.80){
                System.out.println(" Eres una persona alta");
            } else if (altura < 1.80 && altura > 1.70){
                System.out.println(" Eres una persona de estatura promedio");
            } else {
                System.out.println(" Eres una persona de estatura baja");
            }
        }else {
            System.out.println(nombre + " Eres menor de edad");
        }
    }
}
