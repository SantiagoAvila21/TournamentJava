package Presentacion;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import Logica.Cuadro;
import Logica.CuadroCreator;
import Logica.Jugador;

public class Modelo {
	private Vista Vistaprincipal;
	private VistaForm VistaFormu;
	private ArrayList<Jugador> jugadoresInscritos;
	
	public void init() {
		this.VistaFormu = new VistaForm(this);
		this.VistaFormu.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.VistaFormu.setResizable(true);
		this.Vistaprincipal = new Vista(this);
		this.Vistaprincipal.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.Vistaprincipal.setResizable(false);
		this.Vistaprincipal.setSize(1200,850);
		this.Vistaprincipal.setVisible(false);
	}
	
	public void addPlayer() {
		if(this.VistaFormu.getNombre().getText().equals("") || this.VistaFormu.getVentaja().getText().equals("")) {
			JOptionPane.showMessageDialog(VistaFormu,"Porfavor ingresar datos completos del Jugador");
			return;
		}
		String data[] = {this.VistaFormu.getNombre().getText(), this.VistaFormu.getVentaja().getText()};
		DefaultTableModel tablModel = (DefaultTableModel) this.VistaFormu.getTable().getModel();
		tablModel.addRow(data);
		this.VistaFormu.getNombre().setText("");
		this.VistaFormu.getVentaja().setText("");
		this.VistaFormu.addPlayer();
	}
	
	public void deletePlayer() {
		DefaultTableModel tablModel = (DefaultTableModel) this.VistaFormu.getTable().getModel();
		if(this.VistaFormu.getTable().getSelectedRowCount() == 1) tablModel.removeRow(this.VistaFormu.getTable().getSelectedRow());
		else {
			if(this.VistaFormu.getTable().getRowCount() == 0) JOptionPane.showMessageDialog(VistaFormu,"Ningun Jugador ha sido añadido");
			else JOptionPane.showMessageDialog(VistaFormu,"Porfavor selecciona solo un jugador a eliminar");
		}
	}
	
	public void startTournament() {
		this.jugadoresInscritos = new ArrayList<Jugador>();
		DefaultTableModel tablModel = (DefaultTableModel) this.VistaFormu.getTable().getModel();

        for (int i = 0; tablModel.getRowCount() > i; i++) {
            String nombreJugador = (String) tablModel.getValueAt(i, 0);
            int ventajaJugador = Integer.parseInt((String) tablModel.getValueAt(i, 1));
            this.jugadoresInscritos.add(new Jugador(nombreJugador, ventajaJugador));
        }
        CuadroCreator creadorCuadro = new CuadroCreator(this.jugadoresInscritos);
		this.VistaFormu.setVisible(false);
		this.Vistaprincipal.paintCuadros(creadorCuadro.getCuadros(), this.jugadoresInscritos.size()/4);
		this.Vistaprincipal.setVisible(true);
	}
}
