package Presentacion;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MenuPrincipal extends JPanel{
	private JLabel lblSaludo;
	private ImageIcon imagen;
	private JButton bIniciarSesion;
	private JButton bRegistrarse;
	private JPanel panelCentro;
	public PIniciarSesion panelInicioSesion;
	public PRegistroCliente panelRegClient;
	public Controlador controller;

	MenuPrincipal(Controlador controller){
		this.controller = controller;
		this.setLayout(new BorderLayout());
		this.lblSaludo = new JLabel("Bienvenido", SwingConstants.CENTER);
		this.lblSaludo.setFont(new Font(null, Font.BOLD,70));
		this.add(lblSaludo, BorderLayout.NORTH);
		this.imagen = new ImageIcon("data/logo.png");
		
		this.panelCentro = new JPanel();
		this.panelCentro.setLayout(new BoxLayout(this.panelCentro,1));
		this.panelCentro.add(Box.createRigidArea(new Dimension(0, 30)));
		
		JLabel img = new JLabel(imagen);
		img.setAlignmentX(CENTER_ALIGNMENT);
		this.panelCentro.add(img);
		this.panelCentro.add(Box.createRigidArea(new Dimension(0, 30)));
	
		this.bIniciarSesion = new JButton("Iniciar sesión");
		this.bIniciarSesion.setAlignmentX(CENTER_ALIGNMENT);
		this.bIniciarSesion.setFont(new Font(null, Font.PLAIN,35));
		this.bIniciarSesion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				iniciarSesion();
			}
			
		});
		
		this.panelCentro.add(bIniciarSesion);
		this.panelCentro.add(Box.createRigidArea(new Dimension(0, 30)));
		
		
		this.bRegistrarse = new JButton("Registrarse");
		this.bRegistrarse.setAlignmentX(CENTER_ALIGNMENT);
		this.bRegistrarse.setFont(new Font(null, Font.PLAIN,35));
		this.bRegistrarse.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				registrarse();
			}
			
		});
		
		this.panelCentro.add(bRegistrarse);

		
		this.add(panelCentro, BorderLayout.CENTER);
		
	}
	
	protected void registrarse() {
		this.removeAll();
		this.panelRegClient = new PRegistroCliente();
		this.add(this.panelRegClient);
		this.revalidate();
		this.repaint();
		this.panelRegClient.setVisible(true);
		
	}

	public void iniciarSesion() {
		this.removeAll();
		this.panelInicioSesion = new PIniciarSesion(this.controller);
		this.add(this.panelInicioSesion);
		this.revalidate();
		this.repaint();
		this.panelInicioSesion.setVisible(true);
	}
}
