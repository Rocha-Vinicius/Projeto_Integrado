package com.salutem.salutem.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
		private long id_Usuario;
		
		@NotNull
		@Size(min = 5, max = 45)
		private String nomeUsuario;
		
		@NotNull
		@Size(min = 5, max = 45)
		private String emailUsuario;
		
		@NotNull
		@Size (min = 8, max = 20)
		private String senhaUsuario;
		
		@Size(min = 6, max = 6)
		private String crmUsuario;
		
		
		@Size(min = 5, max= 45)
		private String urlImagemUsuario;
		

		// Criando o relacionamento entre usuario e grupo
		@ManyToMany
		@JsonIgnoreProperties("listaDeUsuarios")
		@JoinTable(
                name = "tb_usuariogrupo", 
                joinColumns = @JoinColumn(name = "fk_usuario"), //nomeando p facilitar a busca no banco
                inverseJoinColumns = @JoinColumn(name = "fk_grupo"))
		private List<GrupoModel> listaGrupoUsuario = new ArrayList<>(); // atributo que irá mapear as relações
		
		@OneToMany(mappedBy = "usuarioPostagem", cascade = CascadeType.ALL)
		@JsonIgnoreProperties("usuarioPostagem")
		private List<PostagemModel> listaPostagemUsuario = new ArrayList<>();

		public List<GrupoModel> getListaGrupoUsuario() {
			return listaGrupoUsuario;
		}


		public void setListaGrupoUsuario(List<GrupoModel> listaGrupoUsuario) {
			this.listaGrupoUsuario = listaGrupoUsuario;
		}


		public List<PostagemModel> getListaPostagemUsuario() {
			return listaPostagemUsuario;
		}


		public void setListaPostagemUsuario(List<PostagemModel> listaPostagemUsuario) {
			this.listaPostagemUsuario = listaPostagemUsuario;
		}


		public long getId_Usuario() {
			return id_Usuario;
		}


		public void setId_Usuario(long id_Usuario) {
			this.id_Usuario = id_Usuario;
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

		
	
}
