package Presentacion;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
		pDevuelto.setBorder(BorderFactory.createTitledBorder("Formulario para vehiculo devuelto"));
		
		GridBagConstraints gbcnt = new GridBagConstraints();
		gbcnt.gridx = 0;
		gbcnt.gridy = 0;
		gbcnt.gridwidth = 2;
		gbcnt.fill = GridBagConstraints.HORIZONTAL;
		gbcnt.insets = new Insets(5,0,30,0);
		JLabel lblDevuelto = new JLabel("Cambiar el estado de un vehículo devuelto");
		lblDevuelto.setFont(new Font(null, Font.PLAIN, 20));
		pDevuelto.add(lblDevuelto, gbcnt);
		
		JLabel lblPlaca= new JLabel("Ingrese la placa del vehículo a cambiar de estado");
		gbcnt.gridy = 1;
		gbcnt.insets = new Insets(8,5,8,5);
		lblPlaca.setFont(new Font(null, Font.PLAIN, 15));
		pDevuelto.add(lblPlaca, gbcnt);
		
		JTextField txtPlaca= new JTextField("AAA-000");
		gbcnt.gridy = 2;
		txtPlaca.setFont(new Font(null, Font.PLAIN, 15));
		pDevuelto.add(txtPlaca, gbcnt);
		
		JLabel bMantenimiento = new JLabel("Mandar a ", SwingConstants.RIGHT);
		bMantenimiento.setFont(new Font(null, Font.PLAIN, 15));
		gbcnt.gridy = 3;
		gbcnt.gridwidth = 1;
		pDevuelto.add(bMantenimiento, gbcnt);
		
		String opciones[] = {"Mantenimiento", "Limpieza"};
		JComboBox JBopciones = new JComboBox(opciones);
		gbcnt.gridy = 3;
		gbcnt.gridx = 1;
		gbcnt.gridwidth = 1;
		pDevuelto.add(JBopciones, gbcnt);
		
		JLabel lblFechaInic= new JLabel("Ingrese la fecha de inicio", SwingConstants.RIGHT);
		gbcnt.gridy = 4;
		gbcnt.gridx = 0;
		lblFechaInic.setFont(new Font(null, Font.PLAIN, 15));
		pDevuelto.add(lblFechaInic, gbcnt);
		
		JTextField txtFechaInic= new JTextField("dd/mm/aaaa");
		gbcnt.gridx = 1;
		txtFechaInic.setFont(new Font(null, Font.PLAIN, 15));
		pDevuelto.add(txtFechaInic, gbcnt);
		
		JLabel lblFechaFin= new JLabel("Ingrese la fecha de salida", SwingConstants.RIGHT);
		gbcnt.gridy = 5;
		gbcnt.gridx = 0;
		lblFechaFin.setFont(new Font(null, Font.PLAIN, 15));
		pDevuelto.add(lblFechaFin, gbcnt);
		
		JTextField txtFechaFin= new JTextField("dd/mm/aaaa");
		gbcnt.gridx = 1;
		txtFechaFin.setFont(new Font(null, Font.PLAIN, 15));
		pDevuelto.add(txtFechaFin, gbcnt);
		
		JButton bCambiarEstadoDev = new JButton("Cambiar estado");
		bCambiarEstadoDev.setFont(new Font(null, Font.PLAIN, 18));
		gbcnt.gridy = 6;
		gbcnt.gridx = 0;
		gbcnt.gridwidth = 2;
		gbcnt.insets = new Insets(20,5,20,5);
		gbcnt.ipady = 18;
		pDevuelto.add(bCambiarEstadoDev, gbcnt);
		panelCentro.add(pDevuelto);
		
		//segundo panel (derecha)
		JPanel pDisponible = new JPanel();
		pDisponible.setLayout(new GridBagLayout());
		pDisponible.setBorder(BorderFactory.createTitledBorder("Formulario para vehiculo disponible"));
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(5,0,30,0);
		JLabel lblDisp = new JLabel("Cambiar el estado de un vehículo disponible");
		lblDisp.setFont(new Font(null, Font.PLAIN, 20));
		pDisponible.add(lblDisp, gbc);
		
		JLabel lblPlacaDisp= new JLabel("Ingrese la placa del vehículo a cambiar de estado", SwingConstants.CENTER);
		gbc.gridy = 1;
		gbc.insets = new Insets(8,5,8,5);
		lblPlacaDisp.setFont(new Font(null, Font.PLAIN, 15));
		pDisponible.add(lblPlacaDisp, gbc);
		
		JTextField txtPlacaDisp= new JTextField("AAA-000");
		gbc.gridy = 2;
		txtPlacaDisp.setFont(new Font(null, Font.PLAIN, 15));
		pDisponible.add(txtPlacaDisp, gbc);
		
		JButton bCambiarEstadoDisp = new JButton("Cambiar estado");
		bCambiarEstadoDisp.setFont(new Font(null, Font.PLAIN, 18));
		gbcnt.gridy = 6;
		gbcnt.gridx = 0;
		gbcnt.gridwidth = 2;
		gbcnt.insets = new Insets(20,5,147,5);
		gbcnt.ipady = 18;
		pDisponible.add(bCambiarEstadoDisp, gbcnt);
		
		panelCentro.add(pDevuelto);
		panelCentro.add(pDisponible);
		this.add(panelCentro, BorderLayout.CENTER);

		
	}
}
