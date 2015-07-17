package com.company.project.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.company.project.entity.Cliente;
import com.company.project.entity.Endereco;
import com.company.project.entity.TipoPessoa;
import com.company.project.service.CadastroClienteService;
import com.markinster.dont.rename.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroClienteBean implements Serializable { 

	private static final long serialVersionUID = 5749306295815215606L;
	
	@Inject
	private CadastroClienteService service;
	
	private Cliente selecionado = new Cliente();
	
	private Endereco endereco = new Endereco();
	
	private List<TipoPessoa> tiposPessoa;
	
			
	public Cliente getSelecionado() {
		return selecionado;
	}
	
	public void setSelecionado(Cliente selecionado) {
		this.selecionado = selecionado;
	}
	
	public List<TipoPessoa> getTiposPessoa() {
		if (tiposPessoa == null){
			tiposPessoa = new ArrayList<TipoPessoa>();
			Collections.addAll(tiposPessoa, TipoPessoa.values());
		}
		
		return tiposPessoa;
	}	
	
	public Endereco getEndereco() {
		return endereco;
	}
	
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public void addEndereco() {
		if(selecionado.getEnderecos() == null)
			selecionado.setEnderecos(new ArrayList<Endereco>());
		
		endereco.setCliente(selecionado);
		selecionado.getEnderecos().add(endereco);
		endereco = new Endereco();
	}
	
	public void salvar() {
		service.salvar(selecionado);
		limpaFormulario();
		FacesUtil.addInfoMessage("Cliente salvo com sucess!");
	}
	
	public void removeEndereco() {
		if (selecionado.getEnderecos() != null && selecionado.getEnderecos().size() > 0)
			selecionado.getEnderecos().remove(endereco);
	}

	private void limpaFormulario() {
		selecionado = new Cliente();
	}
	
	

}
