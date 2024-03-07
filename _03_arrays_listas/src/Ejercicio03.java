import java.util.Scanner;

public class Ejercicio03 {
    //1. debo crear un método main
    public static void main(String[] args) {
        //System.out.println("Hola");

        //2. como decirle al programa que corra esta clase Ejercicio03
        /* Current File en la parte superior derecha*/

        /*Sistema de Reservas: Desarrolla un programa para un teatro para gestionar las
        reservas de asientos. El teatro tiene 5 filas con 10 asientos cada una. Utiliza un
        arreglo bidimensional de boolean donde true representa un asiento ocupado y false
        uno disponible. El sistema debe permitir:
         Reservar y cancelar asientos.
         Mostrar los asientos disponibles.
         Contabilizar el total de asientos ocupados y disponibles.*/

        //3. crear la matriz para guardar el estado de todos los asientos
        boolean[][] listaAsientos = new boolean[5][10];

        //4. Objeto Scanner para poder obtener info del usuario por consola
        Scanner objScanner = new Scanner(System.in);

        //5. Variable para guardar la opción del usuario
        int opcion = 0;

        do {
            objScanner.nextLine();
            System.out.println("ENTER PARA CONTINUAR...");
            objScanner.nextLine();

            System.out.println("----- RESERVA DE SILLAS TEATRO -----");
            System.out.println("1. Reservar asiento.");
            System.out.println("2. Cancelar asiento.");
            System.out.println("3. Mostrar asientos disponibles");
            System.out.println("4. Contabilizar el total de asientos ocupados y disponibles.");
            System.out.println("5. Salir");
            System.out.println("Ingrese una opción");

            //6. leer la opción elegida por el usuario
            opcion = objScanner.nextInt();

            //7. validar opciones
            switch (opcion){
                case 1:
                    //que asiento quiere reservar
                    System.out.println("Ingrese la fila (1-5)");
                    //leer la fila. se debe restar -1 ya que los arreglos empiezan desde cero
                    int fila = objScanner.nextInt()-1;
                    System.out.println("ingrese el asiento (1-10)");
                    int asiento = objScanner.nextInt()-1;

                    /*validar que el asiento está disponible (viene por defecto todo en falso)*/
                    //if(listaAsientos[fila][asiento] == false){

                    if(!listaAsientos[fila][asiento]){

                        listaAsientos[fila][asiento] = true;
                        System.out.println("Asiento reservado correctamente!");
                    } else {
                        System.out.println("Este asiento ya se encuentra reservado.");
                    }

                    break;

                case 2:
                    System.out.println("Ingrese la fila (1-5)");
                    fila = objScanner.nextInt()-1;
                    System.out.println("Ingrese el asiento (1-10)");
                    asiento = objScanner.nextInt()-1;

                    if (listaAsientos[fila][asiento]){
                        listaAsientos[fila][asiento] = false;
                        System.out.println("Reserva de asiento cancelada correctamente");
                    } else {
                        System.out.println("Este asiento ya está libre");
                    }
                    break;

                case 3: //Muestra los asientos disponibles
                    System.out.println("Asientos disponibles (fila-asiento)");

                    //recorrer la matriz
                    //iniciamos con las filas, ya que las filas tienen columnas
                    for (int i = 0; i < 5 ; i++) {
                        // for para recorrer las columnas
                        for (int j = 0; j < 10; j++) {
                            //mostrar asientos disponibles
                            //saber que es false, osea los disponibles
                            if (!listaAsientos[i][j]){
                                System.out.println("("+(i+1)+"-"+(j+1)+")");
                            }
                        }
                    }
                    break;

                case 4: //Contabilizar el total de asientos ocupados y disponibles.
                    int ocupados = 0, disponibles = 0;

                    //for each, creo la variable iteradora que recorra la matriz(tipo boolean)

                    for (boolean[] filaAsientos: listaAsientos) {
                        for ( boolean asientoOcupado: filaAsientos){

                            if (asientoOcupado){
                                ocupados++;
                            }else {
                                disponibles++;
                            }
                        }

                    }
                    System.out.println("Total de asientos ocupados: " + ocupados);
                    System.out.println("Total de asientos disponibles: " + disponibles);

                    break;
            }

        }while (opcion != 5);

    }
}
