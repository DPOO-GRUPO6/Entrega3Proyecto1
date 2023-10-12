package logica;

import java.util.ArrayList;
import java.util.Date;

public class Cliente extends Usuario{
	String email;
	long telefono;
	Date fechaNacimiento;
	String nacionalidad;
	Licencia licencia;
	TarjetaCredito tarjetaCredito;
	
	public Cliente(String logIn, String contraseña, String nombreCompleto, String tipoUsuario,String email,
			long telefono,Date date,String nacionalidad,Licencia licencia, TarjetaCredito tarjetaCredito) {
		super(logIn, contraseña, nombreCompleto, tipoUsuario);
		this.email = email;
		this.telefono = telefono;
		this.fechaNacimiento = date;
		this.nacionalidad = nacionalidad;
		this.licencia = licencia;
		this.tarjetaCredito = tarjetaCredito;
		
	}
	
	public void resevarVehiculo(ArrayList<Object> infoReserva) {
		//categoria, fecha recogida, hora recogida, fecha llegada, hora llegada, sede recogida, sede llegada
		//se ecrea reserva
		
		System.out.println(infoReserva);
	}
}
