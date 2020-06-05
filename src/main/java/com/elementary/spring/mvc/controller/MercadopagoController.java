package com.elementary.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("mercadopago")
public class MercadopagoController {

    @GetMapping("boton")
    public String boton(ModelMap map){
        map.addAttribute("welcomeMessage", "welcome");
        return "mercadopago/boton";
    }

    @GetMapping("/showViewPage")
    public String passParametersWithModel(Model model) {
        Map<String, String> map = new HashMap<>();
        map.put("spring", "mvc");
        model.addAttribute("message", "Baeldung");
        model.mergeAttributes(map);
        return "mercadopago/viewPage";
    }

    @GetMapping("/printViewPage")
    public String passParametersWithModelMap(ModelMap map) {
        map.addAttribute("welcomeMessage", "welcome");
        map.addAttribute("message", "Baeldung");
        return "mercadopago/viewPage";
    }

    @GetMapping("/goToViewPage")
    public ModelAndView passParametersWithModelAndView() {
        ModelAndView modelAndView = new ModelAndView("mercadopago/viewPage");
        modelAndView.addObject("message", "Baeldung");
        return modelAndView;
    }
}
