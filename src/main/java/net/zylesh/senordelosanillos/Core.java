package net.zylesh.senordelosanillos;

import com.jtattoo.plaf.mint.MintLookAndFeel;
import net.zylesh.senordelosanillos.ui.VentanaDeInformacion;
import net.zylesh.senordelosanillos.ui.VentanaPrincipal;

import javax.swing.*;
import java.io.*;
import java.util.Timer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class Core {

    private Core() {}

    public static void crearInterfaz() {
        try {
            UIManager.setLookAndFeel(MintLookAndFeel.class.getName());
            File file = new File(System.getProperty("user.dir") + File.separator + "log.log");
            if (file.createNewFile()) {
                file.setReadable(true);
                file.setWritable(true);
            }
            VentanaPrincipal ventana = new VentanaPrincipal();
            ventana.open();
        } catch (Exception e) {
            e.printStackTrace();
            VentanaDeInformacion.mostrarVentana(VentanaDeInformacion.ERROR, "Error", "Error al generar interfaz.");
        }
    }

    private static final File log = new File(System.getProperty("user.dir") + File.separator + "log.log");
    private static final ExecutorService logWriterThread = Executors.newSingleThreadExecutor();

    public static void log(String mensaje, boolean escribir) {
        System.out.println(mensaje);
        if (escribir) writeLog(mensaje);
    }

    private static void writeLog(String message) {
        logWriterThread.submit(() -> {
            try (FileWriter fw = new FileWriter(log, true); PrintWriter writer = new PrintWriter(fw)) {
                writer.println(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
