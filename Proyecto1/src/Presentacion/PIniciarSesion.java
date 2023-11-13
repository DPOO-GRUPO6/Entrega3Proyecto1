package Presentacion;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import logica.Empresa;

public class PIniciarSesion extends JPanel{
	private MenuPrincipal panelMenuPrincipal;
	public Controlador controller;
	
	PIniciarSesion(Controlador controller){
		this.controller = controller;
		JPanel panelCentro = new JPanel();
		this.setLayout(new BorderLayout());
		JLabel lblIniciarSesion = new JLabel("Iniciar sesion", SwingConstants.CENTER);
		lblIniciarSesion.setFont(new Font(null, Font.BOLD,55));
		this.add(lblIniciarSesion, BorderLayout.NORTH);
	
		 GridBagLayout gbl = new GridBagLayout();
		 panelCentro.setLayout(gbl);
		 GridBagConstraints gbcnt = new GridBagConstraints();
		 JLabel lblUsuario = new JLabel("Ingrese su usuario: ");
		 lblUsuario.setFont(new Font(null, Font.PLAIN,20));
		 
	     JTextField textUsuario = new JTextField("", 15);
	     textUsuario.setFont(new Font(null, Font.PLAIN,20));
	    	 
	     
	     JLabel lblPwd = new JLabel("Ingrese su contrasenia: ");
	     lblPwd.setFont(new Font(null, Font.PLAIN,20));
		 
	     JTextField textPwd = new JTextField("", 15);
	     textPwd.setFont(new Font(null, Font.PLAIN,20));
	     
	     JButton bIniciar = new JButton("Iniciar Sesion");
	     bIniciar.setFont(new Font(null, Font.PLAIN,20));
	     bIniciar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String usuario = textUsuario.getText();
				String pwd = textPwd.getText();
				iniciarSesion(usuario, pwd);
				textUsuario.setText("");
				textPwd.setText("");
			}
	    	 
	     });
	     
	     gbcnt.fill = GridBagConstraints.HORIZONTAL;
	     gbcnt.ipady = 10;
	     gbcnt.gridx = 1;
	     gbcnt.gridy = 0;
	     panelCentro.add(lblUsuario, gbcnt);
	     
	     gbcnt.fill = GridBagConstraints.HORIZONTAL;
	     gbcnt.gridx = 2;
	     gbcnt.gridy = 0;
	     panelCentro.add(textUsuario, gbcnt);
	     
	     panelCentro.add(Box.createRigidArea(new Dimension(0, 150)));
	     
	     gbcnt.fill = GridBagConstraints.HORIZONTAL;
	     gbcnt.ipady = 10;
	     gbcnt.gridx = 1;
	     gbcnt.gridy = 1;
	     panelCentro.add(lblPwd, gbcnt);
	     
	     gbcnt.gridx = 2;
	     gbcnt.gridy = 1;
	     panelCentro.add(textPwd,gbcnt);
	     
	     gbcnt.gridx = 1;
	     gbcnt.gridy = 2;
	     gbcnt.fill = GridBagConstraints.HORIZONTAL;
	     gbcnt.gridwidth = 2;
	     panelCentro.add(Box.createRigidArea(new Dimension(0, 100)),gbcnt);
	     
	     gbcnt.gridx = 1;
	     gbcnt.gridy = 3;
	     gbcnt.gridwidth = 2;
	     panelCentro.add(bIniciar,gbcnt);
	     
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

	protected void iniciarSesion(String usuario, String pwd) {
		int tipoUsuario = this.controller.inicarSesion(usuario, pwd);
		
		if(tipoUsuario == 0) {
			this.removeAll();
			PMenuCliente panelInicioUsuario = new PMenuCliente(this.controller);
			this.add(panelInicioUsuario);
			this.revalidate();
			this.repaint();
			panelInicioUsuario.setVisible(true);
		}
		else if(tipoUsuario == 1) {
			this.removeAll();
			PMenuEmpleado panelInicioUsuario = new PMenuEmpleado(this.controller);
			this.add(panelInicioUsuario);
			this.revalidate();
			this.repaint();
			panelInicioUsuario.setVisible(true);
		}
		else if(tipoUsuario == 2) {
			this.removeAll();
			PMenuAdminLocal panelInicioUsuario = new PMenuAdminLocal(this.controller);
			this.add(panelInicioUsuario);
			this.revalidate();
			this.repaint();
			panelInicioUsuario.setVisible(true);
		}
		else if(tipoUsuario == 3) {
			this.removeAll();
			PMenuAdminGeneral panelInicioUsuario = new PMenuAdminGeneral();
			this.add(panelInicioUsuario);
			this.revalidate();
			this.repaint();
			panelInicioUsuario.setVisible(true);
		}
		else {
			 JOptionPane.showMessageDialog(this,"No se encontró el usuario o la contraseña es incorrecta","Alert",JOptionPane.WARNING_MESSAGE); 
		}	
	}


	protected void volverAPanelAnterior() {
		this.removeAll();
		this.panelMenuPrincipal = new MenuPrincipal(this.controller);
		this.add(this.panelMenuPrincipal);
		this.revalidate();
		this.repaint();
		this.panelMenuPrincipal.setVisible(true);
	}
}
