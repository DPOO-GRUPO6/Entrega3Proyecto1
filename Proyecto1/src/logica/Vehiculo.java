package logica;

import java.util.ArrayList;
import java.util.List;

public class Vehiculo {
	String placa;
	String marca;
	String modelo;
	String color;
	String tipoTransmision;
	int capacidad;
	Categoria categoria; //crear clase categoria
	Sede sede;
	Estado estado;
	ArrayList<Reserva> reservasVehiculo;
	//Añadir reserva? para poder tener un carro para la reserva?
	


	public String getPlaca() {
		return placa;
	}

	public Vehiculo(String placa, String marca, String modelo, String color, String tipoTransmision, int capacidad,
			Categoria categoria, Sede sede, Estado estado, ArrayList<Reserva> reservasVehiculo) {
		super();
		this.placa = placa;
		this.marca = marca;
		this.modelo = modelo;
		this.color = color;
		this.tipoTransmision = tipoTransmision;
		this.capacidad = capacidad;
		this.categoria = categoria;
		this.sede = sede;
		this.estado = estado;
		this.reservasVehiculo = reservasVehiculo;
	}

	public Estado getEstado() {
		return estado;
	}
	public Categoria getCategoria() {
		return categoria;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Sede getSede() {
		return sede;
	}

	public void setSede(Sede sede) {
		this.sede = sede;
	}
	public List getReservas() {
		return reservasVehiculo;
	}

	
	
}
