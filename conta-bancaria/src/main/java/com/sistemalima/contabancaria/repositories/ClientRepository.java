package com.sistemalima.contabancaria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistemalima.contabancaria.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{

}
