package logica;

import java.util.ArrayList;

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
		
	public void setInformacionEmpleadoSede(Sede sede, Empleado empleado, String nombreEmpleado, String nuevoLogIn, String nuevaContraseña, Sede nuevaSede, boolean cambiarLogIn, boolean cambiarContraseña, boolean cambiarSede)
	{
			ArrayList<Empleado> listaEmpleados = sede.getEmpleadosSede();
			
			for (Empleado empleadoCambio: listaEmpleados) 
			{
				String nombre = empleadoCambio.getNombreCompleto();
				
				if(nombre.equals(nombreEmpleado))
				{
					if(cambiarLogIn)
					{
						empleadoCambio.setLogIn(nuevoLogIn);
					}
					else if(cambiarContraseña)
					{
						empleadoCambio.setContraseña(nuevaContraseña);
					}
					else if(cambiarSede)
					{
						empleadoCambio.setSede(nuevaSede);	
					}
				}		 
			}
	}
	
	public Sede getSede() {
		return sede;
	}

	public void setSede(Sede sede) {
		this.sede = sede;
	}
	
	
	
	
	
	
}
