import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Inventario objInventario = new Inventario();

/*        Producto pro1 = new Producto(1,"Lápiz",2000);
        Producto pro2 = new Producto(2,"Cuaderno",7000);

        objInventario.agregarProducto(pro1);
        objInventario.agregarProducto(pro2);

        System.out.println("Lista de productos");
        objInventario.listarProductos();

        objInventario.eliminarProducto(1);
        System.out.println("Listar despues de eliminar el producto");
        objInventario.listarProductos();

        System.out.println(objInventario.buscarPorNombre("Cuaderno"));*/
        Producto producto = new Producto();
        Inventario inventario = new Inventario();

        Scanner objScanner = new Scanner(System.in);
        int option = 0;

        do {
            try {
                System.out.println("""
                        MENÚ DE OPCIONES:
                        1. Añadir un producto
                        2. Eliminar un producto
                        3. Listar los productos
                        4. Buscar un producto
                        5. Salir
                        """);
                option = objScanner.nextInt();
                switch (option){
                    case 1:
                        System.out.println("Ingrese el ID del producto");
                        int id = objScanner.nextInt();
                        System.out.println("Ingrese el nombre del producto");
                        String nombre = objScanner.next();
                        System.out.println("Ingrese el precio del producto");
                        double precio = objScanner.nextDouble();

                        producto = new Producto(id, nombre, precio);
                        inventario.agregarProducto(producto);
                        System.out.println("Producto agregado satisfactoriamente!");
                        break;

                    case 2:
                        System.out.println("Ingrese el Id del producto que desea eliminar");
                        id = objScanner.nextInt();
                        if (inventario.eliminarProducto(id)){
                            System.out.println("Producto eliminados satisfactoriamente");
                        }else {
                            System.out.println("El id no se encuentra registrado");
                        }
                        break;

                    case 3:
                        System.out.println("A continuación se encuentran los productos disponibles");
                        inventario.listarProductos();
                        break;

                    case 4:
                        System.out.println("Ingrese el nombre del producto");
                        nombre = objScanner.next();
                        System.out.println(inventario.buscarPorNombre(nombre).toString());
                        break;
                }
            }catch (Exception e){
                System.out.println("Caracteres no válidos");
                objScanner.next();
            }
        }while (option !=5);
        objScanner.close();
    }
}