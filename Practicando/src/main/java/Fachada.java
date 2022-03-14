import lector.DronRevisor;
import objetos_compartidos.Corral;
import consumidor.DronRecolector;
import objetos_compartidos.Informe;
import productor.Gallina;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Getter
@Setter
public class Fachada {
    private static Fachada fachada;

    private Corral corral;
    private Informe informe;

    private Fachada() {
        corral = Corral.getInstance();
        informe = Informe.getInstance();
    }

    public static Fachada getInstance() {
        if (fachada == null) {
            fachada = new Fachada();
        }
        return fachada;
    }

    public void init() {
        this.crearDronRecolector();
        this.crearGallinas();
        this.crearDronRevisor();
        // this.fin();
    }


    private void crearGallinas() {
        List<String> gallinasNom = Arrays.asList("Caponata", "Turuleta", "Piquito de oro", "Ponedora", "Plumitas");
        List<Gallina> gallinas = gallinasNom.stream().map(Gallina::new).collect(Collectors.toList());

        ExecutorService executor = Executors.newFixedThreadPool(4);
        gallinas.forEach(executor::execute);

        //       Con submit podemos obtener los Future, tipo:
        //       ExecutorService executor = Executors.newFixedThreadPool(10);
        //       Future<String> future = executor.submit(() -> doLongWork("hi with future 1"));
        //       System.out.println(future.get());
    }

    private void crearDronRecolector() {
        DronRecolector dr = new DronRecolector(69);
        dr.start();
    }

    private void crearDronRevisor() {
        DronRevisor dr = new DronRevisor(5346);
        dr.start();
    }


    private void fin() {
        System.exit(0);
    }
}
