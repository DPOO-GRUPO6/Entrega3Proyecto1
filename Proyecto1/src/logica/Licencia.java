package logica;

import java.time.LocalDate;
//import java.util.Date;

public class Licencia {
	int numero;
	String paisExpedicion;
	LocalDate fechaVencimiento;
	
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getPaisExpedicion() {
		return paisExpedicion;
	}
	public void setPaisExpedicion(String paisExpedicion) {
		this.paisExpedicion = paisExpedicion;
	}
	public LocalDate getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(LocalDate fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	public Licencia(int numero, String paisExpedicion, LocalDate fechaVencimiento) {
		super();
		this.numero = numero;
		this.paisExpedicion = paisExpedicion;
		this.fechaVencimiento = fechaVencimiento;
	}
	
	
}
