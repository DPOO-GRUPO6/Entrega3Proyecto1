package logica;

import java.time.LocalDate;
import java.util.ArrayList;

public class Tarifa {
	Categoria categoriaVehiculo;
	int valorExtraSede;
	int valorExtraConductor;
	int valorExtraSeguro;
	
	public int establecerTarifaPorDia(LocalDate fechaInicAlquiler, ArrayList<LocalDate> fechasTempAltas,ArrayList<LocalDate> fechasTempBajas) {
		return 0; //falta ejecutar algoritmo
	}
	
	public int calcularTarifaEstimada() { //tarifa para la reserva
		return 0;
	}
	
	public int calcularTarifaTotal() {
		return 0;
	}
	
	public Tarifa(Categoria categoriaVehiculo, int valorExtraSede, int valorExtraConductor, int valorExtraSeguro) {
		this.categoriaVehiculo = categoriaVehiculo;
		this.valorExtraSede = valorExtraSede;
		this.valorExtraConductor = valorExtraConductor;
		this.valorExtraSeguro = valorExtraSeguro;
	}
	
	
	
}
