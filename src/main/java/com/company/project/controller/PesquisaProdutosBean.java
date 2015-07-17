package com.company.project.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.company.project.entity.Produto;
import com.company.project.repository.filter.ProdutoFilter;
import com.company.project.service.CadastroProdutoService;
import com.markinster.dont.rename.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaProdutosBean implements Serializable {

	private static final long serialVersionUID = 2206533405555969224L;	
	
	private String sku;
	private String nome;
	private Produto produto;

	@Inject
	private CadastroProdutoService service;

	private List<Produto> listagem;
	
	public List<Produto> getListagem() {
		if (!FacesUtil.isPostback())
			pesquisar();
		
		return listagem;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getSku() {
		return sku;
	}
	
	public void setSku(String sku) {
		this.sku = sku;
	}
	
	public Produto getProduto() {
		return produto;
	}
	
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public void pesquisar() {
		ProdutoFilter filter = new ProdutoFilter();
		filter.setSku(sku);
		filter.setNome(nome);
		listagem = service.pesquisar(filter);
	}
	
	public void excluir() {
		service.remover(produto);
		pesquisar();
	}
	
}
