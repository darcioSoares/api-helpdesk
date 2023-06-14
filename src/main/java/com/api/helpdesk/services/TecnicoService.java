package com.api.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.helpdesk.domain.Tecnico;
import com.api.helpdesk.domain.dtos.TecnicoDTO;
import com.api.helpdesk.repositories.TecnicoRepository;
import com.api.helpdesk.services.exceptions.ObjectnotFoundException;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository tecnicoRepository;
	
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
		Tecnico newObj = new Tecnico(objDTO);
		return tecnicoRepository.save(newObj);
	}
	
	
}
