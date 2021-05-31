package com.salutem.salutem.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tb_usuario")
public class UsuarioModel {


		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private long id;
		
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


		public long getId() {
			return id;
		}


		public void setId(long id) {
			this.id = id;
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
