package br.com.freegames.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiModel {

  private Integer id;
  private String title;
  private String thumbnail;
  private String short_description;
  private String game_url;
  private String genre;
  private String platform;
  private String publisher;
  private String developer;
  private String release_date;
  private String freetogame_profile_url;

  
  @Override
  public String toString() {
    return "ApiModel [developer=" + developer + ", freetogame_profile_url=" + freetogame_profile_url + ", game_url="
        + game_url + ", genre=" + genre + ", id=" + id + ", platform=" + platform + ", publisher=" + publisher
        + ", release_date=" + release_date + ", short_description=" + short_description + ", thumbnail=" + thumbnail
        + ", title=" + title + "]";
  }

}
