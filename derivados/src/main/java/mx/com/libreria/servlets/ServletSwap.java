package mx.com.libreria.servlets;

import java.io.IOException;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.com.libreria.math.Swap;
import mx.com.libreria.math.SwapRow;

/**
 * Clase para calcular el valor de un swap.
 */
public class ServletSwap extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 529897221291090882L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Swap swap = new Swap();
		Double nocional = null;
		Double interesRecibido = null;
		Double interesPagado = null;

		if (request.getParameter("nocional").compareTo("") != 0) {
			nocional = Double.valueOf(request.getParameter("nocional"));
		}

		if (request.getParameter("interesRecibido").compareTo("") != 0) {
			interesRecibido = Double.valueOf(request
					.getParameter("interesRecibido"));
		}

		if (request.getParameter("interesPagado").compareTo("") != 0) {
			interesPagado = Double.valueOf(request
					.getParameter("interesPagado"));
		}

		swap.setNocional(nocional);
		swap.setRecibeInteres(interesRecibido);
		swap.setPagaInteres(interesPagado);

		List<SwapRow> tasas = getList(request);
		swap.setTasas(tasas);

		if (tasas.size() >= 3) {
			if (request.getParameter("metodo").compareTo("B") != 0) {
				swap.setValorSwap(swap.calculaValuacionBonos());
			} else {
				swap.setValorSwap(swap.calculaValuacionFRA());
			}
			request.setAttribute("result", "result");
		} else {
			request.setAttribute("empty", "empty");
		}

		request.setAttribute("swap", swap);
		request.getRequestDispatcher("/swap/irs.jsp")
				.forward(request, response);
	}

	private List<SwapRow> getList(HttpServletRequest request) {
		List<SwapRow> list = new LinkedList<SwapRow>();

		for (int i = 1; i <= 3; i++) {
			Double tiempo = null;
			Double tasa = null;

			if (request.getParameter("mes_" + i).compareTo("") != 0) {
				tiempo = Double.valueOf(request.getParameter("mes_" + i));
			}
			if (request.getParameter("libor_" + i).compareTo("") != 0) {
				tasa = Double.valueOf(request.getParameter("libor_" + i));
			}
			if (tiempo != null && tasa != null) {
				list.add(new SwapRow(tiempo, tasa));
			}
		}
		return list;
	}
}
