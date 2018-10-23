package mx.com.libreria.math;

import mx.com.libreria.estructuras.DataRow;

public class Calculate {
	
	public static double getMax(DataRow datos, int type, int indice) { 
		double max = 0;
		for (int i=indice; i < datos.getDatos().size(); i++) {
			if (type == 0) {
				if (max < datos.getDatos().get(i).getRendimientoContinuo()) { 
					max = datos.getDatos().get(i).getRendimientoContinuo();
				}					
			} else if (type == 1) { 
				if (max < datos.getDatos().get(i).getRendimientoContinuoNormalizado()) {
					max =  datos.getDatos().get(i).getRendimientoContinuoNormalizado();
				}
			} else if (type == 2) {
				if (max < datos.getDatos().get(i).getRendimientoCompuesto()) { 
					max += datos.getDatos().get(i).getRendimientoCompuesto();
				}
			}
			
		}
		return max;
	}
	
	public static double getMin(DataRow datos, int type, int indice) { 
		double min = 100000000;
		for (int i=indice; i < datos.getDatos().size(); i++) {
			if (type == 0) {
				if (min > datos.getDatos().get(i).getRendimientoContinuo()) { 
					min = datos.getDatos().get(i).getRendimientoContinuo();
				}					
			} else if (type == 1) { 
				if (min > datos.getDatos().get(i).getRendimientoContinuoNormalizado()) {
					min =  datos.getDatos().get(i).getRendimientoContinuoNormalizado();
				}
			} else if (type == 2) {
				if (min > datos.getDatos().get(i).getRendimientoCompuesto()) { 
					min += datos.getDatos().get(i).getRendimientoCompuesto();
				}
			}
			
		}
		return min;
	}
	
	public static double getMedia(DataRow datos, int type, int indice) {
		double media = 0;
		for (int i=indice; i < datos.getDatos().size(); i++) {
			if (type == 0) { 
				media += datos.getDatos().get(i).getRendimientoContinuo();	
			} else if (type == 1) { 
				media += datos.getDatos().get(i).getRendimientoContinuoNormalizado();
			} else if (type == 2) { 
				media += datos.getDatos().get(i).getRendimientoCompuesto();
			}
			
		}
		return media / (datos.getDatos().size() - indice);
	}
	
	public static double getVarianza(DataRow datos, int type, int indice) { 
		double media = getMedia(datos, type, indice);
		double varianza = 0;
		for (int i=indice; i < datos.getDatos().size(); i++) {
			if (type == 0) {
				varianza += Math.pow((datos.getDatos().get(i).getRendimientoContinuo() - media), 2);
			} else if (type == 1) {
				varianza += Math.pow((datos.getDatos().get(i).getRendimientoContinuoNormalizado() - media), 2);
			} else if (type == 2) {
				varianza += Math.pow((datos.getDatos().get(i).getRendimientoCompuesto() - media), 2);
			}
		}
		return varianza / (datos.getDatos().size() - indice);		
	}
	
	public static double getDesviacionEstandard(DataRow datos, int type, int indice) {
		double media = getMedia(datos, type, indice);
		double desv = 0;
		for (int i=indice; i < datos.getDatos().size(); i++) {
			if (type == 0) {
				desv += Math.pow((datos.getDatos().get(i).getRendimientoContinuo() - media), 2);
			} else if (type == 1) { 
				desv += Math.pow((datos.getDatos().get(i).getRendimientoContinuoNormalizado() - media), 2);
			} else if (type == 2) {
				desv += Math.pow((datos.getDatos().get(i).getRendimientoCompuesto() - media), 2);
			}
		}
		return Math.pow(desv / (datos.getDatos().size() - indice), 0.5);
	}
	
	public static double getSesgo(DataRow datos, int type, int indice) { 
		double media = getMedia(datos, type, indice);
		double sesgo = 0;
		for (int i=indice; i < datos.getDatos().size(); i++) {
			if (type == 0) {
				sesgo += Math.pow((datos.getDatos().get(i).getRendimientoContinuo() - media), 3);
			} else if (type == 1) { 
				sesgo += Math.pow((datos.getDatos().get(i).getRendimientoContinuoNormalizado() - media), 3);
			} else if (type == 2) {
				sesgo += Math.pow((datos.getDatos().get(i).getRendimientoCompuesto() - media), 3);
			}
		}		
		
		
		double denominador = (datos.getDatos().size() - indice - 1) * Math.pow(getDesviacionEstandard(datos, type, indice), 3);
		return sesgo / denominador;
	}
	
	public static double getCurtosis(DataRow datos, int type, int indice) {
		double media = getMedia(datos, type, indice);
		double curtosis = 0;
		for (int i=indice; i < datos.getDatos().size(); i++) {
			if (type == 0) { 
				curtosis += Math.pow((datos.getDatos().get(i).getRendimientoContinuo() - media), 4);
			} else if (type == 1) { 
				curtosis += Math.pow((datos.getDatos().get(i).getRendimientoContinuoNormalizado() - media), 4);
			} else if (type == 2) {
				curtosis += Math.pow((datos.getDatos().get(i).getRendimientoCompuesto() - media), 4);
			}
		}		 
		double denominador = (datos.getDatos().size() - indice - 1) * Math.pow(getDesviacionEstandard(datos, type, indice), 4);		
		return (curtosis / denominador) - 3;
	}
	
	public static void main(String[] args) {
		/*
		Vector<Double> datos = new Vector<Double>();
		datos.add((double)8);
		datos.add((double)7);
		datos.add((double)8);
		
		System.out.println("media: " + Calculate.getMedia(datos));
		System.out.println("desv: " + Calculate.getDesviacionEstandard(datos));
		System.out.println("varianza: " + Calculate.getVarianza(datos));
		System.out.println("curtosis: " + Calculate.getCurtosis(datos));
		System.out.println("sesgo: " + Calculate.getSesgo(datos));*/
	}
}
