package edu.server.servicio;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import edu.client.ProduccionService.ProduccionService;
import edu.server.dominio.Administrador;
import edu.server.dominio.Almacen;
import edu.server.dominio.Estado;
import edu.server.dominio.Insumos;
import edu.server.dominio.Produccion;
import edu.server.repositorio.Empleado;
import edu.server.repositorio.EstadoOrden;
import edu.server.repositorio.Insumo;
import edu.server.repositorio.OrdenProvisionInsumo;
import edu.server.repositorio.ProveedorDeInsumo;
import edu.server.repositorio.RemitoInternoInsumo;
import edu.server.repositorio.RenglonOrdenProvisionInsumo;
import edu.server.repositorio.RenglonOrdenProvisionInsumoId;
import edu.server.repositorio.RenglonRemitoInternoInsumo;
import edu.server.repositorio.RenglonRemitoInternoInsumoId;
import edu.shared.DTO.EmpleadoDTO;
import edu.shared.DTO.InsumoDTO;
import edu.shared.DTO.OrdenProvisionInsumoDTO;
import edu.shared.DTO.ProveedorDeInsumosDTO;
import edu.shared.DTO.RemitoProvisionInsumoDTO;
import edu.shared.DTO.RenglonOrdenProvisionInsumoDTO;
import edu.shared.DTO.RenglonRemitoProvisionInsumoDTO;

