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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import logica.Empresa;

public class PIniciarSesion extends JPanel{
	private MenuPrincipal panelMenuPrincipal;
	
	PIniciarSesion(){
		JPanel panelCentro = new JPanel();
		this.setLayout(new BorderLayout());
		JLabel lblIniciarSesion = new JLabel("Iniciar sesión", SwingConstants.CENTER);
		lblIniciarSesion.setFont(new Font(null, Font.BOLD,55));
		this.add(lblIniciarSesion, BorderLayout.NORTH);
	
		 GridBagLayout gbl = new GridBagLayout();
		 panelCentro.setLayout(gbl);
		 GridBagConstraints gbcnt = new GridBagConstraints();
		 JLabel lblUsuario = new JLabel("Ingrese su usuario: ");
		 lblUsuario.setFont(new Font(null, Font.PLAIN,20));
		 
	     JTextField textUsuario = new JTextField("", 15);
	     textUsuario.setFont(new Font(null, Font.PLAIN,20));
	     
	     JLabel lblPwd = new JLabel("Ingrese su contraseña: ");
	     lblPwd.setFont(new Font(null, Font.PLAIN,20));
		 
	     JTextField textPwd = new JTextField("", 15);
	     textPwd.setFont(new Font(null, Font.PLAIN,20));
	     
	     JButton bIniciar = new JButton("Iniciar Sesión");
	     bIniciar.setFont(new Font(null, Font.PLAIN,20));
	     bIniciar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				goPanelIniciarSesion();
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

	protected void goPanelIniciarSesion() {
		this.removeAll();
		PMenuEmpleado panelEmplado = new PMenuEmpleado();
		this.add(panelEmplado);
		this.revalidate();
		this.repaint();
		panelEmplado.setVisible(true);
	}

	protected void volverAPanelAnterior() {
		this.removeAll();
		this.panelMenuPrincipal = new MenuPrincipal();
		this.add(this.panelMenuPrincipal);
		this.revalidate();
		this.repaint();
		this.panelMenuPrincipal.setVisible(true);
	}
}
