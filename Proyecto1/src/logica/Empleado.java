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
	
	public void cambiarEstadoVehiculo(Vehiculo vehiculo, Date fechaInicio, Date fechaFin)
	{
		Estado estado = vehiculo.getEstado();
		estado.setNombre("Reservado");
		estado.setFechaInicio(fechaInicio);
		estado.setFechaFin(fechaFin);
	}
	
	public void cambiarEstadoVehiculoEntrega(Sede sede,Vehiculo vehiculo, Date fechaInicio, Date fechaFin, boolean mantenimiento)
	{
		Estado estado = vehiculo.getEstado();
		
		if(mantenimiento) 
		{
			estado.setNombre("Mantenimiento");
			sede.removerVehiculoDeSede(vehiculo);
		}
		else
		{
			estado.setNombre("Limpieza");
		}

		estado.setFechaInicio(fechaInicio);
		estado.setFechaFin(fechaFin);
	}
	
	public boolean verificarTarjetaRetenidaCliente(Cliente cliente)
	{
		TarjetaCredito tarjetaCredito = cliente.getTarjetaCredito();
		return tarjetaCredito.getBloqueo();
	}
	
	public boolean verificarDisponibilidadCategoria(Sede sede, String categoriaDeseada)
	{
		ArrayList<Vehiculo> listaVehiculos = sede.getVehiculosSede();
		
		for (Vehiculo vehiculo: listaVehiculos) 
		{
			Categoria categoria = vehiculo.getCategoria();
			Estado estado = vehiculo.getEstado();
			
			String categoriaCarro = categoria.getNombre();
			String estadoCarro = estado.getNombre();
			
			if (categoriaDeseada.equals(categoriaCarro) && (!estadoCarro.equals("Reservado") || !estadoCarro.equals("Alquilado")))
			{
				return true;
			}
		}
		
		return false;
	}
	
	public Sede getSede() {
		return sede;
	}

	public void setSede(Sede sede) {
		this.sede = sede;
	}

}


