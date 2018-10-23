package mx.com.libreria.servlets;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;

import mx.com.libreria.manager.Logging;

/**
 * Clase para obtener paths y nombres de archivos.
 */
public class GettingPaths extends HttpServlet {
    /**
     * Clase serializable.
     */
    private static final long serialVersionUID = 4912805031024992155L;
    /**
     * Prefijo del nombre de los archivos PDF. 
     */
    private static String prefixNamePDF = "pago";
    /**
     * Archivo pdf del reporte.
     */
    private static String postfixNamePDF = ".pdf";
    /**
     * Variable para guardar rutas de esta aplicación web.
     */
    private static String webRootPath;
     /**
     * Metodo para tomar un path especifico.
     * @param  type   Tipo de path
     * @return String
     */
    public static String getPath(final String type) {
        Logging.debug(GettingPaths.class, "Ruta: " + webRootPath);
        String path = "";
        
        if (type.equals("repository")) {
            path += GettingPaths.webRootPath 
                + "repository\\";// + generateFiles();
        } else if (type.equals("images")) {
            path += GettingPaths.webRootPath + "/jhx/templates/images/hsbc.GIF";
        } 
        
        return path;
    }    
    /**
     * Metodo para genera un nombre de un archivo.
     * @return String
     */
    public static synchronized String generateFiles() {
        return GettingPaths.prefixNamePDF 
            + System.currentTimeMillis() 
            + GettingPaths.postfixNamePDF;
    }    
    /**
     * Para inicializar el servlet.
     * @param servletConfig     Configuracion del server
     * @throws ServletException Excepcion
     */
    public void init(final ServletConfig servletConfig) 
        throws ServletException {
    
        super.init(servletConfig);
        GettingPaths.webRootPath = 
            servletConfig.getServletContext().getRealPath("/");
        if (GettingPaths.webRootPath == null) { 
            GettingPaths.webRootPath = 
                servletConfig.getServletContext().getRealPath(".");
        }      
        Logging.debug(GettingPaths.class, "WebPath: " + webRootPath);
                
    }
    /**
     * Accesor a la variable statica prefixNamePDF.
     * @return String
     */
    public static String getPrefixNamePDF() { 
        return GettingPaths.prefixNamePDF;
    }
    /**
     * Accesor a la variable statica postfixNamePDF.
     * @return String
     */    
    public static String getPostfixNamePDF() { 
        return GettingPaths.postfixNamePDF;
    }
    public static void setRutaWeb(String pRuta) { 
    	GettingPaths.webRootPath = pRuta;
    }   
    public static String getRutaWeb() { 
    	return GettingPaths.webRootPath;
    }
    
}
