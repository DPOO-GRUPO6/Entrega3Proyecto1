package logica;
//coemntario
//import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

import persistencia.LectorArchivo;

public class Empresa {
	
	HashMap<String,Sede> sedes = new HashMap<String,Sede>();
	ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	ArrayList<Empleado> empleados = new ArrayList<Empleado>();
	ArrayList<AdministradorLocal> adminsLocales = new ArrayList<AdministradorLocal>();
	AdministradorGeneral adminGeneral = new AdministradorGeneral("","","","");
	ArrayList<Categoria> categorias = new ArrayList<Categoria>();
	ArrayList<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
	
	
	public void cargarInformacion() {
		
		ArrayList<String> lineasSed;
		lineasSed = LectorArchivo.leer("data/sedes.txt");
		for(String linea : lineasSed) {
			String []datos = linea.split(";");
			Sede s = new Sede(datos[0],datos[1],datos[2],datos[3],datos[4]);
			sedes.put(datos[0], s);
		
		}

		ArrayList<String> lineasC;
		lineasC = LectorArchivo.leer("data/clientes.txt");
		for(String linea : lineasC) {
			String []datos = linea.split(";");
			DateTimeFormatter df = DateTimeFormatter .ofPattern("dd/MM/yyyy");
			Licencia l = new Licencia(Integer.parseInt(datos[7]),datos[8],LocalDate.parse(datos[9],df));
			TarjetaCredito t = new TarjetaCredito(Integer.parseInt(datos[10]),LocalDate.parse(datos[11],df),false);

			Cliente u = new Cliente(datos[0],datos[1],datos[2],"cliente",datos[3], Long.parseLong(datos[4]), LocalDate.parse(datos[5],df),datos[6],l,t);
			usuarios.add(u);
			clientes.add(u);
		}

		ArrayList<String> lineasEmp;
		lineasEmp = LectorArchivo.leer("data/empleados.txt");
		for(String linea : lineasEmp) {
			String []datos = linea.split(";");
			Sede s = sedes.get(datos[3]);
			if(datos[4].equals("empleado")) {
				Empleado e = new Empleado(datos[0],datos[1],datos[2],datos[4],s);
				s.addEmpleadoASede(e);
				empleados.add(e);
				usuarios.add(e);
			}
			else if(datos[4].equals("administradorLocal")) {
				AdministradorLocal ad = new AdministradorLocal(datos[0],datos[1],datos[2],datos[4],s);
				adminsLocales.add(ad);
				usuarios.add(ad);
				s.setNombreAdminSede(datos[2]);
				s.setAdminSede(ad);	
			}
				
			else if(datos[4].equals("administradorGeneral")) {
				AdministradorGeneral adminGen = new AdministradorGeneral(datos[0],datos[1],datos[2],datos[4]);
				this.adminGeneral = adminGen;
			}
		}
		
		ArrayList<String> lineasVe;
		lineasVe = LectorArchivo.leer("data/vehiculos.txt");
		for(String linea : lineasVe) {
			String []datos = linea.split(";");
			Categoria c = new Categoria(datos[6]);
			categorias.add(c);
			Sede sed = sedes.get(datos[7]);
			Vehiculo ve = new Vehiculo(datos[0],datos[1],datos[2],datos[3],datos[4],Integer.parseInt(datos[5]),c,sed);
			vehiculos.add(ve);
			sed.addVehiculoASede(ve);
		}
		
	}
	public Usuario iniciarSesion(String usuario, String pwd) {
		Usuario rta = null;
		for(Usuario u: usuarios) {
			if(u.logIn.equals(usuario)){
				if(u.contraseña.equals(pwd)) {
					rta = u;
					}
				}
			}
		
	return rta;
	}
	
	public void accionesCliente(int op, ArrayList<Object> datos1,Cliente cliente){
		//acciones cliente
		if(op == 1) {
			System.out.println("proceso de reserva");
			//les estoy haciendo el cast para que al cliente ya le legue la inf que necesita en el formato que es
			Categoria c = categorias.get((int)datos1.get(0)-1);
			DateTimeFormatter df = DateTimeFormatter .ofPattern("dd/MM/yyyy");
			LocalDate fechaInic = LocalDate.parse((CharSequence) datos1.get(1),df);
			//hora entrega
			LocalDate fechaFin = LocalDate.parse((CharSequence) datos1.get(3),df);
			//hora llegada
			Sede sedeInic = sedes.get(datos1.get(5));
			Sede sedeFin = sedes.get(datos1.get(6));
			System.out.println(c.nombre);
			System.out.println(fechaInic);
			System.out.println(fechaFin);
			System.out.println(sedeInic);
			System.out.println(sedeFin);
			cliente.resevarVehiculo(datos1);
		}
		
		else {
			System.out.println("proceso de alquiler");
		}
		
	}
	
	public void accionesEmpleado(){
		//acciones empleado
		System.out.println("todo ok2");
	}
	
	public ArrayList<String> getCategoriasStr() {
		ArrayList<String> categs = new ArrayList<String>();
		for(Categoria c: categorias) {
			categs.add(c.nombre);
		}
		return categs;
	}
	
	public ArrayList<String> getSedesStr() {
		ArrayList<String> seds = new ArrayList<String>();
		for(Sede s: sedes.values()) {
			seds.add(s.nombre);
		}
		return seds;
	}
	
	
}
