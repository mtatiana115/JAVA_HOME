import javax.swing.JOptionPane;

//clase + instancia + constructor
//clase
public class Calculator {
    public final JOptionPane pane;

//constructor
    public Calculator(JOptionPane pane) {
        this.pane = pane;
    }

    public void startCalculator() {
        String option = JOptionPane.showInputDialog(null, "MENU DE OPERACIONES \n 1. Sumar \n 2. Restar \n 3. Multiplicar \n 4. Dividir");

        // Obtener los dos números a operar
        String num1 = JOptionPane.showInputDialog(null, "Ingrese el primer número");
        String num2 = JOptionPane.showInputDialog(null, "Ingrese el segundo número");

        // Convertir de string a número decimal
        double a = Double.parseDouble(num1);
        double b = Double.parseDouble(num2);

        switch (option) {
            case "1":
                JOptionPane.showMessageDialog(null, a + " + " + b + " = " + (a + b));
                break;

            case "2":
                JOptionPane.showMessageDialog(null, a + " - " + b + " = " + (a - b));
                break;

            case "3":
                JOptionPane.showMessageDialog(null, a + " * " + b + " = " + (a * b));
                break;

            case "4":
                if (b == 0) {
                    JOptionPane.showMessageDialog(null, "El número b debe ser diferente de cero");
                } else {
                    JOptionPane.showMessageDialog(null, a + " / " + b + " = " + (a / b));
                }
                break;  // ¡No olvides el break aquí!

            default:
                JOptionPane.showMessageDialog(null, "Opción no válida");
        }
    }
}
