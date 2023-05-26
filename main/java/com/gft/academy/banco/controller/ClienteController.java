package com.gft.academy.banco.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gft.academy.banco.model.Banco;
import com.gft.academy.banco.model.ClienteCredor;
import com.gft.academy.banco.model.ClienteDevedor;
import com.gft.academy.banco.repository.BancoRepository;
import com.gft.academy.banco.repository.ClienteCredorRepository;
import com.gft.academy.banco.repository.ClienteDevedorRepository;

@Controller
public class ClienteController {

	@Autowired
	ClienteDevedorRepository devedorRepository;
	
	@Autowired
	ClienteCredorRepository credorRepository;
	
	@Autowired
	BancoRepository bancoRepository;
	
	@GetMapping("/")
	
	public String listarClientes(@RequestParam(value="type", required=false) String type, Model model) { 
			
		if(type == null) {
			type = "solicitante";
			model.addAttribute("type", "solicitante");
		}	
		
		
			model.addAttribute("listaDevedores", devedorRepository.findAll());					
			model.addAttribute("listaCredores", credorRepository.findAll());			
			model.addAttribute("banco", bancoRepository.findAll());
		
		System.out.println(":::::::: " + type);
		return "home";
	}
	
	
	@GetMapping("/inicial")
	public String inicial() {
		return "redirect:/";
	}
	
	@GetMapping("/solicitando/{id}")
	public String solicitandoDinheiro(Model model, @PathVariable("id") Long id) {
		loadSolicitante(model, id);
		return "solicitante2";
	}
	
	@GetMapping("/creditando/{id}")
	public String creditandoDinheiro(Model model, @PathVariable("id") Long id) {
		loadCredor(model, id);
		return "credor";
	}
	
	@GetMapping("/informações-solicitante/{id}")
	public String todasInformacoes (Model model, @PathVariable("id") Long id, @ModelAttribute("devedor") ClienteDevedor devedor) {
		loadSolicitante(model, id);
		ClienteDevedor loadDevedor = loadSolicitante(model, devedor.getId());
		calcularRegra(loadDevedor, model);
		System.out.println(devedor.getCalculos().getTaxa());
		System.out.println(loadDevedor.getCalculos().getTaxa());
		System.out.println(loadDevedor.getValorDevedor());
		return "confimacaoSolicitante";
	}
	
	@GetMapping("/dia-seguinte")
	public String diaSeguinte(Model model, Banco banco) {
		return "redirect:/";
	}
	
	
	
	@PostMapping("/salvar-solicitado")
	public ModelAndView salvarSolicitado(Model model) {
		return new ModelAndView("redirect:/");
		
	}
	
	@PostMapping("/salvar-depositado")
	public String salvarDepositado(Model model, @ModelAttribute("credor") ClienteCredor credor) {
		System.out.println("aaa");
		ClienteCredor credores = loadCredor(model, credor.getId());
		
		if(credor.getDisponivel() > 0) {
			credores.setDisponivel(credor.getDisponivel());			
			credores.setSituacao("Com saldo");
			
			Banco banco = loadBanco(model);
			
			credores.getCalculos().setBanco(banco);
			credores.getCalculos().somarFundo();
			
			
			banco.setSaldo(credores.getCalculos().getSaldo());
			System.out.println(banco.getSaldo());
			bancoRepository.save(banco);
		}
		
		return "redirect:/";
	}
	
	@PostMapping("/realizar-acao")
	public String realizarAcao(Model model) {
		System.out.println("entrou aqui");
		List<ClienteDevedor> listaSolicitantes =  devedorRepository.verificaDia(LocalDate.now());
		List<ClienteCredor> listaCredores = credorRepository.findAll();
		Banco banco = loadBanco(model);
		
		
		listaSolicitantes.forEach(solicitante ->{
			if(banco.getSaldo() >= solicitante.getValorDevedor()) {
				if(!solicitante.getSituacao().equals("Devedor")) {
			solicitante.getCalculos().setBanco(banco);
			solicitante.getCalculos().tiraFundoAprovado();
			banco.setSaldo(solicitante.getCalculos().getRetiraSaldo());
				}
			}
			listaCredores.forEach(credor ->{
				if (credor.getDisponivel() > solicitante.getValorDevedor()) {
					solicitante.setSituacao("Devedor");
					bancoRepository.save(banco);
					devedorRepository.save(solicitante);
					credorRepository.save(credor);
					
				}
			});
		});
		
		return "redirect:/";
	}
	
	@PostMapping("/calcular-regra")
	public String calcularRegra(@ModelAttribute("devedor") ClienteDevedor devedor, Model model) {
		ClienteDevedor loadDevedor = loadSolicitante(model, devedor.getId());
		loadDevedor.setCalculos(devedor.getCalculos());
		loadDevedor.setValorDevedor(devedor.getValorDevedor());
		loadDevedor.getCalculos().setParcelas(devedor.getCalculos().getParcelas());
		loadDevedor.setTotalParcelas(loadDevedor.getCalculos().getParcelas());
		loadDevedor.getCalculos().calcular(loadDevedor);
		loadDevedor.setSituacao("Solicitado");
		LocalDate hoje = LocalDate.now();
		loadDevedor.setDataEmprestimo(hoje);
		loadDevedor.setValorParcelas(devedor.getCalculos().getValorPorParcela());
		loadDevedor.getCalculos().valorCadaParcela();
		devedorRepository.save(loadDevedor);
		return "solicitante2";
	}
	
	private ClienteDevedor loadSolicitante(Model model, Long id) {
		ClienteDevedor cliente = devedorRepository.findById(id).get();
		model.addAttribute("solicitante", cliente);
		return cliente;
	}
	
	private ClienteCredor loadCredor(Model model, Long id) {
		ClienteCredor cliente = credorRepository.findById(id).get();
		model.addAttribute("credores", cliente);
		return cliente;
	}
	
	private Banco loadBanco(Model model) {
		Long id = 15l;
		Banco banco = bancoRepository.findById(id).get();
		model.addAttribute("banco", banco);
		return banco;
	}
	
}
