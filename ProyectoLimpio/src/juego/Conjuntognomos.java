package juego;

import java.awt.Image;
import entorno.Entorno;
import java.awt.Toolkit;


public class Conjuntognomos {
    Gnomos[] Todoslosgnomos= new Gnomos[4];
    final Entorno entorno;
    int contadorGnomos=0;

    public Conjuntognomos(Entorno entorno){
       this.entorno=entorno;
       
    }

    public void dibujarGnomos() {
        for (int i = 0; i < Todoslosgnomos.length; i++) {
            if (this.Todoslosgnomos[i] == null) {
                double x = 1920/2; // Cambia la posición x de cada gnomo
                double y = 100;           // Puedes cambiar y según lo que necesites
                this.Todoslosgnomos[i] = new Gnomos(x, y, 0.4, entorno);
            }
            this.Todoslosgnomos[i].dibujarEseGnomo(Todoslosgnomos[i].getxInicial(), Todoslosgnomos[i].getyInicial());
        }
    } 

}




