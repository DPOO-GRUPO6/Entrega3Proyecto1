package Presentacion;

import java.text.ParseException;

import logica.Empresa;
import logica.Usuario;

public class Controlador {
	Empresa ferrari = new Empresa();
	
	public void cargarInformacion() throws ParseException {
		this.ferrari.cargarInformacion();
	}
	
	public int inicarSesion(String usuario, String pwd){
		Usuario rta = ferrari.iniciarSesion(usuario, pwd);
		// 0 > cliente
		// 1 > empleado
		// 2 > adminlocal
		// 3 > admin local
		// -1 > null
		if(rta == null) {
			return -1;
		}
		else if(rta.getTipoUsuario().equals("cliente")) {
			return 0;
		}
		else if(rta.getTipoUsuario().equals("empleado")) {
			return 1;
		}
		else if(rta.getTipoUsuario().equals("administradorLocal")) {
			return 2;
		}
		else if(rta.getTipoUsuario().equals("administradorGeneral")) {
			return 3;
		}
		else {
			return -1;
		}
	}
	
	
}
