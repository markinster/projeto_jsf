package com.company.project.service;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;

import com.company.project.entity.Pedido;
import com.company.project.entity.StatusPedido;
import com.company.project.repository.Pedidos;
import com.markinster.dont.rename.util.jpa.Transactional;

public class CadastroPedidoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Pedidos pedidos;
	
	@Transactional
	public Pedido salvar(Pedido pedido) {
		if (pedido.isNovo()) {
			pedido.setDataCriacao(new Date());
			pedido.setStatus(StatusPedido.ORCAMENTO);
		}
		
		pedido = this.pedidos.guardar(pedido);
		return pedido;
	}
	
}