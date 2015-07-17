package com.company.project.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.company.project.entity.Pedido;
import com.company.project.entity.StatusPedido;
import com.company.project.repository.Pedidos;
import com.company.project.repository.filter.PedidoFilter;

@Named
@ViewScoped
public class PesquisaPedidosBean implements Serializable {

	private static final long serialVersionUID = 2206533405555969224L;

	@Inject
	private Pedidos pedidos;

	private PedidoFilter filtro;
	private List<Pedido> pedidosFiltrados;

	public PesquisaPedidosBean() {
		filtro = new PedidoFilter();
		pedidosFiltrados = new ArrayList<>();
	}

	public void pesquisar() {
		pedidosFiltrados = pedidos.filtrados(filtro);
	}

	public StatusPedido[] getStatuses() {
		return StatusPedido.values();
	}

	public List<Pedido> getPedidosFiltrados() {
		return pedidosFiltrados;
	}

	public PedidoFilter getFiltro() {
		return filtro;
	}

}
