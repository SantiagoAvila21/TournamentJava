package Logica;

import java.util.ArrayList;

public class Cuadro {
	private ArrayList<Jugador> players;

	public Cuadro(ArrayList<Jugador> jugadoresEnCuadro) {
		this.players = jugadoresEnCuadro;
	}
	
	public Cuadro() {
		this.players = new ArrayList<Jugador>();
	}
	
	public ArrayList<Jugador> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Jugador> players) {
		this.players = players;
	}
}
