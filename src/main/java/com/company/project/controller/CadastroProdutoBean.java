package com.company.project.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import com.company.project.entity.Categoria;
import com.company.project.entity.Produto;
import com.company.project.service.CadastroProdutoService;
import com.markinster.dont.rename.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroProdutoBean implements Serializable {

	private static final long serialVersionUID = 5875894129527629865L;
	
	@Inject
	private CadastroProdutoService service;
		
	//categoria selecionada
	private Categoria categoriaPai;	
	
	//produdo selecionado para alteracao/inclusao
	private Produto produto = new Produto();
	
	//lista de categorias do comboBox
	private List<Categoria> categoriasRaizes = new ArrayList<Categoria>();

	//lista de subcategorias do comboBox
	private List<Categoria> subcategorias = new ArrayList<Categoria>();

	private UploadedFile file;
	
	public Produto getProduto() {
		return produto;
	}
	
	public void setProduto(Produto produto) {
		this.produto = produto;
		
		if (this.produto != null)
			this.categoriaPai = produto.getCategoria().getCategoriaPai();
	}
	
	public List<Categoria> getCategoriasRaizes() {
		return categoriasRaizes;
	}	
	
	public List<Categoria> getSubcategorias() {
		return subcategorias;
	}

	public Categoria getCategoriaPai() {
		return categoriaPai;
	}
	
	public void setCategoriaPai(Categoria categoriaPai) {
		this.categoriaPai = categoriaPai;
	}	
	
	
	public void inicializar(ComponentSystemEvent e) {		
		
		if (!FacesUtil.isPostback()) {
			categoriasRaizes = service.getCategoriasRaiz();
			
			if (categoriaPai != null)
				listaSubcategorias();			
		}
	}
	
	public void listaSubcategorias() {
		subcategorias = service.getSubcategorias(categoriaPai);
	}	
	
	public void salvar () {
		produto = service.salvar(produto);
		limpaFormulario();
		FacesUtil.addInfoMessage("Produto salvo com sucesso");
	}
	
	private void limpaFormulario() {
		produto = new Produto();
		categoriaPai = null; 
		subcategorias = null;
	}
	
	public String getTitulo() {
		return produto.getId() == null ? "Novo Produto" : "Editando produto";
	}
	
	
	public void carregaArquivo(FileUploadEvent event) {
		file = event.getFile();
	}
	
	public UploadedFile getFile() {
		return file;
	}

}
