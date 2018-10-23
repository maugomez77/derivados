package mx.com.libreria.estructuras;

import java.util.Vector;

public class DataRow {
	
	private String labelA;
	private String labelB;
	
	private Vector<Datos> datos;

	public DataRow() { 
		datos = new Vector<Datos>();
	}
	
	public String getLabelA() {
		return labelA;
	}

	public void setLabelA(String labelA) {
		this.labelA = labelA;
	}

	public String getLabelB() {
		return labelB;
	}

	public void setLabelB(String labelB) {
		this.labelB = labelB;
	}

	public Vector<Datos> getDatos() {
		return datos;
	}

	public void setDatos(Vector<Datos> datos) {
		this.datos = datos;
	}
}
