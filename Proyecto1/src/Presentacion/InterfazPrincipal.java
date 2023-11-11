package Presentacion;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

import logica.AdministradorGeneral;
import logica.AdministradorLocal;
import logica.Alquiler;
import logica.Categoria;
import logica.Cliente;
import logica.Empleado;
import logica.Empresa;
import logica.Reserva;
import logica.Sede;
import logica.TarjetaCredito;
import logica.Usuario;
import logica.Vehiculo;
import logica.Seguro;


public class InterfazPrincipal extends JFrame{
	 
	private MenuPrincipal menuPrincipal;
	public Controlador controller = new Controlador();
	
	
	public void ejecutar() throws ParseException{
		this.controller.cargarInformacion();
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize((int)size.getWidth()-450,(int)size.getHeight()-150);
		this.setTitle("Consecionario Ferrari");
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.menuPrincipal = new MenuPrincipal(controller);
		this.add(menuPrincipal);
	}

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		
	InterfazPrincipal app = new InterfazPrincipal();
	app.ejecutar();
	app.setVisible(true);

	}

}
