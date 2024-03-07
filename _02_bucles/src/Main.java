import javax.swing.*;
import java.sql.SQLOutput;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        /* 1. Contador Simple: Escribe un programa que utilice un bucle for para contar del 1 al 10 e
        imprimir cada número en la consola.*/

        //comentar ctrl + shift + /
        //shift + f10
        // cambiar de linea => ctrl + shift + flechaArriba

        /*for (int i= 1; i<=10; i++){
            System.out.println(i);
        }*/





        /* 2. Suma de Números: Utiliza un bucle while para sumar los números del 1 al 100 e imprimir
        el resultado.*/

/*        int contador = 0;
        int sumaTotal = 0;

        while (contador <= 100){
            sumaTotal += contador; donde i (condicion), como aumentará i
            contador++;
        }
        System.out.println(sumaTotal);*/





        // 3. Tabla 1 - 10
        //ciclo for  => recibe 3 parámetros, contador,

        /*for (int i = 1; i < 11; i++) {
            System.out.println("\nTabla del " +i);

            for (int j= 1; j<=10; j++){
                System.out.println(i+"x"+j+" = "+ (i*j));
            }
        }*/





        /* 9. Sistema de Menú Interactivo: Desarrolla un sistema de menú interactivo que muestre
        diferentes opciones al usuario (por ejemplo, 1. Ver saldo 2. Depositar dinero 3. Retirar
        dinero 4. Salir). Utiliza un bucle para permitir al usuario interactuar con el menú hasta que
        elija salir.*/

        String option;
        double saldo = 0;

        /*do {
            option = JOptionPane.showInputDialog(null, "\n\n MENÚ DE OPCIONES\n" +
                    "1. Ver saldo\n" +
                    "2. Depositar dinero\n" +
                    "3. Retirar\n" +
                    "4. Salir\n\n" +
                    "Ingrese una opción: "
            );

            switch (option) {

                //JOptionPane siempre devuelve uun string por eso los casos van entre comillas
                //JOptionPane puede ser showMessageDialog or showInputDialog

                case "1":
                    JOptionPane.showMessageDialog(null, "Tu saldo hasta el momento es: $" + saldo);
                    break;
                case "2":
                    String precioString = JOptionPane.showInputDialog(null, "Ingrese el valor a depositar \n");
                    double precio = Double.parseDouble(precioString);

                    //convertir o parsear el string a double usando la clase Double
                    try {


                        if (precio>0){
                            saldo += precio;

                            JOptionPane.showMessageDialog(null, "Dinero ingresado correctamente!, \nTu saldo es:" + saldo);
                        } else {
                            JOptionPane.showMessageDialog(null,"Los caracteres ingresados no son válidos");
                        }

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Los caracteres ingresados no son válidos");
                    }

                    break;

                case "3":
                    try {
                        double valorRetirar= Double.parseDouble(JOptionPane.showInputDialog(null,"Ingresa el valor a retirar"));

                        if(valorRetirar <= saldo && valorRetirar > 0){
                            saldo -= valorRetirar;
                            JOptionPane.showMessageDialog(null,"Retiro correcto! \n Tu saldo es: " + saldo);
                        } else {
                            JOptionPane.showMessageDialog(null,"Saldo insuficiente");
                        }

                    }catch (Exception e){
                        JOptionPane.showMessageDialog(null, "Los caracteres ingresados no son válidos");
                    }
                    break;

                default:
                    JOptionPane.showMessageDialog(null,"Los caracteres ingresados no son válidos");
            }

        } while (!option.equals("4"));*/





        /* 4. Suma de Números Pares: Escribe un programa que sume solo los números pares del 1 al
        100 usando un bucle for y luego imprime el resultado.
         */
        /*int sumaPares = 0;
        for (int i = 0; i < 101; i+=2) {
            sumaPares += i;
        }
        System.out.println("La suma de números pares del 1 al 100 es: " + sumaPares);*/





        /* 5. Validación de Entrada de Usuario: Escribe un programa que solicite al usuario que
        ingrese su edad. Si el usuario ingresa un valor que no es un número válido o está fuera de
        un rango razonable (por ejemplo, 0-120), el programa debe pedirle al usuario que intente de
        nuevo. Utiliza un bucle para seguir solicitando la entrada hasta que sea válida.
         */

        /*int age = 0;
        do{
        age = Integer.parseInt(JOptionPane.showInputDialog(null,"What is your age?"));
        } while (age < 0 || age >120);

        System.out.println("Thanks! you are " + age + " years old");*/




        /*6. Verificar un Número Primo: Escribe un programa que utilice un bucle para verificar si un
        número dado es primo o no.*/
        /*String primeNum;

        do {
             primeNum = JOptionPane.showInputDialog(null,"Enter a number");
             int primeNumR = Integer.parseInt(primeNum);
             for (int i = 2; i <= Math.sqrt(primeNumR) + 1; i++){
                 if (primeNumR % i == 0){
                     JOptionPane.showMessageDialog(null,"Your number is not a prime");
                     break;
                 } else {
                     JOptionPane.showMessageDialog(null,"Your number is prime");
                     break;
                 }
             }

        } while (!primeNum.equals("0"));*/





        //7. Juego de Adivinanzas: Crea un pequeño juego de adivinanzas donde el usuario debe
        //adivinar un número generado aleatoriamente. Utiliza un bucle do-while para permitir al
        //usuario seguir intentando hasta que adivine el número.

        /*Random random = new Random();
        int numRandom = random.nextInt(100) +1;
        System.out.println(numRandom);
        int numUser = 0;

        do {
            try {
                numUser = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número a adivinar"));
                if (numUser == numRandom){
                    JOptionPane.showMessageDialog(null,"Haz adivinado!");
                } else {
                    JOptionPane.showMessageDialog(null,"Intenta nuevamente");
                }
            } catch (Exception e){
              JOptionPane.showMessageDialog(null,"Ingrese caracter válido");
            };
        } while (numRandom != numUser);*/


        /* Cálculo de Interés Compuesto: Crea un programa que calcule el crecimiento de una
        inversión bajo un esquema de interés compuesto. El usuario debe poder ingresar el capital
        inicial, la tasa de interés anual y el número de años. Utiliza un bucle para calcular y mostrar
        el saldo de la inversión al final de cada año. */

        double initialCap =  0;
        double annualRate = 0;
        double years = 0;

        try {
            initialCap = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el capital inicial de su inversión"));
            annualRate = Double.parseDouble(JOptionPane.showInputDialog("Ingrese la tasa de interés anual"));
            years = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el número de años"));



            for (int i = 1; i <= years; i++) {
                double investmentBalance = initialCap * Math.pow((1 + annualRate / 100), i);
                JOptionPane.showMessageDialog(null,"Saldo de la inversion en el año " + i +" = " + investmentBalance);
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"caracteres inválidos");
        }


    }
}