package logica;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class Cliente extends Usuario{
	String email;
	long telefono;
	Date fechaNacimiento;
	String nacionalidad;
	Licencia licencia;
	TarjetaCredito tarjetaCredito;
	
	public TarjetaCredito getTarjetaCredito() {
		return tarjetaCredito;
	}

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
	
	public Object resevarVehiculo(ArrayList<Object> infoReserva) throws ParseException {
		Cliente cliente1= (Cliente)infoReserva.get(5);
		Categoria categoria1= (Categoria)infoReserva.get(0);
		Sede sedeLlegada= (Sede)infoReserva.get(3);
		Sede sedeSalida= (Sede)infoReserva.get(4);
		Date fechaSalida= (Date)infoReserva.get(1);
		Date fechaLlegada= (Date)infoReserva.get(2);
		Object reservaCliente= Empresa.realizarReserva(cliente1,categoria1,sedeLlegada,sedeSalida,fechaSalida,fechaLlegada, null, 0);
		return reservaCliente;

	}
	
	public Object alquilarVehiculo(ArrayList<Object> infoAlquiler) throws ParseException {
		Cliente cliente1= (Cliente)infoAlquiler.get(7);
		Categoria categoria1= (Categoria)infoAlquiler.get(0);
		Sede sedeLlegada= (Sede)infoAlquiler.get(3);
		Sede sedeSalida= (Sede)infoAlquiler.get(4);
		Date fechaSalida= (Date)infoAlquiler.get(1);
		Date fechaLlegada= (Date)infoAlquiler.get(2);
		Seguro seguroAlquiler= (Seguro)infoAlquiler.get(5);
		List listaConductores= (List)infoAlquiler.get(6);
		
		Object alquilerCliente= Empresa.realizarAlquiler(cliente1,categoria1,sedeLlegada,sedeSalida,fechaSalida,fechaLlegada, null, 0,seguroAlquiler, listaConductores);
		return alquilerCliente;
	}

}
