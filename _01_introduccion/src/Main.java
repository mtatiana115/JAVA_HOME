import javax.swing.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /*//System.out.println let us print a message by console
        System.out.println("Hello world!");

        //instanciar tha Scanner class let us create an object to read data ingresada from console
        //importar la clase => Me paro despues de la palabra Scanner y le doy ctrl + espacio enter
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
        }*/

        //Ejercicio 1
        //Calculadora basica

        Scanner objScanner1 = new Scanner(System.in);

        String option = JOptionPane.showInputDialog(null,"MENU DE OPERACIONES \n 1. Sumar \n 2. Restar \n 3. Multiplicar \n 4. Dividir");

        //Obtener los dos número a operar
        String num1 = JOptionPane.showInputDialog(null,"Ingrese el primer número");
        String num2 = JOptionPane.showInputDialog(null,"Ingrese el segundo número");

        //Convertir de string a número decimal
        double a = Double.parseDouble(num1);
        double b = Double.parseDouble(num2);

        switch (option){
            case "1":
                JOptionPane.showMessageDialog(null, a + " + "+ b +" = "+ (a+b));
                break;

            case "2":
                JOptionPane.showMessageDialog(null, a + " - "+ b +" = "+ (a-b));
                break;

            case "3":
                JOptionPane.showMessageDialog(null, a + " * "+ b +" = "+ (a*b));
                break;

            case "4":
                if (b == 0){
                    JOptionPane.showMessageDialog(null,"El número b debe ser diferente de cero");
                }else {
                    JOptionPane.showMessageDialog(null, a + " / "+ b +" = "+ (a/b));

                }


            default:
                JOptionPane.showMessageDialog(null,"Opción no válida");
        }
    }
}