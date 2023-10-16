package logica;

import java.util.ArrayList;
import java.util.Date;

public class Empleado extends Usuario{
	Sede sede;
	
	public Empleado(String logIn, String contraseña, String nombreCompleto, String tipoUsuario,Sede sede) 
	{
		super(logIn, contraseña, nombreCompleto, tipoUsuario);
		this.sede = sede;
	}
	
	public void cambiarEstadoVehiculoReserva(Vehiculo vehiculo, Date fechaInicio, Date fechaFin)
	{
		Estado estado = vehiculo.getEstado();
		String nombre = estado.getNombre();
		
		if (nombre.equals("Dispon	ible"))
		{
			estado.setNombre("Reservado");
			estado.setFechaInicio(fechaInicio);
			estado.setFechaFin(fechaFin);
		}
	}
	
	public void cambiarEstadoVehiculoAlquilado(Vehiculo vehiculo, Date fechaInicio, Date fechaFin)
	{
		Estado estado = vehiculo.getEstado();
		String nombre = estado.getNombre();
		
		if (nombre.equals("Disponible"))
		{
			estado.setNombre("Alquilado");
			estado.setFechaInicio(fechaInicio);
			estado.setFechaFin(fechaFin);
		}
	}
	
	public void cambiarEstadoVehiculoDevolver(Vehiculo vehiculo, Date fechaInicio, Date fechaFin, boolean mantenimiento)
	{
		Estado estado = vehiculo.getEstado();
		String nombre = estado.getNombre();
		
		if(mantenimiento && nombre.equals("Alquilado")) 
		{
			estado.setNombre("Mantenimiento");
		}
		else
		{
			estado.setNombre("Limpieza");
		}

		estado.setFechaInicio(fechaInicio);
		estado.setFechaFin(fechaFin);
	}
	
	public void cambiarEstadoVehiculoDisponible(Vehiculo vehiculo)
	{
		Estado estado = vehiculo.getEstado();
		String nombre = estado.getNombre();

		if (nombre.equals("Mantenimiento") || nombre.equals("Limpieza"))
		{
			estado.setNombre("Dispoible");
			estado.setFechaInicio(null);
			estado.setFechaFin(null);
		}
	}

	public boolean verificarTarjetaRetenidaCliente(Cliente cliente)
	{
		TarjetaCredito tarjetaCredito = cliente.getTarjetaCredito();
		return tarjetaCredito.getBloqueo();
	}
	
	public Vehiculo verificarDisponibilidadCategoriaEnSede(Sede sede, String categoriaDeseada)
	{
		ArrayList<Vehiculo> listaVehiculosSede = sede.getVehiculosSede();
		
		for (Vehiculo vehiculo: listaVehiculosSede) 
		{
			Categoria categoria = vehiculo.getCategoria();
			Estado estado = vehiculo.getEstado();
			
			String categoriaCarro = categoria.getNombre();
			String estadoCarro = estado.getNombre();
			
			if (categoriaDeseada.equals(categoriaCarro) && (!estadoCarro.equals("Reservado") || !estadoCarro.equals("Alquilado")))
			{
				return vehiculo;
			}
		}
		
		return null;
	}
	
	public Vehiculo verificarDisponibilidadCategoriaFueraSede(ArrayList<Vehiculo> vehiculos,String categoriaDeseada)
	{
		
		for (Vehiculo vehiculo: vehiculos) 
		{
			Categoria categoria = vehiculo.getCategoria();
			Estado estado = vehiculo.getEstado();
			
			String categoriaCarro = categoria.getNombre();
			String estadoCarro = estado.getNombre();
			
			if (categoriaDeseada.equals(categoriaCarro) && (!estadoCarro.equals("Reservado") || !estadoCarro.equals("Alquilado")))
			{
				return vehiculo;
			}
		}
		
		return null;
	}
	
	public Sede getSede() {
		return sede;
	}

	public void setSede(Sede sede) {
		this.sede = sede;
	}

}


