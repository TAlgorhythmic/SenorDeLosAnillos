package net.zylesh.senordelosanillos.common.heroes;

import net.zylesh.senordelosanillos.common.Habilidad;
import net.zylesh.senordelosanillos.common.Heroe;
import net.zylesh.senordelosanillos.common.Personaje;
import net.zylesh.senordelosanillos.common.bestias.Orco;

public final class Elfo extends Heroe {

    public Elfo(Personaje personaje, int vida, int resistencia) {
        super(personaje, vida, resistencia);
        this.identificadorTipo = "Elfo";
        this.habilidades.add(new Habilidad("Odio") {
            @Override
            public int extraDamage() {
                return this.getAfectado() instanceof Orco ? +10 : 0;
            }
        });
    }
}
