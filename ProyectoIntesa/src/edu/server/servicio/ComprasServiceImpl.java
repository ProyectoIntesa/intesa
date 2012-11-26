package edu.server.servicio;

import java.util.LinkedList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import edu.client.ComprasService.ComprasService;
import edu.server.dominio.Compras;
import edu.server.dominio.Ventas;
import edu.server.repositorio.Categoria;
import edu.server.repositorio.Cliente;
import edu.server.repositorio.Contacto;
import edu.server.repositorio.Direccion;
import edu.server.repositorio.Insumo;
import edu.server.repositorio.Localidad;
import edu.server.repositorio.Marca;
import edu.server.repositorio.Pais;
import edu.server.repositorio.Proveedor;
import edu.server.repositorio.ProveedorDeInsumo;
import edu.server.repositorio.ProveedorDeInsumoId;
import edu.server.repositorio.Provincia;
import edu.shared.DTO.ClienteDTO;
import edu.shared.DTO.ContactoDTO;
import edu.shared.DTO.DireccionDTO;
import edu.shared.DTO.InsumoDTO;
import edu.shared.DTO.ProveedorDTO;
import edu.shared.DTO.ProveedorDeInsumosDTO;

public class ComprasServiceImpl extends RemoteServiceServlet implements ComprasService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5338747283996853109L;




	@Override
	public Boolean registrarNuevoProveedor(ProveedorDTO proveedor) throws IllegalArgumentException {
		Pais pais = new Pais();
		Provincia provincia = new Provincia();
		Localidad local = new Localidad();
		Direccion domicilio = new Direccion();
		Proveedor nuevo = new Proveedor();
		pais.setNombre(proveedor.getDireccion().getPais().toUpperCase());
		provincia.setNombre(proveedor.getDireccion().getProvincia().toUpperCase());
		local.setNombre(proveedor.getDireccion().getLocalidad().toUpperCase());
		local.setCodigoPostal(proveedor.getDireccion().getCodigoLocalidad());
		provincia.setPais(pais);
		local.setProvincia(provincia);
		
		domicilio.setCalle(proveedor.getDireccion().getCalle());
		domicilio.setAltura(proveedor.getDireccion().getAltura());
		domicilio.setPiso(proveedor.getDireccion().getPiso());
		domicilio.setOficina(proveedor.getDireccion().getOficina());
		domicilio.setCpa(proveedor.getDireccion().getCpa());
		domicilio.setLocalidad(local);

		nuevo.setNombre(proveedor.getNombre());
		nuevo.setCuit(proveedor.getCuit());
		nuevo.setResponsable(proveedor.getResponsable());
		nuevo.setRubro(proveedor.getRubro());
		nuevo.setFax(proveedor.getFax());
		nuevo.setTelefono(proveedor.getTelefono());
		nuevo.setPaginaWeb(proveedor.getPaginaWeb());
		nuevo.setMail(proveedor.getMail());
		nuevo.setObservaciones(proveedor.getObservaciones());
		nuevo.setTipoProveedor(proveedor.getTipoProveedor());
		nuevo.setDireccion(domicilio);
		if (proveedor.getContacto().size() > 0) {
			for (ContactoDTO contacto : proveedor.getContacto()) {
				Contacto nuevoCont = new Contacto();
				nuevoCont.setCargo(contacto.getCargo());
				nuevoCont.setCelular(contacto.getCelular());
				nuevoCont.setInternoEmpresa(contacto.getInternoEmpresa());
				nuevoCont.setMail(contacto.getMail());
				nuevoCont.setNombre(contacto.getNombre());
				nuevoCont.setTelefonoEmpresa(contacto.getTelefonoEmpresa());
				nuevoCont.setTelefonoParticular(contacto.getTelefonoParticular());
				nuevoCont.setProveedor(nuevo);
				nuevo.getContactos().add(nuevoCont);
			}
		}
		Compras adminCompras = new Compras();
		return adminCompras.registrarProveedor(nuevo);

	}
	
	@Override
	public Boolean registrarCambioProveedor(ProveedorDTO proveedor) throws IllegalArgumentException{
		Pais pais = new Pais();
		Provincia provincia = new Provincia();
		Localidad local = new Localidad();
		Direccion domicilio = new Direccion();
		Proveedor nuevo = new Proveedor();
		pais.setNombre(proveedor.getDireccion().getPais().toUpperCase());
		provincia.setNombre(proveedor.getDireccion().getProvincia().toUpperCase());
		local.setNombre(proveedor.getDireccion().getLocalidad().toUpperCase());
		local.setCodigoPostal(proveedor.getDireccion().getCodigoLocalidad());
		provincia.setPais(pais);
		local.setProvincia(provincia);
		
		domicilio.setCalle(proveedor.getDireccion().getCalle());
		domicilio.setAltura(proveedor.getDireccion().getAltura());
		domicilio.setPiso(proveedor.getDireccion().getPiso());
		domicilio.setOficina(proveedor.getDireccion().getOficina());
		domicilio.setCpa(proveedor.getDireccion().getCpa());
		domicilio.setLocalidad(local);

		nuevo.setNombre(proveedor.getNombre());
		nuevo.setCuit(proveedor.getCuit());
		nuevo.setResponsable(proveedor.getResponsable());
		nuevo.setRubro(proveedor.getRubro());
		nuevo.setFax(proveedor.getFax());
		nuevo.setTelefono(proveedor.getTelefono());
		nuevo.setPaginaWeb(proveedor.getPaginaWeb());
		nuevo.setMail(proveedor.getMail());
		nuevo.setObservaciones(proveedor.getObservaciones());
		nuevo.setTipoProveedor(proveedor.getTipoProveedor());
		nuevo.setDireccion(domicilio);
		if (proveedor.getContacto().size() > 0) {
			for (ContactoDTO contacto : proveedor.getContacto()) {
				Contacto nuevoCont = new Contacto();
				nuevoCont.setCargo(contacto.getCargo());
				nuevoCont.setCelular(contacto.getCelular());
				nuevoCont.setInternoEmpresa(contacto.getInternoEmpresa());
				nuevoCont.setMail(contacto.getMail());
				nuevoCont.setNombre(contacto.getNombre());
				nuevoCont.setTelefonoEmpresa(contacto.getTelefonoEmpresa());
				nuevoCont.setTelefonoParticular(contacto.getTelefonoParticular());
				nuevoCont.setProveedor(nuevo);
				nuevo.getContactos().add(nuevoCont);
			}
		}
		Compras adminCompras = new Compras();
		return adminCompras.registrarCambiosProveedor(nuevo);
	}
	
	
	
	
	@Override
	public ProveedorDTO getEmpresaCompleta(String nombre)  throws IllegalArgumentException{
			
	
		Compras adminCompras = new Compras();
		
		Proveedor busqueda = new Proveedor();
		
		
		busqueda = adminCompras.getEmpresaCompleta(nombre);
								
		ProveedorDTO result = new ProveedorDTO();
		
		DireccionDTO dire = new DireccionDTO();
		
		dire.setCalle(busqueda.getDireccion().getCalle());
		dire.setAltura(busqueda.getDireccion().getAltura());
		dire.setOficina(busqueda.getDireccion().getOficina());
		dire.setPiso(busqueda.getDireccion().getPiso());
		dire.setCpa(busqueda.getDireccion().getCpa());
		dire.setLocalidad(busqueda.getDireccion().getLocalidad().getNombre());
		dire.setProvincia(busqueda.getDireccion().getLocalidad().getProvincia().getNombre());
		dire.setPais(busqueda.getDireccion().getLocalidad().getProvincia().getPais().getNombre());
		dire.setCodigoLocalidad(busqueda.getDireccion().getLocalidad().getCodigoPostal());	
				
		result.setNombre(busqueda.getNombre());
		result.setCuit(busqueda.getCuit());
		result.setRubro(busqueda.getRubro());
		result.setResponsable(busqueda.getResponsable());
		result.setTelefono(busqueda.getTelefono());
		result.setFax(busqueda.getFax());
		result.setMail(busqueda.getMail());
		result.setPaginaWeb(busqueda.getPaginaWeb());
		result.setObservaciones(busqueda.getObservaciones());
		result.setTipoProveedor(busqueda.getTipoProveedor());
		result.setDireccion(dire);
		
		
		
		for (Contacto contacto : busqueda.getContactos()) {

			ContactoDTO nuevo = new ContactoDTO();
			nuevo.setNombre(contacto.getNombre());
			nuevo.setCargo(contacto.getCargo());
			nuevo.setCelular(contacto.getCelular());
			nuevo.setMail(contacto.getMail());
			nuevo.setTelefonoEmpresa(contacto.getTelefonoEmpresa());
			nuevo.setTelefonoParticular(contacto.getTelefonoParticular());
			nuevo.setInternoEmpresa(contacto.getInternoEmpresa());
			
			result.getContacto().add(nuevo);
			
		}				
		
		
			
		return result;
		
	}
	
	@Override
	public List<String> getNombresEmpresas() throws IllegalArgumentException {
		
		Compras adminCompras = new Compras();
		
		return adminCompras.getNombresEmpresas();
		
	}

	
	@Override
	public List<String> getRubros() throws IllegalArgumentException {
		
		Compras adminCompras = new Compras();
		
		return adminCompras.getRubros();
		
	}
	
	@Override
	public List<String> getTipos() throws IllegalArgumentException {
		
		Compras adminCompras = new Compras();
		
		return adminCompras.getTipos();
		
	}
	
	@Override
	public List<String> getContactos() throws IllegalArgumentException {
		
		Compras adminCompras = new Compras();
		
		return adminCompras.getContactos();
		
	}
	
	@Override
	public List<ContactoDTO> getEmpresasPorContacto(String nombre) throws IllegalArgumentException{
		
		List<ContactoDTO> result = new LinkedList<ContactoDTO>();
		Compras adminCompras = new Compras();
		List<Contacto> busqueda = adminCompras.getEmpresasPorContacto(nombre);
	
			
			for (Contacto contacto : busqueda) {

				String[] rubroYempresa = adminCompras.getEmpresaRubroPorIdProveedor(contacto.getProveedor().getCodigoProveedor());

				ContactoDTO nuevo = new ContactoDTO();
				ProveedorDTO nuevoProveedor = new ProveedorDTO();
				nuevoProveedor.setNombre(rubroYempresa[0]);
				nuevoProveedor.setRubro(rubroYempresa[1]);
				nuevo.setNombre(contacto.getNombre());
				nuevo.setCargo(contacto.getCargo());
				nuevo.setProveedor(nuevoProveedor);

				result.add(nuevo);

			}

		
		return result;
		
		
	}
	
	@Override
	public ContactoDTO getContactoCompleto(String nombreContacto, String nombreEmpresa) throws IllegalArgumentException{
		
		Contacto contacto;
		
		Compras adminCompras = new Compras();
		contacto = adminCompras.getContactoCompleto(nombreContacto, nombreEmpresa);
		
		
		ContactoDTO nuevo = new ContactoDTO();
		nuevo.setNombre(contacto.getNombre());
		nuevo.setCargo(contacto.getCargo());
		nuevo.setCelular(contacto.getCelular());
		nuevo.setMail(contacto.getMail());
		nuevo.setTelefonoEmpresa(contacto.getTelefonoEmpresa());
		nuevo.setTelefonoParticular(contacto.getTelefonoParticular());
		nuevo.setInternoEmpresa(contacto.getInternoEmpresa());
		
		return nuevo;
		
	}
	
	@Override
	public Boolean eliminarContacto(String nombreEmpresa, String nombreContacto) throws IllegalArgumentException{
		
		Compras adminCompras = new Compras();
		 
		return adminCompras.eliminarContacto(nombreEmpresa, nombreContacto);
	
	}
	
	@Override
	public List<ProveedorDTO> getEmpresasPorRubro(String nombre) throws IllegalArgumentException{
		
		List<ProveedorDTO> result = new LinkedList<ProveedorDTO>();
		Compras adminCompras = new Compras();
		List<Proveedor> busqueda = adminCompras.getEmpresasPorRubro(nombre);
		
		for (Proveedor proveedor : busqueda) {
			
			ProveedorDTO nuevo = new ProveedorDTO();
			nuevo.setNombre(proveedor.getNombre());
			nuevo.setRubro(proveedor.getRubro());
			nuevo.setTelefono(proveedor.getTelefono());
			nuevo.setMail(proveedor.getMail());
			
			result.add(nuevo);		
			
		}
		
		return result;
		
		
	}
	
	@Override
	public List<ProveedorDTO> getEmpresasPorTipo(String nombre) throws IllegalArgumentException{
		
		List<ProveedorDTO> result = new LinkedList<ProveedorDTO>();
		Compras adminCompras = new Compras();
		List<Proveedor> busqueda = adminCompras.getEmpresasPorTipo(nombre);
		
		for (Proveedor proveedor : busqueda) {
			
			ProveedorDTO nuevo = new ProveedorDTO();
			nuevo.setNombre(proveedor.getNombre());
			nuevo.setRubro(proveedor.getRubro());
			nuevo.setTelefono(proveedor.getTelefono());
			nuevo.setMail(proveedor.getMail());
			
			result.add(nuevo);		
			
		}
		
		return result;
		
		
	}
	
	@Override
	public Boolean eliminarEmpresa(String nombreEmpresa) throws IllegalArgumentException{
		
		Compras adminCompras = new Compras();
		 
		return adminCompras.eliminarEmpresa(nombreEmpresa);
		
		
		
	}
	
	@Override
	public List<ProveedorDTO> getEmpresas(String nombre) throws IllegalArgumentException{
		
		List<ProveedorDTO> result = new LinkedList<ProveedorDTO>();
		Compras adminCompras = new Compras();
		List<Proveedor> busqueda = adminCompras.getEmpresas(nombre);
		
		for (Proveedor proveedor : busqueda) {
			
			ProveedorDTO nuevo = new ProveedorDTO();
			nuevo.setNombre(proveedor.getNombre());
			nuevo.setRubro(proveedor.getRubro());
			nuevo.setTelefono(proveedor.getTelefono());
			nuevo.setMail(proveedor.getMail());
			
			result.add(nuevo);		
			
		}
		
		return result;
		
		
	}
	
	
	@Override
	public int retornaIdContacto(String nombreEmpresa, String nombreContacto) throws IllegalArgumentException{
		
		Compras adminCompras = new Compras();
		return adminCompras.retornaIdContacto(nombreEmpresa, nombreContacto);	
		
	}
	
	@Override
	public Boolean modificarContacto(ContactoDTO contacto, int idContacto) throws IllegalArgumentException{
		
		
		Contacto nuevoCont = new Contacto();
		Compras adminCompras = new Compras();
		Proveedor proveedor = adminCompras.getEmpresaCompleta(contacto.getProveedor().getNombre());
		
		nuevoCont.setIdContacto(idContacto);
		nuevoCont.setCargo(contacto.getCargo());
		nuevoCont.setCelular(contacto.getCelular());
		nuevoCont.setInternoEmpresa(contacto.getInternoEmpresa());
		nuevoCont.setMail(contacto.getMail());
		nuevoCont.setNombre(contacto.getNombre());
		nuevoCont.setTelefonoEmpresa(contacto.getTelefonoEmpresa());
		nuevoCont.setTelefonoParticular(contacto.getTelefonoParticular());
		nuevoCont.setProveedor(null);
		nuevoCont.setProveedor(proveedor);
			
				 
		return adminCompras.registrarCambiosContacto(nuevoCont);
		
		
	}
	
	@Override
	public Boolean registrarNuevoInsumo(InsumoDTO insumo) throws IllegalArgumentException {
		
		Compras adminCompras = new Compras();
		
		Marca marca = new Marca();
		marca.setNombre(insumo.getMarca());
	
		Categoria categoria = new Categoria();
		categoria.setNombre(insumo.getCategoria());
		
		Insumo nuevo = new Insumo();
		nuevo.setCategoria(categoria);
		nuevo.setMarca(marca);
		nuevo.setNombre(insumo.getNombre());
		nuevo.setLoteCompra(insumo.getLoteCompra());
		nuevo.setStockSeguridad(insumo.getStockSeguridad());
		nuevo.setObservaciones(insumo.getObservaciones());
		
		
		if (insumo.getProveedor().size() > 0){
			
			for(ProveedorDeInsumosDTO proveedor : insumo.getProveedor()){
				ProveedorDeInsumoId idPI = new ProveedorDeInsumoId();
				Proveedor buscar = adminCompras.getProveedorPorNombre(proveedor.getNombre());
				idPI.setIdProveedor(buscar.getCodigoProveedor());
				ProveedorDeInsumo nuevoProv = new ProveedorDeInsumo();
				nuevoProv.setProveedor(buscar);
				nuevoProv.setId(idPI);
				nuevoProv.setInsumo(nuevo);
				nuevoProv.setObservaciones(proveedor.getObservaciones()); 
				nuevoProv.setPrecio((double) proveedor.getPrecio());
				
				nuevo.getProveedorDeInsumos().add(nuevoProv);			
				
			}
		}
		
		return adminCompras.registrarInsumo(nuevo);

	}
}
