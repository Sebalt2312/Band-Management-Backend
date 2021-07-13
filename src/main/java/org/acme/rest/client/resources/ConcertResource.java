package org.acme.rest.client.resources;

import org.acme.rest.client.entity.Concert;
import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;

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
}
