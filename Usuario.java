import java.util.ArrayList;

public abstract class Usuario {
    protected String nombre;
    protected String idUsuario;
    protected ArrayList<Libro> librosPrestados;

    public Usuario(String nombre, String idUsuario) {
        this.nombre = nombre;
        this.idUsuario = idUsuario;
        this.librosPrestados = new ArrayList<>();
    }

    public abstract boolean puedePedirMasLibros();

    public void prestarLibro(Libro libro) {
        if (puedePedirMasLibros()) {
            librosPrestados.add(libro);
        } else {
            System.out.println("El usuario ha alcanzado el límite de libros permitidos.");
        }
    }

    public void devolverLibro(Libro libro) {
        librosPrestados.remove(libro);
    }

    public String getNombre() {
        return nombre;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public ArrayList<Libro> getLibrosPrestados() {
        return librosPrestados;
    }

    @Override
    public String toString() {
        return "Usuario " + nombre + ", ID " + idUsuario + ", Libros prestados: " + librosPrestados.size();
    }
}
