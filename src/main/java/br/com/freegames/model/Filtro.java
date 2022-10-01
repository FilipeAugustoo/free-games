package br.com.freegames.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Filtro {

  public Integer id = 0;
  private String filtro;

  public Filtro(String filtro, Integer id) {
    this.id = id;
    this.filtro = filtro;
  }
}
