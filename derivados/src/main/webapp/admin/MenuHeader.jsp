  	<div class="nav-wrapper">
			<div class="nav-left"></div>
			
			<div class="nav">
				<ul id="navigation">
			   		<li class="active">
						<a href="#">
							<span class="menu-left"></span>
							<span class="menu-mid">Home</span>
							<span class="menu-right"></span>
						</a>
					</li>
					
					<li class="">
						<a href="#">
							<span class="menu-left"></span>
							<span class="menu-mid">Opciones</span>
							<span class="menu-right"></span>
						</a>
						
						<% if (session.getAttribute("uname") != null && !session.getAttribute("uname").toString().equals("")) { %>			   		
	            	   	<div class="sub">
			   				<ul>
			   					<li>
									<a href="<%out.println(request.getContextPath());%>/opciones/callSolo.jsp?sessionid=<%= session.getId() %>">Call con primas manuales</a>
								</li>
								<li>
									<a href="<%out.println(request.getContextPath());%>/opciones/putSolo.jsp?sessionid=<%= session.getId() %>">Put con primas manuales</a>
								</li>								
			   					<li>
									<a href="<%out.println(request.getContextPath());%>/opciones/call.jsp?sessionid=<%= session.getId() %>">Call con Black And Scholes</a>
								</li>
								<li>
									<a href="<%out.println(request.getContextPath());%>/opciones/put.jsp?sessionid=<%= session.getId() %>">Put con Black And Scholes</a>
								</li>
								<li>
									<a href="<%out.println(request.getContextPath());%>/opciones/volatilidad.jsp?sessionid=<%= session.getId() %>">Volatilidad Implicita</a>
								</li>
								<!-- 
								<li>
									<a href="<%out.println(request.getContextPath());%>/opciones/griegas.jsp?sessionid=<%= session.getId() %>">Griegas</a>
								</li>
								-->
								<li>
									<a href="<%out.println(request.getContextPath());%>/opciones/arboles.jsp?sessionid=<%= session.getId() %>">Arboles Binomiales</a>
								</li>																											   		
			   				</ul>
			   				<div class="btm-bg"></div>
			   			</div>			   			
			   			<% } %>
			   						   		
					</li>
					
					<li class="">
						<a href="#">
							<span class="menu-left"></span>
							<span class="menu-mid">Acciones</span>
							<span class="menu-right"></span>
						</a>
						
						<% if (session.getAttribute("uname") != null && !session.getAttribute("uname").toString().equals("")) { %>			   		
	            	   	<div class="sub">
			   				<ul>
			   					<li>
									<a href="<%out.println(request.getContextPath());%>/acciones/rendimientos.jsp?sessionid=<%= session.getId() %>">Rendimientos e Histogramas</a>
								</li>
								<!-- 
			   					<li>
									<a href="<%out.println(request.getContextPath());%>/acciones/montecarlo.jsp?sessionid=<%= session.getId() %>">Montecarlo</a>
								</li>
								-->
			   				</ul>
			   				<div class="btm-bg"></div>
			   			</div>			   			
			   			<% } %>			   						   	
					</li>
										
			   		<li class="">
						<a href="#">
							<span class="menu-left"></span>
							<span class="menu-mid">Futuros</span>
							<span class="menu-right"></span>
						</a>
						<% if (session.getAttribute("uname") != null && !session.getAttribute("uname").toString().equals("")) { %>						
			   			<div class="sub">
			   				<ul>
			   					<li>
									<a href="<%out.println(request.getContextPath());%>/futuros/acciones.jsp?sessionid=<%= session.getId() %>">Acciones</a>
								</li>								
			   					<!-- 
			   					<li>
									<a href="<%out.println(request.getContextPath());%>/futuros/commodities.jsp?sessionid=<%= session.getId() %>">Commodities</a>									
								</li>
								<li>
									<a href="<%out.println(request.getContextPath());%>/futuros/divisas.jsp?sessionid=<%= session.getId() %>">Divisas</a>									
								</li>
								-->			   												
			   				</ul>
			   				<div class="btm-bg"></div>
			   			</div>
			   			<% } %>
			   		</li>
			   			
			   		<li class="">
						<a href="#">
							<span class="menu-left"></span>
							<span class="menu-mid">Bonos</span>
							<span class="menu-right"></span>
						</a>
						<% if (session.getAttribute("uname") != null && !session.getAttribute("uname").toString().equals("")) { %>						
			   			<div class="sub">
			   				<ul>
			   					<li>
			   						<a href="<%out.println(request.getContextPath());%>/bonos/tasas.jsp?sessionid=<%= session.getId() %>">Tasa Cero / Forward</a>																	
								</li>
								<!-- 
								<li>
			   						<a href="<%out.println(request.getContextPath());%>/bonos/fra.jsp?sessionid=<%= session.getId() %>">FRA</a>																	
								</li>
								-->			   					
			   				</ul>
			   				<div class="btm-bg"></div>
			   			</div>
			   			<% } %>
			   		</li>								
			   			 
			   		<li class="">
						<a href="#">
							<span class="menu-left"></span>
							<span class="menu-mid">Swaps</span>
							<span class="menu-right"></span>
						</a>
						<% if (session.getAttribute("uname") != null && !session.getAttribute("uname").toString().equals("")) { %>						
			   			<div class="sub">
			   				<ul>
			   					<li>
			   						<a href="<%out.println(request.getContextPath());%>/swap/irs.jsp?sessionid=<%= session.getId() %>">IRS</a>																	
								</li>
								<!-- 
								<li>
			   						<a href="<%out.println(request.getContextPath());%>/swap/currency.jsp?sessionid=<%= session.getId() %>">Currency</a>																	
								</li>
								-->			   					
			   				</ul>
			   				<div class="btm-bg"></div>
			   			</div>
			   			<% } %>
			   		</li>	   					   	
			   		<li class="last">
						<a href="">
							<span class="menu-left"></span>
							<span class="menu-mid">Creditos</span>
							<span class="menu-right"></span>
						</a>
						<% if (session.getAttribute("uname") != null && !session.getAttribute("uname").toString().equals("")) { %>
						<div class="sub">
			   				<ul>
			   					<li>
									<a href="<%out.println(request.getContextPath());%>/manager/webmaster.jsp?sessionid=<%= session.getId() %>">Contacte a WebMaster</a>																	
								</li>
			   					<li>
									<a href="../login/Logout.jsp">Logout</a>
								</li>			   					
			   				</ul>
			   				<div class="btm-bg"></div>
			   			</div>
			   			<% } %>
			   		</li>			   										   					   					   				
			   	</ul>
			</div>
			<div class="nav-right"></div>
		</div>  	  		
  	