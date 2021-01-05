package com.sistemalima.contabancaria.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sistemalima.contabancaria.dto.ClientDTO;
import com.sistemalima.contabancaria.entities.Client;
import com.sistemalima.contabancaria.repositories.ClientRepository;
import com.sistemalima.contabancaria.services.exceptions.DataBaseException;
import com.sistemalima.contabancaria.services.exceptions.ResourceNotFoundException;

// camada de serviço / regra de negocio

@Service
public class ClientService {

	// dependencia para a camada de acesso a dados

	@Autowired
	private ClientRepository repository;

	// metodo buscar todos

	@Transactional(readOnly = true)
	public List<ClientDTO> findAll() {
		List<Client> list = repository.findAll();
		return list.stream().map(x -> new ClientDTO(x)).collect(Collectors.toList());
	}

	// metodo buscar cliente por id

	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {
		Optional<Client> obj = repository.findById(id);
		Client entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new ClientDTO(entity);
	}

	// metodo para inserir um cliente verificando no banco de dados, se já existe
	// email ou cpf duplicados

	@Transactional
	public ClientDTO insert(ClientDTO dto) {

		List<Client> list = repository.findAll();
		Client entity = new Client();
		for (Client client : list) {
			if (!client.getEmail().equals(dto.getEmail()) && !client.getCpf().equals(dto.getCpf())) {
				entity.setNome(dto.getNome());
				entity.setEmail(dto.getEmail());
				entity.setCpf(dto.getCpf());
				entity.setDataNascimento(dto.getDataNascimento());
				entity = repository.save(entity);
			} else {
				throw new DataBaseException("Dados duplicados");
			}
		}

		return new ClientDTO(entity);
	}

	// metodo para atualizar um cliente

	@Transactional
	public ClientDTO update(Long id, ClientDTO dto) {
		try {
			Client entity = repository.getOne(id);
			entity.setNome(dto.getNome());
			entity.setEmail(dto.getEmail());
			entity.setCpf(dto.getCpf());
			entity.setDataNascimento(dto.getDataNascimento());
			entity = repository.save(entity);
			return new ClientDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found" + id);
		}
	}

	// metodo para deletar um cliente / não vai ser monitorado

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) { // exceção guando for deletar id que não existe
			throw new ResourceNotFoundException("Id not found " + id);
		}
	}
}
