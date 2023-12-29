package net.zylesh.senordelosanillos.batalla;

import net.zylesh.senordelosanillos.Core;
import net.zylesh.senordelosanillos.batalla.config.Configuracion;
import net.zylesh.senordelosanillos.common.Bestia;
import net.zylesh.senordelosanillos.common.Entidad;
import net.zylesh.senordelosanillos.common.Heroe;
import net.zylesh.senordelosanillos.common.Personaje;
import net.zylesh.senordelosanillos.common.debuffos.DeBuffo;
import net.zylesh.senordelosanillos.common.debuffos.TurnoDebuffo;
import net.zylesh.senordelosanillos.juego.GameWindow;
import net.zylesh.senordelosanillos.ui.VentanaDeInformacion;
import net.zylesh.senordelosanillos.ui.VentanaPrincipal;

import java.io.File;
import java.lang.reflect.Constructor;
import java.util.*;

public final class CampoDeBatalla {

    // Fondos predefinidos. En la configuración se puede usar un fondo propio también. Lo cambié a un array ya que no se por qué motivo usé un hashmap en un caso así.
    public static final File[] fondosBasicos = new File[3];
    static {
        try {
            File background1 = new File(CampoDeBatalla.class.getClassLoader().getResource("assets" + File.separator + "juego" + File.separator + "fondos" + File.separator + "background1.jpg").getFile());
            File background2 = new File(CampoDeBatalla.class.getClassLoader().getResource("assets" + File.separator + "juego" + File.separator + "fondos" + File.separator + "background2.jpg").getFile());
            File background3 = new File(CampoDeBatalla.class.getClassLoader().getResource("assets" + File.separator + "juego" + File.separator + "fondos" + File.separator + "background3.jpg").getFile());
            fondosBasicos[0] = background1;
            fondosBasicos[1] = background2;
            fondosBasicos[2] = background3;
        } catch (Exception e) {
            Core.log(e.getMessage(), true);
            VentanaDeInformacion.mostrarVentana(VentanaDeInformacion.ERROR, "Error", "El archivo del ejecutable probablemente esté corrupto/haya sido modificado.");
        }
    }

    private static final Random r = new Random();

    /**
     * La función que se ejecuta al darle click al botón "Todo Aleatorio".
     * Hice algunos cambiós aquí también, los loops while no tenían ningún sentido al igual que los iteradores for,
     * guardando la posición actual todo y saber exactamente cuantos elementos hay.
     * @return La configuración generada.
     */
    public static Configuracion generarConfig() {
        int max = Personaje.presetsHeroes.length;
        Personaje ph1 = Personaje.presetsHeroes[r.nextInt(max)];
        Personaje ph2 = Personaje.presetsHeroes[r.nextInt(max)];
        Personaje ph3 = Personaje.presetsHeroes[r.nextInt(max)];
        max = Personaje.presetsBestias.length;
        Personaje pb1 = Personaje.presetsBestias[r.nextInt(max)];
        Personaje pb2 = Personaje.presetsBestias[r.nextInt(max)];
        Personaje pb3 = Personaje.presetsBestias[r.nextInt(max)];
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
        int num = r.nextInt(3);
        return fondosBasicos[num];
    }

    private final static Map<Integer, Heroe> equipoHeroes = new LinkedHashMap<>();
    private final static Map<Integer, Bestia> equipoBestias = new LinkedHashMap<>();
    private int turno = 0;
    private final File fondo;
    private Entidad entidadEnAccion;
    private static final Timer timer = new Timer();

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

    /**
     * Este método se ejecuta recursivamente cada 3,5 segundos hasta que haya un equipo ganador.
     */
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
        for (DeBuffo deBuffo : this.entidadEnAccion.getDeBuffos()) if (deBuffo instanceof TurnoDebuffo) ((TurnoDebuffo) deBuffo).action(this.entidadEnAccion);
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
                if (entidadEnAccion.isAlive()) entidadEnAccion.atacarConOutput(victima);
                else GameWindow.g().nuevoOutput(entidadEnAccion.getNombre() + " ha muerto a causa de su debuffo de turno, por lo cual se canceló su ataque.");
                siguienteTurno();
            }
        }, 3500L);
    }

    /**
     * @param turno el turno actual
     * @return la victima a la que debería atacar el personaje activo.
     */
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

        /**
         * Cargar configuración.
         * @param config el objeto de configuración.
         */
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
