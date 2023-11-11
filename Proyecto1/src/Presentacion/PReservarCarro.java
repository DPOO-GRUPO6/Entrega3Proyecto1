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

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class PReservarCarro extends JPanel{
	
	PReservarCarro(){
		this.setLayout(new BorderLayout());
		JLabel lblTitulo = new JLabel("Reservar vehículo", SwingConstants.CENTER);
		lblTitulo.setFont(new Font(null, Font.BOLD, 45));
		this.add(lblTitulo, BorderLayout.NORTH);
		
		JPanel panelCentro = new JPanel();
		GridBagLayout gbl = new GridBagLayout();
		panelCentro.setLayout(gbl);
		GridBagConstraints gbcnt = new GridBagConstraints();
		Font defaultFont = new Font(null, Font.PLAIN,15);
		
		JLabel lblInstruccion = new JLabel("Complete la siguiente información",SwingConstants.CENTER);
		lblInstruccion.setFont(new Font(null, Font.PLAIN,15));
		gbcnt.gridx = 0;
	    gbcnt.gridy = 0;
	    gbcnt.gridwidth = 5;
	    gbcnt.fill = GridBagConstraints.HORIZONTAL;
	    gbcnt.insets  = new Insets(0,0,50,0);
	    panelCentro.add(lblInstruccion,gbcnt);
	    
	    JLabel lblCategoria = new JLabel("Seleccione la categoría", SwingConstants.RIGHT);
	    gbcnt.insets  = new Insets(10,5,10,2);
	    gbcnt.fill = GridBagConstraints.HORIZONTAL;
	    gbcnt.gridwidth = 1;
	    lblCategoria.setFont(defaultFont);
		gbcnt.gridx = 0;
	    gbcnt.gridy = 1;
	    panelCentro.add(lblCategoria,gbcnt);
	    
	    JLabel lblFechaInicio = new JLabel("Fecha de recogida", SwingConstants.RIGHT);
	    lblFechaInicio.setFont(defaultFont);
	    gbcnt.gridy = 2;
	    panelCentro.add(lblFechaInicio,gbcnt);
	    
	    JLabel lblHoraInicio = new JLabel("Hora de recogida", SwingConstants.RIGHT);
	    lblHoraInicio.setFont(defaultFont);
	    gbcnt.gridy = 3;
	    panelCentro.add(lblHoraInicio,gbcnt);
	    
	    JLabel lblSedeRecogida = new JLabel("Sede de recogida", SwingConstants.RIGHT);
	    lblSedeRecogida.setFont(defaultFont);
	    gbcnt.gridy = 4;
	    panelCentro.add(lblSedeRecogida,gbcnt);
	    
	    JLabel lblFechaFin = new JLabel("Fecha llegada", SwingConstants.RIGHT);
	    lblFechaFin.setFont(defaultFont);
	    gbcnt.gridx = 2;
	    gbcnt.gridy = 2;
	    panelCentro.add(lblFechaFin,gbcnt);
	    
	    JLabel lblHoraFin = new JLabel("Hora de llegada", SwingConstants.RIGHT);
	    lblHoraFin.setFont(defaultFont);
	    gbcnt.gridx = 2;
	    gbcnt.gridy = 3;
	    panelCentro.add(lblHoraFin,gbcnt);
	    
	    JLabel lblSedeLlegada = new JLabel("Sede de llegada", SwingConstants.RIGHT);
	    lblSedeLlegada.setFont(defaultFont);
	    gbcnt.gridy = 4;
	    panelCentro.add(lblSedeLlegada,gbcnt);
	    
	    	    
	    /* text fields para info de registro */
	    gbcnt.insets  = new Insets(0,8,0,35);
	    JTextField txtCategoria  = new JTextField("categoria");
	    txtCategoria.setFont(defaultFont);
		gbcnt.gridx = 1;
	    gbcnt.gridy = 1;
	    gbcnt.gridwidth = 3;
	    panelCentro.add(txtCategoria,gbcnt);
	    
	    JTextField txtFechaInic  = new JTextField("fecha rec");
	    txtFechaInic.setFont(defaultFont);
	    gbcnt.gridy = 2;
	    gbcnt.gridwidth = 1;
	    gbcnt.ipadx = 45;
	    panelCentro.add(txtFechaInic,gbcnt);
	   
	    JTextField txtHoraInic  = new JTextField("Hora rec");
	    txtHoraInic.setFont(defaultFont);
	    gbcnt.gridy = 3;
	    panelCentro.add(txtHoraInic,gbcnt);
	    
	    JTextField txtSedeInic  = new JTextField("sede red");
	    txtSedeInic.setFont(defaultFont);
	    gbcnt.gridy = 4;
	    panelCentro.add(txtSedeInic,gbcnt);
	    
	    
	    String categorias[]= {"cat 1", "cat2"};
	    JComboBox CBcategoria = new JComboBox(categorias);
	    CBcategoria.setBackground(Color.white);
	    gbcnt.gridx = 3;
	    gbcnt.gridy = 2;
	    panelCentro.add(CBcategoria,gbcnt);
	    
	    JTextField txtHoraFin  = new JTextField("hora llegada");
	    txtHoraFin.setFont(defaultFont);
	    gbcnt.gridy = 3;
	    panelCentro.add(txtHoraFin,gbcnt);
	    
	    String sedes[]= {"sede1", "sede2"};
	    JComboBox CBsedes = new JComboBox(sedes);
	    CBsedes.setBackground(Color.white);
	    gbcnt.gridy = 4;
	    panelCentro.add(CBsedes,gbcnt);
	      
	    
	    JButton bEnviar = new JButton("Reservar vehiculo");
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
		PMenuCliente panelAnterior = new PMenuCliente();
		this.removeAll();
		this.add(panelAnterior);
		this.revalidate();
		this.repaint();
		panelAnterior.setVisible(true);
	}
}
