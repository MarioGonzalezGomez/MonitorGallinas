package productor;


import objetos_compartidos.Corral;
import entity.Huevo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Gallina extends Thread {

    private int numHuevosPuestos = 0;
    private final static int HUEVOS_MAX = 20;


    private Corral corral;
    private String nombre;

    private final static long TIEMPO_COMER = 2000;


    public Gallina(String nombre) {
        this.corral = Corral.getInstance();
        this.nombre = nombre;
    }

    @Override
    public void run() {
        while (numHuevosPuestos <= HUEVOS_MAX) {
            Huevo huevo = new Huevo(numHuevosPuestos + 1 + this.nombre, this.nombre);
            numHuevosPuestos++;
            //Mandarlo al Corral
            corral.poner(this, huevo);
            try {
                Thread.sleep(TIEMPO_COMER);
                // this.setPriority(num aleatorio entre 3-8);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
