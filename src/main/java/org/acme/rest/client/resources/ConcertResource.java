package org.acme.rest.client.resources;

import org.acme.rest.client.entity.Concert;
import org.eclipse.microprofile.graphql.*;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@GraphQLApi
public class ConcertResource {

  @Query("concerts")
  @Description("Get all Concerts.")
  public List<Concert> getAll() {
    return Concert.findAll().list();
  }

  @Query("findByConcertName")
  @Description("Get a Band with its members by Name.")
  public Concert findByConcertName(@Name("name") String name) {
    return Concert.findByName(name);
  }

  @Mutation
  @Transactional
  public String createConcert(@Name("concertName") String concertName,
                              @Name("date") Date date,
                              @Name("bands") List<String> bands) {
    return Concert.create(concertName, date, bands);
  }

  @Mutation
  @Transactional
  public String addBandToConcert(@Name("concertName") String concertName,
                                 @Name("bandName") String bandName) {
    return Concert.addBand(concertName, bandName);
  }
}
