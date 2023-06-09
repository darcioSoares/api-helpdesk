package com.api.helpdesk.domain;

import java.util.ArrayList;
import java.util.List;

import com.api.helpdesk.domain.enums.Perfil;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Tecnico extends Pessoa{
	private static final long serialVersionUID = 1L;
	
	//tecnico e o nome do campo que esta em chamado, referenciando o relacionamento com essa tabela
	@OneToMany(mappedBy = "tecnico")
	private List<Chamado> chamados = new ArrayList<>();

	public Tecnico() {
		super();		
		//addPerfil mt get de Pessoa, estou sentando um perfil na estanciação da classe
		addPerfil(Perfil.CLIENTE);
	}

	public Tecnico(Integer id, String nome, String cpf, String email, String senha) {
		super(id, nome, cpf, email, senha);		
		addPerfil(Perfil.CLIENTE);
	}

	public List<Chamado> getChamados() {
		return chamados;
	}

	public void setChamados(List<Chamado> chamados) {
		this.chamados = chamados;
	}
	
	

}
