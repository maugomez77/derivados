package mx.com.libreria.excel;

import java.io.File;
import java.io.IOException;

import org.jfree.ui.RefineryUtilities;

import mx.com.libreria.charts.LineChartDemo2;

import mx.com.libreria.estructuras.ContadorFrecuencias;
import mx.com.libreria.estructuras.DataRow;
import mx.com.libreria.estructuras.Datos;

import mx.com.libreria.manager.Utilerias;

import mx.com.libreria.math.Frecuencias;
import mx.com.libreria.math.Rendimientos;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ReadExcel {

	private String inputFile;

	public void setInputFile(String inputFile) {
		this.inputFile = inputFile;
	}

	public DataRow read() throws IOException  {
		File inputWorkbook = new File(inputFile);
		Workbook w;
		DataRow datos = new DataRow();
		try {
			w = Workbook.getWorkbook(inputWorkbook);
			// Get the first sheet
			Sheet sheet = w.getSheet(0);
			// Loop over first 10 column and lines

			//Cell cell = sheet.getCell(0, 0);
			//CellType type = cell.getType();
			
			//System.out.println("Data label: " + sheet.getCell(0, 0).getContents());
			datos.setLabelA(sheet.getCell(0, 0).getContents());
			//cell = sheet.getCell(1, 0);
			//System.out.println("Data label: " + sheet.getCell(1, 0).getContents());
			datos.setLabelB(sheet.getCell(1, 0).getContents());
			
			//el primer dato es la columna
			//posteriormente el renglon
			
			for (int i = 1; i < sheet.getRows(); i++) {
				
				Datos elemento = new Datos();
				
				elemento.setFecha(sheet.getCell(0, i).getContents());
				elemento.setSerie(Utilerias.strToDouble(sheet.getCell(1, i).getContents()));
				
				datos.getDatos().add(elemento);							
			}
			
		} catch (BiffException e) {
			e.printStackTrace();
		}
		
		return datos;
	}

	public static void main(String[] args) throws IOException {
		ReadExcel test = new ReadExcel();
		test.setInputFile("c:/gcms_xml/libro1.xls");
		DataRow datos = test.read();
		//for (int i=0; i < datos.getDatos().size(); i++) { 
			//Datos elemento = datos.getDatos().get(i);
			//System.out.println("pares:  " + elemento.getFecha() + " " + elemento.getSerie());
		//}
				
//		System.out.println("fin");
		
		Rendimientos.calculaRendimientoContinuo(datos, 1);
		
		//System.out.println("desv: " + Calculate.getDesviacionEstandard(datos, 0, 1));
		//System.out.println("var: " + Calculate.getVarianza(datos, 0, 1));
		//System.out.println("sesgo: " + Calculate.getSesgo(datos, 0, 1));
		//System.out.println("curtosis: " + Calculate.getCurtosis(datos, 0, 1));
		
		//System.out.println("max value: " + Frecuencias.getMaxValue(datos, 0, 1));
		//System.out.println("min value: " + Frecuencias.getMinValue(datos, 0, 1));
		
		
		double saltos = (Math.abs(Frecuencias.getMaxValue(datos, 0, 1)) + Math.abs(Frecuencias.getMinValue(datos, 0, 1))) / 50;
		
		//System.out.println("saltos: " + saltos);
				
		//ContadorFrecuencias frec = Frecuencias.calculaFrecuencias(datos, 0, 1, -.07, .11, .005);
		ContadorFrecuencias frec = Frecuencias.calculaFrecuencias(datos, 0, 1, 
				-.07, .11,//Frecuencias.getMinValue(datos, 0, 1), Frecuencias.getMaxValue(datos, 0, 1), 
				saltos);
		
		for (int i=0; i < frec.getDatos().size(); i++) { 
			//System.out.println("Frec " + frec.getDatos().get(i).getRangoInicial() + " " 
			//		                   + frec.getDatos().get(i).getRangoFinal() + " "
			//		                   + frec.getDatos().get(i).getContador());
		}
		
		LineChartDemo2 demo = new LineChartDemo2("Frecuencia", frec);
		demo.pack();
		RefineryUtilities.centerFrameOnScreen(demo);
		demo.setVisible(true);
		
		/*
		Rendimientos.calculaRendimientoNormalizadoContinuo(datos, 1);
		
		System.out.println("desv: " + Calculate.getDesviacionEstandard(datos, 1, 1));
		System.out.println("var: " + Calculate.getVarianza(datos, 1, 1));
		System.out.println("sesgo: " + Calculate.getSesgo(datos, 1, 1));
		System.out.println("curtosis: " + Calculate.getCurtosis(datos, 1, 1));
		
		Rendimientos.calculaRendimientoCompuesto(datos, 1);
		
		System.out.println("desv: " + Calculate.getDesviacionEstandard(datos, 2, 1));
		System.out.println("var: " + Calculate.getVarianza(datos, 2, 1));
		System.out.println("sesgo: " + Calculate.getSesgo(datos, 2, 1));
		System.out.println("curtosis: " + Calculate.getCurtosis(datos, 2, 1));
		
		*/
	}

}
