package mx.com.libreria.estructuras;

import java.io.Serializable;
import java.util.Vector;

public class DatosOpciones implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8505436411619509714L;
	Vector<Opciones> datos;
	public DatosOpciones() { 
		datos = new Vector<Opciones>();
	}
	
	public Vector<Opciones> getDatos() {
		return datos;
	}
	public void setDatos(Vector<Opciones> datos) {
		this.datos = datos;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
