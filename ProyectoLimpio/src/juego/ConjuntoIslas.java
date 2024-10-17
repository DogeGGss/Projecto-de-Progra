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
   Image fondo=Toolkit.getDefaultToolkit().getImage("C:\\Users\\Rodrigo\\Desktop\\progra  1 tp\\Projecto-de-Progra\\ProyectoLimpio\\Fondo.png");


   public Image getFondo() {
    return fondo;
}



public ConjuntoIslas(Isla[] islas, Entorno entorno, int cantidadFilas, int anchoIsla, int altoIsla){
    this.islas=islas;
    this.entorno=entorno;
    this.cantidadFilas=cantidadFilas;
    this.anchoIsla=anchoIsla;
    this.altoIsla=altoIsla;
    
   }


   
 //arreglar esta funcion. Pensar en una nueva logica.
 // idea: cada isla es unica y deben imprimirse por separado.
   public void dibujarRectangulos() {
    int k=0;
    for (int fila = 0; fila < this.cantidadFilas; fila++) {
        int cantidadRectangulos = this.cantidadFilas - fila;
        int anchoTotalFila = cantidadRectangulos * (this.anchoIsla + espaciadoHorizontal) - espaciadoHorizontal;
        int xInicial = islas[k].getxCentro() - (anchoTotalFila / 2);
        int y = islas[k].getyInicial()- fila * (altoIsla + espaciadoVertical);
        for (int i = 0; i < cantidadRectangulos; i++) {
            int x = xInicial + i * (anchoIsla + espaciadoHorizontal);
            entorno.dibujarImagen(islas[k].getImagen(), x, y, 0, 6);
        }
    }
}

public void dibujarFondo() {
    entorno.dibujarImagen(getFondo(), 960, 530, 0, 12); 
}

}
