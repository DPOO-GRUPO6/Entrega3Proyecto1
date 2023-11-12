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

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class PAlquilarConReserva extends JPanel{
	
	PAlquilarConReserva(){
		this.setLayout(new BorderLayout());
		JLabel lblTitulo = new JLabel("Finalizar alquiler", SwingConstants.CENTER);
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
	    gbcnt.gridwidth = 2;
	    gbcnt.fill = GridBagConstraints.HORIZONTAL;
	    gbcnt.insets  = new Insets(0,5,20,5);
	    panelCentro.add(lblInstruccion,gbcnt);
	    
	    JLabel lblSeguro = new JLabel("Seleccione un seguro (opcional)", SwingConstants.RIGHT);
	    lblSeguro.setFont(defaultFont);
	    gbcnt.gridy = 1;
	    gbcnt.gridx = 0;
	    panelCentro.add(lblSeguro, gbcnt);
	    
	    String seguros[]= {"seg1", "seg2"};
	    JComboBox CBseguro = new JComboBox(seguros);
	    CBseguro.setBackground(Color.white);
	    gbcnt.gridy = 1;
	    gbcnt.gridx = 2;
	    gbcnt.gridwidth = 2;
	    panelCentro.add(CBseguro,gbcnt);
	    gbcnt.insets = new Insets(0,0,10,0);
	    
	    JPanel PregistroConductorExtra = new JPanel();
	    PregistroConductorExtra.setLayout(new GridBagLayout());
	    GridBagConstraints gbc = new GridBagConstraints();
	    PregistroConductorExtra.setBorder(BorderFactory.createTitledBorder("Registro de conductores adicionales- Complete la siguiente información (opcional)"));
	    
	    JLabel lblNumeroLicenciaExtra = new JLabel("Número de licencia",SwingConstants.RIGHT);
	    lblNumeroLicenciaExtra.setFont(new Font(null, Font.PLAIN, 13));
	    gbc.gridy= 1;
	    gbc.fill = GridBagConstraints.HORIZONTAL;
	    gbc.insets = new Insets(5,5,5,5);
	    PregistroConductorExtra.add(lblNumeroLicenciaExtra, gbc);
	    
	    JLabel lblPaisExpExtra = new JLabel("Pais de expedicion de la licencia",SwingConstants.RIGHT);
	    gbc.gridy= 2;
	    lblPaisExpExtra.setFont(new Font(null, Font.PLAIN, 13));
	    PregistroConductorExtra.add(lblPaisExpExtra, gbc);
	    
	    JLabel lblFechaExpExtra = new JLabel("Fecha de expedición de la licencia",SwingConstants.RIGHT);
	    gbc.gridy= 3;
	    lblFechaExpExtra.setFont(new Font(null, Font.PLAIN, 13));
	    PregistroConductorExtra.add(lblFechaExpExtra, gbc);
	    
	    JTextField txtNumeroLicenciaExtra = new JTextField("Número de licencia");
	    gbc.gridx = 1;
	    gbc.gridy= 1;
	    PregistroConductorExtra.add(txtNumeroLicenciaExtra, gbc);
	    
	    JTextField txtPaisExpExtra = new JTextField("Pais de expedicion de la licencia");
	    gbc.gridy= 2;
	    PregistroConductorExtra.add(txtPaisExpExtra, gbc);
	    
	    JTextField txtFechaExpExtra = new JTextField("Fecha de expedición de la licencia");
	    gbc.gridy= 3;
	    PregistroConductorExtra.add(txtFechaExpExtra, gbc);
	    
	    JButton bAgregarCondExtra = new JButton("Agregar conductor extra");
	    gbc.gridy= 0;
	    gbc.gridx = 3;
	    gbc.gridheight = 4;
	    gbc.fill = GridBagConstraints.VERTICAL;
	    PregistroConductorExtra.add(bAgregarCondExtra, gbc);
	    
	    gbcnt.gridy = 6;
	    gbcnt.gridx = 0;
	    gbcnt.gridwidth = 4;
	    gbcnt.insets = new Insets(15,0,0,0);
	    panelCentro.add(PregistroConductorExtra,gbcnt);
	    
	    JButton bEnviar = new JButton("Alquilar vehiculo");
	    bEnviar.setFont(new Font(null, Font.BOLD,18));
	    gbcnt.gridx = 0;
	    gbcnt.gridy = 7;
	    gbcnt.gridwidth = 4;
	    gbcnt.ipady = 10;
	    gbcnt.insets  = new Insets(20,10,10,10);
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
		/*
		PMenuCliente panelAnterior = new PMenuCliente();
		this.removeAll();
		this.add(panelAnterior);
		this.revalidate();
		this.repaint();
		panelAnterior.setVisible(true);
		*/
	}
}
