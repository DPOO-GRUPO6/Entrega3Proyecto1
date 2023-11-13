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

import logica.Reserva;
import logica.Vehiculo;

public class PReservarCarro extends JPanel{
	public Controlador controller;

	public PReservarCarro(Controlador controller){
		this.controller=controller;
		// TODO Auto-generated constructor stub
	    MaskFormatter formatFecha = null;
		try {
			formatFecha = new MaskFormatter("##/##/####");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setLayout(new BorderLayout());
		JLabel lblTitulo = new JLabel("Reservar vehículo", SwingConstants.CENTER);
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
	    gbcnt.insets  = new Insets(0,0,50,0);
	    panelCentro.add(lblInstruccion,gbcnt);
	    
	    JLabel lblCategoria = new JLabel("Seleccione la categoría", SwingConstants.RIGHT);
	    gbcnt.insets  = new Insets(10,5,10,2);
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
		Object textoOriginal = txtFechaInic.getValue();
	    txtFechaInic.setFont(defaultFont);
	    gbcnt.gridy = 2;
	    gbcnt.gridwidth = 1;
	    gbcnt.ipadx = 45;
	    panelCentro.add(txtFechaInic,gbcnt);
	    	   
	    String horas[]= {"07:00","08:00", "09:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00"};
	    JComboBox CBhorasRec = new JComboBox(horas);
	    CBhorasRec.setBackground(Color.white);
	    gbcnt.gridy = 3;
	    panelCentro.add(CBhorasRec,gbcnt);
	    
	    
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
	      
	    
	    JButton bEnviar = new JButton("Reservar vehiculo");
	    bEnviar.setFont(new Font(null, Font.BOLD,18));
	    gbcnt.gridx = 0;
	    gbcnt.gridy = 7;
	    gbcnt.gridwidth = 4;
	    gbcnt.ipady = 10;
	    gbcnt.insets  = new Insets(50,10,10,10);
	    gbcnt.fill = GridBagConstraints.HORIZONTAL;
	    panelCentro.add(bEnviar,gbcnt);
	    bEnviar.addActionListener(new ActionListener() {
	    @Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int categoria= CBcategorias.getSelectedIndex();
				
				String fechaInic= txtFechaInic.getText();
				String horaInic= (String) CBhorasRec.getSelectedItem();
				String fechafin= txtFechaFin.getText();
				String horaFin= (String) CBhorasSal.getSelectedItem();
				String sedeRecogida = (String) CBsedes1.getSelectedItem();
				String sedeEntrega = (String) CBsedes.getSelectedItem();
				List datos2 = new ArrayList<>();
				try {
					datos2 = (List)controller.infoReserva(categoria,fechaInic,horaInic,fechafin,horaFin,sedeRecogida,sedeEntrega);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				if ((int)datos2.get(0)==1) {
					String resp= (String)datos2.get(1);
					JOptionPane.showMessageDialog(bEnviar, resp, "Alert", JOptionPane.WARNING_MESSAGE);
				}
				else if ((int)datos2.get(0)==2) {
					int abono= (int)datos2.get(1);
					Reserva reserva= (Reserva)datos2.get(2);
					Vehiculo vehiculoReserva= (Vehiculo)datos2.get(3);
					ventanaAbono(abono,reserva,vehiculoReserva);
	
					txtFechaInic.setValue(textoOriginal);
					txtFechaFin.setValue(textoOriginal);
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
	     
	}

	protected void volverAPanelAnterior() {
		PMenuCliente panelAnterior = new PMenuCliente(controller);
		this.removeAll();
		this.add(panelAnterior);
		this.revalidate();
		this.repaint();
		panelAnterior.setVisible(true);
	}
	private void ventanaAbono(int abono, Reserva reserva, Vehiculo vehiculoReserva) {
		JFrame ventana1 = new JFrame("Aceptar abono");
		String resp= "Para realizar su reserva debe realizar el pago del abono el cual es el 30%. El cual es de "+ String.valueOf(abono) + "¿Acepta el pago?";
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
				int idReserva= controller.realizarReserva(reserva,vehiculoReserva);
				String idreserv= String.valueOf(idReserva);
				JOptionPane.showMessageDialog(null, "El id de su  reserva es " + idreserv + " cuando realice alquiler ingreselo", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
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
