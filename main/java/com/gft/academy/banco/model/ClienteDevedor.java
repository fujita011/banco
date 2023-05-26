package com.gft.academy.banco.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "CLIENTE_DEVEDOR")
@NoArgsConstructor
public class ClienteDevedor extends Cliente{
		
	private String empresa;
	private Double renda;
	private Integer rating;
	private Integer totalParcelas;
	private Double valorParcelas;
	private Double valorDevedor;
	private String situacao;
	
	@Transient
	private Calculos calculos = new Calculos(this);
	
	@Transient
	private Cliente cliente;
	
	public ClienteDevedor(String nome, String cpf, String situacao) {
		super(nome, cpf);
		this.situacao = situacao;
	}
	
	public Calculos getCalculos() {
		if(this.calculos.getSolicitante() == null) {
			this.calculos.calcular();			
		}
		return this.calculos;
	}
	
	
	@Override
	public String toString() {
		return "ClienteDevedor [renda=" + renda + ", nome=" + getNome() +", valorDevedor=" + valorDevedor +"]";
	}
}
