package net.zylesh.senordelosanillos.common.debuffos;

import net.zylesh.senordelosanillos.common.Entidad;

/**
 * Interfaz para crear un debuffo de tipo ataque.
 */
@FunctionalInterface
public interface AtaqueDebuffo extends DeBuffo {

    /**
     * Este método se ejecutará cada vez que la victima reciba daño.
     * @param victima La entidad afectada.
     */
    void action(Entidad victima);
}
