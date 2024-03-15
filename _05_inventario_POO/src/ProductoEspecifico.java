public class ProductoEspecifico extends Producto {
    private String categoria, marca;


    public ProductoEspecifico(int id, String nombre, double precio, String categoria, String marca) {
        super(id, nombre, precio);
        //super activa el metodo constructor del padre, añado todos los atributos
        this.categoria = categoria;
        this.marca = marca;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Override
    public String toString() {
        return "ProductoEspecifico{" +
                "categoria='" + categoria + '\'' +
                ", marca='" + marca + '\'' +
                "} " + super.toString();
    }
}
