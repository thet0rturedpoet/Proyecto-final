import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca(5, 5);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Bienvenido a la Biblioteca");
            System.out.println("1. Agregar Libro");
            System.out.println("2. Eliminar Libro");
            System.out.println("3. Buscar Libro");
            System.out.println("4. Registrar Estudiante");
            System.out.println("5. Registrar Profesor");
            System.out.println("6. Prestar Libro");
            System.out.println("7. Devolver Libro");
            System.out.println("8. Consultar Libros Prestados");
            System.out.println("9. Ubicar Libro");
            System.out.println("10. Reubicar Libro");
            System.out.println("11. Reorganizar Biblioteca");
            System.out.println("12. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion;
            try {
                opcion = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Por favor, ingrese un número válido.");
                scanner.nextLine();
                continue;
            }

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el título del libro: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Ingrese el autor del libro: ");
                    String autor = scanner.nextLine();
                    System.out.print("Ingrese el número de copias disponibles: ");
                    int numCopias = scanner.nextInt();
                    scanner.nextLine();
                    biblioteca.agregarLibro(new Libro(titulo, autor, numCopias));
                    break;
                case 2:
                    System.out.print("Ingrese el título del libro a eliminar: ");
                    titulo = scanner.nextLine();
                    biblioteca.eliminarLibro(titulo);
                    break;
                case 3:
                    System.out.print("Ingrese el título del libro a buscar: ");
                    titulo = scanner.nextLine();
                    Libro libro = biblioteca.buscarLibro(titulo);
                    if (libro != null) {
                        System.out.println("Libro encontrado: " + libro);
                    } else {
                        System.out.println("Libro no encontrado");
                    }
                    break;
                case 4:
                    System.out.print("Ingrese el nombre del estudiante: ");
                    String nombreEstudiante = scanner.nextLine();
                    System.out.print("Ingrese el ID del estudiante: ");
                    String idEstudiante = scanner.nextLine();
                    biblioteca.registrarUsuario(new Estudiante(nombreEstudiante, idEstudiante));
                    break;
                case 5:
                    System.out.print("Ingrese el nombre del profesor: ");
                    String nombreProfesor = scanner.nextLine();
                    System.out.print("Ingrese el ID del profesor: ");
                    String idProfesor = scanner.nextLine();
                    biblioteca.registrarUsuario(new Profesor(nombreProfesor, idProfesor));
                    break;
                case 6:
                    System.out.print("Ingrese el título del libro a prestar: ");
                    titulo = scanner.nextLine();
                    System.out.print("Ingrese el ID del usuario: ");
                    String idUsuario = scanner.nextLine();
                    if (biblioteca.prestarLibro(titulo, idUsuario)) {
                        System.out.println("Libro prestado con éxito");
                    } else {
                        System.out.println("No se pudo prestar el libro");
                    }
                    break;
                case 7:
                    System.out.print("Ingrese el título del libro a devolver: ");
                    titulo = scanner.nextLine();
                    System.out.print("Ingrese el ID del usuario: ");
                    idUsuario = scanner.nextLine();
                    if (biblioteca.devolverLibro(titulo, idUsuario)) {
                        System.out.println("Libro devuelto con éxito");
                    } else {
                        System.out.println("No se pudo devolver el libro");
                    }
                    break;
                case 8:
                    System.out.print("Ingrese el ID del usuario: ");
                    idUsuario = scanner.nextLine();
                    System.out.println("Libros prestados: " + biblioteca.consultarLibrosPrestados(idUsuario));
                    break;
                case 9:
                    System.out.print("Ingrese el título del libro a ubicar: ");
                    titulo = scanner.nextLine();
                    int[] ubicacion = biblioteca.consultarUbicacion(titulo);
                    if (ubicacion != null) {
                        System.out.println("El libro está ubicado en la fila " + ubicacion[0] + ", columna " + ubicacion[1]);
                    } else {
                        System.out.println("Libro no encontrado en las estanterías");
                    }
                    break;
                case 10:
                    System.out.print("Ingrese el título del libro a reubicar: ");
                    titulo = scanner.nextLine();
                    System.out.print("Ingrese la nueva fila: ");
                    int nuevaFila = scanner.nextInt();
                    System.out.print("Ingrese la nueva columna: ");
                    int nuevaColumna = scanner.nextInt();
                    scanner.nextLine();
                    biblioteca.reubicarLibro(titulo, nuevaFila, nuevaColumna);
                    break;
                case 11:
                    biblioteca.reorganizarBiblioteca();
                    System.out.println("Biblioteca reorganizada con éxito");
                    break;
                case 12:
                    System.out.println("Saliendo del sistema...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }
}
