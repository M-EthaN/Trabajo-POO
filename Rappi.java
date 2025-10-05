// Clase Producto
class Producto {
    private String nombre;
    private double precio;

    public Producto(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public void mostrarInfo() {
        // Mostrar información del producto
    }

    // Getters y setters
}

// Clase Vendedor
class Vendedor {
    private String nombre;

    public Vendedor(String nombre) {
        this.nombre = nombre;
    }

    public Producto crearProducto(String nombre, double precio) {
        // Retorna un nuevo producto
        return null;
    }
}

// Clase Comprador
class Comprador {
    private String nombre;

    public Comprador(String nombre) {
        this.nombre = nombre;
    }

    public Pedido hacerPedido(Producto producto, String direccionEntrega) {
        // Retorna un nuevo pedido con el producto elegido
        return null;
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
        // Mostrar detalles del pedido
    }
}

// Clase Repartidor
class Repartidor {
    private String nombre;

    public Repartidor(String nombre) {
        this.nombre = nombre;
    }

    public void entregarPedido(Pedido pedido) {
        // Simula entrega del pedido
    }
}

// Clase Rappi (gestiona pedidos y repartidores)
class Rappi {
    public void recibirPedido(Pedido pedido) {
        // Guardar pedido
    }

    public void asignarRepartidor(Pedido pedido, Repartidor repartidor) {
        // Asignar pedido a un repartidor
    }
}

// Clase principal
public class Main {
    public static void main(String[] args) {
        // Crear vendedor y producto
        Vendedor vendedor = new Vendedor("Restaurante Don Pepe");
        Producto producto = vendedor.crearProducto("Hamburguesa doble", 20.0);

        // Crear comprador
        Comprador comprador = new Comprador("Laura");

        // Comprador hace pedido
        Pedido pedido = comprador.hacerPedido(producto, "Av. Principal 456");

        // Crear repartidor y Rappi
        Repartidor repartidor = new Repartidor("Carlos");
        Rappi rappi = new Rappi();

        // Rappi gestiona pedido
        rappi.recibirPedido(pedido);
        rappi.asignarRepartidor(pedido, repartidor);

        // Repartidor entrega pedido
        repartidor.entregarPedido(pedido);
    }
}

