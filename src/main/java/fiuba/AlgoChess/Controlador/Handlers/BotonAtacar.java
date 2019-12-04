package fiuba.AlgoChess.Controlador.Handlers;

import fiuba.AlgoChess.Modelo.Ubicacion.Posicion;
import fiuba.AlgoChess.Vista.Tablero.VistaTablero;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class BotonAtacar implements EventHandler<ActionEvent> {

	private Posicion posicion;
	private VistaTablero tablero;
	
	
	public BotonAtacar(Posicion posicion, VistaTablero tablero) {
		
		this.posicion = posicion;
		this.tablero = tablero;
	}
	
	
	@Override
	public void handle(ActionEvent event) {
	
		if(this.tablero.estadoActual().equals("ataque")) {
			
			this.tablero.comportamientoSeleccionarUnidad();
		} else {
			
			this.tablero.comportamientoDeAtaque(this.posicion);
		}
	}
}
