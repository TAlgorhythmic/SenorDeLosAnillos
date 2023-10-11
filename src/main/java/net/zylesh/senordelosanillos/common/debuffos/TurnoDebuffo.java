package net.zylesh.senordelosanillos.common.debuffos;

import net.zylesh.senordelosanillos.common.Entidad;

@FunctionalInterface
public interface TurnoDebuffo extends DeBuffo {
    void action(Entidad victima);
}
