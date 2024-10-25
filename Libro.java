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
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Libro libro = (Libro) obj;
        return titulo.equals(libro.titulo);
    }
}
