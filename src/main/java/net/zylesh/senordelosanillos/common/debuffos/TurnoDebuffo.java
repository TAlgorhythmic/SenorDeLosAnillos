package net.zylesh.senordelosanillos.common.debuffos;

import net.zylesh.senordelosanillos.common.Entidad;

// FIX: al parecer esta interfaz no estaba siendo usada en ningún sitio.
/**
 * Interfaz para crear un debuffo de tipo turno.
 */
@FunctionalInterface
public interface TurnoDebuffo extends DeBuffo {

    /**
     * Este método se ejecutará al inicio del turno del afectado.
     * @param victima La entidad afectada.
     */
    void action(Entidad victima);
}
