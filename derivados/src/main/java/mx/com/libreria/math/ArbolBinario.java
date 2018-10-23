package mx.com.libreria.math;

public class ArbolBinario {

	/**
	 * El nodo raiz del arbol.
	 */
	private Nodo raiz;

	/**
	 * El numero de periodos del arbol.
	 */
	private Integer periodos;

	/**
	 * Constructor.
	 * 
	 * @param periodos
	 *            El numero de periodos del arbol.
	 * @param subyacente
	 *            El subyacent del nodo raiz.
	 */
	public ArbolBinario(int periodos, Double subyacente) {
		this.raiz = new Nodo(0);
		this.raiz.setSubyacente(subyacente);
		this.periodos = periodos;
	}

	/**
	 * @return the raiz
	 */
	public Nodo getRaiz() {
		return raiz;
	}

	/**
	 * @param raiz
	 *            the raiz to set
	 */
	public void setRaiz(Nodo raiz) {
		this.raiz = raiz;
	}

	/**
	 * @return the periodos
	 */
	public Integer getPeriodos() {
		return periodos;
	}

	/**
	 * @param periodos
	 *            the periodos to set
	 */
	public void setPeriodos(Integer periodos) {
		this.periodos = periodos;
	}

	/**
	 * Construye un arbol binomial de acuerdo al numero de periodos.
	 */
	public void construir() {
		construir(this.raiz, null, new Counter(0), periodos);
	}

	/**
	 * 
	 * @param nodo
	 *            El nodo hijo.
	 * @param padre
	 *            El nodo padre.
	 * @param count
	 *            Contador de nodos.
	 * @param periodos
	 *            Numero de periodos restantes.
	 */
	private void construir(Nodo nodo, Nodo padre, Counter count, int periodos) {
		nodo.setPadre(padre);
		if (periodos > 0) {
			nodo.setHijoDer(new Nodo(count.getCount(), false));
			nodo.setHijoIzq(new Nodo(count.getCount(), true));
			periodos--;
			construir(nodo.getHijoDer(), nodo, count, periodos);
			construir(nodo.getHijoIzq(), nodo, count, periodos);
		}
	}

	/**
	 * Calcula los precios del subyacente para cada nodo del arbol. El recorrido
	 * va desde el nodo padre hasta sus hijos.
	 * 
	 * @param up
	 *            La variable up.
	 * @param down
	 *            La variable down.
	 */
	public void calculaPrecioSubyacente(Double up, Double down) {
		calculaPrecioSubyacente(this.raiz, up, down);
	}

	/**
	 * Calcula los precios del subyacente para cada nodo del arbol. El recorrido
	 * va desde el nodo raiz hasta sus hijos.
	 * 
	 * @param n
	 *            El nodo n.
	 * @param up
	 *            La variable up.
	 * @param down
	 *            La variable down.
	 */
	private void calculaPrecioSubyacente(Nodo n, Double up, Double down) {
		if (n != null) {
			n.calculaPrecioSubyacente(up, down);
			calculaPrecioSubyacente(n.getHijoIzq(), up, down);
			calculaPrecioSubyacente(n.getHijoDer(), up, down);
		}
	}

	/**
	 * Calcula los precios de opciones americanas para cada nodo del arbol. El
	 * recorrido es desde los hijos hasta el nodo raiz.
	 * 
	 * @param k
	 *            El precio de k.
	 * @param tasa
	 *            La tasa libre de riesgo.
	 * @param tiempo
	 *            El tiempo anualizado en decimales.
	 * @param probabilidad
	 *            La probabilidad.
	 */
	public void calculaPrecioOpcionAmericana(Double k, Double tasa,
			Double tiempo, Double probabilidad) {
		calculaPrecioOpcionAmericana(this.raiz, k, tasa, tiempo, probabilidad);
	}

	/**
	 * Calcula los precios de opciones americanas para cada nodo del arbol. El
	 * recorrido es desde los hijos hasta el nodo raiz.
	 * 
	 * @param n
	 *            El nodo n.
	 * @param k
	 *            El precio de k.
	 * @param tasa
	 *            La tasa libre de riesgo.
	 * @param tiempo
	 *            El tiempo anualizado en decimales.
	 * @param probabilidad
	 *            La probabilidad.
	 */
	private void calculaPrecioOpcionAmericana(Nodo n, Double k, Double tasa,
			Double tiempo, Double probabilidad) {
		if (n != null) {
			calculaPrecioOpcionAmericana(n.getHijoIzq(), k, tasa, tiempo,
					probabilidad);
			calculaPrecioOpcionAmericana(n.getHijoDer(), k, tasa, tiempo,
					probabilidad);
			n.calculaPrecioOpcionAmericana(k, tasa, tiempo, probabilidad);
		}
	}

	/**
	 * Calcula los precios de opciones europeas para cada nodo del arbol. El
	 * recorrido es desde los hijos hasta el nodo raiz.
	 * 
	 * @param k
	 *            El precio de k.
	 * @param tasa
	 *            La tasa libre de riesgo.
	 * @param tiempo
	 *            El tiempo anualizado en decimales.
	 * @param probabilidad
	 *            La probabilidad.
	 */
	public void calculaPrecioOpcionEuropea(Double k, Double tasa,
			Double tiempo, Double probabilidad) {
		calculaPrecioOpcionEuropea(this.raiz, k, tasa, tiempo, probabilidad);
	}

