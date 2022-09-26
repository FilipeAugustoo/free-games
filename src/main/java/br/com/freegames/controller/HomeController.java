package br.com.freegames.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.freegames.model.ApiModel;
import br.com.freegames.model.Filtro;
import br.com.freegames.service.ServiceApi;

@Controller
public class HomeController {

  @Autowired
  private ServiceApi serviceApi;

  @GetMapping("/home")
  public String home(Model model) {
    List<ApiModel> result = serviceApi.retornaJogosReduzido();
    model.addAttribute("jogos", result);
    
    return "homePage";
  }

  @GetMapping("/home/lista-completa")
  public String listaCompleta(@RequestParam(value = "filtro", required = false) String filtro, Model model ) {
    
    if(filtro == null) {
      List<ApiModel> result = serviceApi.retornaJogos();
      model.addAttribute("jogos", result);
    } else if(filtro != null) {
      List<ApiModel> result = serviceApi.retornaJogosFiltrados(filtro);
      model.addAttribute("jogos", result);
    }
    model.addAttribute("opcoes", Filtro.values());

    return "listaJogos";
  }

  @PostMapping("/home/lista-completa")
  public String teste(Filtro filtro, Model model) {
    // List<ApiModel> result = serviceApi.retornaJogosFiltrados(filtro.toString().toLowerCase());
    // model.addAttribute("jogosFiltrados", result);

    return "redirect:/home/lista-completa/?filtro=" + filtro.toString().toLowerCase();
  }
}
