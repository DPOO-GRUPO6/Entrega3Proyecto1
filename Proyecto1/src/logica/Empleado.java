package logica;

public class Empleado extends Usuario{
	Sede sede;
	
	public Empleado(String logIn, String contraseña, String nombreCompleto, String tipoUsuario,Sede sede) {
		super(logIn, contraseña, nombreCompleto, tipoUsuario);
		this.sede = sede;
	}
	
	public Vehiculo entregarVehiculo(Vehiculo vehiculo, Cliente cliente, Sede sede)
	{
		return vehiculo;
	}
	
	public void recibirVehiculo(Vehiculo vehiculo, Cliente cliente, Sede sede)
	{
	
	}
	
	public void actualizarEstadoVehiculo(Vehiculo vehiculo)
	{
	
	}
	
	public boolean verificarTarjetaRetenidaCliente(Cliente cliente)
	{
		return false;
	}
	
	public boolean verificarDisponibilidadCategoria(Sede sede)
	{
		return false;
	}
	
	
	public Sede getSede() {
		return sede;
	}

	public void setSede(Sede sede) {
		this.sede = sede;
	}

}


