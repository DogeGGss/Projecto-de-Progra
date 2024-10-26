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
   Image fondo=Toolkit.getDefaultToolkit().getImage("C:\\Users\\destr\\Desktop\\Tarea progra\\Projecto-de-Progra\\ProyectoLimpio\\Fondo.png");
   Image fondo2=Toolkit.getDefaultToolkit().getImage("Nubes png gif.gif");
   Image casita=Toolkit.getDefaultToolkit().getImage("casa.png");
   

   public Image getFondo() {
    return fondo;
}
public Image getFondo2() {
    return fondo2;
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
    dibujarFila(0, 0);
    // Dibuja la casita de los gnomos después de dibujar todas las filas
    entorno.dibujarImagen(getCasita(), islas[0].getxCentro(), islas[islas.length - 1].getyInicial() - islas[islas.length - 1].getAltoIsla() - 25, 0, 0.45);
}

private void dibujarFila(int fila, int k) {
    // Caso base: Si hemos dibujado todas las filas, terminamos
    if (fila >= this.cantidadFilas) {
        return;
    }

    // Calcular el número de islas en la fila actual (decreciente)
    int numIslasEnFila = this.cantidadFilas - fila;

    for (int j = 0; j < numIslasEnFila; j++) {
        // Calcula la posición x para centrar la fila de islas
        int x = (int) (960 + (j - (numIslasEnFila - 1) / 2.0) * (anchoIsla + espaciadoHorizontal)); // Centra la fila
        int y = 930 - fila * (altoIsla + espaciadoVertical); // Ajusta la altura por fila

        // Crea la isla y dibuja la imagen
        this.islas[k] = new Isla(x, y, anchoIsla, altoIsla, entorno);
        entorno.dibujarImagen(islas[k].getImagen(), x, y, 0, 6);
        k++; // Incrementa el índice k para la próxima isla
    }

    // Llama a la función recursiva para la siguiente fila, pasando el índice k actualizado
    dibujarFila(fila + 1, k);
}
public void dibujarFondo() {
    entorno.dibujarImagen(getFondo(), 960, 530, 0, 12); 
    entorno.dibujarImagen(getFondo2(), 960, 530, 0, 4);
}

}
