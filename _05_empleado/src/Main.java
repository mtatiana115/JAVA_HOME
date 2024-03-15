import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Empleado empleado = new Empleado();
        GestionEmpleados gestionEmpleados = new GestionEmpleados();
        EmpleadoPermanente empleadoPermanente = new EmpleadoPermanente();
        EmpleadoTemporal empleadoTemporal = new EmpleadoTemporal();


        Scanner objScanner = new Scanner(System.in);
        int option = 0;
    do {
        try {
            System.out.println("""
                    MENÚ DE OPCIONES:
                    1. Añadir empleado.
                    2. Eliminar empleado.
                    3. Mostrar empleados.
                    4. EXIT.
                    """);
            option = objScanner.nextInt();

            switch (option){
                case 1 :
                    System.out.println("Ingrese el nombre del empleado");
                    String nombre = objScanner.next();
                    System.out.println("Ingrese la edad del empleado");
                    int edad = objScanner.nextInt();
                    System.out.println("Ingrese el id del empleado");
                    int idEmpleado = objScanner.nextInt();
                    System.out.println("Ingrese el salario del empleado");
                    double salario = objScanner.nextDouble();
                    System.out.println("""
                            Tipo de empleado:
                            1. Permanente.
                            2. Temporal.
                            Selecciona una opcion:
                            """);
                    int tipoEmpleado = objScanner.nextInt();
                    if (tipoEmpleado == 1){
                        empleadoPermanente = new EmpleadoPermanente(nombre, edad, idEmpleado, salario);
                        gestionEmpleados.agregarEmpleado(empleadoPermanente);
                        System.out.println("Empleado agregado satisfactoriamente");
                    } else if (tipoEmpleado == 2){
                        empleadoTemporal = new EmpleadoTemporal(nombre, edad, idEmpleado, salario);
                        gestionEmpleados.agregarEmpleado(empleadoTemporal);
                        System.out.println("Empleado agregado satisfactoriamente");
                    }else {
                        System.out.println("Opción inválidad");
                    }

                    break;

                case 2 :
                    System.out.println("Ingrese el id del empleado");
                    idEmpleado = objScanner.nextInt();
                    if (gestionEmpleados.eliminarEmpleado(idEmpleado)){
                        System.out.println("El empleado ha sido eliminado");
                    }else {
                        System.out.println("El empleado no se encuentra");
                    }
                    break;

                case 3 :
                    System.out.println("A continuación se encuentran los empleados registrados");
                    gestionEmpleados.listarEmpleados();
                case 4:
                    System.out.println("Gracias, vuelva pronto.");
                    break;
            }
        }catch (Exception e){
            System.out.println("Caracteres inválidos");
            objScanner.next();
        }
    } while (option != 4);
    objScanner.close();
    }

}
