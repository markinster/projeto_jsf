package com.company.project.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.company.project.entity.Produto;
import com.company.project.repository.Produtos;
import com.markinster.dont.rename.util.cdi.CDIServiceLocator;

@FacesConverter (forClass = Produto.class, value = "produtoConverter")
public class ProdutoConverter implements Converter {
	
	private Produtos produtos;
	
	//forca uma injecao de dependencia
	public ProdutoConverter() {
		produtos =  CDIServiceLocator.getBean(Produtos.class);
	}
	

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		
		Produto Produto = null;
		if (value != null) {
			Long id = Long.valueOf(value);			
			Produto = produtos.porId(id);
		}
		
		return Produto;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		if (value == null)
			return "";
		
		Produto Produto = (Produto) value;
		
		return Produto.getId() + "";
	}

}
