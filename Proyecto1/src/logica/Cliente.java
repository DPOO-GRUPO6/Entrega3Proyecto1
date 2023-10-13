package logica;

import java.text.ParseException;
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
	
	public Reserva resevarVehiculo(ArrayList<Object> infoReserva) throws ParseException {
		//categoria, fecha recogida, hora recogida, fecha llegada, hora llegada, sede recogida, sede llegada
		//se ecrea reserva
		Cliente cliente1= (Cliente)infoReserva.get(5);
		Categoria categoria1= (Categoria)infoReserva.get(0);
		Sede sedeLlegada= (Sede)infoReserva.get(3);
		Sede sedeSalida= (Sede)infoReserva.get(4);
		Date fechaSalida= (Date)infoReserva.get(1);
		Date fechaLlegada= (Date)infoReserva.get(2);
		Reserva reservaCliente= Empresa.realizarReserva(cliente1,categoria1,sedeLlegada,sedeSalida,fechaSalida,fechaLlegada, null, 0);
		System.out.println(infoReserva.get(5));
		return reservaCliente;
		/*new Reserva(infoReserva.get(5), infoReserva.get(0), infoReserva.get(3), infoReserva.get(4), infoReserva.get(1), infoReserva.get(2), null, 0);
		 * Cliente cliente;
	Categoria categoria;
	Sede sedeSalida;
	Sede sedeLleagada;
	Date FechaSalida;
	//LocalTime horaSalida;
	Date FechaLlegada;
	//LocalTime horaLlegada;
	Tarifa tarifaEstimada;
	int abono;*/
	}
}
