package juego;

import entorno.Entorno;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Pep {
    private double xInicial; // Posición inicial de Pep en el eje X
    private double yInicial; // Posición inicial de Pep en el eje Y
    private double angulo;   // Ángulo de rotación de Pep
    private double escala;   // Escala para dibujar la imagen de Pep
    public double velocidad = 5; // Velocidad de movimiento de Pep

    private boolean enElAire = false; // Variable para verificar si Pep está en el aire
    private final double FUERZA_SALTO = 10; // Fuerza del salto
    private double velocidadVertical = 0; // Velocidad vertical de Pep
    private Image imagen1; // Imagen de Pep mirando a la derecha
    private Image imagen2; // Imagen de Pep mirando a la izquierda
    private boolean direccion; // True si Pep mira a la derecha, false si a la izquierda

    private Entorno entorno; // Referencia al entorno de juego
    private final double GRAVEDAD = 0.5; // Valor constante de gravedad

    // Constructor de la clase Pep
    public Pep(double xInicial, double yInicial, double angulo, double escala, Entorno entorno) {
        this.xInicial = xInicial; // Inicializa la posición X
        this.yInicial = yInicial; // Inicializa la posición Y
        this.angulo = angulo;     // Inicializa el ángulo
        this.escala = escala;     // Inicializa la escala
        this.entorno = entorno;   // Inicializa el entorno

        // Carga las imágenes de Pep desde archivos
        try {
            this.imagen1 = ImageIO.read(new File("C:\\Users\\destr\\Desktop\\Tarea progra\\Projecto-de-Progra\\ProyectoLimpio\\Happy-Minion.png"));
            this.imagen2 = ImageIO.read(new File("C:\\Users\\destr\\Desktop\\Tarea progra\\Projecto-de-Progra\\ProyectoLimpio\\Happy-Minion-Invertida.png"));
        } catch (IOException e) {
            e.printStackTrace(); // Maneja excepciones si las imágenes no se cargan
        }

        this.direccion = true; // Inicializa la dirección de Pep mirando a la derecha
    }

    // Método para dibujar a Pep en el entorno
    public void dibujar() {
        if (direccion) {
            entorno.dibujarImagen(imagen1, xInicial, yInicial, 0, escala); // Dibuja imagen mirando a la derecha
        } else {
            entorno.dibujarImagen(imagen2, xInicial, yInicial, 0, escala); // Dibuja imagen mirando a la izquierda
        }
    }

    // Método para aplicar gravedad a Pep
    public void aplicarGravedad(boolean hayColision, int yPlataforma) {
        if (hayColision) {
            this.yInicial = yPlataforma - (imagen1.getHeight(null) * escala); // Ajusta Pep a la plataforma
            enElAire = false; // Pep está en el suelo
            velocidadVertical = 0; // Reinicia la velocidad vertical al caer
        } else {
            this.yInicial += velocidadVertical; // Suma la velocidad vertical a la posición Y
            velocidadVertical += GRAVEDAD; // Aumenta la velocidad vertical debido a la gravedad
        }

        // Verifica que Pep no caiga por debajo del entorno
        if (this.yInicial > entorno.getHeight()) {
            this.yInicial = entorno.getHeight(); // Ajusta Pep a la parte inferior de la ventana
        }
    }

    // Método para saltar
    public void saltar() {
        if (!enElAire) { // Solo permite saltar si no está en el aire
            velocidadVertical = -FUERZA_SALTO; // Asigna la fuerza de salto (negativa para ir hacia arriba)
            enElAire = true; // Establece que Pep está en el aire
        }
    }

    // Método para mover a Pep
    public void mover(double dx) {
        if (dx > 0 ) { // Movimiento a la derecha
            this.xInicial += dx;
            direccion = true;
        } else if (dx < 0) { // Movimiento a la izquierda
            this.xInicial += dx;
            direccion = false;
        }
    }
    
    // Método para verificar colisión con plataformas
    public boolean colisionaCon(int xPlataforma, int yPlataforma, int anchoPlataforma, int altoPlataforma) {
        int xPep = (int) this.xInicial;
        int yPep = (int) this.yInicial;
        int anchoPep = (int) (imagen1.getWidth(null) * escala);
        int altoPep = (int) (imagen1.getHeight(null) * escala);
        int TOLERANCIA = 5;

        // Verifica si Pep colisiona con la plataforma, considerando el margen
        boolean colision = xPep < xPlataforma + anchoPlataforma &&
                           xPep + anchoPep > xPlataforma &&
                           yPep + altoPep > yPlataforma - TOLERANCIA && // Ajusta por tolerancia
                           yPep < yPlataforma + altoPlataforma;

        return colision; // Retorna true si hay colisión, false de lo contrario
    }


    // Métodos para establecer valores
    public void setYInicial(double yInicial) {
        this.yInicial = yInicial; // Establece la posición Y inicial de Pep
    }

    public void setEnElAire(boolean enElAire) {
        this.enElAire = enElAire; // Establece el estado de si Pep está en el aire
    }

    public void setXInicial(int xInicial) {
        this.xInicial = xInicial;
    }

    // Métodos para obtener la posición de Pep
    public double getXInicial() {
        return xInicial; // Retorna la posición X inicial de Pep
    }

    public double getYInicial() {
        return yInicial; // Retorna la posición Y inicial de Pep
    }

    public int getAnchoPep() {
        return (int) (imagen1.getWidth(null) * escala); // Retorna el ancho de Pep escalado
    }
}