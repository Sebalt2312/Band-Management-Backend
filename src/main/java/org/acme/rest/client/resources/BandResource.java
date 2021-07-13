package org.acme.rest.client.resources;


import io.smallrye.graphql.api.Subscription;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.operators.multi.processors.BroadcastProcessor;
import org.acme.rest.client.entity.Band;
import org.eclipse.microprofile.graphql.*;

import javax.transaction.Transactional;
import java.util.List;

@GraphQLApi
public class BandResource {

    BroadcastProcessor<Band> processor = BroadcastProcessor.create();

    @Query("bands")
    @Description("Get all Bands.")
    public List<Band> getAll() {
        return Band.findAll().list();
    }

    @Query("findByBandName")
    @Description("Get a Band with its members by Name.")
    public Band findByBandName(@Name("bandName") String bandName) {
        return Band.findByName(bandName);
    }

    @Mutation
    @Transactional
    public String createBand(@Name("bandName") String bandName, @Name("genre") String genre) {
        return Band.create(bandName, genre);
    }

    @Subscription
    public Multi<Band> bandCreated() {
        return processor;
    }
}