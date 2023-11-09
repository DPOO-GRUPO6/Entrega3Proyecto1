package Presentacion;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.JButton;
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
	    
	    JLabel lblNombre = new JLabel("Placa del vehículo", SwingConstants.RIGHT);
	    gbcnt.insets  = new Insets(10,5,10,2);
	    gbcnt.fill = GridBagConstraints.HORIZONTAL;
	    gbcnt.gridwidth = 1;
	    lblNombre.setFont(defaultFont);
		gbcnt.gridx = 0;
	    gbcnt.gridy = 1;
	    panelCentro.add(lblNombre,gbcnt);
	    
	    JLabel lblCorreo = new JLabel("Marca del vehículo", SwingConstants.RIGHT);
	    lblCorreo.setFont(defaultFont);
	    gbcnt.gridy = 2;
	    panelCentro.add(lblCorreo,gbcnt);
	    
	    JLabel lblTelefono = new JLabel("Modelo del vehículo", SwingConstants.RIGHT);
	    lblTelefono.setFont(defaultFont);
	    gbcnt.gridy = 3;
	    panelCentro.add(lblTelefono,gbcnt);
	    
	    JLabel lblNacimiento = new JLabel("Color del vehículo", SwingConstants.RIGHT);
	    lblNacimiento.setFont(defaultFont);
	    gbcnt.gridy = 4;
	    panelCentro.add(lblNacimiento,gbcnt);
	    
	    JLabel lblNacionalidad = new JLabel("Tipo de transmisión", SwingConstants.RIGHT);
	    lblNacionalidad.setFont(defaultFont);
	    gbcnt.gridx = 2;
	    gbcnt.gridy = 1;
	    panelCentro.add(lblNacionalidad,gbcnt);
	    
	    JLabel lblNumLicencia = new JLabel("Capacidad del vehículo", SwingConstants.RIGHT);
	    lblNumLicencia.setFont(defaultFont);
	    gbcnt.gridy = 2;
	    panelCentro.add(lblNumLicencia,gbcnt);
	    
	    JLabel lblFechaExpLic = new JLabel("Sede asignada", SwingConstants.RIGHT);
	    lblFechaExpLic.setFont(defaultFont);
	    gbcnt.gridy = 3;
	    panelCentro.add(lblFechaExpLic,gbcnt);
	    
	    	    
	    /* text fields para info de registro */
	    
	    JTextField txtNombre  = new JTextField("Nombre completo");
	    txtNombre.setFont(defaultFont);
		gbcnt.gridx = 1;
	    gbcnt.gridy = 1;
	    panelCentro.add(txtNombre,gbcnt);
	    
	    JTextField txtCorreo  = new JTextField("Correo");
	    txtCorreo.setFont(defaultFont);
	    gbcnt.gridy = 2;
	    panelCentro.add(txtCorreo,gbcnt);
	   
	    JTextField txtTelefono  = new JTextField("Telefono");
	    txtTelefono.setFont(defaultFont);
	    gbcnt.gridy = 3;
	    panelCentro.add(txtTelefono,gbcnt);
	    
	    JTextField txtNacimiento  = new JTextField("Bday");
	    txtNacimiento.setFont(defaultFont);
	    gbcnt.gridy = 4;
	    panelCentro.add(txtNacimiento,gbcnt);
	    
	    
	    JTextField txtNumLicencia  = new JTextField("Licencia#");
	    txtNumLicencia.setFont(defaultFont);
	    gbcnt.gridx = 3;
	    gbcnt.gridy = 1;
	    panelCentro.add(txtNumLicencia,gbcnt);
	    
	    JTextField txtFechaExpLic  = new JTextField("fecha exp licencia");
	    txtFechaExpLic.setFont(defaultFont);
	    gbcnt.gridy = 2;
	    panelCentro.add(txtFechaExpLic,gbcnt);
	    
	    JTextField txtNacionalidad  = new JTextField("Nation");
	    txtNacionalidad.setFont(defaultFont);
	    gbcnt.gridy = 3;
	    panelCentro.add(txtNacionalidad,gbcnt);
	      
	    
	    JButton bEnviar = new JButton("Registrarse");
	    bEnviar.setFont(new Font(null, Font.BOLD,18));
	    gbcnt.gridx = 0;
	    gbcnt.gridy = 7;
	    gbcnt.gridwidth = 4;
	    gbcnt.ipady = 10;
	    gbcnt.insets  = new Insets(70,10,30,10);
	    gbcnt.fill = GridBagConstraints.HORIZONTAL;
	    panelCentro.add(bEnviar,gbcnt);
	    
	    this.add(panelCentro, BorderLayout.CENTER);
	}
}
