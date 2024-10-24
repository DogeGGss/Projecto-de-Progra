package juego;

import entorno.Entorno;
import entorno.InterfaceJuego;
import java.awt.Toolkit;
import java.awt.Image;

public class Juego extends InterfaceJuego {
    private Entorno entorno;
    final ConjuntoIslas miMapa;
    final Pep pep;
    final Conjuntognomos gnomos;
   final Conjuntotortugas tortugas;
    

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
        double escalaPep = 0.3;  
       

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
        //miMapa.islas[0].getxCentro(),miMapa.islas[this.miMapa.islas.length-1].getyInicial()-this.miMapa.islas.length-1+this.miMapa.islas[0].getAltoIsla()-25
        this.gnomos= new Conjuntognomos(entorno);
        this.tortugas=new Conjuntotortugas(entorno);
        this.entorno.iniciar();
    }


    private void verificarColisionesTortugas(){

                    for (Isla isla : miMapa.islas) {
                        if (isla != null) {
                            for (int i = 0; i < 4; i++) {
                            // Obtener la posición y dimensiones de la isla
                            int xIsla = isla.getxInicial();
                            int yIsla = isla.getyInicial();
                            int anchoIsla = isla.getAnchoIsla();
                            int altoIsla = isla.getAltoIsla();
                
                            // Obtener la posición y dimensiones de Pep
                            double xTor = tortugas.conjunTortugas[i].getxInicial();
                            double yTor = tortugas.conjunTortugas[i].getyInicial();
                            int anchoTor = tortugas.conjunTortugas[i].getAncho();
                            int altoTor = tortugas.conjunTortugas[i].getAltura();
                
                            // Verificar colisiones solo con los laterales
                            boolean colisionX = xTor + anchoTor > xIsla && xTor < xIsla + anchoIsla;
                            boolean colisionY = yTor +  altoTor > yIsla && yTor < yIsla + altoIsla;
                
                            if (colisionX && colisionY) {
                                // Comprobar si la colisión fue con el lateral izquierdo o derecho
                                if (xTor + anchoTor / 2 < xIsla + anchoIsla / 2) {
                                    // Colisión con el lateral izquierdo
                                    tortugas.conjunTortugas[i].setyInicial(yIsla - tortugas.conjunTortugas[i].getAltura());
                               // tortugas.conjunTortugas[i].setColisionLateral(true);
                            } else {
                               // tortugas.conjunTortugas[i].setColisionLateral(false);
                            }
                        }
                            }
                    
                        }
                }
    }

    private boolean ColisionaPep() {
        double xPep = pep.getXInicial();
        double yPep = pep.getY();
        int anchoPep = pep.getAnchoPep();
        int altoPep = pep.getAltura();
        int Margen = 3;
    
        for (int i = 0; i < miMapa.islas.length; i++) {
            int xIsla = miMapa.islas[i].getxInicial();
            int yIsla = miMapa.islas[i].getyInicial();
            int anchoIsla = miMapa.islas[i].getAnchoIsla();
            int altoIsla = miMapa.islas[i].getAltoIsla();
            
            boolean colisionX = (xPep + anchoPep / 2 + Margen > xIsla - anchoIsla / 2) && (xPep - anchoPep / 2 - Margen < xIsla + anchoIsla / 2);
            boolean colisionY =  (yPep + altoPep > yIsla) && (yPep < yIsla + altoIsla) && (pep.getY() + altoPep <= yIsla +1);

            if (colisionX && colisionY) {
                return true;
            }
        }
        return false;
    }
    
    

    private void VerificarColision(){
      
        if (ColisionaPep() && entorno.sePresiono('w') && pep.colision){
                pep.colision=false;
                pep.setYInicial(pep.getY()-100);
                 pep.mover();
                
            }  else if(ColisionaPep()){
                pep.colision=true;
                  pep.GRAVEDAD=0.0;
          } else {
                pep.colision=false;
                 pep.GRAVEDAD=2;
        }
            
    }
        
    
                
     private void verificarColisiones() {
        // Obtener la posición y dimensiones de Pep
        double xPep = pep.getXInicial();
        double yPep = pep.getY();
        int anchoPep = pep.getAnchoPep();
        int altoPep = pep.getAltura();
        int Margen=15;
        
        for(int i=0;i<miMapa.islas.length-1;i++){
                // Obtener la posición y dimensiones de la isla
                int xIsla = miMapa.islas[i].getxInicial();
                int yIsla = miMapa.islas[i].getyInicial();
                int anchoIsla = miMapa.islas[i].getAnchoIsla();
                int altoIsla = miMapa.islas[i].getAltoIsla();

                
                for (int j = 0; i < 4; i++) {
                    boolean colisionXgnomos = this.gnomos.Todoslosgnomos[j].getxInicial() + this.gnomos.Todoslosgnomos[j].getAncho() > xIsla 
                        && this.gnomos.Todoslosgnomos[j].getxInicial() < xIsla + anchoIsla;
                    boolean colisionYgnomos = this.gnomos.Todoslosgnomos[j].getyInicial() + this.gnomos.Todoslosgnomos[j].getAltura() > yIsla 
                        && this.gnomos.Todoslosgnomos[j].getyInicial() < yIsla + altoIsla;

                    if (colisionXgnomos && colisionYgnomos) {
                        this.gnomos.Todoslosgnomos[j].setyInicial(yIsla - this.gnomos.Todoslosgnomos[j].getAltura()); // Ajusta la posición del gnomo justo encima de la isla
                        this.gnomos.Todoslosgnomos[j].setColision(true);  // Todos los gnomos tienen colisión
                    } else {
                       
                    }
                }
                for (int k = 0; i < 4; i++) {
                   
                    //cronometro para cambiar de direccion despues de una cantidad especifica de ticks
                    if(this.gnomos.Todoslosgnomos[k].tiempoInicial==40){
                        this.gnomos.Todoslosgnomos[k].flag=true;
                        this.gnomos.Todoslosgnomos[k].tiempoInicial=0; 
                     } else {
                        this.gnomos.Todoslosgnomos[k].tiempoInicial++;
                        this.gnomos.Todoslosgnomos[k].flag=false;
                     }
                   
                }

                // Verificar si Pep está colisionando con la isla
                boolean colisionX = (xPep + anchoPep/2 > xIsla-anchoIsla/2 )&& (xPep-anchoPep/2 < xIsla + anchoIsla/2);
                double DistanciaEnYdeLaIsla= Math.abs(yPep + altoPep/2 - yIsla - altoIsla/2);
                boolean colisionY=DistanciaEnYdeLaIsla<Margen;
                if (colisionX && colisionY) {
                        pep.GRAVEDAD=0.0;
                        break;
                       // pep.setEnElAire(false); // Indica que Pep ya no está en el aire
                        //pep.setColision(true); 
                    } else{
                        pep.GRAVEDAD=2;
                        //pep.setEnElAire(true);
                        //pep.setColision(false);
                    }
                
            }
        }
            
        
    

    public void tick() {

        miMapa.dibujarFondo();
        miMapa.dibujarRectangulos();
        pep.dibujar();
        gnomos.dibujarGnomos();
        tortugas.dibujarTortugas();
        pep.mover();
        gnomos.Todoslosgnomos[0].moverGnomo();
        gnomos.Todoslosgnomos[1].moverGnomo();
        gnomos.Todoslosgnomos[2].moverGnomo();
        gnomos.Todoslosgnomos[3].moverGnomo();

       tortugas.conjunTortugas[0].moverTortuga();
       tortugas.conjunTortugas[1].moverTortuga();
       tortugas.conjunTortugas[2].moverTortuga();
       tortugas.conjunTortugas[3].moverTortuga();


        //verificarColisiones(); 

        verificarColisionesTortugas();

        VerificarColision();


    }

    public static void main(String[] args) {
        Juego juego = new Juego();
    }
}
