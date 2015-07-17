package com.company.project.utils;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.company.project.entity.Categoria;
import com.company.project.entity.Grupo;
import com.company.project.entity.Usuario;

public class ImplantarBaseDadosInicial {

	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("CreateDB");
		EntityManager manager = factory.createEntityManager();

		manager.getTransaction().begin();

		try {
			
			
			//criando categorias de produtos
			Categoria brinde =  new Categoria();
			brinde.setDescricao("Brinde");
			manager.persist(brinde);
		
			//subcategorias de brinde
			Categoria brinquedo =  new Categoria();
			brinquedo.setDescricao("Brinquedo");
			brinquedo.setCategoriaPai(brinde);
			manager.persist(brinquedo);
			
			Categoria vestuario =  new Categoria();
			vestuario.setDescricao("Vestuário");
			vestuario.setCategoriaPai(brinde);
			manager.persist(vestuario);
			
			Categoria canecas =  new Categoria();
			canecas.setDescricao("Canecas");
			canecas.setCategoriaPai(brinde);
			manager.persist(canecas);
			
			
			
			// criando grupos de usuarios
			Grupo administrador = new Grupo();
			administrador.setDescricao("ADMINISTRADORES");
			administrador.setNome("ADMINISTRADORES");
			manager.persist(administrador);

			Grupo vendedores = new Grupo();
			vendedores.setDescricao("VENDEDORES");
			vendedores.setNome("VENDEDORES");
			manager.persist(vendedores);

			
			
			
			// criando os usuarios
			Usuario marcos = new Usuario();
			marcos.setNome("Marcos Soares");
			marcos.setEmail("markinster@gmail.com");
			marcos.setSenha(StringUtils.geraMD5("123"));
			ArrayList<Grupo> gruposMarcos = new ArrayList<Grupo>();
			gruposMarcos.add(administrador);
			gruposMarcos.add(vendedores);
			marcos.setGrupos(gruposMarcos);
			manager.persist(marcos);

			Usuario vendedor = new Usuario();
			vendedor.setNome("Vendedor");
			vendedor.setEmail("vendedor@gmail.com");
			vendedor.setSenha(StringUtils.geraMD5("123"));
			ArrayList<Grupo> gruposVend = new ArrayList<Grupo>();
			gruposVend.add(vendedores);
			vendedor.setGrupos(gruposVend);
			manager.persist(vendedor);
			
			
			manager.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
		}

		manager.close();
		factory.close();
	}

}
