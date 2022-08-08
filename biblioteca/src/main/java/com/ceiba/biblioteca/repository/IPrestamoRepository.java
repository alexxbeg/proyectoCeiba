package com.ceiba.biblioteca.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ceiba.biblioteca.entity.PrestamoEntity;

@Repository
public interface IPrestamoRepository extends JpaRepository<PrestamoEntity, Integer> {
	
//	@Query("SELECT id FROM Tbl_Prestamo E WHERE id_usuario = ?1")
//	public Long gatCantidadPrestamos(String idUsuario);
	
	Optional<PrestamoEntity> findPrestamoByIdentificacionUsuario(String user);
	
	Optional<PrestamoEntity> findPrestamoById(Long id);

}
