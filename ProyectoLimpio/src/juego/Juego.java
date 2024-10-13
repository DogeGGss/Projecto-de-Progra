package juego;

//cosas a solucionar en esta version: 
//vamos a tener que agrandar el tamaño de las plataformas en X creo yo, Son algo chicas.






import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {
    private Entorno entorno;
    final Mapa miMapa;
    final Pep pep;

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
    
      // Verificar si toca el suelo
    

        pep.dibujar();
    
        pep.mover();
    }

    public static void main(String[] args) {
        Juego juego = new Juego();
    }
}