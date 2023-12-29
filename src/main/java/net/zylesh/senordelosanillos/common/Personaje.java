package net.zylesh.senordelosanillos.common;

import net.zylesh.senordelosanillos.common.bestias.Orco;
import net.zylesh.senordelosanillos.common.bestias.Trasgo;
import net.zylesh.senordelosanillos.common.heroes.Elfo;
import net.zylesh.senordelosanillos.common.heroes.Hobbit;
import net.zylesh.senordelosanillos.common.heroes.Humano;

import java.io.File;

public class Personaje {

    // Personajes predefinidos, lo cambié a arrays (había puesto HashSets por algún motivo). ¡NO MODIFICAR SU CONTENIDO O EL PROGRAMA DARÁ ERRORES!
    public static final Personaje[] presetsHeroes = new Personaje[6];
    public static final Personaje[] presetsBestias = new Personaje[4];
    static {
        try {
            presetsHeroes[0] = new Personaje("Gandalf", Humano.class, new File(Personaje.class.getClassLoader().getResource("assets" + File.separator + "juego" + File.separator + "personajes" + File.separator + "gandalf.png").toURI()));
            presetsHeroes[1] = new Personaje("Aragorn", Humano.class, new File(Personaje.class.getClassLoader().getResource("assets" + File.separator + "juego" + File.separator + "personajes" + File.separator + "aragorn.png").toURI()));
            presetsHeroes[2] = new Personaje("Frodo", Hobbit.class, new File(Personaje.class.getClassLoader().getResource("assets" + File.separator + "juego" + File.separator + "personajes" + File.separator + "frodo.png").toURI()));
            presetsHeroes[3] = new Personaje("Légolas", Elfo.class, new File(Personaje.class.getClassLoader().getResource("assets" + File.separator + "juego" + File.separator + "personajes" + File.separator + "legolas.png").toURI()));
            presetsHeroes[4] = new Personaje("Samwise", Hobbit.class, new File(Personaje.class.getClassLoader().getResource("assets" + File.separator + "juego" + File.separator + "personajes" + File.separator + "samwise.png").toURI()));
            presetsHeroes[5] = new Personaje("Elrond", Elfo.class, new File(Personaje.class.getClassLoader().getResource("assets" + File.separator + "juego" + File.separator + "personajes" + File.separator + "elrond.png").toURI()));
            presetsBestias[0] = new Personaje("Orco 1", Orco.class, new File(Personaje.class.getClassLoader().getResource("assets" + File.separator + "juego" + File.separator + "personajes" + File.separator + "orco1.png").toURI()));
            presetsBestias[1] = new Personaje("Orco 2", Orco.class, new File(Personaje.class.getClassLoader().getResource("assets" + File.separator + "juego" + File.separator + "personajes" + File.separator + "orco2.png").toURI()));
            presetsBestias[2] = new Personaje("Orco 3", Orco.class, new File(Personaje.class.getClassLoader().getResource("assets" + File.separator + "juego" + File.separator + "personajes" + File.separator + "orco3.png").toURI()));
            presetsBestias[3] = new Personaje("Trasgo", Trasgo.class, new File(Personaje.class.getClassLoader().getResource("assets" + File.separator + "juego" + File.separator + "personajes" + File.separator + "trasgo1.png").toURI()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String nombre;
    private File imagen;
    private String tipo;
    private Entidad entidad;

    public Personaje() {}

    public Personaje(String nombre, Class<? extends Entidad> tipo, File imagen) {
        this.nombre = nombre;
        this.imagen = imagen;
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

    public File getImagen() {
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

    public void setImagen(File imagen) {
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
