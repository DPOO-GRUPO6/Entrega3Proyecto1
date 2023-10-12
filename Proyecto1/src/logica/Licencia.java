package logica;

import java.util.Date;


public class Licencia {
	int numero;
	String paisExpedicion;
	Date fechaVencimiento;
	
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
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	public Licencia(int numero, String paisExpedicion, Date date) {
		super();
		this.numero = numero;
		this.paisExpedicion = paisExpedicion;
		this.fechaVencimiento = date;
	}
	
	
}
