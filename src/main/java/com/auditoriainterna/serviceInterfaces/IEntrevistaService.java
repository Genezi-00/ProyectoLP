package com.auditoriainterna.serviceInterfaces;

import java.util.List;
import java.util.Optional;

import com.auditoriainterna.model.Entrevista;

public interface IEntrevistaService {
	public abstract List<Entrevista> listaEntrevistas();
	 public List<Entrevista> obtenerEntrevistasPorCodigoAuditoria(int codigoAuditoria);
	 public Entrevista crearEntrevista(Entrevista e);
	 public Optional<Entrevista> listaId(int id);
	 public void eliminar(int id);
}
