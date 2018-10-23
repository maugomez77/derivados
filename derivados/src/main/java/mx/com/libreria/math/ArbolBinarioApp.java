package mx.com.libreria.math;

public class ArbolBinarioApp {

	/**
	 * La variable arbol binario.
	 */
	private ArbolBinario ab;

	/**
	 * El precio de s0.
	 */
	private Double s0;

	/**
	 * La volatidad (en decimales).
	 */
	private Double volatilidad;

	/**
	 * La variable tasa libre de riesgo.
	 */
	private Double tasaLibreRieso;

	/**
	 * El valor de k.
	 */
	private Double k;

	/**
	 * El tiempo anualizado (en decimales).
	 */
	private Double tiempo;

	/**
	 * El numero de periodos del arbol.
	 */
	private Integer periodos;

	/**
	 * La variable up.
	 */
	private Double up;

	/**
	 * La variable down.
	 */
	private Double down;

	/**
	 * La variable probabilidad;
	 */
	private Double probabilidad;

	/**
	 * 
	 * @param s0
	 *            El precio de s0.
	 * @param volatilidad
	 *            La volatilidad.
	 * @param tasaLibreRiesgo
	 *            La tasa libre de riesgo.
	 * @param k
	 *            El valor de k.
	 * @param tiempo
	 *            El tiempo anualizado en decimales.
	 * @param periodos
	 *            El numero de periodos del arbol.
	 */
	public ArbolBinarioApp(Double s0, Double volatilidad,
			Double tasaLibreRiesgo, Double k, Double tiempo, Integer periodos) {
		this.s0 = s0;
		this.volatilidad = volatilidad;
		this.tasaLibreRieso = tasaLibreRiesgo;
		this.k = k;
		this.tiempo = tiempo;
		this.periodos = periodos;
		this.calculaUp();
		this.calculaDown();
		this.calculaProbabilidad();
		this.ab = new ArbolBinario(periodos, s0);
	}

	/**
	 * @return the ab
	 */
	public ArbolBinario getAb() {
		return ab;
	}

	/**
	 * @param ab
	 *            the ab to set
	 */
	public void setAb(ArbolBinario ab) {
		this.ab = ab;
	}

	/**
	 * @return the s0
	 */
	public Double getS0() {
		return s0;
	}

	/**
	 * @param s0
	 *            the s0 to set
	 */
	public void setS0(Double s0) {
		this.s0 = s0;
	}

	/**
	 * @return the volatilidad
	 */
	public Double getVolatilidad() {
		return volatilidad;
	}

	/**
	 * @param volatilidad
	 *            the volatilidad to set
	 */
	public void setVolatilidad(Double volatilidad) {
		this.volatilidad = volatilidad;
	}

	/**
	 * @return the tasaLibreRieso
	 */
	public Double getTasaLibreRieso() {
		return tasaLibreRieso;
	}

	/**
	 * @param tasaLibreRieso
	 *            the tasaLibreRieso to set
	 */
	public void setTasaLibreRieso(Double tasaLibreRieso) {
		this.tasaLibreRieso = tasaLibreRieso;
	}

	/**
	 * @return the k
	 */
	public Double getK() {
		return k;
	}

	/**
	 * @param k
	 *            the k to set
	 */
	public void setK(Double k) {
		this.k = k;
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
	 * @return the periodos
	 */
	public Integer getPeriodos() {
		return periodos;
	}

	/**
	 * @param periodos
	 *            the periodos to set
	 */
	public void setPeriodos(Integer periodos) {
		this.periodos = periodos;
	}

	/**
	 * @return the up
	 */
	public Double getUp() {
		return up;
	}

	/**
	 * @param up
	 *            the up to set
	 */
	public void setUp(Double up) {
		this.up = up;
	}

	/**
	 * @return the down
	 */
	public Double getDown() {
		return down;
	}

	/**
	 * @param down
	 *            the down to set
	 */
	public void setDown(Double down) {
		this.down = down;
	}

	/**
	 * @return the probabilidad
	 */
	public Double getProbabilidad() {
		return probabilidad;
	}

	/**
	 * @param probabilidad
	 *            the probabilidad to set
	 */
	public void setProbabilidad(Double probabilidad) {
		this.probabilidad = probabilidad;
	}

	/**
	 * Calcula la variable up.
	 */
	private void calculaUp() {
		this.up = Math.exp(this.volatilidad * Math.sqrt(this.tiempo));
	}

	/**
	 * Calcula la variable down.
	 */
	private void calculaDown() {
		this.down = Math.exp(-this.volatilidad * Math.sqrt(this.tiempo));
	}

	/**
	 * Calcula la variable probabilidad.
	 */
	private void calculaProbabilidad() {
		this.probabilidad = ((Math.exp(this.tasaLibreRieso * this.tiempo) - down))
				/ (up - down);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArbolBinarioApp ab = new ArbolBinarioApp(270.97, 0.5911, 0.0029, 150.0,
				1.0 / 12, 7);
		ab.getAb().construir();
		ab.getAb().calculaPrecioSubyacente(ab.getUp(), ab.getDown());
		ab.getAb().calculaPrecioOpcionEuropea(ab.getK(),
				ab.getTasaLibreRieso(), ab.getTiempo(), ab.getProbabilidad());

		//System.out.println("PreOrden: " + ab.getAb().preorden());
		//System.out.println("InOrden: " + ab.getAb().inorden());
		//System.out.println("PostOrden: " + ab.getAb().postorden());
		//System.out.println("altura: " + ab.getAb().calcularAltura());
		//System.out.println("contar: " + ab.getAb().contar());
		//System.out.println("sumar: " + ab.getAb().sumar());
		//System.out.println("mayor: " + ab.getAb().buscarMayor());

		ArbolBinarioApp ab2 = new ArbolBinarioApp(270.97, 0.5911, 0.0029,
				150.0, 1.0 / 12, 7);
		ab2.getAb().construir();
		ab2.getAb().calculaPrecioSubyacente(ab2.getUp(), ab2.getDown());
		ab2.getAb()
				.calculaPrecioOpcionAmericana(ab2.getK(),
						ab2.getTasaLibreRieso(), ab2.getTiempo(),
						ab2.getProbabilidad());

		//System.out.println("PreOrden: " + ab2.getAb().preorden());
		//System.out.println("InOrden: " + ab2.getAb().inorden());
		//System.out.println("PostOrden: " + ab2.getAb().postorden());
		//System.out.println("altura: " + ab2.getAb().calcularAltura());
		//System.out.println("contar: " + ab2.getAb().contar());
		//System.out.println("sumar: " + ab2.getAb().sumar());
		//System.out.println("mayor: " + ab2.getAb().buscarMayor());
	}
}
