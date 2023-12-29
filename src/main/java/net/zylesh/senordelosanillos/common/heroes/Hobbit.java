package net.zylesh.senordelosanillos.common.heroes;

import net.zylesh.senordelosanillos.common.Habilidad;
import net.zylesh.senordelosanillos.common.Heroe;
import net.zylesh.senordelosanillos.common.Personaje;
import net.zylesh.senordelosanillos.common.bestias.Trasgo;

public final class Hobbit extends Heroe {

    public Hobbit(Personaje personaje, int vida, int resistencia) {
        super(personaje, vida, resistencia);
        this.habilidades.add(new Habilidad("Miedo") {
            @Override
            public int extraDamage() {
                return this.getAfectado() instanceof Trasgo ? -5 : 0;
            }
        });
    }
}
