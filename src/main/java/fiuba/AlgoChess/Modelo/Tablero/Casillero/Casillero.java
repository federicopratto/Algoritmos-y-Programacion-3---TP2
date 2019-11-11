package fiuba.AlgoChess.Modelo.Tablero.Casillero;

import fiuba.AlgoChess.Modelo.Errores.*;
import fiuba.AlgoChess.Modelo.Jugador.Bando;
import fiuba.AlgoChess.Modelo.Unidad.Unidad;


public class Casillero {

	// Atributos.
	
	private int[] ubicacion;
	private Estado estado;
	private Bando bando;


	// Metodos.
	
	/* Este metodo esta bien.
	 * 
	 * POST: Crea un nuevo Casillero Libre con un determinado Bando.
	 */
	public Casillero(int x, int y, Bando bando) {
		
		this.ubicacion = new int [2];
		this.ubicacion[0] = x;
		this.ubicacion[1] = y;
		this.estado = new Libre();
		this.bando = bando;
	}
	
	
	/*
	 * PRE:  El casillero se encuentra vacio.
	 * POST: Agrega una unidad al casillero.
	 */
	public boolean agregarEntidad(Unidad unidad) {

		try {

			estado.agregarEntidad(unidad, this);
			unidad.serColocadaEnCasilleroDeBando(this.bando);

		} catch (CasilleroOcupadoException error) {

			return false;
		} catch (DistintoBandoException e){

			return false;
		}

		estado = new Ocupado(unidad);
		return true;
	}


	/*
	 * PRE:  El casillero se encuentra ocupado.
	 * POST: Quita una unidad al casillero.
	 */
	public Unidad quitarEntidad() {
		Unidad unidad;
		
		try {

			unidad = estado.quitarEntidad();
			estado = new Libre();

		} catch (CasilleroLibreException error) {

			return null;
		}
		return unidad;
	}


	/*
	 *
	 */
	public int[] getPosicion() {

		return ubicacion;
	}
}
