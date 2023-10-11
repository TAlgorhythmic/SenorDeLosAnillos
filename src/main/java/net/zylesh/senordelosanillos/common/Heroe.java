package net.zylesh.senordelosanillos.common;

public abstract class Heroe extends Entidad {

    protected Heroe(Personaje personaje, int vida, int resistencia) {
        super(personaje, vida, resistencia);
        this.damage = generarAtaque();
    }

    @Override
    public int generarAtaque() {
        return (int) Math.round(Math.random() * 100);
    }
}
