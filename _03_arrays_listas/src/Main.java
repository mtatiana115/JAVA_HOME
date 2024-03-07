import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /*Calculadora de Promedios: Escribe un programa que utilice un array de tipo
        double para almacenar las calificaciones finales de 10 estudiantes en un curso. El
        programa debe calcular y mostrar el promedio de estas calificaciones. */

        //1. Creamos nuestro objeto scanner
        Scanner objScanner = new Scanner(System.in);

        //3. CReamos nuestro array que guardar{a las notas de los estudiantes
        //3.1 donde voy a guardar el array: tipo de dato (double[]), nombre variable, new para crear, creo un doble de x posisciones
        double[] notas = new  double[10];

        //6. variable para guardar la suma total
        double sumaTotal =0;

        //2. ciclo for para pedir 10 veces al estudiante la nota.
        for (int i = 0; i < 10; i++) {
            System.out.println("Ingrese la nota del estudiante #"+(i+1));

            //5.
            try {
                //4.
                notas [i] = objScanner.nextDouble();

                //7.
                sumaTotal += notas[i];
            }catch (Exception e){
                System.out.println("Nota no válida");
                break;
            }
        }
        //8. variable para guardar el promedio general de las notas
        double promedio = sumaTotal/notas.length;
        System.out.println("El promedio de todas las notas es: "+promedio);
    }
}