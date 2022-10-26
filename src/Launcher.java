import Presentacion.Modelo;

public class Launcher {
	private Modelo App;
	
	public Launcher() {
		App = new Modelo();
		App.init();
	}
	
	public static void main(String[] args) {
		new Launcher();
	}
	
}
