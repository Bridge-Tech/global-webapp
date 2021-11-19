package br.com.fiap.iDoei.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fiap.iDoei.exception.FoodNotFoundException;
import br.com.fiap.iDoei.model.Food;
import br.com.fiap.iDoei.model.Person;
import br.com.fiap.iDoei.repository.FoodRepository;

@Controller
public class FoodController {

	@Autowired
	private FoodRepository repository;
	
	
	//repository.findByisSelected(true);
	/*
	 * Add o Id da pessoa na tabela comida, sem usar o objeto
	 * Person, usar o id como somente numero de Indetificação
	 */
	
	@GetMapping("/food/register")
	public String index(Food food) {
		return "registerFood";
	}
	
	@PostMapping("/food/register")
	public String save(@Valid Food food, BindingResult result,  RedirectAttributes redirect, Authentication auth) {
		if(result.hasErrors()) {
			System.out.println("Não salvando Nova Comida ...");
			return "registerFood";
		}
		food.setIsSelected(false);
		
		Person person = (Person) auth.getPrincipal();
		
		
		food.setPersonId(person.getId());
		System.out.println("Tudo certo salvando Comida");
		repository.save(food);
		
		return "redirect:/food";
	}
	
	@GetMapping("/food")
	public ModelAndView view(Food food) {
		
		ModelAndView modelAndView = new ModelAndView("food");
		List<Food> l_foods =  repository.findAll();
		modelAndView.addObject("foods",l_foods);
		return modelAndView;
	}
	
	@GetMapping("/food/hold/{id}")
	private String hold(@PathVariable Long id, Authentication auth) {
		Optional<Food> optional = repository.findById(id);
		
		if (optional.isEmpty()) {
			throw new FoodNotFoundException("Comida não encontrado!");
		}
		
		Food food = optional.get();
		if(!food.getIsSelected()) {
			food.setIsSelected(true);
		}
		
		repository.save(food);
		return "redirect:/donate";
	}
	
//	@GetMapping("/food/drop/{id}")
//	private String drop(@PathVariable Long id, Authentication auth) {
//		Optional<Food> optional = repository.findById(id);
//		
//		if (optional.isEmpty()) {
//			throw new FoodNotFoundException("Comida não encontrado!");
//		}
//		Food food = optional.get();
//		if(food.getIsSelected()) {
//			food.setIsSelected(false);
//		}
//		repository.save(food);
//		return "redirect:/food";
//	}
}
