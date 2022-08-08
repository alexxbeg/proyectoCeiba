package com.ceiba.biblioteca.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="Tbl_Prestamo")

public class PrestamoEntity {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "ISBN", nullable = false, length = 10)
	private String isbn;
    
	@Column(name = "ID_USUARIO", nullable = false, length = 10)
	private String identificacionUsuario;
    
	@Column(name = "TIPO_USUARIO", nullable = false, length = 2)
	private Long tipoUsuario;
    
	@Column(name = "FECHA_DEVOLUCION", updatable = false)
	@Temporal(TemporalType.DATE)
    private Date fechaMaximaDevolucion;
	
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getIdentificacionUsuario() {
		return identificacionUsuario;
	}
	public void setIdentificacionUsuario(String identificacionUsuario) {
		this.identificacionUsuario = identificacionUsuario;
	}
	public Long getTipoUsuario() {
		return tipoUsuario;
	}
	public void setTipoUsuario(Long tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	public Date getFechaMaximaDevolucion() {
		return fechaMaximaDevolucion;
	}
	public void setFechaMaximaDevolucion(Date fechaMaximaDevolucion) {
		this.fechaMaximaDevolucion = fechaMaximaDevolucion;
	}
	public PrestamoEntity(Long id, String isbn, String identificacionUsuario, Long tipoUsuario, Date fechaMaximaDevolucion) {
		super();
		this.id = id;
		this.isbn = isbn;
		this.identificacionUsuario = identificacionUsuario;
		this.tipoUsuario = tipoUsuario;
		this.fechaMaximaDevolucion = fechaMaximaDevolucion;
	}
	
	public PrestamoEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "PrestamoEntity [id=" + id + ", isbn=" + isbn + ", identificacionUsuario=" + identificacionUsuario
				+ ", tipoUsuario=" + tipoUsuario + ", fechaMaximaDevolucion=" + fechaMaximaDevolucion + "]";
	}
	
	
	
}
