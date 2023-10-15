package logica;

public class AdministradorLocal extends Usuario{
	Sede sede;
	
	public AdministradorLocal(String logIn, String contraseña, String nombreCompleto, String tipoUsuario,Sede sede) {
		super(logIn, contraseña, nombreCompleto, tipoUsuario);
		this.sede = sede;
		
	}

	public Sede getSede() {
		return sede;
	}

	public void setSede(Sede sede) {
		this.sede = sede;
	}
	
	
	
	
	
	
	
}
