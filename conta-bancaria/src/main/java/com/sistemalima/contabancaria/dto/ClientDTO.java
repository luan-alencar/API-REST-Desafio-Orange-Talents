package com.sistemalima.contabancaria.dto;

import java.io.Serializable;
import java.time.Instant;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.hibernate.validator.constraints.br.CPF;

import com.sistemalima.contabancaria.entities.Client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;

	@NotBlank(message = "Campo obrigatório")
	private String nome;

	@NotBlank
	@Email(message = "Favor entrar com um email válido")
	private String email;

	@CPF(message = "Favor entrar com um cpf valido")
	private String cpf;

	@NotNull
	@PastOrPresent(message = "A data de nascimento não pode ser futura")
	private Instant dataNascimento;

	public ClientDTO(Client entity) {
		this.id = entity.getId();
		this.nome = entity.getNome();
		this.email = entity.getEmail();
		this.cpf = entity.getCpf();
		this.dataNascimento = entity.getDataNascimento();
	}
}
