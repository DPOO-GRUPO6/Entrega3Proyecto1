package logica;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import logica.Tarifa;

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
	
	
	public void cargarInformacion() throws ParseException {
		
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
			String pattern = "dd/MM/yyyy";
	        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
	        Date date = sdf.parse(datos[9]);
			Licencia l = new Licencia(Integer.parseInt(datos[7]),datos[8],date);
			TarjetaCredito t = new TarjetaCredito(Integer.parseInt(datos[10]),sdf.parse(datos[11]),false);

			Cliente u = new Cliente(datos[0],datos[1],datos[2],"cliente",datos[3], Long.parseLong(datos[4]), sdf.parse(datos[5]),datos[6],l,t);
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
	
	public void accionesCliente(int op, ArrayList<Object> datos1,Cliente cliente) throws ParseException{
		//acciones cliente
		if(op == 1) {
			System.out.println("proceso de reserva");
			//les estoy haciendo el cast para que al cliente ya le legue la inf que necesita en el formato que es
			ArrayList<Object> retornar = new ArrayList<Object>();
			Categoria c = categorias.get((int)datos1.get(0)-1);
			retornar.add(c);
			String pattern = "dd/MM/yyyy HH:mm";
			String horaI = (String) datos1.get(2);
			String horaF = (String) datos1.get(4);
	        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
	        Date fechaInic = sdf.parse((String)datos1.get(1)+ " "+horaI);
	        retornar.add(fechaInic);
	        Date fechaFin = sdf.parse((String)datos1.get(3)+ " "+horaF);
	        retornar.add(fechaFin);
			Sede sedeInic = sedes.get(datos1.get(5));
			retornar.add(sedeInic);
			Sede sedeFin = sedes.get(datos1.get(6));
			retornar.add(sedeFin);
			retornar.add(cliente);
			
			System.out.println(retornar);
			cliente.resevarVehiculo(retornar);
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
	public static Reserva realizarReserva(Cliente cliente1, Categoria categoria1, Sede sedeLlegada, Sede sedeSalida, Date fechaSalida, Date fechaLlegada, Tarifa tarifa1, int Abono) throws ParseException {
		//Reserva reservaCliente = new Reserva(infoReserva.get(5), infoReserva.get(0), infoReserva.get(3), infoReserva.get(4), infoReserva.get(1), infoReserva.get(2), null, 0);
		//long elapsedms = date1.getTime() - date2.getTime();
		String fecha1Alta= "01";
		String fecha2Alta= "06";
		String fecha1Baja= "07";
		String fecha2Baja= "12";

		SimpleDateFormat formato = new SimpleDateFormat("MM");

		Date fecha1 = formato.parse(fecha1Alta);
		Date fecha2 = formato.parse(fecha2Alta);
		Date fecha3 = formato.parse(fecha1Baja);
		Date fecha4 = formato.parse(fecha2Baja);
		
		long elapsedms = fechaSalida.getTime() - fechaLlegada.getTime();
        double diff3 = TimeUnit.MINUTES.convert(elapsedms, TimeUnit.MILLISECONDS);
        int diasCobrar= (int) Math.ceil(Math.ceil(diff3/60)/24);
		int tarifaDia= Tarifa.establecerTarifaPorDia(fecha1,fecha2,fecha3,fecha4,fechaSalida, categoria1);
		int tarifaEstimada= Tarifa.calcularTarifaEstimada(tarifaDia, diasCobrar);
		
		Tarifa tarifa2= new Tarifa(categoria1, Abono, Abono, Abono);
		
		return null;
	}
	
}
	
	

