package mx.com.libreria.math;

public class SwapRow {

	/**
	 * El tiempo.
	 */
	private Double tiempo;

	/**
	 * La tasa LIBOR.
	 */
	private Double tasa;

	public SwapRow(Double tiempo, Double tasa) {
		this.tiempo = tiempo;
		this.tasa = tasa;
	}

	/**
	 * @return the tiempo
	 */
	public Double getTiempo() {
		return tiempo;
	}

	/**
	 * @param tiempo
	 *            the tiempo to set
	 */
	public void setTiempo(Double tiempo) {
		this.tiempo = tiempo;
	}

	/**
	 * @return the tasa
	 */
	public Double getTasa() {
		return tasa;
	}

	/**
	 * @param interes
	 *            the interes to set
	 */
	public void setTasa(Double tasa) {
		this.tasa = tasa;
	}
}
