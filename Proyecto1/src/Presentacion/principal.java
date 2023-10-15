package Presentacion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import logica.AdministradorGeneral;
import logica.AdministradorLocal;
import logica.Cliente;
import logica.Empleado;
import logica.Empresa;
import logica.Sede;
import logica.Usuario;
import logica.Vehiculo;

public class principal {
	Empresa ferrari = new Empresa();
	
	public void ejecutar() throws ParseException {
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
							ferrari.accionesCliente(1,datos1,client);
						}
						else if(op == 2) {
							//ferrari.accionesCliente(2,datos1,client);
							System.out.println("otro proceso");
						}
						else if(op == 3) {
							cont = false;
						}
						else {
							System.out.println("Opcion no válida");
						}
						}
					}
					else if(rta.getTipoUsuario().equals("empleado")) 
					{
						Empleado empleado = (Empleado)rta;
						boolean cont = true;
						while(cont) 
						{
							System.out.println("Que desea hacer?");
							System.out.println("1. Cambiar el estado de un vehiculo devuelto");
							System.out.println("2. Cambiar el estado de un vehiculo a disponible");
							System.out.println("3. Salir");
							int op = Integer.parseInt(input("Seleccione su opcion"));
							
							if(op == 1)
							{
								System.out.println("El vehiculo necesita mantenimiento?");
								System.out.println("1. Si");
								System.out.println("2. No");
								int ls = Integer.parseInt(input("Seleccione su opcion"));
								
								if (ls == 1)
								{
									SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
									String fechaInicio = input("Ingrese la fecha de inicio");
									String fechaFin = input("Ingrese la fecha de salida");
									Date fechaI = formato.parse(fechaInicio); 
									Date fechaF = formato.parse(fechaFin); 
									empleado.cambiarEstadoVehiculoDevolver(null, fechaI, fechaF, true);
									System.out.println("El estado del vehiculo ha sido cambiado a mantenimiento y podra volver al inventario el" + fechaF);
								}
								
								if (ls == 2)
								{
									SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
									String fechaInicio = input("Ingrese la fecha de inicio");
									String fechaFin = input("Ingrese la fecha de salida");
									Date fechaI = formato.parse(fechaInicio); 
									Date fechaF = formato.parse(fechaFin); 
									empleado.cambiarEstadoVehiculoDevolver(null, fechaI, fechaF, false);
									System.out.println("El estado del vehiculo ha sido cambiado a limpieza y estara listo para el" + fechaF);
								}
							}
							
							else if(op == 2)
							{
								empleado.cambiarEstadoVehiculoDisponible(null);
								System.out.println("El estado del vehiculo ha sido cambiado a disponible y puede ser reservado o alquilado");
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
					else if(rta.getTipoUsuario().equals("administradorLocal")) 
					{
						AdministradorLocal adminlocal = (AdministradorLocal)rta;
						Empleado empleado = (Empleado)rta;

						boolean cont = true;
						while(cont) 
						{
							System.out.println("Que desea hacer?");
							System.out.println("1. Crear un nuevo empleado");
							System.out.println("2. Cambiar la informacion de algun empleado");
							System.out.println("3. Salir");
							int op = Integer.parseInt(input("Seleccione su opcion"));
							
							if(op == 1)
							{
								String logIn = input("Ingrese el logIn del nuevo empleado");
								String contraseña = input("Ingrese la contraseña del nuevo empleado");
								String nombreCompleto= input("Ingrese el nombre completo del nuevo empleado");
								String tipoUsuario = input("Ingrese el tipo de usuario del nuevo empleado");
								adminlocal.crearEmpleado(logIn, contraseña, nombreCompleto, tipoUsuario, null);
								System.out.println("El empleado " + nombreCompleto + "ha sido creado");
							}
							
							if(op == 2)
							{
								String nombreEmpleado = ("Ingrese el nombre del empleado a buscar");
								System.out.println("Que informacion desea cambiar?");
								System.out.println("1. El logIn");
								System.out.println("2. La contraseña");
								System.out.println("3. La sede");
								int ls = Integer.parseInt(input("Seleccione su opcion"));
								
								if(ls == 1)
								{
									String nuevoLogIn = ("Ingrese el LogIn nuevo");
									adminlocal.setInformacionEmpleadoSede(null, empleado, nombreEmpleado, nuevoLogIn, null, null, true, false, false);
								}
								if(ls == 2)
								{
									String nuevaContraseña = ("Ingrese el LogIn nuevo");
									adminlocal.setInformacionEmpleadoSede(null, empleado, nombreEmpleado, null, nuevaContraseña, null, false, true, false);
								}
								if(ls == 3)
								{
									String nuevaSede = ("Ingrese el LogIn nuevo");
									adminlocal.setInformacionEmpleadoSede(null, empleado, nombreEmpleado, null, null, null, false, false, true);
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
						System.out.println("\n4.Realizar translado interno\n5.Modificar informacion sede\n6.Salir");
						int op = Integer.parseInt(input("\nSeleccione su opcion"));
						if(op == 1) {
							//ingresar nuevo vehiculo
							ArrayList<String> datosVehiculo = ingresarNuevoVehiculo();
							ferrari.registrarNuevoVehiculo(adminGen,datosVehiculo);
						}
						else if(op == 2) {
							//dar de baja vehiculo
							String placa = input("\nIngrese la placa del vehiculo a dar de baja");
							ferrari.darDeBajaVehiculo(adminGen,placa);
							System.out.println("El estado del vehiculo ha cambiado a: desechado");
						}
						else if(op == 3) {
							//configurar seguro
							ArrayList<String> dataSeguro = tomarDatosSeguro();
							ferrari.configurarSeguro(adminGen, dataSeguro);
							
						}
						else if(op == 4) {
							//realizar translado
							String placa = input("\nIngrese la placa del vehiculo a transladar");
							String sede = input("\nIngrese la sede de destino");
							ferrari.realizarTranslado(adminGen, placa, sede);
							
						}
						
						else if(op == 5) {
							//modificar info sede
							String sede = input("\nIngrese el nombre de la sede a modificar");
							System.out.println("Que desea modificar de la sede?");
							
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
				System.out.println("tenemos que crear un nuevo usuario cliente >:v");
			}
			else if(opcion ==3) {
				continuar = false;
			}
			else {
				System.out.println("Opción no valida");
			}
		}
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
		}
		int sedeR = Integer.parseInt(input("\nIngrese la sede de recogida"));
		datos1.add(seds.get(sedeR-1));
		int sedeE = Integer.parseInt(input("\nIngrese la sede de entrega"));
		datos1.add(seds.get(sedeE-1));
		
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
