package Presentacion;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import Logica.Cuadro;
import Logica.Jugador;

public class Vista extends JFrame{

	private Modelo model;
	private Controlador control;
	private JTable[] cuadros;
	private JPanel CuadrosPanel;
	JTextField textBox = new JTextField();
	
	public Vista(Modelo m) {
		model = m;
		initComponents();
	}
	
	public boolean isUnderDiagonal(int posx, int posy) {
		System.out.println(posx + " " + posy + "-*-*-*-*");
		if(posx >= (posx-1)) return true;
		return false;
	}
	
	public void paintCuadros(Cuadro[] infoCuadros, int numCuadros) {
		cuadros = new JTable[numCuadros];
		for(int i = 0; i < numCuadros; i++) {
			// Generar la informacion que llevara cada cuadro en la tabla
			String[] columnNames = {"" ,"Nombre Jugador (Ventaja)", "1", "2", "3", "4", "5"};
			Object[][] data = new Object[5][6];
			int iterCol = 0;
			for(Jugador it: infoCuadros[i].getPlayers()) {
				Arrays.fill(data[iterCol], "");
				data[iterCol][1] = it.getNombre() + " (" + it.getVentaja() + ")";
				data[iterCol][0] = iterCol+1;
				//Pintar de negro la celda interseccion
				TableColumn col = cuadros[i].getColumnModel().getColumn(iterCol+2);
				col.setCellEditor(new DefaultCellEditor(textBox));
				
				iterCol++;
			}
			
			// Pintar la tabla con la informacion predispuesta y agregando los listeners a cada celda
			DefaultTableModel model = new DefaultTableModel(data, columnNames);
			this.cuadros[i] = new JTable(model);
			this.cuadros[i].setRowHeight(25);
			this.cuadros[i].getColumnModel().getColumn(1).setPreferredWidth(165);
			this.cuadros[i].getColumnModel().getColumn(0).setPreferredWidth(20);
			this.cuadros[i].setFocusable(true);
			this.cuadros[i].setDefaultEditor(Object.class, null);
			
			this.cuadros[i].addMouseListener(new MouseAdapter() {
			    public void mousePressed(MouseEvent mouseEvent) {
			    	// Ingresar la informacion del partido seleccionado
			        JTable table =(JTable) mouseEvent.getSource();
			        if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1 && table.getSelectedColumn() != 0 && table.getSelectedColumn() != 1 && table.getSelectedRow() != table.getSelectedColumn()-2) {
			        	System.out.println(table.getSelectedColumn() + " " + table.getSelectedRow());
			       
			        	Object Jugador1 = table.getModel().getValueAt(table.getSelectedColumn()-2, 1);
			        	Object Jugador2 = table.getModel().getValueAt(table.getSelectedRow(), 1);
			        	System.out.println(Jugador1 + " " + Jugador2);
			        	
			        	if(Jugador1 == null|| Jugador2 == null) return;
			        	
			        	JTextField player1Field = new JTextField(2);
			            JTextField player2Field = new JTextField(2);
			            
			            
			            JPanel myPanel = new JPanel();
			            myPanel.add(new JLabel("Sets a favor de " + Jugador1 + ":"));
			            myPanel.add(player1Field);
			            myPanel.add(Box.createHorizontalStrut(15)); // a spacer
			            myPanel.add(new JLabel("Sets a favor de " + Jugador2 + ":"));
			            myPanel.add(player2Field);

			            int result = JOptionPane.showConfirmDialog(null, myPanel, "Ingresar Resultado del partido", JOptionPane.OK_CANCEL_OPTION);
			            if (result == JOptionPane.OK_OPTION) {
			            	table.getModel().setValueAt(player1Field.getText() + "-" + player2Field.getText(), table.getSelectedRow(), table.getSelectedColumn());
			            	if(isUnderDiagonal(table.getSelectedColumn(), table.getSelectedRow())) table.getModel().setValueAt(player2Field.getText() + "-" + player1Field.getText(), table.getSelectedColumn()-2, table.getSelectedRow()+table.getSelectedColumn());
			            	
			            	System.out.println("x value: " + player1Field.getText());
			            	System.out.println("y value: " + player2Field.getText());
			            }
			        }
			    }
			});
			this.cuadros[i].setPreferredScrollableViewportSize(this.cuadros[i].getPreferredSize());
			JScrollPane cuadroIndPanel = new JScrollPane(this.cuadros[i]);
			Dimension d = this.cuadros[i].getPreferredSize();
			cuadroIndPanel.setPreferredSize(new Dimension(d.width, this.cuadros[i].getRowHeight()*5+1));
			this.CuadrosPanel.add(cuadroIndPanel);
		}
	}
	
	
	public void initComponents() {
		this.CuadrosPanel = new JPanel();
		GridLayout Layout = new GridLayout(0,2);
		Layout.setHgap(25);
		Layout.setVgap(25);
		Border blackline = BorderFactory.createTitledBorder("Cuadros");
		JPanel panel = new JPanel();
		LayoutManager layout = new FlowLayout();  
		panel.setLayout(layout); 
		panel.setBorder(blackline);
		this.CuadrosPanel.setLayout(Layout);
		panel.add(this.CuadrosPanel);
		this.add(panel);
		this.pack();
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
