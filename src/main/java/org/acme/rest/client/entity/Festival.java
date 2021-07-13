package org.acme.rest.client.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.*;
import org.eclipse.microprofile.graphql.Mutation;

import javax.persistence.*;
import javax.transaction.Transactional;
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
  public static Festival create(String name) {
    Festival festival = Festival.builder().name(name).build();
    Festival.persist(festival);
    return festival;
  }
}
