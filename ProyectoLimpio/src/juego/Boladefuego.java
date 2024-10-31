package juego;

import java.util.Random;
import java.awt.Image;
import entorno.Entorno;
import java.awt.Toolkit;

public class Boladefuego {

    private double xInicial; // Posición inicial de la bola de fuego en el eje X
    private double yInicial; // Posición inicial de la bola de fuego en el eje Y
    private double escala = 0.5;   // Escala para dibujar la imagen de la bola de fuego
    public double velocidad = 3; // Velocidad de movimiento de la bola de fuego
    private Image imagen1; // Imagende la bola de fuego mirando a la derecha
    private Image imagen2; // Imagen de la bola de fuego mirando a la izquierda
    public Image imagenInicial; //imagen con el sentido establecido por la direccion
    private Entorno entorno; // Referencia al entorno de juego
    public boolean direccion; //true si mira a la derecha, flase a la izquierda


    //constructor de la bola de fuego
    public Boladefuego(double xInicial,double yInicial,Entorno entorno,boolean direccion){
    this.xInicial=xInicial;
    this.yInicial=yInicial;
    this.entorno=entorno;
    this.imagen1=Toolkit.getDefaultToolkit().getImage("BolaDefuego.gif");
    this.imagen2=Toolkit.getDefaultToolkit().getImage("BolaDUfuego.gif");
    this.direccion=direccion;
    }
    
    //dibuja la bola de fuego usando la imagen segun su direccion
    public void dibujarboladefuego(){
        if(direccion){
            this.imagenInicial=imagen1;
        } else {
            this.imagenInicial=imagen2;  
        }
       
        entorno.dibujarImagen(imagenInicial,xInicial,yInicial,0,escala);
        moverBola();
    }  
    
    //mueve la bola de fuego 
    public void moverBola() {   
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