package org.acme.rest.client.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.*;
import org.eclipse.microprofile.graphql.Mutation;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
@Table(name = "festival")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Festival   extends PanacheEntityBase {

  @Id
  @GeneratedValue
  private Long id;

  @NonNull
  private String name;

  @NonNull
  private Date startDate;

  @NonNull
  private Date endDate;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(
          name = "festivals_bands",
          joinColumns = { @JoinColumn(name = "band_id") },
          inverseJoinColumns = { @JoinColumn(name = "festival_id") })
  private List<Band> bands;

  public static Festival findByName(String name){
    return Festival.find("name", name).firstResult();
  }

  @Mutation
  @Transactional
  public static Festival create(String name, Date startDate, Date endDate) {
    Festival festival = Festival.builder().name(name).startDate(startDate).endDate(endDate).build();
    Festival.persist(festival);
    return festival;
  }

  public static String addBand(String festivalName, String bandName) {
    Festival festival = Festival.findByName(festivalName);
    Band band = Band.findByName(bandName);
    festival.getBands().add(band);
    Festival.persist(festival);
    return band.getBandName() + " booked for Festival: " + festival.getName();
  }
}
