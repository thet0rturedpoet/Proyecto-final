public class Libro {
    private String titulo;
    private String autor;
    private int numCopias;

    public Libro(String titulo, String autor, int numCopias) {
        this.titulo = titulo;
        this.autor = autor;
        this.numCopias = numCopias;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getNumCopias() {
        return numCopias;
    }

    public void setNumCopias(int numCopias) {
        this.numCopias = numCopias;
    }

    @Override
    public String toString() {
        return titulo + " de " + autor + ", " + numCopias + " copias disponibles";
    }
}
