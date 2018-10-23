<%@ include file="../admin/Secure.jsp"%>

<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="org.jfree.chart.ChartUtilities"%>
<%@ page import="mx.com.libreria.factory.ObjectFactory"%>
<%@ page import="mx.com.libreria.math.CalculoOpciones"%>
<%@ page import="mx.com.libreria.manager.Utilerias"%>
<%@ page import="mx.com.libreria.math.Bootstrap"%>

<html>
<%@ include file="../admin/Header.jsp"%>

<body>
	<div class="wrapper1">
		<div class="wrapper">
			<%@ include file="../admin/MenuHeader.jsp"%>
			<div class="content">

				<% if(request.getParameter("sessionid") != null 
					  && session.getId().equals(request.getParameter("sessionid"))
					  && session.getAttribute("uname") != null) {
					  Bootstrap b = (Bootstrap) request.getAttribute("bootstrap"); 
					  if (b == null) {
					  	b = new Bootstrap();
					  } %>
				<p>
				<h2>Tasas Cero/Forward</h2>
				</p>

				<form
					action="<%out.println(request.getContextPath());%>/servlet/ServletBono?sessionid=<%= session.getId() %>"
					method="post">
					<table class="tablecontent">
						<tr>
							<td align="center">Nocional</td>
							<td align="center">Vencimiento</td>
							<td align="center">Cup&oacute;n</td>
							<td align="center">Precio Mdo.</td>
							<td align="center">Tasa Cero</td>
							<td align="center">Tasa Forward</td>
						</tr>
						<tr>
							<td align="center"><input type="text" name="nocional_1"
								size="6"
								value="<%=b.getBonos() != null && b.getBonos().size() > 0 ? b.getBonos().get(0).getNocionalString() : "" %>">
							</td>
							<td align="center"><input type="text" name="vencimiento_1"
								size="6"
								value="<%=b.getBonos() != null && b.getBonos().size() > 0 ? b.getBonos().get(0).getVencimientoString() : "" %>">
							</td>
							<td align="center"><input type="text" name="cupon_1"
								size="6"
								value="<%=b.getBonos() != null && b.getBonos().size() > 0 ? b.getBonos().get(0).getCuponString() : "" %>">
							</td>
							<td align="center"><input type="text" name="precio_1"
								size="6"
								value="<%=b.getBonos() != null && b.getBonos().size() > 0 ? b.getBonos().get(0).getPrecioMdoString() : "" %>">
							</td>
							<td align="center"><input type="text" name="cero_1" size="6"
								readonly="readonly"
								value="<%=b.getBonos() != null && b.getBonos().size() > 0 ? b.getBonos().get(0).getTasaCeroString() : "" %>">
							</td>
							<td align="center"><input type="text" name="forward_1"
								size="6" readonly="readonly"
								value="<%=b.getBonos() != null && b.getBonos().size() > 0 ? b.getBonos().get(0).getTasaForwardString() : "" %>">
							</td>
						</tr>
						<tr>
							<td align="center"><input type="text" name="nocional_2"
								size="6"
								value="<%=b.getBonos() != null && b.getBonos().size() > 1 ? b.getBonos().get(1).getNocionalString() : "" %>">
							</td>
							<td align="center"><input type="text" name="vencimiento_2"
								size="6"
								value="<%=b.getBonos() != null && b.getBonos().size() > 1 ? b.getBonos().get(1).getVencimientoString() : "" %>">
							</td>
							<td align="center"><input type="text" name="cupon_2"
								size="6"
								value="<%=b.getBonos() != null && b.getBonos().size() > 1 ? b.getBonos().get(1).getCuponString() : "" %>">
							</td>
							<td align="center"><input type="text" name="precio_2"
								size="6"
								value="<%=b.getBonos() != null && b.getBonos().size() > 1 ? b.getBonos().get(1).getPrecioMdoString() : "" %>">
							</td>
							<td align="center"><input type="text" name="cero_2" size="6"
								readonly="readonly"
								value="<%=b.getBonos() != null && b.getBonos().size() > 1 ? b.getBonos().get(1).getTasaCeroString() : "" %>">
							</td>
							<td align="center"><input type="text" name="forward_2"
								size="6" readonly="readonly"
								value="<%=b.getBonos() != null && b.getBonos().size() > 1 ? b.getBonos().get(1).getTasaForwardString() : "" %>">
							</td>
						</tr>
						<tr>
							<td align="center"><input type="text" name="nocional_3"
								size="6"
								value="<%=b.getBonos() != null && b.getBonos().size() > 2 ? b.getBonos().get(2).getNocionalString() : "" %>">
							</td>
							<td align="center"><input type="text" name="vencimiento_3"
								size="6"
								value="<%=b.getBonos() != null && b.getBonos().size() > 2 ? b.getBonos().get(2).getVencimientoString() : "" %>">
							</td>
							<td align="center"><input type="text" name="cupon_3"
								size="6"
								value="<%=b.getBonos() != null && b.getBonos().size() > 2 ? b.getBonos().get(2).getCuponString() : "" %>">
							</td>
							<td align="center"><input type="text" name="precio_3"
								size="6"
								value="<%=b.getBonos() != null && b.getBonos().size() > 2 ? b.getBonos().get(2).getPrecioMdoString() : "" %>">
							</td>
							<td align="center"><input type="text" name="cero_3" size="6"
								readonly="readonly"
								value="<%=b.getBonos() != null && b.getBonos().size() > 2 ? b.getBonos().get(2).getTasaCeroString() : "" %>">
							</td>
							<td align="center"><input type="text" name="forward_3"
								size="6" readonly="readonly"
								value="<%=b.getBonos() != null && b.getBonos().size() > 2 ? b.getBonos().get(2).getTasaForwardString() : "" %>">
							</td>
						</tr>
						<tr>
							<td align="center"><input type="text" name="nocional_4"
								size="6"
								value="<%=b.getBonos() != null && b.getBonos().size() > 3 ? b.getBonos().get(3).getNocionalString() : "" %>">
							</td>
							<td align="center"><input type="text" name="vencimiento_4"
								size="6"
								value="<%=b.getBonos() != null && b.getBonos().size() > 3 ? b.getBonos().get(3).getVencimientoString() : "" %>">
							</td>
							<td align="center"><input type="text" name="cupon_4"
								size="6"
								value="<%=b.getBonos() != null && b.getBonos().size() > 3 ? b.getBonos().get(3).getCuponString() : "" %>">
							</td>
							<td align="center"><input type="text" name="precio_4"
								size="6"
								value="<%=b.getBonos() != null && b.getBonos().size() > 3 ? b.getBonos().get(3).getPrecioMdoString() : "" %>">
							</td>
							<td align="center"><input type="text" name="cero_4" size="6"
								readonly="readonly"
								value="<%=b.getBonos() != null && b.getBonos().size() > 3 ? b.getBonos().get(3).getTasaCeroString() : "" %>">
							</td>
							<td align="center"><input type="text" name="forward_4"
								size="6" readonly="readonly"
								value="<%=b.getBonos() != null && b.getBonos().size() > 3 ? b.getBonos().get(3).getTasaForwardString() : "" %>">
							</td>
						</tr>
						<tr>
							<td align="center"><input type="text" name="nocional_5"
								5
								size="6"
								value="<%=b.getBonos() != null && b.getBonos().size() > 4 ? b.getBonos().get(4).getNocionalString() : "" %>">
							</td>
							<td align="center"><input type="text" name="vencimiento_5"
								size="6"
								value="<%=b.getBonos() != null && b.getBonos().size() > 4 ? b.getBonos().get(4).getVencimientoString() : "" %>">
							</td>
							<td align="center"><input type="text" name="cupon_5"
								size="6"
								value="<%=b.getBonos() != null && b.getBonos().size() > 4 ? b.getBonos().get(4).getCuponString() : "" %>">
							</td>
							<td align="center"><input type="text" name="precio_5"
								size="6"
								value="<%=b.getBonos() != null && b.getBonos().size() > 4 ? b.getBonos().get(4).getPrecioMdoString() : "" %>">
							</td>
							<td align="center"><input type="text" name="cero_5" size="6"
								readonly="readonly"
								value="<%=b.getBonos() != null && b.getBonos().size() > 4 ? b.getBonos().get(4).getTasaCeroString() : "" %>">
							</td>
							<td align="center"><input type="text" name="forward_5"
								size="6" readonly="readonly"
								value="<%=b.getBonos() != null && b.getBonos().size() > 4 ? b.getBonos().get(4).getTasaForwardString() : "" %>">
							</td>
						</tr>
						<tr>
							<td><input type="submit" name="Calcular" value="Calcular">
							</td>
						</tr>
					</table>
				</form>
				<% if (request.getAttribute("empty") != null) { %>
				<script>
					alert("Favor de llenar todos los datos de al menos un bono");
				</script>
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