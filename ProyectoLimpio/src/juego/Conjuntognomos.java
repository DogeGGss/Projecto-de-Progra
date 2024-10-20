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


    public void dibujarGnomos(double x, double y){
        while(contadorGnomos<=4){
            for(int i=0;i<4;i++){
            if(this.Todoslosgnomos[i]==null){
                    this.Todoslosgnomos[i]= new Gnomos(x,y,0.4,entorno);   
                    this.Todoslosgnomos[i].dibujarEseGnomo(x,y);
            }
            contadorGnomos++;
        }    
    }
    }


    //[2,4] 

}

