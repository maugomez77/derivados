package mx.com.libreria.math;

import java.awt.Color;
import java.awt.Font;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.title.TextTitle;

import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.RectangleInsets;

import mx.com.libreria.estructuras.ContadorFrecuencias;
import mx.com.libreria.estructuras.DataRow;
import mx.com.libreria.estructuras.ElementoFrecuencias;

public class Frecuencias {
	
	public static double getMaxValue(DataRow datos, int type, int indice) {

		double max = -1;
		for (int i=indice; i < datos.getDatos().size(); i++) {
			if (type == 0) {
				if (datos.getDatos().get(i).getRendimientoContinuo() > max) { 
					max = datos.getDatos().get(i).getRendimientoContinuo();
				}			 
			} else if (type == 1) {
				if (datos.getDatos().get(i).getRendimientoContinuoNormalizado() > max) {
					max = datos.getDatos().get(i).getRendimientoContinuoNormalizado();
				}
			} else if (type == 2) {
				if (datos.getDatos().get(i).getRendimientoCompuesto() > max) {
					max = datos.getDatos().get(i).getRendimientoCompuesto();
				}
			}
		}
		return max;
	}
	
	public static double getMinValue(DataRow datos, int type, int indice) {

		double min = 100000000;
		for (int i=indice; i < datos.getDatos().size(); i++) {
			if (type == 0) {
				if (datos.getDatos().get(i).getRendimientoContinuo() < min) { 
					min = datos.getDatos().get(i).getRendimientoContinuo();
				}			 
			} else if (type == 1) {
				if (datos.getDatos().get(i).getRendimientoContinuoNormalizado() < min) {
					min = datos.getDatos().get(i).getRendimientoContinuoNormalizado();
				}
			} else if (type == 2) {
				if (datos.getDatos().get(i).getRendimientoCompuesto() < min) {
					min = datos.getDatos().get(i).getRendimientoCompuesto();
				}
			}
		}
		return min;
	}
	
	private static boolean inRange(double valor, double rangoInicial, double rangoFinal) { 
		if (valor >= rangoInicial && valor <= rangoFinal) { 
			return true;
		}
		return false;
	}
	private static int getCuentaRango(DataRow datos, int type, int indice, double rangoInicial, double rangoFinal) {
		int contador = 0;
		for (int i=indice; i < datos.getDatos().size(); i++) { 
			if (type == 0) {
				if (inRange(datos.getDatos().get(i).getRendimientoContinuo(), rangoInicial, rangoFinal)) { 
					contador = contador + 1;
				}
			} else if (type == 1) {
				if (inRange(datos.getDatos().get(i).getRendimientoContinuoNormalizado(), rangoInicial, rangoFinal)) { 
					contador = contador + 1;
				}
			} else if (type == 2) { 
				if (inRange(datos.getDatos().get(i).getRendimientoCompuesto(), rangoInicial, rangoFinal)) { 
					contador = contador + 1;
				}
			}
		}		
		return contador;
	}
	
	public static JFreeChart createChart(ContadorFrecuencias frec, String leyenda, String title, String subtitle1, String subtitle2, String subtitle3) {
	
		XYSeries series1 = new XYSeries(leyenda);
		for (int i=0; i < frec.getDatos().size(); i++) {
				series1.add(frec.getDatos().get(i).getRangoInicial(), frec.getDatos().get(i).getContador());
		}				
		
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series1);
				
		// create the chart...
		JFreeChart chart = ChartFactory.createXYLineChart(title, // chart
																				// title
				"X", // x axis label
				"Y", // y axis label
				dataset, // data
				PlotOrientation.VERTICAL, true, // include legend
				true, // tooltips
				false // urls
				);
		
		// NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
		chart.setBackgroundPaint(Color.white);
		// get a reference to the plot for further customisation...
		XYPlot plot = (XYPlot) chart.getPlot();
		plot.setBackgroundPaint(Color.lightGray);
		plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
		plot.setDomainGridlinePaint(Color.white);
		plot.setRangeGridlinePaint(Color.white);
				
		// change the auto tick unit selection to integer units only...
		NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		
		chart.getTitle().setFont(new Font("Tahoma", Font.BOLD, 20));
		
		TextTitle dataTitle= new TextTitle(subtitle1);
		dataTitle.setHorizontalAlignment(HorizontalAlignment.CENTER);
		dataTitle.setFont(new Font("Verdana", Font.ITALIC, 12));		
		chart.addSubtitle(dataTitle);
		
	    dataTitle= new TextTitle(subtitle2);
		dataTitle.setHorizontalAlignment(HorizontalAlignment.CENTER);
		dataTitle.setFont(new Font("Verdana", Font.ITALIC, 12));		
		chart.addSubtitle(dataTitle);
		
		dataTitle= new TextTitle(subtitle3);
		dataTitle.setHorizontalAlignment(HorizontalAlignment.CENTER);
		dataTitle.setFont(new Font("Verdana", Font.ITALIC, 12));		
		chart.addSubtitle(dataTitle);
		
		return chart;	
	}
	
	public static ContadorFrecuencias calculaFrecuencias(DataRow datos, int type, int indice, 
			double rangoInicial, double rangoFinal, double saltos) { 
		//
		//double numeroSaltos = Math.floor((rangoFinal - rangoInicial) / saltos);
		
		ContadorFrecuencias frec = new ContadorFrecuencias();
		
		while (rangoInicial <= rangoFinal) {
			ElementoFrecuencias elemento = new ElementoFrecuencias();
			elemento.setRangoInicial(rangoInicial);
			elemento.setRangoFinal(rangoInicial + saltos);
			elemento.setContador(getCuentaRango(datos, type, indice, rangoInicial, rangoInicial + saltos));			
			frec.getDatos().add(elemento);						
			rangoInicial += saltos + .00001;
		}
		
		return frec;
	}
}
