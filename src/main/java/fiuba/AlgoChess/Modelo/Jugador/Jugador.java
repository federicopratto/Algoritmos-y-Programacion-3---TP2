package fiuba.AlgoChess.Modelo.Jugador;

import fiuba.AlgoChess.Modelo.Unidad.Entidad;
import java.util.Scanner;

public class Jugador {

    private String nombre;
    private int puntos;
    private int cantidadEntidades = 0;
    Scanner consola = new Scanner(System.in);

    public Jugador(String nombre){

        this.nombre = nombre;
        this.puntos = 20;
    }


    public boolean colocarEntidad(Entidad unaEntidad){

        if(this.puntos >= unaEntidad.getCosto()){ //precioentidad

            this.puntos-= unaEntidad.getCosto();
            this.cantidadEntidades ++;
            return true;

        }else{
            return false;
        }

    }

    //funciona como controlador de estado de jugador.
    public boolean sigueJugando(){
        return(this.cantidadEntidades >0);

    }

    public int[] elegirCasillero(){

        return
    }
}