package objetos_compartidos;

import consumidor.DronRecolector;
import entity.Paquete;
import lector.DronRevisor;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Informe {
    private static Informe informe;
    private Path file = null;
    private int lectores = 0;
    private final String ruta = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "files" + File.separator + "informe.txt";

    //En lugar de un int esto será un dron?
    private boolean hayEscritor = false;


    private int peticionesEscritura = 0;

    private Informe() {

    }

    public static Informe getInstance() {
        if (informe == null) {
            informe = new Informe();
        }
        return informe;
    }

    //private Paquete paquete;


    public synchronized void escribir(DronRecolector escritor, Paquete paquete) throws IOException {
        // Generamos una petición de escritura (Prioridad a escribir)
        peticionesEscritura++;
        // Espera mientras haya un lector o una petición pendiente
        while (lectores > 0) {
            try {
                wait();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        peticionesEscritura--;
        hayEscritor = true;

        System.out.println("Escritor " + escritor.getId() + " comienza a escribir.");

        //Marcar un delay aleatorio y el que se indique
        int DELAY = 1000;

        try {
            Thread.sleep((int) (Math.random() * DELAY));
        } catch (InterruptedException e) {
            System.err.println("Error en la espera del escritor");
        }


        file = Paths.get(ruta);
        if (Files.notExists(file)) {
            file = Files.createFile(Paths.get(ruta));
        }
        Files.write(Paths.get(ruta), paquete.toString().getBytes(StandardCharsets.UTF_8),
                StandardOpenOption.APPEND);

        System.out.println("Escritor " + escritor.getId() + " termina de escribir.");
        hayEscritor = false;

        notifyAll();
    }

    public synchronized void leer(DronRevisor lector) throws IOException {
        // Si hay alguien escribiendo o peticiones pendientes por escribir
        while (hayEscritor || peticionesEscritura > 0 || file == null) {
            try {
                wait();
            } catch (InterruptedException ex) {
                System.err.println("Leer: Error en get -> " + ex.getMessage());
            }
        }
        lectores++;
        System.out.println("Lector " + lector.getId() + " empieza a leer.");

        int DELAY = 1000;

        try {
            Thread.sleep((int) (Math.random() * DELAY));
        } catch (InterruptedException e) {
            System.err.println("Error en la espera del lector");
        }

        String content = Files.readString(Paths.get(ruta));
        System.out.println(content);


        System.out.println("Lector " + lector.getId() + " termina de leer.");
        lectores--;
        notifyAll();
    }

}
