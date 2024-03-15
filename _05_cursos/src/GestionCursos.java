import java.util.ArrayList;
import java.util.Scanner;

public class GestionCursos {
    //atributos
    private ArrayList<Curso> listaCursos;

    //constructor
    public GestionCursos (){
        this.listaCursos = new ArrayList<>();
    }
    //methods
    public void agregarCursos(Scanner objScanner){
        System.out.println("Ingrese el nombre del curso");
        String nombre = objScanner.next();
        System.out.println("Ingrese el código del curso");
        String codigo = objScanner.next();

        for (Curso curso : this.listaCursos){
            if (curso.getCodigo().equalsIgnoreCase(codigo)){
                System.out.println("El código ingresado ya existe");
                return;
            }
        }
        Curso curso = new Curso(codigo, nombre);

        this.listaCursos.add(curso);
        System.out.println("El curso ha sido agregado satisfactoriamente");
    }
    public boolean listarCurso(){
        if (this.listaCursos.isEmpty()){
            System.out.println("No existen cursos registrados");
            return false;
        } else {
            for (Curso curso : this.listaCursos){
                System.out.println(listaCursos.toString());
                //verificar que el array list del tostring tambien tenga el to String
            }
            return true;
        }
    }

    public void eliminarCurso(Scanner objScanner){
        this.listarCurso();
        System.out.println("Ingrese el código ");
        String codigo = objScanner.next();
        if (this.listaCursos.removeIf(curso -> curso.getCodigo() == codigo)){
            System.out.println("El curso ha sido removido");
        }else {
            System.out.println("El código ingresado no se encuentra");
        }
    }

    public Curso buscarCodigo(String code){
        for (Curso curso: this.listaCursos){
            if (curso.getCodigo().equalsIgnoreCase(code)){
                return curso;
            }
        }
        return null;
    }

    //getters and setters

    //to string


}
