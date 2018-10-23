package mx.com.libreria.manager;

/**
 * Interface para definir valores constantes usados por las
 * utilerias y realizar ciertas validaciones.
 */
public interface Constants {

	String ROL_ADMON = "1";
	
	String PAGO_CREDITO = "C";
	String DESC_PAGO_CREDITO = "Credito";
	
	String PAGO_CONTADO = "P";
	String DESC_PAGO_CONTADO = "Contado";
	
	String PAGO_INVENTARIO = "I";
	String DESC_PAGO_INVENTARIO = "Carga de Inventario";
	
	int INFINITY_VALUE = 1000000;
	
	String INDEX_PAGE = "index.jsp";
	
	String LOGIN_REGISTER_NEW = "/login/Register.jsp";
	
	String LOGIN_INDEX = "/login/index.jsp";
	
	String OBJETO_VENTA = "objetoVenta";
	
	String OBJETO_COMPRA = "objetoCompra";
	
	String COMPRA_PROVEEDOR = "compraProveedor";
	
	String COMPRA_CLIENTE = "compraCliente";
	
	String REMISION_ACTUAL_SESION = "remisionActualSesion";
	
	String INVENTARIO_ACTUAL_SESION = "inventarioActualSesion";
	
	String ESTATUS_PAGO_COMPRA = "estatusPagoCompra";
	
	String ESTATUS_PAGO_VENTA = "estatusPagoVenta";
	
	String DECIMAL_FORMAT = "########.######";
	
	String START_DATE_RANGE = " 00:00:00";
	
	String END_DATE_RANGE = " 23:59:59";	
	
	String FORMAT_DATE = "yyyy-MM-dd";
	
	String FORMAT_DATE_WITH_HOUR = "yyyy-MM-dd HH:mm:ss";
	
	String PROVEEDOR_ID_INVENTARIO = "proveedorIdInventario";
	
	String CLIENTE_ID_INVENTARIO = "clienteIdInventario";
}
