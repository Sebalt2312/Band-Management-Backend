package org.acme.rest.client.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.*;
import org.acme.rest.client.enums.Instrument;
import org.eclipse.microprofile.graphql.Mutation;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

@Entity
@Data
@Builder
@Table(name = "member")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Member extends PanacheEntityBase {

  @Id
  @GeneratedValue
  private Long id;

  private String name;

  private Instrument instrument;

  @ManyToOne
  @JoinColumn(name="band_id")
  private Band band;

  @Transactional
  public static String create(String name, String instrument) {
    Member person = Member.builder()
            .name(name)
            .instrument(Instrument.getInstrument(instrument)).build();
    Member.persist(person);
    return person.name;
  }

  @Transactional
  public static String createWithBand(String name, String instrument, int bandId) {
    Member person = Member.builder()
            .name(name)
            .instrument(Instrument.getInstrument(instrument))
            .band(Band.findById((long) bandId)).build();
    Member.persist(person);
    return person.name;
  }

  @Mutation
  @Transactional
  public static String addBand(String name, String bandName) {
    Member member = Member.findByName(name);
    Band band = Band.findByName(bandName);
    member.setBand(band);
    Member.persist(member);
    return member.band.getBandName();
  }

  public static Member findByName(String name) {
    return find("name", name).firstResult();
  }

  public static List<Member> findByBand(String bandName) {
    Band band = Band.findByName(bandName);
    return find("band", band).list();
  }
}
