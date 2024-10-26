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
   final Boladefuego[] fuego;
   
    

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

        Boladefuego[] poder=new Boladefuego[1];
        poder[0] = new Boladefuego(entorno);
        this.fuego=poder;




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
    
    private void VerificarColision() {
        if (ColisionaPep()) {
            // Si detecta colisión, establecemos colision=true y apagamos la gravedad
            pep.colision = true;
            pep.GRAVEDAD = 0.0;
            System.out.println("colision...");
    
        
        } else {
            // Si no está en colisión, se aplica gravedad y se establece colision=false
            pep.colision = false;
            pep.GRAVEDAD = 2.3;
            System.out.println("sin colision    ...");
        }
    }
    
    
    


    //Que pep  no atraviese los bordes de la pantalla 
    private void ColisionConBordesLateralesPep(){
        //Toma las dimenciones de pep
        double xPep = pep.getXInicial();
        double anchoPep = pep.getAnchoPep();
    
        double bordeIzquierdoPep = xPep - anchoPep / 2;
        double bordeDerechoPep = xPep + anchoPep / 2;
        if(bordeIzquierdoPep<0){
            pep.setXInicial(0);
        } else if(bordeDerechoPep>1920){
            pep.setXInicial(1920);
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

    private boolean PepConTortuga(Tortugas[] tortuga) {
        // Dimensiones de Pep
        double xPep = pep.getXInicial();
        double anchoPep = pep.getAnchoPep();
        double yPep = pep.getY();
        double altoPep = pep.getAltura();
    
        double bordeIzquierdoPep = xPep - anchoPep / 2;
        double bordeDerechoPep = xPep + anchoPep / 2;
        double cabezaPep = yPep - altoPep / 2;
        double piesPep = yPep + altoPep / 2;
    
        for (int i = 0; i < tortuga.length; i++) {
            // Dimensiones de la tortuga
            double xTortuga = tortuga[i].getxInicial();
            double anchoTortuga = tortuga[i].getAncho();
            double yTortuga = tortuga[i].getyInicial();
            double altoTortuga = tortuga[i].getAltura();
    
            // Bordes de la tortuga en x
            double bordeIzquierdoTortuga = xTortuga - anchoTortuga / 2;
            double bordeDerechoTortuga = xTortuga + anchoTortuga / 2;
            double cabezaTortuga = yTortuga - altoTortuga / 2;
            double piesTortuga = yTortuga + altoTortuga / 2;
    
            // Verifica si hay colisión
            if (!(bordeIzquierdoPep > bordeDerechoTortuga || 
                  bordeDerechoPep < bordeIzquierdoTortuga || 
                  cabezaPep > piesTortuga || 
                  piesPep < cabezaTortuga)) {
                return true; // Hay colisión
            }
        }
        return false; // No hay colisión
    }

    private void queGnomoFueEliminado(Gnomos[] gnomo) {
        for (int i = 0; i < gnomo.length; i++) {
            // Verifica si el gnomo colisiona con Pep
            if (GnomosSalvado(gnomo[i]) && gnomo[i].getyInicial() > 500) {
                this.gnomos.contadorGnomosSalvados++;
                gnomo[i]=null;
            }
    
            // Verifica si el gnomo fue eliminado
            if (GnomoEliminado(gnomo[i]) || gnomo[i].getyInicial()>1080) {
                this.gnomos.contadorGnomosEliminados++;
                gnomo[i] = null; // Establece el gnomo como null
            }
        }
    }
    
    // Verifica si el gnomo colisiona con una tortuga
    private boolean GnomoEliminado(Gnomos gnomo) {
        // Dimensiones del gnomo
        double xGnomo = gnomo.getxInicial();
        double anchoGnomo = gnomo.getAncho();
        double yGnomo = gnomo.getyInicial();
        double altoGnomo = gnomo.getAltura();
    
        // Bordes del gnomo
        double bordeIzquierdoGnomo = xGnomo - anchoGnomo / 2;
        double bordeDerechoGnomo = xGnomo + anchoGnomo / 2;
        double cabezaGnomo = yGnomo - altoGnomo / 2;
        double piesGnomo = yGnomo + altoGnomo / 2;
    
        // Recorre el array de tortugas
        for (int i = 0; i < this.tortugas.conjunTortugas.length; i++) {
            Tortugas tortuga = this.tortugas.conjunTortugas[i];
    
            // Si la tortuga no está presente, continúa
            if (tortuga == null) {
                continue;
            }
    
            // Dimensiones de la tortuga
            double xTortuga = tortuga.getxInicial();
            double anchoTortuga = tortuga.getAncho();
            double yTortuga = tortuga.getyInicial();
            double altoTortuga = tortuga.getAltura();
    
            // Bordes de la tortuga
            double bordeIzquierdoTortuga = xTortuga - anchoTortuga / 2;
            double bordeDerechoTortuga = xTortuga + anchoTortuga / 2;
            double cabezaTortuga = yTortuga - altoTortuga / 2;
            double piesTortuga = yTortuga + altoTortuga / 2;
    
            // Verifica si hay colisión
            if (!(bordeIzquierdoGnomo > bordeDerechoTortuga || 
                  bordeDerechoGnomo < bordeIzquierdoTortuga || 
                  cabezaGnomo > piesTortuga || 
                  piesGnomo < cabezaTortuga)) {
                return true; // Hay colisión
            }
        }
        return false; // No hay colisión
    }
    

 //dice si el gnomo colisiona con pep
     private boolean GnomosSalvado(Gnomos gnomo){
         // Dimensiones de Pep
         double xPep = pep.getXInicial();
         double anchoPep = pep.getAnchoPep();
         double yPep = pep.getY();
         double altoPep = pep.getAltura();
     
         double bordeIzquierdoPep = xPep - anchoPep / 2;
         double bordeDerechoPep = xPep + anchoPep / 2;
         double cabezaPep = yPep - altoPep / 2;
         double piesPep = yPep + altoPep / 2;
     
             // Dimensiones del gnomo
             double xGnomo = gnomo.getxInicial();
             double anchoGnomo = gnomo.getAncho();
             double yGnomo = gnomo.getyInicial();
             double altoGnomo = gnomo.getAltura();
     
             // Bordes de la tortuga en x
             double bordeIzquierdoGnomo = xGnomo - anchoGnomo/ 2;
             double bordeDerechoGnomo =xGnomo +anchoGnomo / 2;
             double cabezaGnomo = yGnomo - altoGnomo / 2;
             double piesGnomo = yGnomo + altoGnomo / 2;
     
             // Verifica si hay colisión
             if (!(bordeIzquierdoPep > bordeDerechoGnomo || 
                   bordeDerechoPep < bordeIzquierdoGnomo || 
                   cabezaPep > piesGnomo || 
                   piesPep < cabezaGnomo)) {
                 return true; // Hay colisión
             }
         return false; // No hay colisión
     }
 
    //que pasa segun con que colisione pep
    private boolean PepSeMuere(){
        
        if(PepConTortuga(this.tortugas.conjunTortugas) || pep.getY()>1080 || this.gnomos.contadorGnomosEliminados==8){
            return true;    
        }
        return false;
    }

    private void PepCondicion(){
        if(PepSeMuere()){
            pep.finDelJuego=true;
        }
        if(pep.finDelJuego){
            entorno.dibujarImagen(Toolkit.getDefaultToolkit().getImage("Derrota.jpg"), 960, 530, 0, 2);
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

    //cuenta los gnomos eliminados (por las tortugas o que calleron la vacio)
    private String contadorGnomosEliminados(){
        return "gnomos eliminados:"+this.gnomos.contadorGnomosEliminados;
    }
    //gnomos salvados por Pep
    private String contadorGnomosSalvados(){
        return "gnomos salvados:"+this.gnomos.contadorGnomosSalvados;
    }

    //cuenta las tortugas eliminadas por Pep
    private String contadorTortugasEliminadas(){
        return "t";
    }
    
    //muestra en pantalla todos los marcadores
    private void Marcadores(){
        entorno.escribirTexto(tiempoTranscurrido(), 50, 50);
       entorno.escribirTexto(contadorGnomosEliminados(), 250, 50);
       entorno.escribirTexto(contadorGnomosSalvados(), 570, 50);

    }
            
    public void tick() {
        //dibuja y crea el fondo y las plataforma
        miMapa.dibujarFondo();
        miMapa.dibujarRectangulos();

        //dibuja y crea a Pep, los gnomos y las tortugas
        pep.dibujar();
        gnomos.dibujarGnomos();
        tortugas.dibujarTortugas();

            if (entorno.sePresiono('c')) { 
                this.fuego[0].dibujarboladefuego(pep);
            }
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
        
        queGnomoFueEliminado(this.gnomos.Todoslosgnomos);
        //Muestra los datos del juego en la parte superior
        Marcadores();

        PepCondicion();
    }

    public static void main(String[] args) {
        Juego juego = new Juego();
    }
}
