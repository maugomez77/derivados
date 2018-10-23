package mx.com.libreria.math;

public class Nodo {

	/**
	 * El id del nodo.
	 */
	private Integer id;

	/**
	 * El subyacente del nodo.
	 */
	private Double subyacente;

	/**
	 * El precio del nodo.
	 */
	private Double precio;

	/**
	 * El padre del nodo.
	 */
	private Nodo padre;

	/**
	 * El hijo izquierdo del nodo.
	 */
	private Nodo hijoIzq;

	/**
	 * El hijo derecho del nodo.
	 */
	private Nodo hijoDer;

	/**
	 * Si el nodo es hijo izquierdo.
	 */
	private Boolean esHijoIzq;

	/**
	 * Si el valor es de si ejercer.
	 */
	private Boolean valorEjercer;

	/**
	 * Constructor.
	 * 
	 * @param id
	 *            El Id del nodo.
	 */
	public Nodo(Integer id) {
		this.id = id;
		this.esHijoIzq = null;
		this.valorEjercer = null;
		this.subyacente = null;
		this.precio = null;
		this.padre = null;
		this.hijoDer = null;
		this.hijoIzq = null;
	}

	/**
	 * Constructor.
	 * 
	 * @param id
	 *            El id del nodo.
	 * @param esHijoIzq
	 *            Si es hijo izquiero.
	 */
	public Nodo(Integer id, Boolean esHijoIzq) {
		this.id = id;
		this.esHijoIzq = esHijoIzq;
		this.valorEjercer = null;
		this.subyacente = null;
		this.precio = null;
		this.padre = null;
		this.hijoDer = null;
		this.hijoIzq = null;
	}

	/**
	 * Calcula el precio del subyacente del nodo.
	 * 
	 * @param up
	 *            La variable up.
	 * @param down
	 *            La variable down.
	 */
	public void calculaPrecioSubyacente(Double up, Double down) {
		if (this.padre != null) {
			if (this.esHijoIzq != null && this.esHijoIzq) {
				this.subyacente = this.padre.getSubyacente() * down;
			} else {
				this.subyacente = this.padre.getSubyacente() * up;
			}
		}
	}

	/**
	 * Calcula el precio de la opcion americana del nodo.
	 * 
	 * @param k
	 *            La variable K.
	 * @param tasa
	 *            La variable tasa.
	 * @param tiempo
	 *            La variable tiempo.
	 * @param probabilidad
	 *            La variable probabilidad.
	 */
	public void calculaPrecioOpcionAmericana(Double k, Double tasa,
			Double tiempo, Double probabilidad) {
		Double ejerce = k - this.subyacente;
		Double noEjerce;

		if (this.hijoDer != null && this.hijoIzq != null) {
			noEjerce = Math.exp(-tasa * tiempo)
					* (this.hijoDer.getPrecio() * probabilidad + this.hijoIzq
							.getPrecio() * (1 - probabilidad));
		} else {
			noEjerce = 0.0;
		}
		this.precio = Math.max(ejerce, noEjerce);
		//System.out.println("Max " + id + ": " + this.precio);
		if (this.precio == ejerce) {
			this.valorEjercer = true;
		} else {
			this.valorEjercer = false;
		}
	}

	/**
	 * Calcula el precio de la opcion europea del nodo.
	 * 
	 * @param k
	 *            La variable K.
	 * @param tasa
	 *            La variable tasa.
	 * @param tiempo
	 *            La variable tiempo.
	 * @param probabilidad
	 *            La variable probabilidad.
	 */
	public void calculaPrecioOpcionEuropea(Double k, Double tasa,
			Double tiempo, Double probabilidad) {
		Double ejerce = k - this.subyacente;

		if (this.hijoDer != null && this.hijoIzq != null) {
			this.precio = Math.exp(-tasa * tiempo)
					* (this.hijoDer.getPrecio() * probabilidad + this.hijoIzq
							.getPrecio() * (1 - probabilidad));
		} else {
			this.precio = Math.max(ejerce, 0.0);
		}
		//System.out.println("Max " + id + ": " + this.precio);
		if (this.precio == ejerce) {
			this.valorEjercer = true;
		} else {
			this.valorEjercer = false;
		}
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the subyacente
	 */
	public Double getSubyacente() {
		return subyacente;
	}

	/**
	 * @param subyacente
	 *            the subyacente to set
	 */
	public void setSubyacente(Double subyacente) {
		this.subyacente = subyacente;
	}

	/**
	 * @return the precio
	 */
	public Double getPrecio() {
		return precio;
	}

	/**
	 * @param precio
	 *            the precio to set
	 */
	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	/**
	 * @return the padre
	 */
	public Nodo getPadre() {
		return padre;
	}

	/**
	 * @param padre
	 *            the padre to set
	 */
	public void setPadre(Nodo padre) {
		this.padre = padre;
	}

	/**
	 * @return the hijoIzq
	 */
	public Nodo getHijoIzq() {
		return hijoIzq;
	}

	/**
	 * @param hijoIzq
	 *            the hijoIzq to set
	 */
	public void setHijoIzq(Nodo hijoIzq) {
		this.hijoIzq = hijoIzq;
	}

	/**
	 * @return the hijoDer
	 */
	public Nodo getHijoDer() {
		return hijoDer;
	}

	/**
	 * @param hijoDer
	 *            the hijoDer to set
	 */
	public void setHijoDer(Nodo hijoDer) {
		this.hijoDer = hijoDer;
	}

	/**
	 * @return the esHijoIzq
	 */
	public Boolean getEsHijoIzq() {
		return esHijoIzq;
	}

	/**
	 * @param esHijoIzq
	 *            the esHijoIzq to set
	 */
	public void setEsHijoIzq(Boolean esHijoIzq) {
		this.esHijoIzq = esHijoIzq;
	}

	/**
	 * @return the valorEjercer
	 */
	public Boolean getValorEjercer() {
		return valorEjercer;
	}

	/**
	 * @param valorEjercer
	 *            the valorEjercer to set
	 */
	public void setValorEjercer(Boolean valorEjercer) {
		this.valorEjercer = valorEjercer;
	}
}
