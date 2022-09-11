package br.com.freegames.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.freegames.model.ApiModel;
import br.com.freegames.service.ServiceApi;

@Controller
@RequestMapping("/home")
public class HomeController {

  @Autowired
  private ServiceApi serviceApi;

  @GetMapping
  public String home(Model model) {
    List<ApiModel> result = serviceApi.retornaJogosReduzido();
    model.addAttribute("jogos", result);

    return "home";
  }

  @GetMapping("/lista-completa")
  public String listaCompleta(Model model) {
    List<ApiModel> result = serviceApi.restornaTodosOsJogos();
    model.addAttribute("jogos", result);

    return "lista-completa";
  }
}
