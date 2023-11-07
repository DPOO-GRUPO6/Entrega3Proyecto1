package Presentacion;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

public class PIniciarSesion extends JPanel{
	
	PIniciarSesion(){
		JPanel panelCentro = new JPanel();
		this.setLayout(new BorderLayout());
		JLabel lblIniciarSesion = new JLabel("Iniciar sesión", SwingConstants.CENTER);
		lblIniciarSesion.setFont(new Font(null, Font.BOLD,60));
		this.add(lblIniciarSesion, BorderLayout.NORTH);
	
		 SpringLayout layout = new SpringLayout();
		 panelCentro.setLayout(layout);
		 JLabel lblUsuario = new JLabel("Ingrese su usuario: ");
		 lblUsuario.setFont(new Font(null, Font.PLAIN,20));
		 
	     JTextField textUsuario = new JTextField("Text field", 15);
	     textUsuario.setFont(new Font(null, Font.PLAIN,20));
	     
	     JLabel lblPwd = new JLabel("Ingrese su contreseña: ");
	     lblPwd.setFont(new Font(null, Font.PLAIN,20));
		 
	     JTextField textPwd = new JTextField("Pwd", 15);
	     textPwd.setFont(new Font(null, Font.PLAIN,20));
	     
	     JButton bIniciar = new JButton("Iniciar Sesión");
	     
	     panelCentro.add(lblPwd);
	     panelCentro.add(textPwd);
	     panelCentro.add(lblUsuario);
	     panelCentro.add(textUsuario);
	     
	     
	     layout.putConstraint(SpringLayout.EAST, lblUsuario, 10, SpringLayout.EAST, this);
	     layout.putConstraint(SpringLayout.SOUTH, lblUsuario, 10, SpringLayout.SOUTH, this);
	     layout.putConstraint(SpringLayout.WEST, textUsuario, 10, SpringLayout.EAST, lblUsuario);
	     layout.putConstraint(SpringLayout.SOUTH, textUsuario, 10, SpringLayout.SOUTH, this);
	     
	     layout.putConstraint(SpringLayout.EAST, lblPwd, 10, SpringLayout.EAST, this);
	     layout.putConstraint(SpringLayout.NORTH, lblPwd, 10, SpringLayout.SOUTH, this);
	     layout.putConstraint(SpringLayout.WEST, textPwd, 10, SpringLayout.EAST, lblPwd);
	     layout.putConstraint(SpringLayout.NORTH, textPwd, 10, SpringLayout.SOUTH, this);
	     
	     this.add(panelCentro, BorderLayout.CENTER);
	     this.add(bIniciar, BorderLayout.AFTER_LAST_LINE);
	}
}
