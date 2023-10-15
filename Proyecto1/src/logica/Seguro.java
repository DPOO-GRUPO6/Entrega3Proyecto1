package logica;

public class Seguro {
	String nombre;
	int costo;
	
	
	public Seguro(String nombre, int costo) {
		this.nombre = nombre;
		this.costo = costo;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public int getCosto() {
		return costo;
	}


	public void setCosto(int costo) {
		this.costo = costo;
	}
	
	
	
}
