package juego;

import java.awt.Image;
import entorno.Entorno;
import java.awt.Toolkit;


public class RecolectoraDeGnomos {
    public double xInicial; // Posición inicial de la recolectora de gnomos en el eje X
    public double yInicial; // Posición inicial de la recolectora de gnomos en el eje Y
    private double escala = 1;   // Escala para dibujar la imagen de la recolectora de gnomos
    public double velocidad = 6.3; // Velocidad de movimiento de la recolectora de gnomos
    private Image imagen; // Imagende la recolectora de gnomos mirando a la derecha
    private Entorno entorno; // Referencia al entorno de juego
    


    //constructor de la recolectora de gnomos
    public RecolectoraDeGnomos(double xInicial,double yInicial,Entorno entorno){
    this.xInicial=xInicial;
    this.yInicial=yInicial;
    this.entorno=entorno;
    this.imagen=Toolkit.getDefaultToolkit().getImage("portalInterDimencionalDeGnomos.png");
    }
    
    //dibuja la recolectora de gnomos
    public void dibujarRecolectoraDeGnomos(){
        entorno.dibujarImagen(imagen,xInicial,yInicial,0,escala);
       // moverRecolectoraDeGnomos();
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

    public Image getImagen() {
        return imagen;
    }

    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }

    public Entorno getEntorno() {
        return entorno;
    }

    public void setEntorno(Entorno entorno) {
        this.entorno = entorno;
    }

    public int getAltura() {
        return (int) (imagen.getHeight(null) * escala); // Retorna la altura de Pep escalada
    }
    public int getAnchoPep() {
        return (int) (imagen.getWidth(null) * escala); // Retorna el ancho de Pep escalado
    }
}
