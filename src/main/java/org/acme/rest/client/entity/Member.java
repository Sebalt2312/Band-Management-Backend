package org.acme.rest.client.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.acme.rest.client.enums.Instrument;

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
public class Member  extends PanacheEntityBase {

  @Id
  @GeneratedValue
  private int id;

  private String name;

  private Instrument instrument;

  @ManyToMany
  private List<Band> bands;

}
