package org.acme.rest.client.enums;

import java.util.Locale;

public enum Genre {


  ROCK("Rock"),
  PUNK("Punk"),
  METAL("Metal"),
  HARDCORE("Hardcore"),
  ACOUSTIC("Acoustic"),
  POP("Pop"),
  TECHNO("Techno");

  String name;

  Genre(String name){
    this.name=name;
  };

  public static Genre getGenre(String name) {
    name = name.toUpperCase(Locale.ROOT);
    return Genre.valueOf(name);
  }

}
