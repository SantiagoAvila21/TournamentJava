package Logica;

public class Jugador implements Comparable<Jugador>{
	private String Nombre;
	private int Ventaja;
	
	public Jugador(String nombre, int vent) {
		this.Nombre = nombre;
		this.Ventaja = vent;
	}
	
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public int getVentaja() {
		return Ventaja;
	}
	public void setVentaja(int ventaja) {
		Ventaja = ventaja;
	}
	@Override
	public int compareTo(Jugador o) {
		 int compareage = ((Jugador) o).getVentaja();
		 return this.Ventaja - compareage;
	}
	
}
