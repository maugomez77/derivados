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
						
						  <p><h2>Valuacion sobre Call usando Black and Scholes</h2></p>    					    	    													
      					 
      					<form action="<%out.println(request.getContextPath());%>/opciones/call.jsp?sessionid=<%= session.getId() %>" method="post">
      					    <table class="tablecontent">
      					    	<tr>
      					    		<td>
		    							Compra / Venta:
		    						</td>
		    						<td>
		    							<select name="formaPago">
		    								<option value="C" <% if (!Utilerias.isNullOrUndefined(request.getParameter("formaPago")) && request.getParameter("formaPago").equals("C")) out.println("selected"); %>>Compra
		    								<option value="V" <% if (!Utilerias.isNullOrUndefined(request.getParameter("formaPago")) && request.getParameter("formaPago").equals("V")) out.println("selected"); %>>Venta
		    							</select>		    							
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
		    							Volatilidad:
		    						</td>
		    						<td>
		    							<input type="text" name="vol" value="<%= Utilerias.mostrarCamposFront(request.getParameter("vol")) %>">
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
		    			 	
		    			 	if (!Utilerias.isNullOrUndefined(request.getParameter("formaPago"))  
		    			 		&& !Utilerias.isNullOrUndefined(request.getParameter("s0"))  
		    			 		&& !Utilerias.isNullOrUndefined(request.getParameter("k"))
		    			 		&& !Utilerias.isNullOrUndefined(request.getParameter("r"))
		    			 		&& !Utilerias.isNullOrUndefined(request.getParameter("vol"))
		    			 		&& !Utilerias.isNullOrUndefined(request.getParameter("tiempo"))) { 
		    			 		
		    			 		String link = request.getContextPath() + "/servlet/ServletDemo2ChartGenerator?type=call";
		    			 		link += "&formaPago=" + request.getParameter("formaPago") + "&s0=" + request.getParameter("s0") + "&k=" + request.getParameter("k");
		    			 		link += "&r=" + request.getParameter("r");
		    			 		link += "&vol=" + request.getParameter("vol") + "&tiempo=" + request.getParameter("tiempo");
		    			 		
		    			 		%> 
		    			 		
		    			 		<IMG SRC="<%= link %>" BORDER=1 WIDTH=600 HEIGHT=350/>		    			 		
		    			 		
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