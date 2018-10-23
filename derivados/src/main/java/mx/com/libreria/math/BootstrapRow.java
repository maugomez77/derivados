package mx.com.libreria.math;

public class BootstrapRow {

	private static final Integer VALOR_MENSUAL = 12;

	/**
	 * El valor del nocional del bono.
	 */
	private Double nocional;

	/**
	 * El tiempo de vencimiento del bono.
	 */
	private Double vencimiento;

	/**
	 * El cupon del bono.
	 */
	private Double cupon;

	/**
	 * El precio de mercado del bono.
	 */
	private Double precioMdo;

	/**
	 * El valor m.
	 */
	private Double m;

	/**
	 * La tasa cero del bono.
	 */
	private Double tasaCero;

	/**
	 * La tasa forward del bono.
	 */
	private Double tasaForward;

	/**
	 * Constructor.
	 * 
	 * @param nocional
	 *            El valor nocional del bono.
	 * @param vencimiento
	 *            El tiempo de vencimiento anualizado en decimales.
	 * @param cupon
	 *            El valor del cupon del bono.
	 * @param precioMdo
	 *            El precio de mercado del bono.
	 */
	public BootstrapRow(Double nocional, Double vencimiento, Double cupon,
			Double precioMdo) {
		this.nocional = nocional;
		this.vencimiento = vencimiento;
		this.cupon = cupon;
		this.precioMdo = precioMdo;
		this.m = VALOR_MENSUAL / (this.vencimiento * VALOR_MENSUAL);
	}

	/**
	 * @return the nocional
	 */
	public Double getNocional() {
		return nocional;
	}

	/**
	 * @return the nocional as String
	 */
	public String getNocionalString() {
		return "" + (nocional != null ? nocional : "");
	}

	/**
	 * @param nocional
	 *            the nocional to set
	 */
	public void setNocional(Double nocional) {
		this.nocional = nocional;
	}

	/**
	 * @return the vencimiento
	 */
	public Double getVencimiento() {
		return vencimiento;
	}

	/**
	 * @return the vencimiento as String
	 */
	public String getVencimientoString() {
		return "" + (vencimiento != null ? vencimiento : "");
	}

	/**
	 * @param vencimiento
	 *            the vencimiento to set
	 */
	public void setVencimiento(Double vencimiento) {
		this.vencimiento = vencimiento;
	}

	/**
	 * @return the cupon
	 */
	public Double getCupon() {
		return cupon;
	}

	/**
	 * @return the cupon as String
	 */
	public String getCuponString() {
		return "" + (cupon != null ? cupon : "");
	}

	/**
	 * @param cupon
	 *            the cupon to set
	 */
	public void setCupon(Double cupon) {
		this.cupon = cupon;
	}

	/**
	 * @return the precioMdo
	 */
	public Double getPrecioMdo() {
		return precioMdo;
	}

	/**
	 * @return the precioMdo as String
	 */
	public String getPrecioMdoString() {
		return "" + (precioMdo != null ? precioMdo : "");
	}

	/**
	 * @param precioMdo
	 *            the precioMdo to set
	 */
	public void setPrecioMdo(Double precioMdo) {
		this.precioMdo = precioMdo;
	}

	/**
	 * @return the m
	 */
	public Double getM() {
		return m;
	}

	/**
	 * @param m
	 *            the m to set
	 */
	public void setM(Double m) {
		this.m = m;
	}

	/**
	 * @return the tasaCero
	 */
	public Double getTasaCero() {
		return tasaCero;
	}

	/**
	 * @return the tasaCero as String
	 */
	public String getTasaCeroString() {
		return "" + (tasaCero != null ? tasaCero * 100 : "");
	}

	/**
	 * @param tasaCero
	 *            the tasaCero to set
	 */
	public void setTasaCero(Double tasaCero) {
		this.tasaCero = tasaCero;
	}

	/**
	 * @return the tasaForward
	 */
	public Double getTasaForward() {
		return tasaForward;
	}

	/**
	 * @return the tasaForward as String
	 */
	public String getTasaForwardString() {
		return "" + (tasaForward != null ? tasaForward * 100 : "");
	}

	/**
	 * @param tasaForward
	 *            the tasaForward to set
	 */
	public void setTasaForward(Double tasaForward) {
		this.tasaForward = tasaForward;
	}

	/**
	 * @return La cadena con la informacion del Bono.
	 */
	public String toString() {
		StringBuffer info = new StringBuffer();
		info.append("Nocional: " + this.getNocional());
		info.append(", Cupon: " + this.getCupon());
		info.append(", PrecioMdo: " + this.getPrecioMdo());
		info.append(", Vencimiento: " + this.getVencimiento());
		info.append(", Tasa Cero: " + this.getTasaCeroString());
		info.append(", Tasa Forward: " + this.getTasaForwardString());
		return info.toString();
	}
}
