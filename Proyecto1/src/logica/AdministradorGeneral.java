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
	
	public ArrayList<Vehiculo> registrarNuevoVehiculo(ArrayList<String> data,HashMap<String,Sede>sedes,ArrayList<Categoria>categorias,ArrayList<Vehiculo>vehiculos) {
		int capacidad = Integer.parseInt(data.get(5));
		Sede sede = sedes.get(data.get(7));
		Categoria categoria = null;
		for(Categoria c: categorias) {
			if(c.nombre.equals(data.get(5))) {
				categoria = c;
			}
		}
		Estado estado = new Estado("disponible",0,null,null);
		Vehiculo newVe = new Vehiculo(data.get(0),data.get(1),data.get(2),data.get(3), data.get(4),capacidad,categoria, sede, estado, null);
		vehiculos.add(newVe);
		return vehiculos;
	}
	
	public ArrayList<Seguro> configurarSeguro(String nombre,int precio,ArrayList<Seguro> seguros){
		Seguro seg = new Seguro(nombre,precio);
		seguros.add(seg);
		return seguros;
		
	}
	
	
	public void realizarTranslado(String placa, Sede sedeDest,ArrayList<Vehiculo> vehiculos) {
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
		sedeIn.removerVehiculoDeSede(vehiculo);
	}

}
