package mx.com.libreria.manager;

import java.util.ArrayList;
import java.util.List;

import mx.com.libreria.interfases.persistencia.dao.BaseDao;

import mx.com.libreria.marcador.Marker;

import mx.com.libreria.model.login.Rol;
import mx.com.libreria.model.login.Usuario;

public class CatalogMGR {

	BaseDao baseDao;
	
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;		
	}

	@SuppressWarnings("unchecked")
	public List<?> getList(Object obj) {
		List<?> listaObjectos = null;
		if (obj instanceof Rol) {
			Rol prod = (Rol) obj;
			listaObjectos = (ArrayList<Rol>) baseDao.find(prod);
		} else if (obj instanceof Usuario) {
			Usuario prod = (Usuario) obj;
			listaObjectos = (ArrayList<Usuario>) baseDao.find(prod);
		} else {
			Logging.debug(CatalogMGR.class, "no hay mapping: getList");
		}
		
		return listaObjectos;
	}
	
	@SuppressWarnings("rawtypes")
	public List<?> getListParameters(String query, Object[] values) {
		List<?> listaObjectos = (ArrayList) baseDao.find(query, values);
		return listaObjectos;
	}
	
	public Object getObjectData(Object obj) { 		
		if (obj instanceof Usuario) {
			Usuario prod = (Usuario) obj;			
			prod = (Usuario) baseDao.getByPK(prod, prod.getUsuarioId());							
			return prod;
		} else if (obj instanceof Rol) {
			Rol prod = (Rol) obj;			
			prod = (Rol) baseDao.getByPK(prod, prod.getRolId());							
			return prod;
		} else {
			Logging.debug(CatalogMGR.class, "Existe un no mapeo en getObjectData");
		}		
		return null;			
	}
	/**
	 * 
	 * @param operacion   1 = update
	 *                    0 = insert
	 *                   -1 = delete
	 * @return
	 */
	public int dmlOperations(int operacion, Object obj) {
		int result = 0;
		Marker marker = null;
		try { 
			if (obj instanceof Usuario) {
				Usuario prod = (Usuario) obj;
				if (operacion == -1) { 
					prod = (Usuario) getObjectData(prod);
				}
				marker = prod;
			} else if (obj instanceof Rol) {
			    Rol prod = (Rol) obj;
				if (operacion == -1) { 
					prod = (Rol) getObjectData(prod);
				}
				marker = prod;			
			} else { 
				Logging.debug(CatalogMGR.class, "no hubo mapping para dml operation");
				marker = null;
			}		
			
			if (marker != null) { 
				Logging.debug(CatalogMGR.class, "marker not null");
				switch (operacion) { 
					case -1: baseDao.delete(marker);
					         result = -1;
							 break;		
			        case 0: baseDao.save(marker);
			        		result = 0;
							break;
					case 1: baseDao.update(marker);
							result = 1;
							break;
					case 2: baseDao.saveOrUpdate(marker);
							result = 2;
						    break;
					case 3: 
						    break;
					default: break;		
				};
				
			}
				
		} catch (Exception e) {
			Logging.debug(CatalogMGR.class, " dmlOperations " + e.toString());
			result = -1;
		}
		
		return result;
	}
	
	public String validarCamposLlenos(Object obj) {
		StringBuilder result = new StringBuilder("");
					
		if (obj instanceof Rol) { 
			Rol prod = (Rol) obj;
			if (Utilerias.isNullOrUndefined(prod.getNombreRol())) 
 				result.append("- Nombre del rol del usuario -");
		}
		
		if (obj instanceof Usuario) { 
			Usuario prod = (Usuario) obj;
			if (Utilerias.isNullOrUndefined(prod.getUsuarioId())) 
 				result.append("- Identificador del usuario -");
 				                 	
 			if (Utilerias.isNullOrUndefined(prod.getNombreUsuario()))
 				result.append("- Nombre del usuario -");	
 
 			if (Utilerias.isNullOrUndefined(prod.getContrasena()))
  				result.append("- Contrasena del usuario -");	
  
 			if (Utilerias.isNullOrUndefined(prod.getContrasenaConf()))
  				result.append("- Contrasena de confirmacion del usuario -");	
 			
 			if (!Utilerias.isNullOrUndefined(prod.getContrasena()) && !Utilerias.isNullOrUndefined(prod.getContrasenaConf())) { 
 				if (!prod.getContrasena().equals(prod.getContrasenaConf())) { 
 					result.append("- La contrasena de confirmacion no es la misma a la capturada inicialmente -");
 				}
 			}
  			if (Utilerias.isNullOrUndefined(prod.getCorreoElectronico()))
  				result.append("- Correo electronico del usuario -");
  			
  			if (Utilerias.isNullOrUndefined(prod.getTelefono()))
  				result.append("- Telefono del usuario -");

  			if (Utilerias.isNullOrUndefined(prod.getRolUsuario().getNombreRol()))
  				result.append("- Rol del usuario -");
		}			
		return result.toString();
	}

	public static void main(String[] args) { 			
	}
}
