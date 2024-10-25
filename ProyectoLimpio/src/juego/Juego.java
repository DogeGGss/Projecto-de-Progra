package juego;

import entorno.Entorno;
import entorno.InterfaceJuego;
import java.awt.Toolkit;
import java.awt.Color;
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

        //se crea el array de islas vacio
        Isla[] islaCompleto2= new Isla[cantIslas];

        // Creamos el objeto conjunto de islas
        this.miMapa = new ConjuntoIslas(islaCompleto2, entorno, cantidadFilas, ancho, alto);
       //Se crea el conjunto de gnomos
        this.gnomos= new Conjuntognomos(entorno);
        //conjunto de tortugas
        this.tortugas=new Conjuntotortugas(entorno);
        //inicia el juego!
        this.entorno.iniciar();
    }

    //funciones declaradas dentro de la clase juego

    //detecta si la tortuga colisiona con una isla y tambien si detecta con los bordes de la misma
    private boolean colisionTortugas(Tortugas tortuga){
        //obtiene las dimenciones de la tortuga
        double xTortuga = tortuga.getxInicial();
        double yTortuga = tortuga.getyInicial();
        int anchoTortuga = tortuga.getAncho();
        int altoTortuga = tortuga.getAltura();
        int Margen = 3;

        //recorre el array de islas 
        for (int i = 0; i < miMapa.islas.length; i++) {

            //obtiene la dimencion de la isla a analizar
            int xIsla = miMapa.islas[i].getxInicial();
            int yIsla = miMapa.islas[i].getyInicial();
            int anchoIsla = miMapa.islas[i].getAnchoIsla();
            int altoIsla = miMapa.islas[i].getAltoIsla();

            //detecta si la tortuga se encuentra sobre 
            boolean colisionX = (xTortuga + anchoTortuga / 2 + Margen > xIsla - anchoIsla / 2) && (xTortuga - anchoTortuga / 2 - Margen < xIsla + anchoIsla / 2);
            boolean colisionY =  (yTortuga + altoTortuga > yIsla) && (yTortuga < yIsla + altoIsla) && (yTortuga + altoTortuga <= yIsla +1);

            //detecta si la tortuga toca los extremos de la isla
            boolean colisionConLaterales=(xTortuga + anchoTortuga / 2  == xIsla + anchoIsla / 2) || (xTortuga - anchoTortuga / 2== xIsla - anchoIsla / 2);
            
            //si toca el lateral entonces se activa la colisionLateral. Dentro del objeto tortuga se cambia su direccion debido a esta variable de instancia
            if(colisionConLaterales){
                tortuga.colisionLateral=true;
            } else {
                tortuga.colisionLateral=false;
            }
            //retorna si colisiona con la isla debidamente 
            if (colisionX && colisionY) {
                return true;
            }
        }
        return false;
    }


    //retorna un booleano si la tortuga colisiona con un extremo de la isla
    private boolean colisionTortugasLateral(Tortugas tortuga) {
        
        //calcula su dimencion en x 
        double xTortuga = tortuga.getxInicial();
        int anchoTortuga = tortuga.getAncho();
        
        // Define los bordes de la tortuga en x
        double bordeIzquierdoTortuga = xTortuga - anchoTortuga / 2;
        double bordeDerechoTortuga = xTortuga + anchoTortuga / 2;
        
        //recorre el array de islas
        for (int i = 0; i < miMapa.islas.length; i++) {
            //calcula su dimencion en x
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

    //verifica la colision con la isla y con los bordes de la misma
    private void verificarColisionesTortugas(Tortugas[] tortuga){
        //recorre el array de tortugas para analizar cada caso en particular
        for(int i=0; i<tortuga.length;i++){
            //si colisiona con la isla y con su extremo
            if(colisionTortugas(tortuga[i])&&colisionTortugasLateral(tortuga[i])){
                //deja de bajar en el eje y la tortuga
                tortuga[i].setGravedad(0);
                //la colision se activa
                tortuga[i].colision=true;
                
                //dependiendo de a donde cambie su direccion, se hace un mini tp imperceptible para el ojo humano, para que no entre en un ciclo infinito
                if( tortuga[i].direccion){
                    tortuga[i].setxInicial(tortuga[i].getxInicial()-4);
                } else {
                    tortuga[i].setxInicial(tortuga[i].getxInicial()+4);
                }

                //se activa la colision lateral 
                tortuga[i].setColisionLateral(true);
                
            } else if(colisionTortugas(tortuga[i])) {
                tortuga[i].setGravedad(0);
                tortuga[i].colision=true;

                //se desactiva la colision lateral 
                tortuga[i].setColisionLateral(false);
            } else {
                //al no haber colision, la tortuga comienza a bajar en el eje y
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
    //calcula los minutos y segundos transcurridos
    private String tiempoTranscurrido() {
        int milisegundos = entorno.tiempo();
        int minutos = 0;
        int segundos = 0;
    
        // Calcular minutos
        minutos = milisegundos / 60000; // 1 minuto = 60000 milisegundos
        milisegundos %= 60000; // Obtener el resto de milisegundos después de contar minutos
    
        // Calcular segundos
        segundos = milisegundos / 1000; // 1 segundo = 1000 milisegundos
    
        // Formatear la salida para asegurar que los minutos y segundos tengan dos dígitos
        String resultado= String.format("tiempo: %d:%02d", minutos, segundos);
        entorno.cambiarFont("Arial", 30, Color.BLACK);
        return resultado;
    }

    //cuenta los gnomos eliminados por las tortugas o que calleron la vacio y salvados por Pep
    private String contadorGnomos(){
        return "g";
    }

    //cuenta las tortugas eliminadas por Pep
    private String contadorTortugas(){
        return "t";
    }
    
    //muestra en pantalla todos los marcadores
    private void Marcadores(){
        entorno.escribirTexto(tiempoTranscurrido(), 50, 50);
        entorno.escribirTexto(contadorGnomos(), 250, 50);

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
        
        //Muestra los datos del juego en la parte superior
        Marcadores();
    }

    public static void main(String[] args) {
        Juego juego = new Juego();
    }
}
