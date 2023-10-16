package logica;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
	static ArrayList<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
	ArrayList<Seguro> seguros =  new ArrayList<Seguro>();
	static HashMap<Integer,Reserva> reservas = new HashMap<Integer,Reserva>();
	
	public static HashMap getReservas() {
		
		return reservas;
		
	}
	
	
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
		
		ArrayList<String> lineasCat;
		lineasCat = LectorArchivo.leer("data/categorias.txt");
		for(String linea : lineasCat) {
			String []datos = linea.split(";");
			Categoria c = new Categoria(datos[0],Integer.parseInt(datos[1]));
			categorias.add(c);
		}
		
		ArrayList<String> lineasSeg;
		lineasSeg = LectorArchivo.leer("data/seguros.txt");
		for(String linea : lineasSeg) {
			String []datos = linea.split(";");
			Seguro seg = new Seguro(datos[0],Integer.parseInt(datos[1]));
			seguros.add(seg);
		}
		
		ArrayList<String> lineasVe;
		lineasVe = LectorArchivo.leer("data/vehiculos.txt");
		for(String linea : lineasVe) {
			String []datos = linea.split(";");
			Categoria categoria = null;
			for(Categoria c: categorias) {
				if(c.getNombre().equals(datos[6])) {
					categoria = c;
				}
			}
			Sede sed = sedes.get(datos[7]);
			Estado estado = new Estado("disponible",0,null,null);
			Vehiculo ve = new Vehiculo(datos[0],datos[1],datos[2],datos[3],datos[4],Integer.parseInt(datos[5]),categoria,sed,estado, new ArrayList<Reserva>());
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
	
	public Object accionesCliente(int op, ArrayList<Object> datos1,Cliente cliente) throws ParseException{
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
			return cliente.resevarVehiculo(retornar);
		}
		
		else {
			System.out.println("proceso de alquiler");
			return null;
		}
		
	}
	
	/** Metodos 
	 * acciones empleado **/
	
	
	public Vehiculo verificarDisponibilidadCategoria(Empleado empleado, String sede, String categoriaDeseada, AdministradorGeneral adminGen)
	{	
		for (String sedeCarro: sedes.keySet())
		{
			if(sede.equals(sedeCarro))
			{
				Sede sedeEp = sedes.get(sedeCarro);
				Vehiculo busqueda1 = empleado.verificarDisponibilidadCategoriaEnSede(sedeEp, categoriaDeseada);
				Vehiculo busqueda2 = empleado.verificarDisponibilidadCategoriaFueraSede(vehiculos, categoriaDeseada);
				
				if (busqueda1 instanceof Vehiculo)
				{
					return busqueda1;
				}
				else if (busqueda2 instanceof Vehiculo)
				{
					String placa = busqueda2.getPlaca();
					adminGen.realizarTranslado(placa, sedeEp, vehiculos);
					return busqueda2;
				}
			}
		}
		return null;
				
	}
	
	public void cambiarEstadoVehiculoAlquilado(Empleado empleado, String placaVehiculo, Date fechaInicio, Date fechaFin)
	{
		for (Vehiculo vehiculo: vehiculos) 
		{
			String placa = vehiculo.getPlaca();
			
			if(placaVehiculo.equals(placa))
			{
				empleado.cambiarEstadoVehiculoAlquilado(vehiculo, fechaInicio, fechaFin);
			}
		}
	}
	
	public void cambiarEstadoVehiculoDevolver(Empleado empleado, String placaVehiculo, Date fechaInicio, Date fechaFin, boolean mantenimiento)
	{
		for (Vehiculo vehiculo: vehiculos) 
		{
			String placa = vehiculo.getPlaca();
			
			if(placaVehiculo.equals(placa))
			{
				empleado.cambiarEstadoVehiculoDevolver(vehiculo, fechaInicio, fechaFin, mantenimiento);
			}
		}
	}
	
	public void cambiarEstadoVehiculoDisponible(Empleado empleado, String placaVehiculo)
	{
		for (Vehiculo vehiculo: vehiculos) 
		{
			String placa = vehiculo.getPlaca();
			
			if(placaVehiculo.equals(placa))
			{
				empleado.cambiarEstadoVehiculoDisponible(vehiculo);
			}
		}
	
	} 
	
	/** Metodos 
	 * acciones admin local **/
	
	/** Metodos 
	 * acciones admin general **/
	
	
	public void registrarNuevoVehiculo(AdministradorGeneral adminGen,ArrayList<String> data) {
		ArrayList<Vehiculo> newVehiculos = adminGen.registrarNuevoVehiculo(data, sedes, categorias, vehiculos);
		this.vehiculos = newVehiculos;
	}
	
	public void darDeBajaVehiculo(AdministradorGeneral adminGen, String placa) {
		ArrayList<Vehiculo> newVehiculos = adminGen.darDeBajaVehiculo(placa, vehiculos);
		this.vehiculos = newVehiculos;
	}
	
	public void configurarSeguro(AdministradorGeneral adminGen,ArrayList<String> datosSeguro) {
		ArrayList<Seguro> newSeguros = adminGen.configurarSeguro(datosSeguro.get(0), Integer.parseInt(datosSeguro.get(1)), seguros);
		this.seguros = newSeguros;
	}
	
	public void realizarTranslado(AdministradorGeneral adminGen,String placa,String sede) {
		Sede sedeDest = sedes.get(sede);
		adminGen.realizarTranslado(placa, sedeDest, vehiculos);
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
	public static Object realizarReserva(Cliente cliente1, Categoria categoria1, Sede sedeLlegada, Sede sedeSalida, Date fechaSalida, Date fechaLlegada, Tarifa tarifa1, int Abono) throws ParseException {
		//Reserva reservaCliente = new Reserva(infoReserva.get(5), infoReserva.get(0), infoReserva.get(3), infoReserva.get(4), infoReserva.get(1), infoReserva.get(2), null, 0);
		//long elapsedms = date1.getTime() - date2.getTime();
		int valorExtraSeguro= 0;
		int valorExtraSede1= 0;
		int valorExtraConductor= 0;
		
		String fecha1Alta= "01";
		String fecha2Alta= "06";
		String fecha1Baja= "07";
		String fecha2Baja= "12";

		SimpleDateFormat formato = new SimpleDateFormat("MM");

		Date fecha1 = formato.parse(fecha1Alta);
		Date fecha2 = formato.parse(fecha2Alta);
		Date fecha3 = formato.parse(fecha1Baja);
		Date fecha4 = formato.parse(fecha2Baja);
		long elapsedms = fechaLlegada.getTime() - fechaSalida.getTime();
        double diff3 = TimeUnit.MINUTES.convert(elapsedms, TimeUnit.MILLISECONDS);
        int diasCobrar= (int) Math.ceil(Math.ceil(diff3/60)/24);
		int tarifaDia= Tarifa.establecerTarifaPorDia(fecha1,fecha2,fecha3,fecha4,fechaSalida, categoria1);
		int tarifaEstimada= Tarifa.calcularTarifaEstimada(tarifaDia, diasCobrar);
		double abono=tarifaEstimada* 0.3;
		int abono1= (int)(abono);
		ArrayList<Vehiculo> vehiculoscategoria= new ArrayList<Vehiculo>();
		//System.out.println(vehiculos);
		for (int i=0;i<vehiculos.size();i++) {
			
		      Vehiculo vehiculo1= vehiculos.get(i);
		      Categoria categoriaVehiculo= vehiculo1.getCategoria();
		      if (categoriaVehiculo==categoria1) {
		    	  vehiculoscategoria.add(vehiculo1);
		      }
		      
		    }
		Vehiculo vehiculoAReservar= null;
		ArrayList<Vehiculo> vehiculosdisponibles= new ArrayList<Vehiculo>();
		for (int i=0;i<vehiculoscategoria.size();i++) {		
		      Vehiculo vehiculo1= vehiculoscategoria.get(i);
		      Estado estadoVehiculo= vehiculo1.getEstado();
		      String estado= estadoVehiculo.getNombre();
		      List listaReservas= vehiculo1.getReservas();
		      Boolean siReserva= false;
		      if (estado=="disponible") {
		    	  if (listaReservas==null) {
		    	  //vehiculoAReservar= vehiculo1;
		    	  siReserva= true;
		    	  }
		    	  else{
		    		  for (int a=0;a<listaReservas.size();a++) {
			    		  Reserva reserva1= (Reserva)listaReservas.get(i);
			    		  Date inicialReserva= reserva1.getFechaSalida();
			    		  Date finalReserva= reserva1.getFechaLlegada();
			    		  if (inicialReserva.before(fechaSalida) && finalReserva.before(fechaLlegada)&&finalReserva.before(fechaSalida) ) {
			    			  siReserva= true;}
			              else if (inicialReserva.after(fechaSalida) && finalReserva.after(fechaLlegada)&&inicialReserva.after(fechaLlegada) ) {
			            	   siReserva= true;}
			    		  	}
		    		  
		    	  	}
		    	  
		      }
		      else if (estado=="alquilado"){
		    	  
		    	  if (listaReservas==null) {
		    		  Date inicialAlquiler= estadoVehiculo.getFechaInicio();
		    		  Date finalAlquiler= estadoVehiculo.getFechaFin();
		    		  if (inicialAlquiler.before(fechaSalida) && finalAlquiler.before(fechaLlegada)&&finalAlquiler.before(fechaSalida) ) {
		    			  siReserva= true;}
		              else if (inicialAlquiler.after(fechaSalida) && finalAlquiler.after(fechaLlegada)&&inicialAlquiler.after(fechaLlegada) ) {
		            	   siReserva= true;}	  
		    	  }		    		  
		    	  else {
		    		  for (int a=0;a<listaReservas.size();a++) {
		    		  Reserva reserva1= (Reserva)listaReservas.get(i);
		    		  Date inicialReserva= reserva1.getFechaSalida();
		    		  Date finalReserva= reserva1.getFechaLlegada();
		    		  if (inicialReserva.before(fechaSalida) && finalReserva.before(fechaLlegada)&&finalReserva.before(fechaSalida) ) {
		    			  siReserva= true;}
		              else if (inicialReserva.after(fechaSalida) && finalReserva.after(fechaLlegada)&&inicialReserva.after(fechaLlegada) ) {
		            	   siReserva= true;}
		    		  	}
		    	  		}
		    	  
		      }
		      
		      
		      else if (estado=="limpieza" || estado=="mantenimiento"){
		    	  
		    	  if (listaReservas==null) {
		    		  Date inicialAlquiler= estadoVehiculo.getFechaInicio();
		    		  Date finalAlquiler= estadoVehiculo.getFechaFin();
		    		  if (inicialAlquiler.before(fechaSalida) && finalAlquiler.before(fechaLlegada)&&finalAlquiler.before(fechaSalida) ) {
		    			  siReserva= true;}
		              else if (inicialAlquiler.after(fechaSalida) && finalAlquiler.after(fechaLlegada)&&inicialAlquiler.after(fechaLlegada) ) {
		            	   siReserva= true;}	  
		    	  }		    		  
		    	  else {
		    		  for (int a=0;a<listaReservas.size();a++) {
		    		  Reserva reserva1= (Reserva)listaReservas.get(i);
		    		  Date inicialReserva= reserva1.getFechaSalida();
		    		  Date finalReserva= reserva1.getFechaLlegada();
		    		  if (inicialReserva.before(fechaSalida) && finalReserva.before(fechaLlegada)&&finalReserva.before(fechaSalida) ) {
		    			  siReserva= true;}
		              else if (inicialReserva.after(fechaSalida) && finalReserva.after(fechaLlegada)&&inicialReserva.after(fechaLlegada) ) {
		            	   siReserva= true;}
		    		  	}
		    	  		}
		    	  
		      }
		      if(siReserva);
		      vehiculosdisponibles.add(vehiculo1);
		      
		    }
		for (int n=0;n<vehiculosdisponibles.size();n++) {
			Vehiculo vehiculo= vehiculosdisponibles.get(n);
			if(vehiculo.getSede()==sedeSalida) {
				vehiculoAReservar=vehiculo;
				break;
			}	
		}
		
		if (vehiculoAReservar==null) {
			if (vehiculosdisponibles.isEmpty()) {
				vehiculoAReservar=null;
			}
			else {
				vehiculoAReservar=vehiculosdisponibles.get(0);
				double valorExtraSede= tarifaEstimada* 0.05;
				valorExtraSede1= (int)(valorExtraSede);
				tarifaEstimada= tarifaEstimada + valorExtraSede1;
				double abono2= tarifaEstimada*0.3;
				abono1= (int)abono2;
			}
		}
		System.out.println(abono1);
		Tarifa tarifa2= new Tarifa(categoria1, tarifaEstimada, abono1, valorExtraSede1,valorExtraConductor, valorExtraSeguro);
		Reserva reservaARealizar= new Reserva(cliente1, categoria1, sedeSalida, sedeLlegada, fechaSalida, fechaLlegada,tarifa2, abono1);
			ArrayList<Object> infoReserva= new ArrayList<Object>();
			infoReserva.add(reservaARealizar);
			infoReserva.add(vehiculoAReservar);
			return infoReserva;
	}
	
}
	
	

