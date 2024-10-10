package juego;
import entorno.Entorno;

public class Pep {
    //para utulizar la funcion del entorno dibujarImagen(Image imagen, double x,double y, double angulo, double escala)

    private double xInicial;
    private double yInicial;
    private double angulo;
    private double escala;

    //para pasar de archivo a imagen se usa  cargarImagen(String archivo)
    private String archivo;

    //para donde mira Pep
    private boolean sentido;

    private Entorno entorno; // Asegúrate de tener una clase Entorno
   

    private Pep(xInicial,yInicial,angulo,escala,archivo,sentido,entorno){

    }
}
