import java.util.ArrayList;

public class Biblioteca {
    private ArrayList<Libro> libros;
    private ArrayList<Usuario> usuarios;
    private Libro[][] estanterias;

    public Biblioteca(int filas, int columnas) {
        this.libros = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.estanterias = new Libro[filas][columnas];
    }

    public void registrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public void agregarLibro(Libro libro) {
        libros.add(libro);
    }

    public void eliminarLibro(String titulo) {
        libros.removeIf(libro -> libro.getTitulo().equals(titulo));
    }

    public Libro buscarLibro(String titulo) {
        for (Libro libro : libros) {
            if (libro.getTitulo().equals(titulo)) {
                return libro;
            }
        }
        return null;
    }

    public boolean prestarLibro(String titulo, String idUsuario) {
        Libro libro = buscarLibro(titulo);
        if (libro != null && libro.getNumCopias() > 0) {
            for (Usuario usuario : usuarios) {
                if (usuario.getIdUsuario().equals(idUsuario)) {
                    if (usuario.puedePedirMasLibros()) {
                        usuario.prestarLibro(libro);
                        libro.setNumCopias(libro.getNumCopias() - 1);
                        return true;
                    } else {
                        System.out.println("El usuario ha alcanzado el límite de libros permitidos.");
                        return false;
                    }
                }
            }
        }
        return false;
    }

    public boolean devolverLibro(String titulo, String idUsuario) {
        Libro libro = buscarLibro(titulo);
        if (libro != null) {
            for (Usuario usuario : usuarios) {
                if (usuario.getIdUsuario().equals(idUsuario) && usuario.getLibrosPrestados().contains(libro)) {
                    usuario.devolverLibro(libro);
                    libro.setNumCopias(libro.getNumCopias() + 1);
                    return true;
                }
            }
        }
        return false;
    }

    public ArrayList<Libro> consultarLibrosPrestados(String idUsuario) {
        for (Usuario usuario : usuarios) {
            if (usuario.getIdUsuario().equals(idUsuario)) {
                return usuario.getLibrosPrestados();
            }
        }
        return new ArrayList<>();
    }

    public void ubicarLibro(String titulo, int fila, int columna) {
        Libro libro = buscarLibro(titulo);
        if (libro != null) {
            estanterias[fila][columna] = libro;
        }
    }

    public int[] consultarUbicacion(String titulo) {
        for (int i = 0; i < estanterias.length; i++) {
            for (int j = 0; j < estanterias[i].length; j++) {
                if (estanterias[i][j] != null && estanterias[i][j].getTitulo().equals(titulo)) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    public void reubicarLibro(String titulo, int nuevaFila, int nuevaColumna) {
        if (nuevaFila < 0 || nuevaFila >= estanterias.length || nuevaColumna < 0 || nuevaColumna >= estanterias[nuevaFila].length) {
            System.out.println("La ubicación es inválida.");
            return;
        }
        int[] ubicacionActual = consultarUbicacion(titulo);
        if (ubicacionActual != null) {
            Libro libro = estanterias[ubicacionActual[0]][ubicacionActual[1]];
            estanterias[ubicacionActual[0]][ubicacionActual[1]] = null;
            estanterias[nuevaFila][nuevaColumna] = libro;
        } else {
            System.out.println("Libro no encontrado para reubicar.");
        }
    }

    public void reorganizarBiblioteca() {
        libros.sort((libro1, libro2) -> libro1.getAutor().compareTo(libro2.getAutor()));
        int indice = 0;
        for (int i = 0; i < estanterias.length; i++) {
            for (int j = 0; j < estanterias[i].length; j++) {
                if (indice < libros.size()) {
                    estanterias[i][j] = libros.get(indice);
                    indice++;
                }
            }
        }
    }
}
