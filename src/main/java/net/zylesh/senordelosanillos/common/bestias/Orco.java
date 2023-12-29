package net.zylesh.senordelosanillos.common.bestias;

import net.zylesh.senordelosanillos.common.Habilidad;
import net.zylesh.senordelosanillos.common.Bestia;
import net.zylesh.senordelosanillos.common.Personaje;

public final class Orco extends Bestia {

    public Orco(Personaje personaje, int vida, int resistencia) {
        super(personaje, vida, resistencia);

        // Habilidad de fuerza desmesurada.
        this.habilidades.add(new Habilidad("Fuerza Desmesurada") {
            @Override
            public int extraDamage() {
                return this.getAfectado() != null ? (this.getAfectado().getResistencia() * 90) / 100 : 0;
            }
        });
    }
}
