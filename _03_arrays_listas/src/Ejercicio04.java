import javax.swing.*;
import java.util.ArrayList;

public class Ejercicio04 {
    public static void main(String[] args) {
        /* Planificador de Viajes: Escribe un programa para ayudar a planificar viajes por
        carretera. Los usuarios pueden ingresar varias ciudades que planean visitar en
        orden. Utiliza un arreglo de String para almacenar las ciudades. El programa debe
        ser capaz de:
         Añadir y remover ciudades del itinerario.
         Mostrar la lista completa de ciudades a visitar.
         (Opcional) Calcular la distancia total del viaje asumiendo distancias ficticias entre
        ciudades consecutivas.*/

        //Creación de la lista de canciones
        ArrayList<String> journey = new ArrayList<>();

        int option =0;


        do {
            try {
                option = Integer.parseInt(JOptionPane.showInputDialog("--- PLANIFICADOR DE VIAJES --- \n"+
                        "1. Añadir y remover ciudades del itinerario \n"+
                        "2. Mostrar la lista completa de ciudades a visitar \n"+
                        "3. calcular la distancia total del viaje \n"+
                        "4. Salir \n"+
                        "Ingrese una opción: "));

                switch (option) {
                    case 1: //añadir y remover ciudades del itinerario
                        do {
                            option = Integer.parseInt(JOptionPane.showInputDialog(" --- ACTUALIZAR ITINERARIO ---\n" +
                                    "1. Añadir una nueva ciudad al itinerario\n" +
                                    "2. Remover una ciudad del itinerario \n" +
                                    "3. Volver al menú principal \n" +
                                    "Ingrese una opción: "));

                            switch (option) {
                                case 1: //añadir ciudades

                                    String newCity = JOptionPane.showInputDialog("Ingrese el nombre de la ciudad que desea añadir");
                                    journey.add(newCity.toLowerCase());

                                    JOptionPane.showMessageDialog(null, newCity + " ha sido agregada correctamente a tu itinerario.");
                                    JOptionPane.showMessageDialog(null, "--- TU ITINERARIO: --- \n" + journey);
                                    break;

                                case 2: //Remover una ciudad

                                    String deleteCity = JOptionPane.showInputDialog("Ingrese el nombre de la ciudad que desea eliminar");

                                    if (journey.remove(deleteCity.toLowerCase())) {
                                        JOptionPane.showMessageDialog(null, deleteCity + " ha sido eliminada de tu itinerario");
                                        JOptionPane.showMessageDialog(null, "--- TU ITINERARIO: --- \n" + journey);
                                    } else {
                                        JOptionPane.showMessageDialog(null, deleteCity + " no se encuentra en tu itinerario de viaje");
                                    }
                                    break;
                            }

                        } while (option != 3);
                        break;

                    case 2: //Mostrar itinerario
                        JOptionPane.showMessageDialog(null, "Lista completa de ciudades a visitar...");
                        JOptionPane.showMessageDialog(null, "--- TU ITINERARIO: --- \n" + journey);
                        break;

                    case 3: //Distancia total del viaje
                        if (journey.isEmpty()){
                            JOptionPane.showMessageDialog(null,"Su itinerario se encuentra vacío");
                        } else {
                            double totalDistance = 0.0;

                            for (int i = 0; i < journey.size(); i++) {
                                try {
                                    double distance = Double.parseDouble(JOptionPane.showInputDialog("Ingrese la distancia en kilómetros que hay hasta la ciudad de: " + journey.get(i)));
                                    totalDistance += distance;

                                }catch (Exception e){
                                    JOptionPane.showMessageDialog(null, "Por favor, ingrese una distancia válida.");
                                    i--;
                                }
                            }
                            JOptionPane.showMessageDialog(null, "La distancia total del viaje es: " + totalDistance + " km");
                        }

                        break;
                }

            }catch (Exception e){
                JOptionPane.showMessageDialog(null,"Caracteres no válidos");
            }
        } while (option != 4);

    }
}
