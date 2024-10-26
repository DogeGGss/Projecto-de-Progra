package juego;

import java.util.Random;
import java.awt.Image;
import entorno.Entorno;
import java.awt.Toolkit;



public class Boladefuego {




    private double xInicial; // Posición inicial del gnomo en el eje X
    private double yInicial; // Posición inicial del gnomo en el eje Y
    private double escala = 1;   // Escala para dibujar la imagen del gnomo
    public double velocidad = 1; // Velocidad de movimiento del gnomo
    private Image imagen1; // Imagen del gnomo mirando a la derecha
    private Image imagen2; // Imagen del gnomo mirando a la izquierda
    public Image imagenInicial; //imagen con el sentido establecido por la direccion
    private Entorno entorno; // Referencia al entorno de juego
    public boolean colision;//true si esta sobre una plataforma, false sino
    public boolean direccion;



    
    public Boladefuego(Entorno entorno){
    this.entorno=entorno;
    this.imagen1=Toolkit.getDefaultToolkit().getImage("rammus derecha.png");
    this.imagen2=Toolkit.getDefaultToolkit().getImage("BolaDUfuego.gif");
    //setea de forma aleatoria hacia donde mira el gnomo
    

    }
    
    public void dibujarboladefuego(Pep Nasus){
        
        if(Nasus.direccion){
            this.xInicial = Nasus.getX() + Nasus.getAnchoPep() /2;
            this.imagenInicial=imagen1;
        } else {
            this.xInicial = Nasus.getX() - Nasus.getAnchoPep() /2;
            this.imagenInicial=imagen2;
        }

        this.yInicial=Nasus.getY();
        entorno.dibujarImagen(imagenInicial,xInicial,yInicial,0,escala);
    }  
    

       

    public void moverBola() {
           
        if (direccion) {
            xInicial += velocidad; // Movimiento a la derecha
        } else {
            xInicial -= velocidad; // Movimiento a la izquierda
        }
    
    }

}