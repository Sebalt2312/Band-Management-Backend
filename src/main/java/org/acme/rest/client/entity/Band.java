package org.acme.rest.client.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.*;
import org.acme.rest.client.enums.Genre;
import org.eclipse.microprofile.graphql.Mutation;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

@Entity
@Data
@Builder
@Table(name = "band")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Band extends PanacheEntityBase {

  @Id
  @GeneratedValue
  private Long id;

  private String bandName;

  private Genre genre;

  @OneToMany(mappedBy = "band")
  private List<Member> member;


  @ManyToMany(mappedBy = "bands")
  private List<Concert> concerts;

  @ManyToMany(mappedBy = "bands")
  private List<Festival> festivals;


  @Mutation
  @Transactional
  public static Band create(String name, String genre) {
    Band band = Band.builder()
            .bandName(name)
            .genre(Genre.getGenre(genre)).build();
    Band.persist(band);
    return band;
  }

  @Mutation
  @Transactional
  public static String remove(int id) {
    Band band = Band.findById(id);
    Band.remove(id);
    return band.getBandName();
  }

  public static Band findByName(String name){
    return find("bandName", name).firstResult();
  }

}
