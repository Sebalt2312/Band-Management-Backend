package org.acme.rest.client.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.*;
import org.acme.rest.client.enums.Genre;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Band extends PanacheEntityBase {

  @Id
  @GeneratedValue
  private int id;

  @NonNull
  private String bandName;

  private Genre genre;

  @ManyToMany
  private List<Member> member;

  @ManyToMany
  private List<Concert> concerts;

  @ManyToMany
  private List<Festival> festivals;


}
