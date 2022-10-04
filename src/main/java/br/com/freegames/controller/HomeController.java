package br.com.freegames.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.freegames.model.ApiModel;
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
  public String listaCompleta(
    @RequestParam(required = false, defaultValue = "0") Integer categoria, 
    @RequestParam(required = false, defaultValue = "0") Integer plataforma, 
    Model model ) {

    if(categoria != 0 && plataforma != 0) {
      List<ApiModel> result = serviceApi.retornaJogosCom2Filtros(categoria, plataforma);
      model.addAttribute("jogos", result);
      model.addAttribute("jogoSelecionadoCat", serviceApi.retornaFiltrosCategoria().get(categoria));
      model.addAttribute("jogoSelecionadoPla", serviceApi.retornaFiltrosPlataforma().get(plataforma));
    } else if(categoria > 0  && plataforma == 0) {
      List<ApiModel> result = serviceApi.retornaJogosFiltradosCat(categoria);
      model.addAttribute("jogos", result);
      model.addAttribute("jogoSelecionadoCat", serviceApi.retornaFiltrosCategoria().get(categoria));
      model.addAttribute("jogoSelecionadoPla", serviceApi.retornaFiltrosPlataforma().get(0));
    } else if(categoria == 0 && plataforma > 0) {
      List<ApiModel> result = serviceApi.retornaJogosFiltradosPla(plataforma);
      model.addAttribute("jogos", result);
      model.addAttribute("jogoSelecionadoCat", serviceApi.retornaFiltrosCategoria().get(0));
      model.addAttribute("jogoSelecionadoPla", serviceApi.retornaFiltrosPlataforma().get(plataforma));
    } else {
      List<ApiModel> result = serviceApi.retornaJogos();
      model.addAttribute("jogos", result);
      model.addAttribute("jogoSelecionadoCat", serviceApi.retornaFiltrosCategoria().get(0));
      model.addAttribute("jogoSelecionadoPla", serviceApi.retornaFiltrosPlataforma().get(0));
    }
    
    model.addAttribute("opcoesCat", serviceApi.retornaFiltrosCategoria());
    model.addAttribute("opcoesPla", serviceApi.retornaFiltrosPlataforma());

    return "listaJogos";
  }

  @PostMapping("/home/lista-completa")
  public String teste(Integer categoria, Integer plataforma) {

    return "redirect:/home/lista-completa/?categoria=" + categoria + "&plataforma=" + plataforma;
  }
} 
