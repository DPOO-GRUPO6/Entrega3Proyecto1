package Presentacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
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
	public Controlador controller;
	private String[] sedes;
	private JComboBox jcbSedes;
	
	PModificarSede(Controlador controller){
		this.controller = controller;
		this.sedes = this.controller.getSedes();
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
	    
	    
	    JComboBox jcbSedes = new JComboBox(this.sedes);
	    jcbSedes.setBackground(Color.white);
	    gbcnt.insets  = new Insets(10,5,10,2);
	    gbcnt.fill = GridBagConstraints.HORIZONTAL;
	    jcbSedes.setFont(defaultFont);
	    gbcnt.gridy = 1;
	    gbcnt.ipady = 10;
	    this.jcbSedes = jcbSedes;
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
				String sedeSelecc = (String) jcbSedes.getSelectedItem();
				modificarNombreSede(sedeSelecc);
				
			}
	    });
	    
	    bModificarDireccion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String sedeSelecc = (String) jcbSedes.getSelectedItem();
				modificarDireccionSede(sedeSelecc);
			}
	    });
	    
	    bModificarDiasAtencion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String sedeSelecc = (String) jcbSedes.getSelectedItem();
				modificarDiasAtencion(sedeSelecc);
			}
	    });
	    
	    bModificarHorasAtencion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String sedeSelecc = (String) jcbSedes.getSelectedItem();
				modificarHorasAtencion(sedeSelecc);
			}
	    });
	    
	    bModificarAdminLocal.addActionListener(new ActionListener() {

	 			@Override
	 			public void actionPerformed(ActionEvent e) {
	 				String sedeSelecc = (String) jcbSedes.getSelectedItem();
	 				modificarAdminLocal(sedeSelecc);
	 			}
	 	    });
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


	protected void modificarAdminLocal(String sedeSelecc) {
		String nuevoAdminLoc = JOptionPane.showInputDialog(this, "Ingrese el nuevo administrador local de la sede", "");
		if(nuevoAdminLoc != null) {
			String result = this.controller.modificarInfoSede(5, nuevoAdminLoc, sedeSelecc);
			JOptionPane.showMessageDialog(null, result);
		}
	}

	protected void modificarHorasAtencion(String sedeSelecc) {
		String nuevasHorasAt = JOptionPane.showInputDialog(this, "Ingrese las nuevas horas de atención de la sede", "");
		if(nuevasHorasAt != null) {
			String result = this.controller.modificarInfoSede(4, nuevasHorasAt, sedeSelecc);
			JOptionPane.showMessageDialog(null, result);
		}
	}

	protected void modificarDiasAtencion(String sedeSelecc ) {
		String nuevosDiasAt = JOptionPane.showInputDialog(this, "Ingrese los nuevos dias de atención de la sede", "");
		if(nuevosDiasAt != null) {
			String result = this.controller.modificarInfoSede(3, nuevosDiasAt, sedeSelecc);
			JOptionPane.showMessageDialog(null, result);
		}
	}

	protected void modificarDireccionSede(String sedeSelecc) {
		String nuevaDir = JOptionPane.showInputDialog(this, "Ingrese la nueva dirección de la sede", "");
		if(nuevaDir != null) {
			String result = this.controller.modificarInfoSede(2, nuevaDir, sedeSelecc);
			JOptionPane.showMessageDialog(null, result);
		}
	}

	protected void modificarNombreSede(String sedeSelecc) {
		String nuevoNombre = JOptionPane.showInputDialog(this, "Ingrese el nuevo nombre de la sede", "");
		if(nuevoNombre != null) {
			String result = this.controller.modificarInfoSede(1, nuevoNombre, sedeSelecc);
			JOptionPane.showMessageDialog(null, result);
			this.sedes = this.controller.getSedes();
			this.jcbSedes.removeAllItems();
			for(String sede: this.sedes) {
				this.jcbSedes.addItem(sede);
			}
		}
		
	}
	
	protected void volverAPanelAnterior() {
		PMenuAdminGeneral panelAnterior = new PMenuAdminGeneral(this.controller);
		this.removeAll();
		this.add(panelAnterior);
		this.revalidate();
		this.repaint();
		panelAnterior.setVisible(true);
	}
}
