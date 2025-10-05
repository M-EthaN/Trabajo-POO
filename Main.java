import java.util.*;

// ======== CLASE PRODUCTO ========
class Producto {
    private String nombre;
    private double precio;
    private String id;
    private String vendedorNombre;

    public Producto(String id, String nombre, double precio, String vendedorNombre) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.vendedorNombre = vendedorNombre;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public String getVendedorNombre() { return vendedorNombre; }

    public void mostrarInfo() {
        System.out.println("[" + id + "] " + nombre + " - S/ " + precio + " (Puesto por " + vendedorNombre + ")");
    }
}

// ======== CLASE VENDEDOR ========
class Vendedor {
    private String nombre;
    private String id;

    public Vendedor(String nombre, String id) {
        this.nombre = nombre;
        this.id = id;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }

    public Producto crearProducto(String nombre, double precio, int contador) {
        String idProd = "P" + contador;
        System.out.println("✅ Producto agregado por " + this.nombre);
        return new Producto(idProd, nombre, precio, this.nombre);
    }
}

// ======== CLASE COMPRADOR ========
class Comprador {
    private String nombre;
    private String id;
    private ArrayList<Pedido> historialPedidos;

    public Comprador(String nombre, String id) {
        this.nombre = nombre;
        this.id = id;
        this.historialPedidos = new ArrayList<>();
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }

    public Pedido hacerPedido(Producto producto, String direccionEntrega) {
        Pedido p = new Pedido(this, producto, direccionEntrega);
        historialPedidos.add(p);
        System.out.println("🧾 Pedido creado por " + nombre);
        return p;
    }

    public void mostrarHistorial() {
        if (historialPedidos.isEmpty()) {
            System.out.println("📭 No tienes pedidos aún.");
            return;
        }
        System.out.println("\n=== HISTORIAL DE PEDIDOS ===");
        for (Pedido p : historialPedidos) {
            p.mostrarInfo();
            System.out.println("---------------------------");
        }
    }
}

// ======== CLASE PEDIDO ========
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
        System.out.println("Pedido de " + comprador.getNombre() + " (" + comprador.getId() + ")");
        System.out.println("Producto: " + producto.getNombre() + " | Precio: S/ " + producto.getPrecio() +
                " (Vendedor: " + producto.getVendedorNombre() + ")");
        System.out.println("Dirección: " + direccionEntrega);
    }
}

// ======== CLASE PRINCIPAL ========
public class Main {
    private static ArrayList<Producto> catalogo = new ArrayList<>();
    private static HashSet<String> idsUsados = new HashSet<>();
    private static int contadorProductos = 1;
    private static Scanner sc = new Scanner(System.in);
    private static Random random = new Random();

    public static void main(String[] args) {
        System.out.println("=== 🍔 Bienvenido a MiniRappi ===");

        while (true) {
            System.out.print("\nIngrese su ID para iniciar sesión (A# Vendedor | B# Comprador | N para nuevo usuario | X para salir): ");
            String id = sc.nextLine().trim().toUpperCase();

            if (id.equals("X")) {
                System.out.println("👋 Cerrando el sistema...");
                break;
            }

            if (id.equals("N")) {
                crearNuevoUsuario();
                continue;
            }

            if (id.startsWith("A")) {
                menuVendedor(id);
            } else if (id.startsWith("B")) {
                menuComprador(id);
            } else {
                System.out.println("❌ ID no válido. Intente de nuevo.");
            }
        }
        sc.close();
    }

    // ======== CREACIÓN DE NUEVO USUARIO ========
    private static void crearNuevoUsuario() {
        System.out.println("\n¿Desea crear un (1) Vendedor o (2) Comprador?");
        int tipo = leerEntero();

        if (tipo == 1) {
            System.out.print("Ingrese su nombre de vendedor: ");
            String nombre = sc.nextLine();
            String id = generarIdUnico('A');
            System.out.println("✅ Vendedor creado: " + nombre + " | ID: " + id);
        } else if (tipo == 2) {
            System.out.print("Ingrese su nombre de comprador: ");
            String nombre = sc.nextLine();
            String id = generarIdUnico('B');
            System.out.println("✅ Comprador creado: " + nombre + " | ID: " + id);
        } else {
            System.out.println("❌ Opción inválida.");
        }
    }

