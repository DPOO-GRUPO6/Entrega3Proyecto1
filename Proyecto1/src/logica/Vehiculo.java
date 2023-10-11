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
	
	public Vehiculo(String placa, String marca,String modelo, String color, String tipoTransmision, int capacidad, Categoria categoria,
			Sede sede) {
		super();
		this.placa = placa;
		this.marca = marca;
		this.modelo = modelo;
		this.color = color;
		this.tipoTransmision = tipoTransmision;
		this.capacidad = capacidad;
		this.categoria = categoria;
		this.sede = sede;
	}
	
	
}
