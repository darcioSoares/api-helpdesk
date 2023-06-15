package com.api.helpdesk.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.helpdesk.domain.Tecnico;
import com.api.helpdesk.domain.dtos.TecnicoDTO;
import com.api.helpdesk.services.TecnicoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoResource {
	
	@Autowired
	private TecnicoService tecnicoService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id) {
		
		Tecnico obj = tecnicoService.findById(id);
		return ResponseEntity.ok().body(new TecnicoDTO(obj));		
	}
	
	@GetMapping
	public ResponseEntity<List<TecnicoDTO>> findAll(){
		List<Tecnico> list = tecnicoService.findAll();
		List<TecnicoDTO> listDTO = list.stream().map(obj -> new TecnicoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@PostMapping
	public ResponseEntity<TecnicoDTO> create(@Valid @RequestBody TecnicoDTO objDTO){
		Tecnico newObj = tecnicoService.create(objDTO);
		//created espera uma uri, comm esta criando uma uri com id do usuario criado
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}	
	
	
		
	
}
