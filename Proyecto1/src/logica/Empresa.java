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
	static ArrayList<Seguro> seguros =  new ArrayList<Seguro>();
	static HashMap<Integer,Reserva> reservas = new HashMap<Integer,Reserva>();
	static HashMap<Integer,Alquiler> alquileres = new HashMap<Integer,Alquiler>();
	
	
	public static HashMap getReservas() {
		
		return reservas;
		
	}
	public static  List getSeguros() {
		
		return seguros;
		
	}
	public static  HashMap getAlquileres() {
		
		return alquileres;
		
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
				usuarios.add(adminGen);
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
			Estado estado = new Estado("Disponible",0,null,null);
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
			ArrayList<Object> retornar = new ArrayList<Object>();
			//System.out.println(datos1);
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
			retornar.add((Seguro)datos1.get(7));
			retornar.add(datos1.get(8));
			retornar.add(cliente);
			//System.out.println(retornar);
			
			return cliente.alquilarVehiculo(retornar);
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
	
	public boolean cambiarEstadoVehiculoDevolver(Empleado empleado, String placaVehiculo, Date fechaInicio, Date fechaFin, boolean mantenimiento)
	{
		for (Vehiculo vehiculo: vehiculos) 
		{
			String placa = vehiculo.getPlaca();
			Estado estado = vehiculo.getEstado();
			String estadoVehiculo = estado.getNombre();
			
			if(placaVehiculo.equals(placa) && estadoVehiculo.equals("Alquilado"))
			{
				empleado.cambiarEstadoVehiculoDevolver(vehiculo, fechaInicio, fechaFin, mantenimiento);
				return true;
			}
		}
		return false;
	}
	
	public boolean cambiarEstadoVehiculoDisponible(Empleado empleado, String placaVehiculo)
	{
		for (Vehiculo vehiculo: vehiculos) 
		{
			String placa = vehiculo.getPlaca();
			Estado estado = vehiculo.getEstado();
			String nombreEstado = estado.getNombre();
			
			if(placaVehiculo.equals(placa) && (nombreEstado.equals("Mantenimiento") || nombreEstado.equals("Limpieza")))
			{
				empleado.cambiarEstadoVehiculoDisponible(vehiculo);
				return true;
			}
			
		}
		return false;
	}
	
	/** Metodos 
	 * acciones admin local **/
	
	public boolean crearEmpleado(AdministradorLocal adminLocal, String logIg, String contraseña, String nombreCompleto, String tipoUsuario, String sede)
	{
		for(Empleado empleadoBuscar: empleados)
		{
			String nombreEmpleado = empleadoBuscar.getNombreCompleto();
			String logInEmpleado = empleadoBuscar.getLogIn();
		
			for (String sedeEmpleado: sedes.keySet())
			{
				if(sedeEmpleado.equals(sede) && (nombreCompleto.equals(nombreEmpleado) || logInEmpleado.equals(logIg)))
				{
					return false;
				}
				if(sedeEmpleado.equals(sede))
				{
					Sede sedeEp = sedes.get(sedeEmpleado);
					adminLocal.crearEmpleado(logIg, contraseña, nombreCompleto, tipoUsuario, sedeEp);
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean setInformacionEmpleadoSede(AdministradorLocal adminLocal, String nombreEmpleado, String nuevologIn, String nuevaContraseña, String nuevaSede, boolean cambiarLogIn, boolean cambiarContraseña, boolean cambiarSede)
	{		
		for(Empleado empleadoCambio: empleados)
		{
			String nombreEmpleadoCambio = empleadoCambio.getNombreCompleto();
			String logInEmpleadoCambio = empleadoCambio.getLogIn();
			String contraseñaEmpleadoCambio = empleadoCambio.getContraseña();
			
			Sede sedeEmpleadoCambio = empleadoCambio.getSede();
			String nombreSedeEmpleadoCambio = sedeEmpleadoCambio.getNombre();
			
			for (String sedeNueva: sedes.keySet())
			{
				if(cambiarSede)
				{
					if(sedeNueva.equals(nuevaSede) && nombreEmpleadoCambio.equals(nombreEmpleado) && !nuevaSede.equals(nombreSedeEmpleadoCambio))
					{
						Sede sedeCambio = sedes.get(sedeNueva);	
						adminLocal.setInformacionEmpleadoSede(sedeEmpleadoCambio, empleadoCambio, nombreEmpleado, nuevologIn, nuevaContraseña, sedeCambio, cambiarLogIn, cambiarContraseña, cambiarSede);
						return true;
					}
				}
				if(cambiarLogIn)
				{
					if(nombreEmpleadoCambio.equals(nombreEmpleado) && !nuevologIn.equals(logInEmpleadoCambio))
					{
						adminLocal.setInformacionEmpleadoSede(sedeEmpleadoCambio, empleadoCambio, nombreEmpleado, nuevologIn, nuevaContraseña, null, cambiarLogIn, cambiarContraseña, cambiarSede);
						return true;
					}
				}
				if(cambiarContraseña)
				{
					if(nombreEmpleadoCambio.equals(nombreEmpleado) && !nuevaContraseña.equals(contraseñaEmpleadoCambio))
					{
						adminLocal.setInformacionEmpleadoSede(sedeEmpleadoCambio, empleadoCambio, nombreEmpleado, nuevologIn, nuevaContraseña, null, cambiarLogIn, cambiarContraseña, cambiarSede);
						return true;
					}
				}
			}	
		}
		
		return false;
	}
	
	/** Metodos 
	 * acciones admin general **/
	
	
	public String registrarNuevoVehiculo(AdministradorGeneral adminGen,ArrayList<String> data) {
		Vehiculo newVehiculo = adminGen.registrarNuevoVehiculo(data, sedes, categorias);
		vehiculos.add(newVehiculo);
		return newVehiculo.getPlaca();
	}
	
	public String darDeBajaVehiculo(AdministradorGeneral adminGen, String placa) {
		ArrayList<Vehiculo> newVehiculos = adminGen.darDeBajaVehiculo(placa, vehiculos);
		this.vehiculos = newVehiculos;
		String estado = "";
		for(Vehiculo v: vehiculos) {
			if(v.getPlaca().equals(placa)){
				estado = v.getEstado().getNombre();
			}
		}
		return estado;
	}
	
	public String configurarSeguro(AdministradorGeneral adminGen,ArrayList<String> datosSeguro) {
		Seguro newSeguro = adminGen.configurarSeguro(datosSeguro.get(0), Integer.parseInt(datosSeguro.get(1)));
		seguros.add(newSeguro);
		return newSeguro.getNombre();
	}
	
	public boolean realizarTranslado(AdministradorGeneral adminGen,String placa,String sede) {
		Sede sedeDest = sedes.get(sede);
		ArrayList<Sede> newSedes = adminGen.realizarTranslado(placa, sedeDest, vehiculos);
		Sede newSede1 = newSedes.get(0);
		Sede newSede2 = newSedes.get(1);
		sedes.put(newSede1.getNombre(), newSede1);
		sedes.put(newSede2.getNombre(), newSede2);
		Vehiculo veh = newSede1.buscarVehiculoEspecifico(placa);
		Vehiculo veh2 = newSede2.buscarVehiculoEspecifico(placa);
		if(veh != null && veh2 == null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public Sede modificarSede(AdministradorGeneral adminGen,int opcion,String cambio,String sedeCambio) {
		Sede sedeMod = null;
		int posicionCambio = opcion -1;
		Sede sedeCamb = sedes.get(sedeCambio);
		if(posicionCambio != 4) {
			Sede nuevaSede = adminGen.modificarSede(posicionCambio, cambio, sedeCamb);
			sedes.put(nuevaSede.getNombre(), nuevaSede);
			sedeMod = nuevaSede;
		}
		else {
			AdministradorLocal newAdminLoc = null;
			for(AdministradorLocal adLoc: adminsLocales) {
				if(adLoc.getNombreCompleto().equals(cambio)) {
					newAdminLoc = adLoc;
				}
			}
			if(newAdminLoc == null) {
				for(Empleado emp: empleados) {
					if(emp.getNombreCompleto().equals(cambio)) {
						newAdminLoc = new AdministradorLocal(emp.getLogIn(),emp.getContraseña(),emp.getNombreCompleto(),"administradorLocal",emp.getSede());
					}
				}
			}
			if(newAdminLoc != null) {
				Sede nuevaSede = adminGen.modificarAdminLocalSede(cambio, sedeCamb, newAdminLoc);
				sedes.put(nuevaSede.getNombre(), nuevaSede);
				sedeMod= nuevaSede;
			}
		}
		return sedeMod;
	}
	/** Metodos 
	 * acciones crear nuevo cliente 
	 * @throws ParseException 
	 * @throws NumberFormatException **/
	
	public Cliente crearNuevoCliente(String nombre,String email,String telefono,String fechaNacimiento,
			String nacionalidad,String numeroLicencia,String paisLicencia,String fechaExpLicencia,String numeroTC,
			String fechaVen,String LogIn,String contrasenia) throws NumberFormatException, ParseException {
		String pattern = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern); 
		TarjetaCredito tc = new TarjetaCredito(Integer.parseInt(numeroTC),sdf.parse(fechaVen),false);
		Licencia l = new Licencia(Integer.parseInt(numeroLicencia),paisLicencia,sdf.parse(fechaExpLicencia));
		
		Cliente newCliente = new Cliente(LogIn, contrasenia, nombre, "cliente", email,Long.parseLong(telefono),
				sdf.parse(fechaNacimiento),nacionalidad,l,tc);
		clientes.add(newCliente);
		usuarios.add(newCliente);	
		return newCliente;
	}
	
	/** Metodos 
	 * auxiliares **/
	
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
	
	/** Metodos 
	 * reservas y alquiler **/
	
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
		    	  if (listaReservas.isEmpty()) {
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
		      if(siReserva) {
		      vehiculosdisponibles.add(vehiculo1);
		      }
		      
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
		
		Tarifa tarifa2= new Tarifa(categoria1, tarifaEstimada, abono1, valorExtraSede1,valorExtraConductor, valorExtraSeguro);
		Reserva reservaARealizar= new Reserva(cliente1, categoria1, sedeSalida, sedeLlegada, fechaSalida, fechaLlegada,tarifa2, abono1);
			ArrayList<Object> infoReserva= new ArrayList<Object>();
			infoReserva.add(reservaARealizar);
			infoReserva.add(vehiculoAReservar);
			return infoReserva;
	}
	public static Object realizarAlquiler(Cliente cliente1, Categoria categoria1, Sede sedeLlegada, Sede sedeSalida,
			Date fechaSalida, Date fechaLlegada, Object object, int i, Seguro seguroAlquiler, List listaConductores) throws ParseException {
		int valorExtraSeguro= seguroAlquiler.getCosto();
		int valorExtraSede1= 0;
		double valorExtraConductor1= listaConductores.size()*0.05;
		int valorExtraConductor= (int)valorExtraConductor1;
		
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
		int tarifaTotal= Tarifa.calcularTarifaTotal(tarifaEstimada,valorExtraSeguro );
		ArrayList<Vehiculo> vehiculoscategoria= new ArrayList<Vehiculo>();
	
		for (int s=0;s<vehiculos.size();s++) {
			Vehiculo vehiculoCat=vehiculos.get(s);
		      Categoria categoriaVehiculo= vehiculoCat.getCategoria();
		      if (categoriaVehiculo==categoria1) {
		    	  vehiculoscategoria.add(vehiculoCat);
		      }
		      
		    }
		Vehiculo vehiculoAAlquilar= null;
		ArrayList<Vehiculo> vehiculosdisponibles= new ArrayList<Vehiculo>();
		for (int n=0;n<vehiculoscategoria.size();n++) {		
		      Vehiculo vehiculo1= vehiculoscategoria.get(n);
		      Estado estadoVehiculo= vehiculo1.getEstado();
		      String estado= estadoVehiculo.getNombre();
		      List listaAlquileres= vehiculo1.getReservas();
		      Boolean  siAlquiler= false;
		      if (estado=="disponible") {
		    	  //System.out.println("lista 1"+listaAlquileres);
		    	  if (listaAlquileres.isEmpty()) {
		    		  //System.out.println("lista 2"+vehiculos);
		    	  //vehiculoAReservar= vehiculo1;
		    	  siAlquiler= true;
		    	  }
		    	  else{
		    		  for (int a=0;a<listaAlquileres.size();a++) {
			    		  Reserva reserva1= (Reserva)listaAlquileres.get(i);
			    		  Date inicialReserva= reserva1.getFechaSalida();
			    		  Date finalReserva= reserva1.getFechaLlegada();
			    		  if (inicialReserva.before(fechaSalida) && finalReserva.before(fechaLlegada)&&finalReserva.before(fechaSalida) ) {
			    			  siAlquiler= true;}
			              else if (inicialReserva.after(fechaSalida) && finalReserva.after(fechaLlegada)&&inicialReserva.after(fechaLlegada) ) {
			            	  siAlquiler= true;}
			    		  	}
		    	  	}
		    	  if(siAlquiler) {
				      vehiculosdisponibles.add(vehiculo1);
		    		  }
		    	  
		      }
		
	}
		for (int n=0;n<vehiculosdisponibles.size();n++) {
			Vehiculo vehiculo= vehiculosdisponibles.get(n);
			if(vehiculo.getSede()==sedeSalida) {
				vehiculoAAlquilar=vehiculo;
				break;
			}	
		}

		
		if (vehiculoAAlquilar==null) {
			if (vehiculosdisponibles.isEmpty()) {
				vehiculoAAlquilar=null;
			}
			else {
				vehiculoAAlquilar=vehiculosdisponibles.get(0);
				double valorExtraSede= tarifaTotal* 0.05;
				valorExtraSede1= (int)(valorExtraSede);
				tarifaTotal= tarifaTotal + valorExtraSede1;
			}
		}

		Tarifa tarifa2= new Tarifa(categoria1, tarifaTotal, 0, valorExtraSede1,valorExtraConductor, valorExtraSeguro);
		ArrayList<ConductorAdicional>conductoresAdicionales = new ArrayList<ConductorAdicional>();
		for (int r=0;r<listaConductores.size();r++) {

			List conductor= (List) listaConductores.get(r);
			String numLicencia1= (String)conductor.get(0);
			String paisLicencia= (String)conductor.get(1);
			int numLicencia= Integer.parseInt(numLicencia1);
			
			String vencimientoLicencia= (String)conductor.get(2);
			SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
			Date vencimientoLicencia1 = format1.parse(vencimientoLicencia);
			Licencia licenciaCondAdi= new Licencia(numLicencia, paisLicencia, vencimientoLicencia1);
			ConductorAdicional condAcional= new ConductorAdicional(licenciaCondAdi);
			conductoresAdicionales.add(condAcional);
			
			}
		Alquiler alquilerCliente= new Alquiler(cliente1, null, vehiculoAAlquilar, sedeSalida, sedeLlegada, tarifa2,seguroAlquiler, conductoresAdicionales);
		
		return alquilerCliente;
	
}
	public static Object realizarAlquilerReserva(int idReservaCliente, List seguroConductores) throws ParseException {
		// TODO Auto-generated method stub
		Seguro seguroAlquiler= (Seguro)seguroConductores.get(0);
		int valorExtraSeguro= seguroAlquiler.getCosto();
		int valorExtraSede1= 0;
		List listaConductores= (List)seguroConductores.get(1);
		double valorExtraConductor1= listaConductores.size()*0.05;
		int valorExtraConductor= (int)valorExtraConductor1;
		Reserva reservaAlquiler= reservas.get(idReservaCliente);
		Tarifa tarifaReserva= reservaAlquiler.getTarifaEstimada();
		int tarifaEstimada= tarifaReserva.getTarifa();
		int tarifaTotal= Tarifa.calcularTarifaTotal(tarifaEstimada,valorExtraSeguro );
		tarifaReserva.setTarifa(tarifaTotal);
		Vehiculo vehiculo= null;
		for (int t=0;t<vehiculos.size();t++) {
			Vehiculo vehiculo1= vehiculos.get(t);
			List listaReservas= (List)vehiculo1.getReservas();
			for (int h=0;h<listaReservas.size();h++) {
				Reserva reserva= (Reserva)listaReservas.get(h);
				if (reserva==reservaAlquiler) {
					vehiculo=vehiculo1;
				}
			}
		}
	
		ArrayList<ConductorAdicional>conductoresAdicionales = new ArrayList<ConductorAdicional>();
		for (int r=0;r<listaConductores.size();r++) {
			List conductor= (List) listaConductores.get(r);
			String numLicencia1= (String)conductor.get(0);
			String paisLicencia= (String)conductor.get(1);
			int numLicencia= Integer.parseInt(numLicencia1);
			
			String vencimientoLicencia= (String)conductor.get(2);
			SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
			Date vencimientoLicencia1 = format1.parse(vencimientoLicencia);
			Licencia licenciaCondAdi= new Licencia(numLicencia, paisLicencia, vencimientoLicencia1);
			ConductorAdicional condAcional= new ConductorAdicional(licenciaCondAdi);
			conductoresAdicionales.add(condAcional);
			}
		Alquiler alquilerCliente= new Alquiler(reservaAlquiler.getCliente(), reservaAlquiler, vehiculo, reservaAlquiler.getSedeLleagada(), reservaAlquiler.getSedeSalida(), tarifaReserva, seguroAlquiler, conductoresAdicionales);
		ArrayList<Object> todo =  new ArrayList<Object>();
		todo.add(alquilerCliente);
		todo.add(reservaAlquiler);	
		return todo;
	}
}
	
	