    // ======== MENÚ VENDEDOR ========
    private static void menuVendedor(String idVend) {
        System.out.print("Ingrese su nombre de vendedor: ");
        String nombre = sc.nextLine();
        Vendedor vendedor = new Vendedor(nombre, idVend);
        System.out.println("Bienvenido, " + vendedor.getNombre() + ". Tu ID de sesión: " + vendedor.getId());

        while (true) {
            System.out.println("\n=== MENÚ VENDEDOR ===");
            System.out.println("1. Agregar producto al catálogo");
            System.out.println("2. Ver catálogo actual");
            System.out.println("3. Cerrar sesión");
            System.out.print("Seleccione una opción: ");

            int opc = leerEntero();
            if (opc == 1) {
                System.out.print("Nombre del producto: ");
                String nomProd = sc.nextLine();
                System.out.print("Precio del producto: ");
                double precio = leerDouble();

                Producto nuevo = vendedor.crearProducto(nomProd, precio, contadorProductos++);
                catalogo.add(nuevo);
                System.out.println("Producto agregado correctamente ✅");
            } else if (opc == 2) {
                mostrarCatalogo();
            } else if (opc == 3) {
                System.out.println("🔒 Sesión cerrada del vendedor " + vendedor.getNombre());
                break;
            } else {
                System.out.println("❌ Opción inválida.");
            }
        }
    }

    // ======== MENÚ COMPRADOR ========
    private static void menuComprador(String idComp) {
        System.out.print("Ingrese su nombre de comprador: ");
        String nombre = sc.nextLine();
        Comprador comprador = new Comprador(nombre, idComp);
        System.out.println("Bienvenido, " + comprador.getNombre() + ". Tu ID de sesión: " + comprador.getId());

        while (true) {
            System.out.println("\n=== MENÚ COMPRADOR ===");
            System.out.println("1. Ver catálogo de productos");
            System.out.println("2. Realizar pedido");
            System.out.println("3. Ver historial de pedidos");
            System.out.println("4. Cerrar sesión");
            System.out.print("Seleccione una opción: ");

            int opc = leerEntero();
            if (opc == 1) {
                mostrarCatalogo();
            } else if (opc == 2) {
                if (catalogo.isEmpty()) {
                    System.out.println("🚫 No hay productos disponibles aún.");
                    continue;
                }
                mostrarCatalogo();
                System.out.print("Ingrese el ID del producto que desea comprar: ");
                String idProd = sc.nextLine();

                Producto productoSeleccionado = null;
                for (Producto p : catalogo) {
                    if (p.getId().equalsIgnoreCase(idProd)) {
                        productoSeleccionado = p;
                        break;
                    }
                }

                if (productoSeleccionado == null) {
                    System.out.println("❌ ID de producto no encontrado.");
                    continue;
                }

                System.out.print("Ingrese dirección de entrega: ");
                String direccion = sc.nextLine();

                Pedido pedido = comprador.hacerPedido(productoSeleccionado, direccion);
                pedido.mostrarInfo();
                System.out.println("✅ Pedido realizado correctamente.");
            } else if (opc == 3) {
                comprador.mostrarHistorial();
            } else if (opc == 4) {
                System.out.println("🔒 Sesión cerrada del comprador " + comprador.getNombre());
                break;
            } else {
                System.out.println("❌ Opción inválida.");
            }
        }
    }

    // ======== MÉTODOS AUXILIARES ========
    private static String generarIdUnico(char prefijo) {
        String id;
        do {
            int num = random.nextInt(31); // 0-30
            id = prefijo + String.valueOf(num);
        } while (idsUsados.contains(id));
        idsUsados.add(id);
        return id;
    }

    private static void mostrarCatalogo() {
        if (catalogo.isEmpty()) {
            System.out.println("📭 El catálogo está vacío.");
            return;
        }
        System.out.println("\n=== CATÁLOGO DE PRODUCTOS ===");
        for (Producto p : catalogo) {
            p.mostrarInfo();
        }
    }

    private static int leerEntero() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.print("❗ Ingrese un número válido: ");
            }
        }
    }

    private static double leerDouble() {
        while (true) {
            try {
                return Double.parseDouble(sc.nextLine());
            } catch (Exception e) {
                System.out.print("❗ Ingrese un número decimal válido: ");
            }
        }
    }
}
