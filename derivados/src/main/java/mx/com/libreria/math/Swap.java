package mx.com.libreria.math;

import java.util.LinkedList;
import java.util.List;

public class Swap {

	/**
	 * Las tasas libor continuas del swap.
	 */
	private List<SwapRow> tasas;

	/**
	 * El nocional del swap.
	 */
	private Double nocional;

	/**
	 * El interes que se recibe (compuesto semestralmente).
	 */
	private Double recibeInteres;

	/**
	 * El interes que se paga (compuesto semestralmente).
	 */
	private Double pagaInteres;

	/**
	 * El valor del swap.
	 */
	private Double valorSwap;

	/**
	 * Constructor.
	 */
	public Swap() {

	}

	/**
	 * Constructor.
	 * 
	 * @param tasas
	 *            Las tasas libor.
	 * @param nocional
	 *            El nocional.
	 * @param recibeInteres
	 *            El interes que se recibe.
	 * @param pagaInteres
	 *            El interes que se paga.
	 */
	public Swap(List<SwapRow> tasas, Double nocional, Double recibeInteres,
			Double pagaInteres) {
		this.tasas = tasas;
		this.nocional = nocional;
		this.recibeInteres = recibeInteres;
		this.pagaInteres = pagaInteres;
	}

	/**
	 * Calcula la tasa forward del swap (compuesta semestralmente).
	 * 
	 * @param interesAnualContinuo
	 *            El interes continuo anual.
	 * @return La tasa forward compuesta semestralmente.
	 */
	private Double tasaForwardCompuesta(Double interesAnualContinuo) {
		return (Math.sqrt(Math.exp(interesAnualContinuo)) - 1) * 2;
	}

	/**
	 * Calcula la tasa forward del swap entre el mes 1 y mes 2 (anual continua).
	 * 
	 * @param tiempo1
	 *            El tiempo1.
	 * @param tasa1
	 *            La tasa1.
	 * @param tiempo2
	 *            El tiempo2.
	 * @param tasa2
	 *            La tasa2.
	 * @return
	 */
	private Double tasaForwardContinua(Double tasa1, Double tiempo1,
			Double tasa2, Double tiempo2) {
		return (tasa2 * (tiempo2 / 12) - tasa1 * (tiempo1 / 12))
				/ ((tiempo2 / 12) - (tiempo1 / 12));
	}

	/**
	 * Calcula el valor del swap para un tiempo dado.
	 * 
	 * @param L
	 *            EL nocional del swap.
	 * @param Rk
	 *            La tasa fija.
	 * @param Rf
	 *            La tasa variable (forward).
	 * @param R
	 *            La tasa R.
	 * @param T
	 *            El tiempo.
	 * @return
	 */
	private Double valorSwap(Double L, Double Rk, Double Rf, Double R, Double T) {
		return L * ((Rk / 2) - (Rf / 2)) * Math.exp((-R * (T / 12)));
	}

	/**
	 * Calcula el valor de un SWAP usando el metodo de FRA.
	 * 
	 * @return La valuacion del SWAP por el metodo de FRA.
	 */
	public Double calculaValuacionFRA() {
		Double valuacion = 0.0;
		for (int i = 0; i < tasas.size(); i++) {
			if (i == 0) {
				valuacion += valorSwap(this.nocional, this.recibeInteres,
						this.pagaInteres, tasas.get(i).getTasa(), tasas.get(i)
								.getTiempo());
			} else {
				Double tasaForward = tasaForwardCompuesta(tasaForwardContinua(
						tasas.get(i).getTasa(), tasas.get(i).getTiempo(), tasas
								.get(i - 1).getTasa(), tasas.get(i - 1)
								.getTiempo()));
				valuacion += valorSwap(this.nocional, this.recibeInteres,
						tasaForward, tasas.get(i).getTasa(), tasas.get(i)
								.getTiempo());
			}
		}
		return valuacion;
	}

	/**
	 * 
	 * @return El valor fijo.
	 */
	private Double bFix() {
		Double m = (this.recibeInteres / 2) * this.nocional;
		Double bfix = 0.0;
		for (int i = 0; i < tasas.size(); i++) {
			if (i == tasas.size() - 1) {
				bfix += Math.exp(-tasas.get(i).getTasa()
						* (tasas.get(i).getTiempo() / 12))
						* (m + this.nocional);
			} else {
				bfix += Math.exp(-tasas.get(i).getTasa()
						* (tasas.get(i).getTiempo() / 12))
						* m;
			}
		}
		return bfix;
	}

	/**
	 * 
	 * @return El valor variable.
	 */
	private Double bFl() {
		Double K = (this.pagaInteres / 2) * this.nocional;
		Double bfl = (this.nocional + K)
				* Math.exp(-tasas.get(0).getTasa()
						* (tasas.get(0).getTiempo() / 12));
		return bfl;
	}

	/**
	 * Calcula el valor de un SWAP usando el metodo de Bonos.
	 * 
	 * @return La valuacion del SWAP por el metodo de Bonos.
	 */
	public Double calculaValuacionBonos() {
		return bFix() - bFl();
	}

	/**
	 * @return the tasas
	 */
	public List<SwapRow> getTasas() {
		return tasas;
	}

	/**
	 * @param tasas
	 *            the tasas to set
	 */
	public void setTasas(List<SwapRow> tasas) {
		this.tasas = tasas;
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
		return nocional != null ? "" + nocional : "";
	}

	/**
	 * @param nocional
	 *            the nocional to set
	 */
	public void setNocional(Double nocional) {
		this.nocional = nocional;
	}

	/**
	 * @return the recibeInteres
	 */
	public Double getRecibeInteres() {
		return recibeInteres;
	}

	/**
	 * @return the recibeInteres as String
	 */
	public String getRecibeInteresString() {
		return recibeInteres != null ? "" + recibeInteres : "";
	}

	/**
	 * @param recibeInteres
	 *            the recibeInteres to set
	 */
	public void setRecibeInteres(Double recibeInteres) {
		this.recibeInteres = recibeInteres;
	}

	/**
	 * @return the pagaInteres
	 */
	public Double getPagaInteres() {
		return pagaInteres;
	}

	/**
	 * @return the pagaInteres as String
	 */
	public String getPagaInteresString() {
		return pagaInteres != null ? "" + pagaInteres : "";
	}

	/**
	 * @param pagaInteres
	 *            the pagaInteres to set
	 */
	public void setPagaInteres(Double pagaInteres) {
		this.pagaInteres = pagaInteres;
	}

	/**
	 * @return the valorSwap
	 */
	public Double getValorSwap() {
		return valorSwap;
	}

	/**
	 * @return the valorSwap as String
	 */
	public String getValorSwapString() {
		return valorSwap != null ? "" + valorSwap : "";
	}

	/**
	 * @param valorSwap
	 *            the valorSwap to set
	 */
	public void setValorSwap(Double valorSwap) {
		this.valorSwap = valorSwap;
	}

	public static void main(String[] args) {
		List<SwapRow> list = new LinkedList<SwapRow>();
		list.add(new SwapRow(3.0, .10));
		list.add(new SwapRow(9.0, .105));
		list.add(new SwapRow(15.0, .11));

		//Swap swap = new Swap(list, 100.0, .08, .102);
		//System.out.println("FRA: " + swap.calculaValuacionFRA());
		//System.out.println("Bonos: " + swap.calculaValuacionBonos());
	}
}
