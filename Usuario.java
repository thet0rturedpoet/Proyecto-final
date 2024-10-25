import java.util.ArrayList;

public class Usuario {
    private String nombre;
    private String idUsuario;
    private ArrayList<Libro> librosPrestados;
    private static final int LIMITE_LIBROS = 5;

    public Usuario(String nombre, String idUsuario) {
        this.nombre = nombre;
        this.idUsuario = idUsuario;
        this.librosPrestados = new ArrayList<>();
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void prestarLibro(Libro libro) {
        librosPrestados.add(libro);
    }

    public void devolverLibro(Libro libro) {
        librosPrestados.remove(libro);
    }

    public boolean puedePedirMasLibros() {
        return librosPrestados.size() < LIMITE_LIBROS;
    }

    public ArrayList<Libro> getLibrosPrestados() {
        return librosPrestados;
    }
}
