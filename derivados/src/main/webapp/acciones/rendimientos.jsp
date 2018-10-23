<%@ include file="../admin/Secure.jsp" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>

<%@ page import="org.jfree.chart.JFreeChart" %>
<%@ page import="org.jfree.chart.ChartUtilities" %>

<%@ page import="mx.com.libreria.factory.ObjectFactory" %>

<%@ page import="mx.com.libreria.files.FileElements"%>
<%@ page import="mx.com.libreria.files.SubirArchivos"%>

<%@ page import="mx.com.libreria.math.CalculoOpciones"%>

<%@ page import="mx.com.libreria.estructuras.DatosOpciones"%>

<%@ page import="mx.com.libreria.manager.CatalogMGR"%>
<%@ page import="mx.com.libreria.manager.Utilerias" %>

<html>
	<%@ include file="../admin/Header.jsp" %>

	<body>
		<div class="wrapper1">
			<div class="wrapper">
				<%@ include file="../admin/MenuHeader.jsp" %>			
				<div class="content">

				<% if(request.getParameter("sessionid") != null 
					  && session.getId().equals(request.getParameter("sessionid"))
					  && session.getAttribute("uname") != null){  %>
      				
      					<% CatalogMGR catalogMGR = (CatalogMGR) ObjectFactory.getBean("catalogoMGR"); %>
						
						  <p><h2>Rendimientos sobre precios de acciones</h2></p>    					    	    													
      					 
      					<form action="<%out.println(request.getContextPath());%>/acciones/rendimientos.jsp?sessionid=<%= session.getId() %>" method="post"  enctype="multipart/form-data">
      					    <table class="tablecontent">
      					    	<tr>
      					    		<td>
		    							Tipos de Rendimientos:
		    						</td>
		    						<td>
		    							<select name="tipoRend">
		    								<option value="CONT" <% if (!Utilerias.isNullOrUndefined(request.getParameter("tipoRend")) && request.getParameter("tipoRend").equals("CONT")) out.println("selected"); %>>Continuo
		    								<option value="NORM" <% if (!Utilerias.isNullOrUndefined(request.getParameter("tipoRend")) && request.getParameter("tipoRend").equals("NORM")) out.println("selected"); %>>Normalizado
		    								<option value="COMP" <% if (!Utilerias.isNullOrUndefined(request.getParameter("tipoRend")) && request.getParameter("tipoRend").equals("COMP")) out.println("selected"); %>>Compuesto
		    							</select>		    							
		    						</td>
		    					</tr>
      					    	<tr>
      					    		<td>
		    							Periodicidad:
		    						</td>
		    						<td>
		    							<select name="periodicidad">
		    								<option value="D" <% if (!Utilerias.isNullOrUndefined(request.getParameter("periodicidad")) && request.getParameter("periodicidad").equals("D")) out.println("selected"); %>>Diario
		    								<option value="S" <% if (!Utilerias.isNullOrUndefined(request.getParameter("periodicidad")) && request.getParameter("periodicidad").equals("S")) out.println("selected"); %>>Semanal
		    								<option value="M" <% if (!Utilerias.isNullOrUndefined(request.getParameter("periodicidad")) && request.getParameter("periodicidad").equals("M")) out.println("selected"); %>>Mensual
		    							</select>		    							
		    						</td>
		    					</tr>

								<tr>
      					    		<td>
		    							Numero de Saltos entre Rango Minimo y Maximo:
		    						</td>
		    						<td>
		    							<input type="text" name="saltos" value="<%= Utilerias.mostrarCamposFront(request.getParameter("saltos")) %>">		    							
		    						</td>
		    					</tr>
		    					
      					    	<tr>
      					    		<td>
		    							Archivo de Excel:
		    						</td>
		    						<td>
		    							<input type="file" name="fichero">		    							
		    						</td>
		    					</tr>		    					
		    					<tr>
		    						<td><input type="submit" name="Calcular" value="Calcular"></td>
		    					</tr>
		    				</table>
		    			</form>		
		    									
		    				<% 
		    			
		    				FileElements files = SubirArchivos.procesaArchivos(request);
							
		    				if (files.isMultiParte() 
		    					&& files.getNameFile() != null && !files.getNameFile().equals("")
		    					&& files.getTipoRend() != null && !files.getTipoRend().equals("")
		    					&& files.getPeriodicidad() != null && !files.getPeriodicidad().equals("")
		    					&& files.getSaltos() != null && !files.getSaltos().equals("")) {
	    			 			String link = request.getContextPath() + "/servlet/ServletDemo2ChartGenerator?type=rendimientos";
	    			 			link += "&tipoRend=" + files.getTipoRend();
	    			 			link += "&periodicidad=" + files.getPeriodicidad();
	    			 			link += "&saltos=" + files.getSaltos();
	    			 			link += "&fichero=" + files.getNameFile();		    			 		
	    			 			%> 
	    			 	
	    			 			<IMG SRC="<%= link %>" BORDER=1 WIDTH=600 HEIGHT=350/>			    			
		    			  <% } else { %>
		    			  		
		    			  		<% out.println("Debes especificar un nombre de archivo forzosamente además de que todos los campos son requeridos"); %>
		    			  		
		    			  <% } %> 
      			<% } else { 
  					session.invalidate();    
  					response.sendRedirect(request.getContextPath() + Utilerias.LOGIN_INDEX);		  							
				} %>				
									
				<%@ include file="../admin/Footer.jsp" %>    
  			</div> <% //se cierra content %>   		
  		</div><% //se cierra wrapper %>
  	</div><% //se cierra wrapper1 %>
  	
</body>
</html>