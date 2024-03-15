package EJERCICIO_04;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {


        //TEST 1: Calcular el {srea de un c{irculo
        Circulo objCirculo = new Circulo(10);
        System.out.println("El área del círculo es: "+ objCirculo.calcularArea());

        //TEST 2: Calcular el {area de un rectángulo
        Rectangulo objRectangulo = new Rectangulo(20,40);
        System.out.println("El área del rectángulo es: "+ objRectangulo.calcularArea());

        //TEST 3: Calcular área de un cuadrado
            Cuadrado objCuadrado = new Cuadrado(40);
        System.out.println("El área del cuadrado es: "+ objCuadrado.calcularArea());

    }
}