package juego;

import entorno.Entorno;

public class ConjuntoBombaTortuga {
    final Entorno entorno;
    BombaTortuga[] bomba= new BombaTortuga[4]; //es una bomba por cada tortuga
    public double xInicial;
    public double yInicial;

    public ConjuntoBombaTortuga(Entorno entorno){
        this.entorno=entorno;
    }

    //crea la bomba solamente cuando no haya una ya activa de esa tortuga y la tortuga este sobre una isla
    public void crearBomba(Tortugas tortuga, int n){
        if(bomba[n]==null && tortuga.colision && tortuga!=null){
            //se crea en el punto donde esta la tortuga y mira hacia la direccion que este mirando en ese momento la tortuga
            this.xInicial=tortuga.getxInicial();
            this.yInicial=tortuga.getyInicial()+40;
            bomba[n]= new BombaTortuga(xInicial, yInicial, entorno, tortuga.direccion);
        } 
        if( bomba[n]!=null){
            bomba[n].dibujarBomba();
        }
    }
}
