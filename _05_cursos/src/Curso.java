import java.util.ArrayList;
import java.util.Scanner;

public class Curso {
    //atributos
    private String codigo;
    private  String nombre;
    private static int idEst = 0;

    //El array list recibe la clase Estudiantes (creo la clase estudiante)
    private ArrayList<Estudiante> listaEstudiantes = new ArrayList<>();

    //constructor
    public Curso(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.listaEstudiantes = new ArrayList<>();
    }

    public Curso(){};

    //methods
    public void agregarEstudiante(Scanner objScanner){
        System.out.println("Ingrese el nombre del estudiante");
        String nombre = objScanner.next();
        System.out.println("Ingrese el email del estudiante");
        String email = objScanner.next();

        Estudiante estudiante = new Estudiante(idEst, nombre, email);
        idEst++;

        if (this.listaEstudiantes.add(estudiante)){
            System.out.println("El estudiantes ha sido agregado correctamente");
        }else {
            System.out.println("El estudiante no se ha ingresado");
        }
    }

    public void listarEstudiantes(){
        if (this.listaEstudiantes.isEmpty()){
            System.out.println("No hay estudiantes inscritos");
        } else {
            System.out.println("Los estudiantes actuales del curso son: ");
            for (Estudiante estudiante : this.listaEstudiantes){
                System.out.println(estudiante.toString());
            }
        }
    }

    public void removerEstudiante (Scanner objScanner){
        this.listarEstudiantes();
        System.out.println("Ingrese el id del estudiante");
        int id = objScanner.nextInt();
        if (this.listaEstudiantes.removeIf(estudiante -> estudiante.getId() == id)){
            System.out.println("El estudiante ha sido eliminado");
        }else {
            System.out.println("No se encuentra el id del estudiante ingresado");
        }

    }


    //getters and setters

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Estudiante> getListaEstudiantes() {
        return listaEstudiantes;
    }

    public void setListaEstudiantes(ArrayList<Estudiante> listaEstudiantes) {
        this.listaEstudiantes = listaEstudiantes;
    }


    //To String

    @Override
    public String toString() {
        return "Curso{" +
                "codigo=" + codigo +
                ", nombre='" + nombre + '\'' +
                ", listaEstudiantes=" + listaEstudiantes.toString() +
                '}';
    }
}
