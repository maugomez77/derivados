<%@ include file="../admin/Secure.jsp"%>

<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="mx.com.libreria.manager.Utilerias"%>
<%@ page import="mx.com.libreria.math.Swap"%>

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
					Swap s = (Swap) request.getAttribute("swap"); 
					if (s == null) {
						s = new Swap();
					} %>
				<p>
				<h2>SWAP IRS/Bonos</h2>
				</p>

				<form
					action="<%out.println(request.getContextPath());%>/servlet/ServletSwap?sessionid=<%= session.getId() %>"
					method="post">
					<table class="tablecontent">
						<tr>
							<td>Nocional:</td>
							<td><input type="text" name="nocional"
								value="<%= s.getNocionalString() %>" size="10">
							</td>
							<td></td>
						</tr>
						<tr>
							<td>Inter&eacute;s Recibido (compuesto semestral):</td>
							<td><input type="text" name="interesRecibido"
								value="<%= s.getRecibeInteresString() %>" size="10">
							</td>
							<td></td>
						</tr>
						<tr>
							<td>Inter&eacute;s Pagado (compuesto semestral):</td>
							<td><input type="text" name="interesPagado"
								value="<%= s.getPagaInteresString() %>" size="10">
							</td>
							<td></td>
						</tr>
						<tr>
							<td>M&eacute;todo:</td>
							<td><select name="metodo">
									<option value="F"
										<% if (!Utilerias.isNullOrUndefined(request.getParameter("metodo")) && request.getParameter("metodo").equals("F")) out.println("selected"); %>>FRA
									<option value="B"
										<% if (!Utilerias.isNullOrUndefined(request.getParameter("metodo")) && request.getParameter("metodo").equals("B")) out.println("selected"); %>>Bonos
							</select></td>
							<td></td>
						</tr>
						<tr>
							<td></td>
							<td align="left">Mes</td>
							<td align="left">LIBOR</td>
						</tr>
						<tr>
							<td></td>
							<td align="left"><input type="text" name="mes_1"
								size="10"
								value="<%=s.getTasas() != null && s.getTasas().size() > 0 ? s.getTasas().get(0).getTiempo() : "" %>">
							</td>
							<td align="left"><input type="text" name="libor_1"
								size="10"
								value="<%=s.getTasas() != null && s.getTasas().size() > 0 ? s.getTasas().get(0).getTasa() : "" %>">
							</td>
						</tr>
						<tr>
							<td></td>
							<td align="left"><input type="text" name="mes_2"
								size="10"
								value="<%=s.getTasas() != null && s.getTasas().size() > 1 ? s.getTasas().get(1).getTiempo() : "" %>">
							</td>
							<td align="left"><input type="text" name="libor_2"
								size="10"
								value="<%=s.getTasas() != null && s.getTasas().size() > 1 ? s.getTasas().get(1).getTasa() : "" %>">
							</td>
						</tr>
						<tr>
							<td></td>
							<td align="left"><input type="text" name="mes_3"
								size="10"
								value="<%=s.getTasas() != null && s.getTasas().size() > 2 ? s.getTasas().get(2).getTiempo() : "" %>">
							</td>
							<td align="left"><input type="text" name="libor_3"
								size="10"
								value="<%=s.getTasas() != null && s.getTasas().size() > 2 ? s.getTasas().get(2).getTasa() : "" %>">
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
					alert("Favor de llenar todos los datos");
				</script>
				<% } %>
				
				<% if (request.getAttribute("result") != null) { %>
				<table class="tablecontent">
					<tr>
						<td>Valor SWAP:</td>
						<td>
							<%= s.getValorSwapString() %>
						</td>
					</tr>
				</table>
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