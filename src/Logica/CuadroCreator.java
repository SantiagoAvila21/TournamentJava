package Logica;

import java.util.ArrayList;
import java.util.Collections;

public class CuadroCreator {

	private Cuadro[] cuadros;
	private ArrayList<Jugador> players;
	
	public CuadroCreator(ArrayList<Jugador> jugd) {
		this.players = jugd;
	}
	
	private void CreateCuadro() {
		this.cuadros = new Cuadro[this.players.size()/4];
		for(int i = 0; i < this.players.size()/4; i++) this.cuadros[i] = new Cuadro();
	
		int numJugadores = this.players.size();
		Collections.sort(this.players);
		int numCuadros = numJugadores/4, iterCuadro = 0, fct = 1;
	    boolean rep = false;
	    
		for(Jugador player: this.players) {
			this.cuadros[iterCuadro].getPlayers().add(player);
			if(rep){
                iterCuadro += fct;
                rep = false;
            }else if(iterCuadro == numCuadros-1 || (iterCuadro == 0 && cuadros[iterCuadro].getPlayers().size() > 1)){
                rep = true;
                fct *= -1;
            }else iterCuadro += fct;
		}
	}

	public Cuadro[] getCuadros() {
		if(this.cuadros == null) CreateCuadro();
		return this.cuadros;
	}

	public void setCuadros(Cuadro[] cuadros) {
		this.cuadros = cuadros;
	}
	
}
