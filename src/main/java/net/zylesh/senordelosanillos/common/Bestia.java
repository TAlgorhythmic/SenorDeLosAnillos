package net.zylesh.senordelosanillos.common;

public abstract class Bestia extends Entidad {

    protected Bestia(Personaje personaje, int vida, int resistencia) {
        super(personaje, vida, resistencia);
        this.damage = generarAtaque();
    }

    @Override
    public int generarAtaque() {
        return (int) Math.round(Math.random() * 90);
    }
}
