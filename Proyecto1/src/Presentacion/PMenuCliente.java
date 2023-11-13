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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PMenuCliente extends JPanel{
	private PReservarCarro panelReservas;
	public Controlador controller;
	
	public PMenuCliente(Controlador controller){
		this.controller= controller;
		this.setLayout(new BorderLayout());
		JLabel lblBienvenido = new JLabel("Bienvenido", SwingConstants.CENTER);
		lblBienvenido.setFont(new Font(null, Font.BOLD, 45));
		this.add(lblBienvenido, BorderLayout.NORTH);
		
		JPanel panelCentro  = new JPanel();
		panelCentro.setLayout(new GridBagLayout());
		GridBagConstraints gbcnt = new GridBagConstraints();
		Font defaultFont = new Font(null, Font.PLAIN,25);
		
		gbcnt.insets  = new Insets(0,0,50,0);
		gbcnt.gridx = 0;
		gbcnt.gridy = 0;
		JLabel lblinstrucc = new JLabel("Qué desea hacer?", SwingConstants.RIGHT);
		lblinstrucc.setFont(new Font(null, Font.PLAIN,20));
		panelCentro.add(lblinstrucc, gbcnt);
		
		gbcnt.insets  = new Insets(10,0,10,0);
		gbcnt.gridy = 1;
		gbcnt.ipady = 10;
		gbcnt.ipadx = 150;
		gbcnt.fill = GridBagConstraints.HORIZONTAL;
		JButton bReservar = new JButton("Reservar vehiculo");
		bReservar.setFont(defaultFont);
		panelCentro.add(bReservar, gbcnt);
		bReservar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				goPanelReservarCarro();
			}
			
		});
		
		gbcnt.gridy = 2;
		JButton bAlquilarNormal = new JButton("Alquilar sin reserva");
		bAlquilarNormal.setFont(defaultFont);
		panelCentro.add(bAlquilarNormal,gbcnt);
		bAlquilarNormal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				goPAlquilarSinReserva();
			}
			
		});
		
		
		gbcnt.gridy = 3;
		JButton bAlquilarConReserva = new JButton("Alquilar con reserva");
		bAlquilarConReserva.setFont(defaultFont);
		panelCentro.add(bAlquilarConReserva,gbcnt);
		bAlquilarConReserva.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				goOptionPanelModifDatos();
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

	protected void goOptionPanelModifDatos() {
		int sel = JOptionPane.showConfirmDialog(this,"Desea modificar los datos de su reserva?", "Modificar datos reserva", JOptionPane.YES_NO_OPTION);
		if(sel ==1) {
			PAlquilarConReserva panelAlquiler = new PAlquilarConReserva(this.controller);
			this.removeAll();
			this.add(panelAlquiler);
			this.revalidate();
			this.repaint();
			panelAlquiler.setVisible(true);
		}
		else if(sel==0) {
			PAlquilarSinReserva panelAlquiler = new PAlquilarSinReserva(this.controller);
			this.removeAll();
			this.add(panelAlquiler);
			this.revalidate();
			this.repaint();
			panelAlquiler.setVisible(true);
		}
	}
	

	protected void goPAlquilarSinReserva() {
		PAlquilarSinReserva panelAlquilarSinReserva = new PAlquilarSinReserva(this.controller);
		this.removeAll();
		this.add(panelAlquilarSinReserva);
		this.revalidate();
		this.repaint();
		panelAlquilarSinReserva.setVisible(true);		
	}

	protected void cerrarSesion() {
		
		MenuPrincipal panelAnterior = new MenuPrincipal(this.controller);
		this.removeAll();
		this.add(panelAnterior);
		this.revalidate();
		this.repaint();
		panelAnterior.setVisible(true);
		
	}

	protected void goPanelReservarCarro() {
		this.removeAll();
		this.panelReservas = new PReservarCarro(this.controller);
		this.add(this.panelReservas);
		this.revalidate();
		this.repaint();
		this.panelReservas.setVisible(true);
	}
}
