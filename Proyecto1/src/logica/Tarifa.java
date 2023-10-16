package logica;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Tarifa {
	Categoria categoriaVehiculo;
	int tarifa;
	int abono;
	int valorExtraSede;
	int valorExtraConductor;
	int valorExtraSeguro;
	
	public Tarifa(Categoria categoriaVehiculo, int tarifa, int abono, int valorExtraSede, int valorExtraConductor,
			int valorExtraSeguro) {
		this.categoriaVehiculo = categoriaVehiculo;
		this.tarifa = tarifa;
		this.abono = abono;
		this.valorExtraSede = valorExtraSede;
		this.valorExtraConductor = valorExtraConductor;
		this.valorExtraSeguro = valorExtraSeguro;
	}

	public static int establecerTarifaPorDia(Date fecha1, Date fecha2, Date fecha3, Date fecha4, Date fechaSalida, Categoria categoria) {
		if (fechaSalida.after(fecha1)&& fechaSalida.before(fecha2)) {
			return (int)categoria.getCostoPorDia();
		}
		else {
		int porDia= (int)categoria.getCostoPorDia();
		double porDia2= porDia +porDia*0.15;
		return (int)porDia2; //falta ejecutar algoritmo
		}
	}
	
	public static int calcularTarifaEstimada(int tarifaPorDia, int dias) { //tarifa para la reserva
		return tarifaPorDia* dias;
	}
	
	public int calcularTarifaTotal() {
		return 0;
	}
	



	
	
	
	
}
