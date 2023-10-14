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

	
}
