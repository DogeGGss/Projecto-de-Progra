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
   final ConjuntoDePoder fuego;
   
    

    Juego() {
        this.entorno = new Entorno(this, "Proyecto para TP", 1920, 1080);
        //caracteristicas de las islas
        int cantIslas = 1;
        int ancho = 150; 
        int alto = 50;   
        int cantidadFilas = 5;
        //posicion de aparicion de pep
        double xInicialPep = 590;
        double yInicialPep = 657.5; 
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

   
        //conjunto de bolas de fuego
        this.fuego=new ConjuntoDePoder(entorno);

        //inicia el juego!
        this.entorno.iniciar();
    }

    //funciones declaradas dentro de la clase juego

    //detecta si la tortuga colisiona con una isla
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

            //calcula la dimencion de la isla en x
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

                //deja de bajar en el eje "y" la tortuga
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
                
                //si solamente la tortuga esta sobre la isla
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


    //devuelve un boolean segun si Pep esta sobre una isla o no
    private boolean ColisionaPep() {
        //dimenciones de Pep
        double xPep = pep.getXInicial();
        double yPep = pep.getY();
        int anchoPep = pep.getAnchoPep();
        int altoPep = pep.getAltura();
        //margen de error
        int margen = 3; 
        
        //recorre el array de islas
        for (int i = 0; i < miMapa.islas.length; i++) {

            //dimencion de la isla a analizar
            int xIsla = miMapa.islas[i].getxInicial();
            int yIsla = miMapa.islas[i].getyInicial();
            int anchoIsla = miMapa.islas[i].getAnchoIsla();
            int altoIsla = miMapa.islas[i].getAltoIsla();
    
            // calculamos que la colision en "x" y en "y" sean verdaderas
            boolean colisionX = (xPep + anchoPep / 2 > xIsla - anchoIsla / 2 - margen) &&  (xPep - anchoPep / 2 < xIsla + anchoIsla / 2 + margen);
            boolean colisionY = (yPep + altoPep / 2 + margen > yIsla - altoIsla / 2) && (yPep - altoPep / 2 - margen < yIsla + altoIsla / 2);
            
            if (colisionX && colisionY) {
                return true; // Colisión detectada
            }
        }
        return false; // No hay colisión
        }
    
    //Pregunta si Pep esta sobre la isla, segun sea la respuesta, ajusta la gravedad
    private void VerificarColision() {
        if (ColisionaPep()) {
            // Si detecta colisión, establecemos colision=true y apagamos la gravedad
            pep.colision = true;
            pep.GRAVEDAD = 0.0;      
        } else {
            // Si no está en colisión, se aplica gravedad y se establece colision=false
            pep.colision = false;
            pep.GRAVEDAD = 2;    
        }
    }
    
    //Que pep  no atraviese los bordes de la pantalla 
    private void ColisionConBordesLateralesPep(){
        //Toma las dimenciones de pep
        double xPep = pep.getXInicial();
        double anchoPep = pep.getAnchoPep();
    
        double bordeIzquierdoPep = xPep - anchoPep / 2;
        double bordeDerechoPep = xPep + anchoPep / 2;

        //segun la resolucion de la pantalla
        if(bordeIzquierdoPep<0){
            pep.setXInicial((int)anchoPep/2 + 1); //se hace un mini tp imperceptible para el ojo humano, para que no entre en un ciclo infinito
        } else if(bordeDerechoPep>1920){
            pep.setXInicial(1810+(int)anchoPep/2);//se hace un mini tp imperceptible para el ojo humano, para que no entre en un ciclo infinito
        }
    }

    //verifica si un gnomo especifico se encuentra sobre una isla
    private boolean ColisionesGnomos(Gnomos gnomo){
        //dimenciones del gnomo
        double xGnomo = gnomo.getxInicial();
        double yGnomo = gnomo.getyInicial();
        int anchoGnomo = gnomo.getAncho();
        int altoGnomo = gnomo.getAltura();
        int Margen = 3;
        
        //recorre el array de islas
        for (int i = 0; i < miMapa.islas.length; i++) {
            int xIsla = miMapa.islas[i].getxInicial();
            int yIsla = miMapa.islas[i].getyInicial();
            int anchoIsla = miMapa.islas[i].getAnchoIsla();
            int altoIsla = miMapa.islas[i].getAltoIsla();
            
            //verifica que la colision en "x" y en "y" sean verdaderas a la vez
            boolean colisionX = (xGnomo + anchoGnomo / 2 + Margen > xIsla - anchoIsla / 2) && (xGnomo - anchoGnomo / 2 - Margen < xIsla + anchoIsla / 2);
            boolean colisionY =  (yGnomo + altoGnomo > yIsla) && (yGnomo < yIsla + altoIsla) && (yGnomo + altoGnomo <= yIsla +1);

            //retorna el resultado
            if (colisionX && colisionY) {
                return true;
            }
        }
        return false;
    }

    //si hay colision el gnomo se para sobre la plataforma, en caso contrario se cae por la fuerza de gravedad
    private void verificarColisionesGnomos(Gnomos[] gnomo){
        //analiza el caso particular de cada gnomo
        for(int i=0; i<gnomo.length;i++){
            if (ColisionesGnomos(gnomo[i])){
                //si esta sobre la isla, se desactiva la gravedad y se activa la colision
                gnomo[i].setGravedad(0);
                gnomo[i].colision=true;  
            } else {
                //sino, se vuelve a aplicar la gravedad y la colision se desactiva
                gnomo[i].setGravedad(2);
                gnomo[i].colision=false;
            }
        }
    }

    //verifica si Pep colisiona con una Tortuga
    private boolean PepConTortuga(Tortugas[] tortuga) {

        // Dimensiones de Pep
        double xPep = pep.getXInicial();
        double anchoPep = pep.getAnchoPep();
        double yPep = pep.getY();
        double altoPep = pep.getAltura();
        
        //bordes de Pep
        double bordeIzquierdoPep = xPep - anchoPep / 2;
        double bordeDerechoPep = xPep + anchoPep / 2;
        double cabezaPep = yPep - altoPep / 2;
        double piesPep = yPep + altoPep / 2;

        //recorre el array de tortugas
        for (int i = 0; i < tortuga.length; i++) {
            // Dimensiones de la tortuga
            double xTortuga = tortuga[i].getxInicial();
            double anchoTortuga = tortuga[i].getAncho();
            double yTortuga = tortuga[i].getyInicial();
            double altoTortuga = tortuga[i].getAltura();
    
            // Bordes de la tortuga
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

    //verifica si el gnomo fue colisionado por Pep, por una tortuga o se tiró al vacio
    private void queGnomoFueEliminado(Gnomos[] gnomo) {
        //analiza cada caso
        for (int i = 0; i < gnomo.length; i++) {
            // Verifica si el gnomo colisiona con Pep en las ultimas 2 filas de islas
            if (GnomosSalvado(gnomo[i]) && gnomo[i].getyInicial() > 500) {
                this.gnomos.contadorGnomosSalvados++; //aumenta el marcadores 
                gnomo[i]=null;// Establece el gnomo como null
            }
    
            // Verifica si el gnomo fue eliminado
            if (GnomoEliminado(gnomo[i]) || gnomo[i].getyInicial()>1080) {
                this.gnomos.contadorGnomosEliminados++;//aumenta el marcadores 
                gnomo[i] = null; // Establece el gnomo como null
            }
        }
    }

    //devuelve un boolean si la bola de fuego tocó un borde
    private boolean bolaDeFuegoTocaBorde(Boladefuego bolaDeFuego) {
                // Verifica si la bola de fuego está fuera de los límites laterales de la pantalla
                if (bolaDeFuego.getxInicial() < 0 || bolaDeFuego.getxInicial() > 1900) { // 1900 es el ancho de la pantalla
                    return true; 
                }        
        return false;
    }

    //verifica si es que la bola de fuego tocó alguna tortuga
    private boolean bolaDeFuegoColisionTortuga(Boladefuego bolaDeFuego){

            //dimenciones de la bola de fuego
            double xBolaDeFuego =bolaDeFuego.getxInicial();
            double anchoBolaDeFuego = bolaDeFuego.getAncho();
            double yBolaDeFuego = bolaDeFuego.getyInicial();
            double altoBolaDeFuego = bolaDeFuego.getAltura();

            double bordeIzquierdoBolaDeFuego = xBolaDeFuego -anchoBolaDeFuego / 2;
            double bordeDerechoBolaDeFuego = xBolaDeFuego + anchoBolaDeFuego / 2;
            double cabezaBolaDeFuego= yBolaDeFuego - altoBolaDeFuego / 2;
            double piesBolaDeFuego= yBolaDeFuego + altoBolaDeFuego / 2;

        //analiza en cada caso a las tortugas
        for (int i = 0; i < this.tortugas.conjunTortugas.length; i++) {

            // Dimensiones de la tortuga
            double xTortuga = this.tortugas.conjunTortugas[i].getxInicial();
            double anchoTortuga = this.tortugas.conjunTortugas[i].getAncho();
            double yTortuga = this.tortugas.conjunTortugas[i].getyInicial();
            double altoTortuga = this.tortugas.conjunTortugas[i].getAltura();
    
            // Bordes de la tortuga en x
            double bordeIzquierdoTortuga = xTortuga - anchoTortuga / 2;
            double bordeDerechoTortuga = xTortuga + anchoTortuga / 2;
            double cabezaTortuga = yTortuga - altoTortuga / 2;
            double piesTortuga = yTortuga + altoTortuga / 2;
    
            // Verifica si hay colisión
            if (!(bordeIzquierdoBolaDeFuego > bordeDerechoTortuga || 
            bordeDerechoBolaDeFuego< bordeIzquierdoTortuga || 
            cabezaBolaDeFuego> piesTortuga || 
            piesBolaDeFuego < cabezaTortuga)) {
                this.tortugas.conjunTortugas[i]=null; //elimina la tortuga que fue colisionada por el poder del sol
                this.tortugas.contadorEnemigosEliminados++; //aumenta el marcador 
                return true; // Hay colisión
            }
        }
        return false; // No hay colisión

    }

    //si la bola de fuego existe, analiza si fue colisionada por el borde o una tortuga, si fue asi la vuelve null
    private void bolaDeFuegoColisioes(Boladefuego[] bolasDeFuego){
        for (int i = 0; i < bolasDeFuego.length; i++) {
            if (bolasDeFuego[i] != null) {
             if(bolaDeFuegoTocaBorde(bolasDeFuego[i])||bolaDeFuegoColisionTortuga(bolasDeFuego[i])){
                bolasDeFuego[i] = null;
             }
            }
        }
    }


    // Verifica si el gnomo colisionó con una tortuga
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
    
            // Si la tortuga no está presente, continúa con la siguiente tortuga en el array
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
        if(PepConTortuga(this.tortugas.conjunTortugas) || pep.getY()>1080 || this.gnomos.contadorGnomosEliminados==30){
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
        if(this.gnomos.contadorGnomosSalvados==1){
            pep.ganoElJuego=true;
        }
        if(pep.ganoElJuego){
            entorno.dibujarImagen(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Rodrigo\\Desktop\\progra  1 tp\\Projecto-de-Progra\\ProyectoLimpio\\victoria3.jpg"), 960, 530, 0, 1.8);
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
        return "Gnomos eliminados:"+this.gnomos.contadorGnomosEliminados;
    }
    //gnomos salvados por Pep
    private String contadorGnomosSalvados(){
        return "Gnomos salvados:"+this.gnomos.contadorGnomosSalvados;
    }

    //cuenta las tortugas eliminadas por Pep
    private String contadorTortugasEliminadas(){
        return "Enemigos matados:" + this.tortugas.contadorEnemigosEliminados;
    }
    
    //muestra en pantalla todos los marcadores
    private void Marcadores(){
        entorno.escribirTexto(tiempoTranscurrido(), 50, 50);
       entorno.escribirTexto(contadorGnomosEliminados(), 250, 50);
       entorno.escribirTexto(contadorGnomosSalvados(), 570, 50);
       entorno.escribirTexto(contadorTortugasEliminadas(), 1100, 50);
       
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
        ColisionConBordesLateralesPep();
        
       
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
        this.fuego.crearBolaDeFuego(pep);
        bolaDeFuegoColisioes(fuego.poderDeFuego);
    }

    public static void main(String[] args) {
        Juego juego = new Juego();
    }
}
