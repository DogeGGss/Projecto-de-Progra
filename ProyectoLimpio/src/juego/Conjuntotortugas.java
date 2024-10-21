package juego;

import java.awt.Image;
import entorno.Entorno;
import java.awt.Toolkit;

public class Conjuntotortugas {
    Tortugas[] conjunTortugas= new Tortugas[4];
    final Entorno entorno;
    int contadorTortugas=0;

    public Conjuntotortugas(Entorno entorno){
        this.entorno=entorno;
        
     }
 
     public void dibujarTortugas() {
         for (int i = 0; i < conjunTortugas.length; i++) {
             if (this.conjunTortugas[i] == null) {
                 double x = 430+i*350+20; //aparicion de las tortugas en la parte superior de la pantalla (NO caen sobre la casita)
                 double y = 100;           // Puedes cambiar y según lo que necesites
                 this.conjunTortugas[i] = new Tortugas(x, y, 0.3, entorno);
             }
             this.conjunTortugas[i].dibujarEsaTortuga(conjunTortugas[i].getxInicial(), conjunTortugas[i].getyInicial());
         }
     } 

}
