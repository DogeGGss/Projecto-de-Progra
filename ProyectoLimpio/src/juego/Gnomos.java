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
    private boolean direccion; // True el gnomo mira a la derecha, false si a la izquierda
    private Entorno entorno; // Referencia al entorno de juego
    private boolean colision=false;//true si esta sobre una plataforma, false sino

    public Gnomos(double xInicial,double yInicial,double escala,Entorno entorno){
    this.xInicial=xInicial;
    this.yInicial=yInicial;
    this.escala=escala;
    this.imagen1=Toolkit.getDefaultToolkit().getImage("C:\\Users\\destr\\Desktop\\Tarea progra\\Projecto-de-Progra\\ProyectoLimpio\\gnomo derecha.png");
    this.imagen2=Toolkit.getDefaultToolkit().getImage("C:\\Users\\destr\\Desktop\\Tarea progra\\Projecto-de-Progra\\ProyectoLimpio\\gnomos.png");

    //setea de forma aleatoria hacia donde mira el gnomo
    Random rand = new Random();
    int direccion = rand.nextInt(2);
   
    if(direccion==1){
        this.direccion=true;
    } else {
        this.direccion=false;
    }
        }
}
