package mx.com.libreria.estructuras;

import java.io.Serializable;

public class Datos implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8314683828883749884L;

	private String fecha;
	
	private double serie;
	
	private double rendimientoCompuesto;
	
	private double rendimientoContinuo;

	private double rendimientoContinuoNormalizado;

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public double getSerie() {
		return serie;
	}

	public void setSerie(double serie) {
		this.serie = serie;
	}

	public double getRendimientoCompuesto() {
		return rendimientoCompuesto;
	}

	public void setRendimientoCompuesto(double rendimientoCompuesto) {
		this.rendimientoCompuesto = rendimientoCompuesto;
	}

	public double getRendimientoContinuo() {
		return rendimientoContinuo;
	}

	public void setRendimientoContinuo(double rendimientoContinuo) {
		this.rendimientoContinuo = rendimientoContinuo;
	}

	public double getRendimientoContinuoNormalizado() {
		return rendimientoContinuoNormalizado;
	}

	public void setRendimientoContinuoNormalizado(
			double rendimientoContinuoNormalizado) {
		this.rendimientoContinuoNormalizado = rendimientoContinuoNormalizado;
	}

	
}
