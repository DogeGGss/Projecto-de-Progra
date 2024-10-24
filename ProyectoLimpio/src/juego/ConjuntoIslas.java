package juego;

import java.awt.Image;
import entorno.Entorno;
import java.awt.Toolkit;

public class ConjuntoIslas {
   Isla[] islas;
   final Entorno entorno;
   public int cantidadFilas;
   final int anchoIsla;
   final int altoIsla;
   final int espaciadoHorizontal = 200;
   final int espaciadoVertical = 150;
   Image fondo=Toolkit.getDefaultToolkit().getImage("Fondo.png");
   Image casita=Toolkit.getDefaultToolkit().getImage("casa.png");

   public Image getFondo() {
    return fondo;
}
   public Image getCasita(){
    return casita;
   }

public ConjuntoIslas(Isla[] islas, Entorno entorno, int cantidadFilas, int anchoIsla, int altoIsla){
    this.islas=islas;
    this.entorno=entorno;
    this.cantidadFilas=cantidadFilas;
    this.anchoIsla=anchoIsla;
    this.altoIsla=altoIsla;
    
   }

   public void dibujarRectangulos() {
    int k = 0;
    for (int fila = 0; fila < this.cantidadFilas; fila++) {
        // Calcular el número de islas en la fila actual (decreciente)
        int numIslasEnFila = this.cantidadFilas - fila;
        
        for (int j = 0; j < numIslasEnFila; j++) {
            // Calcula la posición x para centrar la fila de islas
            int x = (int) (960 + (j - (numIslasEnFila - 1) / 2.0) * (anchoIsla + espaciadoHorizontal)); // Centra la fila
            int y = 930 - fila * (altoIsla + espaciadoVertical); // Ajusta la altura por fila

            // Crea la isla y dibuja la imagen
            this.islas[k++] = new Isla(x, y, anchoIsla, altoIsla, entorno);
            entorno.dibujarImagen(islas[k-1].getImagen(), x, y, 0, 6);
        }
    }
    //dibuja la casita de los gnomos
    entorno.dibujarImagen(getCasita(),islas[0].getxCentro(),islas[islas.length-1].getyInicial()-islas[islas.length-1].getAltoIsla()-25,0,0.45);
    
}


public void dibujarFondo() {
    entorno.dibujarImagen(getFondo(), 960, 530, 0, 12); 
}

}
