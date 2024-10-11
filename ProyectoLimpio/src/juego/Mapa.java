package juego; 

import java.awt.Image;
import java.awt.Color;
import entorno.Entorno;
import java.awt.Toolkit;

public class Mapa {
    private int xCentro; // Para centrar horizontalmente
    private int yInicial; // Para la posición vertical inicial
    private int ancho;
    private int alto;
    private int cantidadFilas;
    private Entorno entorno;
    private Image imagen;
    private Image fondo;
    private int espaciadoHorizontal = 200; // Espacio horizontal entre rectángulos
    private int espaciadoVertical = 150;   // Espacio vertical entre filas

    // Constructor
    public Mapa(int xInicial, int yInicial, int ancho, int alto, int cantidadFilas, Entorno entorno) {
        this.cantidadFilas = cantidadFilas; // Número de filas en la pirámide
        this.ancho = ancho; // Ancho de cada rectángulo
        this.alto = alto;  // Altura de cada rectángulo
        this.xCentro = 1920 / 2; // Mitad de la resolución horizontal (1900)
        this.yInicial = yInicial; // Posición vertical inicial
        this.entorno = entorno; // Inicializa el entorno
        this.imagen = Toolkit.getDefaultToolkit().getImage("C:\\Users\\destr\\Desktop\\Tarea progra\\ProyectoLimpio\\Plataformas.png");
        this.fondo = Toolkit.getDefaultToolkit().getImage("C:\\Users\\destr\\Desktop\\Tarea progra\\ProyectoLimpio\\Fondo.png"); // Carga la imagen de fondo
    }

    // Método para dibujar el fondo
    public void dibujarFondo() {
        entorno.dibujarImagen(fondo, 960, 530, 0, 12); // Dibuja la imagen de fondo en la posición (0, 0)
    }

    // Método para dibujar los rectángulos en el entorno
    public void dibujarRectangulos() {
        for (int fila = 0; fila < cantidadFilas; fila++) {
            int cantidadRectangulos = cantidadFilas - fila; // Número de rectángulos en la fila actual
            int anchoTotalFila = cantidadRectangulos * (ancho + espaciadoHorizontal) - espaciadoHorizontal; // Ancho total de la fila

            // Calcula la posición inicial X para centrar la fila en la pantalla
            int xInicial = xCentro - (anchoTotalFila / 2);

            // Calcula la posición Y de la fila, ajustando el espacio vertical
            int y = yInicial - fila * (alto + espaciadoVertical);

            for (int i = 0; i < cantidadRectangulos; i++) {
                // Calcula la posición X de cada rectángulo en la fila
                int x = xInicial + i * (ancho + espaciadoHorizontal);
                entorno.dibujarImagen(imagen, x, y, 0, 6); // Dibuja la imagen en el entorno
            }
        }
    }
}
