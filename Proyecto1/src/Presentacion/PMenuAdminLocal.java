package Presentacion;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class PMenuAdminLocal extends JPanel {
	public Controlador controller;
	
	PMenuAdminLocal(Controlador controlador) {
		this.setLayout(new BorderLayout());
		this.controller = controlador;
		JLabel lblBienvenido = new JLabel("Bienvenido", SwingConstants.CENTER);
		lblBienvenido.setFont(new Font(null, Font.BOLD, 45));
		this.add(lblBienvenido, BorderLayout.NORTH);

		JPanel panelCentro = new JPanel();
		panelCentro.setLayout(new GridBagLayout());
		GridBagConstraints gbcnt = new GridBagConstraints();
		Font defaultFont = new Font(null, Font.PLAIN, 25);

		gbcnt.insets = new Insets(0, 0, 40, 0);
		gbcnt.gridx = 0;
		gbcnt.gridy = 0;
		JLabel lblinstrucc = new JLabel("Qué desea hacer?", SwingConstants.RIGHT);
		lblinstrucc.setFont(new Font(null, Font.PLAIN, 20));
		panelCentro.add(lblinstrucc, gbcnt);

		gbcnt.insets = new Insets(10, 0, 30, 0);
		gbcnt.gridy = 1;
		gbcnt.ipady = 25;
		gbcnt.fill = GridBagConstraints.HORIZONTAL;
		JButton bCrearEmpleado = new JButton("Crear un nuevo empleado");
		bCrearEmpleado.setFont(defaultFont);
		panelCentro.add(bCrearEmpleado, gbcnt);
		bCrearEmpleado.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				goPanelCrearEmpleado();
			}

		});

		gbcnt.gridy = 2;
		JButton bCambiarInfoEmplado = new JButton("Cambiar la información de algún empleado");
		bCambiarInfoEmplado.setFont(defaultFont);
		panelCentro.add(bCambiarInfoEmplado, gbcnt);
		bCambiarInfoEmplado.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				goPanelCambiarInfoEmpleado();
			}
			
		});

		this.add(panelCentro, BorderLayout.CENTER);
		
		JPanel panelSur = new JPanel();
	     FlowLayout layoutPsur = new FlowLayout();
	     layoutPsur.setAlignment(FlowLayout.LEFT);
	     panelSur.setLayout(layoutPsur);
	     JButton bVolver = new JButton("Cerrar sesión");
	     bVolver.setFont(new Font(null, Font.PLAIN, 25));
	     
	     bVolver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cerrarSesion();
			}
	    	 
	     });
	     
	     panelSur.add(bVolver);
	     this.add(panelSur, BorderLayout.SOUTH);
	}

	protected void cerrarSesion() {
		/*
		MenuPrincipal panelAnterior = new MenuPrincipal();
		this.removeAll();
		this.add(panelAnterior);
		this.revalidate();
		this.repaint();
		panelAnterior.setVisible(true);
		*/
		
	}

	protected void goPanelCambiarInfoEmpleado() {
		this.removeAll();
		this.setLayout(new BorderLayout());
		JLabel lblTitulo = new JLabel("Cambiar información de empleado", SwingConstants.CENTER);
		lblTitulo.setFont(new Font(null, Font.BOLD, 30));
		this.add(lblTitulo, BorderLayout.NORTH);
		
		JPanel panelCentro = new JPanel();
		GridBagLayout gbl = new GridBagLayout();
		panelCentro.setLayout(gbl);
		GridBagConstraints gbcnt = new GridBagConstraints();
		Font defaultFont = new Font(null, Font.PLAIN,17);
		
		JLabel lblInstruccion = new JLabel("Ingrese el nombre completo del empleado",SwingConstants.CENTER);
		lblInstruccion.setFont(new Font(null, Font.PLAIN,20));
		gbcnt.gridx = 0;
	    gbcnt.gridy = 0;
	    gbcnt.fill = GridBagConstraints.HORIZONTAL;
	    gbcnt.insets  = new Insets(0,0,25,0);
	    panelCentro.add(lblInstruccion,gbcnt);
	    
	    JTextField txtNombre = new JTextField("Nombre Completo", SwingConstants.RIGHT);
	    gbcnt.insets  = new Insets(10,8,10,8);
	    txtNombre.setFont(defaultFont);
	    gbcnt.gridy = 1;
	    gbcnt.ipady = 10;
	    panelCentro.add(txtNombre,gbcnt);
	    
	    JButton bModificarLogIn = new JButton("Modificar logIn");
	    bModificarLogIn.setFont(defaultFont);
	    gbcnt.gridy = 2;
	    panelCentro.add(bModificarLogIn,gbcnt);
	    bModificarLogIn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String nuevoLogIn = JOptionPane.showInputDialog(panelCentro, "Ingrese el nuevo logIn del empleado", "");
				String nombreEmpleado = txtNombre.getText();
				
				if(modificarEmpleado(nombreEmpleado, nuevoLogIn, null, null, true, false, false))
				{
					String mensaje = "El nuevo LogIn del empleado " + nombreEmpleado + " es " + nuevoLogIn;
					JOptionPane.showMessageDialog(bModificarLogIn, mensaje);
				}
				else
				{
					String mensaje = "Ha habido algun error ingrese la informacion nuevamente";
					JOptionPane.showMessageDialog(bModificarLogIn, mensaje);
				}
			
			}
	    });
	    
	    JButton bModificarPwd = new JButton("Modificar contraseña");
	    bModificarPwd.setFont(defaultFont);
	    gbcnt.gridy = 3;
	    panelCentro.add(bModificarPwd,gbcnt);
	    bModificarPwd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String nuevaPwd = JOptionPane.showInputDialog(panelCentro, "Ingrese la nueva contraseña del empleado", "");
				String nombreEmpleado = txtNombre.getText();
				
				if(modificarEmpleado(nombreEmpleado, null, nuevaPwd, null, false, true, false))
				{
					String mensaje = "La nueva contraseña del empleado " + nombreEmpleado + " es " + nuevaPwd;
					JOptionPane.showMessageDialog(bModificarPwd, mensaje);
				}
				else
				{
					String mensaje = "Ha habido algun error ingrese la informacion nuevamente";
					JOptionPane.showMessageDialog(bModificarPwd, mensaje);
				}

			}
	    });
	    
	    JButton bModificarSede = new JButton("Modificar sede");
	    bModificarSede.setFont(defaultFont);
	    gbcnt.gridy = 4;
	    panelCentro.add(bModificarSede,gbcnt);
	    bModificarSede.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String nuevaSede = JOptionPane.showInputDialog(panelCentro, "Ingrese la nueva sede del empleado", "");
				String nombreEmpleado = txtNombre.getText();
				
				if(modificarEmpleado(nombreEmpleado, null, null, nuevaSede, false, false, true))
				{
					String mensaje = "La nueva sede del empleado " + nombreEmpleado + " es " + nuevaSede;
					JOptionPane.showMessageDialog(bModificarSede, mensaje);
				}
				else
				{
					String mensaje = "Ha habido algun error ingrese la informacion nuevamente";
					JOptionPane.showMessageDialog(bModificarSede, mensaje);
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
		this.revalidate();
		this.repaint();
	}

	protected void goPanelCrearEmpleado() {
		this.removeAll();
		this.setLayout(new BorderLayout());
		JLabel lblTitulo = new JLabel("Crear nuevo empleado", SwingConstants.CENTER);
		lblTitulo.setFont(new Font(null, Font.BOLD, 45));
		this.add(lblTitulo, BorderLayout.NORTH);
		
		JPanel panelCentro = new JPanel();
		GridBagLayout gbl = new GridBagLayout();
		panelCentro.setLayout(gbl);
		GridBagConstraints gbcnt = new GridBagConstraints();
		Font defaultFont = new Font(null, Font.PLAIN,17);
		
		JLabel lblInstruccion = new JLabel("Ingrese la siguiente información",SwingConstants.CENTER);
		lblInstruccion.setFont(new Font(null, Font.PLAIN,15));
		gbcnt.gridx = 0;
	    gbcnt.gridy = 0;
	    gbcnt.gridwidth = 5;
	    gbcnt.fill = GridBagConstraints.HORIZONTAL;
	    gbcnt.insets  = new Insets(0,0,50,0);
	    panelCentro.add(lblInstruccion,gbcnt);
	    
	    JLabel lblLogIn = new JLabel("LogIn", SwingConstants.RIGHT);
	    gbcnt.insets  = new Insets(10,8,10,8);
	    gbcnt.fill = GridBagConstraints.HORIZONTAL;
	    gbcnt.gridwidth = 1;
	    lblLogIn.setFont(defaultFont);
		gbcnt.gridx = 0;
	    gbcnt.gridy = 1;
	    panelCentro.add(lblLogIn,gbcnt);
	    
	    JLabel lblNombre = new JLabel("Nombre completo", SwingConstants.RIGHT);
	    lblNombre.setFont(defaultFont);
	    gbcnt.gridy = 2;
	    panelCentro.add(lblNombre,gbcnt);
	    
	    JLabel lblSede = new JLabel("Sede asignada", SwingConstants.RIGHT);
	    lblSede.setFont(defaultFont);
	    gbcnt.gridy = 3;
	    panelCentro.add(lblSede,gbcnt);
	    
	    JLabel lblPwd = new JLabel("Contraseña", SwingConstants.RIGHT);
	    lblPwd.setFont(defaultFont);
	    gbcnt.gridy = 4;
	    panelCentro.add(lblPwd,gbcnt);
	    
	    //Cajas de texto
	    
	    JTextField txtLogIn  = new JTextField("LogIn");
	    txtLogIn.setFont(defaultFont);
		gbcnt.gridx = 1;
	    gbcnt.gridy = 1;
	    panelCentro.add(txtLogIn,gbcnt);
	    
	    JTextField txtNombre  = new JTextField("Nombre completo");
	    txtNombre.setFont(defaultFont);
	    gbcnt.gridy = 2;
	    panelCentro.add(txtNombre,gbcnt);
	   
	    JTextField txtSede  = new JTextField("Sede");
	    txtSede.setFont(defaultFont);
	    gbcnt.gridy = 3;
	    panelCentro.add(txtSede,gbcnt);
	    
	    JTextField txtPwd  = new JTextField("pwd");
	    txtPwd.setFont(defaultFont);
	    gbcnt.gridy = 4;
	    panelCentro.add(txtPwd,gbcnt);
	    
	    gbcnt.insets  = new Insets(45,0,0,0);
	    JButton bRegistrarEmpleado = new JButton("Registrar nuevo empleado");
	    bRegistrarEmpleado.setFont(new Font(null, Font.PLAIN, 25));
	    gbcnt.gridy = 5;
	    gbcnt.gridx = 0;
	    gbcnt.gridwidth = 2;
	    gbcnt.ipady = 10;
	    gbcnt.fill = GridBagConstraints.HORIZONTAL;
	    panelCentro.add(bRegistrarEmpleado,gbcnt);
	    bRegistrarEmpleado.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				String logIn = txtLogIn.getText();
				String nombre = txtNombre.getText();
				String sede = txtSede.getText();
				String contrasena = txtPwd.getText();
				
				if(crearEmpleado(logIn, nombre, sede, contrasena, "empleado"))
				{
					String mensaje = "El empleado " + nombre + " a sido creado con exito";
					JOptionPane.showMessageDialog(bRegistrarEmpleado, mensaje);
				}
				else
				{
					String mensaje = "Ha habido algun error ingrese la informacion nuevamente";
					JOptionPane.showMessageDialog(bRegistrarEmpleado, mensaje);
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
		this.revalidate();
		this.repaint();
	}

	//Logica de boton devolver
	protected void volverAPanelAnterior() 
	{
		PMenuAdminLocal panelAnterior = new PMenuAdminLocal(this.controller);
		this.removeAll();
		this.add(panelAnterior);
		this.revalidate();
		this.repaint();
		panelAnterior.setVisible(true);
	}
	
	//Logica de crear un nuevo empleado
	protected boolean crearEmpleado(String logIn, String nombre, String sede, String contrasena, String TipoUsuario)
	{
		boolean empleadoCreado = this.controller.crearEmpleado(logIn, nombre, sede, contrasena, TipoUsuario);
		return empleadoCreado;
	}

	//Logica de modificar la info de un empleado 
	protected boolean modificarEmpleado(String nombreCompleto, String nuevoLogIn, String nuevaContrasena, String nuevaSede, boolean cambiarLogin, boolean cambiarContraseña, boolean cambiarSede)
	{
		boolean infoModificada = this.controller.modificarInfoEmpleado(nombreCompleto, nuevoLogIn, nuevaContrasena, nuevaSede, cambiarLogin, cambiarContraseña, cambiarSede);
		return infoModificada;
	}

}
