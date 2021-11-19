package br.com.fiap.iDoei.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fiap.iDoei.model.Ong;
import br.com.fiap.iDoei.repository.OngRepository;
@Controller
public class OngController {
	
	
	@Autowired
	private OngRepository repository;
	
	@RequestMapping("/ong/register")
	public String create(Ong ong) {
		return"registerOng";
	}
	
	@PostMapping("/ong/register")
	public String save(@Valid Ong ong, BindingResult result,  RedirectAttributes redirect) {
		if(result.hasErrors()) {
			System.out.println("Não salvando Nova Ong ...");
			return "registerPerson";
		}
		
		Optional<Ong> optional = repository.findByCnpj(ong.getCnpj());
		if (!optional.isEmpty()) {
			System.out.println("Não salvando Nova Ong ... CNPG Já Usado");
			return "registerPerson";
		}
		if (!ong.getPassword().equals(ong.getRepeatPassword())) {
			System.out.println("Não salvando Nova Ong ... Senha não são Iguais");
			return "registerOng";
		}
		
		ong.setFullAddress("R." + ong.getAddress() + 
				"- " + ong.getDistrict() + 
				"- Nº " + ong.getNumber().toString() +
				"- "+ ong.getComplement()
				);
		System.out.println("Tudo certo salvando Ong");
		repository.save(ong);
		
		return "redirect:login";
	}
	
}
