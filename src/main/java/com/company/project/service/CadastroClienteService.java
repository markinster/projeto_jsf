package com.company.project.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.company.project.entity.Cliente;
import com.company.project.repository.Clientes;
import com.company.project.repository.filter.ClienteFilter;
import com.markinster.dont.rename.util.jpa.Transactional;

public class CadastroClienteService implements Serializable {

	private static final long serialVersionUID = 135317711970345871L;
	
	@Inject
	private Clientes clientes;

	@Transactional
	public void salvar(Cliente cliente) {
		String documento = cliente.getDocumentoReceitaFederal();
		documento = documento.replaceAll("/", "");
		documento = documento.replaceAll("-", "");
		documento = documento.replaceAll("\\.", "");
		cliente.setDocumentoReceitaFederal(documento);
		clientes.guarda(cliente);
		
	}

	public List<Cliente> pesquisar(ClienteFilter filtro) {
		return clientes.filtrados(filtro);		
	}

	@Transactional
	public void excluir(Cliente cliente) {
		clientes.remover(cliente);		
	} 


}
