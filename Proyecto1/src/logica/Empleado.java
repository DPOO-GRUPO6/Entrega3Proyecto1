package logica;

public class Empleado extends Usuario{
	Sede sede;
	
	public Empleado(String logIn, String contraseña, String nombreCompleto, String tipoUsuario,Sede sede) {
		super(logIn, contraseña, nombreCompleto, tipoUsuario);
		this.sede = sede;
	}

}
