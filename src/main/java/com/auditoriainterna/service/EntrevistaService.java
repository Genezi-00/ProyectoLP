package com.auditoriainterna.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.auditoriainterna.model.Entrevista;
import com.auditoriainterna.repositories.EntrevistaRepository;
import com.auditoriainterna.serviceInterfaces.IEntrevistaService;

@Service
public class EntrevistaService implements IEntrevistaService {

	
	
	@Autowired
	private EntrevistaRepository entrevistaRepository;

	 
	
	@Override
	public List<Entrevista> listaEntrevistas() {
		return entrevistaRepository.findAll();
	}


	@Override
	public List<Entrevista> obtenerEntrevistasPorCodigoAuditoria(int codigoAuditoria) {
		return entrevistaRepository.findByCodigoAuditoria(codigoAuditoria);
	}


	@Override
	public Entrevista crearEntrevista(Entrevista e) {
		return entrevistaRepository.save(e);
	}


	@Override
	public Optional<Entrevista> listaId(int id) {
		return entrevistaRepository.findById(id);
	}


	@Override
	public void eliminar(int id) {
		entrevistaRepository.deleteById(id);
		
	}




	



	

	

	
}
