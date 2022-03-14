package util;

public class Util {

    //En el objeto observable (Que extienda de observable), tendré que tener un objeto de cada observador. Tendrá este método

    // public void notificar(){
    //     if(paquetes.size()%10==0){
    //         observer.update(this, "paquetes");
    //     }
    // }

    //Donde "observer" es el objeto observador al que quiero mandar la información

    //Si queremos añadir los observadores a través de un objeto observado, podemos añadirle el método:

    // @Override
    // public void addObserver(Observer observer) {
    //     this.observer = observer;
    // }


    //El observador, por otro lado, implementará observer y tendrá este método:

    //@Override
    //public void update(Observable o, Object arg) {
    //    if (arg.equals("paquetes")) {
    //        System.out.println(((Almacen) o).getPaquete());
    //    }
    //}

    //Deberemos castear el objeto "o" para acceder a los métodos del objeto
    //La clave "args" puesta como paquetes, es un modo de despertar solo a aquellos observers que me interese, y que el resto no haga nada

}
