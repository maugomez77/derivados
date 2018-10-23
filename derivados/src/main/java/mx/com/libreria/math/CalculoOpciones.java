package mx.com.libreria.math;

import java.awt.Font;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.HorizontalAlignment;

import mx.com.libreria.estructuras.DatosOpciones;
import mx.com.libreria.estructuras.Opciones;

public class CalculoOpciones {
	
	public static double compraCall(double So, double K, double r, double vol, double T) { 
		return Math.max(So - K, 0) - BlackAndScholes.BSCall(So, K, r, vol, T);
	}
	
	public static double compraCall(double So, double K, double prima) {
		return Math.max(So - K, 0) - prima;
	}
	
	public static double ventaCall(double So, double K, double r, double vol, double T) { 
		return -Math.max(So - K, 0) + BlackAndScholes.BSCall(So, K, r, vol, T);
	}
	
	public static double ventaCall(double So, double K, double prima) {
		return -Math.max(So - K, 0) + prima;
	}
	
	public static void main(String[] args) { 
		//System.out.println("Valor: " + CalculoOpciones.compraCall(40, 50, 1));			
		DatosOpciones datos = CalculoOpciones.getGraficaCompraCallOpcion(40, 50, 1);
		
		for (int i=0; i < datos.getDatos().size(); i++) { 
			//System.out.println("datos: " + datos.getDatos().get(i).getX() + " " + datos.getDatos().get(i).getY());
		}
	}
		
	public static double compraPut(double So, double K, double r, double vol, double T) {
		return Math.max(K - So, 0) - BlackAndScholes.BSPut(So, K, r, vol, T);
	}
	
	public static double compraPut(double So, double K, double prima) { 
		return Math.max(K - So, 0) - prima;
	}
	
	public static double ventaPut(double So, double K, double r, double vol, double T) {
		return -Math.max(K - So, 0) + BlackAndScholes.BSPut(So, K, r, vol, T);
	}
	
	public static double ventaPut(double So, double K, double prima) { 
		return -Math.max(K - So, 0) + prima;
	}
	
	public static DatosOpciones getGraficaCompraCallOpcion(double So, double K, double prima) {
		
		DatosOpciones datos = new DatosOpciones();
		Opciones elemento = null;
		
		double saltos = So / 5;
		
		for (double rangoInicial = saltos, i = 0; i < 10; i++) {
			elemento = new Opciones();
			elemento.setX(rangoInicial);
			elemento.setY(CalculoOpciones.compraCall(rangoInicial, K, prima));
			datos.getDatos().add(elemento);
			rangoInicial += saltos;
		}
		
		return datos;
	}	
	
	public static DatosOpciones getGraficaVentaCallOpcion(double So, double K, double prima) {
		
		DatosOpciones datos = new DatosOpciones();
		Opciones elemento = null;
		
		double saltos = So / 5;
		
		for (double rangoInicial = saltos, i = 0; i < 10; i++) {
			elemento = new Opciones();
			elemento.setX(rangoInicial);
			elemento.setY(CalculoOpciones.ventaCall(rangoInicial, K, prima));
			datos.getDatos().add(elemento);
			rangoInicial += saltos;
		}
		
		return datos;
	}

	
	public static DatosOpciones getGraficaCompraPutOpcion(double So, double K, double prima) {
		
		DatosOpciones datos = new DatosOpciones();
		Opciones elemento = null;
		
		double saltos = So / 5;
		
		for (double rangoInicial = saltos, i = 0; i < 10; i++) {
			elemento = new Opciones();
			elemento.setX(rangoInicial);
			elemento.setY(CalculoOpciones.compraPut(rangoInicial, K, prima));
			datos.getDatos().add(elemento);
			rangoInicial += saltos;
		}
		
		return datos;
	}	
	
	public static DatosOpciones getGraficaVentaPutOpcion(double So, double K, double prima) {
		
		DatosOpciones datos = new DatosOpciones();
		Opciones elemento = null;
		
		double saltos = So / 5;
		
		for (double rangoInicial = saltos, i = 0; i < 10; i++) {
			elemento = new Opciones();
			elemento.setX(rangoInicial);
			elemento.setY(CalculoOpciones.ventaPut(rangoInicial, K, prima));
			datos.getDatos().add(elemento);
			rangoInicial += saltos;
		}
		
		return datos;
	}
	/**
	 * Creates a sample time series chart.
	 * 
	 * @return a time series chart.
	 */
	public static JFreeChart createTimeSeriesChartBest(DatosOpciones datos, String leyenda, String titulo, String subtitle1, String subtitle2) {
		XYDataset dataset = createDataset(datos, leyenda);
		//JFreeChart chart = createChart(dataset);		
		JFreeChart chart = ChartFactory.createXYLineChart(titulo, 
				"Precio Spot", // x axis label
				"Utilidad / Perdida", // y axis label
				dataset, // data
				PlotOrientation.VERTICAL, true, // include legend
				true, // tooltips
				false // urls
				);				
		
		chart.getTitle().setFont(new Font("Tahoma", Font.BOLD, 20));
		
		TextTitle dataTitle= new TextTitle(subtitle1);
		dataTitle.setHorizontalAlignment(HorizontalAlignment.CENTER);
		dataTitle.setFont(new Font("Verdana", Font.ITALIC, 12));		
		chart.addSubtitle(dataTitle);
		
	    dataTitle= new TextTitle(subtitle2);
		dataTitle.setHorizontalAlignment(HorizontalAlignment.CENTER);
		dataTitle.setFont(new Font("Verdana", Font.ITALIC, 12));		
		chart.addSubtitle(dataTitle);
		
		return chart;
	}
	
	private static XYDataset createDataset(DatosOpciones datos, String nombreSerie) {
		XYSeries series1 = new XYSeries(nombreSerie);		
		for (int i=0; i < datos.getDatos().size(); i++) {
			series1.add(datos.getDatos().get(i).getX(), datos.getDatos().get(i).getY());
		}				
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series1);		
		return dataset;
	}
}
