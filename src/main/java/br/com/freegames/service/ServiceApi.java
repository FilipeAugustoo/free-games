package br.com.freegames.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.freegames.model.ApiModel;

@Service
public class ServiceApi {

  public List<ApiModel> retornaJogos() {

    try {
      URI uri = new URI("https://www.freetogame.com/api/games?sort-by=release-date");

      RestTemplate restTemplate = new RestTemplate();

      ResponseEntity<List<ApiModel>> result = restTemplate.exchange(uri, HttpMethod.GET, null,
          new ParameterizedTypeReference<>() {
          });

      return result.getBody();
    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    }

  }

  public List<ApiModel> retornaJogosReduzido() {
    var jogosAPI = retornaJogos();
    List<ApiModel> jogos = new ArrayList<>();
    for(int i = 0; i < 10; i++) {
      jogos.add(jogosAPI.get(i));
    }

    return jogos;
  }

  public List<String> retornaOpcoes() {
    List<String> opcoes = new ArrayList<String>();
    opcoes.add("mmorpg");
    opcoes.add("shooter");
    opcoes.add("pvp");
    opcoes.add("mmofps");

    return opcoes;
  }
}
