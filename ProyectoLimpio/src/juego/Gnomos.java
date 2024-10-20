package juego;

import java.awt.Image;
import entorno.Entorno;
import java.awt.Toolkit;
import java.util.Random;

public class Gnomos {
    private double xInicial; // Posición inicial del gnomo en el eje X
    private double yInicial; // Posición inicial del gnomo en el eje Y
    private double escala;   // Escala para dibujar la imagen del gnomo
    public double velocidad = 5; // Velocidad de movimiento del gnomo
    public boolean enElAire = true; // Variable para verificar que gnomo está en el aire
    private double velocidadVertical = 0; // Velocidad vertical del gnomo
    private Image imagen1; // Imagen del gnomo mirando a la derecha
    private Image imagen2; // Imagen del gnomo mirando a la izquierda
    private Image imagenInicial;
    

    private boolean direccion; // True el gnomo mira a la derecha, false si a la izquierda
    private Entorno entorno; // Referencia al entorno de juego
    private boolean colision=false;//true si esta sobre una plataforma, false sino

    public Gnomos(double xInicial,double yInicial,double escala,Entorno entorno){
    this.xInicial=xInicial;
    this.yInicial=yInicial;
    this.escala=escala;
    this.imagen1=Toolkit.getDefaultToolkit().getImage("C:\\Users\\Rodrigo\\Desktop\\progra  1 tp\\Projecto-de-Progra\\ProyectoLimpio\\Happy-Minion.png");
    this.imagen2=Toolkit.getDefaultToolkit().getImage("C:\\Users\\Rodrigo\\Desktop\\progra  1 tp\\Projecto-de-Progra\\ProyectoLimpio\\gnomos.png");

    //setea de forma aleatoria hacia donde mira el gnomo
    Random rand = new Random();
    int direccion = rand.nextInt(2);

    if(direccion==1){
        this.direccion=true;
    } else {
        this.direccion=false;
    }
        }


    public void dibujarEseGnomo(double x,double y){
        if(direccion){
            this.imagenInicial=imagen1;
        } else {
            this.imagenInicial=imagen2;
        }
        System.out.println("dibujando gnomo");
        entorno.dibujarImagen(imagenInicial,x,y,0,escala);
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

    public boolean isEnElAire() {
        return enElAire;
    }

    public void setEnElAire(boolean enElAire) {
        this.enElAire = enElAire;
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
}
