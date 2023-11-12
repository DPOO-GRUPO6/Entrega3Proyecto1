package Presentacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;

public class PRegistroCliente extends JPanel{
	private PMenuCliente panelMenuCliente; // a este es el que finalmente debería de estar conectado
	private MenuPrincipal panelMenuPrincipal;
	public Controlador controller;
	
	private JFormattedTextField txtTelefono;
	private JFormattedTextField txtNacimiento;
	private JFormattedTextField txtNumLicencia;
	private JFormattedTextField txtFechaExpLic;
	private JFormattedTextField txtNumTarjCred;
	private JFormattedTextField txtFechaVenTarj;
	
	PRegistroCliente(Controlador controlador){
		this.controller = controlador;
		this.setLayout(new BorderLayout());
		JLabel lblRegistrarse = new JLabel("Registarse", SwingConstants.CENTER);
		lblRegistrarse.setFont(new Font(null, Font.BOLD, 55));
		this.add(lblRegistrarse, BorderLayout.NORTH);
		
		JPanel panelCentro = new JPanel();
		GridBagLayout gbl = new GridBagLayout();
		panelCentro.setLayout(gbl);
		GridBagConstraints gbcnt = new GridBagConstraints();
		Font defaultFont = new Font(null, Font.PLAIN,15);
		
		JLabel lblInstruccion = new JLabel("Ingrese la siguiente información",SwingConstants.CENTER);
		lblInstruccion.setFont(new Font(null, Font.PLAIN,25));
		gbcnt.gridx = 0;
	    gbcnt.gridy = 0;
	    gbcnt.gridwidth = 4;
	    gbcnt.fill = GridBagConstraints.HORIZONTAL;
	    panelCentro.add(lblInstruccion,gbcnt);
	    panelCentro.add(Box.createRigidArea(new Dimension(0, 55)),gbcnt);
	    
	    gbcnt.insets  = new Insets(5,5,5,2);
	    gbcnt.gridwidth = 1;
	    JLabel lblNombre = new JLabel("Nombre completo", SwingConstants.RIGHT);
		lblInstruccion.setFont(defaultFont);
		gbcnt.gridx = 0;
	    gbcnt.gridy = 1;
	    panelCentro.add(lblNombre,gbcnt);
	    
	    JLabel lblCorreo = new JLabel("Correo electrónico", SwingConstants.RIGHT);
	    lblCorreo.setFont(defaultFont);
	    gbcnt.gridy = 2;
	    panelCentro.add(lblCorreo,gbcnt);
	    
	    JLabel lblTelefono = new JLabel("Numero de telefono", SwingConstants.RIGHT);
	    lblTelefono.setFont(defaultFont);
	    gbcnt.gridy = 3;
	    panelCentro.add(lblTelefono,gbcnt);
	    
	    JLabel lblNacimiento = new JLabel("Fecha de nacimiento", SwingConstants.RIGHT);
	    lblNacimiento.setFont(defaultFont);
	    gbcnt.gridy = 4;
	    panelCentro.add(lblNacimiento,gbcnt);
	    
	    JLabel lblNacionalidad = new JLabel("Nacionalidad", SwingConstants.RIGHT);
	    lblNacionalidad.setFont(defaultFont);
	    gbcnt.gridy = 5;
	    panelCentro.add(lblNacionalidad,gbcnt);
	    
	    
	    JLabel lblNumLicencia = new JLabel("Numero de licencia", SwingConstants.RIGHT);
	    lblNumLicencia.setFont(defaultFont);
	    gbcnt.gridy = 6;
	    panelCentro.add(lblNumLicencia,gbcnt);
	    
	    JLabel lblPaisLicencia = new JLabel("Pais de expedicion de su licencia", SwingConstants.RIGHT);
	    lblPaisLicencia.setFont(defaultFont);
	    gbcnt.gridx = 2;
	    gbcnt.gridy = 1;
	    panelCentro.add(lblPaisLicencia,gbcnt);
	    
	    JLabel lblFechaExpLic = new JLabel("Fecha de expedición de su licencia", SwingConstants.RIGHT);
	    lblFechaExpLic.setFont(defaultFont);
	    gbcnt.gridy = 2;
	    panelCentro.add(lblFechaExpLic,gbcnt);
	    
	    JLabel lblNumTarj = new JLabel("Número de su tarjeta de crédito", SwingConstants.RIGHT);
	    lblNumTarj.setFont(defaultFont);
	    gbcnt.gridy = 3;
	    panelCentro.add(lblNumTarj,gbcnt);
	    
	    JLabel lblFechaVenTarj = new JLabel("Fecha de vencimiento de su tarjeta", SwingConstants.RIGHT);
	    lblFechaVenTarj.setFont(defaultFont);
	    gbcnt.gridy = 4;
	    panelCentro.add(lblFechaVenTarj,gbcnt);
	    
	    JLabel lblNameUsuario = new JLabel("Cree un nombre de usuario", SwingConstants.RIGHT);
	    lblNameUsuario.setFont(defaultFont);
	    gbcnt.gridy = 5;
	    panelCentro.add(lblNameUsuario,gbcnt);
	    
	    JLabel lblPwdUsuario = new JLabel("Cree una contraseña", SwingConstants.RIGHT);
	    lblPwdUsuario.setFont(defaultFont);
	    gbcnt.gridy = 6;
	    panelCentro.add(lblPwdUsuario,gbcnt);
	    
	    
	    /** 
	     * text fields para info de registro de un nuevo cliente
	     * */
	    
	    JTextField txtNombre  = new JTextField("");
	    txtNombre.setFont(defaultFont);
		gbcnt.gridx = 1;
	    gbcnt.gridy = 1;
	    panelCentro.add(txtNombre,gbcnt);
	    
	    JTextField txtCorreo  = new JTextField("");
	    txtCorreo.setFont(defaultFont);
	    gbcnt.gridy = 2;
	    panelCentro.add(txtCorreo,gbcnt);
	   
	    
	    
	    try {
			MaskFormatter formatNumero = new MaskFormatter("##########");
			this.txtTelefono  = new JFormattedTextField(formatNumero);
			this.txtTelefono.setFont(defaultFont);
			    gbcnt.gridy = 3;
			    panelCentro.add(txtTelefono,gbcnt);
		} catch (ParseException e) {
			//e.printStackTrace();
			mostrarError();
		}

	    try {
	    	MaskFormatter formatFecha = new MaskFormatter("##/##/####");
	    	this.txtNacimiento  = new JFormattedTextField(formatFecha);
	    	this.txtNacimiento.setFont(defaultFont);
	    	gbcnt.gridy = 4;
	    	panelCentro.add(txtNacimiento,gbcnt);
	    } catch (ParseException e) {
	    	//e.printStackTrace();
	    	mostrarError();
	    }
	    
	    
	    JTextField txtNacionalidad  = new JTextField("");
	    txtNacionalidad.setFont(defaultFont);
	    gbcnt.gridy = 5;
	    panelCentro.add(txtNacionalidad,gbcnt);
	    
	    
	    try {
			MaskFormatter formatNumLicencia = new MaskFormatter("#########");
			this.txtNumLicencia  = new JFormattedTextField(formatNumLicencia);
			this.txtNumLicencia.setFont(defaultFont);
			    gbcnt.gridy = 6;
			    panelCentro.add(txtNumLicencia,gbcnt);
		} catch (ParseException e) {
			//e.printStackTrace();
			mostrarError();
		}
	    
	    JTextField txtPaisLicencia  = new JTextField("");
	    txtPaisLicencia.setFont(defaultFont);
	    gbcnt.gridy = 1;
	    gbcnt.gridx = 3;
	    panelCentro.add(txtPaisLicencia,gbcnt);
	    
	    try {
			MaskFormatter formatFecha = new MaskFormatter("##/##/####");
			this.txtFechaExpLic  = new JFormattedTextField(formatFecha);
			this.txtFechaExpLic.setFont(defaultFont);
			 gbcnt.gridy = 2;
			    panelCentro.add(txtFechaExpLic,gbcnt);
		} catch (ParseException e) {
			//e.printStackTrace();
			mostrarError();
		}
	    
	    
	    try {
			MaskFormatter formatNumTarjCred = new MaskFormatter("#########");
			this.txtNumTarjCred  = new JFormattedTextField(formatNumTarjCred);
			txtNumTarjCred.setFont(defaultFont);
			 txtNumTarjCred.setFont(defaultFont);
			   gbcnt.gridy = 3;
			   panelCentro.add(txtNumTarjCred,gbcnt);
		} catch (ParseException e) {
			//e.printStackTrace();
			mostrarError();
		}
	    
	    
	    try {
			MaskFormatter formatFecha = new MaskFormatter("##/##/####");
			this.txtFechaVenTarj  = new JFormattedTextField(formatFecha);
			txtFechaVenTarj.setFont(defaultFont);
			gbcnt.gridy = 4;
		    panelCentro.add(txtFechaVenTarj,gbcnt);
		} catch (ParseException e) {
			//e.printStackTrace();
			mostrarError();
		}
	    
	    JTextField txtNameUsuario  = new JTextField("");
	    txtNameUsuario.setFont(defaultFont);
	    gbcnt.gridy = 5;
	    panelCentro.add(txtNameUsuario,gbcnt);
	    
	    JTextField txtPwdUsuario  = new JTextField("");
	    txtPwdUsuario.setFont(defaultFont);
	    gbcnt.gridy = 6;
	    panelCentro.add(txtPwdUsuario,gbcnt);
	    
	    JButton bEnviar = new JButton("Registrarse");
	    bEnviar.setFont(new Font(null, Font.BOLD,18));
	    gbcnt.gridx = 0;
	    gbcnt.gridy = 7;
	    gbcnt.gridwidth = 4;
	    gbcnt.ipady = 10;
	    gbcnt.insets  = new Insets(30,10,30,10);
	    gbcnt.fill = GridBagConstraints.HORIZONTAL;
	    panelCentro.add(bEnviar,gbcnt);
	    bEnviar.addActionListener(new ActionListener() {

	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	    		String nombre = txtNombre.getText();
	    		String email = txtCorreo.getText();
	    		String telefono = txtTelefono.getText();
	    		String fechaNacimiento = txtNacimiento.getText();
	    		String nacionalidad = txtNacionalidad.getText();
	    		String numeroLicencia = txtNumLicencia.getText();
	    		String paisLicencia = txtPaisLicencia.getText();
	    		String fechaExpLicencia = txtFechaExpLic.getText();
	    		String numeroTC = txtNumTarjCred.getText();
	    		String fechaExpTarjCred = txtFechaVenTarj.getText();
	    		String logIn = txtNameUsuario.getText();
	    		String contrasenia = txtPwdUsuario.getText();
	    		if(nombre != null && email!= null && telefono!= null && fechaNacimiento!= null && nacionalidad!= null 
	    				&& numeroLicencia!= null && paisLicencia!= null && fechaExpLicencia!= null && numeroTC!= null
	    				&& fechaExpTarjCred!= null && logIn!= null && contrasenia!= null) {
	    			if(guardarRegistroNewCliente(nombre,email,telefono,fechaNacimiento,nacionalidad,numeroLicencia,paisLicencia,fechaExpLicencia,numeroTC,fechaExpTarjCred,logIn,contrasenia)) {
	    				iniciarMenuCliente();
	    			}
	    		}
	    		else {
	    			mostrarError();
	    		}
	    	}

	    });
	    
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


