package Presentacion;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

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
	
	
	public String[] getCategorias() {
		ArrayList<String> categorias = this.ferrari.getCategoriasStr();
		String[] Arr = categorias.toArray(new String[categorias.size()]);
		return Arr;
	}
	
	public String[] getSedes() {
		ArrayList<String> sedes = this.ferrari.getSedesStr();
		String[] Arr = sedes.toArray(new String[sedes.size()]);
		return Arr;
	}
	
	//métodos adminGeneral
	
	public String registrarNuevoCarro(String placa, String marca, String modelo, String color, String tipoTrans, String capacidad, String categoria,String sede) {
		ArrayList<String> data = new ArrayList<String>();
		data.add(placa);
		data.add(marca);
		data.add(modelo);
		data.add(color);
		data.add(tipoTrans);
		data.add(capacidad);
		data.add(categoria);
		data.add(sede);
		String placaNuevoCar = this.ferrari.registrarNuevoVehiculo(this.ferrari.adminGeneral, data);
		
		return placaNuevoCar;
	}
	
	public String darDeBajaCarro(String placa) {
		String estado = this.ferrari.darDeBajaVehiculo(this.ferrari.adminGeneral, placa);
		if(estado == "") {
			return "-";
		}
		return estado;
	}
	
	public String configurarNuevoSeguro(String nombre, String precio) {
		ArrayList<String> datosSeguro = new ArrayList<String>();
		datosSeguro.add(nombre);
		datosSeguro.add(precio);
		String nombreNewSeguro = this.ferrari.configurarSeguro(this.ferrari.adminGeneral, datosSeguro);
		return nombreNewSeguro;
	}
	
	public boolean hacerTranslado(String placa, String sedeDest) {
		boolean verificar = this.ferrari.realizarTranslado(this.ferrari.adminGeneral, placa, sedeDest);
		return verificar;
	}
}
