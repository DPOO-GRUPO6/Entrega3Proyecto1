package logica;

import java.util.ArrayList;
import java.util.HashMap;

public class AdministradorGeneral extends Usuario{

	public AdministradorGeneral(String logIn, String contraseña, String nombreCompleto, String tipoUsuario) {
		super(logIn, contraseña, nombreCompleto, tipoUsuario);
	}
	
	public ArrayList<Vehiculo> darDeBajaVehiculo(String placa, ArrayList<Vehiculo> vehiculos) {
		for(Vehiculo v: vehiculos) {
			if(v.getPlaca().equals(placa)){
				v.getEstado().setNombre("desechado");
			}
		}
		return vehiculos;
	}
	
	public Vehiculo registrarNuevoVehiculo(ArrayList<String> data,HashMap<String,Sede>sedes,ArrayList<Categoria>categorias) {
		int capacidad = Integer.parseInt(data.get(5));
		Sede sede = sedes.get(data.get(7));
		Categoria categoria = null;
		for(Categoria c: categorias) {
			if(c.nombre.equals(data.get(5))) {
				categoria = c;
			}
		}
		Estado estado = new Estado("disponible",0,null,null);
		Vehiculo newVe = new Vehiculo(data.get(0),data.get(1),data.get(2),data.get(3), data.get(4),capacidad,categoria, sede, estado,new ArrayList<Reserva>());
		return newVe;
	}
	
	public Seguro configurarSeguro(String nombre,int precio){
		Seguro seg = new Seguro(nombre,precio);
		return seg;
		
	}
	
	
	public ArrayList<Sede> realizarTranslado(String placa, Sede sedeDest,ArrayList<Vehiculo> vehiculos) {
		ArrayList<Sede> newSedes = new ArrayList<Sede>();
		Vehiculo vehiculo = null;
		Sede sedeIn = null;
		for(Vehiculo v: vehiculos) {
			if(v.getPlaca().equals(placa)){
				vehiculo = v;
				sedeIn = vehiculo.getSede();
			}
		}
		if(vehiculo != null) {
		vehiculo.setSede(sedeDest);	
		}
		
		sedeDest.addVehiculoASede(vehiculo);
		newSedes.add(sedeDest);
		sedeIn.removerVehiculoDeSede(vehiculo);
		newSedes.add(sedeIn);
		
		return newSedes;
	}
	
	public Sede modificarSede(int posicion, String cambio,Sede sedeCambio) {
		if(posicion == 0) {
			sedeCambio.setNombre(cambio);
		}
		else if(posicion ==1) {
			sedeCambio.setDireccion(cambio);
		}
		else if(posicion == 2) {
			sedeCambio.setDiasAtencion(cambio);
		}
		else if(posicion == 3) {
			sedeCambio.setHorasAtencion(cambio);
		}
		return sedeCambio;
	}
	
	public Sede modificarAdminLocalSede(String cambio,Sede sedeCambio, AdministradorLocal adminLocal) {
		sedeCambio.setNombreAdminSede(cambio);
		sedeCambio.setAdminSede(adminLocal);
		return sedeCambio;
	}

}
