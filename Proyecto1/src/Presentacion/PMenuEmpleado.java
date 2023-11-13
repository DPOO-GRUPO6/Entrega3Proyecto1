package Presentacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;


public class PMenuEmpleado extends JPanel{
	public Controlador controller;
	private JFormattedTextField txtPlacaDisp;
	private JFormattedTextField txtPlacaDevu;
	private JFormattedTextField txtFechaInic;
	private JFormattedTextField txtFechaFin;
	
	
	PMenuEmpleado(Controlador controlador){
		this.setLayout(new BorderLayout());
		this.controller = controlador;
		
		//panel cero -> menu principal
		JLabel lblBienvenido = new JLabel("Bienvenido", SwingConstants.CENTER);
		lblBienvenido.setFont(new Font(null, Font.BOLD, 45));
		this.add(lblBienvenido, BorderLayout.NORTH);
		
		JPanel panelCero = new JPanel();
		panelCero.setLayout(new GridBagLayout());
		GridBagConstraints gb = new GridBagConstraints();
		gb.insets = new Insets(5,0,30,0);
		JLabel lblInstrucc = new JLabel("Que desea hacer?", SwingConstants.CENTER);
		lblInstrucc.setFont(new Font(null, Font.PLAIN, 15));
		gb.gridx = 0;
		gb.fill = GridBagConstraints.HORIZONTAL;
		gb.gridy = 0;
		panelCero.add(lblInstrucc, gb);
		
		gb.insets = new Insets(20,0,30,0);
		JButton bCambiarDevuelto = new JButton("Cambiar el estado de un vehículo devuelto");
		bCambiarDevuelto.setFont(new Font(null, Font.PLAIN, 20));
		gb.gridy = 1;
		gb.ipady = 30;
		panelCero.add(bCambiarDevuelto, gb);
		bCambiarDevuelto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				goPanelVehiculoDevuelto();
			}
			
		});
		JButton bCambiarDisp = new JButton("Cambiar el estado de un vehículo a disponible");
		bCambiarDisp.setFont(new Font(null, Font.PLAIN, 20));
		gb.ipady = 30;
		gb.gridy = 3;
		panelCero.add(bCambiarDisp, gb);
		bCambiarDisp.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				doPanelVehiculoDisponible();
			}
			
		});
		//Boton Cerrar sesion
		this.add(panelCero, BorderLayout.CENTER);
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

	protected void cerrarSesion() {
		/*
		MenuPrincipal panelAnterior = new MenuPrincipal();
		this.removeAll();
		this.add(panelAnterior);
		this.revalidate();
		this.repaint();
		panelAnterior.setVisible(true);	
		*/	
	}

	//Panel Vehiculo Disponible
	protected void doPanelVehiculoDisponible() 
	{
		this.removeAll();
		JPanel pDisponible = new JPanel();
		pDisponible.setLayout(new GridBagLayout());
		GridBagConstraints gbcnt = new GridBagConstraints();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(5,0,30,0);
		JLabel lblDisp = new JLabel("Cambiar el estado de un vehículo disponible", SwingConstants.CENTER);
		lblDisp.setFont(new Font(null, Font.BOLD, 30));
		this.add(lblDisp, BorderLayout.NORTH);

		JLabel lblPlacaDisp= new JLabel("Ingrese la placa del vehículo a cambiar de estado", SwingConstants.CENTER);
		gbc.gridy = 1;
		gbc.insets = new Insets(8,5,8,5);
		lblPlacaDisp.setFont(new Font(null, Font.PLAIN, 20));
		pDisponible.add(lblPlacaDisp, gbc); 
		
		try 
		{
			MaskFormatter formatPlaca = new MaskFormatter("UUU-###");
			this.txtPlacaDisp = new JFormattedTextField(formatPlaca);
			txtPlacaDisp.setFont(new Font(null, Font.PLAIN, 20));
			txtPlacaDisp.setHorizontalAlignment(JTextField.CENTER);
			gbc.gridy = 2;
		    pDisponible.add(txtPlacaDisp,gbc);
		} 
		
		catch (ParseException e) 
		{
			e.printStackTrace(); 
		}
		
		//Boton cambiar estado disponible
		JButton bCambiarEstadoDisp = new JButton("Cambiar estado");
		bCambiarEstadoDisp.setFont(new Font(null, Font.PLAIN, 20));
		gbcnt.gridy = 6;
		gbcnt.gridx = 0;
		gbcnt.gridwidth = 2;
		gbcnt.insets = new Insets(20,5,147,5);
		gbcnt.ipady = 18;
		pDisponible.add(bCambiarEstadoDisp, gbcnt);
		bCambiarEstadoDisp.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				String placa = txtPlacaDisp.getText();
				if(cambiarEstadoDisponible(placa))
				{
					String mensaje = "El vehiculo con la placa " + placa + " ha cambiado su estado a Disponible";
					JOptionPane.showMessageDialog(bCambiarEstadoDisp, mensaje);
				}
				else
				{
					String mensaje = "Ha habido algun error ingrese la informacion nuevamente";
					JOptionPane.showMessageDialog(bCambiarEstadoDisp, mensaje);
				}
			}
				
		});
		
		//Boton devolver vehiculo disponible
		JPanel panelSur = new JPanel();
	    FlowLayout layoutPsur = new FlowLayout();
	    layoutPsur.setAlignment(FlowLayout.LEFT);
	    panelSur.setLayout(layoutPsur);
	    JButton bVolver = new JButton("Volver");
	    bVolver.setFont(new Font(null, Font.PLAIN, 25));
	    bVolver.addActionListener(new ActionListener() 
	     {
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				volverAPanelAnterior();
			}
	    	 
	     });
	     
	     panelSur.add(bVolver);
	    this.add(panelSur, BorderLayout.SOUTH);
		this.add(pDisponible);
		this.revalidate();
		this.repaint();
	}
	
	
	//Accion de devolver
	protected void volverAPanelAnterior() {
		PMenuEmpleado panelAnterior = new PMenuEmpleado(this.controller);
		this.removeAll();
		this.add(panelAnterior);
		this.revalidate();
		this.repaint();
		panelAnterior.setVisible(true);
	}
	
	//Panel Vehiculo devuelto 
	protected void goPanelVehiculoDevuelto() {
		this.removeAll();
		JPanel pDevuelto = new JPanel();
		pDevuelto.setLayout(new GridBagLayout());
		
		GridBagConstraints gbcnt = new GridBagConstraints();
		gbcnt.gridx = 0;
		gbcnt.gridy = 0;
		gbcnt.gridwidth = 2;
		gbcnt.fill = GridBagConstraints.HORIZONTAL;
		gbcnt.insets = new Insets(5,0,30,0);
		JLabel lblDevuelto = new JLabel("Cambiar el estado de un vehículo devuelto", SwingConstants.CENTER);
		lblDevuelto.setFont(new Font(null, Font.BOLD, 30));
		this.add(lblDevuelto, BorderLayout.NORTH);
		
		JLabel lblPlaca= new JLabel("Ingrese la placa del vehículo a cambiar de estado");
		gbcnt.gridy = 1;
		gbcnt.insets = new Insets(8,5,8,5);
		lblPlaca.setFont(new Font(null, Font.PLAIN, 15));
		pDevuelto.add(lblPlaca, gbcnt);
		
		try {
			MaskFormatter formatPlaca = new MaskFormatter("UUU-###");
			this.txtPlacaDevu = new JFormattedTextField(formatPlaca);
			gbcnt.gridy = 2;
			txtPlacaDevu.setFont(new Font(null, Font.PLAIN, 15));
			txtPlacaDevu.setHorizontalAlignment(JTextField.CENTER);
			pDevuelto.add(txtPlacaDevu, gbcnt);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		JLabel bMantenimiento = new JLabel("Mandar a ", SwingConstants.RIGHT);
		bMantenimiento.setFont(new Font(null, Font.PLAIN, 15));
		gbcnt.gridy = 3;
		gbcnt.gridwidth = 1;
		pDevuelto.add(bMantenimiento, gbcnt);
		
		String opciones[] = {"Mantenimiento", "Limpieza"};
		JComboBox JBopciones = new JComboBox(opciones);
		JBopciones.setBackground(Color.white);
		gbcnt.gridy = 3;
		gbcnt.gridx = 1;
		gbcnt.gridwidth = 1;
		pDevuelto.add(JBopciones, gbcnt);
		
		JLabel lblFechaInic= new JLabel("Ingrese la fecha de inicio", SwingConstants.RIGHT);
		gbcnt.gridy = 4;
		gbcnt.gridx = 0;
		lblFechaInic.setFont(new Font(null, Font.PLAIN, 15));
		pDevuelto.add(lblFechaInic, gbcnt);
		
		try {
			MaskFormatter formatFecha = new MaskFormatter("##/##/####");
			this.txtFechaInic = new JFormattedTextField(formatFecha);
			gbcnt.gridx = 1;
			txtFechaInic.setFont(new Font(null, Font.PLAIN, 15));
			pDevuelto.add(txtFechaInic, gbcnt);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		JLabel lblFechaFin= new JLabel("Ingrese la fecha de salida", SwingConstants.RIGHT);
		gbcnt.gridy = 5;
		gbcnt.gridx = 0;
		lblFechaFin.setFont(new Font(null, Font.PLAIN, 15));
		pDevuelto.add(lblFechaFin, gbcnt);
		
		try {
			MaskFormatter formatFecha = new MaskFormatter("##/##/####");
			this.txtFechaFin = new JFormattedTextField(formatFecha);
			gbcnt.gridx = 1;
			txtFechaFin.setFont(new Font(null, Font.PLAIN, 15));
			pDevuelto.add(txtFechaFin, gbcnt);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		JButton bCambiarEstadoDev = new JButton("Cambiar estado");
		bCambiarEstadoDev.setFont(new Font(null, Font.PLAIN, 18));
		gbcnt.gridy = 6;
		gbcnt.gridx = 0;
		gbcnt.gridwidth = 2;
		gbcnt.insets = new Insets(20,5,20,5);
		gbcnt.ipady = 18;
		pDevuelto.add(bCambiarEstadoDev, gbcnt);
		bCambiarEstadoDev.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				String placa = txtPlacaDevu.getText();
				Object estado = JBopciones.getSelectedItem();
				String fechaInicio = txtFechaInic.getText(); 
				String fechaFinal = txtFechaFin.getText();
				if(cambiarEstadoDevuelto(placa, fechaInicio, fechaFinal, estado))
				{
					String mensaje = "El vehiculo con la placa " + placa + " ha cambiado su estado a " + estado;
					JOptionPane.showMessageDialog(bCambiarEstadoDev, mensaje);
				}
				else
				{
					String mensaje = "Ha habido algun error ingrese la informacion nuevamente";
					JOptionPane.showMessageDialog(bCambiarEstadoDev, mensaje);
				}
			}
		});

		
		//Logica de boton devolver
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
		
		this.add(pDevuelto);
		this.revalidate();
		this.repaint();
	}
	
	//Mandar la info al controlador para cambiar el estado a disponible
	protected boolean cambiarEstadoDisponible(String placa)
	{
		boolean cambioHecho = this.controller.estadoVehiculoDisponible(placa);
		return cambioHecho; 
	}
	
	//Mandar la info al controlador para cambiar el estado a devuelto
	protected boolean cambiarEstadoDevuelto(String placa, String fechaInicio, String fechaFinal, Object estado)
	{
		boolean cambioHecho = this.controller.estadoVehiculoDevuelto(placa, fechaInicio, fechaFinal, estado);
		return cambioHecho; 
	}
	
}
