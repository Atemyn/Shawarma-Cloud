package shawarmas.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.extern.slf4j.Slf4j;
import shawarmas.model.Ingredient;
import shawarmas.model.Ingredient.Type;
import shawarmas.model.Shawarma;
import shawarmas.model.ShawarmaOrder;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("shawarmaOrder")
public class DesignShawarmaController {
	@ModelAttribute
	public void addIngredientsToModel(Model model) {
		List<Ingredient> ingredients = Arrays.asList(
			new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
			new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
			new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
			new Ingredient("CARN", "Carnitas", Type.PROTEIN),
			new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
			new Ingredient("LETC", "Lettuce", Type.VEGGIES),
			new Ingredient("CHED", "Cheddar", Type.CHEESE),
			new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
			new Ingredient("SLSA", "Salsa", Type.SAUCE),
			new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
		);
		
		Type[] types = Ingredient.Type.values();
		for (Type type : types) {
			model.addAttribute(type.toString().toLowerCase(),
			filterByType(ingredients, type));
		}
	}
	
	private Iterable<Ingredient> filterByType(
			List<Ingredient> ingredients, Type type) {
		return ingredients
			.stream()
			.filter(x -> x.getType().equals(type))
			.collect(Collectors.toList());
	}
	
	@ModelAttribute(name = "shawarmaOrder")
	public ShawarmaOrder order() {
	return new ShawarmaOrder();
	}
	
	@ModelAttribute(name = "shawarma")
	public Shawarma shawarma() {
	return new Shawarma();
	}
	
	@GetMapping
	public String showDesignForm() {
	return "design";
	}
	
	@PostMapping
	public String processShawarma(@Valid Shawarma shawarma, Errors errors,
			@ModelAttribute ShawarmaOrder shawarmaOrder) {
		
		if (errors.hasErrors()) {
			return "design";
		}
		
		shawarmaOrder.addShawarma(shawarma);
		log.info("Processing shawarma: {}", shawarma);
		
		return "redirect:/orders/current";
	}

}
