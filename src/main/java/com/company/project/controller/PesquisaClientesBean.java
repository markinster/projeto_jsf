package com.company.project.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.company.project.entity.Cliente;
import com.company.project.repository.filter.ClienteFilter;
import com.company.project.service.CadastroClienteService;
import com.markinster.dont.rename.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaClientesBean implements Serializable {

	private static final long serialVersionUID = 6129744475107678436L;
	
	@Inject
	private CadastroClienteService service;
	
	private List<Cliente> listagem;
	
	private Cliente cliente;
	
	@Inject
	private ClienteFilter filtro;
	
	public List<Cliente> getListagem() {
		if (!FacesUtil.isPostback())
			pesquisar();
		
		return listagem;
	}
	
	public void setListagem(List<Cliente> listagem) {
		this.listagem = listagem;
	}
	
	public ClienteFilter getFiltro() {
		return filtro;
	}
	
	public void setFiltro(ClienteFilter filtro) {
		this.filtro = filtro;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public void pesquisar() {
		listagem = service.pesquisar(filtro);
	}
	
	public void excluir() {
		service.excluir(cliente);
		pesquisar();
	}	

}
