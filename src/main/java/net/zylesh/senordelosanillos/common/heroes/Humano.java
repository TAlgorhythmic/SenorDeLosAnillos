package net.zylesh.senordelosanillos.common.heroes;

import net.zylesh.senordelosanillos.common.Heroe;
import net.zylesh.senordelosanillos.common.Personaje;

public final class Humano extends Heroe {

    public Humano(Personaje personaje, int vida, int resistencia) {
        super(personaje, vida, resistencia);
        this.identificadorTipo = "Humano";
    }
}
