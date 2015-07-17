package com.company.project.controller;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class ProdutoBean implements Serializable {

	private static final long serialVersionUID = -4434920342716180921L;
	
	public void excluir() {
		System.out.println("Executou a exclusao");
	}

}
