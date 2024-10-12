package juego;

import java.awt.Image;
import entorno.Entorno;
import java.awt.Toolkit;

public class Mapa {
    private int xCentro;
    private int yInicial;
    private int ancho;
    private int alto;
    private int cantidadFilas;
    private Entorno entorno;
    private Image imagen;
    private Image fondo;
    private int espaciadoHorizontal = 200;
    private int espaciadoVertical = 150;

    public Mapa(int xInicial, int yInicial, int ancho, int alto, int cantidadFilas, Entorno entorno) {
        this.cantidadFilas = cantidadFilas;
        this.ancho = ancho;
        this.alto = alto;
        this.xCentro = 1920 / 2;
        this.yInicial = yInicial;
        this.entorno = entorno;
        this.imagen = Toolkit.getDefaultToolkit().getImage("C:\\Users\\destr\\Desktop\\Tarea progra\\Projecto-de-Progra\\ProyectoLimpio\\Plataformas.png");
        this.fondo = Toolkit.getDefaultToolkit().getImage("C:\\Users\\destr\\Desktop\\Tarea progra\\Projecto-de-Progra\\ProyectoLimpio\\Fondo.png");
    }

    public void dibujarFondo() {
        entorno.dibujarImagen(fondo, 960, 530, 0, 12);
    }

    public boolean verificarColision(Pep pep) {
        for (int fila = 0; fila < cantidadFilas; fila++) {
            int cantidadRectangulos = cantidadFilas - fila;
            int anchoTotalFila = cantidadRectangulos * (ancho + espaciadoHorizontal) - espaciadoHorizontal;
            int xInicial = xCentro - (anchoTotalFila / 2);
            int y = yInicial - fila * (alto + espaciadoVertical);

            for (int i = 0; i < cantidadRectangulos; i++) {
                int x = xInicial + i * (ancho + espaciadoHorizontal);
                if (pep.colisionaCon(x, y, ancho, alto)) {
                    return true;
                }
            }
        }
        return false;
    }

    public int obtenerYPlataforma(Pep pep) {
        for (int fila = 0; fila < cantidadFilas; fila++) {
            int cantidadRectangulos = cantidadFilas - fila;
            int anchoTotalFila = cantidadRectangulos * (ancho + espaciadoHorizontal) - espaciadoHorizontal;
            int xInicial = xCentro - (anchoTotalFila / 2);
            int y = yInicial - fila * (alto + espaciadoVertical);

            for (int i = 0; i < cantidadRectangulos; i++) {
                int x = xInicial + i * (ancho + espaciadoHorizontal);
                if (pep.colisionaCon(x, y, ancho, alto)) {
                    return y;
                }
            }
        }
        return Integer.MAX_VALUE;
    }

    public void dibujarRectangulos() {
        for (int fila = 0; fila < cantidadFilas; fila++) {
            int cantidadRectangulos = cantidadFilas - fila;
            int anchoTotalFila = cantidadRectangulos * (ancho + espaciadoHorizontal) - espaciadoHorizontal;
            int xInicial = xCentro - (anchoTotalFila / 2);
            int y = yInicial - fila * (alto + espaciadoVertical);

            for (int i = 0; i < cantidadRectangulos; i++) {
                int x = xInicial + i * (ancho + espaciadoHorizontal);
                entorno.dibujarImagen(imagen, x, y, 0, 6);
            }
        }
    }
}
