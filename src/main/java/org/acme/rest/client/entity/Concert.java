package org.acme.rest.client.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Date;
import java.util.List;

@Entity
@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Concert  extends PanacheEntityBase {

  @Id
  @GeneratedValue
  private int id;

  private String name;

  @NonNull
  private Date date;

  @NonNull
  @ManyToMany
  private List<Band> bands;


}
