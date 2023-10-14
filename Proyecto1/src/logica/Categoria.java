package logica;

public class Categoria {
	String nombre;
	int costoPorDia;
	

	public Categoria(String nombre, int costoPorDia) {
		this.nombre = nombre;
		this.costoPorDia = costoPorDia;
	}

	public long getCostoPorDia() {
		return costoPorDia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	 
	
}
