package juego;

import entorno.Entorno;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Pep {
    private double xInicial;
    private double yInicial;
    private double angulo;
    private double escala;
    public double velocidad = 5;
    private Image imagen;
    private boolean direccion; // true si mira a la derecha, false si a la izquierda

    private Entorno entorno; // Asegúrate de tener una clase Entorno

    // Constructor de la clase Pep
    public Pep(double xInicial, double yInicial, double angulo, double escala, Entorno entorno) {
        this.xInicial = xInicial;
        this.yInicial = yInicial;
        this.angulo = angulo;
        this.escala = escala;
        this.entorno = entorno;

        // Cargar la imagen usando ImageIO
        try {
            this.imagen = ImageIO.read(new File("C:\\Users\\destr\\Desktop\\Tarea progra\\ProyectoLimpio\\Happy-Minion.png"));
        } catch (IOException e) {
            e.printStackTrace(); // Imprime el error si no se puede cargar la imagen
        }

        this.direccion = true; // Inicialmente, Pep mira a la derecha
    }

    // Método para dibujar el personaje
    // Método para dibujar el personaje
    public void dibujar() {
        // Dibuja la imagen sin rotación y escala negativa solo en X
        entorno.dibujarImagen(imagen, xInicial, yInicial, direccion ? 0.0 : Math.PI, escala);
    }
    
    
    


    // Método para mover a Pep
    public void mover(double dx) {
        this.xInicial += dx; // Cambia la posición en X
        // Actualiza la dirección y el ángulo
        if (dx > 0) {
            direccion = true; // Mirando a la derecha
        } else if (dx < 0) {
            direccion = false; // Mirando a la izquierda
        }
    }
}
