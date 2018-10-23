package mx.com.libreria.estructuras;

import java.io.Serializable;
import java.util.Vector;

public class ContadorFrecuencias implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3566298915818061464L;
	
	private Vector<ElementoFrecuencias> datos;
	
	public ContadorFrecuencias() { 
		datos = new Vector<ElementoFrecuencias>();
	}

	public Vector<ElementoFrecuencias> getDatos() {
		return datos;
	}

	public void setDatos(Vector<ElementoFrecuencias> datos) {
		this.datos = datos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
