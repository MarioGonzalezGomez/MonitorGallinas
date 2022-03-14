package objetos_compartidos;

import consumidor.DronRecolector;
import entity.Huevo;
import lombok.Getter;
import lombok.Setter;
import productor.Gallina;

import java.util.ArrayDeque;

@Getter
@Setter
public class Corral {
    private static Corral corral;
    private final static int NUM_PARCELAS = 8;

    private int parcelasOcupadas = 0;
    private boolean corralLLeno = false;

    private ArrayDeque<Huevo> huevos;

    // private boolean hayRecogedor=false;

    private Corral() {
        huevos = new ArrayDeque<>();
    }

    public static Corral getInstance() {
        if (corral == null) {
            corral = new Corral();
        }
        return corral;
    }


    public synchronized void poner(Gallina gallina, Huevo huevo) {
        //Esperaremos mientras el corral siga lleno
        while (this.corralLLeno) {
            System.out.println("\tLa gallina: " + gallina.getNombre() + " quiere poner un huevo");

            try {
                //Pausamos el hilo de ejecucion ya que no se puede extraer
                System.out.println("\tLa gallina: " + gallina.getNombre() + " tiene que esperar");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //Una vez superada esta espera activa:
        System.out.println("\tLa gallina: " + gallina.getNombre() + " pone el huevo: " + huevo.getId());
        parcelasOcupadas += 1;
        if (parcelasOcupadas >= NUM_PARCELAS) {
            corralLLeno = true;
        }
        huevos.add(huevo);
        notifyAll();
    }

    public synchronized Huevo coger(DronRecolector dronRecolector) {

        while (huevos.size() == 0) {
            try {
                System.out.println("Dron Recolector: " + dronRecolector.getId() + " espera");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Dron Recolector: " + dronRecolector.getId() + " recoge un huevo");
        corralLLeno = false;
        notifyAll();
        Huevo huevo = huevos.pop();
        return huevo;
    }

}
