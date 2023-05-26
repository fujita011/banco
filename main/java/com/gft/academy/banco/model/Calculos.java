package com.gft.academy.banco.model;

import lombok.Data;

@Data
public class Calculos {
	
	private Integer valorMaximo;
	private Double valorMinimo = 300.0;
	private ClienteDevedor solicitante;
	private Integer parcelaMaxima;
	private Integer parcelaMinima;
	private Integer parcelas = 0;
	private Double taxa = 0.0;
	private Double valorPorParcela = 0.0;
	private ClienteCredor credor;
	private Banco banco;
	private Double saldo = 0.0;
	private Double retiraSaldo = 0.0;
	
	

	public Calculos(ClienteDevedor devedor) {
		this.solicitante = devedor;
	}
	
	public void calcular(ClienteDevedor devedor) {
		this.solicitante = devedor;
		calcular();
	}
	
	public Calculos(ClienteCredor credor) {
		this.credor = credor;
	}
	
	
	
	
	public Calculos(Banco banco) {
		this.banco = banco;
	}

	public void calcular() {
		
		calcularValorMaximo();
		calcularParcelas();
	}
	
	
	private void calcularValorMaximo() {
		
		taxa = taxa(taxa);
		System.out.println("chegou aqui");
		Double aux = (solicitante.getRenda() * 3)/ (1 + taxa);
		System.out.println(aux);
        
		
		this.valorMaximo = aux.intValue();
		System.out.println(valorMaximo);
	}

	private Double taxa(Double taxa) {
		switch(solicitante.getRating()) {
		case 1:
			taxa= 0.05;
			break;
		case 2:
			taxa= 0.04;
			break;
		case 3:
			taxa= 0.03;
			break;
		case 4:
			taxa= 0.02;
			break;
		case 5:
			taxa= 0.01;
			break;
		}
		return taxa;
	}
	
	private void calcularParcelas() {
		Double taxa = 0.0;
		
		Double divida = solicitante.getValorDevedor();
		
		if (divida < 300 || divida > valorMaximo) {
			System.out.println(valorMaximo);
			System.out.println(divida);
			System.out.println("FALHOU AQUI");
		}else {
			
			divida = solicitante.getValorDevedor() * (1 + taxa(taxa));
			Double maxparcelas = divida / 300.0;
	    	parcelaMaxima = maxparcelas.intValue();
	    	Double minparcelas = divida / (solicitante.getRenda() * 0.30);
	    	if(minparcelas < 1) {
	    		minparcelas = 1.0;
	    	}
	        parcelaMinima = minparcelas.intValue(); 
	        
		}
	}
	
	public void valorCadaParcela() {
		valorPorParcela = 0.0;
		Double valor = 0.0;
		valor = solicitante.getValorDevedor() / parcelas ;
		valorPorParcela = valor;
	}

	public void somarFundo() {
		saldo = banco.getSaldo() + credor.getDisponivel();
	}
	
	public void tiraFundoAprovado() {
		
		retiraSaldo = banco.getSaldo() - solicitante.getValorDevedor();
		
	}
	
}
