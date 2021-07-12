package org.acme.rest.client.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Festival  extends PanacheEntityBase {

  @Id
  @GeneratedValue
  private int id;

  @NonNull
  private String name;

  @NonNull
  @ManyToMany
  private List<Band> bands;

}
