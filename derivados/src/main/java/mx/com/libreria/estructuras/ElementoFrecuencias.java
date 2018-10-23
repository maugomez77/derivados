package mx.com.libreria.estructuras;

import java.io.Serializable;

public class ElementoFrecuencias implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6229365706040028191L;
	
	private double rangoInicial;
	private double rangoFinal;
	private int contador;
	public double getRangoInicial() {
		return rangoInicial;
	}
	public void setRangoInicial(double rangoInicial) {
		this.rangoInicial = rangoInicial;
	}
	public double getRangoFinal() {
		return rangoFinal;
	}
	public void setRangoFinal(double rangoFinal) {
		this.rangoFinal = rangoFinal;
	}
	public int getContador() {
		return contador;
	}
	public void setContador(int contador) {
		this.contador = contador;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
