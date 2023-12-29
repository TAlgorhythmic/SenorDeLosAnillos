package net.zylesh.senordelosanillos.common;

import net.zylesh.senordelosanillos.common.bestias.Orco;
import net.zylesh.senordelosanillos.common.bestias.Trasgo;
import net.zylesh.senordelosanillos.common.heroes.Elfo;
import net.zylesh.senordelosanillos.common.heroes.Hobbit;
import net.zylesh.senordelosanillos.common.heroes.Humano;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Personaje {

    // Personajes predefinidos, lo cambié a arrays (había puesto HashSets por algún motivo). ¡NO MODIFICAR SU CONTENIDO O EL PROGRAMA DARÁ ERRORES!
    public static final Personaje[] presetsHeroes = new Personaje[6];
    public static final Personaje[] presetsBestias = new Personaje[4];
    static {
        try {
            presetsHeroes[0] = new Personaje("Gandalf", Humano.class, Personaje.class.getClassLoader().getResourceAsStream("assets" + File.separator + "juego" + File.separator + "personajes" + File.separator + "gandalf.png"));
            presetsHeroes[1] = new Personaje("Aragorn", Humano.class, Personaje.class.getClassLoader().getResourceAsStream("assets" + File.separator + "juego" + File.separator + "personajes" + File.separator + "aragorn.png"));
            presetsHeroes[2] = new Personaje("Frodo", Hobbit.class, Personaje.class.getClassLoader().getResourceAsStream("assets" + File.separator + "juego" + File.separator + "personajes" + File.separator + "frodo.png"));
            presetsHeroes[3] = new Personaje("Légolas", Elfo.class, Personaje.class.getClassLoader().getResourceAsStream("assets" + File.separator + "juego" + File.separator + "personajes" + File.separator + "legolas.png"));
            presetsHeroes[4] = new Personaje("Samwise", Hobbit.class, Personaje.class.getClassLoader().getResourceAsStream("assets" + File.separator + "juego" + File.separator + "personajes" + File.separator + "samwise.png"));
            presetsHeroes[5] = new Personaje("Elrond", Elfo.class, Personaje.class.getClassLoader().getResourceAsStream("assets" + File.separator + "juego" + File.separator + "personajes" + File.separator + "elrond.png"));
            presetsBestias[0] = new Personaje("Orco 1", Orco.class, Personaje.class.getClassLoader().getResourceAsStream("assets" + File.separator + "juego" + File.separator + "personajes" + File.separator + "orco1.png"));
            presetsBestias[1] = new Personaje("Orco 2", Orco.class, Personaje.class.getClassLoader().getResourceAsStream("assets" + File.separator + "juego" + File.separator + "personajes" + File.separator + "orco2.png"));
            presetsBestias[2] = new Personaje("Orco 3", Orco.class, Personaje.class.getClassLoader().getResourceAsStream("assets" + File.separator + "juego" + File.separator + "personajes" + File.separator + "orco3.png"));
            presetsBestias[3] = new Personaje("Trasgo", Trasgo.class, Personaje.class.getClassLoader().getResourceAsStream("assets" + File.separator + "juego" + File.separator + "personajes" + File.separator + "trasgo1.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String nombre;
    private BufferedImage imagen;
    private String tipo;
    private Entidad entidad;

    public Personaje() {}

    public Personaje(String nombre, Class<? extends Entidad> tipo, InputStream imagen) throws IOException {
        this.nombre = nombre;
        this.imagen = ImageIO.read(imagen);
        this.tipo = tipo.getName();
    }

    public String getNombre() {
        return nombre;
    }

    @SuppressWarnings("unchecked")
    public Class<? extends Entidad> getTipo() {
        try {
            return (Class<? extends Entidad>) Class.forName(tipo);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public BufferedImage getImagen() {
        return imagen;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTipo(Class<? extends Entidad> tipo) {
        this.tipo = tipo.getName();
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setImagen(BufferedImage imagen) {
        this.imagen = imagen;
    }

    public void setEntidad(Entidad entidad) {
        this.entidad = entidad;
    }

    public Entidad getEntidad() {
        return entidad;
    }

    @Override
    public String toString() {
        try {
            return nombre + " (" + Class.forName(tipo).getSimpleName() + ")";
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return nombre + " (" + tipo + ")";
    }
}
