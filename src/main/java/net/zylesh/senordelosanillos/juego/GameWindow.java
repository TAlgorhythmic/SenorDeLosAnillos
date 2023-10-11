package net.zylesh.senordelosanillos.juego;

import net.zylesh.senordelosanillos.Core;
import net.zylesh.senordelosanillos.batalla.CampoDeBatalla;
import net.zylesh.senordelosanillos.common.Entidad;
import net.zylesh.senordelosanillos.ui.VentanaPrincipal;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.Timer;
import java.util.concurrent.atomic.AtomicInteger;

public class GameWindow extends JFrame {

    private static GameWindow ventana;

    private static final java.util.List<BufferedImage> imagenes = new ArrayList<>();

    private static BufferedImage barra = null;
    static {
        try {
            BufferedImage original = ImageIO.read(GameWindow.class.getClassLoader().getResourceAsStream("assets" + File.separator + "juego" + File.separator + "utils" + File.separator + "tag.png"));
            barra = new BufferedImage(Toolkit.getDefaultToolkit().getScreenSize().width, 40, original.getType());
            Graphics2D g2d = barra.createGraphics();
            g2d.drawImage(original, 0, 0, Toolkit.getDefaultToolkit().getScreenSize().width, 40, null);
            g2d.dispose();
            for (int i = 0; i <= 18; i++) {
                BufferedImage img = ImageIO.read(GameWindow.class.getClassLoader().getResourceAsStream("assets" + File.separator + "juego" + File.separator + "espada" + File.separator + "sword" + i + ".png"));
                imagenes.add(img);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean shouldClose = false;
    private PersonajeLabel h1, h2, h3, b1, b2, b3;
    private FondoLabel fondo;
    private JLabel barraLabel;
    private JLabel textInfo;
    private final Output outputDelJuego = new Output();
    private boolean haEmpezado = false;
    private final SpringLayout layout;
    private final CampoDeBatalla campoDeBatalla;

    private GameWindow(CampoDeBatalla campoDeBatalla) {
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        setMaximumSize(Toolkit.getDefaultToolkit().getScreenSize());
        setMinimumSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.layout = new SpringLayout();
        this.campoDeBatalla = campoDeBatalla;
        init(campoDeBatalla);
        gameLoop();
    }

    public static GameWindow g(CampoDeBatalla campoDeBatalla) {
        if (ventana == null) ventana = new GameWindow(campoDeBatalla);
        return ventana;
    }

    public static GameWindow g() {
        return ventana;
    }

    public void nuevoOutput(String mensaje) {
        outputDelJuego.add(mensaje);
        Core.log(mensaje, true);
    }

    public void configurar() {
        setIconImage(VentanaPrincipal.icon);
        setLayout(layout);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (!haEmpezado && e.getKeyCode() == KeyEvent.VK_SPACE) empezar();
            }
        });
        textInfo.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        textInfo.setForeground(Color.WHITE);
        outputDelJuego.setVisible(true);

        add(textInfo);
        add(barraLabel);
        add(h1);
        add(h2);
        add(h3);
        add(b1);
        add(b2);
        add(b3);
        add(fondo);

        int alturaMedia = ((getSize().height / 2) - (PersonajeLabel.getAltura() / 2)) - 35;

        layout.putConstraint(SpringLayout.WEST, h1, 180, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, h1, 30, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, h2, 150, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, h2, alturaMedia, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, h3, 180, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.SOUTH, h3, -120, SpringLayout.SOUTH, this);

        layout.putConstraint(SpringLayout.EAST, b1, -300, SpringLayout.EAST, this);
        layout.putConstraint(SpringLayout.NORTH, b1, 30, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.EAST, b2, -270, SpringLayout.EAST, this);
        layout.putConstraint(SpringLayout.NORTH, b2, alturaMedia, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.EAST, b3, 210, SpringLayout.WEST, b1);
        layout.putConstraint(SpringLayout.SOUTH, b3, -120, SpringLayout.SOUTH, this);

    }

    public void empezar() {
        this.haEmpezado = true;
        remove(textInfo);
        remove(barraLabel);
        outputDelJuego.setVisible(true);
        campoDeBatalla.iniciar();
    }

    public void init(CampoDeBatalla campoDeBatalla) {
        try {
            this.h1 = new PersonajeLabel(campoDeBatalla.getHeroe1());
            this.h2 = new PersonajeLabel(campoDeBatalla.getHeroe2());
            this.h3 = new PersonajeLabel(campoDeBatalla.getHeroe3());
            this.b1 = new PersonajeLabel(campoDeBatalla.getBestia1());
            this.b2 = new PersonajeLabel(campoDeBatalla.getBestia2());
            this.b3 = new PersonajeLabel(campoDeBatalla.getBestia3());
            this.fondo = new FondoLabel(campoDeBatalla);
        } catch (IOException ignored) {}
        this.barraLabel = new JLabel(new ImageIcon(barra));
        this.outputDelJuego.setPreferredSize(new Dimension(360, 180));
        this.textInfo = new JLabel("Pulsa espacio para comenzar");
    }

