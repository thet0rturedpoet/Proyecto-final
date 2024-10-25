public class Profesor extends Usuario {
    private static final int LIMITE_LIBROS = 10;

    public Profesor(String nombre, String idUsuario) {
        super(nombre, idUsuario);
    }

    @Override
    public boolean puedePedirMasLibros() {
        return getLibrosPrestados().size() < LIMITE_LIBROS;
    }
}
