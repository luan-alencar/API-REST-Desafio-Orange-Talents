package com.sistemalima.contabancaria.dto;

import java.io.Serializable;
import java.time.Instant;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;

import org.hibernate.validator.constraints.br.CPF;

import com.sistemalima.contabancaria.entities.Client;

public class ClientDTO implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	private long id;
	
	@NotBlank(message = "Campo obrigatório")
	private String nome;
	
	@Email(message = "Favor entrar com um email válido")
	private String email;
	
	@CPF(message = "Favor entrar com um cpf valido")
	private String cpf;
	
	@PastOrPresent(message = "A data de nascimento não pode ser futura")
	private  Instant dataNascimento;
	
	public ClientDTO() {
		
	}

	public ClientDTO(long id, String nome, String email, String cpf, Instant dataNascimento) {
	
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
	}
	
	public ClientDTO(Client entity) {
		this.id = entity.getId();
		this.nome = entity.getNome();
		this.email = entity.getEmail();
		this.cpf = entity.getCpf();
		this.dataNascimento = entity.getDataNascimento();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Instant getDatanascimento() {
		return dataNascimento;
	}

	public void setDatanascimento(Instant dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	
	
}
