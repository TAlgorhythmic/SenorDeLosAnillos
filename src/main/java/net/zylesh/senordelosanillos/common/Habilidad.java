package net.zylesh.senordelosanillos.common;

import net.zylesh.senordelosanillos.common.debuffos.DeBuffo;

import java.util.List;

/**
 * Clase de habilidades especiales, tienen soporte para mejorar o empeorar daño, resistencia e inflingir buffos/debuffos
 * Al instanciar debe sobreescribir extraDamage(), extraResistance() o getBuffos().
 */
public abstract class Habilidad {

    private Entidad afectado;
    private final String nombre;

    public Habilidad(String nombre) {
        this.nombre = nombre;
    }

    public final String getNombre() {
        return nombre;
    }

    // Must override
    public int extraDamage() {
        return 0;
    }

    // Must override
    public int extraResistance() {
        return 0;
    }

    // Must override
    public List<DeBuffo> getBuffos() {
        return null;
    }

    public void setAfectado(Entidad victima) {
        this.afectado = victima;
    }

    public Entidad getAfectado() {
        return afectado;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Habilidad)) return false;
        Habilidad o = (Habilidad) obj;
        return extraDamage() == o.extraDamage() && extraResistance() == o.extraResistance();
    }

    @Override
    public int hashCode() {
        return (extraDamage() * 31) + (extraResistance() * 31);
    }
}
