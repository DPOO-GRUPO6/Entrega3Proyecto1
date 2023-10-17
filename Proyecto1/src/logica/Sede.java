package logica;

import java.util.ArrayList;

public class Sede {
	String nombre;
	String direccion;
	String diasAtencion;
	String horasAtencion;
	String nombreAdminSede;
	AdministradorLocal adminSede = new AdministradorLocal("","","","",null);
	ArrayList<Empleado> empleadosSede = new ArrayList<Empleado>();
	ArrayList<Vehiculo> vehiculosSede = new ArrayList<Vehiculo>();
	
	public Sede(String nombre, String direccion, String diasAtencion, String horasAtencion, String nombreAdminSede) {
		super();
		this.nombre = nombre;
		this.direccion = direccion;
		this.diasAtencion = diasAtencion;
		this.horasAtencion = horasAtencion;
		this.nombreAdminSede = nombreAdminSede;
	}

	public AdministradorLocal getAdminSede() {
		return adminSede;
	}

	public void setAdminSede(AdministradorLocal adminSede) {
		this.adminSede = adminSede;
	}

	public String getNombreAdminSede() {
		return nombreAdminSede;
	}

	public void setNombreAdminSede(String nombreAdminSede) {
		this.nombreAdminSede = nombreAdminSede;
	}

	public ArrayList<Empleado> getEmpleadosSede() {
		return empleadosSede;
	}
	
	public void addEmpleadoASede(Empleado emp) {
		empleadosSede.add(emp);
	}
	
	public void addVehiculoASede(Vehiculo ve) {
		vehiculosSede.add(ve);
	}

	public ArrayList<Vehiculo> getVehiculosSede() {
		return vehiculosSede;
	}
	
	public void removerVehiculoDeSede(Vehiculo vehiculo) {
		vehiculosSede.remove(vehiculo);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getDiasAtencion() {
		return diasAtencion;
	}

	public void setDiasAtencion(String diasAtencion) {
		this.diasAtencion = diasAtencion;
	}

	public String getHorasAtencion() {
		return horasAtencion;
	}

	public void setHorasAtencion(String horasAtencion) {
		this.horasAtencion = horasAtencion;
	}

	public void setEmpleadosSede(ArrayList<Empleado> empleadosSede) {
		this.empleadosSede = empleadosSede;
	}

	public void setVehiculosSede(ArrayList<Vehiculo> vehiculosSede) {
		this.vehiculosSede = vehiculosSede;
	}
	
	public Vehiculo buscarVehiculoEspecifico(String placa) {
		Vehiculo vehiculoBuscado = null;
		for(Vehiculo ve: vehiculosSede) {
			if(ve.getPlaca().equals(placa)) {
				vehiculoBuscado = ve;
			}
		}
		return vehiculoBuscado;
	}
	

	
}
