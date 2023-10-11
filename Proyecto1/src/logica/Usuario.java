package logica;

public abstract class Usuario {
	String logIn;
	String contraseña;
	String nombreCompleto;
	String tipoUsuario;
	
	public String getLogIn() {
		return logIn;
	}
	public void setLogIn(String logIn) {
		this.logIn = logIn;
	}
	public String getContraseña() {
		return contraseña;
	}
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	public String getTipoUsuario() {
		return tipoUsuario;
	}
	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	public Usuario(String logIn, String contraseña, String nombreCompleto, String tipoUsuario) {
		super();
		this.logIn = logIn;
		this.contraseña = contraseña;
		this.nombreCompleto = nombreCompleto;
		this.tipoUsuario = tipoUsuario;
	}

	
	
	
}
