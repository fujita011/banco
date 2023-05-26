package com.gft.academy.banco.service;

import org.springframework.stereotype.Service;

import com.gft.academy.banco.model.ClienteDevedor;

@Service
public class ClienteDevedorService {
	
	ClienteDevedor cd = new ClienteDevedor();
	
//	
//	public Double getValorMaximo() {
//		taxa();
//		Double aux = (cd.getRenda() * 3)/ (1 + taxa);
//		aux = aux * 100;
//		Integer auxInt = aux.intValue();
//		aux = auxInt / 100.0;
//		return aux;
//	}
//	
	public Double getValorParcelaMaximo() {
		return cd.getRenda() * 0.30;
	}
 
	
//	public void valorTaxado() {
//		taxa();
//		Double solicitado = cd.getValorDevedor();
//
//    	Double divida = solicitado * (1 + taxa());
//  	Double maxparcelas = divida / 300.0;
//    	Integer totMaxParcelas = maxparcelas.intValue();
//    	Double minparcelas = divida / (cd.getRenda() * 0.30);
//    	Integer aux = minparcelas.intValue();
//    	if ((minparcelas - aux) > 0 ) {
//    		minparcelas += 1;
//    	}
//        Integer totMinParcelas = minparcelas.intValue();  

	}
	