	protected boolean guardarRegistroNewCliente(String nombre, String email, String telefono, String fechaNacimiento,
			String nacionalidad, String numeroLicencia, String paisLicencia, String fechaExpLicencia, String numeroTC,
			String fechaExpTarjCred, String logIn, String contrasenia) {
		String result = this.controller.crearNuevoCliente(nombre, email, telefono, fechaNacimiento, nacionalidad, numeroLicencia, paisLicencia, fechaExpLicencia, numeroTC, fechaExpTarjCred, logIn, contrasenia);
		if(result == "error") {
			JOptionPane.showMessageDialog(this,"No se pudo registrar el nuevo usuario","Alert",JOptionPane.WARNING_MESSAGE);
			return false;
		}
		else {
			JOptionPane.showMessageDialog(this,"El cliente con el nombre "+result+" ha sido creado con éxito");
			return true;
		}
	
	}

	protected void iniciarMenuCliente() {
		this.removeAll();
		this.panelMenuCliente = new PMenuCliente(this.controller);
		this.add(this.panelMenuCliente);
		this.revalidate();
		this.repaint();
		this.panelMenuCliente.setVisible(true);
		
	}
	
	protected void volverAPanelAnterior() {
		
		this.removeAll();
		this.panelMenuPrincipal = new MenuPrincipal(this.controller);
		this.add(this.panelMenuPrincipal);
		this.revalidate();
		this.repaint();
		this.panelMenuPrincipal.setVisible(true);
		

	}
	
	protected void mostrarError() {
		JOptionPane.showMessageDialog(this,"Error. Asegúrese de llenar toda la información","Alert",JOptionPane.WARNING_MESSAGE);
	}

}
