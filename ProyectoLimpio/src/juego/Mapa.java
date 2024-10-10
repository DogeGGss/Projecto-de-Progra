package juego; 

import java.awt.Color;
import entorno.Entorno;

public class Mapa {
    private int xInicial;
    private int yInicial;
    private int ancho;
    private int alto;
    private int cantidadFilas;
    private Entorno entorno; // Asegúrate de tener una clase Entorno

    // Constructor
    public Mapa(int xInicial, int yInicial, int ancho, int alto, int cantidadFilas, Entorno entorno) {
        this.xInicial = 100;
        this.yInicial = 500;
        this.ancho = 100;
        this.alto = 50;
        this.cantidadFilas = 5;
        this.entorno = entorno; // Inicializa entorno
        dibujarRectangulos(); // Llama al método para dibujar rectángulos
    }

    // Método para dibujar los rectángulos en el entorno
    private void dibujarRectangulos() {
        for (int fila = 0; fila < cantidadFilas; fila++) {
            int cantidadRectangulos = cantidadFilas - fila; 
            int y = yInicial - fila * (alto + 50); // Calcula la posición Y de la fila

            // Ajustar la posición horizontal de los rectángulos en la fila
            int ajustar = (cantidadFilas - cantidadRectangulos) * (ancho + 50) / 2;

            for (int i = 0; i < cantidadRectangulos; i++) {
                int x = xInicial + ajustar + i * (ancho + 50); // Calcula la posición X de cada rectángulo
                entorno.dibujarRectangulo(x, y, ancho, alto, 0, Color.RED); // Dibuja el rectángulo en el entorno
            }
        }
    }
}
