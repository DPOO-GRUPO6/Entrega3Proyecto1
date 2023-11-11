package Presentacion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logica.AdministradorGeneral;
import logica.AdministradorLocal;
import logica.Alquiler;
import logica.Categoria;
import logica.Cliente;
import logica.Empleado;
import logica.Empresa;
import logica.Reserva;
import logica.Sede;
import logica.TarjetaCredito;
import logica.Usuario;
import logica.Vehiculo;
import logica.Seguro;

public class principal {
	Empresa ferrari = new Empresa();
	
	public void ejecutar() throws ParseException {
		int idReserva=0;
		int idAlquiler=0;
		boolean continuar = true;
		ferrari.cargarInformacion();
		
		
		while(continuar) {
			System.out.println("\n--- MENU PRINCIPAL ---");
			System.out.println("\n1. Iniciar sesión\n2. Registrarse\n3. Salir \n");
			int opcion = Integer.parseInt(input("Ingrese su opción"));
			

			if (opcion == 1){
				String usuario = input("\nIngrese su usuario");
				String pwd = input("Ingrese su contrseña");
				Usuario rta = ferrari.iniciarSesion(usuario, pwd);
				if(rta == null ) {
					System.out.println("Usuario no encontrado o contraseña incorrecta");
				}
				else{
					System.out.println("\nIngreso exitoso\n");
					System.out.println("\nBienvenido "+rta.getNombreCompleto());
					if(rta.getTipoUsuario().equals("cliente")) {
						Cliente client = (Cliente)rta;
						System.out.println(rta);
						boolean cont = true;
						while(cont) {
						System.out.println("\nQue desea hacer?\n1.Reservar vehiculo\n2.Alquilar vehiculo\n3.Cerrar sesión");
						int op = Integer.parseInt(input("\nSeleccione su opcion"));
						if(op == 1) {
							ArrayList<Object> datos1 = registroPrimerosDatos();
							List listaReserva= (List) ferrari.accionesCliente(1,datos1,client);
							Reserva reserva= (Reserva)listaReserva.get(0);
							int abono= reserva.getAbono();
							Vehiculo vehiculoReserva= (Vehiculo) listaReserva.get(1);
							if (vehiculoReserva==null) {
								System.out.println("No se puede realizar esta reserva con esas especificaciones");
							}
							else {
								Cliente cliente1= reserva.getCliente();
								TarjetaCredito tarjetaCliente= cliente1.getTarjetaCredito();
								boolean bloqueo= tarjetaCliente.getBloqueo();
								if (bloqueo)
								{
									System.out.println("No se puede realizar esta reserva porque la tarjeta se encuentra bloqueada");
								}
								else {
									System.out.println("Para realizar su reserva debe realizar el pago del abono el cual es el 30%");
									System.out.println("El cual es de "+ abono);
									System.out.println("¿Acepta el pago?");
									System.out.println("\n1. Si \n2. No");
									int opcionElegida = Integer.parseInt(input("\nSeleccione su opcion"));
									if (opcionElegida==1) {
										Map reservasEmpresa=Empresa.getReservas();
										idReserva+=1;
										reservasEmpresa.put(idReserva, reserva);
										List reservasCarro= vehiculoReserva.getReservas();
										reservasCarro.add(reserva);
										System.out.println("El id de su  reserva es " + idReserva + " cuando realice alquiler ingreselo");		
									}
								}
								
							}
							//System.out.println(reservaCliente.getTarifaEstimada());
							//stem.out.println(reservaCliente.getAbono());
						}
						else if(op == 2) {
							System.out.println("¿Tiene alguna reserva?");
							System.out.println("\n1. Si \n2. No");
							int opcionElegida = Integer.parseInt(input("\nSeleccione su opcion"));
							if (opcionElegida==1) {
								int idReservaCliente = Integer.parseInt(input("\n Que numero es su reserva"));
								System.out.println("¿Quiere cambiar su reserva?");
								System.out.println("\n1. Si \n2. No");
								int opcionElegida2 = Integer.parseInt(input("\nSeleccione su opcion"));
								if (opcionElegida2==1) {
									ArrayList<Object> datos2 = registroDatosAlquiler();
									Alquiler alquiler= (Alquiler)ferrari.accionesCliente(2,datos2,client);
									Cliente cliente1= alquiler.getCliente();		
									TarjetaCredito tarjetaCliente= cliente1.getTarjetaCredito();
									Boolean tarjetaBloqueada= tarjetaCliente.getBloqueo();
									if (tarjetaBloqueada==false) {
									HashMap reservasTotales= Empresa.getReservas();
									Reserva reservaRealizada= (Reserva)reservasTotales.get(idReservaCliente);
									int AbonoPagado= reservaRealizada.getAbono();
									int total= alquiler.getTarifaPagar()-AbonoPagado;
									System.out.println("Para realizar el alquiler debe realizar el pago total menos el abono ya pagado");
									System.out.println("En total es "+ total);
									System.out.println("¿Acepta el pago?");
									System.out.println("\n1. Si \n2. No");
									int op1 = Integer.parseInt(input("\nSeleccione su opcion"));
									if (op1==1){
										System.out.println("Se realizó el alquiler");
										String pattern = "dd/MM/yyyy HH:mm";
										String horaI = (String) datos2.get(2);
										String horaF = (String) datos2.get(4);
								        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
								        Date fechaInic = sdf.parse((String)datos2.get(1)+ " "+horaI);
								        Date fechaFin = sdf.parse((String)datos2.get(3)+ " "+horaF);
										Empleado.cambiarEstadoVehiculoAlquilado(alquiler.getVehiculo(), fechaInic, fechaFin);
										idAlquiler+=1;
										System.out.println("El id de su alquiler es " + idAlquiler);
										HashMap alquileres= Empresa.getAlquileres();
										alquileres.put(idAlquiler,alquiler);
									}}
									else {
										System.out.println("Su tarjeta esta bloqueada no se puede realizar el alquiler");
									}
									
									
									
									
								}
								else {
									List SeguroConductores= registroSeguroConductorAdicional();
									List alquilerInfo= (List)Empresa.realizarAlquilerReserva(idReservaCliente, SeguroConductores);
									Alquiler alquiler= (Alquiler)alquilerInfo.get(0);
									Reserva reserva= (Reserva)alquilerInfo.get(1);
									Cliente cliente2=alquiler.getCliente();
									TarjetaCredito tarjetaCliente= cliente2.getTarjetaCredito();
									Boolean tarjetaBloqueada= tarjetaCliente.getBloqueo();
									if (tarjetaBloqueada==false) {
									int AbonoPagado= reserva.getAbono();
									int total= alquiler.getTarifaPagar()-AbonoPagado;
									System.out.println("Para realizar el alquiler debe realizar el pago total menos el abono ya pagado");
									System.out.println("En total es "+ total);
									System.out.println("¿Acepta el pago?");
									System.out.println("\n1. Si \n2. No");
									int op1 = Integer.parseInt(input("\nSeleccione su opcion"));
									if (op1==1){
										System.out.println("Se realizó el alquiler");
										Date fechaInicial= reserva.getFechaSalida();
										Date fechaFinal= reserva.getFechaLlegada();
										Empleado.cambiarEstadoVehiculoAlquilado(alquiler.getVehiculo(), fechaInicial, fechaFinal);
										idAlquiler+=1;
										System.out.println("El id de su alquiler es " + idAlquiler);
										HashMap alquileres= Empresa.getAlquileres();
										alquileres.put(idAlquiler,alquiler);
									}}
									else {
										System.out.println("Su tarjeta esta bloqueada no se puede realizar el alquiler");
										}
									
								}
							}
							else if (opcionElegida == 2) {
								ArrayList<Object> datos2 = registroDatosAlquiler();
								Alquiler alquiler= (Alquiler)ferrari.accionesCliente(2,datos2,client);
								Cliente cliente1= alquiler.getCliente();
								TarjetaCredito tarjetaCliente= cliente1.getTarjetaCredito();
								Boolean tarjetaBloqueada= tarjetaCliente.getBloqueo();
								if (tarjetaBloqueada==false) {		
								int total= alquiler.getTarifaPagar();
								System.out.println("Para realizar el alquiler debe realizar el pago total");
								System.out.println("El cual es de "+ total);
								System.out.println("¿Acepta el pago?");
								System.out.println("\n1. Si \n2. No");
								int op1 = Integer.parseInt(input("\nSeleccione su opcion"));
								if (op1==1){
									System.out.println("Se realizó el alquiler");
									String pattern = "dd/MM/yyyy HH:mm";
									String horaI = (String) datos2.get(2);
									String horaF = (String) datos2.get(4);
							        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
							        Date fechaInic = sdf.parse((String)datos2.get(1)+ " "+horaI);
							        Date fechaFin = sdf.parse((String)datos2.get(3)+ " "+horaF);
									Empleado.cambiarEstadoVehiculoAlquilado(alquiler.getVehiculo(), fechaInic, fechaFin);
									idAlquiler+=1;
									System.out.println("El id de su alquiler es " + idAlquiler);
									HashMap alquileres= Empresa.getAlquileres();
									alquileres.put(idAlquiler,alquiler);
								}
								}
								
							}
					
							
							
						}
						else if(op == 3) {
							cont = false;
						}
						else {
							System.out.println("Opcion no válida");
						}
						}
					}
					else if(rta.getTipoUsuario().equals("empleado")) {
						Empleado empleado = (Empleado)rta;
						
						boolean cont = true;
						while(cont) 
						{
							System.out.println("Que desea hacer?");
							System.out.println("1. Cambiar el estado de un vehiculo devuelto");
							System.out.println("2. Cambiar el estado de un vehiculo a disponible");
							System.out.println("3. Salir");
							int op = Integer.parseInt(input("Seleccione su opcion"));
							System.out.println();
							
							if(op == 1) ///Cambiar estado devolver vehiculo Consola
							{
								System.out.println("El vehiculo necesita mantenimiento?");
								System.out.println("1. Si");
								System.out.println("2. No");
								int ls = Integer.parseInt(input("Seleccione su opcion"));
								System.out.println();
								
								if (ls == 1)
								{
									SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
									String fechaInicio = input("Ingrese la fecha de inicio");
									String fechaFin = input("Ingrese la fecha de salida");
									Date fechaI = formato.parse(fechaInicio); 
									Date fechaF = formato.parse(fechaFin); 
									String placaCarro = input("Ingrese la placa del vehiculo");
									ferrari.cambiarEstadoVehiculoDevolver(empleado, placaCarro, fechaI, fechaF, true);
									System.out.println("El estado del vehiculo ha sido cambiado a mantenimiento");
									System.out.println();

								}
								
								if (ls == 2)
								{
									SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
									String fechaInicio = input("Ingrese la fecha de inicio");
									String fechaFin = input("Ingrese la fecha de salida");
									Date fechaI = formato.parse(fechaInicio); 
									Date fechaF = formato.parse(fechaFin); 
									String placaCarro = input("Ingrese la placa del vehiculo");
									ferrari.cambiarEstadoVehiculoDevolver(empleado, placaCarro, fechaI, fechaF, false);
									System.out.println("El estado del vehiculo ha sido cambiado a limpieza");
									System.out.println();

								}
							}
							
							else if(op == 2) ///Cambiar estado disponible vehiculo Consola
							{
								String placaCarro = input("Ingrese la placa del vehiculo");
								ferrari.cambiarEstadoVehiculoDisponible(empleado, placaCarro);
								System.out.println("El estado del vehiculo ha sido cambiado a disponible y puede ser reservado o alquilado");
								System.out.println();
							}
								
							else if(op == 3)
							{
								cont = false;
							}
							else 
							   {
								System.out.println("Opcion no válida");
								System.out.println();
							}
						}
					}
					
					else if(rta.getTipoUsuario().equals("administradorLocal")) ///AdministradorLocal Consola
					{
						AdministradorLocal adminLocal = (AdministradorLocal)rta;
						
						boolean cont = true;
						while(cont) 
						{
							System.out.println("Que desea hacer?");
							System.out.println("1. Crear un nuevo empleado");
							System.out.println("2. Cambiar la informacion de algun empleado");
							System.out.println("3. Salir");
							int op = Integer.parseInt(input("Seleccione su opcion"));
							System.out.println();
							
							if(op == 1)
							{
								String logIn = input("Ingrese el logIn del nuevo empleado");
								String contraseña = input("Ingrese la contraseña del nuevo empleado");
								String nombreCompleto= input("Ingrese el nombre completo del nuevo empleado");
								String tipoUsuario = input("Ingrese el tipo de usuario del nuevo empleado");
								String sede = input("Ingrese la sede del nuevo empleado");
								ferrari.crearEmpleado(adminLocal, logIn, contraseña, nombreCompleto, tipoUsuario, sede);
								System.out.println("El empleado " + nombreCompleto + " ha sido creado");
								System.out.println();
							}
							
							if(op == 2)
							{
								String nombreEmpleado = input("Ingrese el nombre del empleado a buscar");
								System.out.println("Que informacion desea cambiar?");
								System.out.println("1. El logIn");
								System.out.println("2. La contraseña");
								System.out.println("3. La sede");
								int ls = Integer.parseInt(input("Seleccione su opcion"));
								System.out.println();
								
								if(ls == 1)
								{
									String nuevoLogIn = input("Ingrese el LogIn nuevo");
									ferrari.setInformacionEmpleadoSede(adminLocal, nombreEmpleado, nuevoLogIn, null, null, true, false, false);
									System.out.println("Ha sido cambiado el logIn de " + nombreEmpleado);
									System.out.println();
								}
								
								if(ls == 2)
								{
									String nuevaContraseña = input("Ingrese la contraseña nueva");
									ferrari.setInformacionEmpleadoSede(adminLocal, nombreEmpleado, null, nuevaContraseña, null, false, true, false);
									System.out.println("Ha sido cambiado la contraseña de " + nombreEmpleado);
									System.out.println();
								}
								
								if(ls == 3)
								{
									String nuevaSede = input("Ingrese la sede nueva");
									ferrari.setInformacionEmpleadoSede(adminLocal, nombreEmpleado, null, null, nuevaSede, false, false, true);
									System.out.println("Ha sido cambiado la sede de " + nombreEmpleado);
									System.out.println();
								}
								
							}
							
							else if(op == 3)
							{
								cont = false;
							}
							else 
							{
								System.out.println("Opcion no válida");
							}
						}
					}
					
					else if(rta.getTipoUsuario().equals("administradorGeneral")) {
						AdministradorGeneral adminGen = (AdministradorGeneral)rta;
						boolean cont = true;
						while(cont) {
						System.out.println("\nQue desea hacer?\n1.Registrar nuevo vehiculo\n2.Dar de baja vehiculo\n3.Configurar seguro");
						System.out.println("4.Realizar translado interno\n5.Modificar informacion sede\n6.Salir");
						int op = Integer.parseInt(input("\nSeleccione su opcion"));
						if(op == 1) {
							//ingresar nuevo vehiculo
							ArrayList<String> datosVehiculo = ingresarNuevoVehiculo();
							String plac = ferrari.registrarNuevoVehiculo(adminGen,datosVehiculo);
							System.out.println("Reigistro del carro con placa " + plac+ " exitoso");
						}
						else if(op == 2) {
							//dar de baja vehiculo
							String placa = input("\nIngrese la placa del vehiculo a dar de baja");
							String estado = ferrari.darDeBajaVehiculo(adminGen,placa);
							System.out.println("El estado del vehiculo ha cambiado exitosamente a: "+estado);
						}
						else if(op == 3) {
							//configurar seguro
							ArrayList<String> dataSeguro = tomarDatosSeguro();
							String seguro = ferrari.configurarSeguro(adminGen, dataSeguro);
							System.out.println("Nuevo seguro '"+seguro+"' creado exitosamente");
							
						}
						else if(op == 4) {
							//realizar translado
							String placa = input("\nIngrese la placa del vehiculo a transladar");
							String sede = input("\nIngrese la sede de destino");
							boolean verificar = ferrari.realizarTranslado(adminGen, placa, sede);
							if(verificar) {
								System.out.println("Translado exitoso");
							}
							else {
								System.out.println("Translado fallido");
							}
							
						}
						
						else if(op == 5) {
							//modificar info sede
							String sede = input("\nIngrese el nombre de la sede a modificar");
							System.out.println("¿Que desea modificar de la sede?");
							System.out.println("1. Nombre\n2. Direccion\n3. Dias de atencion");
							System.out.println("4. Horas de atencion\n5. Administrador sede");
							String cambio = "";
							int opc = Integer.parseInt(input("\nIngrese su opcion"));
							if(opc == 1) {
								cambio = input("\nIngrese el nuevo nombre");
							}
							else if(opc ==2){
								cambio = input("\nIngrese la nueva direccion");
							}
							else if(opc ==3){
								cambio = input("\nIngrese los nuevos dias de atencion");
							}
							else if(opc ==4){
								cambio = input("\nIngrese las nuevas horas de atencion");
							}
							else if(opc ==5){
								cambio = input("\nIngrese el nombre completo del nuevo administrador local");
							}
							if(cambio != "") {
								Sede sedeMod = ferrari.modificarSede(adminGen, opc, cambio, sede);
								if(sedeMod != null) {
									System.out.println("Se ha modificado exitodamente la sede");
									System.out.println("Nombre: "+sedeMod.getNombre());
									System.out.println("Direccion: "+sedeMod.getDireccion());
									System.out.println("Dias de atencion: "+sedeMod.getDiasAtencion());
									System.out.println("Horas de atencion: "+sedeMod.getHorasAtencion());
									System.out.println("Administrador local: "+sedeMod.getNombreAdminSede());
								}
								else {
									System.out.println("Proceso fallido");
								}
							}
							else {
								System.out.println("Opcion no valida");
							}
						}
						
						else if(op == 6) {
							cont = false;
						}
						else {
							System.out.println("Opcion no válida");
						}
						}
					}
					
				}
			}

			else if(opcion == 2){
				System.out.println("\nRegistro como cliente\nComplete la siguiente informacion");
				System.out.println("\n- Datos personales -");
				String nombre = input("\nIngrese su nombre completo");
				String email = input("\nIngrese su correo electronico");
				String telefono = input("\nIngrese su numero de telefono");
				String fechaNacimiento = input("\nIngrese su fecha de nacimiento (dd/mm/yyyy)");
				String nacionalidad = input("\nIngrese su nacionalidad");
				System.out.println("\n- Datos de licencia de conduccion-");
				String numeroLicencia = input("\nIngrese el numero de su licencia");
				String paisLicencia = input("\nIngrese el pais de expedicion de su licencia");
				String fechaExpLicencia = input("\nIngrese la fecha de expedicion de su licencia (dd/mm/yyyy)");
				System.out.println("\n- Datos de tarjeta de credito-");
				String numeroTC = input("\nIngrese el número de su tarjeta de credito");
				String fechaVen = input("\nIngrese la fecha de vencimiento de su tarjeta de credito (dd/mm/yyyy)");
				String LogIn = input("\nIngrese el nombre de usuario que le gustaria tener");
				String contrasenia = input("\nIngrese una contrasenia");
				Cliente c =ferrari.crearNuevoCliente(nombre, email, telefono, fechaNacimiento, nacionalidad, numeroLicencia, paisLicencia, fechaExpLicencia, numeroTC, fechaVen, LogIn, contrasenia);
				if(c != null) {
					System.out.println(c.getLogIn()+" ha sido registrado con exito");
				}
			}
			else if(opcion ==3) {
				continuar = false;
			}
			else {
				System.out.println("Opción no valida");
			}
		}
	}
	public ArrayList<Object> registroSeguroConductorAdicional() {
		ArrayList<Object> datos1 = new ArrayList<Object>();
		
		List seguros= Empresa.getSeguros();
		for (int l=1;l<seguros.size()+1;l++) {
		      Seguro seguro1= (Seguro) seguros.get(l-1);
		      String seguroAElegir= seguro1.getNombre();
		      System.out.println(l+". "+seguroAElegir);
		    }
		int seguroElegido = Integer.parseInt(input("\nIngrese el seguro que quiere comprar: "));
		datos1.add(seguros.get(seguroElegido-1));
		int conductoresAdicionales = Integer.parseInt(input("\nIngrese cuantos conductores adicionales va a tener el vehiculo :"));
		ArrayList<ArrayList> conductores = new ArrayList<ArrayList>();
		if (conductoresAdicionales!=0) {
			for (int s=1;s<conductoresAdicionales+1;s++) {
			ArrayList<String> infoConductor = new ArrayList<String>();
			String numeroLicencia = String.valueOf(input("\nIngrese el numero de la licencia del conductor "));
			infoConductor.add(numeroLicencia);
			String paisLicencia = String.valueOf(input("\nIngrese el pais de expedición de la licencia del conductor "));
			infoConductor.add(paisLicencia);
			String vencimientoLicencia = String.valueOf(input("\nIngrese la fecha de vencimiento de la licencia del conductor "));
			infoConductor.add(vencimientoLicencia);
			conductores.add(infoConductor);
		}
			
			}datos1.add(conductores);
		return datos1;
	}
	
	
	public ArrayList<Object> registroPrimerosDatos() {
		ArrayList<Object> datos1 = new ArrayList<Object>();
		System.out.println("\nElija la categoria de su vehiculo: ");
		ArrayList<String> categs = ferrari.getCategoriasStr();
		int i = 1;
		for(String cat: categs) {
			System.out.println(i+". "+cat);
			i++;
		}
		int categ = Integer.parseInt(input("\nSeleccione su opcion"));
		datos1.add(categ);
		String fechaR = input("\nIngrese la fecha de recogida");
		datos1.add(fechaR);
		String horaR = input("\nIngrese la hora de recogida");
		datos1.add(horaR);
		String fechaE = input("\nIngrese la fecha de entrega");
		datos1.add(fechaE);
		String horaE = input("\nIngrese la hora de entrega");
		datos1.add(horaE);
		System.out.println("A continuacion se muestran las sedes de la empresa: ");
		ArrayList<String> seds = ferrari.getSedesStr();
		int j = 1;
		for(String sede: seds) {
			System.out.println(j+". "+sede);
			j ++;
		}
		int sedeR = Integer.parseInt(input("\nIngrese la sede de recogida"));
		datos1.add(seds.get(sedeR-1));
		int sedeE = Integer.parseInt(input("\nIngrese la sede de entrega"));
		datos1.add(seds.get(sedeE-1));
		
		return datos1;
	}
	
	
	public ArrayList<Object> registroDatosAlquiler() {
		ArrayList<Object> datos1 = new ArrayList<Object>();
		System.out.println("\nElija la categoria de su vehiculo: ");
		ArrayList<String> categs = ferrari.getCategoriasStr();
		int i = 1;
		for(String cat: categs) {
			System.out.println(i+". "+cat);
			i++;
		}
		int categ = Integer.parseInt(input("\nSeleccione su opcion"));
		datos1.add(categ);
		String fechaR = input("\nIngrese la fecha de recogida");
		datos1.add(fechaR);
		String horaR = input("\nIngrese la hora de recogida");
		datos1.add(horaR);
		String fechaE = input("\nIngrese la fecha de entrega");
		datos1.add(fechaE);
		String horaE = input("\nIngrese la hora de entrega");
		datos1.add(horaE);
		System.out.println("A continuacion se muestran las sedes de la empresa: ");
		ArrayList<String> seds = ferrari.getSedesStr();
		int j = 1;
		for(String sede: seds) {
			System.out.println(j+". "+sede);
			j++;
		}
		int sedeR = Integer.parseInt(input("\nIngrese la sede de recogida"));
		datos1.add(seds.get(sedeR-1));
		int sedeE = Integer.parseInt(input("\nIngrese la sede de entrega"));
		datos1.add(seds.get(sedeE-1));
		List seguros= Empresa.getSeguros();
		for (int l=1;l<seguros.size()+1;l++) {
		      Seguro seguro1= (Seguro) seguros.get(l-1);
		      String seguroAElegir= seguro1.getNombre();
		      System.out.println(l+". "+seguroAElegir);
		    }
		int seguroElegido = Integer.parseInt(input("\nIngrese el seguro que quiere comprar: "));
		datos1.add(seguros.get(seguroElegido-1));
		int conductoresAdicionales = Integer.parseInt(input("\nIngrese cuantos conductores adicionales va a tener el vehiculo :"));
		ArrayList<ArrayList> conductores = new ArrayList<ArrayList>();
		if (conductoresAdicionales!=0) {
			for (int s=1;s<conductoresAdicionales+1;s++) {
			ArrayList<String> infoConductor = new ArrayList<String>();
			String numeroLicencia = String.valueOf(input("\nIngrese el numero de la licencia del conductor "));
			infoConductor.add(numeroLicencia);
			String paisLicencia = String.valueOf(input("\nIngrese el pais de expedición de la licencia del conductor "));
			infoConductor.add(paisLicencia);
			String vencimientoLicencia = String.valueOf(input("\nIngrese la fecha de vencimiento de la licencia del conductor "));
			infoConductor.add(vencimientoLicencia);
			conductores.add(infoConductor);
		}
			
			}datos1.add(conductores);
		return datos1;
	}
	
	
	public ArrayList<String> ingresarNuevoVehiculo(){
		ArrayList<String> data = new ArrayList<String>();
		String placa = input("\nIngrese la placa del vehiculo");
		data.add(placa);
		String marca = input("\nIngrese la marca del vehiculo");
		data.add(marca);
		String modelo = input("\nIngrese el modelo del vehiculo");
		data.add(modelo);
		String color = input("\nIngrese el color del vehiculo");
		data.add(color);
		String tipoTrans = input("\nIngrese el tipo de transmision del vehiculo");
		data.add(tipoTrans);
		String capacidad = input("\nIngrese la capacidad del vehiculo");
		data.add(capacidad);
		String categoria = input("\nIngrese la categoria del vehiculo");
		data.add(categoria);
		String sede = input("\nIngrese la sede asignada para el vehiculo");
		data.add(sede);
		
		return data;
	}
	
	public ArrayList<String> tomarDatosSeguro(){
		ArrayList<String> data = new ArrayList<String>();
		String nombre = input("\nIngrese el nombre del seguro");
		data.add(nombre);
		String precio = input("\nIngrese el precio del seguro");
		data.add(precio);
		return data;
		
	}
	
	public String input(String mensaje)
	{
		try
		{
			System.out.print(mensaje + ": ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		}
		catch (IOException e)
		{
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
	System.out.println("(ﾉ◕ヮ◕)ﾉ*:･ﾟ✧");
	System.out.println("Bienvenido al sistema de renta de carros ");
	
	principal app = new principal();
	app.ejecutar();
	System.out.println("Gracias por su visita (✿◡‿◡)");
	

	}

}
