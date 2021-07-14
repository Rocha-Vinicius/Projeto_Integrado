package com.salutem.salutem.model;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table (name = "tb_postagem")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idPostagem")
public class Postagem {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long idPostagem;
	
	@NotNull
	@Size(min = 5, max = 45)
	private String tituloPostagem;
	
	@NotNull
	@Size (min = 5, max = 255)
	private String descricaoPostagem;
	
	@Size(min = 5, max = 45)
	private String urlImagemPostagem;
	
	@NotNull
	@Size(min = 5, max = 45)
	private String especialidadePostagem;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date data = new java.sql.Date(System.currentTimeMillis());
	
	@ManyToOne 
	@JsonIgnoreProperties("listaDePostagens")
	private Grupo grupoPostagem; // linkando com postagens
	
	@ManyToOne 
	@JsonIgnoreProperties("listaPostagemUsuario")
	private Usuario usuarioPostagem;

	public Long getIdPostagem() {
		return idPostagem;
	}

	public void setIdPostagem(Long idPostagem) {
		this.idPostagem = idPostagem;
	}

	public String getTituloPostagem() {
		return tituloPostagem;
	}

	public void setTituloPostagem(String tituloPostagem) {
		this.tituloPostagem = tituloPostagem;
	}

	public String getDescricaoPostagem() {
		return descricaoPostagem;
	}

	public void setDescricaoPostagem(String descricaoPostagem) {
		this.descricaoPostagem = descricaoPostagem;
	}

	public String getUrlImagemPostagem() {
		return urlImagemPostagem;
	}

	public void setUrlImagemPostagem(String urlImagemPostagem) {
		this.urlImagemPostagem = urlImagemPostagem;
	}

	public String getEspecialidadePostagem() {
		return especialidadePostagem;
	}

	public void setEspecialidadePostagem(String especialidadePostagem) {
		this.especialidadePostagem = especialidadePostagem;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Grupo getGrupoPostagem() {
		return grupoPostagem;
	}

	public void setGrupoPostagem(Grupo grupoPostagem) {
		this.grupoPostagem = grupoPostagem;
	}

	public Usuario getUsuarioPostagem() {
		return usuarioPostagem;
	}

	public void setUsuarioPostagem(Usuario usuarioPostagem) {
		this.usuarioPostagem = usuarioPostagem;
	}
	
}
