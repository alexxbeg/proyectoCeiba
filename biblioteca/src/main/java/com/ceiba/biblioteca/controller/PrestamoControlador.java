package com.ceiba.biblioteca.controller;


import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.biblioteca.dto.PrestamoDTO;
import com.ceiba.biblioteca.entity.PrestamoEntity;
import com.ceiba.biblioteca.service.IPrestamoService;

@Produces("application/Json")
@RestController
@RequestMapping("/v1/biblioteca")
public class PrestamoControlador {
	
	@Autowired
	private IPrestamoService prestamoService;
	
	@GetMapping(path="/iniciar")
	public String iniciarApp() {
		return prestamoService.inicio();
	}

	@GetMapping(path="/iniciar2/{parametro}")
	public String iniciarApp2(@PathVariable("parametro") String parametro) {
		return prestamoService.inicio2(parametro);
	}
	
	@PostMapping("/prestamo")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	ResponseEntity<JSONObject> prestamo(@RequestBody PrestamoDTO prestamoDTO) throws Exception{
		ResponseEntity<JSONObject> response = prestamoService.validarPrestamo(prestamoDTO);
		return response;
    }
	
	@GetMapping("/prestamo/{id-prestamo}")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	ResponseEntity<JSONObject> consultaPrestamo(@PathVariable("id-prestamo") String id) throws Exception{
		JSONObject returnJSON= new JSONObject();
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Optional<PrestamoEntity> prestamoEntity = prestamoService.findPrestamobyId(Long.parseLong(id));
		
		returnJSON.put("id",prestamoEntity.get().getId());
		returnJSON.put("isbn",prestamoEntity.get().getIsbn());
		returnJSON.put("identificacionUsuario",prestamoEntity.get().getIdentificacionUsuario());
		returnJSON.put("tipoUsuario",prestamoEntity.get().getTipoUsuario());
		returnJSON.put("fechaMaximaDevolucion",formato.format(prestamoEntity.get().getFechaMaximaDevolucion()));
		
		return new ResponseEntity<JSONObject>(returnJSON, HttpStatus.OK);
    }
	
	@GetMapping(path="/listar")
	@ResponseBody
	public List<PrestamoEntity> listarPrestamos() {
		List<PrestamoEntity> listaPrestamos = (List<PrestamoEntity>) prestamoService.findAll();
		return listaPrestamos;
	}
}

