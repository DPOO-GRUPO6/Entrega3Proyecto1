package Presentacion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;

import logica.Cliente;
import logica.Empresa;
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
