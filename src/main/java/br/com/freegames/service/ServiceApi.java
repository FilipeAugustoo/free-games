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

  private ResponseEntity<List<ApiModel>> fazRequisicao() {

    try {
      URI uri = new URI("https://www.freetogame.com/api/games");

      RestTemplate restTemplate = new RestTemplate();

      ResponseEntity<List<ApiModel>> result = restTemplate.exchange(uri, HttpMethod.GET, null,
          new ParameterizedTypeReference<>() {
          });

      return result;
    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    }

  }

  public List<ApiModel> retornaLista() {

    var jogos = fazRequisicao();

    List<ApiModel> listaJogos = new ArrayList<>();
    jogos.getBody().forEach(jogo -> listaJogos.add(jogo));

    return listaJogos;
  }
}
