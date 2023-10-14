package logica;

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
	
	public Vehiculo(String placa, String marca,String modelo, String color, String tipoTransmision, int capacidad, Categoria categoria,
			Sede sede, Estado estado) {
		this.placa = placa;
		this.marca = marca;
		this.modelo = modelo;
		this.color = color;
		this.tipoTransmision = tipoTransmision;
		this.capacidad = capacidad;
		this.categoria = categoria;
		this.sede = sede;
		this.estado = estado;
	}

	public String getPlaca() {
		return placa;
	}

	public Estado getEstado() {
		return estado;
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

	
	
}
