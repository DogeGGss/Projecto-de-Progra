package juego;

import java.awt.Image;
import entorno.Entorno;
import java.awt.Toolkit;

public class Conjuntotortugas {
    Tortugas[] conjunTortugas= new Tortugas[4];
    final Entorno entorno;
    int contadorTortugas=0;
    int contadorEnemigosEliminados=0;

    public Conjuntotortugas(Entorno entorno){
        this.entorno=entorno;
        
     }
 
     public void dibujarTortugas() {
        int totalTortugas = conjunTortugas.length;
        int maxPorLado = 2; // Número de tortugas por lado
    
        for (int i = 0; i < totalTortugas; i++) {
            if (this.conjunTortugas[i] == null) {
                double x;
                double y = 100; // Puedes ajustar la posición vertical según lo necesites
    
                // Calcular la posición x en función de la posición de la tortuga
                if (i < maxPorLado) {
                    x = 450 + i * 150; // Tortugas a la derecha
                } else {
                    x = 1460 - (i - maxPorLado) * 150; // Tortugas a la izquierda
                }
    
                this.conjunTortugas[i] = new Tortugas(x, y, 0.3, entorno);
            }
            this.conjunTortugas[i].dibujarEsaTortuga(conjunTortugas[i].getxInicial(), conjunTortugas[i].getyInicial());
        }
    }
    

}
