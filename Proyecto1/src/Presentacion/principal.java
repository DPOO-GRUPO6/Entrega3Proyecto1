package Presentacion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;

import logica.AdministradorGeneral;
import logica.Cliente;
import logica.Empresa;
import logica.Sede;
import logica.Usuario;

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
					else if(rta.getTipoUsuario().equals("empleado")) {
						ferrari.accionesEmpleado();
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
