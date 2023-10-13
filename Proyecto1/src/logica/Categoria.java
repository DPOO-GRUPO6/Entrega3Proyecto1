package logica;

public class Categoria {
	String nombre;
	long costoPorDia = 65000;
	
	public Categoria(String nombre) {
		this.nombre = nombre;
		
	}

	public long getCostoPorDia() {
		return costoPorDia;
	}
	 
	
}
