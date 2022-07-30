package br.com.freegames.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiModel {

  private Long id;
  private String title;
  private String thumbnail;
  private String game_url;
  private String genre;
  private String platform;
  private String publisher;
  private String release_date;
  private String freetogame_profile_url;
 
}
