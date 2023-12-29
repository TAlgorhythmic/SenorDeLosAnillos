package net.zylesh.senordelosanillos.ui;

import net.zylesh.senordelosanillos.batalla.CampoDeBatalla;
import net.zylesh.senordelosanillos.juego.GameWindow;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class VentanaPrincipal extends JFrame {

    // Botones predefinidos para no instanciarlos cada vez que se crea una instancia de VentanaPrincipal (aunque solo se crea una en el programa, pero igualmente me pareció que es una buena práctica).
    private static final JButton configCampoDeBatalla;
    public static final JButton comenzar;
    public static final JButton cerrar;
    private static final JButton creditos;
    static {
        configCampoDeBatalla = new JButton("Configurar");
        comenzar = new JButton("Comenzar");
        comenzar.setEnabled(false);
        cerrar = new JButton("Cerrar");
        cerrar.setEnabled(false);
        creditos = new JButton("Créditos");
        Dimension dim = new Dimension(200, 55);
        configCampoDeBatalla.setPreferredSize(dim);
        comenzar.setPreferredSize(dim);
        cerrar.setPreferredSize(dim);
        creditos.setPreferredSize(dim);
        configCampoDeBatalla.setSize(dim);
        comenzar.setSize(dim);
        cerrar.setSize(dim);
        creditos.setSize(dim);

        creditos.addActionListener(e -> Creditos.mostrarCreditos());
        configCampoDeBatalla.addActionListener(e -> new VentanaDeConfiguracion());
        cerrar.addActionListener(e -> {
            if (GameWindow.g() != null) GameWindow.g().close();
        });

        comenzar.addActionListener(e -> CampoDeBatalla.CampoDeBatallaConfig.empezar());
        configCampoDeBatalla.setHorizontalAlignment(SwingConstants.CENTER);
        configCampoDeBatalla.setVerticalAlignment(SwingConstants.CENTER);
        comenzar.setHorizontalAlignment(SwingConstants.CENTER);
        comenzar.setVerticalAlignment(SwingConstants.CENTER);
        cerrar.setHorizontalAlignment(SwingConstants.CENTER);
        cerrar.setVerticalAlignment(SwingConstants.CENTER);
        creditos.setHorizontalAlignment(SwingConstants.CENTER);
        creditos.setVerticalAlignment(SwingConstants.CENTER);
    }
    // UI Inst finalizado.

    // Uso mi propia instancia de un panel para añadir la imágen de fondo.
    private final Panel contenedor;

    /**
     * Crea una ventana principal para el programa, ofrece botones de créditos, configuración, empezar batalla y cerrar campo de batalla.
     * Cerrar esta ventana terminará el programa independientemente de que haya un campo de batalla abierto o no.
     * @throws IOException Ya que el constructor de la clase Panel tira un IOException, pues para no tener que manejar la excepción aquí y hacerlo en el método de crearInterfaz() de la clase Core,
     * ya que hay otros métodos ahí que tambien tiran IOException y de esta manera manejarlos todos de una vez.
     */
    public VentanaPrincipal() throws IOException {
        super();
        Dimension dim = new Dimension((int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.6), (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.8));
        Dimension minDim = new Dimension((int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.55), (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.8));
        setSize(minDim);
        setPreferredSize(minDim);
        setMinimumSize(minDim);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contenedor = new Panel(this);
        contenedor.setSize(minDim);
        add(contenedor);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                contenedor.resize(getSize());
            }
        });
        setLocationRelativeTo(null);
        setName("Main window");
        setTitle("El Señor de los Anillos - Inti Hernández Servitja");
        contenedor.add(configCampoDeBatalla, 0.147, 0.2);
        contenedor.add(creditos, 0.632, 0.2);
        contenedor.add(comenzar, 0.147, 0.7);
        contenedor.add(cerrar, 0.632, 0.7);
        setSize(dim);
        pack();
        try {
            icon = ImageIO.read(getClass().getClassLoader().getResourceAsStream("assets/interfaz" + File.separator + "logo.png"));
            setIconImage(icon);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static BufferedImage icon;

    public void open() {
        setVisible(true);
    }
}
