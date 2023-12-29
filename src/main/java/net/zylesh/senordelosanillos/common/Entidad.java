package net.zylesh.senordelosanillos.common;

import net.zylesh.senordelosanillos.common.debuffos.AtaqueDebuffo;
import net.zylesh.senordelosanillos.common.debuffos.DeBuffo;
import net.zylesh.senordelosanillos.juego.GameWindow;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Clase padre para todos los tipos de entidad. Maneja la vida, la resistencia, el daño y guarda una instancia de los atributos de personaje (por mera dependencia)
 */
public abstract class Entidad {

    private Personaje personaje;
    protected int vida;
    protected int resistencia;
    protected int damage;
    protected final Set<DeBuffo> deBuffos = new HashSet<>();
    protected final Set<Habilidad> habilidades = new HashSet<>() {
        @Override
        public String toString() {
            return this.isEmpty() ? "Sin habilidades." : toString0(this);
        }
    };
    protected boolean isAlive = true;

    private static String toString0(Set<Habilidad> lista) {
        StringBuilder builder = new StringBuilder();
        for (Habilidad hab : lista) {
            builder.append(hab.getNombre()).append(", ");
        }
        builder.replace(builder.length() - 2, builder.length() - 1, ".");
        return builder.toString();
    }

    Entidad(Personaje personaje, int vida, int resistencia) {
        this.personaje = personaje;
        this.vida = vida;
        this.resistencia = resistencia;
    }

    public int getVida() {
        return vida;
    }

    public Set<Habilidad> getHabilidades() {
        return habilidades;
    }

    public String getNombre() {
        return personaje.getNombre();
    }

    public boolean isAlive() {
        return isAlive;
    }

    public Set<DeBuffo> getDeBuffos() {
        return deBuffos;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public void atacarConOutput(Entidad victima) {
        int damage = atacar(victima);
        if (damage > 0) {
            GameWindow.g().nuevoOutput("La entidad " + this.getNombre() + " ha atacado satisfactoriamente a " + victima.getNombre() + " (-" + damage + " ♥).");
        } else {
            GameWindow.g().nuevoOutput("La entidad " + this.getNombre() + " ha atacado, pero " + victima.getNombre() + " ha resistido el ataque. \uD83D\uDCAA");
        }
        if (!victima.deBuffos.isEmpty()) {
            GameWindow.g().nuevoOutput(victima.getNombre() + " tiene " + victima.deBuffos.size() + " debuffo(s). ¡Cuidado!");
        }
        if (!victima.isAlive()) {
            GameWindow.g().nuevoOutput(victima.getNombre() + " ha muerto.");
        }
    }

    public int atacar(Entidad victima) {
        int modifiedDamage = damage;
        List<DeBuffo> buffosOcasionados = new ArrayList<>();
        for (Habilidad a : habilidades) {
            a.setAfectado(victima);
            modifiedDamage += a.extraDamage();
            if (a.getBuffos() != null && !a.getBuffos().isEmpty())
                buffosOcasionados.addAll(a.getBuffos());
        }
        return victima.damage(modifiedDamage, buffosOcasionados);
    }

    private int damage(int damage, List<DeBuffo> deBuffos) {
        int resistenciaUsual = resistencia;
        if (!this.deBuffos.isEmpty()) deBuffos.forEach(deBuffo -> {
            if (deBuffo instanceof AtaqueDebuffo) ((AtaqueDebuffo) deBuffo).action(this);
        });
        this.deBuffos.addAll(deBuffos);
        if (damage > resistencia) {
            int finalDamage = damage - resistencia;
            vida -= finalDamage;
            resistencia = resistenciaUsual;
            if (vida <= 0) setAlive(false);
            return finalDamage;
        }
        resistencia = resistenciaUsual;
        return 0;
    }

    public int getResistencia() {
        return resistencia;
    }

    public int getDamage() {
        return damage;
    }

    public abstract int generarAtaque();

    public Personaje getPersonaje() {
        return personaje;
    }

    public void setPersonaje(Personaje personaje) {
        this.personaje = personaje;
    }
}
