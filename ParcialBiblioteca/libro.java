public class Libro {
    private String isbn;
    private String titulo;
    private String autor;
    private int anio;
    private int ejemplaresTotales;
    private int ejemplaresDisponibles;
    private int vecesPrestado;

    public Libro(String isbn, String titulo, String autor, int anio, int ejemplaresTotales) {
        if (isbn == null || isbn.length() != 13) {
            throw new IllegalArgumentException("El ISBN debe tener 13 dígitos.");
        }
        if (anio < 1500 || anio > 2025) {
            throw new IllegalArgumentException("El año es inválido.");
        }
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.anio = anio;
        this.ejemplaresTotales = ejemplaresTotales;
        this.ejemplaresDisponibles = ejemplaresTotales;
        this.vecesPrestado = 0;
    }

    public String getIsbn() { return isbn; }
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public int getAnio() { return anio; }
    public int getEjemplaresTotales() { return ejemplaresTotales; }
    public int getEjemplaresDisponibles() { return ejemplaresDisponibles; }
    public int getVecesPrestado() { return vecesPrestado; }

    public boolean estaDisponible() {
        return ejemplaresDisponibles > 0;
    }

    public void prestar() throws LibroNoDisponibleException {
        if (!estaDisponible()) throw new LibroNoDisponibleException("No hay ejemplares disponibles del libro: " + titulo);
        ejemplaresDisponibles--;
        vecesPrestado++;
    }

    public void devolver() {
        if (ejemplaresDisponibles < ejemplaresTotales) {
            ejemplaresDisponibles++;
        }
    }

    @Override
    public String toString() {
        return titulo + " (" + autor + ") - ISBN: " + isbn + " | Disp: " + ejemplaresDisponibles + "/" + ejemplaresTotales;
    }
}