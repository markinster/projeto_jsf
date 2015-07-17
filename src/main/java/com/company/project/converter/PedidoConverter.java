package com.company.project.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.company.project.entity.Pedido;
import com.company.project.repository.Pedidos;
import com.markinster.dont.rename.util.cdi.CDIServiceLocator;

@FacesConverter (forClass = Pedido.class, value = "pedidoConverter")
public class PedidoConverter implements Converter {
	
	private Pedidos pedidos;
	
	//forca uma injecao de dependencia
	public PedidoConverter() {
		pedidos =  CDIServiceLocator.getBean(Pedidos.class);
	}
	

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		
		Pedido pedido = null;
		if (value != null) {
			Long id = Long.valueOf(value);			
			pedido = pedidos.porId(id); 
		}
		
		return pedido;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		if (value == null)
			return "";
		
		Pedido pedido = (Pedido) value;
		
		return pedido.getId() + "";
	}

}
