package edu.server.servicio;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import edu.client.AlmacenService.AlmacenService;
import edu.server.dominio.Administrador;
import edu.server.dominio.Almacen;
import edu.server.dominio.Compras;
import edu.server.dominio.Empleado;
import edu.server.dominio.Estado;
import edu.server.dominio.Insumos;
import edu.server.dominio.Produccion;
import edu.server.repositorio.IngresoInsumos;
import edu.server.repositorio.IngresoInsumosId;
import edu.server.repositorio.Insumo;
import edu.server.repositorio.Marca;
import edu.server.repositorio.OrdenCompraInsumo;
import edu.server.repositorio.OrdenProvisionInsumo;
import edu.server.repositorio.ProveedorDeInsumo;
import edu.server.repositorio.RenglonIngresoInsumos;
import edu.server.repositorio.RenglonIngresoInsumosId;
import edu.server.repositorio.RenglonOrdenProvisionInsumo;
import edu.server.repositorio.RenglonOrdenProvisionInsumoId;
import edu.shared.DTO.EmpleadoDTO;
import edu.shared.DTO.InsumoDTO;
import edu.shared.DTO.OrdenCompraInsumoDTO;
import edu.shared.DTO.OrdenProvisionInsumoDTO;
import edu.shared.DTO.ProveedorDeInsumosDTO;
import edu.shared.DTO.RemitoExternoDTO;
import edu.shared.DTO.RenglonOrdenCompraInsumoDTO;
import edu.shared.DTO.RenglonOrdenProvisionInsumoDTO;
import edu.shared.DTO.RenglonRemitoExternoDTO;

public class AlmacenServiceImpl extends RemoteServiceServlet implements AlmacenService{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8381582438256731656L;

	
	@Override
	public double getCantFaltanteInsumo(InsumoDTO insumo, long idOrdenCompraInsumo) throws IllegalArgumentException{
		Almacen adminAlmacen = new Almacen();
		double cantidadFaltante = 0;
		double cantidadIngresada = 0;
		ComprasServiceImpl adminComprasSrv = new ComprasServiceImpl();
		
		List<IngresoInsumos> listaRemitos = adminAlmacen.getRemitosExternos(idOrdenCompraInsumo);
		OrdenCompraInsumoDTO orden = adminComprasSrv.getOrdenCompraInsumoSegunId(idOrdenCompraInsumo);	
		
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
		Insumos adminInsumo = new Insumos();
		Almacen adminAlmacen = new Almacen();
		Empleado adminEmpleado = new Empleado();
		Administrador admin = new Administrador();
		Compras adminCompras = new Compras();
		
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
			
			int idInsumo = adminInsumo.getIdInsumo(renglon.getInsumo().getNombre(), renglon.getInsumo().getMarca());
			
			Insumo insumo = adminInsumo.getInsumoCompleto(idInsumo, "");
			
			RenglonIngresoInsumosId renglonId = new RenglonIngresoInsumosId(renglon.getItem(), remito.getIdOrdenCompra(), remito.getIdRemitoEx());
			
			RenglonIngresoInsumos renglonGuardar = new RenglonIngresoInsumos();
			
			renglonGuardar.setId(renglonId);
			renglonGuardar.setCantidadIngresada(renglon.getCantIngresada());
			renglonGuardar.setInsumo(insumo);
			
			remitoGuardar.getRenglonIngresoInsumoses().add(renglonGuardar);
			
		}
		
		return adminAlmacen.registrarRemitoExterno(remitoGuardar);
		
	}

	@Override
	public List<Long> getRemitosExternos(long idOrdenCompraInsumos) throws IllegalArgumentException{
		
		Almacen adminAlmacen = new Almacen();
		Compras adminCompras = new Compras();
		List<IngresoInsumos> remitosCompletos = new LinkedList<IngresoInsumos>();
		
		int idorden = adminCompras.getIdOrdenCompraInsumo(idOrdenCompraInsumos);
		
		remitosCompletos = adminAlmacen.getRemitosExternos(idorden);
		
		List<Long> remitos = new LinkedList<Long>();
		
		for (IngresoInsumos remi : remitosCompletos) {
			
			Long id = ((IngresoInsumosId)remi.getId()).getNroRemitoExterno();
			remitos.add(id);
			
		}
		
	
		return remitos;
		
	}

	@Override
	public RemitoExternoDTO getRemitoExternoCompleto(OrdenCompraInsumoDTO orden, long nroRemito) throws IllegalArgumentException{
		
		Almacen adminAlmacen = new Almacen();	
		RemitoExternoDTO result = new RemitoExternoDTO();
		Empleado adminEmpleado = new Empleado();
		Compras adminCompras = new Compras();
		Insumos adminInsumo = new Insumos();
		
		IngresoInsumos remito = adminAlmacen.getRemitoExternoCompleto(orden.getIdOrden(), nroRemito);
		
		result.setEmpleado(adminEmpleado.getNobreYApellidoEmpleado(remito.getEmpleado().getIdEmpleado()));
		result.setFechaIngreso(remito.getFechaIngreso());
		result.setObservaciones(remito.getObservaciones());
		result.setIdRemitoEx(remito.getId().getNroRemitoExterno());

		
		Iterator renglones = remito.getRenglonIngresoInsumoses().iterator();
		
		while (renglones.hasNext()){
			
			RenglonIngresoInsumos renglonViejo = (RenglonIngresoInsumos) renglones.next();
			InsumoDTO insumoNuevo = new InsumoDTO();
			Insumo insumoViejo = new Insumo();
			
			insumoViejo = adminInsumo.getInsumoCompleto(renglonViejo.getInsumo().getIdInsumo(), "");
						
			insumoNuevo.setNombre(insumoViejo.getNombre());
			
			Marca marca = new Marca();
			marca.setNombre(adminInsumo.getNombreMarca(insumoViejo.getMarca().getIdMarca()));
			
			insumoNuevo.setMarca(marca.getNombre());
				
			RenglonRemitoExternoDTO renglonNuevo = new RenglonRemitoExternoDTO();
		
			renglonNuevo.setInsumo(insumoNuevo);
			renglonNuevo.setCantIngresada(renglonViejo.getCantidadIngresada());
			
			result.getRenglonRemitoExterno().add(renglonNuevo);
		}

		return result;
		
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
		edu.server.repositorio.Empleado empPorComun = adminAdmin.getEmpleado(ordenComun.getEmpleadoByIdPedidoPor().getIdEmpleado());
		edu.server.repositorio.Empleado empParaComun = adminAdmin.getEmpleado(ordenComun.getEmpleadoByIdPedidoPara().getIdEmpleado());
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
	
	
	
	
}
