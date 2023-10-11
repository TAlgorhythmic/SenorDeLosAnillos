package net.zylesh.senordelosanillos.common.bestias;

import net.zylesh.senordelosanillos.common.Bestia;
import net.zylesh.senordelosanillos.common.Personaje;

public final class Trasgo extends Bestia {

    public Trasgo(Personaje personaje, int vida, int resistencia) {
        super(personaje, vida, resistencia);
        this.identificadorTipo = "Trasgo";
    }
}
