import java.util.Scanner;

class Producto {
    private String nombre;
    private double precio;

    public Producto(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }

    public void mostrarInfo() {
        System.out.println("Producto: " + nombre + " | Precio: S/ " + precio);
    }
}

class Vendedor {
    private String nombre;

    public Vendedor(String nombre) {
        this.nombre = nombre;
    }

    public Producto crearProducto(String nombre, double precio) {
        return new Producto(nombre, precio);
    }
}

class Comprador {
    private String nombre;

    public Comprador(String nombre) {
        this.nombre = nombre;
    }

    public Pedido hacerPedido(Producto producto, String direccionEntrega) {
        return new Pedido(this, producto, direccionEntrega);
    }

    public String getNombre() {
        return nombre;
    }
}

class Pedido {
    private Comprador comprador;
    private Producto producto;
    private String direccionEntrega;

    public Pedido(Comprador comprador, Producto producto, String direccionEntrega) {
        this.comprador = comprador;
        this.producto = producto;
        this.direccionEntrega = direccionEntrega;
    }

    public void mostrarInfo() {
        System.out.println("Pedido de " + comprador.getNombre() +
                " | Producto: " + producto.getNombre() +
                " | Precio: S/ " + producto.getPrecio() +
                " | Dirección: " + direccionEntrega);
    }
}

class Repartidor {
    private String nombre;

    public Repartidor(String nombre) {
        this.nombre = nombre;
    }

    public void entregarPedido(Pedido pedido) {
        System.out.println("El repartidor " + nombre + " está entregando el pedido...");
        System.out.println("✅ Pedido entregado con éxito.");
    }
}

class Rappi {
    public void recibirPedido(Pedido pedido) {
        System.out.println("📦 Pedido recibido en Rappi:");
        pedido.mostrarInfo();
    }

    public void asignarRepartidor(Pedido pedido, Repartidor repartidor) {
        System.out.println("🚴 Asignando repartidor...");
        repartidor.entregarPedido(pedido);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Crear vendedor
        System.out.print("Ingrese el nombre del vendedor: ");
        Vendedor vendedor = new Vendedor(sc.nextLine());

        // Crear producto
        System.out.print("Ingrese el nombre del producto: ");
        String nombreProd = sc.nextLine();
        System.out.print("Ingrese el precio del producto: ");
        double precioProd = sc.nextDouble();
        sc.nextLine(); // limpiar buffer
        Producto producto = vendedor.crearProducto(nombreProd, precioProd);

        // Crear comprador
        System.out.print("Ingrese el nombre del comprador: ");
        Comprador comprador = new Comprador(sc.nextLine());

        // Crear pedido
        System.out.print("Ingrese la dirección de entrega: ");
        String direccion = sc.nextLine();
        Pedido pedido = comprador.hacerPedido(producto, direccion);

        // Crear repartidor
        System.out.print("Ingrese el nombre del repartidor: ");
        Repartidor repartidor = new Repartidor(sc.nextLine());

        // Gestionar pedido en Rappi
        Rappi rappi = new Rappi();
        rappi.recibirPedido(pedido);
        rappi.asignarRepartidor(pedido, repartidor);

        sc.close();
    }
}
