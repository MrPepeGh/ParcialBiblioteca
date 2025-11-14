import java.util.*;

public class Biblioteca {
    private Map<String, Libro> libros;
    private Map<Integer, Usuario> usuarios;
    private List<Prestamo> prestamos;

    public Biblioteca() {
        libros = new HashMap<String, Libro>();
        usuarios = new HashMap<Integer, Usuario>();
        prestamos = new ArrayList<Prestamo>();
    }

    public void agregarLibro(Libro libro) {
        libros.put(libro.getIsbn(), libro);
    }

    public void registrarUsuario(Usuario usuario) {
        usuarios.put(usuario.getId(), usuario);
    }

    public Libro buscarLibroPorIsbn(String isbn) {
        return libros.get(isbn);
    }

    public Usuario buscarUsuarioPorId(int id) {
        return usuarios.get(id);
    }

    public void realizarPrestamo(int idUsuario, String isbn) throws Exception {
        Usuario usuario = buscarUsuarioPorId(idUsuario);
        Libro libro = buscarLibroPorIsbn(isbn);
        if (usuario == null || libro == null) throw new Exception("Usuario o libro no encontrado.");
        if (usuario.puedePedirPrestado()) {
            libro.prestar();
            Prestamo p = new Prestamo(libro, usuario);
            prestamos.add(p);
            usuario.agregarPrestamo(p);
        }
    }

    public void devolverLibro(int idUsuario, String isbn) throws Exception {
        Usuario usuario = buscarUsuarioPorId(idUsuario);
        if (usuario == null) throw new Exception("Usuario no encontrado.");
        Prestamo encontrado = null;
        for (Prestamo p : usuario.getPrestamos()) {
            if (p.getLibro().getIsbn().equals(isbn) && p.getEstado() == EstadoPrestamo.ACTIVO) {
                encontrado = p;
                break;
            }
        }
        if (encontrado != null) {
            encontrado.devolver();
        } else {
            throw new Exception("No se encontró préstamo activo para ese libro.");
        }
    }

    public List<Libro> obtenerTopLibrosPrestados() {
        List<Libro> lista = new ArrayList<Libro>(libros.values());
        Collections.sort(lista, new Comparator<Libro>() {
            public int compare(Libro l1, Libro l2) {
                return l2.getVecesPrestado() - l1.getVecesPrestado();
            }
        });
        return lista.subList(0, Math.min(5, lista.size()));
    }

    public List<Usuario> obtenerUsuariosConMultas() {
        List<Usuario> conMultas = new ArrayList<Usuario>();
        for (Usuario u : usuarios.values()) {
            if (u.getMultas() > 0) conMultas.add(u);
        }
        return conMultas;
    }

    public void mostrarLibrosDisponibles() {
        for (Libro l : libros.values()) {
            System.out.println(l);
        }
    }
}