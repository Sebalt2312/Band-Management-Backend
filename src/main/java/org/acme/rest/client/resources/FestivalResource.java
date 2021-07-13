package org.acme.rest.client.resources;

import org.acme.rest.client.entity.Band;
import org.acme.rest.client.entity.Festival;
import org.eclipse.microprofile.graphql.*;

import javax.transaction.Transactional;
import java.util.List;

@GraphQLApi
public class FestivalResource {

    @Query("festivals")
    @Description("Get all Festivals.")
    public List<Festival> getAll() {
        return Festival.findAll().list();
    }

    @Query("findByFestivalName")
    @Description("Get a Festival by Name.")
    public Festival findByFestivalName(@Name("name") String name) {
        return Festival.findByName(name);
    }

    @Mutation
    @Transactional
    public String addBandToFestival(String festivalName, String bandName) {
        Festival festival = Festival.findByName(festivalName);
        Band band = Band.findByName(bandName);
        festival.getBands().add(band);
        Festival.persist(festival);
        return festival.getName();
    }
}

