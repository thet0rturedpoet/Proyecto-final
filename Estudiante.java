public class Estudiante extends Usuario {
    private static final int LIMITE_LIBROS = 5;

    public Estudiante(String nombre, String idUsuario) {
        super(nombre, idUsuario);
    }

    @Override
    public boolean puedePedirMasLibros() {
        return this.librosPrestados.size() < LIMITE_LIBROS;
    }
}
