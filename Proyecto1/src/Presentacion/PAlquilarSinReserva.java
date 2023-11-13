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
import javax.swing.border.Border;
import javax.swing.text.MaskFormatter;

import logica.Alquiler;
import logica.Reserva;
import logica.Vehiculo;

public class PAlquilarSinReserva extends JPanel{
	
	public Controlador controller;

	PAlquilarSinReserva(Controlador controller){
		this.controller= controller;
		MaskFormatter formatFecha = null;
	    try {
			formatFecha = new MaskFormatter("##/##/####");
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.setLayout(new BorderLayout());
		JLabel lblTitulo = new JLabel("Alquilar vehículo", SwingConstants.CENTER);
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
	    gbcnt.gridwidth = 5;
	    gbcnt.fill = GridBagConstraints.HORIZONTAL;
	    gbcnt.insets  = new Insets(0,0,20,0);
	    panelCentro.add(lblInstruccion,gbcnt);
	    
	    JLabel lblCategoria = new JLabel("Seleccione la categoría", SwingConstants.RIGHT);
	    gbcnt.insets  = new Insets(5,5,10,2);
	    gbcnt.fill = GridBagConstraints.HORIZONTAL;
	    gbcnt.gridwidth = 1;
	    lblCategoria.setFont(defaultFont);
		gbcnt.gridx = 0;
	    gbcnt.gridy = 1;
	    panelCentro.add(lblCategoria,gbcnt);
	    
	    JLabel lblFechaInicio = new JLabel("Fecha de recogida", SwingConstants.RIGHT);
	    lblFechaInicio.setFont(defaultFont);
	    gbcnt.gridy = 2;
	    panelCentro.add(lblFechaInicio,gbcnt);
	    
	    JLabel lblHoraInicio = new JLabel("Hora de recogida", SwingConstants.RIGHT);
	    lblHoraInicio.setFont(defaultFont);
	    gbcnt.gridy = 3;
	    panelCentro.add(lblHoraInicio,gbcnt);
	    
	    JLabel lblSedeRecogida = new JLabel("Sede de recogida", SwingConstants.RIGHT);
	    lblSedeRecogida.setFont(defaultFont);
	    gbcnt.gridy = 4;
	    panelCentro.add(lblSedeRecogida,gbcnt);
	    
	    JLabel lblFechaFin = new JLabel("Fecha llegada", SwingConstants.RIGHT);
	    lblFechaFin.setFont(defaultFont);
	    gbcnt.gridx = 2;
	    gbcnt.gridy = 2;
	    panelCentro.add(lblFechaFin,gbcnt);
	    
	    JLabel lblHoraFin = new JLabel("Hora de llegada", SwingConstants.RIGHT);
	    lblHoraFin.setFont(defaultFont);
	    gbcnt.gridx = 2;
	    gbcnt.gridy = 3;
	    panelCentro.add(lblHoraFin,gbcnt);
	    
	    JLabel lblSedeLlegada = new JLabel("Sede de llegada", SwingConstants.RIGHT);
	    lblSedeLlegada.setFont(defaultFont);
	    gbcnt.gridy = 4;
	    panelCentro.add(lblSedeLlegada,gbcnt);
	    
	    JLabel lblSeguro = new JLabel("Seleccione un seguro (opcional)", SwingConstants.RIGHT);
	    lblSeguro.setFont(defaultFont);
	    gbcnt.gridy = 5;
	    gbcnt.gridx = 0;
	    panelCentro.add(lblSeguro, gbcnt);
	    	    
	    /* text fields para info de registro */
	    gbcnt.insets  = new Insets(0,8,0,35);
	    String categorias[]= controller.getCategorias();
	    JComboBox CBcategorias = new JComboBox(categorias);
	    CBcategorias.setBackground(Color.white);
		gbcnt.gridx = 1;
	    gbcnt.gridy = 1;
	    gbcnt.gridwidth = 3;
	    panelCentro.add(CBcategorias,gbcnt);
	    
	    JFormattedTextField txtFechaInic  = new JFormattedTextField(formatFecha);
		txtFechaInic.setFont(defaultFont);
		Object textoOriginal = txtFechaInic.getValue();
		gbcnt.gridy = 2;
	    gbcnt.gridwidth = 1;
	    gbcnt.ipadx = 45;
	    panelCentro.add(txtFechaInic,gbcnt);
	    
	    String horas[]= {"8:00", "9:00","10:00","11:00","12:00","13:00","14:00","15:00"};
	    JComboBox CBHorasRec = new JComboBox(horas);
	    CBHorasRec.setBackground(Color.white);
	    gbcnt.gridy = 3;
	    panelCentro.add(CBHorasRec,gbcnt);
	    
	    
	    String sedes[]= controller.getSedes();
	    JComboBox CBsedes1 = new JComboBox(sedes);
	    CBsedes1.setBackground(Color.white);
	    gbcnt.gridy = 4;
	    panelCentro.add(CBsedes1,gbcnt);
	    
	    JFormattedTextField txtFechaFin  = new JFormattedTextField(formatFecha);
		txtFechaFin.setFont(defaultFont);
		gbcnt.gridx = 3;
	    gbcnt.gridy = 2;
	    panelCentro.add(txtFechaFin,gbcnt);
	    
	    JComboBox CBhorasSal = new JComboBox(horas);
	    CBhorasSal.setBackground(Color.white);
	    gbcnt.gridy = 3;
	    panelCentro.add(CBhorasSal,gbcnt);
	    
	    
	    JComboBox CBsedes = new JComboBox(sedes);
	    CBsedes.setBackground(Color.white);
	    gbcnt.gridy = 4;
	    panelCentro.add(CBsedes,gbcnt);
	      
	    String seguros[]= controller.getSeguros();
	    JComboBox CBseguro = new JComboBox(seguros);
	    CBseguro.setBackground(Color.white);
	    gbcnt.gridy = 5;
	    gbcnt.gridx = 1;
	    gbcnt.gridwidth = 4;
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
	    
	    JTextField txtNumeroLicenciaExtra = new JTextField("");
	    gbc.gridx = 1;
	    gbc.gridy= 1;
	    PregistroConductorExtra.add(txtNumeroLicenciaExtra, gbc);
	    
	    JTextField txtPaisExpExtra = new JTextField("");
	    gbc.gridy= 2;
	    PregistroConductorExtra.add(txtPaisExpExtra, gbc);
	    JFormattedTextField txtFechaExpExtra = new JFormattedTextField(formatFecha);
	    txtFechaExpExtra.setFont(defaultFont);
	    gbc.gridy= 3;
	    PregistroConductorExtra.add(txtFechaExpExtra, gbc);
	    
	    JButton bAgregarCondExtra = new JButton("Agregar conductor extra");
	    gbc.gridy= 0;
	    gbc.gridx = 3;
	    gbc.gridheight = 4;
	    gbc.fill = GridBagConstraints.VERTICAL;
	    PregistroConductorExtra.add(bAgregarCondExtra, gbc);
	    
	    gbcnt.gridy = 6;
	    gbcnt.gridx = 0;
	    gbcnt.gridwidth = 4;
	    gbcnt.insets = new Insets(15,0,0,0);
	    panelCentro.add(PregistroConductorExtra,gbcnt);
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
				//Arraylist<Object> datosAlquiler= new ArrayList<Object>;
				
				int categoria= CBcategorias.getSelectedIndex();
				String fechaInic= txtFechaInic.getText();
				String horaInic= (String) CBHorasRec.getSelectedItem();
				String fechafin= txtFechaFin.getText();
				String horaFin= (String) CBhorasSal.getSelectedItem();
				String sedeRecogida = (String) CBsedes1.getSelectedItem();
				String sedeEntrega = (String) CBsedes.getSelectedItem();
				int seguro= CBseguro.getSelectedIndex()-1;
				System.out.println(conductores);
				List listcond= (List)conductores;
				List datos2 = new ArrayList<>();
				try {
					datos2=controller.infoAlquiler(categoria, fechaInic, horaInic, fechafin, horaFin, sedeRecogida, sedeEntrega,seguro,listcond);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				if ((int)datos2.get(0)==0) {
					JOptionPane.showMessageDialog(bEnviar, "No se puede realizar el alquiler con esas especificaciones", "Alert", JOptionPane.WARNING_MESSAGE);
				}
				else {
					int total= (int)datos2.get(0);
					Alquiler alquiler= (Alquiler)datos2.get(1);
					Vehiculo vehiculoAlquiler=(Vehiculo)datos2.get(2);
					ventanaTotal(total,alquiler,vehiculoAlquiler,fechaInic,  fechafin,horaInic,horaFin);
					
				}
				txtFechaInic.setValue(textoOriginal);
				txtFechaFin.setValue(textoOriginal);
				
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
	}

	protected void volverAPanelAnterior() {
		PMenuCliente panelAnterior = new PMenuCliente(this.controller);
		this.removeAll();
		this.add(panelAnterior);
		this.revalidate();
		this.repaint();
		panelAnterior.setVisible(true);
	}
	
	private void ventanaTotal(int total, Alquiler alquiler, Vehiculo vehiculoAlquiler, String fechaInic, String fechafin, String horaInic, String horaFin) {
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
				try {
					idAlquiler = controller.realizarAlquiler(alquiler,vehiculoAlquiler,fechaInic,  fechafin,horaInic,horaFin);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String idalq= String.valueOf( idAlquiler);
				JOptionPane.showMessageDialog(null, "El id de su  alquiler es " + idalq , "Mensaje", JOptionPane.INFORMATION_MESSAGE);
				ventana1.dispose();
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
}
