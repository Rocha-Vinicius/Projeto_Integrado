package com.salutem.salutem.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tb_grupo")
public class GrupoModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idGrupo;
	
	@NotNull(message = "Campo Tema não pode ser nulo")
	@Size(min = 5, max = 45)
	private String temaGrupo;
	
	@NotNull(message = "Campo Categoria não pode ser nulo")
	@Size(min = 5, max = 45)
	private String categoriaGrupo;
	
	@NotNull(message = "Campo Descrição não pode ser nulo")
	@Size(min = 5, max = 255)
	private String descricaoGrupo;
	
	@Size(min = 5, max = 45)
	private String urlImagemGrupo;

	public Long getIdGrupo() {
		return idGrupo;
	}

	public void setIdGrupo(Long idGrupo) {
		this.idGrupo = idGrupo;
	}

	public String getTemaGrupo() {
		return temaGrupo;
	}

	public void setTemaGrupo(String temaGrupo) {
		this.temaGrupo = temaGrupo;
	}

	public String getCategoriaGrupo() {
		return categoriaGrupo;
	}

	public void setCategoriaGrupo(String categoriaGrupo) {
		this.categoriaGrupo = categoriaGrupo;
	}

	public String getDescricaoGrupo() {
		return descricaoGrupo;
	}

	public void setDescricaoGrupo(String descricaoGrupo) {
		this.descricaoGrupo = descricaoGrupo;
	}

	public String getImgGrupo() {
		return urlImagemGrupo;
	}

	public void setImgGrupo(String imgGrupo) {
		this.urlImagemGrupo = imgGrupo;
	}
	
}
