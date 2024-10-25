package juego;

import java.awt.Image;
import entorno.Entorno;
import java.awt.Toolkit;
import java.util.Random;

public class Tortugas {
    private double xInicial; // Posición inicial de la tortuga en el eje X
    private double yInicial; // Posición inicial de la tortuga en el eje Y
    private double escala;   // Escala para dibujar la imagen de la tortuga
    public double velocidad = 1; // Velocidad de movimiento de la tortuga
    private double velocidadVertical = 0; // Velocidad vertical de la tortuga
    private Image imagen1; // Imagen de la tortuga mirando a la derecha
    private Image imagen2; // Imagen de la tortuga mirando a la izquierda
    public Image imagenInicial; //imagen con el sentido establecido por la direccion
    public boolean direccion; // True la tortuga mira a la derecha, false si a la izquierda
    private Entorno entorno; // Referencia al entorno de juego
    public boolean colisionLateral=false;//true si esta sobre una plataforma, false sino
    public boolean flag;
    public boolean enElAire;
    public int tiempoInicial;
    public double gravedad;
    public boolean colision;



    public Tortugas(double xInicial,double yInicial,double escala,Entorno entorno){
        this.xInicial=xInicial;
        this.yInicial=yInicial;
        this.escala=escala;
        this.entorno=entorno;
        this.imagen1=Toolkit.getDefaultToolkit().getImage("rammus derecha.png");
        this.imagen2=Toolkit.getDefaultToolkit().getImage("rammus izquierda.png");
        
        //setea de forma aleatoria hacia donde mira la tortuga
        Random rand = new Random();
        boolean direccionboolean = rand.nextBoolean();
        this.direccion=direccionboolean;

    }

    public void dibujarEsaTortuga(double x,double y){
        if(direccion){
            this.imagenInicial=imagen1;
        } else {
            this.imagenInicial=imagen2;
        }
        entorno.dibujarImagen(imagenInicial,x,y,0,escala);
    }

    public void moverTortuga() {
        // Movimiento continuo del gnomo
    if(colision){
        if (direccion) {
            xInicial += velocidad; // Movimiento a la derecha
        } else {
            xInicial -= velocidad; // Movimiento a la izquierda
        }
        if(colisionLateral){
            direccion=!direccion;
           }
    }
   
  
   
   aplicarGravedad();// Aplicar gravedad en cada tick
}
   
   
public void aplicarGravedad() {
    // aplica la gravedad
    this.yInicial += this.gravedad; 

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

public Image getImagenInicial() {
    return imagenInicial;
}

public void setImagenInicial(Image imagenInicial) {
    this.imagenInicial = imagenInicial;
}

public boolean isDireccion() {
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

public boolean isColisionLateral() {
    return colisionLateral;
}

public void setColisionLateral(boolean colisionLateral) {
    this.colisionLateral = colisionLateral;
}

public boolean isFlag() {
    return flag;
}

public void setFlag(boolean flag) {
    this.flag = flag;
}

public boolean isEnElAire() {
    return enElAire;
}

public void setEnElAire(boolean enElAire) {
    this.enElAire = enElAire;
}

public int getTiempoInicial() {
    return tiempoInicial;
}

public void setTiempoInicial(int tiempoInicial) {
    this.tiempoInicial = tiempoInicial;
}   

public double getGravedad() {
    return gravedad;
}

public void setGravedad(double gravedad) {
    this.gravedad = gravedad;
}

public int getAltura() {
    return (int) (imagen1.getHeight(null) * escala); // Retorna la altura de Pep escalada
}
public int getAncho() {
    return (int) (imagen1.getWidth(null) * escala); // Retorna el ancho de Pep escalado
}

}
