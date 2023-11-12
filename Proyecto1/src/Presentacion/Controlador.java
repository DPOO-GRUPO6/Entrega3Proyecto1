package Presentacion;

import java.text.ParseException;
import java.util.ArrayList;

import logica.Cliente;
import logica.Empresa;
import logica.Sede;
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
	//Registrar nuevo usuario - cliente
	
	public String crearNuevoCliente(String nombre, String email, String telefono, String fechaNacimiento,
			String nacionalidad, String numeroLicencia, String paisLicencia, String fechaExpLicencia, String numeroTC,
			String fechaExpTarjCred, String logIn, String contrasenia) {
		
		try {
			Cliente newCliente = ferrari.crearNuevoCliente(nombre, email, telefono, fechaNacimiento, nacionalidad, numeroLicencia, paisLicencia, fechaExpLicencia, numeroTC, fechaExpTarjCred, logIn, contrasenia);
			return newCliente.getNombreCompleto();
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return "error";
		} catch (ParseException e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	//metodos auxiliares
	
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
	
	public String modificarInfoSede(int opcionCambio, String cambio, String sede) {
		Sede sedeMod = ferrari.modificarSede(this.ferrari.adminGeneral, opcionCambio, cambio, sede);
		if(sedeMod != null) {
			return "Se ha modificado exitosamente la sede.\nInformación de la sede:\n"
					+ "Nombre:"+sedeMod.getNombre()
					+"\nDireccion: "+sedeMod.getDireccion()
					+"\nDias de atencion: "+sedeMod.getDiasAtencion()
					+"\nHoras de atencion: "+sedeMod.getHorasAtencion()
					+"\nAdministrador local: "+sedeMod.getNombreAdminSede();

		}
		else {
			return "Proceso fallido. No se pudo modificar la información de la sede";
	}
	}
}
