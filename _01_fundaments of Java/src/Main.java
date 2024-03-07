import javax.swing.JOptionPane;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        JOptionPane pane = new JOptionPane();

        System.out.println("""
                    Hello and welcome, type the number for the operation you want to make\s
                     1.Calculator\s
                     2.Age verifier\s
                     3.Unit converter\s
                     4.IMC\s
                     5.number classifier\s
                     6.Days in a month\s
                     7.Average calculation\s
                     8.leap-year calculator\s
                     9.Tip calculator\s
                     10.Order three numbers\s
                     11.Horoscope generator\s
                     12.Taxi fare\s
                     13.Temperature converter\s
                     14.Qualification System.\s
                     Choose an option:"""
        );

        int menu = new Scanner(System.in).nextInt();

        switch (menu) {
            case 1:
                Calculator calculators = new Calculator(pane);
                calculators.startCalculator();
                break;
            case 2:
                VerifyAge verifyAge = new VerifyAge();
                verifyAge.ageVerifier();
                break;
        }

    }
}
