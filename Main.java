import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Biblioteca biblioteca = new Biblioteca(5, 5); 

        while (true) {
            System.out.println("\n--- Sistema de Gestión de Biblioteca ---");
            System.out.println("1. Agregar Libro");
            System.out.println("2. Eliminar Libro");
            System.out.println("3. Buscar Libro");
            System.out.println("4. Registrar Usuario");
            System.out.println("5. Prestar Libro");
            System.out.println("6. Devolver Libro");
            System.out.println("7. Consultar Libros Prestados");
            System.out.println("8. Ubicar Libro");
            System.out.println("9. Reubicar Libro");
            System.out.println("10. Reorganizar Biblioteca");
            System.out.println("11. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcion) {
                case 1: 
                    System.out.print("Ingrese el título del libro: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Ingrese el autor del libro: ");
                    String autor = scanner.nextLine();
                    System.out.print("Ingrese el número de copias: ");
                    int numCopias = scanner.nextInt();
                    biblioteca.agregarLibro(new Libro(titulo, autor, numCopias));
                    System.out.println("Libro agregado.");
                    break;

                case 2:
                    System.out.print("Ingrese el título del libro a eliminar: ");
                    String tituloEliminar = scanner.nextLine();
                    biblioteca.eliminarLibro(tituloEliminar);
                    System.out.println("Libro eliminado.");
                    break;

                case 3: 
                    System.out.print("Ingrese el título del libro a buscar: ");
                    String tituloBuscar = scanner.nextLine();
                    int index = biblioteca.buscarLibro(tituloBuscar);
                    if (index != -1) {
                        System.out.println("Libro encontrado: " + biblioteca.getTitulos().get(index));
                    } else {
                        System.out.println("Libro no encontrado.");
                    }
                    break;

                case 4: 
                    System.out.print("Ingrese el nombre del usuario: ");
                    String nombreUsuario = scanner.nextLine();
                    System.out.print("Ingrese el ID del usuario: ");
                    String idUsuario = scanner.nextLine();
                    System.out.print("Es un estudiante (1) o profesor (2): ");
                    int tipoUsuario = scanner.nextInt();
                    scanner.nextLine(); 

                    if (tipoUsuario == 1) {
                        biblioteca.registrarUsuario(new Estudiante(nombreUsuario, idUsuario));
                    } else {
                        biblioteca.registrarUsuario(new Profesor(nombreUsuario, idUsuario));
                    }
                    System.out.println("Usuario registrado.");
                    break;

                case 5:
                    System.out.print("Ingrese el título del libro a prestar: ");
                    String tituloPrestar = scanner.nextLine();
                    System.out.print("Ingrese el ID del usuario: ");
                    String idUsuarioPrestar = scanner.nextLine();
                    if (biblioteca.prestarLibro(tituloPrestar, idUsuarioPrestar)) {
                        System.out.println("Libro prestado.");
                    } else {
                        System.out.println("No se pudo prestar el libro.");
                    }
                    break;

                case 6: 
                    System.out.print("Ingrese el título del libro a devolver: ");
                    String tituloDevolver = scanner.nextLine();
                    System.out.print("Ingrese el ID del usuario: ");
                    String idUsuarioDevolver = scanner.nextLine();
                    if (biblioteca.devolverLibro(tituloDevolver, idUsuarioDevolver)) {
                        System.out.println("Libro devuelto.");
                    } else {
                        System.out.println("No se pudo devolver el libro.");
                    }
                    break;

                case 7:
                    System.out.print("Ingrese el ID del usuario: ");
                    String idUsuarioConsulta = scanner.nextLine();
                    ArrayList<Libro> librosPrestados = biblioteca.consultarLibrosPrestados(idUsuarioConsulta);
                    System.out.println("Libros prestados:");
                    for (Libro libro : librosPrestados) {
                        System.out.println("- " + libro.getTitulo());
                    }
                    break;

                case 8:
                    System.out.print("Ingrese el título del libro a ubicar: ");
                    String tituloUbicar = scanner.nextLine();
                    int[] ubicacion = biblioteca.consultarUbicacion(tituloUbicar);
                    if (ubicacion != null) {
                        System.out.println("Ubicación: Estantería " + ubicacion[0] + ", Posición " + ubicacion[1]);
                    } else {
                        System.out.println("Libro no encontrado en estanterías.");
                    }
                    break;

                case 9:
                    System.out.print("Ingrese el título del libro a reubicar: ");
                    String tituloReubicar = scanner.nextLine();
                    System.out.print("Ingrese la nueva fila: ");
                    int nuevaFila = scanner.nextInt();
                    System.out.print("Ingrese la nueva columna: ");
                    int nuevaColumna = scanner.nextInt();
                    biblioteca.reubicarLibro(tituloReubicar, nuevaFila, nuevaColumna);
                    System.out.println("Libro reubicado.");
                    break;

                case 10:
                    biblioteca.reorganizarBiblioteca();
                    System.out.println("Biblioteca reorganizada.");
                    break;

                case 11:
                    System.out.println("Saliendo del sistema.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        }
    }
}
