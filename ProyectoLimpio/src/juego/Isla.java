package juego;


import java.awt.Image;
import entorno.Entorno;
import java.awt.Toolkit;


public class Isla {
    final int xInicial;
    final int xCentro;


    final int yInicial;
    final int anchoIsla;
    final int altoIsla;

    final Entorno entorno;
    final Image imagen;

    public int getxInicial() {
        return xInicial;
    }

    public int getAnchoIsla() {
        return anchoIsla;
    }

    public int getAltoIsla() {
        return altoIsla;
    }

    public int getxCentro() {
        return xCentro;
    }
   
    public int getyInicial() {
        return yInicial;
    }



    public Image getImagen() {
        return imagen;
    }



    public Isla(int xInicial, int yInicial, int ancho, int alto, Entorno entorno) {
        this.xInicial=xInicial;
        this.anchoIsla = ancho;
        this.altoIsla = alto;
        this.xCentro = 1920 / 2;
        this.yInicial = yInicial;
        this.entorno = entorno;
        this.imagen = Toolkit.getDefaultToolkit().getImage("Plataformas.png");
       
    }
   
}
