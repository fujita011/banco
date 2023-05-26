package com.gft.academy.banco.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "Banco_Friends")
@AllArgsConstructor
@NoArgsConstructor
public class Banco {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Double saldo = 0.0;
	
	@Transient
	private Calculos calculos = new Calculos(this);
	
	@Transient
	private LocalDate diaAtual = LocalDate.now();
	public Double valorAdd( Double mais){
		saldo += mais;
		return saldo;
	}
}
