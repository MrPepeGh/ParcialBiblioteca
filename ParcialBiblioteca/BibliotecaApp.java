import java.util.Scanner;

public class BibliotecaApp {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("\n=== Sistema de Biblioteca ===");
            System.out.println("1. Agregar libro");
            System.out.println("2. Registrar usuario");
            System.out.println("3. Realizar préstamo");
            System.out.println("4. Devolver libro");
            System.out.println("5. Consultar libros disponibles");
            System.out.println("6. Listar usuarios con multas");
            System.out.println("7. Top 5 libros más prestados");
            System.out.println("8. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(sc.nextLine());
                switch (opcion) {
                    case 1:
                        System.out.print("ISBN (13 dígitos): ");
                        String isbn = sc.nextLine();
                        System.out.print("Título: ");
                        String titulo = sc.nextLine();
                        System.out.print("Autor: ");
                        String autor = sc.nextLine();
                        System.out.print("Año: ");
                        int anio = Integer.parseInt(sc.nextLine());
                        System.out.print("Ejemplares totales: ");
                        int tot = Integer.parseInt(sc.nextLine());
                        biblioteca.agregarLibro(new Libro(isbn, titulo, autor, anio, tot));
                        System.out.println("Libro agregado.");
                        break;
                    case 2:
                        System.out.print("Nombre: ");
                        String nombre = sc.nextLine();
                        System.out.print("Email: ");
                        String email = sc.nextLine();
                        biblioteca.registrarUsuario(new Usuario(nombre, email));
                        System.out.println("Usuario registrado.");
                        break;
                    case 3:
                        System.out.print("ID Usuario: ");
                        int idU = Integer.parseInt(sc.nextLine());
                        System.out.print("ISBN Libro: ");
                        String isbnP = sc.nextLine();
                        biblioteca.realizarPrestamo(idU, isbnP);
                        System.out.println("Préstamo realizado.");
                        break;
                    case 4:
                        System.out.print("ID Usuario: ");
                        int idD = Integer.parseInt(sc.nextLine());
                        System.out.print("ISBN Libro: ");
                        String isbnD = sc.nextLine();
                        biblioteca.devolverLibro(idD, isbnD);
                        System.out.println("Libro devuelto.");
                        break;
                    case 5:
                        biblioteca.mostrarLibrosDisponibles();
                        break;
                    case 6:
                        for (Usuario u : biblioteca.obtenerUsuariosConMultas()) {
                            System.out.println(u);
                        }
                        break;
                    case 7:
                        for (Libro l : biblioteca.obtenerTopLibrosPrestados()) {
                            System.out.println(l);
                        }
                        break;
                    case 8:
                        System.out.println("Saliendo...");
                        break;
                    default:
                        System.out.println("Opción inválida.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (opcion != 8);
        sc.close();
    }
}