package br.com.freegames.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.freegames.model.ApiModel;
import br.com.freegames.service.ServiceApi;

@Controller
public class HomeController {

  @Autowired
  private ServiceApi serviceApi;

  @GetMapping("/home")
  public String home(Model model) {
    List<ApiModel> result = serviceApi.retornaLista();
   
    model.addAttribute("jogos", result);

    return "home";
  }
}
