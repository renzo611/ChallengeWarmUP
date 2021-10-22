package com.alkemy.ChallengeWarmUP.dto;

public class PostFilterDto {
	private String titulo;
	private Long idCategoria;
	
	public PostFilterDto(String titulo, Long idCategoria) {
		this.titulo = titulo;
		this.idCategoria = idCategoria;
	}
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public Long getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}
	
	
}
