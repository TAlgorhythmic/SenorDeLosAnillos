package net.zylesh.senordelosanillos.ui;

import net.zylesh.senordelosanillos.batalla.CampoDeBatalla;
import net.zylesh.senordelosanillos.batalla.config.Configuracion;
import net.zylesh.senordelosanillos.common.Bestia;
import net.zylesh.senordelosanillos.common.Entidad;
import net.zylesh.senordelosanillos.common.Heroe;
import net.zylesh.senordelosanillos.common.Personaje;
import net.zylesh.senordelosanillos.common.bestias.Orco;
import net.zylesh.senordelosanillos.common.bestias.Trasgo;
import net.zylesh.senordelosanillos.common.heroes.Elfo;
import net.zylesh.senordelosanillos.common.heroes.Hobbit;
import net.zylesh.senordelosanillos.common.heroes.Humano;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class VentanaDeConfiguracion extends JFrame {

    private final Map<Integer, Personaje> posiciones = new HashMap<>();
    private final Map<JComboBox<Personaje>, Integer> casillas = new HashMap<>();
    private File fondo = CampoDeBatalla.fondosBasicos[0];

    final JButton e1 = new JButton("Definir*");
    final JButton e2 = new JButton("Definir*");
    final JButton e3 = new JButton("Definir*");
    final JButton e4 = new JButton("Definir*");
    final JButton e5 = new JButton("Definir*");
    final JButton e6 = new JButton("Definir*");

    public VentanaDeConfiguracion() {
        Dimension dim = new Dimension(540, 550);
        SpringLayout layout = new SpringLayout();
        setLayout(layout);
        setSize(dim);
        setPreferredSize(dim);
        setResizable(false);
        setAlwaysOnTop(true);
        setTitle("Panel de Configuración");
        setIconImage(VentanaPrincipal.icon);
        Dimension dim2 = new Dimension(320, 30);
        Personaje sample = new Personaje() {
            /**
             * Override para usar de selección default, esta instancia no es considerada un personaje.
             */
            @Override
            public String toString() {
                return "Escoje un personaje";
            }
        };

        // Inicialización UI comienzo.
        JComboBox<Personaje> hbox1 = new JComboBox<>(); hbox1.setPreferredSize(dim2);
        hbox1.addItem(sample);
        Arrays.stream(Personaje.presetsHeroes).forEach(personaje -> {
            if (personaje.getTipo().getSuperclass().equals(Heroe.class)) hbox1.addItem(personaje);
        });
        JComboBox<Personaje> hbox2 = new JComboBox<>(); hbox2.setPreferredSize(dim2);
        hbox2.addItem(sample);
        Arrays.stream(Personaje.presetsHeroes).forEach(personaje -> {
            if (personaje.getTipo().getSuperclass().equals(Heroe.class)) hbox2.addItem(personaje);
        });
        JComboBox<Personaje> hbox3 = new JComboBox<>(); hbox3.setPreferredSize(dim2);
        hbox3.addItem(sample);
        Arrays.stream(Personaje.presetsHeroes).forEach(personaje -> {
            if (personaje.getTipo().getSuperclass().equals(Heroe.class)) hbox3.addItem(personaje);
        });
        JComboBox<Personaje> hbox4 = new JComboBox<>(); hbox4.setPreferredSize(dim2);
        hbox4.addItem(sample);
        Arrays.stream(Personaje.presetsBestias).forEach(personaje -> {
            if (personaje.getTipo().getSuperclass().equals(Bestia.class)) hbox4.addItem(personaje);
        });
        JComboBox<Personaje> hbox5 = new JComboBox<>(); hbox5.setPreferredSize(dim2);
        hbox5.addItem(sample);
        Arrays.stream(Personaje.presetsBestias).forEach(personaje -> {
            if (personaje.getTipo().getSuperclass().equals(Bestia.class)) hbox5.addItem(personaje);
        });
        JComboBox<Personaje> hbox6 = new JComboBox<>(); hbox6.setPreferredSize(dim2);
        hbox6.addItem(sample);
        Arrays.stream(Personaje.presetsBestias).forEach(personaje -> {
            if (personaje.getTipo().getSuperclass().equals(Bestia.class)) hbox6.addItem(personaje);
        });

        Font font = new Font("Comic Sans MS", Font.BOLD, 14);
        JLabel h1 = new JLabel("Héroe 1"); h1.setFont(font);
        JLabel h2 = new JLabel("Héroe 2"); h2.setFont(font);
        JLabel h3 = new JLabel("Héroe 3"); h3.setFont(font);
        JLabel b1 = new JLabel("Bestia 1"); b1.setFont(font);
        JLabel b2 = new JLabel("Bestia 2"); b2.setFont(font);
        JLabel b3 = new JLabel("Bestia 3"); b3.setFont(font);

        JButton customizar = new JButton("Añadir Customizado"); customizar.setPreferredSize(new Dimension(500, 50));
        casillas.put(hbox1, CustomCharacterConfigurator.HEROES);
        casillas.put(hbox2, CustomCharacterConfigurator.HEROES);
        casillas.put(hbox3, CustomCharacterConfigurator.HEROES);
        casillas.put(hbox4, CustomCharacterConfigurator.BESTIAS);
        casillas.put(hbox5, CustomCharacterConfigurator.BESTIAS);
        casillas.put(hbox6, CustomCharacterConfigurator.BESTIAS);
        customizar.addActionListener(e -> new CustomCharacterConfigurator(casillas));

        Dimension eDim = new Dimension(123, 30);
        e1.setEnabled(false); e1.setPreferredSize(eDim);
        e2.setEnabled(false); e2.setPreferredSize(eDim);
        e3.setEnabled(false); e3.setPreferredSize(eDim);
        e4.setEnabled(false); e4.setPreferredSize(eDim);
        e5.setEnabled(false); e5.setPreferredSize(eDim);
        e6.setEnabled(false); e6.setPreferredSize(eDim);

        e1.addActionListener(e -> new GenerarEntidad(1));
        e2.addActionListener(e -> new GenerarEntidad(2));
        e3.addActionListener(e -> new GenerarEntidad(3));
        e4.addActionListener(e -> new GenerarEntidad(4));
        e5.addActionListener(e -> new GenerarEntidad(5));
        e6.addActionListener(e -> new GenerarEntidad(6));

        hbox1.addItemListener(e -> {
            Personaje p = (Personaje) e.getItem();
            if (p.getImagen() != null && p.getTipo() != null && p.getNombre() != null && p.getEntidad() == null) {
                posiciones.put(1, p);
                e1.setEnabled(true);
                return;
            }
            e1.setEnabled(false);
            posiciones.remove(1);
        });
        hbox2.addItemListener(e -> {
            Personaje p = (Personaje) e.getItem();
            if (p.getImagen() != null && p.getTipo() != null && p.getNombre() != null && p.getEntidad() == null) {
                posiciones.put(2, p);
                e2.setEnabled(true);
                return;
            }
            e2.setEnabled(false);
            posiciones.remove(2);
        });
        hbox3.addItemListener(e -> {
            Personaje p = (Personaje) e.getItem();
            if (p.getImagen() != null && p.getTipo() != null && p.getNombre() != null && p.getEntidad() == null) {
                posiciones.put(3, p);
                e3.setEnabled(true);
                return;
            }
            e3.setEnabled(false);
            posiciones.remove(3);
        });
        hbox4.addItemListener(e -> {
            Personaje p = (Personaje) e.getItem();
            if (p.getImagen() != null && p.getTipo() != null && p.getNombre() != null && p.getEntidad() == null) {
                posiciones.put(4, p);
                e4.setEnabled(true);
                return;
            }
            e4.setEnabled(false);
            posiciones.remove(4);
        });
        hbox5.addItemListener(e -> {
            Personaje p = (Personaje) e.getItem();
            if (p.getImagen() != null && p.getTipo() != null && p.getNombre() != null && p.getEntidad() == null) {
                posiciones.put(5, p);
                e5.setEnabled(true);
                return;
            }
            e5.setEnabled(false);
            posiciones.remove(5);
        });
        hbox6.addItemListener(e -> {
            Personaje p = (Personaje) e.getItem();
            if (p.getImagen() != null && p.getTipo() != null && p.getNombre() != null && p.getEntidad() == null) {
                posiciones.put(6, p);
                e6.setEnabled(true);
                return;
            }
            e6.setEnabled(false);
            posiciones.remove(6);
        });

        JLabel label = new JLabel("Fondo");
        JButton fcustom = new JButton("Custom");
        JComboBox<String> tfbox = new JComboBox<>();

        String f1 = "Fondo 1";
        String f2 = "Fondo 2";
        String f3 = "Fondo 3";
        tfbox.addItem(f1);
        tfbox.addItem(f2);
        tfbox.addItem(f3);

        tfbox.addItemListener(e -> {
            if (((String) e.getItem()).contains(" ")) {
                String[] s = ((String) e.getItem()).split(" ");
                fondo = CampoDeBatalla.fondosBasicos[Integer.parseInt(s[1]) - 1];
                if (fondo == null) {
                    fondo = CampoDeBatalla.fondosBasicos[1];
                    VentanaDeInformacion.mostrarVentana(VentanaDeInformacion.ERROR, "Error", "Este fondo no existe, se ha seleccionado de nuevo el valor por defecto.");
                    tfbox.setSelectedItem(f1);
                }
            } else {
                try {
                    File f = new File((String) e.getItem());
                    BufferedImage result = ImageIO.read(f);
                    if (result != null) {
                        fondo = f;
                        return;
                    }
                    throw new IOException("No se pudo leer la imagen, o no existe.");
                } catch (IOException ex) {
                    VentanaDeInformacion.mostrarVentana(VentanaDeInformacion.ERROR, "Error al leer", "Formato no compatible, se ha seleccionado el fondo por defecto.");
                    fondo = CampoDeBatalla.fondosBasicos[1];
                    tfbox.setSelectedItem(f1);
                }
            }
        });
        fcustom.addActionListener(e -> {
            FileDialog fileSelect = new FileDialog(VentanaDeConfiguracion.this);
            fileSelect.setMode(FileDialog.LOAD);
            fileSelect.setFilenameFilter((dir, name) -> name.endsWith(".png") || name.endsWith(".jpg"));
            fileSelect.setMultipleMode(false);
            VentanaDeConfiguracion.this.setVisible(false);
            fileSelect.setVisible(true);
            String file = fileSelect.getFile();
            if (file != null) {
                try {
                    tfbox.addItem(fileSelect.getDirectory() + file);
                    tfbox.setSelectedItem(fileSelect.getDirectory() + file);
                } catch (Exception i) {
                    i.printStackTrace();
                    VentanaDeInformacion.mostrarVentana(VentanaDeInformacion.ERROR, "Error", "No se puede leer el archivo. Asegúrate de que el archivo seleccionado es una imágen.");
                }
            }
            VentanaDeConfiguracion.this.setVisible(true);
        });
        fcustom.setPreferredSize(eDim);
        tfbox.setPreferredSize(dim2);

        label.setFont(font);

        Dimension sz = new Dimension(500, 50);

        JButton random = new JButton("Todo Aleatorio"); random.setPreferredSize(sz);
        random.addActionListener(e -> {
            Configuracion config = CampoDeBatalla.generarConfig();
            CampoDeBatalla.CampoDeBatallaConfig.load(config);
            VentanaPrincipal.comenzar.setEnabled(true);
            VentanaDeConfiguracion.this.setVisible(false);
        });
        JButton guardar = new JButton("Guardar Configuración"); guardar.setPreferredSize(sz);
        guardar.addActionListener(e -> {
            if (!validar()) {
                VentanaDeInformacion.mostrarVentana(VentanaDeInformacion.ERROR, "Error", "Para guardar cambios debes:", " - Especificar un personaje para cada casilla y generar su respectiva entidad.", " - Escojer un fondo.");
                return;
            }
            Configuracion config = new Configuracion((Heroe) posiciones.get(1).getEntidad(), (Heroe) posiciones.get(2).getEntidad(), (Heroe) posiciones.get(3).getEntidad(), (Bestia) posiciones.get(4).getEntidad(), (Bestia) posiciones.get(5).getEntidad(), (Bestia) posiciones.get(6).getEntidad(), fondo);
            CampoDeBatalla.CampoDeBatallaConfig.load(config);
            VentanaPrincipal.comenzar.setEnabled(true);
            VentanaDeConfiguracion.this.setVisible(false);
        });
        // Inicialización UI finalización.

        // Configuración ventana comienzo.
        add(guardar);
        add(random);
        add(label);
        add(fcustom);
        add(tfbox);
        add(h1);
        add(hbox1);
        add(h2);
        add(hbox2);
        add(h3);
        add(hbox3);
        add(b1);
        add(hbox4);
        add(b2);
        add(hbox5);
        add(b3);
        add(hbox6);
        add(customizar);
        add(e1);
        add(e2);
        add(e3);
        add(e4);
        add(e5);
        add(e6);

        layout.putConstraint(SpringLayout.NORTH, hbox1, 75, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, hbox1, 85, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, h1, 80, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, h1, 10, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, hbox2, 110, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, hbox2, 85, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, h2, 115, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, h2, 10, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, hbox3, 145, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, hbox3, 85, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, h3, 150, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, h3, 10, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, hbox4, 195, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, hbox4, 85, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, b1, 200, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, b1, 10, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, hbox5, 230, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, hbox5, 85, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, b2, 235, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, b2, 10, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, hbox6, 265, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, hbox6, 85, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, b3, 270, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, b3, 10, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, customizar, 15, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, customizar, 18, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, guardar, 460, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, guardar, 18, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, random, 395, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, random, 18, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, e1, 75, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, e1, 408, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, e2, 110, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, e2, 408, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, e3, 145, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, e3, 408, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, e4, 195, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, e4, 408, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, e5, 230, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, e5, 408, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, e6, 265, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, e6, 408, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, label, 345, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, label, 10, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, tfbox, 340, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, tfbox, 85, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, fcustom, 340, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, fcustom, 408, SpringLayout.WEST, this);
        setLocationRelativeTo(null);
        pack();
        // Configuración ventana fin.

        setVisible(true);
    }

    private boolean validar() {
        return posiciones.get(1) != null && posiciones.get(2) != null && posiciones.get(3) != null && posiciones.get(4) != null && posiciones.get(5) != null && posiciones.get(6) != null && fondo != null && posiciones.get(1).getEntidad() != null && posiciones.get(2).getEntidad() != null && posiciones.get(3).getEntidad() != null && posiciones.get(4).getEntidad() != null && posiciones.get(5).getEntidad() != null && posiciones.get(6).getEntidad() != null;
    }

    /**
     * Diálogo para configurar personaje.
     */
    private static class CustomCharacterConfigurator extends JFrame implements ItemListener {

        private final Personaje personaje = new Personaje();

        public static final int HEROES = 0;
        public static final int BESTIAS = 1;

        private CustomCharacterConfigurator(Map<JComboBox<Personaje>, Integer> casillas) {
            Dimension dim = new Dimension(540, 370);
            SpringLayout layout = new SpringLayout();
            setLayout(layout);
            setSize(dim);
            setPreferredSize(dim);
            setResizable(false);
            setAlwaysOnTop(true);
            setTitle("Customización de Personaje");
            setIconImage(VentanaPrincipal.icon);

            Font font = new Font("Comic Sans MS", Font.BOLD, 14);

            JLabel nombreL = new JLabel("Nombre:");
            JLabel imagenL = new JLabel("Imagen:");
            JLabel tipoL = new JLabel("Tipo:");
            nombreL.setFont(font);
            imagenL.setFont(font);
            tipoL.setFont(font);

            Dimension d = new Dimension(340, 30);

            JTextField nField = new JTextField();
            JLabel imgLabel = new JLabel("Escoje una imagen.");
            imgLabel.setBorder(BasicBorders.getTextFieldBorder());
            imgLabel.setPreferredSize(d);
            JButton imgButton = new JButton("Escojer");
            imgButton.setPreferredSize(new Dimension(100, 40));
            JComboBox<String> tipos = new JComboBox<>(new String[] {"Humano", "Hobbit", "Elfo", "Orco", "Trasgo"});
            nField.setPreferredSize(d);
            tipos.setPreferredSize(d);
            tipos.addItemListener(this);
            JButton guardar = new JButton("Guardar");
            guardar.setPreferredSize(new Dimension(300, 55));
            personaje.setTipo(Humano.class);
            guardar.addActionListener(e -> {
                if (personaje.getTipo() == null) {
                    VentanaDeInformacion.mostrarVentana(VentanaDeInformacion.ERROR, "Error", "Debes seleccionar el tipo.");
                    return;
                }
                if (personaje.getImagen() == null) {
                    VentanaDeInformacion.mostrarVentana(VentanaDeInformacion.ERROR, "Error", "Debes seleccionar una imagen para el personaje.");
                    return;
                }
                if (nField.getText() == null || nField.getText().isBlank() || !nField.getText().matches("[a-zA-Z]+")) {
                    VentanaDeInformacion.mostrarVentana(VentanaDeInformacion.ERROR, "Error", "Introduzca un nombre válido para el personaje.");
                    return;
                }
                personaje.setNombre(nField.getText());
                if (personaje.getTipo().getSuperclass().equals(Heroe.class)) {
                    casillas.forEach((personajeJComboBox, integer) -> {
                        if (integer == HEROES) {
                            personajeJComboBox.addItem(personaje);
                        }
                    });
                } else if (personaje.getTipo().getSuperclass().equals(Bestia.class)) {
                    casillas.forEach((personajeJComboBox, integer) -> {
                        if (integer == BESTIAS) {
                            personajeJComboBox.addItem(personaje);
                        }
                    });
                }
                setVisible(false);
            });

            imgButton.addActionListener(e -> {
                FileDialog dialog = new FileDialog(CustomCharacterConfigurator.this);
                dialog.setMode(FileDialog.LOAD);
                dialog.setFilenameFilter((dir, name) -> name.endsWith(".png") || name.endsWith(".jpg"));
                CustomCharacterConfigurator.this.setVisible(false);
                dialog.setVisible(true);
                if (dialog.getFile() != null) {
                    File file = new File(dialog.getDirectory(), dialog.getFile());
                    imgLabel.setText(file.getAbsolutePath());
                    try {
                        BufferedImage img;
                        if ((img = ImageIO.read(file)) == null) throw new IOException("No se puede leer la imágen.");
                        personaje.setImagen(img);
                    } catch (IOException o) {
                        VentanaDeInformacion.mostrarVentana(VentanaDeInformacion.ERROR, "Error al leer imágen", "Ha habido un error al leer la imágen.", "Asegúrate de que el archivo es una imágen y no está corrupto.");
                    }
                }
                CustomCharacterConfigurator.this.setVisible(true);
            });

            add(nombreL);
            add(nField);
            add(imagenL);
            add(imgLabel);
            add(imgButton);
            add(tipoL);
            add(tipos);
            add(guardar);

            layout.putConstraint(SpringLayout.NORTH, nombreL, 60, SpringLayout.NORTH, this);
            layout.putConstraint(SpringLayout.WEST, nombreL, 10, SpringLayout.WEST, this);
            layout.putConstraint(SpringLayout.NORTH, imagenL, 110, SpringLayout.NORTH, this);
            layout.putConstraint(SpringLayout.WEST, imagenL, 10, SpringLayout.WEST, this);
            layout.putConstraint(SpringLayout.NORTH, tipoL, 160, SpringLayout.NORTH, this);
            layout.putConstraint(SpringLayout.WEST, tipoL, 10, SpringLayout.WEST, this);

            layout.putConstraint(SpringLayout.NORTH, nField, 52, SpringLayout.NORTH, this);
            layout.putConstraint(SpringLayout.WEST, nField, 80, SpringLayout.WEST, this);
            layout.putConstraint(SpringLayout.NORTH, imgLabel, 102, SpringLayout.NORTH, this);
            layout.putConstraint(SpringLayout.WEST, imgLabel, 80, SpringLayout.WEST, this);
            layout.putConstraint(SpringLayout.NORTH, imgButton, 99, SpringLayout.NORTH, this);
            layout.putConstraint(SpringLayout.WEST, imgButton, 425, SpringLayout.WEST, this);
            layout.putConstraint(SpringLayout.NORTH, tipos, 152, SpringLayout.NORTH, this);
            layout.putConstraint(SpringLayout.WEST, tipos, 80, SpringLayout.WEST, this);
            layout.putConstraint(SpringLayout.NORTH, guardar, 240, SpringLayout.NORTH, this);
            layout.putConstraint(SpringLayout.WEST, guardar, 120, SpringLayout.WEST, this);

            setLocationRelativeTo(null); // Centrar
            pack();
            setVisible(true);
        }

        @Override
        public void itemStateChanged(ItemEvent e) {
            switch ((String) e.getItem()) {
                case "Humano": personaje.setTipo(Humano.class); break;
                case "Hobbit": personaje.setTipo(Hobbit.class); break;
                case "Elfo": personaje.setTipo(Elfo.class); break;
                case "Orco": personaje.setTipo(Orco.class); break;
                case "Trasgo": personaje.setTipo(Trasgo.class); break;
            }
        }
    }

    /**
     * Diálogo para definir características de los personajes.
     */
    class GenerarEntidad extends JFrame {

        private final int posicion;

        private final JLabel info = new JLabel(" - Todos los campos son obligatorios.");

        private final JLabel reqVida = new JLabel("*- ¡Debe ser un número entero entre 50 - 500!");
        private final JLabel label = new JLabel("Vida");
        private final JTextField vida = new JTextField();
        private final JLabel reqResistencia = new JLabel("*- ¡Debe ser un número entero entre 10 - 60!");
        private final JLabel label1 = new JLabel("Resistencia");
        private final JTextField resistencia = new JTextField();
        private final JButton okButton = new JButton("Ok");

        public GenerarEntidad(int posicion) {
            this.posicion = posicion;
            setAlwaysOnTop(true);
            setResizable(false);
            SpringLayout layout = new SpringLayout();
            setLayout(layout);
            Dimension sdim = new Dimension(480, 300);
            setSize(sdim);
            setPreferredSize(sdim);
            Dimension dim = new Dimension(300, 30);
            Font font = new Font(Font.SANS_SERIF, Font.BOLD, 13);
            vida.setPreferredSize(dim);
            resistencia.setPreferredSize(dim);
            info.setFont(font);
            label.setFont(font);
            label1.setFont(font);

            okButton.setPreferredSize(new Dimension(140, 40));
            okButton.addActionListener(e -> generarEntidad());

            add(info);
            add(reqVida);
            add(label);
            add(vida);
            add(reqResistencia);
            add(label1);
            add(resistencia);
            add(okButton);

            layout.putConstraint(SpringLayout.WEST, info, 90, SpringLayout.WEST, this);
            layout.putConstraint(SpringLayout.NORTH, info, 20, SpringLayout.NORTH, this);

            layout.putConstraint(SpringLayout.WEST, label, 8, SpringLayout.WEST, this);
            layout.putConstraint(SpringLayout.NORTH, label, 65, SpringLayout.NORTH, this);
            layout.putConstraint(SpringLayout.WEST, vida, 95, SpringLayout.WEST, this);
            layout.putConstraint(SpringLayout.NORTH, vida, 60, SpringLayout.NORTH, this);
            layout.putConstraint(SpringLayout.WEST, reqVida, 90, SpringLayout.WEST, this);
            layout.putConstraint(SpringLayout.NORTH, reqVida, 31, SpringLayout.NORTH, vida);

            layout.putConstraint(SpringLayout.WEST, label1, 8, SpringLayout.WEST, this);
            layout.putConstraint(SpringLayout.NORTH, label1, 145, SpringLayout.NORTH, this);
            layout.putConstraint(SpringLayout.WEST, resistencia, 95, SpringLayout.WEST, this);
            layout.putConstraint(SpringLayout.NORTH, resistencia, 140, SpringLayout.NORTH, this);
            layout.putConstraint(SpringLayout.WEST, reqResistencia, 90, SpringLayout.WEST, this);
            layout.putConstraint(SpringLayout.NORTH, reqResistencia, 31, SpringLayout.NORTH, resistencia);

            layout.putConstraint(SpringLayout.WEST, okButton, 150, SpringLayout.WEST, this);
            layout.putConstraint(SpringLayout.NORTH, okButton, 210, SpringLayout.NORTH, this);

            pack();
            setLocationRelativeTo(null); // Centrar
            setVisible(true);
        }

        public void generarEntidad() {
            if (!esValido()) {
                VentanaDeInformacion.mostrarVentana(VentanaDeInformacion.ERROR, "Datos incorrectos", "Los datos introducidos no cumplen los requisitos.");
                return;
            }
            try {
                Personaje personaje = posiciones.get(posicion);
                Entidad entidad = personaje.getTipo().getConstructor(Personaje.class, int.class, int.class).newInstance(personaje, Integer.parseInt(vida.getText()), Integer.parseInt(resistencia.getText()));
                personaje.setEntidad(entidad);
                entidad.setPersonaje(personaje);
                switch (posicion) {
                    case 1: e1.setEnabled(false); break;
                    case 2: e2.setEnabled(false); break;
                    case 3: e3.setEnabled(false); break;
                    case 4: e4.setEnabled(false); break;
                    case 5: e5.setEnabled(false); break;
                    case 6: e6.setEnabled(false); break;
                }
                setVisible(false);
                VentanaDeInformacion.mostrarVentana(VentanaDeInformacion.INFO, "Información Personaje", "Ataque: " + entidad.getDamage() + " (auto gen)", "Vida: " + entidad.getVida() + " ❤", "Resistencia: " + entidad.getResistencia(), "Habilidades: " + entidad.getHabilidades().toString());

            } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException |
                     InstantiationException e) {
                e.printStackTrace();
            }
        }

        /**
         * Comprobar información.
         * @return true si la información es correcta/válida.
         */
        private boolean esValido() {
            return vida.getText().matches("[0-9]+") && resistencia.getText().matches("[0-9]+") && Integer.parseInt(vida.getText()) >= 50 && Integer.parseInt(vida.getText()) <= 500 && Integer.parseInt(resistencia.getText()) >= 10 && Integer.parseInt(resistencia.getText()) <= 60;
        }
    }
}
