package Presentacion;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logica.Empresa;
import logica.Reserva;
import logica.Seguro;
import logica.TarjetaCredito;
import logica.Usuario;
import logica.Vehiculo;
import logica.Alquiler;
import logica.Cliente;
import logica.Empleado;

public class Controlador {
	Empresa ferrari = new Empresa();
	Usuario usuario= null;
	public int idReserva=0;
	private int idAlquiler=0;
	
	public void cargarInformacion() throws ParseException {
		this.ferrari.cargarInformacion();
	}
	
	public int inicarSesion(String usuario, String pwd){
		Usuario rta = ferrari.iniciarSesion(usuario, pwd);
		// 0 > cliente
		// 1 > empleado
		// 2 > adminlocal
		// 3 > admin local
		// -1 > null
		if(rta == null) {
			return -1;
		}
		else if(rta.getTipoUsuario().equals("cliente")) {
			this.usuario= (Cliente)rta;
			return 0;
		}
		else if(rta.getTipoUsuario().equals("empleado")) {
			return 1;
		}
		else if(rta.getTipoUsuario().equals("administradorLocal")) {
			return 2;
		}
		else if(rta.getTipoUsuario().equals("administradorGeneral")) {
			return 3;
		}
		else {
			return -1;
		}
	}
	public String[] getSedes() {
		ArrayList<String> listSedes= ferrari.getSedesStr();
		String[] Arr= listSedes.toArray(new String[listSedes.size()]);
		return Arr;
		
	}
	
	public String[] getCategorias() {
		ArrayList<String> listCategorias= ferrari.getCategoriasStr();
		String[] Arr= listCategorias.toArray(new String[listCategorias.size()]);
		return Arr;
		
	}
	
	public String[] getSeguros() {
		ArrayList<Seguro> listSeguros2= ferrari.getSeguros();
		ArrayList<String>listSeguros= new ArrayList<String>();
		listSeguros.add("");
		for (Seguro elemento :listSeguros2) {
			String seguro= elemento.getNombre();
			listSeguros.add(seguro);
		}
		String[] Arr= listSeguros.toArray(new String[listSeguros.size()]);
		return Arr;
		
	}
	
	public List infoReserva(int categoria, String fechaInic, String horaInic, String fechaFin, String horaFin, String sedeRecogida, String sedeEntrega) throws ParseException {
		ArrayList<Object> datos1 = new ArrayList<>();
		datos1.add(categoria);
		datos1.add(fechaInic);
		datos1.add(horaInic);
		datos1.add(fechaFin);
		datos1.add(horaFin);
		datos1.add(sedeRecogida);
		datos1.add(sedeEntrega);
		Cliente cliente= (Cliente)this.usuario;
		List listaReserva= (List)ferrari.accionesCliente(1, datos1, cliente);
		ArrayList<Object> datos2 = new ArrayList<>();
		Reserva reserva= (Reserva)listaReserva.get(0);
		int abono= reserva.getAbono();
		String resp="";
		int op= 0;
		Vehiculo vehiculoReserva= (Vehiculo) listaReserva.get(1);
		if (vehiculoReserva==null) {
			op= 1;
			resp= "No se puede realizar esta reserva con esas especificaciones";
			datos2.add(op);
			datos2.add(resp);
		}
		else {
			Cliente cliente1= reserva.getCliente();
			TarjetaCredito tarjetaCliente= cliente1.getTarjetaCredito();
			boolean bloqueo= tarjetaCliente.getBloqueo();
			if (bloqueo)
			{	
				op= 1;
				resp= "No se puede realizar esta reserva porque la tarjeta se encuentra bloqueada";
				datos2.add(op);
				datos2.add(resp);
			}
			else {
				op=2;
				datos2.add(op);
				datos2.add(abono);
				datos2.add(reserva);
				datos2.add(vehiculoReserva);
				
			}
			
		}
		return datos2;
	}

	public int realizarReserva(Reserva reserva, Vehiculo vehiculoReserva) {
		// TODO Auto-generated method stub
		Map reservasEmpresa= ferrari.getReservas();
		idReserva+=1;
		reservasEmpresa.put(idReserva, reserva);
		List reservasCarro= vehiculoReserva.getReservas();
		reservasCarro.add(reserva);
		return idReserva;
	}


