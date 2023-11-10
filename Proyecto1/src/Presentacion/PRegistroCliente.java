package Presentacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class PRegistroCliente extends JPanel{
	private PMenuCliente panelMenuCliente; // a este es el que finalmente debería de estar conectado
	private PMenuAdminGeneral panelMenuAdmingGen; //terminado
	private PMenuEmpleado panelMenuEmpleado; //terminado
	private PRegistroCarro panelRegistroCarro;
	
	PRegistroCliente(){
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
	    gbcnt.gridwidth = 5;
	    gbcnt.fill = GridBagConstraints.HORIZONTAL;
	    panelCentro.add(lblInstruccion,gbcnt);
	    panelCentro.add(Box.createRigidArea(new Dimension(0, 55)),gbcnt);
	    
	    gbcnt.insets  = new Insets(10,5,10,2);
	    gbcnt.fill = GridBagConstraints.HORIZONTAL;
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
	    
	    gbcnt.gridx = 2;
	    
	    JLabel lblNumLicencia = new JLabel("Numero de licencia", SwingConstants.RIGHT);
	    lblNumLicencia.setFont(defaultFont);
	    gbcnt.gridx = 3;
	    gbcnt.gridy = 1;
	    panelCentro.add(lblNumLicencia,gbcnt);
	    
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
	    
	    JTextField txtNacionalidad  = new JTextField("Nation");
	    txtNacionalidad.setFont(defaultFont);
	    gbcnt.gridy = 5;
	    panelCentro.add(txtNacionalidad,gbcnt);
	    
	    JTextField txtNumLicencia  = new JTextField("Licencia#");
	    txtNumLicencia.setFont(defaultFont);
	    gbcnt.gridx = 4;
	    gbcnt.gridy = 1;
	    panelCentro.add(txtNumLicencia,gbcnt);
	    
	    JTextField txtFechaExpLic  = new JTextField("fecha exp licencia");
	    txtFechaExpLic.setFont(defaultFont);
	    gbcnt.gridy = 2;
	    panelCentro.add(txtFechaExpLic,gbcnt);
	    
	    JTextField txtNumTarjCred  = new JTextField("# tarjeta credito");
	    txtNumTarjCred.setFont(defaultFont);
	    gbcnt.gridy = 3;
	    panelCentro.add(txtNumTarjCred,gbcnt);
	    
	    JTextField txtFechaVenTarj  = new JTextField("fecha ven tarjeta credito");
	    txtFechaVenTarj.setFont(defaultFont);
	    gbcnt.gridy = 4;
	    panelCentro.add(txtFechaVenTarj,gbcnt);
	    
	    JTextField txtNameUsuario  = new JTextField("nombre usuario sel");
	    txtNameUsuario.setFont(defaultFont);
	    gbcnt.gridy = 5;
	    panelCentro.add(txtNameUsuario,gbcnt);
	    
	    JTextField txtPwdUsuario  = new JTextField("contra");
	    txtPwdUsuario.setFont(defaultFont);
	    gbcnt.gridy = 6;
	    panelCentro.add(txtPwdUsuario,gbcnt);
	    
	    JButton bEnviar = new JButton("Registrarse");
	    bEnviar.setFont(new Font(null, Font.BOLD,18));
	    gbcnt.gridx = 1;
	    gbcnt.gridy = 7;
	    gbcnt.gridwidth = 3;
	    gbcnt.ipady = 10;
	    gbcnt.insets  = new Insets(70,10,30,10);
	    gbcnt.fill = GridBagConstraints.HORIZONTAL;
	    panelCentro.add(bEnviar,gbcnt);
	    bEnviar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				iniciarMenuCliente();
				
			}
	    	
	    });
	    
	    
	    this.add(panelCentro, BorderLayout.CENTER);
	    
	}
//metodo para verificar la vista de las interfaces gráficas mientras tanto, porque falta la conexion con el controlador
	protected void iniciarMenuCliente() {
		this.removeAll();
		this.panelMenuEmpleado = new PMenuEmpleado();
		this.add(this.panelMenuEmpleado);
		this.revalidate();
		this.repaint();
		this.panelMenuEmpleado.setVisible(true);
		
	}
}
