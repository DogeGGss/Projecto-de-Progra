package juego;

import java.awt.Image;
import entorno.Entorno;
import java.awt.Toolkit;
import java.util.Random;

public class Conjuntotortugas {
    Tortugas[] conjunTortugas= new Tortugas[4]; //4 tortugas maximo
    final Entorno entorno;
    //marcadores que le dan informacion al usuario
    int contadorTortugas=0;
    int contadorEnemigosEliminados=0;

    //constructor
    public Conjuntotortugas(Entorno entorno){
        this.entorno=entorno;
     }
 
     //genera una posicion aleatoria
     public static int generarPosicionXAleatoria() {
        Random rand = new Random();
        if (rand.nextBoolean()) { // Elige aleatoriamente entre los dos rangos (NO TIRA NUMEROS QUE SEAN EN LA ISLA DE LA CASITA)
            return rand.nextInt(684) + 127; // Rango [127, 810] 
        } else {
            return rand.nextInt(671) + 1110; // Rango [1110, 1780]
        }
    }

    //si la tortuga es nula, entonces la dibuja
     public void dibujarTortugas() {
        int totalTortugas = conjunTortugas.length;
        for (int i = 0; i < totalTortugas; i++) {
            if (this.conjunTortugas[i] == null) {
                int x =  generarPosicionXAleatoria(); // en un x aleatorio
                int y = -350; 
                this.conjunTortugas[i] = new Tortugas(x, y, 0.3, entorno);
            }
            this.conjunTortugas[i].dibujarEsaTortuga(conjunTortugas[i].getxInicial(), conjunTortugas[i].getyInicial());
        }
    }
    

}
