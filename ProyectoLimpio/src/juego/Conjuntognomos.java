package juego;

import java.awt.Image;
import entorno.Entorno;
import java.awt.Toolkit;


public class Conjuntognomos {
    Gnomos[] Todoslosgnomos= new Gnomos[4]; //4 gnomos maximo
    final Entorno entorno;
    //marcadores que le dan informacion al usuario
    public int contadorGnomosNulos=0;
    public int contadorGnomosEliminados=0;
    public int contadorGnomosSalvados=0;

    public Conjuntognomos(Entorno entorno){
       this.entorno=entorno;
    }

    //dibuja el gnomo si el espacio del array esta vacio
    public void dibujarGnomos() {
        for (int i = 0; i < Todoslosgnomos.length; i++) {
            if (this.Todoslosgnomos[i] == null) {
                double x = 1920/2; 
                double y = 30;          
                this.Todoslosgnomos[i] = new Gnomos(x, y, 0.4, entorno);
            }
            this.Todoslosgnomos[i].dibujarEseGnomo(Todoslosgnomos[i].getxInicial(), Todoslosgnomos[i].getyInicial());
        }
    }
}




