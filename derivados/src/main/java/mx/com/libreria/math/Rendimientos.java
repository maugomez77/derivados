package mx.com.libreria.math;

import mx.com.libreria.estructuras.DataRow;

public class Rendimientos {
	
	public static void calculaRendimientoCompuesto(DataRow datos, int indice) { 
		double serieMenosUno = 0, serieActual = 0;
		for (int i=indice; i < datos.getDatos().size(); i++) {
			serieMenosUno = datos.getDatos().get(i-1).getSerie();
			serieActual = datos.getDatos().get(i).getSerie();
			datos.getDatos().get(i).setRendimientoCompuesto((serieActual - serieMenosUno) / serieMenosUno);
		}
	}
	
	public static void calculaRendimientoContinuo(DataRow datos, int indice) { 
		double serieMenosUno = 0, serieActual = 0;
		for (int i=indice; i < datos.getDatos().size(); i++) {
			serieMenosUno = datos.getDatos().get(i-1).getSerie();
			serieActual = datos.getDatos().get(i).getSerie();
			datos.getDatos().get(i).setRendimientoContinuo(Math.log(serieActual / serieMenosUno));
		}		
	}
	
	public static void calculaRendimientoNormalizadoContinuo(DataRow datos, int indice) {
		
		double media = Calculate.getMedia(datos, 0, indice);
		double desv = Calculate.getDesviacionEstandard(datos, 0, indice);
		
		for (int i=indice; i < datos.getDatos().size(); i++) {
			datos.getDatos().get(i).setRendimientoContinuoNormalizado((datos.getDatos().get(i).getRendimientoContinuo() - media) / desv); 			
		}
	}
}
