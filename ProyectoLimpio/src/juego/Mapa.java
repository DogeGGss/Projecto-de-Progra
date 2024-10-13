package juego;

import java.awt.Image;
import entorno.Entorno;
import java.awt.Toolkit;

public class Mapa {
    final int xCentro;
    final int yInicial;
    final int anchoIsla;
    final int altoIsla;
    final int cantidadFilas;
    final Entorno entorno;
    final Image imagen;
    final Image fondo;
    final int espaciadoHorizontal = 200;
    final int espaciadoVertical = 150;
    public Mapa(int xInicial, int yInicial, int ancho, int alto, int cantidadFilas, Entorno entorno) {
        this.cantidadFilas = cantidadFilas;
        this.anchoIsla = ancho;
        this.altoIsla = alto;
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
        // Coordenadas de los pies de Pep
        double yPiesPep = pep.getY() + pep.getAltura();
        for (int fila = 0; fila < cantidadFilas; fila++) {
            int cantidadRectangulos = cantidadFilas - fila;
            int anchoTotalFila = cantidadRectangulos * (anchoIsla + espaciadoHorizontal) - espaciadoHorizontal;
            int xInicial = xCentro - (anchoTotalFila / 2);
            int y = yInicial - fila * (altoIsla + espaciadoVertical);
            for (int i = 0; i < cantidadRectangulos; i++) {
                int x = xInicial + i * (anchoIsla + espaciadoHorizontal);
    
                // Verificar colisión solo con los pies de Pep
                if (pep.colisionaCon(x, y, anchoIsla, altoIsla) && yPiesPep >= y && yPiesPep <= y + altoIsla) {
                    return true;
                }
            }
        }
        return false;
    }
    public int obtenerYPlataforma(Pep pep) {
        for (int fila = 0; fila < cantidadFilas; fila++) {
            int cantidadRectangulos = cantidadFilas - fila;
            int anchoTotalFila = cantidadRectangulos * (anchoIsla + espaciadoHorizontal) - espaciadoHorizontal;
            int xInicial = xCentro - (anchoTotalFila / 2);
            int y = yInicial - fila * (altoIsla + espaciadoVertical);

            for (int i = 0; i < cantidadRectangulos; i++) {
                int x = xInicial + i * (anchoIsla + espaciadoHorizontal);
                if (pep.colisionaCon(x, y, anchoIsla, altoIsla)) {
                    return y;
                }
            }
        }
        return Integer.MAX_VALUE;
    }
    public void dibujarRectangulos() {
        for (int fila = 0; fila < cantidadFilas; fila++) {
            int cantidadRectangulos = cantidadFilas - fila;
            int anchoTotalFila = cantidadRectangulos * (anchoIsla + espaciadoHorizontal) - espaciadoHorizontal;
            int xInicial = xCentro - (anchoTotalFila / 2);
            int y = yInicial - fila * (altoIsla + espaciadoVertical);
            for (int i = 0; i < cantidadRectangulos; i++) {
                int x = xInicial + i * (anchoIsla + espaciadoHorizontal);
                entorno.dibujarImagen(imagen, x, y, 0, 6);
            }
        }
    }
}

