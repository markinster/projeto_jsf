package com.company.project.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.company.project.entity.Categoria;
import com.company.project.entity.Produto;
import com.company.project.repository.Categorias;
import com.company.project.repository.Produtos;
import com.company.project.repository.filter.ProdutoFilter;
import com.markinster.dont.rename.util.jpa.Transactional;
import com.markinster.dont.rename.util.jsf.BusinessException;

public class CadastroProdutoService implements Serializable {

	private static final long serialVersionUID = 7100704209872206736L;

	@Inject
	private Categorias categorias;
	
	@Inject
	private Produtos produtos;
	
	public List<Categoria> getCategoriasRaiz() {
		return categorias.raizes();
	}

	public List<Categoria> getSubcategorias(Categoria categoriaPai) {
		return categorias.subcategoriasDe(categoriaPai);
	}
	
	@Transactional
	public Produto salvar(Produto produto){
		Produto produtoExistente = produtos.porSKU(produto.getSku());
		
		if (produtoExistente != null && !produtoExistente.equals(produto))
			throw new BusinessException("Ja existe um produto com o SKU " + produto.getSku());
		
		return produtos.guardar(produto);
	}
	
	public List<Produto> pesquisar(ProdutoFilter filter) {
		
		return produtos.filtrados(filter);
	}

	@Transactional
	public void remover(Produto produto) {
		produtos.remover(produto);
		
	}
	

}