	public List infoAlquiler(int categoria, String fechaInic, String horaInic, String fechaFin, String horaFin,
			String sedeRecogida, String sedeEntrega, int seguro, List listcond) throws ParseException {
		// TODO Auto-generated method stub
		ArrayList<Object> datos1 = new ArrayList<>();
		datos1.add(categoria);
		datos1.add(fechaInic);
		datos1.add(horaInic);
		datos1.add(fechaFin);
		datos1.add(horaFin);
		datos1.add(sedeRecogida);
		datos1.add(sedeEntrega);
		Seguro seg= null;
		ArrayList<Seguro> Seguros= ferrari.getSeguros();
		if (seguro!=-1) {
			seg= Seguros.get(seguro);
		}
		datos1.add(seg);
		datos1.add(listcond);
		Cliente cliente= (Cliente)this.usuario;
		Alquiler alquiler= (Alquiler)ferrari.accionesCliente(2, datos1, cliente);
		System.out.println(alquiler);
		Cliente cliente1= alquiler.getCliente();
		TarjetaCredito tarjetaCliente= cliente1.getTarjetaCredito();
		Boolean tarjetaBloqueada= tarjetaCliente.getBloqueo();
		int total=0;
		if (tarjetaBloqueada==false) {
			total= alquiler.getTarifaPagar();
		}
		else if (alquiler.getVehiculo()==null) {
			total=0;
		}
		ArrayList<Object> datos2 = new ArrayList<>();
		Vehiculo vehiculoalq= alquiler.getVehiculo();
		datos2.add(total);
		datos2.add(alquiler);
		datos2.add(vehiculoalq);
		return datos2;
		}



	public int realizarAlquiler(Alquiler alquiler, Vehiculo vehiculoAlquiler, String fechaInic, String fechafin, String horaInic, String horaFin) throws ParseException {
		// TODO Auto-generated method stub
		String pattern = "dd/MM/yyyy HH:mm";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date fechaInicio = sdf.parse(fechaInic+ " "+horaInic);
        Date fechaFin = sdf.parse(fechafin+ " "+horaFin);
        Empleado.cambiarEstadoVehiculoAlquilado(vehiculoAlquiler, fechaInicio, fechaFin);
		idAlquiler+=1;
		HashMap alquileres= ferrari.getAlquileres();
		alquileres.put(idAlquiler,alquiler);
		return idAlquiler;
	}

	public ArrayList<Object> infoAlquilerReserva(String idReserva2, int seguro, List conductoresadi) throws ParseException {
		// TODO Auto-generated method stub
		ArrayList<Object> datos1 = new ArrayList<>();
		Seguro seg= null;
		ArrayList<Seguro> Seguros= ferrari.getSeguros();
		if (seguro!=-1) {
			seg= Seguros.get(seguro);
		}
		datos1.add(seg);	
		datos1.add(conductoresadi);
		List datos= (List)datos1;
		int idReservaAlq= Integer.valueOf(idReserva2);
		List alquilerInfo= (List)ferrari.realizarAlquilerReserva(idReservaAlq,datos);
		Alquiler alquiler= (Alquiler)alquilerInfo.get(0);
		Reserva reserva= (Reserva)alquilerInfo.get(1);
		Cliente cliente2=alquiler.getCliente();
		TarjetaCredito tarjetaCliente= cliente2.getTarjetaCredito();
		Boolean tarjetaBloqueada= tarjetaCliente.getBloqueo();
		int total=0;
		if (tarjetaBloqueada==false) {
			int AbonoPagado= reserva.getAbono();
			total= alquiler.getTarifaPagar()-AbonoPagado;
		}
		else if (alquiler.getVehiculo()==null) {
			total=0;
		}
		ArrayList<Object> datos2 = new ArrayList<>();
		Vehiculo vehiculoalq= alquiler.getVehiculo();
		datos2.add(total);
		datos2.add(reserva);
		datos2.add(alquiler);
		datos2.add(vehiculoalq);
		return datos2;
	}

	public int realizarAlquilerReserva(Alquiler alquiler, Vehiculo vehiculoAlquiler, Date fechaInicial,
			Date fechaFinal) {
		// TODO Auto-generated method stub
		Empleado.cambiarEstadoVehiculoAlquilado(vehiculoAlquiler, fechaInicial, fechaFinal);
		idAlquiler+=1;
		HashMap alquileres= ferrari.getAlquileres();
		alquileres.put(idAlquiler,alquiler);
		return idAlquiler;
	}
		
		
	
	
}
