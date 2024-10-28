package juego;

import java.awt.Image;
import entorno.Entorno;
import java.awt.Toolkit;

public class ConjuntoDePoder {
    final Entorno entorno;
    Boladefuego[] poderDeFuego= new Boladefuego[1];
    public double xInicial;
    public double yInicial;

    public ConjuntoDePoder(Entorno entorno){
        this.entorno=entorno;
    }

    public void crearBolaDeFuego(Pep pep){
        if(entorno.sePresiono('c')&& poderDeFuego[0]==null){
            this.xInicial=pep.getXInicial()+pep.getAnchoPep();
            this.yInicial=pep.getY();
            poderDeFuego[0]= new Boladefuego(xInicial, yInicial, entorno, pep.direccion);
        } 
        if( poderDeFuego[0]!=null){
            poderDeFuego[0].dibujarboladefuego();
        }
    }
}
