package Presentacion;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PMenuCliente extends JPanel{
	
	PMenuCliente(){
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
		
		gbcnt.gridy = 2;
		JButton bAlquilarNormal = new JButton("Alquilar sin reserva");
		bAlquilarNormal.setFont(defaultFont);
		panelCentro.add(bAlquilarNormal,gbcnt);
		
		gbcnt.gridy = 3;
		JButton bAlquilarConReserva = new JButton("Alquilar con reserva");
		bAlquilarConReserva.setFont(defaultFont);
		panelCentro.add(bAlquilarConReserva,gbcnt);
		
		this.add(panelCentro, BorderLayout.CENTER);
	}
}
