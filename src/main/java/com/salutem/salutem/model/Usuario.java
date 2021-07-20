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
@Table(name = "tb_usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUsuario;

	@NotNull
	@Size(min=1)
	private String nomeUsuario;

	@NotNull
	@Size(min = 5)
	private String emailUsuario;

	@NotNull
	@Size(min = 8)
	private String senhaUsuario;

	@Size(min = 6, max = 6)
	private String crmUsuario;

	private String urlImagemUsuario;
	
	private String urlCapa;
	
	private String urlBackground;
	

	@ManyToMany(mappedBy = "listaDeUsuarios", cascade = CascadeType.REFRESH)
	@JsonIgnoreProperties("listaDeUsuarios")
	private List<Grupo> listaGrupoUsuario = new ArrayList<>(); // atributo que irá mapear as relações
	
	@OneToMany(mappedBy = "usuarioPostagem", cascade = CascadeType.ALL)
	@JsonIgnoreProperties
	private List<Postagem> listaPostagemUsuario = new ArrayList<>();

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getEmailUsuario() {
		return emailUsuario;
	}

	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}

	public String getSenhaUsuario() {
		return senhaUsuario;
	}

	public void setSenhaUsuario(String senhaUsuario) {
		this.senhaUsuario = senhaUsuario;
	}

	public String getCrmUsuario() {
		return crmUsuario;
	}

	public void setCrmUsuario(String crmUsuario) {
		this.crmUsuario = crmUsuario;
	}

	public String getUrlImagemUsuario() {
		return urlImagemUsuario;
	}

	public void setUrlImagemUsuario(String urlImagemUsuario) {
		this.urlImagemUsuario = urlImagemUsuario;
	}

	public List<Grupo> getListaGrupoUsuario() {
		return listaGrupoUsuario;
	}

	public void setListaGrupoUsuario(List<Grupo> listaGrupoUsuario) {
		this.listaGrupoUsuario = listaGrupoUsuario;
	}

	public List<Postagem> getListaPostagemUsuario() {
		return listaPostagemUsuario;
	}

	public void setListaPostagemUsuario(List<Postagem> listaPostagemUsuario) {
		this.listaPostagemUsuario = listaPostagemUsuario;
	}

	public String getUrlCapa() {
		return urlCapa;
	}

	public void setUrlCapa(String urlCapa) {
		this.urlCapa = urlCapa;
	}

	public String getUrlBackground() {
		return urlBackground;
	}

	public void setUrlBackground(String urlBackground) {
		this.urlBackground = urlBackground;
	}
	
	

}
