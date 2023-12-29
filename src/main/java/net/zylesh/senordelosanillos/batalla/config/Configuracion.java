package net.zylesh.senordelosanillos.batalla.config;

import net.zylesh.senordelosanillos.common.Bestia;
import net.zylesh.senordelosanillos.common.Heroe;

import java.awt.image.BufferedImage;

/**
 * Clase simple cuyo objeto solo almacena un setup. Lo hice con la intención de poder guardar en archivos las configuraciones, algo que quizás se podría implementar y me olvidé. TODO
 */
public class Configuracion {

    private final Heroe heroe1;
    private final Heroe heroe2;
    private final Heroe heroe3;
    private final Bestia bestia1;
    private final Bestia bestia2;
    private final Bestia bestia3;
    private final BufferedImage fondo;

    public Configuracion(Heroe heroe1, Heroe heroe2, Heroe heroe3, Bestia bestia1, Bestia bestia2, Bestia bestia3, BufferedImage fondo) {
        this.heroe1 = heroe1;
        this.heroe2 = heroe2;
        this.heroe3 = heroe3;
        this.bestia1 = bestia1;
        this.bestia2 = bestia2;
        this.bestia3 = bestia3;
        this.fondo = fondo;
    }

    public Heroe getHeroe1() {
        return heroe1;
    }

    public Heroe getHeroe2() {
        return heroe2;
    }

    public Heroe getHeroe3() {
        return heroe3;
    }

    public Bestia getBestia1() {
        return bestia1;
    }

    public Bestia getBestia2() {
        return bestia2;
    }

    public Bestia getBestia3() {
        return bestia3;
    }

    public BufferedImage getFondo() {
        return fondo;
    }
}
