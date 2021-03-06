package br.com.cfsystems.erp.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.cfsystems.erp.model.PaymentMethod;
import br.com.cfsystems.erp.service.PaymentMethodService;

@Controller
@Transactional
@RequestMapping("/paymentMethod")
public class PaymentMethodController {
	
	@Autowired
	private PaymentMethodService service;
	
	@RequestMapping(value={"", "/"}, method={RequestMethod.GET})
	public String home(Model model){
		model.addAttribute("paymentsMethod", service.findAll());
		return "sys/paymentMethod/index";
	}
	
	@RequestMapping(value={"/form"}, method={RequestMethod.GET})
	public String form(PaymentMethod paymentMethod) {
		return "sys/paymentMethod/form";
	}

	@RequestMapping(value={"/save"}, method = RequestMethod.POST)
	public String save(PaymentMethod paymentMethod, RedirectAttributes redirectAttributes) {
		service.save(paymentMethod);
		redirectAttributes.addAttribute("sucesso", "Forma de Pagamento cadastrada com sucesso.");
		return "redirect:/paymentMethod";
	}

}
