package com.salutem.salutem.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
	
	//Relação entre as entidades
	@ManyToMany(mappedBy = "listaGrupoUsuario", cascade = CascadeType.ALL) 
	@JsonIgnoreProperties("listaGrupoUsuario")
	private List<Usuario> listaDeUsuarios = new ArrayList<>(); 
	
	@OneToMany(mappedBy = "postagens", cascade = CascadeType.ALL)
	@JsonIgnoreProperties ("postagens")
	private List<PostagemModel> listaDePostagens = new ArrayList<>();
	
	

	public String getUrlImagemGrupo() {
		return urlImagemGrupo;
	}

	public void setUrlImagemGrupo(String urlImagemGrupo) {
		this.urlImagemGrupo = urlImagemGrupo;
	}

	public List<Usuario> getListaDeUsuarios() {
		return listaDeUsuarios;
	}

	public void setListaDeUsuarios(List<Usuario> listaDeUsuarios) {
		this.listaDeUsuarios = listaDeUsuarios;
	}

	public List<PostagemModel> getListaDePostagens() {
		return listaDePostagens;
	}

	public void setListaDePostagens(List<PostagemModel> listaDePostagens) {
		this.listaDePostagens = listaDePostagens;
	}

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
