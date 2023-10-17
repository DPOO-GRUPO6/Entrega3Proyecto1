package logica;

import java.util.ArrayList;

public class Alquiler {
	
	Cliente cliente;
	Reserva reserva;
	Vehiculo vehiculo;
	Sede sedeLlegada;
	Sede sedeSalida;
	Tarifa tarifa;
	Seguro seguros;
	ArrayList<ConductorAdicional> conductoresAdicionales = new ArrayList<ConductorAdicional>();
	
	public Alquiler(Cliente cliente, Reserva reserva, Vehiculo vehiculo, Sede sedeLlegada, Sede sedeSalida,
			Tarifa tarifa, Seguro seguros, ArrayList<ConductorAdicional> conductoresAdicionales) {
		super();
		this.cliente = cliente;
		this.reserva = reserva;
		this.vehiculo = vehiculo;
		this.sedeLlegada = sedeLlegada;
		this.sedeSalida = sedeSalida;
		this.tarifa = tarifa;
		this.seguros = seguros;
		this.conductoresAdicionales = conductoresAdicionales;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public Vehiculo getVehiculo() {
		return vehiculo;
	}
	public int getTarifaPagar() {
		return tarifa.getTarifa();
	}
}
