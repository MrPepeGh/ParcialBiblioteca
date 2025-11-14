import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Prestamo {
    private Libro libro;
    private Usuario usuario;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;
    private EstadoPrestamo estado;
    private double multa;

    public Prestamo(Libro libro, Usuario usuario) {
        this.libro = libro;
        this.usuario = usuario;
        this.fechaPrestamo = LocalDate.now();
        this.fechaDevolucion = fechaPrestamo.plusDays(14);
        this.estado = EstadoPrestamo.ACTIVO;
        this.multa = 0;
    }

    public Libro getLibro() { return libro; }
    public Usuario getUsuario() { return usuario; }
    public EstadoPrestamo getEstado() { return estado; }
    public double getMulta() { return multa; }

    public void devolver() {
        LocalDate hoy = LocalDate.now();
        if (hoy.isAfter(fechaDevolucion)) {
            long diasRetraso = ChronoUnit.DAYS.between(fechaDevolucion, hoy);
            multa = diasRetraso * 500;
            usuario.agregarMulta(multa);
            estado = EstadoPrestamo.VENCIDO;
        } else {
            estado = EstadoPrestamo.DEVUELTO;
        }
        libro.devolver();
    }

    @Override
    public String toString() {
        return "Libro: " + libro.getTitulo() + " | Estado: " + estado + " | Multa: $" + multa;
    }
}