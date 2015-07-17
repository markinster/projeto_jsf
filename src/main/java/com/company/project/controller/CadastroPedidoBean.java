package com.company.project.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;

import com.company.project.entity.Cliente;
import com.company.project.entity.EnderecoEntrega;
import com.company.project.entity.FormaPagamento;
import com.company.project.entity.Pedido;
import com.company.project.entity.Usuario;
import com.company.project.repository.Clientes;
import com.company.project.repository.Usuarios;
import com.company.project.repository.filter.ClienteFilter;
import com.company.project.service.CadastroPedidoService;
import com.markinster.dont.rename.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroPedidoBean implements Serializable {

	private static final long serialVersionUID = -2288756937338097106L;

	@Inject
	private CadastroPedidoService service;
	
	@Inject
	private Usuarios usuarios;
	
	@Inject
	private Clientes clientes;

	private Pedido pedido = new Pedido();
	private List<Usuario> listagemVendedores;
	private List<Cliente> listagemClientes;
	
	
	public CadastroPedidoBean() {
		limparFormulario();
	}
	
	public void inicializar(ComponentSystemEvent evt) {
		if (!FacesUtil.isPostback()) {
			listagemVendedores = usuarios.vendedores();
			listagemClientes = clientes.filtrados(new ClienteFilter());
		}
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	public List<Usuario> getVendedores() {
		return listagemVendedores;
	}
	
	public void setVendedores(List<Usuario> vendedores) {
		this.listagemVendedores = vendedores;
	}

	public void salvar() {
		service.salvar(pedido);
		limparFormulario();
		FacesUtil.addInfoMessage("Pedido salvo com sucesso");
	}
	
	public List<Cliente> getListagemClientes() {
		return listagemClientes;
	}

	public void limparFormulario() {
		pedido = new Pedido();
		pedido.setEnderecoEntrega(new EnderecoEntrega());
	}
	
	public FormaPagamento[] getFormasPagamento() {
		return FormaPagamento.values();
	}

}
