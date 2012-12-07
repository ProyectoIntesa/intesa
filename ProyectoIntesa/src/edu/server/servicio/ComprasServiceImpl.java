package edu.server.servicio;

import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import edu.client.ComprasService.ComprasService;
import edu.server.dominio.Administrador;
import edu.server.dominio.Compras;
import edu.server.dominio.Estado;
import edu.server.dominio.Empleado;
import edu.server.dominio.ModoDeEnvio;
import edu.server.repositorio.Categoria;
import edu.server.repositorio.Contacto;
import edu.server.repositorio.Direccion;
import edu.server.repositorio.EstadoOrden;
import edu.server.repositorio.IngresoInsumos;
import edu.server.repositorio.IngresoInsumosId;
import edu.server.repositorio.Insumo;
import edu.server.repositorio.Localidad;
import edu.server.repositorio.Marca;
import edu.server.repositorio.ModoEnvio;
import edu.server.repositorio.OrdenCompraInsumo;
import edu.server.repositorio.Pais;
import edu.server.repositorio.Proveedor;
import edu.server.repositorio.ProveedorDeInsumo;
import edu.server.repositorio.ProveedorDeInsumoId;
import edu.server.repositorio.Provincia;
import edu.server.repositorio.RenglonIngresoInsumos;
import edu.server.repositorio.RenglonIngresoInsumosId;
import edu.server.repositorio.RenglonOrdenCompraInsumo;
import edu.server.repositorio.RenglonOrdenCompraInsumoId;
import edu.shared.DTO.ContactoDTO;
import edu.shared.DTO.DireccionDTO;
import edu.shared.DTO.InsumoDTO;
import edu.shared.DTO.OrdenCompraInsumoDTO;
import edu.shared.DTO.ProveedorDTO;
import edu.shared.DTO.ProveedorDeInsumosDTO;
import edu.shared.DTO.RemitoExternoDTO;
import edu.shared.DTO.RenglonOrdenCompraInsumoDTO;
import edu.shared.DTO.RenglonRemitoExternoDTO;

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
				nuevoProv.setPrecio(proveedor.getPrecio());
				
				nuevo.getProveedorDeInsumos().add(nuevoProv);			
				
			}
		}
		
		return adminCompras.registrarInsumo(nuevo);

	}

	@Override
	public Boolean registrarCambioInsumo(InsumoDTO insumo) throws IllegalArgumentException {
		
		Compras adminCompras = new Compras();
		
		Marca marca = new Marca();
		marca.setNombre(insumo.getMarca());
		
		int idMarca = adminCompras.marcaExistente(marca.getNombre());
		marca.setIdMarca(idMarca);
			
		Categoria categoria = new Categoria();
		categoria.setNombre(insumo.getCategoria());
		
		int idCategoria = adminCompras.categoriaExistente(categoria.getNombre());
		categoria.setIdCategoria(idCategoria);
		
		Insumo nuevo = new Insumo();
		nuevo.setIdInsumo(insumo.getIdInsumo());
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
				nuevoProv.setPrecio(proveedor.getPrecio());
				
				
				nuevo.getProveedorDeInsumos().add(nuevoProv);			
				
			}
		}
		
		return adminCompras.registrarCambioInsumo(nuevo);
		
	}
	
	@Override
	public List<String> getNombresInsumos(String letra) throws IllegalArgumentException {
		
		Compras adminCompras = new Compras();
		
		return adminCompras.getNombresInsumos(letra);
		
	}
	
	@Override
	public List<String> getNombresMarcas() throws IllegalArgumentException {
		
		Compras adminCompras = new Compras();
		
		return adminCompras.getNombresMarcas();
		
	}
	
	@Override
	public List<String> getNombresCategorias() throws IllegalArgumentException {
		
		Compras adminCompras = new Compras();
		
		return adminCompras.getNombresCategorias();
		
	}
	
	@Override
	public List<String> getNombresProveedores() throws IllegalArgumentException {
		
		Compras adminCompras = new Compras();
		
		return adminCompras.getNombresProveedores();
		
	}
	
	@Override
	public List<InsumoDTO> getInsumosSegunParametro(String tipo, String dato) throws IllegalArgumentException {
		
		Compras adminCompras = new Compras();
		
		List<InsumoDTO> listResult = new LinkedList<InsumoDTO>();
		List<Insumo> result = new LinkedList<Insumo>();
		
		result = adminCompras.getInsumosSegunParametro(tipo, dato); 
		
		for (Insumo insumo : result) {
			
			InsumoDTO nuevo = new InsumoDTO();
			nuevo.setNombre(insumo.getNombre());
			nuevo.setCategoria(insumo.getCategoria().getNombre());
			nuevo.setMarca(insumo.getMarca().getNombre());
			nuevo.setIdInsumo(insumo.getIdInsumo());
			
			listResult.add(nuevo);				
		}		
		return listResult; 
	
	}
	
	@Override
	public InsumoDTO getInsumoCompleto(int idInsumo, String nombreInsumo)  throws IllegalArgumentException {
		
		Compras adminCompras = new Compras();
		InsumoDTO result = new InsumoDTO();
		Insumo insumo = new Insumo();
		
		insumo = adminCompras.getInsumoCompleto(idInsumo, nombreInsumo);
		
		result.setIdInsumo(insumo.getIdInsumo());
		result.setNombre(insumo.getNombre());
		result.setLoteCompra(insumo.getLoteCompra());
		result.setStockSeguridad(insumo.getStockSeguridad());
		result.setObservaciones(insumo.getObservaciones());
		if(insumo.getCantidad() != -1 && insumo.getCantidad() != 0){
			result.setCantidad(insumo.getCantidad());
		}
		else{
			result.setCantidad(0);
		}
		result.setMarca(insumo.getMarca().getNombre());
		result.setCategoria(insumo.getCategoria().getNombre());
		

			for (ProveedorDeInsumo prov : insumo.getProveedorDeInsumos()) {
				
				ProveedorDeInsumosDTO proveedor = new ProveedorDeInsumosDTO();
				
				Float precio = Float.parseFloat(prov.getPrecio().toString());
				
				proveedor.setPrecio(precio);
				proveedor.setNombre(prov.getProveedor().getNombre());
				proveedor.setObservaciones(prov.getObservaciones());
				
				result.getProveedor().add(proveedor);
				
			}
			
		
		return result;
	}
	
	@Override
	public Boolean eliminarInsumo(InsumoDTO insumo)  throws IllegalArgumentException {
		
		Insumo nuevo = new Insumo();
		nuevo.setIdInsumo(insumo.getIdInsumo());
		Compras adminCompras = new Compras();
		return adminCompras.eliminarInsumo(nuevo);
		
		
		
	}
	
	@Override
	public List<String> getNombresMarcasSegunInsumo(String nombreInsumo)  throws IllegalArgumentException {
		Compras adminCompras = new Compras();
		
		return adminCompras.getNombresMarcasSegunInsumo(nombreInsumo);
	}
	
	@Override
	public List<String> getNombresProvSegunInsumoYMarca(String nombreInsumo, String nombreMarca)  throws IllegalArgumentException {
		
		Compras adminCompras = new Compras();
		
		return adminCompras.getNombresProvSegunInsumoYMarca(nombreInsumo, nombreMarca);
	}

	@Override
	public List<InsumoDTO> getRequerimientosInsumosCompletos()  throws IllegalArgumentException {
		
		Compras adminCompras = new Compras();
		
		List<Object> idInsumos = adminCompras.getRequerimientosNecesario();
		List<InsumoDTO> result = new LinkedList<InsumoDTO>();
		
		for (Object id : idInsumos) {
			InsumoDTO agrega = new InsumoDTO();
			agrega = this.getInsumoCompleto((int)id, ""); 
			result.add(agrega);
		}
		
		return result;
		
	}

	@Override
	public List<InsumoDTO> completarValoresInsumos(List<InsumoDTO> insumos, String proveedor) throws IllegalArgumentException{
		Compras adminCompras = new Compras();
		for (InsumoDTO insumo : insumos) {
			Insumo insuAux = new Insumo();
			int idInsumo =adminCompras.getIdInsumo(insumo.getNombre(), insumo.getMarca());
			int idProv = adminCompras.getProveedorPorNombre(proveedor).getCodigoProveedor();
			insumo.setIdInsumo(idInsumo);
			
			insuAux = adminCompras.getInsumoCompleto(insumo.getIdInsumo(), "");
			insumo.setCantidad(insuAux.getCantidad());
			insumo.setLoteCompra(insuAux.getLoteCompra());
			
			for (ProveedorDeInsumo prov : insuAux.getProveedorDeInsumos()) {
				if(prov.getId().getIdProveedor()== idProv)
				{
					ProveedorDeInsumosDTO proveedorInsu = new ProveedorDeInsumosDTO();
					proveedorInsu.setPrecio(prov.getPrecio());
					insumo.getProveedor().add(proveedorInsu);
				}
			}
		}

		return insumos;
	}

	@Override
	public List<String> getModoDeEnvio()throws IllegalArgumentException{
		ModoDeEnvio adminME = new ModoDeEnvio();
		return adminME.getModoDeEnvio();
	}

	@Override
	public boolean registrarOrdenCompraInsumos(OrdenCompraInsumoDTO orden)throws IllegalArgumentException{
		OrdenCompraInsumo nueva = new OrdenCompraInsumo();
		Compras adminCompras = new Compras();
		Estado adminEstado = new Estado();
		Empleado adEmpleado = new Empleado();
		ModoDeEnvio adminModo = new ModoDeEnvio();
		String nombre = orden.getEmpleado().split(", ")[1];
		String apellido = orden.getEmpleado().split(", ")[0];
		int idEmpleado = adEmpleado.getIdEmpleado(nombre, apellido, "COMPRAS");
		int idEstado = adminEstado.getIdEstado(orden.getEstadoOrden());
		Proveedor prov = adminCompras.getProveedorPorNombre(orden.getProveedor());
		int idModoEnvio = adminModo.getIdModoDeEnvio(orden.getModoEnvio()) ;
		edu.server.repositorio.Empleado responsable = new edu.server.repositorio.Empleado();
		responsable.setIdEmpleado(idEmpleado);
		responsable.setApellido(apellido);
		responsable.setNombre(nombre);
		nueva.setEmpleado(responsable);
		nueva.setProveedor(prov);
		EstadoOrden eo = new EstadoOrden();
		eo.setIdEstadoOrden(idEstado);
		eo.setNombre(orden.getEstadoOrden());
		nueva.setEstadoOrden(eo);
		ModoEnvio me = new ModoEnvio();
		me.setIdModoEnvio(idModoEnvio);
		me.setDescripcion(orden.getModoEnvio());
		nueva.setModoEnvio(me);
		nueva.setIva(orden.getIva());
		nueva.setFechaEdicion(orden.getFechaEdicion());
		if(orden.getFechaGeneracion() != null){nueva.setFechaGeneracion(orden.getFechaGeneracion()); }
		nueva.setFormaPago(orden.getFormaPago());
		nueva.setTotal(orden.getTotal());
		nueva.setObservaciones(orden.getObservaciones());
		
		for (RenglonOrdenCompraInsumoDTO ren : orden.getRenglonOrdenCompraInsumos()) {
			RenglonOrdenCompraInsumo renglon = new RenglonOrdenCompraInsumo();
			RenglonOrdenCompraInsumoId id = new RenglonOrdenCompraInsumoId();
			id.setIdRenglonOrdenCompraInsumo(ren.getItem());
			renglon.setCantidad(ren.getCantidad());
			renglon.setSubtotal(ren.getSubtotal());
			renglon.setId(id);
			int idInsumo = adminCompras.getIdInsumo(ren.getInsumo().getNombre(), ren.getInsumo().getMarca());
			Insumo insu = new Insumo();
			ProveedorDeInsumo provInsumo = new ProveedorDeInsumo();
			ProveedorDeInsumoId provInsumoId = new ProveedorDeInsumoId(prov.getCodigoProveedor(), idInsumo);
			provInsumo.setId(provInsumoId);
			provInsumo.setPrecio(ren.getPrecio());
			insu.getProveedorDeInsumos().add(provInsumo);
			insu.setIdInsumo(idInsumo);
			renglon.setInsumo(insu);
			nueva.getRenglonOrdenCompraInsumos().add(renglon);
		}
		
		return adminCompras.registrarOrdenCompraInsumos(nueva);
	}

	@Override
	public List<String> getNombreEstados()throws IllegalArgumentException{
		
		Estado adminE = new Estado();
		return adminE.getNombreEstados();
		
	}

	@Override
	public List<OrdenCompraInsumoDTO> getOrdenCompraInsumo(String estado, String prov, String fecDesde, String fecHasta) throws IllegalArgumentException{	
		
		Administrador adminAdmin = new Administrador();
		Compras adminCompras = new Compras();
		Estado adminEstados = new Estado();
		List<OrdenCompraInsumo> result = new LinkedList<OrdenCompraInsumo>();
		List<OrdenCompraInsumoDTO> listaResult = new LinkedList<OrdenCompraInsumoDTO>();
		
		int idEstado = adminEstados.getIdEstado(estado);
		int idProv = adminCompras.getIdProveedor(prov);
		
		result = adminCompras.getOrdenCompraInsumo(idEstado, idProv, fecDesde, fecHasta);
		
		for (OrdenCompraInsumo orden : result) {
			OrdenCompraInsumoDTO ordendto = new OrdenCompraInsumoDTO();
			ordendto.setIdOrden(orden.getNroOrdenCompraInsumo());
			if(orden.getNroOrdenCompraInsumoGenerada() == 0)
			{
				ordendto.setNroOrden("S/N");
			}
			else
			{
				DecimalFormat formato = new DecimalFormat("0000000000");
				String numero = ""+formato.format(orden.getNroOrdenCompraInsumoGenerada());
				ordendto.setNroOrden(numero);
			}
			
			ordendto.setEmpleado(adminAdmin.getNombreEmpleado(orden.getEmpleado().getIdEmpleado()));
			ordendto.setEstadoOrden(adminEstados.getNombreEstado(orden.getEstadoOrden().getIdEstadoOrden()));
			ordendto.setProveedor(adminCompras.getNombreProveedor(orden.getProveedor().getCodigoProveedor()));
			
			listaResult.add(ordendto);
			
		}
		return listaResult;
				 
		
	}

	@Override
	public OrdenCompraInsumoDTO getOrdenCompraInsumoSegunId(long idOrden) throws IllegalArgumentException{
		
		OrdenCompraInsumoDTO orden = new OrdenCompraInsumoDTO();
		OrdenCompraInsumo ordenComun = new OrdenCompraInsumo();
		Estado adminEstados = new Estado();
		Compras adminCompras = new Compras();
		Administrador adminAdmin = new Administrador();
		ModoDeEnvio adminModoDeEnvio = new ModoDeEnvio();
		
		orden.setIdOrden(idOrden);
		ordenComun = adminCompras.getOrdenCompraInsumoSegunId(idOrden);
			
		DecimalFormat formato = new DecimalFormat("0000000000");
		String numero = ""+formato.format(ordenComun.getNroOrdenCompraInsumoGenerada());
		orden.setNroOrden(numero);
		
		orden.setEmpleado(adminAdmin.getNombreEmpleado(ordenComun.getEmpleado().getIdEmpleado()));
		orden.setEstadoOrden(adminEstados.getNombreEstado(ordenComun.getEstadoOrden().getIdEstadoOrden()));

		orden.setProveedor(adminCompras.getNombreProveedor(ordenComun.getProveedor().getCodigoProveedor()));

		orden.setModoEnvio(adminModoDeEnvio.getNombreModoEnvio(ordenComun.getModoEnvio().getIdModoEnvio()));
			
		orden.setFormaPago(ordenComun.getFormaPago());
		orden.setIva(ordenComun.getIva());
		orden.setTotal(ordenComun.getTotal());
		orden.setObservaciones(ordenComun.getObservaciones());
		orden.setFechaEdicion(ordenComun.getFechaEdicion());
		orden.setFechaGeneracion(ordenComun.getFechaGeneracion());
		
		Iterator renglones = ordenComun.getRenglonOrdenCompraInsumos().iterator();
		
		while (renglones.hasNext()){
					
			RenglonOrdenCompraInsumoDTO renglonNuevo = new RenglonOrdenCompraInsumoDTO();
			RenglonOrdenCompraInsumo renglon = (RenglonOrdenCompraInsumo) renglones.next();
			
			int idRenglon = ((RenglonOrdenCompraInsumoId)renglon.getId()).getIdRenglonOrdenCompraInsumo();
			
			renglonNuevo.setItem(idRenglon);
			renglonNuevo.setCantidad(renglon.getCantidad());
			renglonNuevo.setSubtotal(renglon.getSubtotal());
					
			InsumoDTO insumo = new InsumoDTO();
			insumo = this.getInsumoCompleto(renglon.getInsumo().getIdInsumo(), "");
					
			renglonNuevo.setInsumo(insumo);
			
			int size = insumo.getProveedor().size();
			
			ProveedorDeInsumosDTO prov = new ProveedorDeInsumosDTO();
			
			for(int j = 0; j < size; j++){
							
				if(insumo.getProveedor().get(j).getNombre().compareTo(orden.getProveedor()) == 0)
					prov = (ProveedorDeInsumosDTO) insumo.getProveedor().get(j);
				
			}
			
			insumo.getProveedor().clear();
			
			insumo.getProveedor().add(prov);
			
			renglonNuevo.setPrecio(insumo.getProveedor().get(0).getPrecio());
			
			orden.getRenglonOrdenCompraInsumos().add(renglonNuevo);
			

		}
					
		
		return orden;
	}
	
	@Override
	public boolean cancelarOrdencompraInsumo(long idOrden, String estado) throws IllegalArgumentException{
		Estado adminEstado = new Estado();
		Compras adminCompras = new Compras();
		int idEstado = adminEstado.getIdEstado(estado);
		return adminCompras.cancelarOrdenCompraInsumo(idOrden, idEstado);
		
	}

	@Override
	public List<OrdenCompraInsumoDTO> getOrdenCompraInsumoGuardada() throws IllegalArgumentException{	
		
		Administrador adminAdmin = new Administrador();
		Compras adminCompras = new Compras();
		
		List<OrdenCompraInsumo> result = new LinkedList<OrdenCompraInsumo>();
		List<OrdenCompraInsumoDTO> listaResult = new LinkedList<OrdenCompraInsumoDTO>();
				
		result = adminCompras.getOrdenCompraInsumoGuardada();
		
		for (OrdenCompraInsumo orden : result) {
			
			OrdenCompraInsumoDTO ordendto = new OrdenCompraInsumoDTO();
			
			ordendto.setIdOrden(orden.getNroOrdenCompraInsumo());
			
			ordendto.setNroOrden("S/N");
			
			ordendto.setEmpleado(adminAdmin.getNombreEmpleado(orden.getEmpleado().getIdEmpleado()));
		
			ordendto.setProveedor(adminCompras.getNombreProveedor(orden.getProveedor().getCodigoProveedor()));
			
			listaResult.add(ordendto);
			
		}
		return listaResult;
				 
		
	}

	@Override
	public List<OrdenCompraInsumoDTO> getOrdenCompraInsumoEnviada() throws IllegalArgumentException{	
		
		Administrador adminAdmin = new Administrador();
		Compras adminCompras = new Compras();
		
		List<OrdenCompraInsumo> result = new LinkedList<OrdenCompraInsumo>();
		List<OrdenCompraInsumoDTO> listaResult = new LinkedList<OrdenCompraInsumoDTO>();
				
		result = adminCompras.getOrdenCompraInsumoEnviada();
		
		for (OrdenCompraInsumo orden : result) {
			
			OrdenCompraInsumoDTO ordendto = new OrdenCompraInsumoDTO();
			
			ordendto.setIdOrden(orden.getNroOrdenCompraInsumo());
			
			DecimalFormat formato = new DecimalFormat("0000000000");
			String numero = ""+formato.format(orden.getNroOrdenCompraInsumoGenerada());
			ordendto.setNroOrden(numero);
							
			ordendto.setEmpleado(adminAdmin.getNombreEmpleado(orden.getEmpleado().getIdEmpleado()));
		
			ordendto.setProveedor(adminCompras.getNombreProveedor(orden.getProveedor().getCodigoProveedor()));
			
			listaResult.add(ordendto);
			
		}
		return listaResult;
				 
		
	}
	
	@Override
	public double getCantFaltanteInsumo(InsumoDTO insumo, long idOrdenCompraInsumo) throws IllegalArgumentException{
		
		double cantidadFaltante = 0;
		double cantidadIngresada = 0;
		Compras adminCompras = new Compras();
		List<IngresoInsumos> listaRemitos = adminCompras.getRemitosExternos(idOrdenCompraInsumo);
		OrdenCompraInsumoDTO orden = this.getOrdenCompraInsumoSegunId(idOrdenCompraInsumo);	
		
		for (RenglonOrdenCompraInsumoDTO renglon : orden.getRenglonOrdenCompraInsumos()) {		
			if(renglon.getInsumo().getIdInsumo() == insumo.getIdInsumo()){
				cantidadFaltante = renglon.getCantidad();
			}
		}
				
		if(!listaRemitos.isEmpty()){	
			for (IngresoInsumos remito : listaRemitos) {
				for (RenglonIngresoInsumos renglon : remito.getRenglonIngresoInsumoses()) {
					if(renglon.getInsumo().getIdInsumo() == insumo.getIdInsumo())
						cantidadIngresada+= renglon.getCantidadIngresada(); 	
				}	
			}			
		}
				
		cantidadFaltante = cantidadFaltante - cantidadIngresada;
		
		return cantidadFaltante;
		
		
	}

	@Override
	public Boolean registrarRemitoExterno(RemitoExternoDTO remito) throws IllegalArgumentException{
		
		Compras adminCompras = new Compras();
		Empleado adminEmpleado = new Empleado();
		Administrador admin = new Administrador();
		
		int idEmpleado = adminEmpleado.getIdEmpleado(remito.getEmpleado());
		
		edu.server.repositorio.Empleado emp = admin.getEmpleado(idEmpleado);
				
		IngresoInsumosId remitoId = new IngresoInsumosId(remito.getIdOrdenCompra(), remito.getIdRemitoEx());
		
		OrdenCompraInsumo orden = new OrdenCompraInsumo();
		orden = adminCompras.getOrdenCompraInsumoSegunId(remito.getIdOrdenCompra());
					
		IngresoInsumos remitoGuardar = new IngresoInsumos();
		remitoGuardar.setFechaIngreso(remito.getFechaIngreso());
		remitoGuardar.setObservaciones(remito.getObservaciones());
		remitoGuardar.setId(remitoId);
		remitoGuardar.setEmpleado(emp);
		remitoGuardar.setOrdenCompraInsumo(orden);
			
		Iterator renglones = remito.getRenglonRemitoExterno().iterator();
			
		while (renglones.hasNext()){
								
			RenglonRemitoExternoDTO renglon = (RenglonRemitoExternoDTO) renglones.next();
			
			int idInsumo = adminCompras.getIdInsumo(renglon.getInsumo().getNombre(), renglon.getInsumo().getMarca());
			
			Insumo insumo = adminCompras.getInsumoCompleto(idInsumo, "");
			
			RenglonIngresoInsumosId renglonId = new RenglonIngresoInsumosId(renglon.getItem(), remito.getIdOrdenCompra(), remito.getIdRemitoEx());
			
			RenglonIngresoInsumos renglonGuardar = new RenglonIngresoInsumos();
			
			renglonGuardar.setId(renglonId);
			renglonGuardar.setCantidadIngresada(renglon.getCantIngresada());
			renglonGuardar.setInsumo(insumo);
			
			remitoGuardar.getRenglonIngresoInsumoses().add(renglonGuardar);
			
		}
		
		return adminCompras.registrarRemitoExterno(remitoGuardar);
		
	}

	@Override
	public List<Long> getRemitosExternos(long idOrdenCompraInsumos) throws IllegalArgumentException{
		
		Compras adminCompras = new Compras();
		List<IngresoInsumos> remitosCompletos = new LinkedList<IngresoInsumos>();
		
		int idorden = adminCompras.getIdOrdenCompraInsumo(idOrdenCompraInsumos);
		
		remitosCompletos = adminCompras.getRemitosExternos(idorden);
		
		List<Long> remitos = new LinkedList<Long>();
		
		for (IngresoInsumos remi : remitosCompletos) {
			
			Long id = ((IngresoInsumosId)remi.getId()).getNroRemitoExterno();
			remitos.add(id);
			
		}
		
	
		return remitos;
		
	}

	@Override
	public RemitoExternoDTO getRemitoExternoCompleto(OrdenCompraInsumoDTO orden, long nroRemito) throws IllegalArgumentException{
		
		Compras adminCompras = new Compras();
		
		
		RemitoExternoDTO result = new RemitoExternoDTO();
		
		
		System.out.println("---------------------------------------------------------------before the call");
			
		
		adminCompras.getRemitoExternoCompleto(orden.getIdOrden(), nroRemito);
		
		System.out.println("----------------------------------------------------------------------------after the call");
		
		return result;
		
	}
	


}
