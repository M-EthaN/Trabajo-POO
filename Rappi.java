import java.util.Scanner;

// Clase Producto
class Producto {
    private String nombre;
    private double precio;
    private int id;

    public Producto(int id, String nombre, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }

    public void mostrarInfo() {
        System.out.println("ID Producto: " + id + " | " + nombre + " | Precio: S/ " + precio);
    }
}

// Clase Vendedor
class Vendedor {
    private String nombre;
    private int id;

    public Vendedor(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }

    public Producto crearProducto(int idProd, String nombre, double precio) {
        System.out.println("👨‍🍳 Vendedor " + nombre + " (ID: " + id + ") creó un nuevo producto.");
        return new Producto(idProd, nombre, precio);
    }
}

// Clase Comprador
class Comprador {
    private String nombre;
    private int id;

    public Comprador(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }

    public Pedido hacerPedido(Producto producto, String direccionEntrega) {
        System.out.println("🧾 Comprador " + nombre + " (ID: " + id + ") realizó un pedido.");
        return new Pedido(this, producto, direccionEntrega);
    }
}

// Clase Pedido
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
        System.out.println("Pedido de " + comprador.getNombre() + " (ID: " + comprador.getId() + ")");
        System.out.println("Producto: " + producto.getNombre() + " (ID: " + producto.getId() + ")");
        System.out.println("Precio: S/ " + producto.getPrecio());
        System.out.println("Dirección: " + direccionEntrega);
    }
}

// Clase Repartidor
class Repartidor {
    private String nombre;
    private int id;

    public Repartidor(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public void entregarPedido(Pedido pedido) {
        System.out.println("🚴 Repartidor " + nombre + " (ID: " + id + ") está entregando el pedido...");
        System.out.println("✅ Pedido entregado con éxito.");
    }
}

// Clase Rappi (gestiona pedidos)
class Rappi {
    public void recibirPedido(Pedido pedido) {
        System.out.println("📦 Pedido recibido en Rappi:");
        pedido.mostrarInfo();
    }

    public void asignarRepartidor(Pedido pedido, Repartidor repartidor) {
        System.out.println("🕹️ Asignando repartidor...");
        repartidor.entregarPedido(pedido);
    }
}

// Clase principal
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Crear vendedor
        System.out.print("Ingrese el ID del vendedor: ");
        int idVend = sc.nextInt();
        sc.nextLine();
        System.out.print("Ingrese el nombre del vendedor: ");
        Vendedor vendedor = new Vendedor(idVend, sc.nextLine());

        // Crear producto (solo vendedor puede hacerlo)
        System.out.print("Ingrese el ID del producto: ");
        int idProd = sc.nextInt();
        sc.nextLine();
        System.out.print("Ingrese el nombre del producto: ");
        String nombreProd = sc.nextLine();
        System.out.print("Ingrese el precio del producto: ");
        double precioProd = sc.nextDouble();
        sc.nextLine();

        Producto producto = vendedor.crearProducto(idProd, nombreProd, precioProd);
        producto.mostrarInfo();

        // Crear comprador
        System.out.print("Ingrese el ID del comprador: ");
        int idComp = sc.nextInt();
        sc.nextLine();
        System.out.print("Ingrese el nombre del comprador: ");
        Comprador comprador = new Comprador(idComp, sc.nextLine());

        // Crear pedido
        System.out.print("Ingrese la dirección de entrega: ");
        String direccion = sc.nextLine();
        Pedido pedido = comprador.hacerPedido(producto, direccion);

        // Crear repartidor
        System.out.print("Ingrese el ID del repartidor: ");
        int idRep = sc.nextInt();
        sc.nextLine();
        System.out.print("Ingrese el nombre del repartidor: ");
        Repartidor repartidor = new Repartidor(idRep, sc.nextLine());

        // Gestionar pedido
        Rappi rappi = new Rappi();
        rappi.recibirPedido(pedido);
        rappi.asignarRepartidor(pedido, repartidor);

        sc.close();
    }
}

