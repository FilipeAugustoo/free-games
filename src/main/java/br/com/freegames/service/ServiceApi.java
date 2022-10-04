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
import br.com.freegames.model.Filtro;

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

  public List<ApiModel> retornaJogosFiltradosCat(Integer id) {
    Filtro filtro = retornaFiltrosCategoria().get(id);

    try {
      URI uri = new URI("https://www.freetogame.com/api/games?category=" + filtro.getFiltro().toLowerCase());

      RestTemplate restTemplate = new RestTemplate();

      ResponseEntity<List<ApiModel>> result = restTemplate.exchange(uri, HttpMethod.GET, null,
          new ParameterizedTypeReference<>() {
          });

      return result.getBody();
    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }

  public List<Filtro> retornaFiltrosCategoria() {
    List<Filtro> listaFiltros = new ArrayList<Filtro>();

    listaFiltros.add(new Filtro("Selecionar Filtro", 0));
    listaFiltros.add(new Filtro("MMORPG", 1));
    listaFiltros.add(new Filtro("MMOFPS", 2));
    listaFiltros.add(new Filtro("PVP", 3));
    listaFiltros.add(new Filtro("SHOOTER", 4));

    return listaFiltros;
  }

  public List<Filtro> retornaFiltrosPlataforma() {
    List<Filtro> listaFiltros = new ArrayList<Filtro>();

    listaFiltros.add(new Filtro("Selecionar Filtro", 0));
    listaFiltros.add(new Filtro("PC", 1));
    listaFiltros.add(new Filtro("BROWSER", 2));

    return listaFiltros;
  }

  public List<ApiModel> retornaJogosCom2Filtros(Integer categoria, Integer plataforma) {
    Filtro filtroCategoria = retornaFiltrosCategoria().get(categoria);
    Filtro filtroPlataforma = retornaFiltrosPlataforma().get(plataforma);

    try {
      URI uri = new URI("https://www.freetogame.com/api/games?platform=" + filtroPlataforma.getFiltro().toLowerCase() + "&category=" + filtroCategoria.getFiltro().toLowerCase());

      RestTemplate restTemplate = new RestTemplate();

      ResponseEntity<List<ApiModel>> result = restTemplate.exchange(uri, HttpMethod.GET, null,
          new ParameterizedTypeReference<>() {
          });

      return result.getBody();
    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }

  public List<ApiModel> retornaJogosFiltradosPla(Integer id) {
    Filtro filtro = retornaFiltrosPlataforma().get(id);

    try {
      URI uri = new URI("https://www.freetogame.com/api/games?platform=" + filtro.getFiltro().toLowerCase());

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
