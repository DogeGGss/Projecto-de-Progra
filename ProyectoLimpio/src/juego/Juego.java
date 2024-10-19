package juego;

import entorno.Entorno;
import entorno.InterfaceJuego;
import java.awt.Toolkit;
import java.awt.Image;

public class Juego extends InterfaceJuego {
    private Entorno entorno;
    final ConjuntoIslas miMapa;
    final Pep pep;
   // final Gnomos minions;
    //final Tortugas rammus;

    Juego() {
        this.entorno = new Entorno(this, "Proyecto para TP", 1920, 1080);

        int cantIslas = 1;
        int xInicial = 960;
        int yInicial = 940;
        int ancho = 150; // Aumenta el ancho de la isla
        int alto = 50;   // Puedes ajustar el alto si es necesario
        int cantidadFilas = 5;

        double xInicialPep = 300;
        double yInicialPep = 280; // Ajusta esta posición según sea necesario
        double anguloPep = 0;      
        double escalaPep = 1;  

        // Creamos a Pep
        this.pep = new Pep(xInicialPep, yInicialPep, anguloPep, escalaPep, entorno);

        // Calcula el total de islas según la cantidad de filas
        for (int i = cantidadFilas; i != 1; i--) {
            cantIslas += i;
        }

        // Creamos y llenamos el array de islas
        Isla[] islaCompleto = new Isla[cantIslas];
        for (int i = 0; i < cantIslas; i++) {
            Isla aux = new Isla(xInicial, yInicial, ancho, alto, entorno);
            islaCompleto[i] = aux;
            // Puedes modificar xInicial e yInicial para colocar islas en diferentes posiciones
        }

        // Creamos el conjunto de islas
        this.miMapa = new ConjuntoIslas(islaCompleto, entorno, cantidadFilas, ancho, alto);

        this.entorno.iniciar();
    }

    private void verificarColisiones() {
        for (Isla isla : miMapa.islas) {
            if (isla != null) {
                // Obtener la posición y dimensiones de la isla
                int xIsla = isla.getxInicial();
                int yIsla = isla.getyInicial();
                int anchoIsla = isla.getAnchoIsla();
                int altoIsla = isla.getAltoIsla();

                // Obtener la posición y dimensiones de Pep
                double xPep = pep.getXInicial();
                double yPep = pep.getY();
                int anchoPep = pep.getAnchoPep();
                int altoPep = pep.getAltura();

                // Verificar si Pep está colisionando con la isla
                boolean colisionX = xPep + anchoPep > xIsla && xPep < xIsla + anchoIsla;
                boolean colisionY = yPep + altoPep > yIsla && yPep < yIsla + altoIsla;
                if (colisionX && colisionY) {
                    if (yPep + altoPep <= yIsla + altoIsla) { // Asegura que Pep no atraviese la isla
                        pep.setYInicial(yIsla - pep.getAltura()); // Ajusta la posición de Pep justo encima de la isla
                        pep.setEnElAire(false); // Indica que Pep ya no está en el aire
                        pep.setColision(true);
                    } else{
                        pep.setColision(false);
                    }
                
                }
                
            }
        }
    }

    public void tick() {
        miMapa.dibujarFondo();
        miMapa.dibujarRectangulos();

        verificarColisiones(); // Cambiado a verificarColisiones()

        pep.dibujar();
        pep.mover();
    }

    public static void main(String[] args) {
        Juego juego = new Juego();
    }
}
