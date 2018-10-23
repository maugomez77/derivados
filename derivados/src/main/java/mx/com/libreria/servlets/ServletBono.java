package mx.com.libreria.servlets;

import java.io.IOException;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.com.libreria.math.Bootstrap;
import mx.com.libreria.math.BootstrapRow;

/**
 * Clase para calcular tasas cero y forward de bonos.
 */
public class ServletBono extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3969870764734614855L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<BootstrapRow> bonos = getList(request);

		if (bonos.size() > 0) {
			Bootstrap bootstrap = new Bootstrap();
			bootstrap.setBonos(bonos);
			bootstrap.calculaTasasCero();
			bootstrap.calculaTasasForward();

			request.setAttribute("bootstrap", bootstrap);
		} else {
			request.setAttribute("empty", "empty");
		}
		request.getRequestDispatcher("/bonos/tasas.jsp").forward(request,
				response);
	}

	private List<BootstrapRow> getList(HttpServletRequest request) {
		List<BootstrapRow> list = new LinkedList<BootstrapRow>();

		for (int i = 1; i <= 5; i++) {
			Double nocional = null;
			Double vencimiento = null;
			Double cupon = null;
			Double precio = null;

			if (request.getParameter("nocional_" + i).compareTo("") != 0) {
				nocional = Double
						.valueOf(request.getParameter("nocional_" + i));
			}
			if (request.getParameter("vencimiento_" + i).compareTo("") != 0) {
				vencimiento = Double.valueOf(request
						.getParameter("vencimiento_" + i));
			}
			if (request.getParameter("cupon_" + i).compareTo("") != 0) {
				cupon = Double.valueOf(request.getParameter("cupon_" + i));
			}
			if (request.getParameter("precio_" + i).compareTo("") != 0) {
				precio = Double.valueOf(request.getParameter("precio_" + i));
			}
			if (nocional != null && vencimiento != null && cupon != null
					&& precio != null) {
				list.add(new BootstrapRow(nocional, vencimiento, cupon, precio));
			}
		}
		return list;
	}
}
