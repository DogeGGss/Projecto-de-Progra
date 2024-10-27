package juego;

import java.util.Random;
import java.awt.Image;
import entorno.Entorno;
import java.awt.Toolkit;

public class Boladefuego {

    private double xInicial; // Posición inicial del gnomo en el eje X
    private double yInicial; // Posición inicial del gnomo en el eje Y
    private double escala = 0.5;   // Escala para dibujar la imagen del gnomo
    public double velocidad = 3; // Velocidad de movimiento del gnomo
    private Image imagen1; // Imagen del gnomo mirando a la derecha
    private Image imagen2; // Imagen del gnomo mirando a la izquierda
    public Image imagenInicial; //imagen con el sentido establecido por la direccion
    private Entorno entorno; // Referencia al entorno de juego
    public boolean colision;//true si esta sobre una plataforma, false sino
    public boolean direccion;
    public boolean flag;


    public Boladefuego(double xInicial,double yInicial,Entorno entorno,boolean direccion){
    this.xInicial=xInicial;
    this.yInicial=yInicial;
    this.entorno=entorno;
    this.imagen1=Toolkit.getDefaultToolkit().getImage("BolaDefuego.gif");
    this.imagen2=Toolkit.getDefaultToolkit().getImage("BolaDUfuego.gif");
    this.direccion=direccion;
    entorno.dibujarImagen(imagen1,xInicial,yInicial,0,escala);
    }
    
    public void dibujarboladefuego(){
        if(direccion){
            //this.xInicial = Nasus.getX() + Nasus.getAnchoPep();
            this.imagenInicial=imagen1;
        } else {
           // this.xInicial = Nasus.getX() - Nasus.getAnchoPep() /2;
            this.imagenInicial=imagen2;  
        }
        System.out.println("imprimiendo la bola de fuego");
        entorno.dibujarImagen(imagenInicial,xInicial,yInicial,0,escala);
        moverBola();
    }  
    
    public void moverBola() {   
        System.out.println("se imprimió");
        if (direccion) {
            xInicial += velocidad; // Movimiento a la derecha
        } else {
            xInicial -= velocidad; // Movimiento a la izquierda
        }
    }

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

    public boolean isColision() {
        return colision;
    }

    public void setColision(boolean colision) {
        this.colision = colision;
    }

    public boolean isDireccion() {
        return direccion;
    }

    public void setDireccion(boolean direccion) {
        this.direccion = direccion;
    }

}