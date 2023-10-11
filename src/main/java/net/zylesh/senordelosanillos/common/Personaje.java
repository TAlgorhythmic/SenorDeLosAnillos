package net.zylesh.senordelosanillos.common;

import net.zylesh.senordelosanillos.common.bestias.Orco;
import net.zylesh.senordelosanillos.common.bestias.Trasgo;
import net.zylesh.senordelosanillos.common.heroes.Elfo;
import net.zylesh.senordelosanillos.common.heroes.Hobbit;
import net.zylesh.senordelosanillos.common.heroes.Humano;

import java.io.File;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class Personaje {

    public static final Set<Personaje> presets;
    static {
        Set<Personaje> p = new LinkedHashSet<>();
        try {
            Personaje p1 = new Personaje("Gandalf", Humano.class, new File(Personaje.class.getClassLoader().getResource("assets" + File.separator + "juego" + File.separator + "personajes" + File.separator + "gandalf.png").toURI()));
            Personaje p2 = new Personaje("Aragorn", Humano.class, new File(Personaje.class.getClassLoader().getResource("assets" + File.separator + "juego" + File.separator + "personajes" + File.separator + "aragorn.png").toURI()));
            Personaje p3 = new Personaje("Frodo", Hobbit.class, new File(Personaje.class.getClassLoader().getResource("assets" + File.separator + "juego" + File.separator + "personajes" + File.separator + "frodo.png").toURI()));
            Personaje p4 = new Personaje("Légolas", Elfo.class, new File(Personaje.class.getClassLoader().getResource("assets" + File.separator + "juego" + File.separator + "personajes" + File.separator + "legolas.png").toURI()));
            Personaje p5 = new Personaje("Samwise", Hobbit.class, new File(Personaje.class.getClassLoader().getResource("assets" + File.separator + "juego" + File.separator + "personajes" + File.separator + "samwise.png").toURI()));
            Personaje p6 = new Personaje("Elrond", Elfo.class, new File(Personaje.class.getClassLoader().getResource("assets" + File.separator + "juego" + File.separator + "personajes" + File.separator + "elrond.png").toURI()));
            Personaje p7 = new Personaje("Orco 1", Orco.class, new File(Personaje.class.getClassLoader().getResource("assets" + File.separator + "juego" + File.separator + "personajes" + File.separator + "orco1.png").toURI()));
            Personaje p8 = new Personaje("Orco 2", Orco.class, new File(Personaje.class.getClassLoader().getResource("assets" + File.separator + "juego" + File.separator + "personajes" + File.separator + "orco2.png").toURI()));
            Personaje p9 = new Personaje("Orco 3", Orco.class, new File(Personaje.class.getClassLoader().getResource("assets" + File.separator + "juego" + File.separator + "personajes" + File.separator + "orco3.png").toURI()));
            Personaje p10 = new Personaje("Trasgo", Trasgo.class, new File(Personaje.class.getClassLoader().getResource("assets" + File.separator + "juego" + File.separator + "personajes" + File.separator + "trasgo1.png").toURI()));
            Collections.addAll(p, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10);
        } catch (Exception e) {
            e.printStackTrace();
        }
        presets = Collections.unmodifiableSet(p);
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
