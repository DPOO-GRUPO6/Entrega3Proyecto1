package Presentacion;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import logica.AdministradorLocal;
import logica.Empleado;
import logica.Empresa;
import logica.Usuario;

public class Controlador {
	Empresa ferrari = new Empresa();
	private Empleado usuarioEmpleado;
	private AdministradorLocal usuarioAdminLocal;
	
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
			this.usuarioEmpleado = (Empleado)rta;
			return 1;
		}
		else if(rta.getTipoUsuario().equals("administradorLocal")) {
			this.usuarioAdminLocal = (AdministradorLocal)rta;
			return 2;
		}
		else if(rta.getTipoUsuario().equals("administradorGeneral")) {
			return 3;
		}
		else {
			return -1;
		}
	}

	
//Acciones empleados 
	
	public boolean estadoVehiculoDisponible(String placa)
	{
		boolean cambioHecho = ferrari.cambiarEstadoVehiculoDisponible(usuarioEmpleado, placa);
		return cambioHecho;
	}
	
	public boolean estadoVehiculoDevuelto(String placa, String fechaInicio, String fechaFinal, Object estado)
	{
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date fechaI = null;
		Date fechaF = null;
		try 
		{
			fechaI = formato.parse(fechaInicio);
			fechaF = formato.parse(fechaFinal);
		} 
		catch (ParseException e) 
		{
			e.printStackTrace();
			return false; 
		}
		
		String es = (String)estado;
		boolean l;
		if(es.equals("Mantenimiento"))
		{
			l = true;
		}
		else
		{
			l = false;
		}
		
		boolean cambioHecho = ferrari.cambiarEstadoVehiculoDevolver(usuarioEmpleado, placa, fechaI, fechaF, l);
		return cambioHecho;
	}
	
//Acciones admin local
	
	public boolean crearEmpleado(String logIn, String nombre, String sede, String contrasena, String tipoUsuario)
	{
		boolean empleadoCreado = ferrari.crearEmpleado(usuarioAdminLocal, logIn, contrasena, nombre, tipoUsuario, sede);
		return empleadoCreado;
	}
	
	public boolean modificarInfoEmpleado(String nombreEmpleado, String nuevologIn, String nuevaContraseña, String nuevaSede, boolean cambiarLogIn, boolean cambiarContraseña, boolean cambiarSede)
	{
		boolean infoModificada = ferrari.setInformacionEmpleadoSede(usuarioAdminLocal, nombreEmpleado, nuevologIn, nuevaContraseña, nuevaSede, cambiarLogIn, cambiarContraseña, cambiarSede);
		return infoModificada;
	}
}