    private boolean enAnimacion = false;
    private PersonajeLabel afectado;

    public void close() {
        shouldClose = true;
    }

    public void animarAtaque(Entidad afectado) {
        PersonajeLabel victima;
        if (h1 != null && h1.getEntidad().equals(afectado)) victima = h1;
        else if (h2 != null && h2.getEntidad().equals(afectado)) victima = h2;
        else if (h3 != null && h3.getEntidad().equals(afectado)) victima = h3;
        else if (b1 != null && b1.getEntidad().equals(afectado)) victima = b1;
        else if (b2 != null && b2.getEntidad().equals(afectado)) victima = b2;
        else if (b3 != null && b3.getEntidad().equals(afectado)) victima = b3;
        else victima = null;
        this.afectado = victima;
        enAnimacion = true;
    }

    private boolean inicializando = true;

    private static final Timer timer = new Timer();
    public void gameLoop() {
        final Iterator<BufferedImage>[] iterator = new Iterator[]{null};
        final JLabel[] labelSword = new JLabel[] {null};

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (inicializando) {
                    layout.putConstraint(SpringLayout.WEST, outputDelJuego, (getSize().width / 2) - 180, SpringLayout.WEST, GameWindow.g());
                    layout.putConstraint(SpringLayout.SOUTH, outputDelJuego, 176, SpringLayout.SOUTH, GameWindow.g());
                    configurar();
                    inicializando = false;
                }
                if (enAnimacion) {
                    if (iterator[0] == null || labelSword[0] == null) {
                        iterator[0] = imagenes.iterator();
                        labelSword[0] = new JLabel();
                        add(labelSword[0]);
                        layout.putConstraint(SpringLayout.WEST, labelSword[0], -30, SpringLayout.WEST, afectado);
                        layout.putConstraint(SpringLayout.NORTH, labelSword[0], -10, SpringLayout.NORTH, afectado);
                    }
                    if (iterator[0].hasNext()) {
                        labelSword[0].setIcon(new ImageIcon(iterator[0].next()));
                    } else {
                        enAnimacion = false;
                        afectado = null;
                        iterator[0] = null;
                        labelSword[0] = null;
                    }
                }
                if (h1 != null && !campoDeBatalla.getHeroe1().isAlive()) { remove(h1); h1 = null;}
                if (h2 != null && !campoDeBatalla.getHeroe2().isAlive()) { remove(h2); h2 = null;}
                if (h3 != null && !campoDeBatalla.getHeroe3().isAlive()) { remove(h3); h3 = null;}
                if (b1 != null && !campoDeBatalla.getBestia1().isAlive()) { remove(b1); b1 = null;}
                if (b2 != null && !campoDeBatalla.getBestia2().isAlive()) { remove(b2); b2 = null;}
                if (b3 != null && !campoDeBatalla.getBestia3().isAlive()) { remove(b3); b3 = null;}
                if (shouldClose) {
                    VentanaPrincipal.cerrar.setEnabled(false);
                    setVisible(false);
                    ventana = null;
                    this.cancel();
                }
            }
        }, 0L, 33L);
    }

    public static class Output extends JFrame {

        private static final int BASE_OFFSET = 10;
        private final SpringLayout layout = new SpringLayout();
        private final Map<JLabel, AtomicInteger> textos = new LinkedHashMap<>();

        public Output() {
            Dimension dim = new Dimension(400, 500);
            setSize(dim);
            setPreferredSize(dim);
            setResizable(false);
            setAlwaysOnTop(true);
            setIconImage(VentanaPrincipal.icon);
            setLayout(layout);
        }

        public void add(String mensaje) {
            if (!textos.isEmpty()) {
                for (JLabel label : textos.keySet()) {
                    textos.get(label).getAndAdd(15);
                    layout.putConstraint(SpringLayout.NORTH, label, textos.get(label).get(), SpringLayout.NORTH, this);
                }
            }
            JLabel label = new JLabel(mensaje);
            label.setVisible(true);
            label.setPreferredSize(new Dimension(400, 10));
            add(label);
            layout.putConstraint(SpringLayout.NORTH, label, BASE_OFFSET, SpringLayout.NORTH, this);
            textos.put(label, new AtomicInteger(BASE_OFFSET));
            SwingUtilities.updateComponentTreeUI(this);
        }
    }
}
