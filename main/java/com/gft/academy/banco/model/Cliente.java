package com.gft.academy.banco.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@AllArgsConstructor
@NoArgsConstructor
public abstract class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String nome;
	private String cpf;
	private String rg;
	private String sexo;
	private String email;
	private String telefone;
	private LocalDate dataNascimento;
	private String rua;
	private String estado;
	private String cidade;
	private String pais;
	private LocalDate dataEmprestimo ;
	

	public Cliente(String nome, String cpf) {
		this.nome = nome;
		this.cpf = cpf;
		
	}
}
