package com.api.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.helpdesk.domain.Pessoa;
import com.api.helpdesk.domain.Cliente;
import com.api.helpdesk.domain.dtos.ClienteDTO;
import com.api.helpdesk.repositories.PessoaRepository;
import com.api.helpdesk.repositories.ClienteRepository;
import com.api.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.api.helpdesk.services.exceptions.ObjectnotFoundException;

import jakarta.validation.Valid;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository tecnicoRepository;
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Cliente findById(Integer id) {
		Optional<Cliente> obj = tecnicoRepository.findById(id);
//		return obj.orElse(null);
		return obj.orElseThrow(()-> new ObjectnotFoundException("Objeto não encontrado! Id: " + id));
	}

	public List<Cliente> findAll() {
		return tecnicoRepository.findAll();
	}

	public Cliente create(ClienteDTO objDTO) {
		//caso venha um id na req para o db agir como um update
		//salvando ele retornara o ID
		objDTO.setId(null);
		validarCpfEmail(objDTO);
		Cliente newObj = new Cliente(objDTO);
		return tecnicoRepository.save(newObj);
	}
	
	public Cliente update(Integer id, @Valid ClienteDTO objDTO) {
		objDTO.setId(id);
		Cliente oldObj = findById(id);
		validarCpfEmail(objDTO);
		oldObj = new Cliente(objDTO);
		return tecnicoRepository.save(oldObj);
	}
	
	public void delete(Integer id) {
		Cliente obj = findById(id);
		if(obj.getChamados().size() > 0) {
			throw new DataIntegrityViolationException("Cliente possui ordens de serviço, não pode ser Deletado");
		}
		tecnicoRepository.deleteById(id);
	}
	
	private void validarCpfEmail(ClienteDTO objDTO) {
		Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
		//estou validando se o cpf já existe, se os ID são compativeis para reutilizar o metodo em update tecnico
		if(obj.isPresent() && obj.get().getId() !=  objDTO.getId()) {
			throw new DataIntegrityViolationException("CPF já cadastrado");
		}
		
		obj = pessoaRepository.findByEmail(objDTO.getEmail());
		if(obj.isPresent() && obj.get().getId() !=  objDTO.getId()) {
			throw new DataIntegrityViolationException("Email já Cadastrado");
		}
	}

	


	
	
}