public class ProduccionServiceImpl extends RemoteServiceServlet implements ProduccionService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5391111874855282111L;

	@Override
	public List<String> getNombresInsumos(String letra) throws IllegalArgumentException {
		
		Insumos adminInsumo = new Insumos();
		
		return adminInsumo.getNombresInsumos(letra);
		
	}
	
	@Override
	public List<String> getNombresMarcas() throws IllegalArgumentException {
		
		Insumos adminInsumo = new Insumos();
		
		return adminInsumo.getNombresMarcas();
		
	}
	
	@Override
	public List<String> getNombresMarcasSegunInsumo(String nombreInsumo) throws IllegalArgumentException {
		
		Insumos adminInsumos = new Insumos();
		
		return adminInsumos.getNombresMarcasSegunInsumo(nombreInsumo);
	}
	
	
	@Override
	public EmpleadoDTO getEmpleado(String nombre, String apellido, String rol) throws IllegalArgumentException {
		
		edu.server.dominio.Empleado adminEmpleado = new edu.server.dominio.Empleado(); 
		Empleado emp;
		EmpleadoDTO empleadoDTO = new EmpleadoDTO();
		int idEmpleado = adminEmpleado.getIdEmpleado(nombre, apellido, rol);
		
		emp = adminEmpleado.getEmpleado(idEmpleado);
		
		empleadoDTO.setApellido(emp.getApellido());
		empleadoDTO.setNombre(emp.getNombre());
		empleadoDTO.setIdEmpleado(emp.getIdEmpleado());
		
		for (Empleado emple : emp.getEmpleadosForEmpleado()) {
		
			EmpleadoDTO aux = new EmpleadoDTO();
			aux.setIdEmpleado(emple.getIdEmpleado());
			aux.setApellido(emple.getApellido());
			aux.setNombre(emple.getNombre());
			
			empleadoDTO.getListaEmpACargo().add(aux);
			
		}

		return empleadoDTO;
	}
	
	
	@Override
	public boolean registrarOrdenProvisionInsumo(OrdenProvisionInsumoDTO orden) throws IllegalArgumentException {
		Insumos adminInsumos = new Insumos();
		Produccion adminProduccion = new Produccion();
		EstadoOrden estado = new EstadoOrden();
		Estado adminEstado = new Estado();
		int idEstado = adminEstado.getIdEstado("GENERADA");
		estado.setIdEstadoOrden(idEstado);
		estado.setNombre("GENERADA");
		Empleado registradoPor = new Empleado();
		registradoPor.setIdEmpleado(orden.getEmpleadoByIdPedidoPor().getIdEmpleado());
		Empleado registradoPara = new Empleado();
		registradoPara.setIdEmpleado(orden.getEmpleadoByIdPedidoPara().getIdEmpleado());
		
		OrdenProvisionInsumo nuevaOrden = new OrdenProvisionInsumo();
		nuevaOrden.setEmpleadoByIdPedidoPara(registradoPara);
		nuevaOrden.setEmpleadoByIdPedidoPor(registradoPor);
		nuevaOrden.setEstadoOrden(estado);
		nuevaOrden.setFechaEdicion(orden.getFechaEdicion());
		nuevaOrden.setFechaGeneracion(orden.getFechaGeneracion());
		nuevaOrden.setObservaciones(orden.getObservaciones());
		
		for (RenglonOrdenProvisionInsumoDTO renglon : orden.getRenglonOrdenProvisionInsumos()) {
			 RenglonOrdenProvisionInsumo nuevoRenglon = new RenglonOrdenProvisionInsumo();
			 RenglonOrdenProvisionInsumoId id = new RenglonOrdenProvisionInsumoId();
			 id.setIdRenglon(renglon.getIdRenglon());
			 Insumo insumo = new Insumo();
			 int idInsumo = adminInsumos.getIdInsumo(renglon.getInsumo().getNombre(), renglon.getInsumo().getMarca());
			 insumo.setIdInsumo(idInsumo);
			 nuevoRenglon.setCantidadRequerida(renglon.getCantidadRequerida());
			 nuevoRenglon.setInsumo(insumo);
			 nuevoRenglon.setId(id);
		
			 nuevaOrden.getRenglonOrdenProvisionInsumos().add(nuevoRenglon);
		}
		
		return adminProduccion.registrarOrdenProvisionInsumo(nuevaOrden);
		
	}
	
	@Override
	public List<String> getNombreEstados()throws IllegalArgumentException{
		
		Estado adminE = new Estado();
		return adminE.getNombreEstados();
		
	}
	
	@Override
	public List<OrdenProvisionInsumoDTO> getOrdenProvisionInsumo(String estado, int empleadoPor, int empleadoPara, String fecDesde, String fecHasta) throws IllegalArgumentException{
		
		Administrador adminAdmin = new Administrador();
		Estado adminEstados = new Estado();
		int idEstado = adminEstados.getIdEstado(estado);
		Produccion adminProd = new Produccion();
		
		List<OrdenProvisionInsumoDTO> listaResult = new LinkedList<OrdenProvisionInsumoDTO>();
		List<OrdenProvisionInsumo> result = new LinkedList<OrdenProvisionInsumo>();
		
		result = adminProd.getOrdenProvisionInsumo(idEstado, empleadoPor, empleadoPara, fecDesde, fecHasta);
		
		for (OrdenProvisionInsumo orden : result) {
			
			OrdenProvisionInsumoDTO ordendto = new OrdenProvisionInsumoDTO();
			ordendto.setIdOrdenProvisionInsumo(orden.getIdOrdenProvisionInsumo());
			ordendto.setEstadoOrden(adminEstados.getNombreEstado(orden.getEstadoOrden().getIdEstadoOrden()));
			ordendto.setFechaGeneracion(orden.getFechaGeneracion());
			EmpleadoDTO empPor = new EmpleadoDTO();
			empPor.setNombre(adminAdmin.getNombreEmpleado(orden.getEmpleadoByIdPedidoPor().getIdEmpleado()));
			EmpleadoDTO empPara = new EmpleadoDTO();
			empPara.setNombre(adminAdmin.getNombreEmpleado(orden.getEmpleadoByIdPedidoPara().getIdEmpleado()));		
			
			ordendto.setEmpleadoByIdPedidoPor(empPor);
			ordendto.setEmpleadoByIdPedidoPara(empPara);
			
			listaResult.add(ordendto);
			
		}
		
		return listaResult;
		
	}
	
	@Override
	public List<OrdenProvisionInsumoDTO> getOrdenProvisionInsumoCompletos(String estado, int empleadoPor, int empleadoPara, String fecDesde, String fecHasta) throws IllegalArgumentException{
		
		Estado adminEstados = new Estado();
		int idEstado = adminEstados.getIdEstado(estado);
		Produccion adminProd = new Produccion();
		
		List<OrdenProvisionInsumoDTO> listaResult = new LinkedList<OrdenProvisionInsumoDTO>();
		List<OrdenProvisionInsumo> result = new LinkedList<OrdenProvisionInsumo>();
		
		result = adminProd.getOrdenProvisionInsumo(idEstado, empleadoPor, empleadoPara, fecDesde, fecHasta);
		
		for (OrdenProvisionInsumo orden : result) {
			
			OrdenProvisionInsumoDTO ordendto = new OrdenProvisionInsumoDTO();
			ordendto = this.getOrdenProvisionInsumoSegunId(orden.getIdOrdenProvisionInsumo());
			
			listaResult.add(ordendto);
			
		}
		
		return listaResult;
		
	}
	
	@Override
	public OrdenProvisionInsumoDTO getOrdenProvisionInsumoSegunId(long idOrden) throws IllegalArgumentException{
		
		OrdenProvisionInsumoDTO orden = new OrdenProvisionInsumoDTO();
		OrdenProvisionInsumo ordenComun = new OrdenProvisionInsumo();
		Estado adminEstados = new Estado();
		Administrador adminAdmin = new Administrador();
		Produccion adminProd = new Produccion();
		
		ordenComun = adminProd.getOrdenProvisionInsumoSegunId(idOrden);
		orden.setIdOrdenProvisionInsumo(idOrden);
		String estado = adminEstados.getNombreEstado(ordenComun.getEstadoOrden().getIdEstadoOrden());
		orden.setEstadoOrden(estado);
		orden.setFechaGeneracion(ordenComun.getFechaGeneracion());
		
		if(ordenComun.getFechaCierre()!=null)
			orden.setFechaCierre(ordenComun.getFechaCierre());
		
		orden.setObservaciones(ordenComun.getObservaciones());
		
		EmpleadoDTO empPor = new EmpleadoDTO();
		EmpleadoDTO empPara = new EmpleadoDTO();
		Empleado empPorComun = adminAdmin.getEmpleado(ordenComun.getEmpleadoByIdPedidoPor().getIdEmpleado());
		Empleado empParaComun = adminAdmin.getEmpleado(ordenComun.getEmpleadoByIdPedidoPara().getIdEmpleado());
		empPor.setNombre(empPorComun.getNombre());
		empPor.setApellido(empPorComun.getApellido());
		empPara.setNombre(empParaComun.getNombre());
		empPara.setApellido(empParaComun.getApellido());
		
		orden.setEmpleadoByIdPedidoPor(empPor);
		orden.setEmpleadoByIdPedidoPara(empPara);
		
				
		Iterator renglones = ordenComun.getRenglonOrdenProvisionInsumos().iterator();
		
		while (renglones.hasNext()){
					
			RenglonOrdenProvisionInsumoDTO renglonNuevo = new RenglonOrdenProvisionInsumoDTO();
			RenglonOrdenProvisionInsumo renglon = (RenglonOrdenProvisionInsumo) renglones.next();
			
			int idRenglon = ((RenglonOrdenProvisionInsumoId)renglon.getId()).getIdRenglon();
			
			renglonNuevo.setIdRenglon(idRenglon);
			
			InsumoDTO insumoNuevo = new InsumoDTO();
			insumoNuevo = this.getInsumoCompleto(renglon.getInsumo().getIdInsumo(), "");
			
			renglonNuevo.setInsumo(insumoNuevo);
			
			renglonNuevo.setCantidadRequerida(renglon.getCantidadRequerida());
			
			orden.getRenglonOrdenProvisionInsumos().add(renglonNuevo);
						

		}
					
		
		return orden;
	}
	
	
	public InsumoDTO getInsumoCompleto(int idInsumo, String nombreInsumo)  throws IllegalArgumentException {
		
		InsumoDTO result = new InsumoDTO();
		Insumo insumo = new Insumo();
		Insumos adminInsumos = new Insumos();
		
		insumo = adminInsumos.getInsumoCompleto(idInsumo, nombreInsumo);
		
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
	public double getCantFaltanteInsumo(InsumoDTO insumo, long idOrdenProvisionInsumo) throws IllegalArgumentException{
		
		Almacen adminAlmacen = new Almacen();
		double cantidadFaltante = 0;
		double cantidadIngresada = 0;
		
		List<RemitoInternoInsumo> listaRemitos = adminAlmacen.getRemitosInternos(idOrdenProvisionInsumo);				
		OrdenProvisionInsumoDTO orden = this.getOrdenProvisionInsumoSegunId(idOrdenProvisionInsumo);
				
		for (RenglonOrdenProvisionInsumoDTO renglon : orden.getRenglonOrdenProvisionInsumos()) {
			if(renglon.getInsumo().getIdInsumo() == insumo.getIdInsumo()){
				cantidadFaltante = renglon.getCantidadRequerida();
			}
		}	
		
		if(!listaRemitos.isEmpty()){
			for(RemitoInternoInsumo remito : listaRemitos){
				for(RenglonRemitoInternoInsumo renglon : remito.getRenglonRemitoInternoInsumos()){
					if(renglon.getInsumo().getIdInsumo() == insumo.getIdInsumo())
						cantidadIngresada+= renglon.getCantidadEntregada();
				}
			}
		}
		
		cantidadFaltante = cantidadFaltante - cantidadIngresada;
		return cantidadFaltante;
	}
	
	@Override
	public Boolean registrarRemitoProvisionInsumo(RemitoProvisionInsumoDTO remito) throws IllegalArgumentException{
		
		Almacen adminAlmacen = new Almacen();
		edu.server.dominio.Empleado adminEmpleado = new edu.server.dominio.Empleado();
		Estado adminEstado = new Estado();
		Produccion adminProd = new Produccion();
		Insumos adminInsumo = new Insumos();
		Insumo insumo = new Insumo();
		
		int idEstado = adminEstado.getIdEstado(remito.getEstadoOrden());
		EstadoOrden est = adminEstado.getEstadoCompleto(idEstado);
		
		int idEmpleado = adminEmpleado.getIdEmpleado(remito.getEmpleado());
		Empleado emp = adminEmpleado.getEmpleado(idEmpleado);
		
		OrdenProvisionInsumo opi = new OrdenProvisionInsumo();
		opi = adminProd.getOrdenProvisionInsumoSegunId(remito.getIdOrdenProvisionInsumo());
		
		RemitoInternoInsumo remitoGuardar = new RemitoInternoInsumo();
		remitoGuardar.setEmpleado(emp);
		remitoGuardar.setEstadoOrden(est);
		remitoGuardar.setFechaCierre(remito.getFechaCierre());
		remitoGuardar.setFechaEdicion(remito.getFechaEdicion());
		remitoGuardar.setFechaGenaracion(remito.getFechaGenaracion());
		remitoGuardar.setObservaciones(remito.getObservaciones());
		remitoGuardar.setOrdenProvisionInsumo(opi);
		
		long idRemito = adminAlmacen.registrarRemitoProvisionInsumo(remitoGuardar); 
				
		if(idRemito != -1){
			
			Iterator renglones = remito.getRenglonRemitoProvisionInsumos().iterator();
			
			while(renglones.hasNext()){
				
				RenglonRemitoProvisionInsumoDTO renglon = (RenglonRemitoProvisionInsumoDTO) renglones.next();
				
				int idInsumo = adminInsumo.getIdInsumo(renglon.getInsumo().getNombre(), renglon.getInsumo().getMarca());
				insumo = adminInsumo.getInsumoCompleto(idInsumo, "");
		
				RenglonRemitoInternoInsumoId renglonId = new RenglonRemitoInternoInsumoId(renglon.getItem(),idRemito);

				RenglonRemitoInternoInsumo renglonGuardar = new RenglonRemitoInternoInsumo();
				
				renglonGuardar.setId(renglonId);
				renglonGuardar.setInsumo(insumo);
				renglonGuardar.setCantidadEntregada(renglon.getCantidadEntregada());
				
				remitoGuardar.getRenglonRemitoInternoInsumos().add(renglonGuardar);
			}
			
			return adminAlmacen.registrarRenglonesDelRemitoProvisionInsumo(remitoGuardar);	
		}
		else{
			return false;
		}
	}

	
	@Override
	public List<Long> idsRemitosInternosInsumos() throws IllegalArgumentException{
		Produccion adminProd = new Produccion();
		List<Long> result = new LinkedList<Long>();
		for (RemitoInternoInsumo remito : adminProd.getRemitosInternosInsumos()) {
			result.add(remito.getIdRemitoInsumo());	
		}
		return result;	
	}
	
	
	@Override
	public RemitoProvisionInsumoDTO getOrdenRemitoInternoInsumoSegunId(Long id) throws IllegalArgumentException{

		Produccion adminProduccion = new Produccion();
		Estado adminEstado = new Estado();
		edu.server.dominio.Empleado adminEmpleado = new edu.server.dominio.Empleado();
		Insumos adminInsumo = new Insumos();
		
		RemitoProvisionInsumoDTO remitoResult = new RemitoProvisionInsumoDTO();
		RemitoInternoInsumo remitoBuscado = new RemitoInternoInsumo();
		remitoBuscado = adminProduccion.getRemitoInternoInsumoSegunId(id);
		
		String est = adminEstado.getNombreEstado(remitoBuscado.getEstadoOrden().getIdEstadoOrden());
		String emp = adminEmpleado.getNobreYApellidoEmpleado(remitoBuscado.getEmpleado().getIdEmpleado());
		
		remitoResult.setFechaCierre(remitoBuscado.getFechaCierre());
		remitoResult.setFechaEdicion(remitoBuscado.getFechaEdicion());
		remitoResult.setFechaGenaracion(remitoBuscado.getFechaGenaracion());
		remitoResult.setEstadoOrden(est);
		remitoResult.setEmpleado(emp);
		remitoResult.setObservaciones(remitoBuscado.getObservaciones());
		remitoResult.setIdRemitoInsumo(id);
		remitoResult.setIdOrdenProvisionInsumo(remitoBuscado.getOrdenProvisionInsumo().getIdOrdenProvisionInsumo());
		
		Iterator renglones = remitoBuscado.getRenglonRemitoInternoInsumos().iterator();
		
		while(renglones.hasNext()){
			
			RenglonRemitoInternoInsumo renglonBuscado = (RenglonRemitoInternoInsumo) renglones.next();
			RenglonRemitoProvisionInsumoDTO renglonResult = new RenglonRemitoProvisionInsumoDTO();
			int idRenglon = ((RenglonRemitoInternoInsumoId)renglonBuscado.getId()).getIdRenglonRemitoInsumo();
		
			InsumoDTO insumoResult = new InsumoDTO();
			Insumo insumoBuscado = new Insumo();

			insumoBuscado = adminInsumo.getInsumoCompleto(renglonBuscado.getInsumo().getIdInsumo(), "");
			insumoResult.setNombre(insumoBuscado.getNombre());
			insumoResult.setMarca(insumoBuscado.getMarca().getNombre());
			
			renglonResult.setCantidadEntregada(renglonBuscado.getCantidadEntregada());
			renglonResult.setInsumo(insumoResult);
			renglonResult.setItem(idRenglon);
						
			remitoResult.getRenglonRemitoProvisionInsumos().add(renglonResult);		
		}
		
		return remitoResult;
		
		
	}
	
}