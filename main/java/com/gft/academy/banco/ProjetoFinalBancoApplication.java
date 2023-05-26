package com.gft.academy.banco;

import java.io.FileInputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;
//
//import com.gft.academy.banco.model.ClienteDevedor;
//import com.gft.academy.banco.repository.ClienteDevedorRepository;
import org.springframework.context.annotation.Bean;

import com.gft.academy.banco.model.Banco;
import com.gft.academy.banco.model.ClienteCredor;
import com.gft.academy.banco.model.ClienteDevedor;
import com.gft.academy.banco.repository.BancoRepository;
import com.gft.academy.banco.repository.ClienteCredorRepository;
import com.gft.academy.banco.repository.ClienteDevedorRepository;

@SpringBootApplication
public class ProjetoFinalBancoApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ProjetoFinalBancoApplication.class, args);
	}
	
	@Bean
	CommandLineRunner run(ClienteDevedorRepository devedorRepository, ClienteCredorRepository credorRepository, BancoRepository bancoRepository) {
		return persons -> {
			InputStream is = new FileInputStream("solicitantes.csv");
			Scanner s = new Scanner(is);
			List<ClienteDevedor> listaDevedor = new ArrayList<>();
			
			
			while(s.hasNext()) {
				ClienteDevedor cd = new ClienteDevedor();
				String line = s.nextLine();
				
				Scanner lineScanner = new Scanner(line);
				
				lineScanner.useDelimiter(",");
				lineScanner.useLocale(Locale.US);
				
				
				String nome = lineScanner.next();
				String cpf = lineScanner.next();
				String rg = lineScanner.next();
				String sexo = lineScanner.next();
				String email = lineScanner.next();
				String telefone = lineScanner.next();
				String dataNascimento = lineScanner.next();
				String empresa = lineScanner.next();
				Double renda = lineScanner.nextDouble();
				String rua = lineScanner.next();
				String cidade = lineScanner.next();
				String estado = lineScanner.next();
				String pais = lineScanner.next();
				Integer rating = lineScanner.nextInt();
				
				
				cd.setNome(nome);
				cd.setCpf(cpf);
				cd.setRg(rg);
				cd.setSexo(sexo);
				cd.setEmail(email);
				cd.setTelefone(telefone);
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate data = LocalDate.parse(dataNascimento, formatter);
				cd.setDataNascimento(data);
				cd.setEmpresa(empresa);
				cd.setRenda(renda);
				cd.setRua(rua);
				cd.setCidade(cidade);
				cd.setEstado(estado);
				cd.setPais(pais);
				cd.setValorDevedor(0.0);
				cd.setSituacao("Solicitar");
				cd.setRating(rating);
//				LocalDate hoje = LocalDate.now();
//				cd.setDataEmprestimo(hoje);
				
				listaDevedor.add(cd);
				
			}
			listaDevedor.forEach(devedores -> {
				
//				devedorRepository.save(devedores);
				
			});
			
			
			System.out.println(listaDevedor);
			
			
			is = new FileInputStream("credores.csv");
			s = new Scanner(is);
			List<ClienteCredor> listaCredor = new ArrayList<>();
			
			
			while(s.hasNext()) {
				ClienteCredor cc = new ClienteCredor();
				String line = s.nextLine();
				
				Scanner lineScanner = new Scanner(line);
				
				lineScanner.useDelimiter(",");
				lineScanner.useLocale(Locale.US);
				
				
				String nome = lineScanner.next();
				String cpf = lineScanner.next();
				String rg = lineScanner.next();
				String sexo = lineScanner.next();
				String email = lineScanner.next();
				String telefone = lineScanner.next();
				String dataNascimento = lineScanner.next();
				String empresa = lineScanner.next();
				String rua = lineScanner.next();
				String cidade = lineScanner.next();
				String estado = lineScanner.next();
				String pais = lineScanner.next();
				
				cc.setNome(nome);
				cc.setCpf(cpf);
				cc.setRg(rg);
				cc.setSexo(sexo);
				cc.setEmail(email);
				cc.setTelefone(telefone);
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate data = LocalDate.parse(dataNascimento, formatter);
				cc.setDataNascimento(data);
				cc.setEmpresa(empresa);
				cc.setRua(rua);
				cc.setCidade(cidade);
				cc.setEstado(estado);
				cc.setPais(pais);
				cc.setDisponivel(0.0);
				cc.setSituacao("Zerado");
//				LocalDate hoje = LocalDate.now();
//				cc.setDataEmprestimo(hoje);
				
				listaCredor.add(cc);
				
			}
			listaCredor.forEach(credores -> {
				
//				credorRepository.save(credores);
				
			});	
			System.out.println(listaCredor);
			Banco bc = new Banco();
			bc.setSaldo(0.0);
			
//			bancoRepository.save(bc);
		};
	}
}
