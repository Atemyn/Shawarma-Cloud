package shawarmas.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import lombok.extern.slf4j.Slf4j;
import shawarmas.model.ShawarmaOrder;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("shawarmaOrder")
public class OrderController {
	
	@GetMapping("/current")
	public String orderForm() {
		return "orderForm";
	}

	@PostMapping
	public String processOrder(ShawarmaOrder shawarmaOrder,
			SessionStatus sessionStatus) {
		log.info("Order submitted: {}", shawarmaOrder);
		sessionStatus.setComplete();
		
		return "redirect:/";
	}
}
