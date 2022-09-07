package br.com.freegames.service;

import java.net.URI;
import java.net.URISyntaxException;
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
      URI uri = new URI("https://www.freetogame.com/api/games");

      RestTemplate restTemplate = new RestTemplate();

      ResponseEntity<List<ApiModel>> result = restTemplate.exchange(uri, HttpMethod.GET, null,
          new ParameterizedTypeReference<>() {
          });

      return result.getBody();
    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    }

  }
}
