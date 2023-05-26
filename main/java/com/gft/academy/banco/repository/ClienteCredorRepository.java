package com.gft.academy.banco.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gft.academy.banco.model.ClienteCredor;

public interface ClienteCredorRepository extends JpaRepository<ClienteCredor, Long>{
	
	
}
