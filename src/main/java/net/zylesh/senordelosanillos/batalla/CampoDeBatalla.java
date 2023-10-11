package net.zylesh.senordelosanillos.batalla;

import net.zylesh.senordelosanillos.Core;
import net.zylesh.senordelosanillos.batalla.config.Configuracion;
import net.zylesh.senordelosanillos.common.Bestia;
import net.zylesh.senordelosanillos.common.Entidad;
import net.zylesh.senordelosanillos.common.Heroe;
import net.zylesh.senordelosanillos.common.Personaje;
import net.zylesh.senordelosanillos.juego.GameWindow;
import net.zylesh.senordelosanillos.ui.VentanaDeInformacion;
import net.zylesh.senordelosanillos.ui.VentanaPrincipal;

import java.io.File;
import java.lang.reflect.Constructor;
import java.util.*;

public final class CampoDeBatalla {

    public static final Map<Integer, File> fondosBasicos = new HashMap<>();
    static {
        try {
            File background1 = new File(CampoDeBatalla.class.getClassLoader().getResource("assets" + File.separator + "juego" + File.separator + "fondos" + File.separator + "background1.jpg").toURI());
            File background2 = new File(CampoDeBatalla.class.getClassLoader().getResource("assets" + File.separator + "juego" + File.separator + "fondos" + File.separator + "background2.jpg").toURI());
            File background3 = new File(CampoDeBatalla.class.getClassLoader().getResource("assets" + File.separator + "juego" + File.separator + "fondos" + File.separator + "background3.jpg").toURI());
            fondosBasicos.put(1, background1);
            fondosBasicos.put(2, background2);
            fondosBasicos.put(3, background3);
        } catch (Exception e) {
            Core.log(e.getMessage(), true);
            VentanaDeInformacion.mostrarVentana(VentanaDeInformacion.ERROR, "Error", "El archivo del ejecutable probablemente esté corrupto/haya sido modificado.");
        }
    }

    private static final Random r = new Random();

    public static Configuracion generarConfig() {
        Personaje ph1 = null;
        Personaje ph2 = null;
        Personaje ph3 = null;
        int max = 6;
        int from = 0;
        int random = r.nextInt(max);
        int i = from;
        int position = 1;
        while (ph1 == null || ph2 == null || ph3 == null) {
            for (Personaje p : Personaje.presets) {
                if (i == random) {
                    switch (position) {
                        case 1: ph1 = p; break;
                        case 2: ph2 = p; break;
                        case 3: ph3 = p; break;
                    }
                    position++;
                    i = from;
                    break;
                }
                i++;
            }
            random = r.nextInt(max);
        }

        Personaje pb1 = null;
        Personaje pb2 = null;
        Personaje pb3 = null;
        from = 6;
        i = from;
        random = r.nextInt(max) + 4;
        position = 1;
        while (pb1 == null || pb2 == null || pb3 == null) {
            for (Personaje p : Personaje.presets) {
                if (!p.getTipo().getSuperclass().equals(Bestia.class)) continue;
                if (i == random) {
                    switch (position) {
                        case 1: pb1 = p; break;
                        case 2: pb2 = p; break;
                        case 3: pb3 = p; break;
                    }
                    position++;
                    i = from;
                    break;
                }
                i++;
            }
            random = r.nextInt(max) + 4;
        }
        File fondo = fondoAleatorio();
        Heroe heroe1 = (Heroe) generarEntidadAleatoriamente(ph1);
        Heroe heroe2 = (Heroe) generarEntidadAleatoriamente(ph2);
        Heroe heroe3 = (Heroe) generarEntidadAleatoriamente(ph3);
        Bestia bestia1 = (Bestia) generarEntidadAleatoriamente(pb1);
        Bestia bestia2 = (Bestia) generarEntidadAleatoriamente(pb2);
        Bestia bestia3 = (Bestia) generarEntidadAleatoriamente(pb3);
        return new Configuracion(heroe1, heroe2, heroe3, bestia1, bestia2, bestia3, fondo);
    }

