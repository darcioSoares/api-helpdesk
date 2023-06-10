package com.api.helpdesk.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.helpdesk.domain.Chamado;
import com.api.helpdesk.domain.Cliente;
import com.api.helpdesk.domain.Tecnico;
import com.api.helpdesk.domain.enums.Perfil;
import com.api.helpdesk.domain.enums.Prioridade;
import com.api.helpdesk.domain.enums.Status;
import com.api.helpdesk.repositories.ChamadoRepository;
import com.api.helpdesk.repositories.ClienteRepository;
import com.api.helpdesk.repositories.TecnicoRepository;

@Service
public class DBService {

	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;	
	@Autowired
	private ChamadoRepository chamadoRepository;
	
	public void instanciaDB() {
		
		Tecnico tec1 = new Tecnico(null, "darcio", "6767655576", "dss@sousa", "123");
		tec1.addPerfil(Perfil.ADMIN);
		
		Cliente cli1 = new Cliente(null, "Linus", "0987655688798", "torvalds@mail", "123");
		
		Chamado c1 = new Chamado(null, Prioridade.MEDIA , Status.ANDAMENTO, "Chamado 01", "primeiro chamado", tec1, cli1); 
		
		tecnicoRepository.saveAll(Arrays.asList(tec1));
		clienteRepository.saveAll(Arrays.asList(cli1));
		chamadoRepository.saveAll(Arrays.asList(c1));
	}
	}

