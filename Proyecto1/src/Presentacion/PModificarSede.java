package Presentacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PModificarSede extends JPanel{
	PModificarSede(){
		this.setLayout(new BorderLayout());
		JLabel lblTitulo = new JLabel("Modificar sede", SwingConstants.CENTER);
		lblTitulo.setFont(new Font(null, Font.BOLD, 45));
		this.add(lblTitulo, BorderLayout.NORTH);
		
		JPanel panelCentro = new JPanel();
		GridBagLayout gbl = new GridBagLayout();
		panelCentro.setLayout(gbl);
		GridBagConstraints gbcnt = new GridBagConstraints();
		Font defaultFont = new Font(null, Font.PLAIN,15);
		
		JLabel lblInstruccion = new JLabel("Seleccione la sede a modificar",SwingConstants.CENTER);
		lblInstruccion.setFont(new Font(null, Font.PLAIN,15));
		gbcnt.gridx = 0;
	    gbcnt.gridy = 0;
	    gbcnt.gridwidth = 2;
	    gbcnt.fill = GridBagConstraints.HORIZONTAL;
	    gbcnt.insets  = new Insets(0,0,30,0);
	    panelCentro.add(lblInstruccion,gbcnt);
	    
	    String[] sedes = {"sede1", "sede2"};
	    JComboBox jcbSedes = new JComboBox(sedes);
	    jcbSedes.setBackground(Color.white);
	    gbcnt.insets  = new Insets(10,5,10,2);
	    gbcnt.fill = GridBagConstraints.HORIZONTAL;
	    jcbSedes.setFont(defaultFont);
	    gbcnt.gridy = 1;
	    gbcnt.ipady = 10;
	    panelCentro.add(jcbSedes,gbcnt);
	    
	    JButton bModificarNombre = new JButton("Modifcar Nombre");
	    bModificarNombre.setFont(defaultFont);
	    gbcnt.gridy = 2;
	    gbcnt.gridwidth = 1;
	    gbcnt.ipady = 8;
	    panelCentro.add(bModificarNombre,gbcnt);
	    
	    JButton bModificarDireccion = new JButton("Modificar dirección");
	    bModificarDireccion.setFont(defaultFont);
	    gbcnt.gridy = 3;
	    panelCentro.add(bModificarDireccion,gbcnt);
	    
	    JButton bModificarDiasAtencion = new JButton("Modificar días de atención");
	    bModificarDiasAtencion.setFont(defaultFont);
	    gbcnt.gridy = 2;
	    gbcnt.gridx = 1;
	    panelCentro.add(bModificarDiasAtencion,gbcnt);
	    
	    JButton bModificarHorasAtencion = new JButton("Modificar horas de atención");
	    bModificarDiasAtencion.setFont(defaultFont);
	    gbcnt.gridy = 3;
	    panelCentro.add(bModificarHorasAtencion,gbcnt);
	    
	    JButton bModificarAdminLocal = new JButton("Modificar administrador local");
	    bModificarAdminLocal.setFont(defaultFont);
	    gbcnt.gridx = 0;
	    gbcnt.gridy = 4;
	    gbcnt.gridwidth = 2;
	    panelCentro.add(bModificarAdminLocal,gbcnt);
	    
	    this.add(panelCentro, BorderLayout.CENTER);
	    
	    //Actions listeners
	    
	    bModificarNombre.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				modificarNombreSede();
			}
	    });
	    
	    bModificarDireccion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				modificarDireccionSede();
			}
	    });
	    
	    bModificarDiasAtencion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				modificarDiasAtencion();
			}
	    });
	    
	    bModificarHorasAtencion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				modificarHorasAtencion();
			}
	    });
	    
	    bModificarAdminLocal.addActionListener(new ActionListener() {

	 			@Override
	 			public void actionPerformed(ActionEvent e) {
	 				modificarAdminLocal();
	 			}
	 	    });
	}

	protected void modificarAdminLocal() {
		String nuevoAdminLoc = JOptionPane.showInputDialog(this, "Ingrese el nuevo administrador local de la sede", "");
	}

	protected void modificarHorasAtencion() {
		String nuevasHorasAt = JOptionPane.showInputDialog(this, "Ingrese las nuevas horas de atención de la sede", "");
	}

	protected void modificarDiasAtencion() {
		String nuevosDiasAt = JOptionPane.showInputDialog(this, "Ingrese los nuevos dias de atención de la sede", "");
	}

	protected void modificarDireccionSede() {
		String nuevaDir = JOptionPane.showInputDialog(this, "Ingrese la nueva dirección de la sede", "");
	}

	protected void modificarNombreSede() {
		String nuevoNombre = JOptionPane.showInputDialog(this, "Ingrese el nuevo nombre de la sede", "");
	}
}
