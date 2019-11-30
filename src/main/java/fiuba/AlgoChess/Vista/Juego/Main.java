package fiuba.AlgoChess.Vista.Juego;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import fiuba.AlgoChess.Modelo.Tablero.Tablero;
import fiuba.AlgoChess.Vista.Compra.CajaDeUnidades;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import fiuba.AlgoChess.Controlador.BotonComenzarJuego;
import fiuba.AlgoChess.Controlador.BotonNuevaPartida;
import fiuba.AlgoChess.Controlador.BotonSalirDelJuego;
import fiuba.AlgoChess.Modelo.Jugador.Bando;
import fiuba.AlgoChess.Modelo.Jugador.Jugador;

public class Main extends Application {

	private Stage escenario;
	private Bando bando1;
	private Bando bando2;
	private Jugador jugador1;
	private Jugador jugador2;
	private Tablero tablero;

	public static void main(String[] args) {
		
		launch(args);
	}

	// Listo.
	@Override
	public void start(Stage stage) throws Exception {

//		stage.setMaximized(true); <-- No creo que haga falta
		this.iniciarJuego();
		
		stage.setResizable(true);
		this.escenario = stage;
		this.escenario.setTitle("AlgoChess");
		this.cambiarEscenaA(this.escenaBienvenida());
		this.escenario.show();
	}
	
	
	private void iniciarJuego() {

		this.bando1 = new Bando();
		this.bando2 = new Bando();
		this.jugador1 = new Jugador("", this.bando1);
		this.jugador2 = new Jugador("", this.bando2);
		this.tablero = new Tablero(this.bando1, this.bando2);
	}

	public void cambiarEscenaA(Scene nuevaEscena) {
		
		this.escenario.setScene(nuevaEscena);
	}

	
	/*
	 * *********************************************
	 * *----------------- ESCENAS -----------------*
	 * *********************************************
	 */
	
	// Listo.
	public Scene escenaBienvenida() throws FileNotFoundException {

		Label labelTitulo = new Label("");
		labelTitulo.setFont(Font.font("Verdana", 48));
		labelTitulo.setTextFill(Color.rgb(255, 255, 255));

		Button botonNuevaPartida = new Button("Nueva Partida");
		botonNuevaPartida.setFont(Font.font("Verdana", 16));
		botonNuevaPartida.setOnAction(new BotonNuevaPartida(this));

		Button botonSalir = new Button("Salir");
		botonSalir.setFont(Font.font("Verdana", 16));
		botonSalir.setOnAction(new BotonSalirDelJuego());
		
		VBox botones = new VBox(botonNuevaPartida, botonSalir);
		botones.setAlignment(Pos.BOTTOM_CENTER);
		botones.setSpacing(10);

		VBox contenedorPrincipal = new VBox(labelTitulo, botones);
		contenedorPrincipal.setSpacing(200);
		contenedorPrincipal.setAlignment(Pos.CENTER);
		
		
		Background fondo = new CreadorDeFondos().crearFondo("./recursos/fondos/fondo1.png");
		contenedorPrincipal.setBackground(fondo);
		
		return new Scene(contenedorPrincipal, 800, 600);
	}


	// Listo.
	public Scene escenaCargaDeJugadores() throws FileNotFoundException{
		
		// Titulo
	    Label labelTitulo = new Label("Ingresar nombres");
		labelTitulo.setFont(Font.font("Times New Roman", 48));
		labelTitulo.setTextFill(Color.rgb(255, 255, 255));

		// Jugador 1
		Label labelJugador1 = new Label("Jugador 1");
		labelJugador1.setFont(Font.font("TimesNewRoman",20));
        labelJugador1.setTextFill(Color.rgb(255, 255, 255));
        TextField nombreJugador1 = new TextField();
        nombreJugador1.setPromptText("Ingresa tu nombre");
        
        // Jugador 2
        Label labelJugador2 = new Label("Jugador 2");
        labelJugador2.setFont(Font.font("TimesNewRoman", 20));
        labelJugador2.setTextFill(Color.rgb(255, 255, 255));
		TextField nombreJugador2 = new TextField();
		nombreJugador2.setPromptText("Ingresa tu nombre");

		Button botonComenzar = new Button("¡Comenzar Juego!");
		botonComenzar.setFont(Font.font(18));
		
		BotonComenzarJuego accionBotonComenzar = new BotonComenzarJuego(nombreJugador1, nombreJugador2, this);
		botonComenzar.setOnAction(accionBotonComenzar);
		
		
		//Creacion de las XBox
		HBox labelJugadores = new HBox(labelJugador1,labelJugador2);
		labelJugadores.setAlignment(Pos.CENTER);
		labelJugadores.setSpacing(500);

		HBox cuadrosDeTexto = new HBox(nombreJugador1,nombreJugador2);
		cuadrosDeTexto.setAlignment(Pos.CENTER);
		cuadrosDeTexto.setSpacing(380);

		VBox contenedorJugadores = new VBox(labelJugadores, cuadrosDeTexto);
		contenedorJugadores.setAlignment(Pos.CENTER);
		contenedorJugadores.setSpacing(15);
		
		VBox contenedorConBoton = new VBox(contenedorJugadores, botonComenzar);
		contenedorConBoton.setAlignment(Pos.CENTER);
		contenedorConBoton.setSpacing(100);
		
		VBox contenedorPrincipal = new VBox(labelTitulo, contenedorConBoton);
		contenedorPrincipal.setAlignment(Pos.CENTER);
		contenedorPrincipal.setSpacing(150);

		// Cargo el fondo.
		Background fondo = new CreadorDeFondos().crearFondo("./recursos/fondos/fondo2.png");
		contenedorPrincipal.setBackground(fondo);

		return new Scene(contenedorPrincipal, 800, 600);
	}
	
	
	public Scene escenaCompraDeUnidades1() throws FileNotFoundException {
		
		
		CajaDeUnidades cajaUnidades = new CajaDeUnidades(this, 1);
		Button botonTerminarCompra = new Button("Terminar Compra");
		
		VBox contenedorPrincipal = new VBox(cajaUnidades, botonTerminarCompra);
		contenedorPrincipal.setAlignment(Pos.CENTER);
		contenedorPrincipal.setSpacing(150);
		
		// Cargo el fondo.
		Background fondo = new Background(new BackgroundImage(new Image(new FileInputStream("./recursos/fondo1.png")),
				BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(800, 600, false, false, false, false)));
		contenedorPrincipal.setBackground(fondo);

		return null;
	}
	
	
//	public Jugador getJugador(int numeroJugador) {
//
//		return this.jugadores[numeroJugador - 1];
//	}
//
//	public Bando getBando(int numeroJugador) {
//
//		return bandos[numeroJugador - 1];
//	}

	public void asignarNombreJugadores(String jugador1, String jugador2) {
		
		this.jugador1.setNombre(jugador1);
		this.jugador2.setNombre(jugador2);
	}
}