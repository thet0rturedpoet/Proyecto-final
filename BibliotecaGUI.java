import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BibliotecaGUI extends JFrame {
    private Biblioteca biblioteca;
    private JTextArea outputArea;
    private JTextField tituloField, autorField, numCopiasField, nombreUsuarioField, idUsuarioField;


    public BibliotecaGUI() {
        biblioteca = new Biblioteca(5, 5);
        initUI();
    }

    private void initUI() {
        setTitle("Sistema de Gestión de Biblioteca");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 3)); 
        getContentPane().add(buttonPanel, BorderLayout.CENTER);

        JButton agregarLibroButton = new JButton("Agregar Libro");
        agregarLibroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                agregarLibro();
            }
        });
        buttonPanel.add(agregarLibroButton);

        JButton sacarLibroButton = new JButton("Eliminar Libro");
        sacarLibroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                sacarLibro();
            }
        });
        buttonPanel.add(sacarLibroButton);

        JButton buscarLibroButton = new JButton("Consultar disponibilidad de un libro");
        buscarLibroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                buscarLibro();
            }
        });
        buttonPanel.add(buscarLibroButton);

        JButton regUsuariosButton = new JButton("Registrar Usuarios");
        regUsuariosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                registrodeUsuarios();
            }
        });
        buttonPanel.add(regUsuariosButton);

        JButton pedLibroButton = new JButton("Pida su libro");
        pedLibroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                pedirLibro();
            }
        });
        buttonPanel.add(pedLibroButton);

        JButton devLibroButton = new JButton("Devolver libro");
        devLibroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                devolverLibro();
            }
        });
        buttonPanel.add(devLibroButton);

        JButton conPrestamoButton = new JButton("Consulte su libro");
        conPrestamoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                consultarPrestamos();
            }
        });
        buttonPanel.add(conPrestamoButton);

        JButton ubicacionLibroButton = new JButton("Ubicación del libro");
        ubicacionLibroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                ubicacionLibro();
            }
        });
        buttonPanel.add(ubicacionLibroButton);

        JButton reLibroButton = new JButton("Reubicar libro");
        reLibroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event){
                reubicarLibro();
            }
        });
        buttonPanel.add(reLibroButton);

        JPanel outputPanel = new JPanel();
        outputPanel.setLayout(new BorderLayout());
        outputArea = new JTextArea(10, 50);
        outputArea.setEditable(false);
        outputPanel.add(new JScrollPane(outputArea), BorderLayout.CENTER); 

        getContentPane().add(outputPanel, BorderLayout.NORTH); 

        setVisible(true);
    }

    private void agregarLibro() {
        tituloField = new JTextField(10);
        autorField = new JTextField(10);
        numCopiasField = new JTextField(10);

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Título:"));
        inputPanel.add(tituloField);
        inputPanel.add(Box.createHorizontalStrut(15));
        inputPanel.add(new JLabel("Autor:"));
        inputPanel.add(autorField);
        inputPanel.add(Box.createHorizontalStrut(15));
        inputPanel.add(new JLabel("Número de Copias:"));
        inputPanel.add(numCopiasField);

        int result = JOptionPane.showConfirmDialog(null, inputPanel, "Agregar Libro", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String titulo = tituloField.getText();
            String autor = autorField.getText();
            int numCopias = Integer.parseInt(numCopiasField.getText());
            biblioteca.agregarLibro(new Libro(titulo, autor, numCopias));
            outputArea.append("Libro agregado: " + titulo + " (" + numCopias + " copias disponibles)" + "\n");
        }
    }

    private void sacarLibro() {
        tituloField = new JTextField(10);
    
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Ingrese el título del libro que desea eliminar:"));
        inputPanel.add(tituloField);
        inputPanel.add(Box.createHorizontalStrut(15));
    
        int result = JOptionPane.showConfirmDialog(null, inputPanel, "Eliminar Libro", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) { 
            String titulo = tituloField.getText();
            if (!titulo.isEmpty()) { 
                biblioteca.eliminarLibro(titulo);
                outputArea.append("Libro eliminado: " + titulo + "\n"); 
            } else {
                JOptionPane.showMessageDialog(null, "El título no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    

    private void buscarLibro(){
        tituloField = new JTextField(10);

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Ingrese el título del libro a buscar: ")); 
        inputPanel.add(tituloField);
        inputPanel.add(Box.createHorizontalStrut(15));

        int result = JOptionPane.showConfirmDialog(null, inputPanel, "Buscar Libro", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) { 
            String titulo = tituloField.getText();
            if (!titulo.isEmpty()) { 
                biblioteca.buscarLibro(titulo);
                outputArea.append("Libro disponible: " + titulo + " de " + autorField.getText() + "\n"); 
            } else {
                JOptionPane.showMessageDialog(null, "El título no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void registrodeUsuarios() {
        nombreUsuarioField = new JTextField(10);
        idUsuarioField = new JTextField(10);
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(0, 2, 10, 10));
        
        inputPanel.add(new JLabel("Nombre del usuario:"));
        inputPanel.add(nombreUsuarioField);
        inputPanel.add(new JLabel("ID del usuario:"));
        inputPanel.add(idUsuarioField);
        
        JButton agregarEstudianteButton = new JButton("Agregar Estudiante");
        agregarEstudianteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                String nombre = nombreUsuarioField.getText();
                String id = idUsuarioField.getText();
                biblioteca.registrarUsuario(new Estudiante(nombre, id));
                outputArea.append("Estudiante registrado: " + nombre + " (ID: " + id + ")\n");
            }
        });
    
        JButton agregarProfesorButton = new JButton("Agregar Profesor");
        agregarProfesorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                String nombre = nombreUsuarioField.getText();
                String id = idUsuarioField.getText();
                biblioteca.registrarUsuario(new Profesor(nombre, id));
                outputArea.append("Profesor registrado: " + nombre + " (ID: " + id + ")\n");
            }
        });
    
        inputPanel.add(agregarEstudianteButton);
        inputPanel.add(agregarProfesorButton);

        int result = JOptionPane.showConfirmDialog(null, inputPanel, "Registro de Usuarios", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
        }
    }
    

    private void pedirLibro(){
        tituloField = new JTextField();
        idUsuarioField = new JTextField();

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(0, 2, 10, 10));

        inputPanel.add(new JLabel("Ingrese el título del libro deseado:"));
        inputPanel.add(tituloField);
        inputPanel.add(new JLabel("ID del usuario:"));
        inputPanel.add(idUsuarioField);

        int result = JOptionPane.showConfirmDialog(null, inputPanel, "Prestar Libro", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String titulo = tituloField.getText();
            String idUsuario = idUsuarioField.getText();

            if(titulo.isEmpty() || idUsuario.isEmpty()){
                JOptionPane.showMessageDialog(null, "El título o el ID de usuario no pueden estar vacíos", "ERROR", JOptionPane.ERROR_MESSAGE);
            }

            boolean prestamoSi = biblioteca.prestarLibro(titulo, idUsuario);
            if(prestamoSi){
                outputArea.append("Libro prestado: " + titulo + " (ID Usuario: " + idUsuario + ") \n");
            }else{
                outputArea.append("No se pudo prestar el libro, asegúrese de su disponibilidad \n" +
                "y si el usuario no ha exedido su límite de préstamos. \n");
            }

        }


    }

    private void devolverLibro(){
        tituloField = new JTextField();
        idUsuarioField = new JTextField();

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(0, 2, 10, 10));

        inputPanel.add(new JLabel("Ingrese el título del libro a devolver:"));
        inputPanel.add(tituloField);
        inputPanel.add(new JLabel("ID del usuario:"));
        inputPanel.add(idUsuarioField);

        int result = JOptionPane.showConfirmDialog(null, inputPanel, "Devolver Libro", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String titulo = tituloField.getText();
            String idUsuario = idUsuarioField.getText();

            if(titulo.isEmpty() || idUsuario.isEmpty()){
                JOptionPane.showMessageDialog(null, "El título o el ID de usuario no pueden estar vacíos", "ERROR", JOptionPane.ERROR_MESSAGE);
            }

            boolean devolucionSi = biblioteca.devolverLibro(titulo, idUsuario);
            if(devolucionSi){
                outputArea.append("Libro devuelto: " + titulo + " (ID Usuario: " + idUsuario + ") \n");
            }else{
                outputArea.append("No se pudo prestar el libro. \n");
            }

        }
    }

    private void consultarPrestamos(){
        idUsuarioField = new JTextField();

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(0, 2, 10, 10));

        inputPanel.add(new JLabel("ID del usuario"));
        inputPanel.add(idUsuarioField);

        int result = JOptionPane.showConfirmDialog(null, inputPanel, "Consultar préstamos", JOptionPane.OK_CANCEL_OPTION);
        if(result == JOptionPane.OK_OPTION){
            String idUsuario = idUsuarioField.getText();

            if(!idUsuario.isEmpty()){
                ArrayList<Libro> librosPrestados = biblioteca.consultarLibrosPrestados(idUsuario);
                outputArea.append("Libro/s prestado/s: \n");
                for(Libro libro : librosPrestados){
                    outputArea.append("- " + libro.getTitulo() + "\n");
                }
            }else{
                JOptionPane.showMessageDialog(null, "Ingrese un ID de usuario válido", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void ubicacionLibro() {
        tituloField = new JTextField(10);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 1, 10, 10));
        inputPanel.add(new JLabel("Título del libro a ubicar"));
        inputPanel.add(tituloField);
    
        int result = JOptionPane.showConfirmDialog(null, inputPanel, "Ubicar Libro", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String titulo = tituloField.getText();
            if (!titulo.isEmpty()) {
                int[] ubicacion = biblioteca.consultarUbicacion(titulo);
                if (ubicacion != null) {
                    outputArea.append("El libro se encuentra en la estantería: " + ubicacion[0] + ", posición: " + ubicacion[1] + ".\n");
                } else {
                    outputArea.append("Libro no encontrado en las estanterías.\n");
                }
            } else {
                JOptionPane.showMessageDialog(null, "El título no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    

    private void reubicarLibro() {
        tituloField = new JTextField(10);
        JTextField filaField = new JTextField(5);
        JTextField columnaField = new JTextField(5);
    
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(0, 2, 10, 10));
    
        inputPanel.add(new JLabel("Título del libro:"));
        inputPanel.add(tituloField);
        inputPanel.add(new JLabel("Nueva fila:"));
        inputPanel.add(filaField);
        inputPanel.add(new JLabel("Nueva columna:"));
        inputPanel.add(columnaField);
    
        int result = JOptionPane.showConfirmDialog(null, inputPanel, "Reubicar Libro", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String titulo = tituloField.getText();
            try {
                int nuevaFila = Integer.parseInt(filaField.getText());
                int nuevaColumna = Integer.parseInt(columnaField.getText());
    
                biblioteca.reubicarLibro(titulo, nuevaFila, nuevaColumna);
                outputArea.append("Libro reubicado: " + titulo + " a la estantería " + nuevaFila + ", posición " + nuevaColumna + ".\n");
    
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor, ingrese números válidos para fila y columna.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (ArrayIndexOutOfBoundsException e) {
                JOptionPane.showMessageDialog(null, "La nueva ubicación está fuera de los límites de las estanterías.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            BibliotecaGUI ex = new BibliotecaGUI();
            ex.setVisible(true);
        });
    }
}
