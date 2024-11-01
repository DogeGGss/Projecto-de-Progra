package juego;

import entorno.Entorno;

public class ConjuntoDePoder {
    final Entorno entorno;
    Boladefuego[] poderDeFuego= new Boladefuego[1];
    public double xInicial;
    public double yInicial;

    public ConjuntoDePoder(Entorno entorno){
        this.entorno=entorno;
    }

    //crea la bola de fuego si el usuario apretó la c y no hay ninguna bola de fuego en pantalla
    public void crearBolaDeFuego(Pep pep){
        if(entorno.sePresiono('c')&& poderDeFuego[0]==null){
            //la bola de fuego comienza en la ubicacion de pep
            this.xInicial=pep.getXInicial();
            this.yInicial=pep.getY();
            poderDeFuego[0]= new Boladefuego(xInicial, yInicial, entorno, pep.direccion);
        } 
        if( poderDeFuego[0]!=null){
            poderDeFuego[0].dibujarboladefuego();
        }
    }
}
