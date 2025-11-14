import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private static int contadorId = 1;
    private int id;
    private String nombre;
    private String email;
    private List<Prestamo> prestamos;
    private double multas;

    public Usuario(String nombre, String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Email inválido.");
        }
        this.id = contadorId++;
        this.nombre = nombre;
        this.email = email;
        this.prestamos = new ArrayList<Prestamo>();
        this.multas = 0.0;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
    public double getMultas() { return multas; }
    public List<Prestamo> getPrestamos() { return prestamos; }

    public boolean puedePedirPrestado() throws UsuarioSinCupoException {
        if (prestamos.size() >= 3) throw new UsuarioSinCupoException("El usuario ya tiene 3 préstamos activos.");
        if (multas > 5000) throw new UsuarioSinCupoException("El usuario tiene multas superiores a $5000.");
        return true;
    }

    public void agregarPrestamo(Prestamo p) {
        prestamos.add(p);
    }

    public void agregarMulta(double valor) {
        multas += valor;
    }

    public void pagarMultas() {
        multas = 0;
    }

    @Override
    public String toString() {
        return "Usuario " + id + ": " + nombre + " | Multas: $" + multas + " | Libros prestados: " + prestamos.size();
    }
}