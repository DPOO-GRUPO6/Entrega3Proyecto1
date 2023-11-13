package Presentacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;

import logica.Alquiler;
import logica.Empleado;
import logica.Empresa;
import logica.Reserva;
import logica.Vehiculo;

public class PAlquilarConReserva extends JPanel{
	
	public Controlador controller;

	PAlquilarConReserva(Controlador controller){
		this.controller= controller;
		MaskFormatter formatFecha = null;
	    try {
			formatFecha = new MaskFormatter("##/##/####");
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.setLayout(new BorderLayout());
		String idReserva;
		idReserva = JOptionPane.showInputDialog(null, "Ingrese el numero de la reserva:");
		String cadena2="";
		if (idReserva.equalsIgnoreCase(cadena2)) {
			JOptionPane.showMessageDialog(null, "No se puede realizar el alquiler sin el numero de reserva", "Alert", JOptionPane.WARNING_MESSAGE);
			volverAPanelAnterior();
		}
		else {
		JLabel lblTitulo = new JLabel("Finalizar alquiler", SwingConstants.CENTER);
		lblTitulo.setFont(new Font(null, Font.BOLD, 45));
		this.add(lblTitulo, BorderLayout.NORTH);
		
		JPanel panelCentro = new JPanel();
		GridBagLayout gbl = new GridBagLayout();
		panelCentro.setLayout(gbl);
		GridBagConstraints gbcnt = new GridBagConstraints();
		Font defaultFont = new Font(null, Font.PLAIN,15);
		
		JLabel lblInstruccion = new JLabel("Complete la siguiente información",SwingConstants.CENTER);
		lblInstruccion.setFont(new Font(null, Font.PLAIN,15));
		gbcnt.gridx = 0;
	    gbcnt.gridy = 0;
	    gbcnt.gridwidth = 2;
	    gbcnt.fill = GridBagConstraints.HORIZONTAL;
	    gbcnt.insets  = new Insets(0,5,20,5);
	    panelCentro.add(lblInstruccion,gbcnt);
	    
	    JLabel lblSeguro = new JLabel("Seleccione un seguro (opcional)", SwingConstants.RIGHT);
	    lblSeguro.setFont(defaultFont);
	    gbcnt.gridy = 1;
	    gbcnt.gridx = 0;
	    panelCentro.add(lblSeguro, gbcnt);
	    
	    String seguros[]= {"seg1", "seg2"};
	    JComboBox CBseguro = new JComboBox(seguros);
	    CBseguro.setBackground(Color.white);
	    gbcnt.gridy = 1;
	    gbcnt.gridx = 2;
	    gbcnt.gridwidth = 2;
	    panelCentro.add(CBseguro,gbcnt);
	    gbcnt.insets = new Insets(0,0,10,0);
	    
	    JPanel PregistroConductorExtra = new JPanel();
	    PregistroConductorExtra.setLayout(new GridBagLayout());
	    GridBagConstraints gbc = new GridBagConstraints();
	    PregistroConductorExtra.setBorder(BorderFactory.createTitledBorder("Registro de conductores adicionales- Complete la siguiente información (opcional)"));
	    
	    JLabel lblNumeroLicenciaExtra = new JLabel("Número de licencia",SwingConstants.RIGHT);
	    lblNumeroLicenciaExtra.setFont(new Font(null, Font.PLAIN, 13));
	    gbc.gridy= 1;
	    gbc.fill = GridBagConstraints.HORIZONTAL;
	    gbc.insets = new Insets(5,5,5,5);
	    PregistroConductorExtra.add(lblNumeroLicenciaExtra, gbc);
	    
	    JLabel lblPaisExpExtra = new JLabel("Pais de expedicion de la licencia",SwingConstants.RIGHT);
	    gbc.gridy= 2;
	    lblPaisExpExtra.setFont(new Font(null, Font.PLAIN, 13));
	    PregistroConductorExtra.add(lblPaisExpExtra, gbc);
	    
	    JLabel lblFechaExpExtra = new JLabel("Fecha de expedición de la licencia",SwingConstants.RIGHT);
	    gbc.gridy= 3;
	    lblFechaExpExtra.setFont(new Font(null, Font.PLAIN, 13));
	    PregistroConductorExtra.add(lblFechaExpExtra, gbc);
	    
	    JTextField txtNumeroLicenciaExtra = new JTextField("Número de licencia");
	    gbc.gridx = 1;
	    gbc.gridy= 1;
	    PregistroConductorExtra.add(txtNumeroLicenciaExtra, gbc);
	    
	    JTextField txtPaisExpExtra = new JTextField("Pais de expedicion de la licencia");
	    gbc.gridy= 2;
	    PregistroConductorExtra.add(txtPaisExpExtra, gbc);
	    
	    JFormattedTextField txtFechaExpExtra = new JFormattedTextField(formatFecha);
	    Object textoOriginal = txtFechaExpExtra.getValue();
	    gbc.gridy= 3;
	    PregistroConductorExtra.add(txtFechaExpExtra, gbc);
	    
	    JButton bAgregarCondExtra = new JButton("Agregar conductor extra");
	    gbc.gridy= 0;
	    gbc.gridx = 3;
	    gbc.gridheight = 4;
	    gbc.fill = GridBagConstraints.VERTICAL;
	    PregistroConductorExtra.add(bAgregarCondExtra, gbc);
	    ArrayList<ArrayList> conductores = new ArrayList<ArrayList>();
	    bAgregarCondExtra .addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ArrayList<String> infoConductor = new ArrayList<String>();
				String numLicencia= txtNumeroLicenciaExtra.getText();
				String paisLicencia=txtPaisExpExtra.getText();
				String fechaLicencia=txtFechaExpExtra.getText();
				infoConductor.add(numLicencia);
				infoConductor.add(paisLicencia);
				infoConductor.add(fechaLicencia);
				conductores.add(infoConductor);
				txtNumeroLicenciaExtra.setText("           ");
				txtPaisExpExtra.setText("          ");
				txtFechaExpExtra.setValue(textoOriginal);
				JOptionPane.showMessageDialog(null, "Se añadio el conductor", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
				
			}
	    	
	    });
	    
	    gbcnt.gridy = 6;
	    gbcnt.gridx = 0;
	    gbcnt.gridwidth = 4;
	    gbcnt.insets = new Insets(15,0,0,0);
	    panelCentro.add(PregistroConductorExtra,gbcnt);
	    
	    JButton bEnviar = new JButton("Alquilar vehiculo");
	    bEnviar.setFont(new Font(null, Font.BOLD,18));
	    gbcnt.gridx = 0;
	    gbcnt.gridy = 7;
	    gbcnt.gridwidth = 4;
	    gbcnt.ipady = 10;
	    gbcnt.insets  = new Insets(20,10,10,10);
	    gbcnt.fill = GridBagConstraints.HORIZONTAL;
	    panelCentro.add(bEnviar,gbcnt);
	    
	    bEnviar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int seguro= CBseguro.getSelectedIndex()-1;
				List conductoresadi= conductores ;
				List alquilerReserva= null;
				try {
					alquilerReserva= (List)controller.infoAlquilerReserva(idReserva,seguro,conductoresadi);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if ((int)alquilerReserva.get(0)==0) {
					JOptionPane.showMessageDialog(bEnviar, "No se puede realizar el alquiler con esas especificaciones", "Alert", JOptionPane.WARNING_MESSAGE);
				}
				else {
					int total= (int)alquilerReserva.get(0);
					Reserva reserva= (Reserva)alquilerReserva.get(1);
					Alquiler alquiler= (Alquiler)alquilerReserva.get(2);
					Vehiculo vehiculoAlquiler=(Vehiculo)alquilerReserva.get(3);
					Date fechaInicial= reserva.getFechaSalida();
					Date fechaFinal= reserva.getFechaLlegada();
					ventanaTotal(total,alquiler,vehiculoAlquiler,fechaInicial,  fechaFinal);
					
				}

			}
	    	
	    });
	    
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
	}}


	protected void ventanaTotal(int total, Alquiler alquiler, Vehiculo vehiculoAlquiler, Date fechaInicial,
			Date fechaFinal) {
		// TODO Auto-generated method stub
		JFrame ventana1 = new JFrame("Realizar reserva");
		String resp= "Para realizar el alquiler debe realizar el pago total.\n"+ " El cual es de "+ String.valueOf(total) + " ¿Acepta el pago? ";
		ventana1.setSize(700, 200);
        ventana1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel pElementonVS = new JPanel();
        JButton botonAceptar = new JButton("Acepto");
        botonAceptar.setPreferredSize(new Dimension(150, 50));
        JButton botonNoAceptar = new JButton("No acepto");
        botonNoAceptar.setPreferredSize(new Dimension(150, 50));
        JPanel pElementonVS2 = new JPanel();
        pElementonVS2.setLayout(new GridLayout(2,1,20,20));
        pElementonVS2.add(botonAceptar);
        pElementonVS2.add(botonNoAceptar);
        pElementonVS.setLayout(new GridLayout(2,1,20,20));
        JLabel etiqueta = new JLabel(resp);
        pElementonVS.add(etiqueta);
		pElementonVS.add(pElementonVS2);
		ventana1.add(pElementonVS);
        ventana1.setVisible(true);
        botonAceptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int idAlquiler=0;
				idAlquiler = controller.realizarAlquilerReserva(alquiler,vehiculoAlquiler,fechaInicial,  fechaFinal);
				String idalq= String.valueOf( idAlquiler);
				JOptionPane.showMessageDialog(null, "El id de su  alquiler es " + idalq , "Mensaje", JOptionPane.INFORMATION_MESSAGE);
				ventana1.dispose();
				volverAPanelAnterior();
			}
        	
        });
        botonNoAceptar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ventana1.dispose();
			}
        	
        });
	}


	protected void volverAPanelAnterior() {
		PMenuCliente panelAnterior = new PMenuCliente(this.controller);
		this.removeAll();
		this.add(panelAnterior);
		this.revalidate();
		this.repaint();
		panelAnterior.setVisible(true);
	}
}
