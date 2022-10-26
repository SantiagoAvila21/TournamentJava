package Presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controlador implements ActionListener{

	private Modelo model;
	
	public Controlador(VistaForm v) {
		model = v.getModel();
	}
	
	//Se obtienen las acciones hechas desde la VistaPrincipal
	@Override
	public void actionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		if(evt.getActionCommand().equals("add")) model.addPlayer();
		if(evt.getActionCommand().equals("delete")) model.deletePlayer();
		if(evt.getActionCommand().equals("start")) model.startTournament();
	}

}
