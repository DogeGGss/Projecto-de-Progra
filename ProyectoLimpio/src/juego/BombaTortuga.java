package juego;

import java.awt.Image;
import entorno.Entorno;
import java.awt.Toolkit;

public class BombaTortuga {
    private double xInicial; // Posición inicial de la bomba en el eje X
    private double yInicial; // Posición inicial de la  bomba en el eje Y
    private double escala = 0.2;   // Escala para dibujar la imagen de la  bomba
    public double velocidad = 2; // Velocidad de movimiento de la  bomba
    private Image imagen1; // Imagen de la bomba mirando a la derecha
    private Image imagen2; // Imagen de la bomba mirando a la izquierda
    public Image imagenInicial; //imagen con el sentido establecido por la direccion
    private Entorno entorno; // Referencia al entorno de juego
    public boolean direccion; //true si mira a la derecha, flase a la izquierda
    public boolean estaTortugaColisionando; //solo sale la bomba cuando la tortuga este sobre una isla


    //constructor de la bomba
    public BombaTortuga(double xInicial,double yInicial,Entorno entorno,boolean direccion){
    this.xInicial=xInicial;
    this.yInicial=yInicial;
    this.entorno=entorno;
    this.imagen1=Toolkit.getDefaultToolkit().getImage("bomba  derecha.png");
    this.imagen2=Toolkit.getDefaultToolkit().getImage("bomba izquierda.png");
    this.direccion=direccion;
    }
    
    //dibuja la bomba usando la imagen segun su direccion
    public void dibujarBomba(){
        if(direccion){
            this.imagenInicial=imagen1; //derecha
        } else {
            this.imagenInicial=imagen2; //izquierda
        }
       
        entorno.dibujarImagen(imagenInicial,xInicial,yInicial,0,escala);
        moverBomba();
    }  
    
    //mueve la bomba constantemente en x
    public void moverBomba() {   
        if (direccion) {
            xInicial += velocidad*2; // Movimiento a la derecha
        } else {
            xInicial -= velocidad*2; // Movimiento a la izquierda
        }
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

    public Image getImagenInicial() {
        return imagenInicial;
    }

    public void setImagenInicial(Image imagenInicial) {
        this.imagenInicial = imagenInicial;
    }

    public Entorno getEntorno() {
        return entorno;
    }

    public void setEntorno(Entorno entorno) {
        this.entorno = entorno;
    }

    public boolean isDireccion() {
        return direccion;
    }

    public void setDireccion(boolean direccion) {
        this.direccion = direccion;
    }
 
    public int getAltura() {
        return (int) (imagen1.getHeight(null) * escala); 
    }
    public int getAncho() {
        return (int) (imagen1.getWidth(null) * escala); 
    }
    
}
