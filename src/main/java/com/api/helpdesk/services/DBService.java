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
		
		Tecnico tec1 = new Tecnico(null, "darcio", "67676552576", "dss@sousa", "123");
		tec1.addPerfil(Perfil.ADMIN);
		Tecnico tec2 = new Tecnico(null, "darcio", "6767655576", "dss1@sousa", "123");
		Tecnico tec3 = new Tecnico(null, "teste", "6764455576", "teste@sousa", "123");
		
		
		
		Cliente cli1 = new Cliente(null, "Linus", "0987655688798", "torvalds@mail", "123");
		Cliente cli2 = new Cliente(null, "miau", "07655688798", "miau@mail", "123");
		Cliente cli3 = new Cliente(null, "totvs", "09876558798", "tor@mail", "123");
				
		Chamado c1 = new Chamado(null, Prioridade.MEDIA , Status.ANDAMENTO, "Chamado 01", "primeiro chamado", tec1, cli1); 
		Chamado c2 = new Chamado(null, Prioridade.MEDIA , Status.ABERTO, "Chamado 01", "primeiro chamado", tec2, cli2); 
		Chamado c3 = new Chamado(null, Prioridade.MEDIA , Status.ENCERRADO, "Chamado 01", "primeiro chamado", tec3, cli3); 
		Chamado c4 = new Chamado(null, Prioridade.MEDIA , Status.ABERTO, "Chamado 01", "test chamado", tec1, cli3); 
		Chamado c5 = new Chamado(null, Prioridade.MEDIA , Status.ABERTO, "Chamado 01", "testando chamado", tec1, cli3); 
		
		tecnicoRepository.saveAll(Arrays.asList(tec1,tec2,tec3));
		clienteRepository.saveAll(Arrays.asList(cli1,cli2,cli3));
		chamadoRepository.saveAll(Arrays.asList(c1,c2,c3,c4,c5));
	}
	
	
}

