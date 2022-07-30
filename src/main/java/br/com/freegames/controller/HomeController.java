package br.com.freegames.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class HomeController {

  @GetMapping("/home")
  public String home(Model model) throws URISyntaxException {
    
    URI uri = new URI("https://www.freetogame.com/api/games");

    RestTemplate restTemplate = new RestTemplate();
    List<?> result = restTemplate.getForObject(uri, List.class);

    System.out.println(result);

    model.addAttribute("jogos", result);

    return "home";
  }
}
