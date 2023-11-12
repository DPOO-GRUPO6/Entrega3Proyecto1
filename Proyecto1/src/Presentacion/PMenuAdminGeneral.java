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
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;

public class PMenuAdminGeneral extends JPanel{
	public Controlador controller;
	private PModificarSede panelModfSede;
	private PRegistroCarro panelRegistroCarro;
	private JFormattedTextField txtPlacaCarroTrans;
	public String sedes[];
	
	PMenuAdminGeneral(Controlador controller){
		this.controller = controller;
		this.sedes = this.controller.getSedes();
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
		bRegistrarVehiculo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				goPanelNewCarro();
			}
			
		});
		
		gbcnt.gridy = 2;
		JButton bDardeBajaVeh = new JButton("Dar de baja un vehículo");
		bDardeBajaVeh.setFont(defaultFont);
		panelCentro.add(bDardeBajaVeh,gbcnt);
		bDardeBajaVeh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				darDeBajaVentana();
			}
			
		});
		
		gbcnt.gridy = 3;
		JButton bConfigurarSeguro = new JButton("Configurar seguro");
		bConfigurarSeguro.setFont(defaultFont);
		panelCentro.add(bConfigurarSeguro,gbcnt);
		bConfigurarSeguro.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
					goPanelConfigurarSeguro();
			}
			
		});
		
		gbcnt.gridy = 4;
		JButton bTransladoIn = new JButton("Realizar translado interno");
		bTransladoIn.setFont(defaultFont);
		panelCentro.add(bTransladoIn,gbcnt);
		bTransladoIn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
					goPanelTransladoInterno();
			}
			
		});
		
		
		gbcnt.gridy = 5;
		JButton bCambiarInfoSede = new JButton("Modificar la información de una sede");
		bCambiarInfoSede.setFont(defaultFont);
		panelCentro.add(bCambiarInfoSede,gbcnt);
		bCambiarInfoSede.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
					goPanelModificarSede();
			}
			
		});

		
		this.add(panelCentro, BorderLayout.CENTER);
		
		JPanel panelSur = new JPanel();
	     FlowLayout layoutPsur = new FlowLayout();
	     layoutPsur.setAlignment(FlowLayout.LEFT);
	     panelSur.setLayout(layoutPsur);
	     JButton bVolver = new JButton("Cerrar sesión");
	     bVolver.setFont(new Font(null, Font.PLAIN, 20));
	     
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


	protected void darDeBajaVentana() {
		String placaCarroBaja = JOptionPane.showInputDialog(this, "Ingrese la placa del vehiculo a dar de baja","AAA-000");
		if(placaCarroBaja != null) {
			String nuevoEstado = this.controller.darDeBajaCarro(placaCarroBaja);
			if(nuevoEstado != "-") {
				JOptionPane.showMessageDialog(null, "El vehiculo se ha cambiado al estado: "+nuevoEstado);
			}
			else if(nuevoEstado == "-") {
				JOptionPane.showMessageDialog(this,"No se encontró un vehiculo con la placa "+placaCarroBaja,"Alert",JOptionPane.WARNING_MESSAGE);
			}
		}
		
	}

	protected void goPanelNewCarro() {
		this.removeAll();
		this.panelRegistroCarro = new PRegistroCarro(this.controller);
		this.add(panelRegistroCarro);
		this.panelRegistroCarro.setVisible(true);
		this.revalidate();
		this.repaint();
		
	}

	protected void goPanelModificarSede() {
		this.removeAll();
		this.panelModfSede = new PModificarSede(this.controller);
		this.add(panelModfSede);
		this.panelModfSede.setVisible(true);
		this.revalidate();
		this.repaint();
	}
	
	protected void goPanelConfigurarSeguro() {
		this.removeAll();
		this.setLayout(new BorderLayout());
		JLabel lblTitulo = new JLabel("Configurar seguro", SwingConstants.CENTER);
		lblTitulo.setFont(new Font(null, Font.BOLD, 45));
		this.add(lblTitulo, BorderLayout.NORTH);
		
		JPanel panelCentro = new JPanel();
		GridBagLayout gbl = new GridBagLayout();
		panelCentro.setLayout(gbl);
		GridBagConstraints gbcnt = new GridBagConstraints();
		Font defaultFont = new Font(null, Font.PLAIN,15);
		
		JLabel lblInstruccion = new JLabel("Ingrese la siguiente información",SwingConstants.CENTER);
		lblInstruccion.setFont(new Font(null, Font.PLAIN,15));
		gbcnt.gridx = 0;
	    gbcnt.gridy = 0;
	    gbcnt.ipady = 10;
	    gbcnt.ipadx = 95;
	    gbcnt.fill = GridBagConstraints.HORIZONTAL;
	    gbcnt.insets  = new Insets(0,0,45,0);
	    panelCentro.add(lblInstruccion,gbcnt);
	    
	    JLabel lblNombreSeguro = new JLabel("Nombre del seguro", SwingConstants.CENTER);
	    gbcnt.insets  = new Insets(5,0,5,0);
	    gbcnt.gridwidth = 1;
	    lblNombreSeguro.setFont(defaultFont);
		gbcnt.gridx = 0;
	    gbcnt.gridy = 1;
	    panelCentro.add(lblNombreSeguro,gbcnt);
	    
	    JTextField txtNombreSeguro = new JTextField("Nombre del seguro", SwingConstants.CENTER);
	    txtNombreSeguro.setFont(defaultFont);
	    gbcnt.gridy = 2;
	    panelCentro.add(txtNombreSeguro,gbcnt);
	    
	    JLabel lblPrecioSeguro = new JLabel("Ingrese el precio del seguro", SwingConstants.CENTER);
	    gbcnt.insets  = new Insets(30,0,5,0);
	    gbcnt.gridwidth = 1;
	    lblPrecioSeguro.setFont(defaultFont);
	    gbcnt.gridy = 3;
	    panelCentro.add(lblPrecioSeguro,gbcnt);
	    
	    JTextField txtPrecioSeguro = new JTextField("precio seguro", SwingConstants.CENTER);
	    txtPrecioSeguro.setFont(defaultFont);
	    gbcnt.insets  = new Insets(5,0,5,0);
	    gbcnt.gridy = 4;
	    panelCentro.add(txtPrecioSeguro,gbcnt);
	    
	    JButton bRegistrarSeguro = new JButton("Registrar nuevo seguro");
	    bRegistrarSeguro.setFont(defaultFont);
	    gbcnt.gridy = 5;
	    gbcnt.insets  = new Insets(40,0,5,0);
	    panelCentro.add(bRegistrarSeguro,gbcnt);
	    
	    bRegistrarSeguro.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String nombreSeguro = txtNombreSeguro.getText();
				String precioSeguro = txtPrecioSeguro.getText();
				try {
					  Integer.parseInt(precioSeguro);
					  agregarNuevoSeguro(nombreSeguro, precioSeguro);
					}
					catch(Exception ex) {
						JOptionPane.showMessageDialog(null,"Error. No se pudo registrar el nuevo seguro","Alert",JOptionPane.WARNING_MESSAGE);
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
	    this.revalidate();
		this.repaint();
	}
	
	protected void agregarNuevoSeguro(String nombreSeguro, String precioSeguro) {
		String confirma = this.controller.configurarNuevoSeguro(nombreSeguro, precioSeguro);
		JOptionPane.showMessageDialog(null, "El seguro "+confirma+" ha sido creado con éxito");
	}


	protected void volverAPanelAnterior() {
		PMenuAdminGeneral panelAnterior = new PMenuAdminGeneral(this.controller);
		this.removeAll();
		this.add(panelAnterior);
		this.revalidate();
		this.repaint();
		panelAnterior.setVisible(true);
	}


	protected void goPanelTransladoInterno() {
		this.removeAll();
		this.setLayout(new BorderLayout());
		JLabel lblTitulo = new JLabel("Realizar translado interno", SwingConstants.CENTER);
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
	    gbcnt.ipady = 10;
	    gbcnt.ipadx = 95;
	    gbcnt.fill = GridBagConstraints.HORIZONTAL;
	    gbcnt.insets  = new Insets(0,0,45,0);
	    panelCentro.add(lblInstruccion,gbcnt);
	    
	    JLabel lblPlacaCarroTrans = new JLabel("Placa del vehículo a transladar", SwingConstants.CENTER);
	    gbcnt.insets  = new Insets(5,0,5,0);
	    gbcnt.gridwidth = 1;
	    lblPlacaCarroTrans.setFont(defaultFont);
		gbcnt.gridx = 0;
	    gbcnt.gridy = 1;
	    panelCentro.add(lblPlacaCarroTrans,gbcnt);
	    
	    
	    try {
			MaskFormatter formatPlaca = new MaskFormatter("UUU-###");
			 this.txtPlacaCarroTrans  = new JFormattedTextField(formatPlaca);
			 this.txtPlacaCarroTrans.setFont(defaultFont);
			 this.txtPlacaCarroTrans.setHorizontalAlignment(SwingConstants.CENTER);
			 gbcnt.gridy = 2;
			 panelCentro.add(this.txtPlacaCarroTrans,gbcnt);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	    
	    JLabel lblSedeDest = new JLabel("Ingrese la sede de destino", SwingConstants.CENTER);
	    gbcnt.insets  = new Insets(30,0,5,0);
	    gbcnt.gridwidth = 1;
	    lblSedeDest.setFont(defaultFont);
	    gbcnt.gridy = 3;
	    panelCentro.add(lblSedeDest,gbcnt);
	    
	    JComboBox jcbSedes = new JComboBox(this.sedes);
	    jcbSedes.setFont(defaultFont);
	    jcbSedes.setBackground(Color.white);
	    gbcnt.insets  = new Insets(5,0,5,0);
	    gbcnt.gridy = 4;
	    panelCentro.add(jcbSedes,gbcnt);
	    
	    JButton bRegistrarTranslado = new JButton("Realizar translado");
	    bRegistrarTranslado.setFont(defaultFont);
	    gbcnt.gridy = 5;
	    gbcnt.insets  = new Insets(40,0,5,0);
	    panelCentro.add(bRegistrarTranslado,gbcnt);
	    
	    bRegistrarTranslado.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String placa = txtPlacaCarroTrans.getText();
				String sede = (String)jcbSedes.getSelectedItem();
				hacerTranslado(placa, sede);
				txtPlacaCarroTrans.setText("");
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
	    this.revalidate();
		this.repaint();
	}


	protected void hacerTranslado(String placa, String sede) {
		boolean result = this.controller.hacerTranslado(placa, sede);
		if(result) {
			JOptionPane.showMessageDialog(null, "El translado ha sido realizado con éxito");
		}
		else {
			JOptionPane.showMessageDialog(this,"Proceso de translado fallido","Alert",JOptionPane.WARNING_MESSAGE);
		}
	}
}
