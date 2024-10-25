import java.util.ArrayList;

public class Biblioteca {
    private ArrayList<Libro> libros;
    private ArrayList<Usuario> usuarios;
    private Libro[][] estanterias;
    private int siguienteFila;
    private int siguienteColumna;

    public Biblioteca(int filas, int columnas) {
        this.libros = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.estanterias = new Libro[filas][columnas];
        this.siguienteFila = 0;
        this.siguienteColumna = 0;
    }

    public void registrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public void agregarLibro(Libro libro) {
        libros.add(libro);
        ubicarLibro(libro); 
    }

    public void eliminarLibro(String titulo) {
        int indice = buscarLibro(titulo);
        if (indice != -1) {
            libros.remove(indice);
            
        }
    }

    public int buscarLibro(String titulo) {
        for (int i = 0; i < libros.size(); i++) {
            if (libros.get(i).getTitulo().equals(titulo)) {
                return i; 
            }
        }
        return -1;
    }

    public boolean prestarLibro(String titulo, String idUsuario) {
        int indice = buscarLibro(titulo);
        if (indice != -1) {
            Libro libro = libros.get(indice);
            if (libro.getNumCopias() > 0) {
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
        }
        return false;
    }

    public boolean devolverLibro(String titulo, String idUsuario) {
        int indice = buscarLibro(titulo);
        if (indice != -1) {
            Libro libro = libros.get(indice);
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

    public void ubicarLibro(Libro libro) {
        if (siguienteFila < estanterias.length) {
            estanterias[siguienteFila][siguienteColumna] = libro;
            siguienteColumna++;
            if (siguienteColumna >= estanterias[siguienteFila].length) {
                siguienteColumna = 0;
                siguienteFila++;
            }
        } else {
            System.out.println("No hay espacio en las estanterías para más libros.");
        }
    }

    public int[] consultarUbicacion(String titulo) {
        if (titulo == null) {
            throw new IllegalArgumentException("El título no puede ser nulo");
        }
        for (int i = 0; i < estanterias.length; i++) {
            for (int j = 0; j < estanterias[i].length; j++) {
                if (estanterias[i][j] != null && titulo.equals(estanterias[i][j].getTitulo())) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    public void reubicarLibro(String titulo, int nuevaFila, int nuevaColumna) {
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

    public ArrayList<String> getTitulos() {
        ArrayList<String> titulos = new ArrayList<>();
        for (Libro libro : libros) {
            titulos.add(libro.getTitulo());
        }
        return titulos;
    }

    public int[] getUbicacionPorIndice(int index) {
        if (index < 0 || index >= libros.size()) {
            return null;
        }
        Libro libro = libros.get(index);
        return consultarUbicacion(libro.getTitulo());
    }
}
