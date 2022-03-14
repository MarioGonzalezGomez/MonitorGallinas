package lector;


import lombok.Getter;
import lombok.Setter;
import objetos_compartidos.Informe;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

@Getter
@Setter
public class DronRevisor extends Thread {
    private long id;
    private Informe informe;

    public DronRevisor(long id) {
        informe = Informe.getInstance();
        this.id = id;
    }

    public DronRevisor() {
        informe = Informe.getInstance();
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep((int) Math.floor(Math.random() * 4000));
            } catch (InterruptedException e) {
                System.err.println("Error en lector");
            }

            try {
                informe.leer(this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
