package logica;

import java.util.Date;

public class TarjetaCredito {
	
	int numero;
	Date fechaVencimiento;
	boolean bloqueo;
	
	public int getNumero() {
		return numero;
	}
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}
	public boolean isBloqueo() {
		return bloqueo;
	}
	public void setBloqueo(boolean bloqueo) {
		this.bloqueo = bloqueo;
	}
	public TarjetaCredito(int numero, Date date, boolean bloqueo) {
		super();
		this.numero = numero;
		this.fechaVencimiento = date;
		this.bloqueo = bloqueo;
	}

}
