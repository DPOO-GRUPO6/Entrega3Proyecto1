package logica;

import java.time.LocalDate;
//import java.util.Date;

public class TarjetaCredito {
	
	int numero;
	LocalDate fechaVencimiento;
	boolean bloqueo;
	
	public int getNumero() {
		return numero;
	}
	public LocalDate getFechaVencimiento() {
		return fechaVencimiento;
	}
	public boolean isBloqueo() {
		return bloqueo;
	}
	public void setBloqueo(boolean bloqueo) {
		this.bloqueo = bloqueo;
	}
	public TarjetaCredito(int numero, LocalDate fechaVencimiento, boolean bloqueo) {
		super();
		this.numero = numero;
		this.fechaVencimiento = fechaVencimiento;
		this.bloqueo = bloqueo;
	}

}
