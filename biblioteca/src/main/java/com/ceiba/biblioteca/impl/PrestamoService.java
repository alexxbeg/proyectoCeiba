package com.ceiba.biblioteca.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ceiba.biblioteca.dto.PrestamoDTO;
import com.ceiba.biblioteca.entity.PrestamoEntity;
import com.ceiba.biblioteca.repository.IPrestamoRepository;
import com.ceiba.biblioteca.service.IPrestamoService;


@Service
public class PrestamoService implements IPrestamoService {
	
	@Autowired
	private IPrestamoRepository prestamoRepository;

	@Override
	public String inicio() {
		// TODO Auto-generated method stub
		return "Inicio App Biblioteca";
	}

	@Override
	public String inicio2(String parametro) {
		// TODO Auto-generated method stub
		return "Inicio App Biblioteca " + parametro;
	}
	
	@Override
	public ResponseEntity<JSONObject> validarPrestamo(PrestamoDTO prestamoDTO) throws Exception {
		JSONObject returnJSON= new JSONObject();
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		try {
			if (prestamoDTO.getTipoUsuario()>3 || prestamoDTO.getTipoUsuario()<1) {
				returnJSON.put("mensaje", HttpStatus.BAD_REQUEST.toString() + " Error: Tipo de Usuario NO permitido en la biblioteca");
				return new ResponseEntity<JSONObject>(returnJSON, HttpStatus.BAD_REQUEST);
			}
				
			if (prestamoDTO.getTipoUsuario() == 3) {
				Optional<PrestamoEntity> prestamoEntity = consultarPretamosUsuario(prestamoDTO.getIdentificacionUsuario());
				//Long prestamoEntity = consultarPretamos(prestamoDTO.getIdentificacionUsuario());
				if (prestamoEntity != null) {
					returnJSON.put("mensaje", HttpStatus.BAD_REQUEST.toString() + " Error: El usuario con identificacion " + prestamoDTO.getIdentificacionUsuario() + " ya tien un libro prestado por lo cual no se le puede realizar otro prestamo");
					return new ResponseEntity<JSONObject>(returnJSON, HttpStatus.BAD_REQUEST);
				}
			}
			
			PrestamoEntity registro = guardarPrestamo(prestamoDTO);
			returnJSON.put("isbn",registro.getIsbn());
			returnJSON.put("fechaMaximaDevolucion",formato.format(registro.getFechaMaximaDevolucion()));
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<JSONObject>(returnJSON, HttpStatus.OK);
	}
	

	@Override
	public PrestamoEntity guardarPrestamo(PrestamoDTO prestamoDTO) {
		PrestamoEntity prestamoEntity = new PrestamoEntity();
		prestamoEntity.setId(prestamoDTO.getId());
		prestamoEntity.setIsbn(prestamoDTO.getIsbn().toUpperCase());
		prestamoEntity.setIdentificacionUsuario(prestamoDTO.getIdentificacionUsuario().toUpperCase());
		prestamoEntity.setTipoUsuario(prestamoDTO.getTipoUsuario());
		prestamoEntity.setFechaMaximaDevolucion(calcularFechaEntrega(prestamoDTO.getTipoUsuario()));
		prestamoRepository.save(prestamoEntity);
		
		return prestamoEntity;
	}
	
	private Date calcularFechaEntrega (Long tipoUsuario) {
		int dias = 0;
		Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        
        if (tipoUsuario == 1) {
        	dias = 10;
        } else if (tipoUsuario == 2) {
        	dias = 8;
        } else {
        	dias = 7;
        }
        c.add(Calendar.DATE, dias);
        dt = c.getTime();
		return dt;
	}
	
	@Override
    public Optional<PrestamoEntity> consultarPretamosUsuario(String idUsuario) {
        Optional<PrestamoEntity> prestamoUsuario = prestamoRepository.findPrestamoByIdentificacionUsuario(idUsuario);
        if (prestamoUsuario.isPresent()) {
            return prestamoUsuario;
        } else
            return null;
    }

	public Optional<PrestamoEntity> findPrestamobyId(Long idPrestamo) {
        Optional<PrestamoEntity> prestamoUsuario = prestamoRepository.findPrestamoById(idPrestamo);
        if (prestamoUsuario.isPresent())
            return prestamoUsuario;
        else
            return null;
    }
	
	@Transactional
	public List<PrestamoEntity> findAll() {
		List<PrestamoEntity> listaPreguntas = (List<PrestamoEntity>) prestamoRepository.findAll();
		return listaPreguntas;
	}
	
}
