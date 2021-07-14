package org.acme.rest.client.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
@Table(name = "concert")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Concert extends PanacheEntityBase {

  @Id
  @GeneratedValue
  private Long id;

  private String name;

  @NonNull
  private Date date;

  @NonNull
  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(
    name = "concerts_bands",
    joinColumns = {@JoinColumn(name = "band_id")},
    inverseJoinColumns = {@JoinColumn(name = "concert_id")}
  )
  private List<Band> bands;

  public static Concert findByName(String name) {
    return Concert.find("name", name).firstResult();
  }

  public static String create(String name, Date date, List<String> bands) {
    Concert concert = Concert.builder().name(name).date(date).build();
    for (String band : bands) {
      Band nextBand = Band.findByName(band);
      concert.getBands().add(nextBand);
    }
    Concert.persist(concert);
    return "Concert " + concert.getName() + " booked.";
  }

  public static String addBand(String concertName, String bandName) {
    Concert concert = Concert.findByName(concertName);
    Band band = Band.findByName(bandName);
    concert.getBands().add(band);
    Concert.persist(concert);
    return band.getBandName() + " booked for Concert on: " + concert.getDate();
  }

}
