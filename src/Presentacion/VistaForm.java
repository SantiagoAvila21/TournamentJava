package Presentacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.LayoutManager;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class VistaForm extends JFrame{
	
	private JButton start, addPlayer, deletePlayer;
	private Controlador control;
	private Modelo model;
	private JLabel labelNumJugadores;
	private JTextField nombreJugador, ventajaJugador;
	private JTable tablaPlayers;
	int numPlayers = 0;
	
	public VistaForm(Modelo m) {
		model = m;
		initComponents();
		captureEvents();
	}

	public void initComponents() {
		JPanel fullGUI = new JPanel();
		fullGUI.setLayout(new BoxLayout(fullGUI, BoxLayout.PAGE_AXIS));
		labelNumJugadores = new JLabel("Numero de Jugadores: " + numPlayers);
		
		JPanel panelForm = new JPanel();
		nombreJugador = new JTextField(25);
		ventajaJugador = new JTextField(25);
		addPlayer = new JButton("Añadir Jugador");
		panelForm.add(new JLabel("Nombre Jugador: "));
		panelForm.add(nombreJugador);
		panelForm.add(new JLabel("Ventaja Jugador: "));
		panelForm.add(ventajaJugador);
		panelForm.add(addPlayer);
		
		JPanel helperPanel = new JPanel();
		start = new JButton("Iniciar Torneo");
		helperPanel.add(labelNumJugadores);
		helperPanel.add(start);
		
		String[] columnNames = { "Nombre Jugador", "Ventaja"};
		DefaultTableModel model = new DefaultTableModel(columnNames, 0);
		tablaPlayers = new JTable(model);
		tablaPlayers.setRowHeight(25);
		tablaPlayers.setFillsViewportHeight(true);
		
        deletePlayer = new JButton("Eliminar Jugador seleccionado");
		JScrollPane panelTabla = new JScrollPane(tablaPlayers);
		
		fullGUI.add(panelForm);
		fullGUI.add(helperPanel);
		fullGUI.add(panelTabla);
		fullGUI.add(deletePlayer);
		
		this.add(fullGUI);
		this.pack();
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void captureEvents() {
		this.start.setActionCommand("start");
		this.start.addActionListener(getControl());
		this.addPlayer.setActionCommand("add");
		this.addPlayer.addActionListener(getControl());
		this.deletePlayer.setActionCommand("delete");
		this.deletePlayer.addActionListener(getControl());
	}
	
	
	
	public JTable getTable() {
		return this.tablaPlayers;
	}
	
	public JTextField getNombre() {
		return this.nombreJugador;
	}
	
	public JTextField getVentaja() {
		return this.ventajaJugador;
	}
	
	public void addPlayer() {
		this.numPlayers++;
		labelNumJugadores.setText("Numero de Jugadores: " + numPlayers);
	}
	
	public Controlador getControl() {
		if(control == null) return new Controlador(this);
		else return control;
	}
	
	public Modelo getModel() {
		return this.model;
	}
}

