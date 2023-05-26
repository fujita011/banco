package com.gft.academy.banco.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Table(name = "CLIENTE_CREDOR")
@Entity
public class ClienteCredor extends Cliente{
	
	private String empresa;
	private Double disponivel;
	private Double recebido;
	private String situacao;
	
	@Transient
	private Calculos calculos = new Calculos(this);
	
	@Transient
	private Cliente cliente;
	
	public ClienteCredor(String nome, String cpf) {
		super(nome, cpf);
	}

	
	
	
	@Override
	public String toString() {
		return "ClienteCredor [disponivel=" + disponivel + ", recebido=" + recebido + ", getNome()=" + getNome() + "]";
	}
}
