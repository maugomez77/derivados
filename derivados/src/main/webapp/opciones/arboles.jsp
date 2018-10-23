<%@ include file="../admin/Secure.jsp"%>

<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="mx.com.libreria.manager.Utilerias"%>
<%@ page import="mx.com.libreria.math.ArbolBinarioApp"%>

<html>
<%@ include file="../admin/Header.jsp"%>

<body>
	<div class="wrapper1">
		<div class="wrapper">
			<%@ include file="../admin/MenuHeader.jsp"%>
			<div class="content">

				<% if(request.getParameter("sessionid") != null 
					  && session.getId().equals(request.getParameter("sessionid"))
					  && session.getAttribute("uname") != null) { %>
				<p>
				<h2>Arboles Binomiales</h2>
				</p>

				<form
					action="<%out.println(request.getContextPath());%>/opciones/arboles.jsp?sessionid=<%= session.getId() %>"
					method="post">
					<table class="tablecontent">
						<tr>
							<td>Valor So:</td>
							<td><input type="text" name="s0"
								value="<%= Utilerias.mostrarCamposFront(request.getParameter("s0")) %>">
							</td>
						</tr>
						<tr>
							<td>Valor K:</td>
							<td><input type="text" name="k"
								value="<%= Utilerias.mostrarCamposFront(request.getParameter("k")) %>">
							</td>
						</tr>
						<tr>
							<td>Tasa libre de Riesgo:</td>
							<td><input type="text" name="r"
								value="<%= Utilerias.mostrarCamposFront(request.getParameter("r")) %>">
							</td>
						</tr>
						<tr>
							<td>Volatilidad:</td>
							<td><input type="text" name="vol"
								value="<%= Utilerias.mostrarCamposFront(request.getParameter("vol")) %>">
							</td>
						</tr>
						<tr>
							<td>Tiempo (en decimales):</td>
							<td><input type="text" name="tiempo"
								value="<%= Utilerias.mostrarCamposFront(request.getParameter("tiempo")) %>">
							</td>
						</tr>
						<tr>
							<td>N&uacute;mero de periodos:</td>
							<td><input type="text" name="periodos"
								value="<%= Utilerias.mostrarCamposFront(request.getParameter("periodos")) %>">
							</td>
						</tr>
						<tr>
							<td>Tipo de Opci&oacute;n:</td>
							<td><select name="tipoOpcion">
									<option value="A"
										<% if (!Utilerias.isNullOrUndefined(request.getParameter("tipoOpcion")) && request.getParameter("tipoOpcion").equals("A")) out.println("selected"); %>>Americana
									<option value="E"
										<% if (!Utilerias.isNullOrUndefined(request.getParameter("tipoOpcion")) && request.getParameter("tipoOpcion").equals("E")) out.println("selected"); %>>Europea
							</select></td>
						</tr>
						<tr>
							<td><input type="submit" name="Calcular" value="Calcular">
							</td>
						</tr>
					</table>
				</form>
				<% if (request.getParameter("Calcular") != null) {
		    			 	if (!Utilerias.isNullOrUndefined(request.getParameter("s0"))  
		    			 		&& !Utilerias.isNullOrUndefined(request.getParameter("k"))  
		    			 		&& !Utilerias.isNullOrUndefined(request.getParameter("r"))  
		    			 		&& !Utilerias.isNullOrUndefined(request.getParameter("vol"))
		    			 		&& !Utilerias.isNullOrUndefined(request.getParameter("tiempo"))
		    			 		&& !Utilerias.isNullOrUndefined(request.getParameter("periodos"))) { %>
				<table class="tablecontent">
					<tr>
						<td>Precio Opci&oacute;n:</td>
						<td>
							<% ArbolBinarioApp ab = new ArbolBinarioApp(Utilerias.strToDouble(request.getParameter("s0")),
															Utilerias.strToDouble(request.getParameter("vol")),
					    			 						Utilerias.strToDouble(request.getParameter("r")),
					    			 						Utilerias.strToDouble(request.getParameter("k")), 
					    			 						Utilerias.strToDouble(request.getParameter("tiempo")),
					    			 						Utilerias.strToInt(request.getParameter("periodos")));
					    			 		ab.getAb().construir();
					    			 		ab.getAb().calculaPrecioSubyacente(ab.getUp(), ab.getDown());
					    			 		if (request.getParameter("tipoOpcion").compareTo("E") == 0) {
					    			 			ab.getAb().calculaPrecioOpcionEuropea(ab.getK(),
					    			 				ab.getTasaLibreRieso(), ab.getTiempo(), ab.getProbabilidad());
					    			 		} else {
					    			 			ab.getAb().calculaPrecioOpcionAmericana(ab.getK(),
						    			 			ab.getTasaLibreRieso(), ab.getTiempo(), ab.getProbabilidad());
					    			 		}
					    			 		out.println(ab.getAb().getRaiz().getPrecio()); %>
						</td>
					</tr>
				</table>
				<% } else { %>
				<script>
					alert("Favor de llenar todos los datos");
				</script>
				<% } %>
				<% } %>

				<% } else { 
  					session.invalidate();    
  					response.sendRedirect(request.getContextPath() + Utilerias.LOGIN_INDEX);		  							
				} %>

				<%@ include file="../admin/Footer.jsp"%>
			</div>
			<% //se cierra content %>
		</div>
		<% //se cierra wrapper %>
	</div>
	<% //se cierra wrapper1 %>

</body>
</html>