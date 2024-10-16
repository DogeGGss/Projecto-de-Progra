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



   public void dibujarRectangulos() {

    for (Isla isla : islas) {

    for (int fila = 0; fila < cantidadFilas; fila++) {
        int cantidadRectangulos = cantidadFilas - fila;
        int anchoTotalFila = cantidadRectangulos * (this.anchoIsla + espaciadoHorizontal) - espaciadoHorizontal;
        int xInicial = isla.getxCentro() - (anchoTotalFila / 2);
        int y = isla.getyInicial()- fila * (altoIsla + espaciadoVertical);
        for (int i = 0; i < cantidadRectangulos; i++) {
            int x = xInicial + i * (anchoIsla + espaciadoHorizontal);
            entorno.dibujarImagen(isla.getImagen(), x, y, 0, 6);
        }
    }
}
}

public void dibujarFondo() {
    entorno.dibujarImagen(getFondo(), 960, 530, 0, 12); 
}

}
