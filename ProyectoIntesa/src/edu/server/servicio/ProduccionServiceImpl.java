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
import edu.shared.DTO.UsuarioCompDTO;

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
	public boolean registrarOrdenProvisionInsumoGerente(OrdenProvisionInsumoDTO orden) throws IllegalArgumentException {
		Insumos adminInsumos = new Insumos();
		Produccion adminProduccion = new Produccion();
		EstadoOrden estado = new EstadoOrden();
		Estado adminEstado = new Estado();
		int idEstado = adminEstado.getIdEstado("VALIDADA");
		estado.setIdEstadoOrden(idEstado);
		estado.setNombre("VALIDADA");
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
				
				Double precio = Double.parseDouble(prov.getPrecio().toString());
				
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
	public Long registrarRemitoProvisionInsumo(RemitoProvisionInsumoDTO remito) throws IllegalArgumentException{
		
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
		
				double cantDisponible = ((insumo.getCantidad())-(renglon.getCantidadEntregada()));
				adminInsumo.setCantInsumo(idInsumo, cantDisponible);
				
				RenglonRemitoInternoInsumoId renglonId = new RenglonRemitoInternoInsumoId(renglon.getItem(),idRemito);

				RenglonRemitoInternoInsumo renglonGuardar = new RenglonRemitoInternoInsumo();
				
				renglonGuardar.setId(renglonId);
				renglonGuardar.setInsumo(insumo);
				renglonGuardar.setCantidadEntregada(renglon.getCantidadEntregada());
				
				remitoGuardar.getRenglonRemitoInternoInsumos().add(renglonGuardar);
			}
			
			boolean result = adminAlmacen.registrarRenglonesDelRemitoProvisionInsumo(remitoGuardar);
			
			if(result == true)
				return idRemito;
			else
				return (long) -1.0;
		}
		else{
			return (long) -1.0;
		}
	}
	
	@Override
	public RemitoProvisionInsumoDTO registrarRemitoProvisionInsumo(RemitoProvisionInsumoDTO remito,String nada) throws IllegalArgumentException{
		
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
		
				double cantDisponible = ((insumo.getCantidad())-(renglon.getCantidadEntregada()));
				adminInsumo.setCantInsumo(idInsumo, cantDisponible);
				
				RenglonRemitoInternoInsumoId renglonId = new RenglonRemitoInternoInsumoId(renglon.getItem(),idRemito);

				RenglonRemitoInternoInsumo renglonGuardar = new RenglonRemitoInternoInsumo();
				
				renglonGuardar.setId(renglonId);
				renglonGuardar.setInsumo(insumo);
				renglonGuardar.setCantidadEntregada(renglon.getCantidadEntregada());
				
				remitoGuardar.getRenglonRemitoInternoInsumos().add(renglonGuardar);
			}
			
			boolean result = adminAlmacen.registrarRenglonesDelRemitoProvisionInsumo(remitoGuardar);
			
			if(result == true){
				
				RemitoProvisionInsumoDTO remitoDevolver = new RemitoProvisionInsumoDTO();
				remitoDevolver = this.getOrdenRemitoInternoInsumoSegunId(idRemito);
				return remitoDevolver;
			}
			else{
				RemitoProvisionInsumoDTO remitoDevolver = new RemitoProvisionInsumoDTO();
				remitoDevolver = null;
				return remitoDevolver;
			}
		}
		else{
			RemitoProvisionInsumoDTO remitoDevolver = new RemitoProvisionInsumoDTO();
			remitoDevolver = null;
			return remitoDevolver;
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
	public List<Long> getRemitosInternosInsumosGenerados() throws IllegalArgumentException{
		Produccion adminProd = new Produccion();
		Estado adminEstado = new Estado();
		int est = adminEstado.getIdEstado("GENERADA");
		List<Long> result = new LinkedList<Long>();
		for (RemitoInternoInsumo remito : adminProd.getRemitosInternosInsumosGenerados(est)) {
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
		
	@Override
	public Boolean validarOrdenesProvisionInsumos(List<Long> listaOrdenes) throws IllegalArgumentException{
		
		Boolean result = true;
		Produccion adminProduccion = new Produccion();
		Estado adminEstado = new Estado();
		int est = adminEstado.getIdEstado("VALIDADA");
		
		for (Long nroOrden : listaOrdenes) {
			
			result = adminProduccion.validarOrdenProvisionInsumos(nroOrden,est);
			if(result == false)
				break;
	
		}
		return result;
	}

	@Override
	public Boolean cancelarOrdenesProvisionInsumos(List<Long> listaOrdenes) throws IllegalArgumentException{
		
		Boolean result = true;
		Produccion adminProduccion = new Produccion();
		Estado adminEstado = new Estado();
		int est = adminEstado.getIdEstado("CANCELADA");
		
		for (Long nroOrden : listaOrdenes) {
			
			result = adminProduccion.cancelarOrdenesProvisionInsumos(nroOrden,est);
			if(result == false)
				break;
	
		}
		return result;
	}
	
	@Override
	public Boolean cerrarRemitoProvisionInsumos(RemitoProvisionInsumoDTO remito, String fecha) throws IllegalArgumentException{
				
		Produccion adminProduccion = new Produccion();
		Estado adminEstado = new Estado();
		int est = adminEstado.getIdEstado("CERRADA");
		
		return adminProduccion.cerrarRemitoProvisionInsumos(remito.getIdRemitoInsumo(),est,fecha);

	}
	
	@Override
	public double getCantInsumo(InsumoDTO insumo) throws IllegalArgumentException{
		
		double cantidadDisponible = 0;
		Insumos adminInsumos = new Insumos();
		Insumo insumoResult = new Insumo();
		int idInsumo = adminInsumos.getIdInsumo(insumo.getNombre(), insumo.getMarca());
		insumoResult = adminInsumos.getInsumoCompleto(idInsumo, "");
		int cantDisponible = insumoResult.getCantidad();
		return cantDisponible;
		
	}
	
	@Override
	public List<UsuarioCompDTO> getUsuariosSupervisoresYGerenteProduccion() throws IllegalArgumentException{
		
		Administrador admin = new Administrador();
		List<UsuarioCompDTO> usuarioSupervisores = new LinkedList<UsuarioCompDTO>();
		usuarioSupervisores = admin.getUsuariosSegunRol("supervisor produccion");
		List<UsuarioCompDTO> usuarioGerente = new LinkedList<UsuarioCompDTO>();
		usuarioGerente = admin.getUsuariosSegunRol("gerente produccion");
		List<UsuarioCompDTO> result = new LinkedList<UsuarioCompDTO>();
		for (UsuarioCompDTO usuario : usuarioSupervisores) {
			result.add(usuario);
		}
		for (UsuarioCompDTO usuario : usuarioGerente) {
			result.add(usuario);
		}
		
		return result;
		
		
		
	}
	
	@Override
	public boolean cerrarOrdenesProvision(String fecha) throws IllegalArgumentException{
			
		boolean retorno = false;
		Estado adminEstados = new Estado();
		int idEstado = adminEstados.getIdEstado("VALIDADA");
		Produccion adminProd = new Produccion();
		
		List<OrdenProvisionInsumoDTO> listaResult = new LinkedList<OrdenProvisionInsumoDTO>();
		List<OrdenProvisionInsumo> result = new LinkedList<OrdenProvisionInsumo>();
		List<Long> listaIdOrdenesProvisionInsumosACerrar = new LinkedList<Long>();
		
		result = adminProd.getOrdenProvisionInsumo(idEstado,0,0,"","");
		
		for (OrdenProvisionInsumo orden : result) {
			
			OrdenProvisionInsumoDTO ordendto = new OrdenProvisionInsumoDTO();
			ordendto = this.getOrdenProvisionInsumoSegunId(orden.getIdOrdenProvisionInsumo());
			
			listaResult.add(ordendto);
			
		}
		
		
		for (OrdenProvisionInsumoDTO orden : listaResult) {
			
			long idOrden = orden.getIdOrdenProvisionInsumo();
			
			List<Long> listaIdRemitos = new LinkedList<Long>();
			
			listaIdRemitos = this.getRemitosInternosInsumosSegunIdOrdenProvision(idOrden);
			
			List<RemitoProvisionInsumoDTO> listaRemitos = new LinkedList<RemitoProvisionInsumoDTO>();
			
			for (int i = 0; i < listaIdRemitos.size(); i++){
				
				long idRemito = listaIdRemitos.get(i);
				RemitoProvisionInsumoDTO remito = new RemitoProvisionInsumoDTO();
				remito = this.getOrdenRemitoInternoInsumoSegunId(idRemito);
				listaRemitos.add(remito);
				
			}
			
			List<InsumoDTO> insumosRemitos = new LinkedList<InsumoDTO>();
			List<InsumoDTO> insumosRemitosFinal = new LinkedList<InsumoDTO>();
			
			for (RemitoProvisionInsumoDTO remito : listaRemitos) {
				
				for (RenglonRemitoProvisionInsumoDTO renglon : remito.getRenglonRemitoProvisionInsumos()) {
				
					String nombreInsumo = ((InsumoDTO)renglon.getInsumo()).getNombre();
					String marcaInsumo = ((InsumoDTO)renglon.getInsumo()).getMarca();
					
					InsumoDTO nuevoInsumo = new InsumoDTO();
					nuevoInsumo.setNombre(nombreInsumo);
					nuevoInsumo.setMarca(marcaInsumo);
					nuevoInsumo.setCantidad((int)renglon.getCantidadEntregada());
					
					insumosRemitos.add(nuevoInsumo);
					
				}
			}
			
			for (int k = 0; k < insumosRemitos.size(); k++){
				
				boolean bandera = true;
				
				for (int h = 0; h < insumosRemitosFinal.size(); h++){
					
					String nombreDuplicado = insumosRemitos.get(k).getNombre();
					String marcaDuplicado = insumosRemitos.get(k).getMarca();
					String nombreFinal = insumosRemitosFinal.get(h).getNombre();
					String marcaFinal = insumosRemitosFinal.get(h).getMarca();
					
					if(nombreDuplicado.compareTo(nombreFinal) == 0 && marcaDuplicado.compareTo(marcaFinal) == 0){
						bandera = false;
						break;
					}
				}
				
				if(bandera == true){
					
					InsumoDTO nuevoInsumo = new InsumoDTO();
					nuevoInsumo.setNombre(insumosRemitos.get(k).getNombre());
					nuevoInsumo.setMarca(insumosRemitos.get(k).getMarca());
					nuevoInsumo.setCantidad(0);
					insumosRemitosFinal.add(nuevoInsumo);
				}
			}
			
			for (int p = 0; p < insumosRemitos.size(); p++){
				
				for(int n = 0; n < insumosRemitosFinal.size(); n++){
					
					String nombreDuplicado = insumosRemitos.get(p).getNombre();
					String marcaDuplicado = insumosRemitos.get(p).getMarca();
					String nombreFinal = insumosRemitosFinal.get(n).getNombre();
					String marcaFinal = insumosRemitosFinal.get(n).getMarca();
					
					if(nombreDuplicado.compareTo(nombreFinal) == 0 && marcaDuplicado.compareTo(marcaFinal) == 0){
						
						int cant = insumosRemitos.get(p).getCantidad();
						insumosRemitosFinal.get(n).setCantidad(insumosRemitosFinal.get(n).getCantidad()+cant);
						break;
					}
				}
			}
			
			
			for (RenglonOrdenProvisionInsumoDTO renglon : orden.getRenglonOrdenProvisionInsumos()) {
				
				for (InsumoDTO insumo : insumosRemitosFinal) {
					
					String nombreInsumoRenglon = ((InsumoDTO)renglon.getInsumo()).getNombre();
					String marcaInsumoRenglon = ((InsumoDTO)renglon.getInsumo()).getMarca();
					String nombreInsumo = insumo.getNombre();
					String marcaInsumo = insumo.getMarca();
					
					if(nombreInsumoRenglon.compareTo(nombreInsumo) == 0 && marcaInsumoRenglon.compareTo(marcaInsumo) == 0){
						renglon.setCantidadRequerida(renglon.getCantidadRequerida()-insumo.getCantidad());
					}
				}
			}
			
			boolean banderita = false;
			
			for (RenglonOrdenProvisionInsumoDTO renglon : orden.getRenglonOrdenProvisionInsumos()) {
				
				if(renglon.getCantidadRequerida() != 0){
					banderita = true;
					break;
				}
			}
			
			if(banderita == false){
				
				listaIdOrdenesProvisionInsumosACerrar.add(orden.getIdOrdenProvisionInsumo());
			}
			
		}
		
		
		for (Long id : listaIdOrdenesProvisionInsumosACerrar) {
			
			int est = adminEstados.getIdEstado("CERRADA");
			
			adminProd.cerrarOrdenesProvisionInsumos(id, est, fecha);
			
			retorno = true;
		}
		
		return retorno;
		
	}
		
	@Override
	public List<Long> getRemitosInternosInsumosSegunIdOrdenProvision(long idOrden) throws IllegalArgumentException{
		Produccion adminProd = new Produccion();
		Estado adminEstado = new Estado();
		int est = adminEstado.getIdEstado("CERRADA");
		List<Long> result = new LinkedList<Long>();
		
		
		for (RemitoInternoInsumo remito : adminProd.getRemitosInternosInsumosCerradosDeUnaCiertaOrdenProvisionInsumo(est, idOrden)) {
			result.add(remito.getIdRemitoInsumo());	
		}
		return result;	
	}	
	
}
