package juego;

import java.awt.Image;
import entorno.Entorno;
import java.awt.Toolkit;


public class Conjuntognomos {
    Gnomos[] Todoslosgnomos= new Gnomos[4];
    final Entorno entorno;
    int contadorGnomosNulos=0;
    public int contadorGnomosEliminados=0;
    public int contadorGnomosSalvados=0;

    public Conjuntognomos(Entorno entorno){
       this.entorno=entorno;
       
    }

    private int gnomosNulos(){
        for(int i=0; i<4;i++){
            if(Todoslosgnomos[i]==null){
                contadorGnomosNulos++;
            }
        }
    return contadorGnomosNulos;
    }

    public void dibujarGnomos() {
        if(gnomosNulos()>2){
        for (int i = 0; i < Todoslosgnomos.length; i++) {
            if (this.Todoslosgnomos[i] == null) {
                double x = 1920/2; // Cambia la posición x de cada gnomo
                double y = 30;           // Puedes cambiar y según lo que necesites
                this.Todoslosgnomos[i] = new Gnomos(x, y, 0.4, entorno);
            }
            this.Todoslosgnomos[i].dibujarEseGnomo(Todoslosgnomos[i].getxInicial(), Todoslosgnomos[i].getyInicial());
        }
    } 
    }
}




