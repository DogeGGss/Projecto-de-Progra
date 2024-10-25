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


    private boolean colisionTortugas(Tortugas tortuga){
        double xTortuga = tortuga.getxInicial();
        double yTortuga = tortuga.getyInicial();
        int anchoTortuga = tortuga.getAncho();
        int altoTortuga = tortuga.getAltura();
        int Margen = 3;
    
        for (int i = 0; i < miMapa.islas.length; i++) {
            int xIsla = miMapa.islas[i].getxInicial();
            int yIsla = miMapa.islas[i].getyInicial();
            int anchoIsla = miMapa.islas[i].getAnchoIsla();
            int altoIsla = miMapa.islas[i].getAltoIsla();
            
            boolean colisionX = (xTortuga + anchoTortuga / 2 + Margen > xIsla - anchoIsla / 2) && (xTortuga - anchoTortuga / 2 - Margen < xIsla + anchoIsla / 2);
            boolean colisionY =  (yTortuga + altoTortuga > yIsla) && (yTortuga < yIsla + altoIsla) && (yTortuga + altoTortuga <= yIsla +1);

            boolean colisionConLaterales=(xTortuga + anchoTortuga / 2  == xIsla + anchoIsla / 2) || (xTortuga - anchoTortuga / 2== xIsla - anchoIsla / 2);
            
            if(colisionConLaterales){
                tortuga.colisionLateral=true;
            } else {
                tortuga.colisionLateral=false;
            }
            if (colisionX && colisionY) {
                return true;
            }
        }
        return false;
    }

    private boolean colisionTortugasLateral(Tortugas tortuga) {
        double xTortuga = tortuga.getxInicial();
        int anchoTortuga = tortuga.getAncho();
        
        // Define los bordes de la tortuga
        double bordeIzquierdoTortuga = xTortuga - anchoTortuga / 2;
        double bordeDerechoTortuga = xTortuga + anchoTortuga / 2;
        
        for (int i = 0; i < miMapa.islas.length; i++) {
            int xIsla = miMapa.islas[i].getxInicial();
            int anchoIsla = miMapa.islas[i].getAnchoIsla();
            
            // Define los bordes de la isla
            double bordeIzquierdoIsla = xIsla - anchoIsla / 2;
            double bordeDerechoIsla = xIsla + anchoIsla / 2;
    
            // Verifica si hay colisión con los bordes de la isla
            boolean colisionConLaterales = (bordeDerechoTortuga == bordeIzquierdoIsla || bordeIzquierdoTortuga == bordeDerechoIsla);
            //Retorna el resultado
            if (colisionConLaterales) {
                return true;
            }
        }
        return false;
    }
    
    
    


    private void verificarColisionesTortugas(Tortugas[] tortuga){
        for(int i=0; i<tortuga.length;i++){
            if(colisionTortugas(tortuga[i])&&colisionTortugasLateral(tortuga[i])){
                tortuga[i].setGravedad(0);
                tortuga[i].colision=true;
                
                if( tortuga[i].direccion){
                    tortuga[i].setxInicial(tortuga[i].getxInicial()-4);
                } else {
                    tortuga[i].setxInicial(tortuga[i].getxInicial()+4);
                }

                tortuga[i].setColisionLateral(true);
                
            } else if(colisionTortugas(tortuga[i])) {
                tortuga[i].setGravedad(0);
                tortuga[i].setColisionLateral(false);
                tortuga[i].colision=true;
                tortuga[i].setColisionLateral(false);
            } else {
                tortuga[i].setGravedad(2);
                tortuga[i].colision=false;
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
    private boolean ColisionesGnomos(Gnomos gnomo){
        double xGnomo = gnomo.getxInicial();
        double yGnomo = gnomo.getyInicial();
        int anchoGnomo = gnomo.getAncho();
        int altoGnomo = gnomo.getAltura();
        int Margen = 3;
    
        for (int i = 0; i < miMapa.islas.length; i++) {
            int xIsla = miMapa.islas[i].getxInicial();
            int yIsla = miMapa.islas[i].getyInicial();
            int anchoIsla = miMapa.islas[i].getAnchoIsla();
            int altoIsla = miMapa.islas[i].getAltoIsla();
            
            boolean colisionX = (xGnomo + anchoGnomo / 2 + Margen > xIsla - anchoIsla / 2) && (xGnomo - anchoGnomo / 2 - Margen < xIsla + anchoIsla / 2);
            boolean colisionY =  (yGnomo + altoGnomo > yIsla) && (yGnomo < yIsla + altoIsla) && (yGnomo + altoGnomo <= yIsla +1);

            if (colisionX && colisionY) {
                return true;
            }
        }
        return false;
    
    }

    private void verificarColisionesGnomos(Gnomos[] gnomo){
        for(int i=0; i<gnomo.length;i++){
            if (ColisionesGnomos(gnomo[i])){
                gnomo[i].setGravedad(0);
                gnomo[i].colision=true;
                
            } else {
                gnomo[i].setGravedad(2);
                gnomo[i].colision=false;
            }
        }
    }
            

    public void tick() {
        //dibuja y crea el fondo y las plataforma
        miMapa.dibujarFondo();
        miMapa.dibujarRectangulos();

        //dibuja y crea a Pep, los gnomos y las tortugas
        pep.dibujar();
        gnomos.dibujarGnomos();
        tortugas.dibujarTortugas();

        //le da la capacidad de moverse a Pep
        pep.mover();

        //le otorga movimiento a los gnomos y tortugas
        for(int i=0;i<4;i++){
            gnomos.Todoslosgnomos[i].moverGnomo();
            tortugas.conjunTortugas[i].moverTortuga();
        }
      
        //verifica si se encuentran sobre una plataforma
        verificarColisionesTortugas(this.tortugas.conjunTortugas); //con una subfuncion detecta los extremos de la isla y hace que no se caiga
        VerificarColision(); 
        verificarColisionesGnomos(this.gnomos.Todoslosgnomos); //con una subfuncion detecta que el gnomo esta en el aire para cambiar de direccion aleatoria


    }

    public static void main(String[] args) {
        Juego juego = new Juego();
    }
}