	/**
	 * 
	 * @param n
	 *            El nodo n.
	 * @param k
	 *            El precio de k.
	 * @param tasa
	 *            La tasa libre de riesgo.
	 * @param tiempo
	 *            El tiempo anualizado en decimales.
	 * @param probabilidad
	 *            La probabilidad.
	 */
	private void calculaPrecioOpcionEuropea(Nodo n, Double k, Double tasa,
			Double tiempo, Double probabilidad) {
		if (n != null) {
			calculaPrecioOpcionEuropea(n.getHijoIzq(), k, tasa, tiempo,
					probabilidad);
			calculaPrecioOpcionEuropea(n.getHijoDer(), k, tasa, tiempo,
					probabilidad);
			n.calculaPrecioOpcionEuropea(k, tasa, tiempo, probabilidad);
		}
	}

	/**
	 * @return El arbol de forma preordenada. (i.e. NID)
	 */
	public String preorden() {
		return preorden(this.raiz, "");
	}

	/**
	 * @return El arbol de forma preordenada. (i.e. NID)
	 */
	private String preorden(Nodo n, String s) {
		if (n != null) {
			s += " " + n.getId() + "-"
					+ (n.getPadre() != null ? n.getPadre().getId() : "") + "-"
					+ n.getEsHijoIzq() + "-" + n.getSubyacente() + "-"
					+ n.getPrecio() + " ";
			s = preorden(n.getHijoIzq(), s);
			s = preorden(n.getHijoDer(), s);
		}
		return s;
	}

	/**
	 * @return El arbol de forma inordenada. (i.e. IND)
	 */
	public String inorden() {
		return inorden(this.raiz, "");
	}

	/**
	 * @return El arbol de forma inordenada. (i.e. IND)
	 */
	private String inorden(Nodo n, String s) {
		if (n != null) {
			s = inorden(n.getHijoIzq(), s);
			s += " " + n.getId() + "-"
					+ (n.getPadre() != null ? n.getPadre().getId() : "") + " ";
			s = inorden(n.getHijoDer(), s);
		}
		return s;
	}

	/**
	 * @return El arbol de forma postordenada. (i.e. IDN)
	 */
	public String postorden() {
		return postorden(this.raiz, "");
	}

	/**
	 * @return El arbol de forma postordenada. (i.e. IDN)
	 */
	private String postorden(Nodo n, String s) {
		if (n != null) {
			s = postorden(n.getHijoIzq(), s);
			s = postorden(n.getHijoDer(), s);
			s += " " + n.getId() + "-"
					+ (n.getPadre() != null ? n.getPadre().getId() : "") + " ";
		}
		return s;
	}

	/**
	 * @param num
	 *            El numero a buscar.
	 * @return True si se encontro el numero.
	 */
	public boolean buscar(int num) {
		return buscar(this.raiz, num);
	}

	/**
	 * @param num
	 *            El numero a buscar.
	 * @return True si se encontro el numero.
	 */
	private boolean buscar(Nodo n, int num) {
		if (n != null) {
			if (n.getId() == num)
				return true;
			boolean ok1 = buscar(n.getHijoIzq(), num);
			boolean ok2 = buscar(n.getHijoDer(), num);
			if (ok1 || ok2)
				return true;
		}
		return false;
	}

	/**
	 * @return El numero de nodos del arbol.
	 */
	public int contar() {
		return contar(this.raiz);
	}

	/**
	 * @return El numero de nodos del arbol.
	 */
	private int contar(Nodo n) {
		if (n == null)
			return 0;
		int c1 = contar(n.getHijoIzq());
		int c2 = contar(n.getHijoDer());
		return c1 + c2 + 1;
	}

	/**
	 * @return La suma de los nodos.
	 */
	public int sumar() {
		return sumar(this.raiz);
	}

	/**
	 * @return La suma de los nodos.
	 */
	private int sumar(Nodo n) {
		if (n == null)
			return 0;
		int c1 = sumar(n.getHijoIzq());
		int c2 = sumar(n.getHijoDer());
		return c1 + c2 + n.getId();
	}

	/**
	 * @return La altura del arbol.
	 */
	public int calcularAltura() {
		return calcularAltura(this.raiz);
	}

	/**
	 * @return La altura del arbol.
	 */
	private int calcularAltura(Nodo n) {
		if (n == null)
			return 0;
		int h1 = calcularAltura(n.getHijoIzq());
		int h2 = calcularAltura(n.getHijoDer());
		if (h1 > h2)
			return h1 + 1;
		return h2 + 1;
	}

	/**
	 * @return El nodo con mayor valor.
	 */
	public int buscarMayor() {
		return buscarMayor(this.raiz);
	}

	/**
	 * @return El nodo con mayor valor.
	 */
	private int buscarMayor(Nodo n) {
		if (n == null)
			return 0;
		int m1 = buscarMayor(n.getHijoIzq());
		int m2 = buscarMayor(n.getHijoDer());
		int m3 = n.getId();

		if (m1 >= m2 && m1 >= m3)
			return m1;
		if (m2 >= m1 && m2 >= m3)
			return m2;
		return m3;
	}
}
