package com.api.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.helpdesk.domain.Pessoa;
import com.api.helpdesk.domain.Tecnico;
import com.api.helpdesk.domain.dtos.TecnicoDTO;
import com.api.helpdesk.repositories.PessoaRepository;
import com.api.helpdesk.repositories.TecnicoRepository;
import com.api.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.api.helpdesk.services.exceptions.ObjectnotFoundException;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = tecnicoRepository.findById(id);
//		return obj.orElse(null);
		return obj.orElseThrow(()-> new ObjectnotFoundException("Objeto não encontrado! Id: " + id));
	}

	public List<Tecnico> findAll() {
		return tecnicoRepository.findAll();
	}

	public Tecnico create(TecnicoDTO objDTO) {
		//caso venha um id na req para o db agir como um update
		//salvando ele retornara o ID
		objDTO.setId(null);
		validarCpfEmail(objDTO);
		Tecnico newObj = new Tecnico(objDTO);
		return tecnicoRepository.save(newObj);
	}
	
	private void validarCpfEmail(TecnicoDTO objDTO) {
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
