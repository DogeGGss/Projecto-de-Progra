package juego;

import entorno.Entorno;

public class ConjuntoDePoder {
    final Entorno entorno;
    Boladefuego[] poderDeFuego= new Boladefuego[1];
    public double xInicial;
    public double yInicial;
    public boolean flag;

    public ConjuntoDePoder(Entorno entorno){
        this.entorno=entorno;
    }
}
