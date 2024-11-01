package juego;

import entorno.Entorno;

public class ConjuntoSuperGnomo {
    SuperGnomo[] ElSuperGnomo= new SuperGnomo[1];  //1 super gnomo en pantalla
    final Entorno entorno;
    public ConjuntoSuperGnomo(Entorno entorno){
        this.entorno=entorno;
    }


    public void dibujarSuperGnomos() {
        for (int i = 0; i < ElSuperGnomo.length; i++) {
             //Si no hay otro super gnomo en pantalla entonces crea a un super gnomo!
            if (this.ElSuperGnomo[i] == null) {
                double x = 1920/2; 
                double y = -70;          
                this.ElSuperGnomo[i] = new SuperGnomo(x, y, 0.16, entorno);
            }
            this.ElSuperGnomo[i].dibujarEseSuperGnomo(ElSuperGnomo[i].getxInicial(), ElSuperGnomo[i].getyInicial());
        }
    }
}
