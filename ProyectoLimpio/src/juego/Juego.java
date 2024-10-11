package juego;

import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {
    // El objeto Entorno que controla el tiempo y otros
    private Entorno entorno;
    private Mapa miMapa; // Declarar miMapa aquí
    private Pep pep;

    // Constructor
    Juego() {
        // Inicializa el objeto entorno
        this.entorno = new Entorno(this, "Proyecto para TP", 1920, 1080);
        
        // Crea una instancia de Mapa
        int xInicial = 960;
        int yInicial = 940;
        int ancho = 100;
        int alto = 50;
        int cantidadFilas = 5;
        this.miMapa = new Mapa(xInicial, yInicial, ancho, alto, cantidadFilas, entorno);
        
        double xInicialPep = 960; // Posición inicial de Pep
        double yInicialPep = 450; // Ajusta la posición vertical según lo necesites
        double anguloPep = 0;      // Ángulo de rotación
        double escalaPep = 1;      // Escala del personaje
        this.pep = new Pep(xInicialPep, yInicialPep, anguloPep, escalaPep, entorno);
        // Inicia el juego
        this.entorno.iniciar();
    } 

    /**
     * Durante el juego, el método tick() será ejecutado en cada instante y 
     * por lo tanto es el método más importante de esta clase. Aquí se debe 
     * actualizar el estado interno del juego para simular el paso del tiempo 
     * (ver el enunciado del TP para mayor detalle).
     */
    public void tick() {
        // Dibuja el fondo
        miMapa.dibujarFondo(); // Llama al método para dibujar el fondo

        // Dibuja los rectángulos
        miMapa.dibujarRectangulos(); // Llama al método para dibujar rectángulos

        pep.dibujar();
        if (entorno.estaPresionada('d')) {
            pep.mover(pep.velocidad); // Mueve Pep a la derecha
        }
        if (entorno.estaPresionada('a')) {
            pep.mover(-pep.velocidad); // Mueve Pep a la izquierda
        }
    }

    // Método main
    public static void main(String[] args) {
        Juego juego = new Juego(); // Crea una instancia de Juego
    }
}
