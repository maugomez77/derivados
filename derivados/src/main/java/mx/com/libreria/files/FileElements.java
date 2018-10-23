package mx.com.libreria.files;

import java.io.Serializable;

public class FileElements implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8494136312692448644L;
	
	private String nameFile;
	private String periodicidad;
	private String tipoRend;
	private String saltos;
	private boolean multiParte;
	public String getNameFile() {
		return nameFile;
	}
	public void setNameFile(String nameFile) {
		this.nameFile = nameFile;
	}
	public String getPeriodicidad() {
		return periodicidad;
	}
	public void setPeriodicidad(String periodicidad) {
		this.periodicidad = periodicidad;
	}
	public String getTipoRend() {
		return tipoRend;
	}
	public void setTipoRend(String tipoRend) {
		this.tipoRend = tipoRend;
	}
	public String getSaltos() {
		return saltos;
	}
	public void setSaltos(String saltos) {
		this.saltos = saltos;
	}
	public boolean isMultiParte() {
		return multiParte;
	}
	public void setMultiParte(boolean multiParte) {
		this.multiParte = multiParte;
	}

	
}
