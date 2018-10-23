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
						
						  <p><h2>Valuacion sobre Put</h2></p>    					    	    													
      					 
      					<form action="<%out.println(request.getContextPath());%>/opciones/putSolo.jsp?sessionid=<%= session.getId() %>" method="post">
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
		    							Prima:
		    						</td>
		    						<td>
		    							<input type="text" name="prima" value="<%= Utilerias.mostrarCamposFront(request.getParameter("prima")) %>">
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
		    			 		&& !Utilerias.isNullOrUndefined(request.getParameter("prima"))) { 
		    			 		
		    			 		String link = request.getContextPath() + "/servlet/ServletDemo2ChartGenerator?type=putSolo";
		    			 		link += "&formaPago=" + request.getParameter("formaPago") + "&s0=" + request.getParameter("s0") + "&k=" + request.getParameter("k");
		    			 		link += "&prima=" + request.getParameter("prima");		    			 		
		    			 		
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