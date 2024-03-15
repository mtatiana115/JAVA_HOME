import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Instanciar clases
        Curso curso = new Curso();
        GestionCursos gestionCursos = new GestionCursos();

        Scanner objScanner = new Scanner(System.in);
        int option = 0;

        do {
            try {
                System.out.println("""
                        MENU DE OPCIONES:
                        1. Administrar cursos
                        2. Administrar estudiantes
                        3. Salir
                        Selecciona una opción
                        """);
                option= objScanner.nextInt();

                switch (option){

                    case 1:
                        int option2 = 0;

                        do {
                            System.out.println("""
                                
                                MENU PARA ADMINISTRAR CURSOS
                                1. Agregar un curso
                                2. Listar los cursos
                                3. Eliminar un curso
                                4. volver al menú anterior
                                Seleccione una opción:
                                """);
                            option2 = objScanner.nextInt();

                            switch (option2){
                                case 1:
                                    gestionCursos.agregarCursos(objScanner);
                                    break;
                                case 2:
                                    gestionCursos.listarCurso();
                                    break;
                                case 3:
                                    gestionCursos.eliminarCurso(objScanner);
                                    break;
                            }
                        }while (option2 !=4);

                        break;

                    case 2:
                        int option3 = 0;

                        do {
                            System.out.println("""
                                
                                MENU PARA ADMINISTRAR ESTUDIANTES
                                1. Agregar un estudiante
                                2. Listar los estudiantes
                                3. Eliminar un estudiante
                                4. volver al menú anterior
                                Seleccione una opción:
                                """);
                            option3 = objScanner.nextInt();

                            switch (option3){
                                case 1:
                                    if (!gestionCursos.listarCurso()){
                                        break;
                                    }
                                    System.out.println("Ingrese el codigo del curso al que desea añadir el estudiantes");
                                    String codigo = objScanner.next();
                                    curso = gestionCursos.buscarCodigo(codigo);
                                    if ( curso == null){
                                        System.out.println("El curso no fue encontrado");
                                    } else {
                                        curso.agregarEstudiante(objScanner);
                                    }
                                    break;
                                case 2:
                                    curso.listarEstudiantes();
                                    break;
                                case 3:
                                    if (!gestionCursos.listarCurso()){
                                        break;
                                    }
                                    curso.removerEstudiante(objScanner);
                                    break;
                            }
                        }while (option3 !=4);
                        break;

                    case 3:
                        System.out.println("Gracias vuelva pronto");
                        break;
                }
            }catch (Exception e){
                System.out.println("Caracteres inválidos " + e);
            }
        }while (option !=3);
    }
}
