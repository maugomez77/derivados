package mx.com.libreria.files;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.com.libreria.excel.ReadExcel;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class SubirArchivos implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2588589202288502642L;

	private static final String PATH_REPOSITORY = "C:\\gcms_xml\\";
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws Exception {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet</title>");
        out.println("</head>");
        out.println("<body>");

        procesaArchivos(request);
                
        out.println("</body>");
        out.println("</html>");

        out.close();
    }

	public static String getPathRepository() { 
		return PATH_REPOSITORY;
	}
	
	@SuppressWarnings("rawtypes")
	public static FileElements procesaArchivos(HttpServletRequest request) throws Exception {
		
		FileElements files = new FileElements();
		
		// Check that we have a file upload request
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		files.setMultiParte(isMultipart);
		
		if (files.isMultiParte()) {
			
			System.out.println("multiparte");
			// Create a factory for disk-based file items
			FileItemFactory factory = new DiskFileItemFactory();
	
			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);
	
			// Parse the request
			List /* FileItem */ items = upload.parseRequest(request);
			
			
			// Create a factory for disk-based file items
			//DiskFileItemFactory factory = new DiskFileItemFactory();

			// Set factory constraints
			//factory.setSizeThreshold(4096);
			//factory.setRepository(new File("."));

			// Create a new file upload handler
			//ServletFileUpload upload = new ServletFileUpload(factory);

			// Set overall request size constraint
			//upload.setSizeMax(1024*512);

			// Parse the request
			//List /* FileItem */ items = upload.parseRequest(request);
			
			
			// Process the uploaded items
			Iterator<?> iter = items.iterator();
			while (iter.hasNext()) {
				
				//System.out.println("elemento 1");
			    FileItem item = (FileItem) iter.next();
			    
			    if (item.isFormField()) {
			        processFormField(item, files);
			    } else {
			        processUploadedFile(item, true, files);
			    }			    			    
			}			
		} else { 
			System.out.println("No es multiparte");
		}
		return files;
	}
	
	public static void processFormField(FileItem item, FileElements file) {
		String name = null;
	    String value = null;
		// Process a regular form field
		if (item.isFormField()) {
		    name = item.getFieldName();
		    value = item.getString();
		    
		    if (name.equals("periodicidad")) { 
		    	file.setPeriodicidad(value);
		    } else if (name.equals("tipoRend")) {
		    	file.setTipoRend(value);
		    } else  if (name.equals("saltos")) {
		    	file.setSaltos(value);		    	
		    }
		}
	}
	
	public static void processUploadedFile(FileItem item, boolean writeToFile, FileElements files) throws Exception {				
		
		// Process a file upload
		if (!item.isFormField()) {
			//String fieldName = item.getFieldName();
		    String fileName = item.getName();
		    //String contentType = item.getContentType();
		    //boolean isInMemory = item.isInMemory();
		    //long sizeInBytes = item.getSize();
		    
		    if (writeToFile && item.getName() != null && !item.getName().equals("")) {
		    			    	
		        File fichero = new File(fileName);
		        		        
		        //nos quedamos solo con el nombre y descartamos el path
                fichero = new  File(PATH_REPOSITORY + fichero.getName());

                // escribimos el fichero colgando del nuevo path
                item.write(fichero);            

                files.setNameFile(fichero.getName());
                
                ReadExcel test = new ReadExcel();
		    	
                //System.out.println("filename: " + fichero.getAbsolutePath());
				
                test.setInputFile(fichero.getAbsolutePath());
				//DataRow datos = test.read();
				//for (int i=0; i < datos.getDatos().size(); i++) { 
					//Datos elemento = datos.getDatos().get(i);
					//System.out.println("X = " + elemento.getFecha() + " , Y = " + elemento.getSerie());
				//}			
		    }	    
		}
	}
	
	
    void depura(String cadena) {
        //System.out.println("El error es " + cadena);
    }    

    /** Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
			processRequest(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}