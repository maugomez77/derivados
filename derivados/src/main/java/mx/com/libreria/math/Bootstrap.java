package mx.com.libreria.math;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Bootstrap {

	private List<BootstrapRow> bonos;

	/**
	 * Calcula el valor del rendimiento compuesto anualizado del bono cuando es
	 * cupon cero.
	 * 
	 * @param row
	 *            Datos de un bono.
	 * @return El valor del rendimiento compuesto anualizado.
	 */
	private Double calculaRendimientoCompuestoCuponCero(BootstrapRow row) {
		return ((row.getNocional() - row.getPrecioMdo()) / row.getPrecioMdo())
				* row.getM();
	}

	/**
	 * Calcula el valor del rendimiento continuo anualizado del bono cuando es
	 * cupon cero.
	 * 
	 * @param row
	 *            Datos de un bono.
	 * @return El valor del rendimiento continuo anualizado.
	 */
	private Double calculaRendimientoContinuoCuponCero(BootstrapRow row) {
		return row.getM()
				* Math.log(1 + (calculaRendimientoCompuestoCuponCero(row) / row
						.getM()));
	}

	/**
	 * Calcula el valor del rendimiento continuo anuzalizado de un bono cuando
	 * paga cupon.
	 * 
	 * @param toIndex
	 * @param multiplicador
	 *            El cupon cero semestral.
	 * @return El rendimiento de un bono cuando paga cupon.
	 */
	private Double calculaRendimientoContinuo(Integer toIndex,
			Double multiplicador) {
		BootstrapRow row = null;
		Double sumatoria = 0.0;
		for (int i = 0; i <= toIndex; i++) {
			row = bonos.get(i);
			if (row.getVencimiento() >= 0.5) {
				if (i < toIndex) {
					sumatoria += Math.exp((-row.getTasaCero())
							* row.getVencimiento())
							* multiplicador;
				} else {
					sumatoria = row.getPrecioMdo() - sumatoria;
					sumatoria = sumatoria / (row.getNocional() + multiplicador);
					sumatoria = Math.log(sumatoria) / -row.getVencimiento();
				}
			}
		}
		return sumatoria;
	}

	/**
	 * Calcula la tasa cero de una lista de bonos.
	 */
	public void calculaTasasCero() {
		BootstrapRow row = null;
		for (int i = 0; i < bonos.size(); i++) {
			row = bonos.get(i);
			if (row.getCupon() == 0.0) {
				row.setTasaCero(calculaRendimientoContinuoCuponCero(row));
			} else {
				// Verificar que se debe hacer cuando el tiempo no es semestral
				// exacto (i.e. 6, 12, 18, 24, etc.)
				row.setTasaCero(calculaRendimientoContinuo(i,
						row.getCupon() / 2));
			}
		}
	}

	/**
	 * Calcula la tasa forward de una lista de bonos.
	 */
	public void calculaTasasForward() {
		for (int i = 1; i < bonos.size(); i++) {
			Double tasaForward = (bonos.get(i).getTasaCero() * bonos.get(i)
					.getVencimiento())
					- (bonos.get(i - 1).getTasaCero() * bonos.get(i - 1)
							.getVencimiento());
			bonos.get(i).setTasaForward(tasaForward);
		}
	}

	/**
	 * @return the bonos
	 */
	public List<BootstrapRow> getBonos() {
		return bonos;
	}

	/**
	 * @param bonos
	 *            the bonos to set
	 */
	public void setBonos(List<BootstrapRow> bonos) {
		this.bonos = bonos;
	}

	public static void main(String[] args) {
		List<BootstrapRow> bonos = new LinkedList<BootstrapRow>();
		bonos.add(new BootstrapRow(100.0, 0.25, 0.0, 97.5));
		bonos.add(new BootstrapRow(100.0, 0.5, 0.0, 94.9));
		bonos.add(new BootstrapRow(100.0, 1.0, 0.0, 90.0));
		bonos.add(new BootstrapRow(100.0, 1.5, 8.0, 96.0));
		bonos.add(new BootstrapRow(100.0, 2.0, 12.0, 101.6));

		Bootstrap b = new Bootstrap();
		b.setBonos(bonos);
		b.calculaTasasCero();
		b.calculaTasasForward();

		for (Iterator<BootstrapRow> it = b.getBonos().iterator(); it.hasNext();) {
			//System.out.println(it.next());
		}
	}
}
