import javax.swing.*;
import java.util.ArrayList;

public class Ejercicio05 {
    public static void main(String[] args) {
        /*lista de reproducción musical*/

        //ARRAY => Tiene una dimensión fija
        //ARRAY LIST => Es dinámico, se desconoce la dimensión

        /* crear un array list*/
        //1. crear variable que contendrá la lista => ArrayList<E>, donde E es un parámetro genérico
        //2. Los parámetros de apertura y cierre en el constructor son para invocar el método de array list

        //Creación de la lista de canciones
        ArrayList<String> playList = new ArrayList<>();

        int option = 0;
        int cancionActual = 0;

        //la clase Integer convierte de string a entero con el método parseInt(), ya que JOption pane solo devuelve string
        do {

            try {
                option = Integer.parseInt(JOptionPane.showInputDialog(" PLAYlIST \n"+
                        "1. Agregar canción \n"+
                        "2. Remover canción \n"+
                        "3. Mostrar canción actual y siguientes en la lista \n"+
                        "4. Saltar a la siguiente canción \n"+
                        "5. Salir \n"+
                        "Ingrese una opción: "));

                switch (option){
                    case 1: //Añadir una canción

                        //Pedimos la nueva canción al usuario
                        String nuevaCancion = JOptionPane.showInputDialog("Ingrese el nombre de la canción a agregar");

                        //Agregamos la nueva canción a la playList
                        //1. nombre del arrayList.metodo add
                        //Guardar la canción en minúscula
                        playList.add(nuevaCancion.toLowerCase());

                        JOptionPane.showMessageDialog(null,nuevaCancion +" Agregada correctamente.");
                        break;
                    case 2: //Eliminar una canción de la playList

                        //Pedimos al usuario el nombre de la canción a eliminar
                        String cancionELiminar = JOptionPane.showInputDialog("Ingrese el nombre de la canción a eliminar");

                        //Eliminar la canción que tenga ese nombre
                        // el métodoto remove puede eliminar por indice o por valor, ambos eliminan la primera coincidencia
                        if (playList.remove(cancionELiminar.toLowerCase())){
                            JOptionPane.showMessageDialog(null,cancionELiminar + " Eliminada correctamente");
                        }else {
                            JOptionPane.showMessageDialog(null, cancionELiminar + " No se encuentra en la playList");
                        }
                        break;

                    case 3: //Mostrar la canción actual y la siguiente en la lista
                        //Preguntar si la playList está vacía
                        if(playList.isEmpty()){
                            JOptionPane.showMessageDialog(null,"La playList está vacía");
                        }else {
                            //Creamos una variable para guardar en texto todas las canciones mayores a la actual
                            String listaTotal = "";
                            //Agregamos la canción actual
                            listaTotal += "Canción actual:\n"+playList.get(cancionActual)+"\n"+"\n\nSiguientes en la lista:\n";

                            //recorrer toda la lista y por cada canción mostrar
                            //Recorrer un arrayList con for
                            for (int i = cancionActual; i < playList.size()-1; i++) {
                                //llenar, por cada iteración concatenamos a la variable listaTotal
                                listaTotal += "play:"+playList.get(i+1)+"\n";
                            }
                            JOptionPane.showMessageDialog(null, listaTotal);
                        }
                        break;
                    case 4: //Saltar a la siguiente canción
                        //validar que si haya una siguiente canción
                        if(cancionActual+1 < playList.size()){
                            cancionActual++;
                            JOptionPane.showMessageDialog(null,playList.get(cancionActual)+" Reproducida correctamente");
                        }else {
                            JOptionPane.showMessageDialog(null,"playList finalizada");
                            cancionActual=0;
                        }
                        break;
                }

            } catch (Exception e){
                JOptionPane.showMessageDialog(null,"Caracteres no válidos");
            }


        }while (option !=5);
    }
}
