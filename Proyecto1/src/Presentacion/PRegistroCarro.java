package Presentacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class PRegistroCarro extends JPanel{
	PRegistroCarro(){
		this.setLayout(new BorderLayout());
		JLabel lblRegistrarse = new JLabel("Registrar nuevo vehículo", SwingConstants.CENTER);
		lblRegistrarse.setFont(new Font(null, Font.BOLD, 45));
		this.add(lblRegistrarse, BorderLayout.NORTH);
		
		JPanel panelCentro = new JPanel();
		GridBagLayout gbl = new GridBagLayout();
		panelCentro.setLayout(gbl);
		GridBagConstraints gbcnt = new GridBagConstraints();
		Font defaultFont = new Font(null, Font.PLAIN,15);
		
		JLabel lblInstruccion = new JLabel("Ingrese la siguiente información",SwingConstants.CENTER);
		lblInstruccion.setFont(new Font(null, Font.PLAIN,15));
		gbcnt.gridx = 0;
	    gbcnt.gridy = 0;
	    gbcnt.gridwidth = 5;
	    gbcnt.fill = GridBagConstraints.HORIZONTAL;
	    gbcnt.insets  = new Insets(0,0,50,0);
	    panelCentro.add(lblInstruccion,gbcnt);
	    
	    JLabel lblPlaca = new JLabel("Placa del vehículo", SwingConstants.RIGHT);
	    gbcnt.insets  = new Insets(10,5,10,2);
	    gbcnt.fill = GridBagConstraints.HORIZONTAL;
	    gbcnt.gridwidth = 1;
	    lblPlaca.setFont(defaultFont);
		gbcnt.gridx = 0;
	    gbcnt.gridy = 1;
	    panelCentro.add(lblPlaca,gbcnt);
	    
	    JLabel lblMarca = new JLabel("Marca del vehículo", SwingConstants.RIGHT);
	    lblMarca.setFont(defaultFont);
	    gbcnt.gridy = 2;
	    panelCentro.add(lblMarca,gbcnt);
	    
	    JLabel lblModelo = new JLabel("Modelo del vehículo", SwingConstants.RIGHT);
	    lblModelo.setFont(defaultFont);
	    gbcnt.gridy = 3;
	    panelCentro.add(lblModelo,gbcnt);
	    
	    JLabel lblColor = new JLabel("Color del vehículo", SwingConstants.RIGHT);
	    lblColor.setFont(defaultFont);
	    gbcnt.gridy = 4;
	    panelCentro.add(lblColor,gbcnt);
	    
	    JLabel lblCategoria = new JLabel("Categoría del vehículo", SwingConstants.RIGHT);
	    lblCategoria.setFont(defaultFont);
	    gbcnt.gridx = 2;
	    gbcnt.gridy = 1;
	    panelCentro.add(lblCategoria,gbcnt);
	    
	    JLabel lblTransmision = new JLabel("Tipo de transmisión", SwingConstants.RIGHT);
	    lblTransmision.setFont(defaultFont);
	    gbcnt.gridx = 2;
	    gbcnt.gridy = 2;
	    panelCentro.add(lblTransmision,gbcnt);
	    
	    JLabel lblCapacidad = new JLabel("Capacidad del vehículo", SwingConstants.RIGHT);
	    lblCapacidad.setFont(defaultFont);
	    gbcnt.gridy = 3;
	    panelCentro.add(lblCapacidad,gbcnt);
	    
	    JLabel lblSede = new JLabel("Sede asignada", SwingConstants.RIGHT);
	    lblSede.setFont(defaultFont);
	    gbcnt.gridy = 4;
	    panelCentro.add(lblSede,gbcnt);
	    
	    	    
	    /* text fields para info de registro */
	    gbcnt.ipadx = 45;
	    JTextField txtPlaca  = new JTextField("AAA-000");
	    txtPlaca.setFont(defaultFont);
		gbcnt.gridx = 1;
	    gbcnt.gridy = 1;
	    panelCentro.add(txtPlaca,gbcnt);
	    
	    JTextField txtMarca  = new JTextField("Marca");
	    txtMarca.setFont(defaultFont);
	    gbcnt.gridy = 2;
	    panelCentro.add(txtMarca,gbcnt);
	   
	    JTextField txtModelo  = new JTextField("modelo");
	    txtModelo.setFont(defaultFont);
	    gbcnt.gridy = 3;
	    panelCentro.add(txtModelo,gbcnt);
	    
	    JTextField txtmodelo  = new JTextField("Color");
	    txtmodelo.setFont(defaultFont);
	    gbcnt.gridy = 4;
	    panelCentro.add(txtmodelo,gbcnt);
	    
	    
	    String categorias[]= {"cat 1", "cat2"};
	    JComboBox CBcategoria = new JComboBox(categorias);
	    CBcategoria.setBackground(Color.white);
	    gbcnt.gridx = 3;
	    gbcnt.gridy = 1;
	    panelCentro.add(CBcategoria,gbcnt);
	    
	    JTextField txtTipoTransmision  = new JTextField("tipo trans");
	    txtTipoTransmision.setFont(defaultFont);
	    gbcnt.gridy = 2;
	    panelCentro.add(txtTipoTransmision,gbcnt);
	    
	    JTextField txtcapacidad  = new JTextField("Capacidad");
	    txtcapacidad.setFont(defaultFont);
	    gbcnt.gridy = 3;
	    panelCentro.add(txtcapacidad,gbcnt);
	    
	    String sedes[]= {"sede1", "sede2"};
	    JComboBox CBsedes = new JComboBox(sedes);
	    CBsedes.setBackground(Color.white);
	    gbcnt.gridy = 4;
	    panelCentro.add(CBsedes,gbcnt);
	      
	    
	    JButton bEnviar = new JButton("Registrar vehiculo");
	    bEnviar.setFont(new Font(null, Font.BOLD,18));
	    gbcnt.gridx = 0;
	    gbcnt.gridy = 7;
	    gbcnt.gridwidth = 4;
	    gbcnt.ipady = 10;
	    gbcnt.insets  = new Insets(50,10,10,10);
	    gbcnt.fill = GridBagConstraints.HORIZONTAL;
	    panelCentro.add(bEnviar,gbcnt);
	    
	    this.add(panelCentro, BorderLayout.CENTER);
	    
	    JPanel panelSur = new JPanel();
	     FlowLayout layoutPsur = new FlowLayout();
	     layoutPsur.setAlignment(FlowLayout.LEFT);
	     panelSur.setLayout(layoutPsur);
	     JButton bVolver = new JButton("Volver");
	     bVolver.setFont(new Font(null, Font.PLAIN, 25));
	     
	     bVolver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				volverAPanelAnterior();
			}
	    	 
	     });
	     
	     panelSur.add(bVolver);
	     this.add(panelSur, BorderLayout.SOUTH);
	    
	}

	protected void volverAPanelAnterior() {
		PMenuAdminGeneral panelAnterior = new PMenuAdminGeneral();
		this.removeAll();
		this.add(panelAnterior);
		this.revalidate();
		this.repaint();
		panelAnterior.setVisible(true);
	}
}
