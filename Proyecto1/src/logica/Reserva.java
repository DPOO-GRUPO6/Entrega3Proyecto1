package logica;

import java.util.Date;

public class Reserva {
	Cliente cliente;
	Categoria categoria;
	Sede sedeSalida;
	Sede sedeLleagada;
	Date FechaSalida;
	//LocalTime horaSalida;
	Date FechaLlegada;
	//LocalTime horaLlegada;
	Tarifa tarifaEstimada;
	int abono;
		
	public Reserva(Cliente cliente, Categoria categoria, Sede sedeSalida, Sede sedeLleagada, Date fechaSalida,
			Date fechaLlegada, Tarifa tarifaEstimada, int abono) {
		this.cliente = cliente;
		this.categoria = categoria;
		this.sedeSalida = sedeSalida;
		this.sedeLleagada = sedeLleagada;
		FechaSalida = fechaSalida;
		FechaLlegada = fechaLlegada;
		this.tarifaEstimada = tarifaEstimada;
		this.abono = abono;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public void setSedeSalida(Sede sedeSalida) {
		this.sedeSalida = sedeSalida;
	}
	public void setSedeLleagada(Sede sedeLleagada) {
		this.sedeLleagada = sedeLleagada;
	}
	public void setFechaSalida(Date fechaSalida) {
		FechaSalida = fechaSalida;
	}
	public void setFechaLlegada(Date fechaLlegada) {
		FechaLlegada = fechaLlegada;
	}
	public Tarifa getTarifaEstimada() {
		return tarifaEstimada;
	}
	public void setTarifaEstimada(Tarifa tarifaEstimada) {
		this.tarifaEstimada = tarifaEstimada;
	}
	public int getAbono() {
		return abono;
	}
	
	
		
}