    private static Entidad generarEntidadAleatoriamente(Personaje personaje) {
        try {
            Constructor<? extends Entidad> constructor = personaje.getTipo().getConstructor(Personaje.class, int.class, int.class);
            Entidad entidad = constructor.newInstance(personaje, r.nextInt(451) + 50, r.nextInt(51) + 10);
            personaje.setEntidad(entidad);
            entidad.setPersonaje(personaje);
            return entidad;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static File fondoAleatorio() {
        int num = r.nextInt(3) + 1;
        return fondosBasicos.get(num);
    }

    private final static Map<Integer, Heroe> equipoHeroes = new LinkedHashMap<>();
    private final static Map<Integer, Bestia> equipoBestias = new LinkedHashMap<>();
    private int turno = 0;
    private File fondo;
    private Entidad entidadEnAccion;
    private static Timer timer = new Timer();

    public File getFondo() {
        return fondo;
    }

    /**
     * @throws UnsupportedOperationException en caso de que haya algún equipo vacío.
     */
    public CampoDeBatalla() throws UnsupportedOperationException {
        fondo = CampoDeBatallaConfig.fondo;
    }

    public Heroe getHeroe1() {
        return equipoHeroes.get(1);
    }

    public Heroe getHeroe2() {
        return equipoHeroes.get(2);
    }

    public Heroe getHeroe3() {
        return equipoHeroes.get(3);
    }

    public Bestia getBestia1() {
        return equipoBestias.get(4);
    }

    public Bestia getBestia2() {
        return equipoBestias.get(5);
    }

    public Bestia getBestia3() {
        return equipoBestias.get(6);
    }

    public void iniciar() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                siguienteTurno();
            }
        }, 2000L);
    }

    public void finalizar(int equipoGanador) {
        if (equipoGanador == 1) {
            GameWindow.g().nuevoOutput("El equipo de h�roes ha ganado la batalla!");
        }
    }

    public void siguienteTurno() {
        if (GameWindow.g() == null) return;
        if (!getHeroe1().isAlive() && !getHeroe2().isAlive() && !getHeroe3().isAlive()) {
            finalizar(2);
            GameWindow.g().setVisible(false);
            return;
        } else if (!getBestia1().isAlive() && !getBestia2().isAlive() && !getBestia3().isAlive()) {
            finalizar(1);
            GameWindow.g().setVisible(false);
            return;
        }
        turno++;
        Entidad entidad = null;
        while (entidad == null) {
            if (equipoHeroes.containsKey(turno) && equipoHeroes.get(turno).isAlive()) entidad = equipoHeroes.get(turno);
            else if (equipoBestias.containsKey(turno) && equipoBestias.get(turno).isAlive()) entidad = equipoBestias.get(turno);
            else turno = 1;
        }
        this.entidadEnAccion = entidad;
        Entidad victima = getVictima(turno);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                GameWindow.g().animarAtaque(victima);
            }
        }, 2000L);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                entidadEnAccion.atacarConOutput(victima);
                siguienteTurno();
            }
        }, 3500L);
    }

    public Entidad getVictima(int turno) {
        switch (turno) {
            case 1: return equipoBestias.get(4).isAlive() ? equipoBestias.get(4) : equipoBestias.get(5).isAlive() ? equipoBestias.get(5) : equipoBestias.get(6).isAlive() ? equipoBestias.get(6) : null;
            case 2: return equipoBestias.get(5).isAlive() ? equipoBestias.get(5) : equipoBestias.get(6).isAlive() ? equipoBestias.get(6) : equipoBestias.get(4).isAlive() ? equipoBestias.get(4) : null;
            case 3: return equipoBestias.get(6).isAlive() ? equipoBestias.get(6) : equipoBestias.get(4).isAlive() ? equipoBestias.get(4) : equipoBestias.get(5).isAlive() ? equipoBestias.get(5) : null;
            case 4: return equipoHeroes.get(1).isAlive() ? equipoHeroes.get(1) : equipoHeroes.get(2).isAlive() ? equipoHeroes.get(2) : equipoHeroes.get(3).isAlive() ? equipoHeroes.get(3) : null;
            case 5: return equipoHeroes.get(2).isAlive() ? equipoHeroes.get(2) : equipoHeroes.get(3).isAlive() ? equipoHeroes.get(3) : equipoHeroes.get(1).isAlive() ? equipoHeroes.get(1) : null;
            case 6: return equipoHeroes.get(3).isAlive() ? equipoHeroes.get(3) : equipoHeroes.get(1).isAlive() ? equipoHeroes.get(1) : equipoHeroes.get(2).isAlive() ? equipoHeroes.get(2) : null;
            default: return null;
        }
    }

    public static class CampoDeBatallaConfig {

        private static File fondo;
        private static CampoDeBatalla campoDeBatallaActivo = null;

        private CampoDeBatallaConfig() {}

        /**
         * Lista no modificable
         */
        public static Collection<Heroe> getHeroes() {
            return equipoHeroes.values();
        }

        /**
         * Lista no modificable
         */
        public static Collection<Bestia> getBestias() {
            return equipoBestias.values();
        }

        public static void addBestia(int posicion, Bestia bestia) {
            equipoBestias.put(posicion, bestia);
        }

        public static void addHeroe(int posicion, Heroe bestia) {
            equipoHeroes.put(posicion, bestia);
        }

        public static void empezar() {
            campoDeBatallaActivo = new CampoDeBatalla();
            GameWindow.g(campoDeBatallaActivo).setVisible(true);
            VentanaPrincipal.cerrar.setEnabled(true);
        }

        public static void load(Configuracion config) {
            addHeroe(1, config.getHeroe1());
            addHeroe(2, config.getHeroe2());
            addHeroe(3, config.getHeroe3());
            addBestia(4, config.getBestia1());
            addBestia(5, config.getBestia2());
            addBestia(6, config.getBestia3());
            fondo = config.getFondo();
        }

        /**
         * @return El campo de batalla actual, null si no hay ninguno activo.
         */
        public static CampoDeBatalla getCampoDeBatallaActivo() {
            return campoDeBatallaActivo;
        }
    }
}
