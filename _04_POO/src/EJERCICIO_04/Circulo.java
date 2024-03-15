package EJERCICIO_04;

public class Circulo extends Figura{

    private double radio;
    //clic dcho generate constructor
    public Circulo(double radio) {

        this.radio = radio;
    }

    @Override
    public double calcularArea(){
      return  Math.PI * this.radio*this.radio;
    }
}
