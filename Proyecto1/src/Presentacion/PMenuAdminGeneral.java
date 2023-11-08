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

public class PMenuAdminGeneral extends JPanel{
	
	PMenuAdminGeneral(){
		this.setLayout(new BorderLayout());
		JLabel lblBienvenido = new JLabel("Bienvenido", SwingConstants.CENTER);
		lblBienvenido.setFont(new Font(null, Font.BOLD, 45));
		this.add(lblBienvenido, BorderLayout.NORTH);
		
		JPanel panelCentro  = new JPanel();
		panelCentro.setLayout(new GridBagLayout());
		GridBagConstraints gbcnt = new GridBagConstraints();
		Font defaultFont = new Font(null, Font.PLAIN,25);
		
		gbcnt.insets  = new Insets(0,0,20,0);
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
		JButton bRegistrarVehiculo = new JButton("Registar nuevo vehiculo");
		bRegistrarVehiculo.setFont(defaultFont);
		panelCentro.add(bRegistrarVehiculo, gbcnt);
		
		gbcnt.gridy = 2;
		JButton bDardeBajaVeh = new JButton("Dar de baja un vehículo");
		bDardeBajaVeh.setFont(defaultFont);
		panelCentro.add(bDardeBajaVeh,gbcnt);
		
		gbcnt.gridy = 3;
		JButton bConfigurarSeguro = new JButton("Configurar seguro");
		bConfigurarSeguro.setFont(defaultFont);
		panelCentro.add(bConfigurarSeguro,gbcnt);
		
		gbcnt.gridy = 4;
		JButton bTransladoIn = new JButton("Realizar translado interno");
		bTransladoIn.setFont(defaultFont);
		panelCentro.add(bTransladoIn,gbcnt);
		
		gbcnt.gridy = 5;
		JButton bCambiarInfoSede = new JButton("Modificar la información de una sede");
		bCambiarInfoSede.setFont(defaultFont);
		panelCentro.add(bCambiarInfoSede,gbcnt);

		
		this.add(panelCentro, BorderLayout.CENTER);
	}
}
