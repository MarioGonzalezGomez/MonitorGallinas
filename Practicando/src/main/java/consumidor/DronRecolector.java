package consumidor;

import objetos_compartidos.Corral;
import entity.Huevo;
import entity.Paquete;
import lombok.Getter;
import lombok.Setter;
import objetos_compartidos.Informe;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class DronRecolector extends Thread {
    private long id;
    private Corral corral;
    private Informe informe;

    private Paquete paqueteActual;
    private List<Huevo> huevos;
    private int idPaquete = 1;

    public DronRecolector() {
        corral = Corral.getInstance();
        informe = Informe.getInstance();
        huevos = new LinkedList<>();
    }

    public DronRecolector(long id) {
        this.id = id;
        this.corral = Corral.getInstance();
        informe = Informe.getInstance();
        huevos = new LinkedList<>();
    }

    @Override
    public void run() {
        //Indicar el número de paquetes que queremos crear
        while (idPaquete < 4) {
            try {
                int tiempoCarga = cargando();
                System.out.println("El dron " + id + " está cargando durante " + tiempoCarga + " seg");
                sleep(tiempoCarga);
                Huevo recogido = corral.coger(this);
                huevos.add(recogido);
                if (huevos.size() == 6) {
                    Paquete paquete = new Paquete(idPaquete, huevos);
                    //Mandar al dron revisor
                    informe.escribir(this, paquete);
                    huevos.clear();
                    idPaquete++;
                }

            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }
        try {
            sleep(5000);
            System.exit(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private int cargando() {
        int temp = (int) Math.floor(Math.random() * 5 + 2);
        return temp;// * 1000;
    }
}
