<%@page import="mx.com.libreria.math.VolatilidadImplicita"%>
<%@ include file="../admin/Secure.jsp" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="org.jfree.chart.JFreeChart" %>
<%@ page import="org.jfree.chart.ChartUtilities" %>

<%@ page import="mx.com.libreria.factory.ObjectFactory" %>

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
						
						  <p><h2>Volatilidad usando BS Call</h2></p>    					    	    													
      					 
      					<form action="<%out.println(request.getContextPath());%>/opciones/volatilidad.jsp?sessionid=<%= session.getId() %>" method="post">
      					    <table class="tablecontent">
      					    	<tr>
      					    		<td>
		    							Valor C Market:
		    						</td>
		    						<td>
		    							<input type="text" name="c" value="<%= Utilerias.mostrarCamposFront(request.getParameter("c")) %>">
		    						</td>
		    					</tr>

       					    	<tr>
      					    		<td>
		    							Valor So:
		    						</td>
		    						<td>
		    							<input type="text" name="s0" value="<%= Utilerias.mostrarCamposFront(request.getParameter("s0")) %>">
		    						</td>
		    					</tr>
		    					<tr>
      					    		<td>
		    							Valor K:
		    						</td>
		    						<td>
		    							<input type="text" name="k" value="<%= Utilerias.mostrarCamposFront(request.getParameter("k")) %>">
		    						</td>
		    					</tr>
		    					<tr>
      					    		<td>
		    							Tasa libre de Riesgo:
		    						</td>
		    						<td>
		    							<input type="text" name="r" value="<%= Utilerias.mostrarCamposFront(request.getParameter("r")) %>">
		    						</td>
		    					</tr>
		    					<tr>
      					    		<td>
		    							Aprox Volatilidad:
		    						</td>
		    						<td>
		    							<input type="text" name="aproxVol" value="<%= Utilerias.mostrarCamposFront(request.getParameter("aproxVol")) %>">
		    						</td>
		    					</tr>
		    					<tr>
      					    		<td>
		    							Tiempo (en decimales):
		    						</td>
		    						<td>
		    							<input type="text" name="tiempo" value="<%= Utilerias.mostrarCamposFront(request.getParameter("tiempo")) %>">
		    						</td>
		    					</tr>
		    					
		    					<tr>
		    						<td><input type="submit" name="Calcular" value="Calcular"></td>
		    					</tr>
		    				</table>
		    			</form>		
		    									
		    			<% if (request.getParameter("Calcular") != null) {  
		    			 	
		    			 	if (!Utilerias.isNullOrUndefined(request.getParameter("c"))  
		    			 		&& !Utilerias.isNullOrUndefined(request.getParameter("s0"))  
		    			 		&& !Utilerias.isNullOrUndefined(request.getParameter("k"))
		    			 		&& !Utilerias.isNullOrUndefined(request.getParameter("r"))
		    			 		&& !Utilerias.isNullOrUndefined(request.getParameter("aproxVol"))
		    			 		&& !Utilerias.isNullOrUndefined(request.getParameter("tiempo"))) { %> 
		    	
		    					<table class="tablecontent">
      					    		<tr>
      					    			<td>
      					    				Volatilidad Implicita:       					    				
      					    			</td>
      					    			<td>
      					    				<% 	double volImplicita = 
					    			 				VolatilidadImplicita.volatilidadImplicita(
						    			 				Utilerias.strToDouble(request.getParameter("c")), 
						    			 				Utilerias.strToDouble(request.getParameter("s0")), 
						    			 				Utilerias.strToDouble(request.getParameter("tiempo")), 
						    			 				Utilerias.strToDouble(request.getParameter("k")), 
						    			 				Utilerias.strToDouble(request.getParameter("r")), 
						    			 				Utilerias.strToDouble("0"), 
						    			 				Utilerias.strToDouble(request.getParameter("aproxVol")));
					    			 			out.println(volImplicita);		    	
					    			 		 %>		    			 					
      					    			</td>
      					    		</tr>
      					    	</table>		
		    					
		    			 	<% } else { %> 
		    			 		<script>alert("Favor de llenar todos los datos");</script>;	    						
		    			 	<% } %>		    													    		
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