package edu.server.servicio;

import java.util.LinkedList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import edu.client.VentasService.VentasService;
import edu.server.dominio.Ventas;
import edu.server.repositorio.Cliente;
import edu.server.repositorio.Contacto;
import edu.server.repositorio.Direccion;
import edu.server.repositorio.Localidad;
import edu.server.repositorio.Pais;
import edu.server.repositorio.Provincia;
import edu.shared.DTO.ClienteDTO;
import edu.shared.DTO.ContactoDTO;
import edu.shared.DTO.DireccionDTO;

public class VentasServiceImpl extends RemoteServiceServlet implements VentasService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6738286800898820770L;

	@Override
	public Boolean registrarNuevoCliente(ClienteDTO cliente) throws IllegalArgumentException {
		Pais pais = new Pais();
		Provincia provincia = new Provincia();
		Localidad local = new Localidad();
		Direccion domicilio = new Direccion();
		Cliente nuevo = new Cliente();
		pais.setNombre(cliente.getDireccion().getPais().toUpperCase());
		provincia.setNombre(cliente.getDireccion().getProvincia().toUpperCase());
		local.setNombre(cliente.getDireccion().getLocalidad().toUpperCase());
		local.setCodigoPostal(cliente.getDireccion().getCodigoLocalidad());
		provincia.setPais(pais);
		local.setProvincia(provincia);
		
		domicilio.setCalle(cliente.getDireccion().getCalle());
		domicilio.setAltura(cliente.getDireccion().getAltura());
		domicilio.setPiso(cliente.getDireccion().getPiso());
		domicilio.setOficina(cliente.getDireccion().getOficina());
		domicilio.setCpa(cliente.getDireccion().getCpa());
		domicilio.setLocalidad(local);

		nuevo.setNombre(cliente.getNombre());
		nuevo.setCuit(cliente.getCuit());
		nuevo.setResponsable(cliente.getResponsable());
		nuevo.setRubro(cliente.getRubro());
		nuevo.setFax(cliente.getFax());
		nuevo.setTelefono(cliente.getTelefono());
		nuevo.setPaginaWeb(cliente.getPaginaWeb());
		nuevo.setMail(cliente.getMail());
		nuevo.setObservaciones(cliente.getObservaciones());
		nuevo.setDireccion(domicilio);
		if (cliente.getContacto().size() > 0) {
			for (ContactoDTO contacto : cliente.getContacto()) {
				Contacto nuevoCont = new Contacto();
				nuevoCont.setCargo(contacto.getCargo());
				nuevoCont.setCelular(contacto.getCelular());
				nuevoCont.setInternoEmpresa(contacto.getInternoEmpresa());
				nuevoCont.setMail(contacto.getMail());
				nuevoCont.setNombre(contacto.getNombre());
				nuevoCont.setTelefonoEmpresa(contacto.getTelefonoEmpresa());
				nuevoCont.setTelefonoParticular(contacto.getTelefonoParticular());
				nuevoCont.setCliente(nuevo);
				nuevo.getContactos().add(nuevoCont);
			}
		}
		Ventas adminVnetas = new Ventas();
		return adminVnetas.registrarCliente(nuevo);

	}
	
	@Override
	public Boolean registrarCambioCliente(ClienteDTO cliente) throws IllegalArgumentException{
		Pais pais = new Pais();
		Provincia provincia = new Provincia();
		Localidad local = new Localidad();
		Direccion domicilio = new Direccion();
		Cliente nuevo = new Cliente();
		pais.setNombre(cliente.getDireccion().getPais().toUpperCase());
		provincia.setNombre(cliente.getDireccion().getProvincia().toUpperCase());
		local.setNombre(cliente.getDireccion().getLocalidad().toUpperCase());
		local.setCodigoPostal(cliente.getDireccion().getCodigoLocalidad());
		provincia.setPais(pais);
		local.setProvincia(provincia);
		
		domicilio.setCalle(cliente.getDireccion().getCalle());
		domicilio.setAltura(cliente.getDireccion().getAltura());
		domicilio.setPiso(cliente.getDireccion().getPiso());
		domicilio.setOficina(cliente.getDireccion().getOficina());
		domicilio.setCpa(cliente.getDireccion().getCpa());
		domicilio.setLocalidad(local);

		nuevo.setNombre(cliente.getNombre());
		nuevo.setCuit(cliente.getCuit());
		nuevo.setResponsable(cliente.getResponsable());
		nuevo.setRubro(cliente.getRubro());
		nuevo.setFax(cliente.getFax());
		nuevo.setTelefono(cliente.getTelefono());
		nuevo.setPaginaWeb(cliente.getPaginaWeb());
		nuevo.setMail(cliente.getMail());
		nuevo.setObservaciones(cliente.getObservaciones());
		nuevo.setDireccion(domicilio);
		if (cliente.getContacto().size() > 0) {
			for (ContactoDTO contacto : cliente.getContacto()) {
				Contacto nuevoCont = new Contacto();
				nuevoCont.setCargo(contacto.getCargo());
				nuevoCont.setCelular(contacto.getCelular());
				nuevoCont.setInternoEmpresa(contacto.getInternoEmpresa());
				nuevoCont.setMail(contacto.getMail());
				nuevoCont.setNombre(contacto.getNombre());
				nuevoCont.setTelefonoEmpresa(contacto.getTelefonoEmpresa());
				nuevoCont.setTelefonoParticular(contacto.getTelefonoParticular());
				nuevoCont.setCliente(nuevo);
				nuevo.getContactos().add(nuevoCont);
			}
		}
		Ventas adminVnetas = new Ventas();
		return adminVnetas.registrarCambiosCliente(nuevo);
	}
	
	
	
	
	@Override
	public List<String> getNombresEmpresas() throws IllegalArgumentException {
		
		Ventas adminVentas = new Ventas();
		
		return adminVentas.getNombresEmpresas();
		
	}
	
	@Override
	public List<String> getRubros() throws IllegalArgumentException {
		
		Ventas adminVentas = new Ventas();
		
		return adminVentas.getRubros();
		
	}
	
	@Override
	public List<String> getContactos() throws IllegalArgumentException {
		
		Ventas adminVentas = new Ventas();
		
		return adminVentas.getContactos();
		
	}
	
	@Override
	public List<ClienteDTO> getEmpresas(String nombre) throws IllegalArgumentException{
		
		List<ClienteDTO> result = new LinkedList<ClienteDTO>();
		Ventas adminVentas = new Ventas();
		List<Cliente> busqueda = adminVentas.getEmpresas(nombre);
		
		for (Cliente cliente : busqueda) {
			
			ClienteDTO nuevo = new ClienteDTO();
			nuevo.setNombre(cliente.getNombre());
			nuevo.setRubro(cliente.getRubro());
			nuevo.setTelefono(cliente.getTelefono());
			nuevo.setMail(cliente.getMail());
			
			result.add(nuevo);		
			
		}
		
		return result;
		
		
	}
	
	
	@Override
	public List<ClienteDTO> getEmpresasPorRubro(String nombre) throws IllegalArgumentException{
		
		List<ClienteDTO> result = new LinkedList<ClienteDTO>();
		Ventas adminVentas = new Ventas();
		List<Cliente> busqueda = adminVentas.getEmpresasPorRubro(nombre);
		
		for (Cliente cliente : busqueda) {
			
			ClienteDTO nuevo = new ClienteDTO();
			nuevo.setNombre(cliente.getNombre());
			nuevo.setRubro(cliente.getRubro());
			nuevo.setTelefono(cliente.getTelefono());
			nuevo.setMail(cliente.getMail());
			
			result.add(nuevo);		
			
		}
		
		return result;
		
		
	}
	
	
	@Override
	public List<ContactoDTO> getEmpresasPorContacto(String nombre) throws IllegalArgumentException{
		
		List<ContactoDTO> result = new LinkedList<ContactoDTO>();
		Ventas adminVentas = new Ventas();
		List<Contacto> busqueda = adminVentas.getEmpresasPorContacto(nombre);
	
			
			for (Contacto contacto : busqueda) {

				String[] rubroYempresa = adminVentas.getEmpresaRubroPorIdCliente(contacto.getCliente().getIdCliente());

				ContactoDTO nuevo = new ContactoDTO();
				ClienteDTO nuevoCliente = new ClienteDTO();
				nuevoCliente.setNombre(rubroYempresa[0]);
				nuevoCliente.setRubro(rubroYempresa[1]);
				nuevo.setNombre(contacto.getNombre());
				nuevo.setCargo(contacto.getCargo());
				nuevo.setCliente(nuevoCliente);

				result.add(nuevo);

			}

		
		return result;
		
		
	}
	
	
	
	@Override
	public ClienteDTO getEmpresaCompleta(String nombre)  throws IllegalArgumentException{
			
	
		Ventas adminVentas = new Ventas();
		Cliente busqueda = new Cliente();
		
		busqueda = adminVentas.getEmpresaCompleta(nombre);
						
		ClienteDTO result = new ClienteDTO();
		
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
	public ContactoDTO getContactoCompleto(String nombreContacto, String nombreEmpresa) throws IllegalArgumentException{
		
		Contacto contacto;
		
		Ventas adminVentas = new Ventas();
		contacto = adminVentas.getContactoCompleto(nombreContacto, nombreEmpresa);
		
		
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
		
		Ventas adminVentas = new Ventas();
		 
		return adminVentas.eliminarContacto(nombreEmpresa, nombreContacto);
		
		
		
	}
	
	
	@Override
	public Boolean eliminarEmpresa(String nombreEmpresa) throws IllegalArgumentException{
		
		Ventas adminVentas = new Ventas();
		 
		return adminVentas.eliminarEmpresa(nombreEmpresa);
		
		
		
	}
	
	@Override
	public Boolean modificarContacto(ContactoDTO contacto, int idContacto) throws IllegalArgumentException{
		
		
		Contacto nuevoCont = new Contacto();
		Ventas adminVentas = new Ventas();
		Cliente cliente = adminVentas.getEmpresaCompleta(contacto.getCliente().getNombre());
		
		nuevoCont.setIdContacto(idContacto);
		nuevoCont.setCargo(contacto.getCargo());
		nuevoCont.setCelular(contacto.getCelular());
		nuevoCont.setInternoEmpresa(contacto.getInternoEmpresa());
		nuevoCont.setMail(contacto.getMail());
		nuevoCont.setNombre(contacto.getNombre());
		nuevoCont.setTelefonoEmpresa(contacto.getTelefonoEmpresa());
		nuevoCont.setTelefonoParticular(contacto.getTelefonoParticular());
		nuevoCont.setProveedor(null);
		nuevoCont.setCliente(cliente);
			
				 
		return adminVentas.registrarCambiosContacto(nuevoCont);
		
		
	}
	
	
	@Override
	public int retornaIdContacto(String nombreEmpresa, String nombreContacto) throws IllegalArgumentException{
		
		Ventas adminVentas = new Ventas();
		return adminVentas.retornaIdContacto(nombreEmpresa, nombreContacto);	
		
	}
	
	
	
}
