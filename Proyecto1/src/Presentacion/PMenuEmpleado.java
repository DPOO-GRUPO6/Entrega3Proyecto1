package Presentacion;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class PMenuEmpleado extends JPanel{
	
	PMenuEmpleado(){
		this.setLayout(new BorderLayout());
		
		JLabel lblBienvenido = new JLabel("Bienvenido", SwingConstants.CENTER);
		lblBienvenido.setFont(new Font(null, Font.BOLD, 45));
		this.add(lblBienvenido, BorderLayout.NORTH);
		
		JPanel panelCentro = new JPanel();
		panelCentro.setLayout(new GridLayout(1, 2));
		
		 //primer panel (izquierda)
		JPanel pDevuelto = new JPanel();
		pDevuelto.setLayout(new GridBagLayout());
		pDevuelto.setBorder(BorderFactory.createTitledBorder("P devuelto"));
		
		GridBagConstraints gbcnt = new GridBagConstraints();
		gbcnt.gridx = 0;
		gbcnt.gridy = 0;
		JLabel lblDevuelto = new JLabel("Cambiar el estado de un vehículo devuelto");
		lblDevuelto.setFont(new Font(null, Font.PLAIN, 20));
		pDevuelto.add(lblDevuelto, gbcnt);
		
		JLabel lblPlaca= new JLabel("Ingrese la placa del vehículo a cambiar de estado");
		gbcnt.gridy = 1;
		lblPlaca.setFont(new Font(null, Font.PLAIN, 15));
		pDevuelto.add(lblPlaca, gbcnt);
		
		JTextField txtPlaca= new JTextField("placa");
		gbcnt.gridy = 2;
		txtPlaca.setFont(new Font(null, Font.PLAIN, 15));
		gbcnt.fill = GridBagConstraints.HORIZONTAL;
		pDevuelto.add(txtPlaca, gbcnt);
		
		
		//segundo panel (derecha)
		JPanel pDisponible = new JPanel();
		pDisponible.setLayout(new GridBagLayout());
		pDisponible.setBorder(BorderFactory.createTitledBorder("P disponible"));
		
		panelCentro.add(pDevuelto);
		panelCentro.add(pDisponible);
		this.add(panelCentro, BorderLayout.CENTER);
		
		
	}
}
