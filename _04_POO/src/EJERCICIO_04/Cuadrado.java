package EJERCICIO_04;

public class Cuadrado extends Rectangulo {

    public Cuadrado(double lado){
        super(lado, lado);
    }
    //No es necesario sobrescribir calcularArea(); porque al implementar de rectangulo ya estamos obteniendo la funcionalidad
}
