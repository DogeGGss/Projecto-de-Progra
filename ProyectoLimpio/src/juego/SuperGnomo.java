package juego;

import java.awt.Image;
import entorno.Entorno;
import java.awt.Toolkit;
import java.util.Random;

public class SuperGnomo {
    private double xInicial; // Posición inicial del super gnomo en el eje X
    private double yInicial; // Posición inicial del super gnomo en el eje Y
    private double escala;   // Escala para dibujar la imagen del super gnomo
    public double velocidad = 1; // Velocidad de movimiento del super gnomo
    private double velocidadVertical = 0; // Velocidad vertical del super gnomo
    private Image imagen1; // Imagen del super gnomo mirando a la derecha
    private Image imagen2; // Imagen del super gnomo mirando a la izquierda
    public Image imagenInicial; //imagen con el sentido establecido por la direccion
    private boolean direccion; // True el super gnomo mira a la derecha, false si a la izquierda
    private Entorno entorno; // Referencia al entorno de juego
    public boolean colision;//true si esta sobre una plataforma, false sino
    public boolean enElAire;
    public double gravedad;

    //constructor
    public SuperGnomo(double xInicial,double yInicial,double escala,Entorno entorno){
    this.xInicial=xInicial;
    this.yInicial=yInicial;
    this.escala=escala;
    this.entorno=entorno;
    this.imagen1=Toolkit.getDefaultToolkit().getImage("SuperGnomoDerecha.png");
    this.imagen2=Toolkit.getDefaultToolkit().getImage("SuperGnomoIzquierda.png");

    //setea de forma aleatoria hacia donde mira el super gnomo
    Random rand = new Random();
    boolean direccionboolean = rand.nextBoolean();
    this.direccion=direccionboolean;
    }
    
    //dibuja al super gnomo segun la direccion aleatoria elegida previamente
    public void dibujarEseSuperGnomo(double x,double y){
        if(colision){
            if(direccion){
                this.imagenInicial=imagen1;
            } else {
            this.imagenInicial=imagen2;
         }
        }  else {
            this.imagenInicial=imagen1;
    }
        
        entorno.dibujarImagen(imagenInicial,x,y,0,escala);
    }

    // Movimiento continuo del super gnomo
    public void moverSuperGnomo() {
        // Solo permite mover si está en la isla
        if (colision) {
            if (direccion) {
                xInicial += velocidad; // Movimiento a la derecha
            } else {
                xInicial -= velocidad; // Movimiento a la izquierda
            }
    
            // Solo cambia de dirección si hay una colisión
        }else  {
                Random rand = new Random();
                boolean n = rand.nextBoolean();
                direccion = n; // Cambia la dirección
            }
            aplicarGravedadSuperGnomos(); // Aplicar gravedad en cada tick
        }
       
    //baja constrantemente al super gnomo en el eje y
    public void aplicarGravedadSuperGnomos() {
        this.yInicial += this.gravedad; 
    }   

     //getters y setters 
    public double getxInicial() {
        return xInicial;
    }

    public void setxInicial(double xInicial) {
        this.xInicial = xInicial;
    }

    public double getyInicial() {
        return yInicial;
    }

    public void setyInicial(double yInicial) {
        this.yInicial = yInicial;
    }

    public double getEscala() {
        return escala;
    }

    public void setEscala(double escala) {
        this.escala = escala;
    }

    public double getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(double velocidad) {
        this.velocidad = velocidad;
    }
    
    public double getVelocidadVertical() {
        return velocidadVertical;
    }

    public void setVelocidadVertical(double velocidadVertical) {
        this.velocidadVertical = velocidadVertical;
    }

    public Image getImagen1() {
        return imagen1;
    }

    public void setImagen1(Image imagen1) {
        this.imagen1 = imagen1;
    }

    public Image getImagen2() {
        return imagen2;
    }

    public void setImagen2(Image imagen2) {
        this.imagen2 = imagen2;
    }

    public boolean getDireccion() {
        return direccion;
    }

    public void setDireccion(boolean direccion) {
        this.direccion = direccion;
    }

    public Entorno getEntorno() {
        return entorno;
    }

    public void setEntorno(Entorno entorno) {
        this.entorno = entorno;
    }

    public boolean isColision() {
        return colision;
    }

    public void setColision(boolean colision) {
        this.colision = colision;
    }
    public double getGravedad() {
        return gravedad;
    }

    public void setGravedad(double gravedad) {
        this.gravedad = gravedad;
    }

    public int getAltura() {
        return (int) (imagen1.getHeight(null) * escala); // Retorna la altura del gnomo escalada
    }
    public int getAncho() {
        return (int) (imagen1.getWidth(null) * escala); // Retorna el ancho delgnomo escalado
    }
}
