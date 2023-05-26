package com.gft.academy.banco.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gft.academy.banco.model.ClienteDevedor;

public interface ClienteDevedorRepository extends JpaRepository<ClienteDevedor, Long> {

//	@Query(value = "select dataEmprestimo from ClienteDevedor where dataEmprestimo = current_date -1")
//	public List<ClienteDevedor> verificaDia();
	
//	@Query(value = "select * from clienteDevedor where dataEmprestimo = curdate() or dataEmprestimo = date_sub(curdate(),interval 1 day);", nativeQuery = true )
	@Query("select c from ClienteDevedor c where c.dataEmprestimo < ?1 order by rating desc")
	List<ClienteDevedor> verificaDia(LocalDate dataEmprestimo);
//	
}
