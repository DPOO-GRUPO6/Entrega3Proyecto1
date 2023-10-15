package logica;

public class AdministradorLocal extends Usuario{
	Sede sede;
	
	public AdministradorLocal(String logIn, String contraseña, String nombreCompleto, String tipoUsuario,Sede sede) {
		super(logIn, contraseña, nombreCompleto, tipoUsuario);
		this.sede = sede;
	}
	
	public Empleado crearEmpleado(String logIg, String contraseña, String nombreCompleto, String tipoUsuario, Sede sede)
	{
		return new Empleado(logIg, contraseña, nombreCompleto, tipoUsuario, sede);
	}
	
	public void modificarInfoEmpleado(Empleado empleado, String nuevologIn, String nuevaContraseña, Sede nuevaSede)
	{
		empleado.setLogIn(nuevologIn);
		empleado.setContraseña(nuevaContraseña);
		empleado.setSede(nuevaSede);	
	}
	
	public Sede getSede() {
		return sede;
	}

	public void setSede(Sede sede) {
		this.sede = sede;
	}
	
	
	
	
	
	
}
