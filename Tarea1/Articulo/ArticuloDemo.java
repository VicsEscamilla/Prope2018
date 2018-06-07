import java.util.concurrent.ThreadLocalRandom;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

class Articulo {
    private double IVA;

    protected int id;
    protected String nombre;
    protected int cantidad;
    protected double precio;
    protected double descuento;
    protected String fechaRegistro;
    protected double ventaTotal;

    public String descripcion;

    public Articulo(int id, String nombre, double precio) {
        this.id = (id >= 1 && id <= 10000) ? id : randomID();
        this.nombre = nombre;
        setPrecio(precio);

        this.IVA = 0.16;
        this.descripcion = "";
        this.cantidad = 0;
        this.descuento = 0.0;
        this.fechaRegistro = "00/00/0000";
        this.ventaTotal = 0.0;
    }

    public Articulo(int id, String nombre, double precio,
            String descripcion, int cantidad, double descuento) {
        this(id, nombre, precio);
        this.descripcion = descripcion;
        setDescuento(descuento);
        registrarProductos(cantidad);
    }

    private int randomID() {
        return ThreadLocalRandom.current().nextInt(1, 10001);
    }

    public void setDescuento(double descuento) {
        if (descuento > 100)
            this.descuento = 100;
        else if (descuento < 0)
            this.descuento = 0;
        else
            this.descuento = descuento;
    }

    public void setDescuento(String descuento) {
        double porciento = Double.parseDouble(descuento.trim().replace("%",""));
        this.descuento = precio * porciento/100;
    }

    public void setPrecio(double precio) {
        this.precio = precio > 0 ? precio : 0;
    }

    public void registrarProductos(int cantidad) {
        this.cantidad = cantidad;
        Date fecha = new Date();
        DateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        this.fechaRegistro = fmt.format(fecha);
    }

    public boolean realizarVenta(int numProductos) {
        if (numProductos <= 0 || cantidad < numProductos)
            return false;

        ventaTotal += numProductos * getPrecioTotal();
        cantidad -= numProductos;
        return true;
    }

    public boolean realizarVenta() {
        return realizarVenta(1);
    }

    public double getPrecioTotal() {
        return precio + precio*IVA - descuento;
    }

    public String toString() {
        return String.format("ID: %d\n"               +
                             "Nombre: %s\n"           +
                             "Descripcion: %s\n"      +
                             "Cantidad: %d\n"         +
                             "Precio: %.2f\n"         +
                             "Descuento: %.2f\n"      +
                             "IVA: %.2f\n"            +
                             "Fecha Registro: %s\n"   +
                             "Venta Total: %.2f\n",
                             id, nombre, descripcion, cantidad, precio,
                             descuento, IVA, fechaRegistro, ventaTotal);
    }

    public void imprimirTodo() {
        System.out.println(this);
    }

    public double getVentaTotal() {
        return ventaTotal;
    }
}

public class ArticuloDemo {
    public static void main(String[] args) {
        Articulo lapiz = new Articulo(1, "lapiz", 5, "madera", 0, 0);
        Articulo libreta = new Articulo(2, "libreta", 12, "de cuadro", 5, 10);
        Articulo laptop = new Articulo(3, "laptop", 10000, "DELL", 2, 55);
        Articulo libro = new Articulo(4, "libro", 356.25, "POO", 8, 12);

        JOptionPane.showMessageDialog(null, lapiz);
        JOptionPane.showMessageDialog(null, libreta);
        JOptionPane.showMessageDialog(null, laptop);
        JOptionPane.showMessageDialog(null, libro);

        JOptionPane.showMessageDialog(null, "Cambiar precio de los lápices por 100");
        lapiz.setPrecio(100);

        JOptionPane.showMessageDialog(null, "Venta de 1 laptop");
        laptop.realizarVenta();

        JOptionPane.showMessageDialog(null, "Cambiar precio de libro por 800");
        libro.setPrecio(800);

        JOptionPane.showMessageDialog(null, "Registrar 1000 lápices");
        lapiz.registrarProductos(1000);

        JOptionPane.showMessageDialog(null, lapiz);
        JOptionPane.showMessageDialog(null, libreta);
        JOptionPane.showMessageDialog(null, laptop);
        JOptionPane.showMessageDialog(null, libro);

        JOptionPane.showMessageDialog(null, "Venta de 800 lápices");
        lapiz.realizarVenta(800);

        JOptionPane.showMessageDialog(null, "Venta de 5 libretas");
        libreta.realizarVenta(5);

        JOptionPane.showMessageDialog(null, "Venta de 1 libro");
        libro.realizarVenta();

        JOptionPane.showMessageDialog(null, "Cambiar descripción de la libreta por 'de dibujo'");
        libreta.descripcion = "de dibujo";

        JOptionPane.showMessageDialog(null, "Cambiar el descuento de la laptop por '95%'");
        libreta.setDescuento("95%");

        JOptionPane.showMessageDialog(null, "Calcular el precio total de la laptop");
        JOptionPane.showMessageDialog(null, laptop.getPrecioTotal());

        JOptionPane.showMessageDialog(null, "Registrar 15 libros");
        libro.registrarProductos(15);

        JOptionPane.showMessageDialog(null, lapiz);
        JOptionPane.showMessageDialog(null, libreta);
        JOptionPane.showMessageDialog(null, laptop);
        JOptionPane.showMessageDialog(null, libro);

        System.out.println(lapiz);
        System.out.println(libreta);
        System.out.println(laptop);
        System.out.println(libro);

        Articulo arts[] = new Articulo[10];
        for (int i=0; i<10; ++i){
            arts[i] = new Articulo(100, "pluma", 50.9);
            System.out.println(arts[i]);
        }

    }
}
