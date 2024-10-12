package juego;

import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {
    private Entorno entorno;
    private Mapa miMapa;
    private Pep pep;

    Juego() {
        this.entorno = new Entorno(this, "Proyecto para TP", 1920, 1080);
        
        int xInicial = 960;
        int yInicial = 940;
        int ancho = 100;
        int alto = 50;
        int cantidadFilas = 5;
        this.miMapa = new Mapa(xInicial, yInicial, ancho, alto, cantidadFilas, entorno);
        
        double xInicialPep = 300;
        double yInicialPep = 280; // Ajusta esta posición según sea necesario
        double anguloPep = 0;      
        double escalaPep = 1;      
        this.pep = new Pep(xInicialPep, yInicialPep, anguloPep, escalaPep, entorno);
        this.entorno.iniciar();
    } 

    public void tick() {
        miMapa.dibujarFondo();
        miMapa.dibujarRectangulos();
    
        boolean hayColision = miMapa.verificarColision(pep);
        int yPlataforma = miMapa.obtenerYPlataforma(pep);
        pep.aplicarGravedad(hayColision, yPlataforma);
    
    
        pep.dibujar();
    
        // Mover Pep solo si no hay colisiones laterales
        if (entorno.estaPresionada('d')) {
            pep.mover(pep.velocidad);
        } else if (entorno.estaPresionada('a')) {
            pep.mover(-pep.velocidad);
        }

        if (entorno.sePresiono('w')) { // Si se presiona la tecla de espacio
            pep.saltar(); // Llama al método de salto de Pep
            System.err.println("aa");
        }
        

    }

    public static void main(String[] args) {
        Juego juego = new Juego();
    }
}