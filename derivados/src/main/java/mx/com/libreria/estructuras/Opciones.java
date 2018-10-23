package mx.com.libreria.estructuras;

import java.io.Serializable;

public class Opciones implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7799261381908742908L;
	
	private double x;
	private double y;
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
