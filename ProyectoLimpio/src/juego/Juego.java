package juego;

//cosas a solucionar en esta version: 
//vamos a tener que agrandar el tamaño de las plataformas en X creo yo, Son algo chicas.

import entorno.Entorno;
import entorno.InterfaceJuego;
import java.awt.Toolkit;
import java.awt.Image;

public class Juego extends InterfaceJuego {
    private Entorno entorno;
    final ConjuntoIslas miMapa;
    final Pep pep;

    final void hayColision1(){
        //for (int k = 0; k < miMapa.islas.length; k++) {
                // Obtener la posición y dimensiones de la isla
                int k=8;
                int xIsla = miMapa.islas[k].getxInicial();
                int yIsla = miMapa.islas[k].getyInicial();
                int anchoIsla = miMapa.islas[k].getAnchoIsla();
                int altoIsla = miMapa.islas[k].getAltoIsla();

        
                // Obtener la posición y dimensiones de Pep
                double xPep = pep.getXInicial();
                double yPep = pep.getY();
                int anchoPep = pep.getAnchoPep();
                int altoPep = pep.getAltura();
        
                // Verificar si Pep está colisionando con la isla
                boolean colisionX = xPep + anchoPep > xIsla && xPep < xIsla + anchoIsla;
                System.out.println(colisionX);
                boolean colisionY = yPep + altoPep > yIsla && yPep < yIsla + altoIsla;
                System.out.println(colisionY);
        
                if (colisionX && colisionY) {
                    if (yPep < yIsla) {
                        pep.setYInicial(yIsla - pep.getAltura());  // Ajusta la posición de Pep justo encima de la isla
                        pep.setEnElAire(false);  // Indica que Pep ya no está en el aire
                        System.out.println("Colisión detectada, ajustando posición");
                    }
                }
                //}
            
            
        }

    

    Juego() {
        this.entorno = new Entorno(this, "Proyecto para TP", 1920, 1080);

        int cantIslas=1;
        int xInicial = 960;
        int yInicial = 940;
        int ancho = 100;
        int alto = 50;
        int cantidadFilas = 5;
        
        double xInicialPep = 300;
        double yInicialPep = 280; // Ajusta esta posición según sea necesario
        double anguloPep = 0;      
        double escalaPep = 1;  
        
        //creamos a Pep
        this.pep = new Pep(800, 200, anguloPep, escalaPep, entorno);
       
        //ver cuantas islas tiene segun la cantidad de filas
        for (int i=cantidadFilas;i!=1;i-- ){
            cantIslas+=i;
        }

        //creamos y llenamos el array de isla
        Isla[] islaCompleto= new Isla[cantIslas];
        for(int i=0; i<cantIslas;i++){
            Isla aux= new Isla(xInicial,yInicial,ancho,alto,entorno);
            islaCompleto[i]=aux;
        }

        ConjuntoIslas mapa= new ConjuntoIslas(islaCompleto, entorno, cantidadFilas, ancho, alto);

        this.miMapa=mapa;


      
        
        
        this.entorno.iniciar();


    } 

    public void tick() {
        

        
        miMapa.dibujarFondo();
       
        miMapa.dibujarRectangulos();

        double yPiesPep = pep.getY() + pep.getAltura();

        hayColision1();



        //REPARAR colision y gravedad
        
        //boolean hayColision = miMapa.verificarColision(pep);
        //int yPlataforma = miMapa.obtenerYPlataforma(pep);
        //pep.aplicarGravedad(hayColision, yPlataforma);
    
      // Verificar si toca el suelo
    
        pep.dibujar();
    
        pep.mover();
    }

    public static void main(String[] args) {
        Juego juego = new Juego();
    }
}