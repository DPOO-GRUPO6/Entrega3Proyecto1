package logica;

import java.util.Date;

public class Reserva {
	Cliente cliente;
	Categoria categoria;
	Sede sedeSalida;
	Sede sedeLleagada;
	Date FechaSalida;
	Date FechaLlegada;
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

	public Sede getSedeSalida() {
		return sedeSalida;
	}

	public void setSedeSalida(Sede sedeSalida) {
		this.sedeSalida = sedeSalida;
	}

	public Sede getSedeLleagada() {
		return sedeLleagada;
	}

	public void setSedeLleagada(Sede sedeLleagada) {
		this.sedeLleagada = sedeLleagada;
	}

	public Date getFechaSalida() {
		return FechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		FechaSalida = fechaSalida;
	}

	public Date getFechaLlegada() {
		return FechaLlegada;
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

	public void setAbono(int abono) {
		this.abono = abono;
	}

	
	
		
}
