package br.com.fiap.iDoei.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fiap.iDoei.model.Donation;
import br.com.fiap.iDoei.model.Food;
import br.com.fiap.iDoei.model.Ong;
import br.com.fiap.iDoei.model.Person;
import br.com.fiap.iDoei.repository.DonationRepository;
import br.com.fiap.iDoei.repository.FoodRepository;
import br.com.fiap.iDoei.repository.OngRepository;

@Controller
public class DonateController {
	@Autowired
	private DonationRepository repository;
	@Autowired
	private FoodRepository foodRepository;
	@Autowired
	private OngRepository ongRepository;
	
	@GetMapping("/donate")
	public ModelAndView donate(Donation donation, Authentication auth) {
		ModelAndView modelAndView = new ModelAndView("donate");
		Person person = (Person) auth.getPrincipal();
		Optional<Food> optional = foodRepository.findByIsSelected(true);
		if (optional.isEmpty()) {
		}
		Food food = optional.get();
		donation.setPerson(person);
		donation.setFood(food);
		Optional<Ong> ongOptional = ongRepository.findById((long) 1);
		if (ongOptional.isEmpty()) {
		}
		Ong ong = ongOptional.get();
		donation.setOng(ong);
		repository.save(donation);
//		List<Food> l_foods =  repository.findAll();
//		System.out.println("Comidas Legais "+ l_foods.toString());
		modelAndView.addObject("donation",donation);
		return modelAndView;
	}
	@GetMapping("/finish/{id}")
	public String finish(@PathVariable Long id,RedirectAttributes redirect) {
		Optional<Donation> donationOptional = repository.findById(id);
		if (donationOptional.isEmpty()) {
		}
		
		Donation donation = donationOptional.get();
		Optional<Food> foodOptional = foodRepository.findById(donation.getFood().getId());
		if (foodOptional.isEmpty()) {
		}
		
		Food food = foodOptional.get();
		food.setIsSelected(false);
		foodRepository.save(food);
		return "redirect:/home";
	}
}
