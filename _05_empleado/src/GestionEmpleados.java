import java.util.ArrayList;

public class GestionEmpleados {
    //atributos
    private ArrayList<Empleado> empleados;

    //constructor
    public GestionEmpleados(){
        this.empleados = new ArrayList<>();
    }

    //methods
    public void agregarEmpleado (Empleado empleado){
        this.empleados.add(empleado);
    }

    public boolean eliminarEmpleado(int id){
        if(this.empleados.removeIf(empleado -> empleado.getIdEmpleado() == id)){
            return true;
        }
        return false;
    }

    public void  listarEmpleados(){
        for (Empleado empleado: this.empleados){
            System.out.println(empleado.toString());
        }
    }
}
