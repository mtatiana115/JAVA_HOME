import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Ejercicio02 {

    public static void main (String[] args){
    /*Buscador de Número Mayor y Menor: Utiliza un array de int para almacenar 5
    números enteros ingresados por el usuario (puedes usar la clase Scanner para la
    entrada de datos). El programa debe encontrar y mostrar el número mayor y el
    menor de la lista.*/

        //1. crear array
        int[] nums = new int[5];

        Scanner objScanner = new Scanner(System.in);

        for (int i = 0; i < 5; i++) {
            System.out.println("Ingrese un número entero");
            nums[i] = objScanner.nextInt();
        }

        int mayor = 0;
        int menor = 0;

        for (int num: nums){
            if (num>mayor){
                mayor = num;
            }
        }

        menor = mayor;

        for (int num: nums){
            if (num<menor){
                menor = num;
            }
        }

        System.out.println(mayor);
        System.out.println(menor);


        ArrayList<Integer> numeros = new ArrayList<>();

        numeros.add(33333);




        System.out.println(numeros.toString());


        //mirar num mayor  y menor
    }

}
