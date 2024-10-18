package juego;

import entorno.Entorno;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Pep {
    private double xInicial; // Posición inicial de Pep en el eje X
    private double yInicial; // Posición inicial de Pep en el eje Y
    private double escala;   // Escala para dibujar la imagen de Pep
    public double velocidad = 5; // Velocidad de movimiento de Pep
    private boolean enElAire = false; // Variable para verificar si Pep está en el aire
    private final double FUERZA_SALTO = 10; // Fuerza del salto
    private double velocidadVertical = 0; // Velocidad vertical de Pep
    private Image imagen1; // Imagen de Pep mirando a la derecha

    private Image imagen2; // Imagen de Pep mirando a la izquierda
    private boolean direccion; // True si Pep mira a la derecha, false si a la izquierda
    private Entorno entorno; // Referencia al entorno de juego

    // Constructor de la clase Pep
    public Pep(double xInicial, double yInicial, double angulo, double escala, Entorno entorno) {
        this.xInicial = xInicial; // Inicializa la posición X
        this.yInicial = yInicial; // Inicializa la posición Y
        this.escala = escala;     // Inicializa la escala
        this.entorno = entorno;   // Inicializa el entorno
        // Carga las imágenes de Pep desde archivos
        try {
            this.imagen1 = ImageIO.read(new File("C:\\Users\\Rodrigo\\Desktop\\progra  1 tp\\Projecto-de-Progra\\ProyectoLimpio\\Happy-Minion.png"));
            this.imagen2 = ImageIO.read(new File("C:\\Users\\Rodrigo\\Desktop\\progra  1 tp\\Projecto-de-Progra\\ProyectoLimpio\\Happy-Minion-Invertida.png"));
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
// Método para mover a Pep
public void mover() {
    // Verifica si se está presionando la tecla de salto
    if ((entorno.estaPresionada('w') || entorno.estaPresionada(entorno.TECLA_ARRIBA)) && !enElAire) {
        velocidadVertical = -FUERZA_SALTO; // Aplica la fuerza hacia arriba
        enElAire = true; // Marca que está en el aire
    }
    // Solo permite movimiento horizontal si Pep no está en el aire
    
        if (entorno.estaPresionada('d') || entorno.estaPresionada(entorno.TECLA_DERECHA)) {
            xInicial += velocidad; // Movimiento a la derecha
            direccion = true; // Mirar a la derecha
        }
        if (entorno.estaPresionada('a') || entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) {
            xInicial -= velocidad; // Movimiento a la izquierda
            direccion = false; // Mirar a la izquierda
        }
    
    // Siempre aplica gravedad
    aplicarGravedad(false, 0); // Asegúrate de gestionar correctamente la colisión
}
// Método para aplicar gravedad a Pep
public void aplicarGravedad(boolean hayColision, int yPlataforma) {
    double GRAVEDAD = 0.2; // Valor de gravedad
    double VELOCIDAD_MAXIMA_CAIDA = 5.0; // Limita la velocidad de caída

    // Si hay colisión, ajusta la posición de Pep
    if (hayColision) {
        this.yInicial = yPlataforma - (imagen1.getHeight(null) * escala); // Ajuste
        enElAire = false; 
        velocidadVertical = 0; 
    } else {
        // Si no hay colisión, aplica la gravedad
        this.yInicial += velocidadVertical; 
        velocidadVertical += GRAVEDAD; 

        // Limitar la velocidad de caída
        if (velocidadVertical > VELOCIDAD_MAXIMA_CAIDA) {
            velocidadVertical = VELOCIDAD_MAXIMA_CAIDA;
        }
    }
}   
    // Método para verificar colisión con plataformas
    public boolean colisionaCon(int xPlataforma, int yPlataforma, int anchoPlataforma, int altoPlataforma) {
        int xPep = (int) this.xInicial;
        int yPiesPep = (int) (this.yInicial + imagen1.getHeight(null) * escala); // Solo los pies de Pep
        int anchoPep = (int) (imagen1.getWidth(null) * escala);
        int TOLERANCIA = 5;
    
        // Verifica si solo los pies de Pep colisionan con la parte superior de la plataforma
        boolean colision = xPep < xPlataforma + anchoPlataforma &&
                           xPep + anchoPep > xPlataforma &&
                           yPiesPep >= yPlataforma - TOLERANCIA && // Solo si los pies están justo sobre la plataforma
                           yPiesPep <= yPlataforma + TOLERANCIA; // Margen de tolerancia para la colisión
    
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
    public int getAltura() {
        return (int) (imagen1.getHeight(null) * escala); // Retorna la altura de Pep escalada
    }
    public int getAnchoPep() {
        return (int) (imagen1.getWidth(null) * escala); // Retorna el ancho de Pep escalado
    }

    public double getY() {
    return yInicial; // Devuelve la posición actual en el eje Y
    }

    public double getX(){
        return xInicial; //  posición actual de Pep en x
    }
    public Image getImagen1() {
        return imagen1;
    }
}