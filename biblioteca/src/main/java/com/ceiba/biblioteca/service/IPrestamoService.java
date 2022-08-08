package com.ceiba.biblioteca.service;

import java.util.List;
import java.util.Optional;

import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;

import com.ceiba.biblioteca.dto.PrestamoDTO;
import com.ceiba.biblioteca.entity.PrestamoEntity;

public interface IPrestamoService {
	
	public String inicio ();
	
	public String inicio2 (String parametro);
	
	public ResponseEntity<JSONObject> validarPrestamo (PrestamoDTO prestamoDTO) throws Exception;
	
	public Optional<PrestamoEntity> findPrestamobyId(Long idPrestamo);
	
	public PrestamoEntity guardarPrestamo (PrestamoDTO prestamoDTO) ;

	public Optional<PrestamoEntity> consultarPretamosUsuario(String idUsuario);
	
	public List<PrestamoEntity> findAll();

}
