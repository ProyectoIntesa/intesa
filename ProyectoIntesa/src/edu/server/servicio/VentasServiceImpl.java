package edu.server.servicio;

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

public class VentasServiceImpl extends RemoteServiceServlet implements
		VentasService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6738286800898820770L;

	@Override
	public Boolean registrarNuevoCliente(ClienteDTO cliente)
			throws IllegalArgumentException {
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
		nuevo.setResponsable(cliente.getRubro());
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
}
