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
    public boolean enElAire = true; // Variable para verificar si Pep está en el aire
    private final double FUERZA_SALTO = 10; // Fuerza del salto
    private double velocidadVertical = 0; // Velocidad vertical de Pep
    private Image imagen1; // Imagen de Pep mirando a la derecha
    private Image imagen2; // Imagen de Pep mirando a la izquierda
    public boolean yaSalto=false;
    public boolean direccion; // True si Pep mira a la derecha, false si a la izquierda
    private Entorno entorno; // Referencia al entorno de juego
    public boolean colision=false;
    public double GRAVEDAD=3;   
    public int contadorDeTiempo;
    public boolean finDelJuego=false;
    private static final float MAX_VELOCIDAD_VERTICAL = 5.0f; // Limita la velocidad vertical

    // Constructor de la clase Pep
    public Pep(double xInicial, double yInicial, double angulo, double escala, Entorno entorno) {
        this.xInicial = xInicial; // Inicializa la posición X
        this.yInicial = yInicial; // Inicializa la posición Y
        this.escala = escala;     // Inicializa la escala
        this.entorno = entorno;   // Inicializa el entorno
        // Carga las imágenes de Pep desde archivos
        try {
            this.imagen1 = ImageIO.read(new File("Nasus derecha.png"));
            this.imagen2 = ImageIO.read(new File("Nasus izquierda.png"));
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
    // Lógica de movimiento horizontal
    if (entorno.estaPresionada('d')) {
        xInicial += velocidad; // Movimiento a la derecha
        direccion = true; // Mirar a la derecha
    }
    if (entorno.estaPresionada('a')) {
        xInicial -= velocidad; // Movimiento a la izquierda
        direccion = false; // Mirar a la izquierda
    }

    // Lógica de salto
    if (entorno.sePresiono('w') && !enElAire) { // Asegúrate de que no esté en el aire
        velocidadVertical = -FUERZA_SALTO; // Aplica la fuerza hacia arriba
        enElAire = true; // Marca que está en el aire
    }


    // Mueve a Pep verticalmente
    yInicial += velocidadVertical;



    if (colision) {
        // Si hay colisión, ajusta la posición de Pep para que no atraviese la isla
        if (velocidadVertical > 0) { // Si Pep está cayendo
            enElAire = false; // Ya no está en el aire
            velocidadVertical = 0; // Resetea la velocidad vertical al aterrizar
            // por ejemplo: yInicial = yIsla + altoIsla; // Ajusta según la colisión
        }
    } else {
        // Si no hay colisión y está en el aire, aplica gravedad
        aplicarGravedad(); 
        enElAire = true;
    }
}

// Método para aplicar gravedad
public void aplicarGravedad() {
    if (enElAire) {
        // Aplica la gravedad a la velocidad vertical (debe ser negativa)
        velocidadVertical += GRAVEDAD; 
        
        // Limitar la velocidad vertical máxima para evitar que sea demasiado grande
        if (velocidadVertical > MAX_VELOCIDAD_VERTICAL) {
            velocidadVertical = MAX_VELOCIDAD_VERTICAL;
  }
 }
}


    //getters y setters 
    public void setYaSalto(boolean salto){
        this.yaSalto=salto;
    }

    public void setColision(Boolean colision){
        this.colision=colision;
    }
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
    public double getGRAVEDAD() {
        return GRAVEDAD;
    }
    public void setGRAVEDAD(double gRAVEDAD) {
        GRAVEDAD = gRAVEDAD;
    }
}