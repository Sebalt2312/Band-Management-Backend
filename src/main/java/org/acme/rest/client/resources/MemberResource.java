package org.acme.rest.client.resources;

import org.acme.rest.client.entity.Member;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

@Path("/member")
@GraphQLApi
public class MemberResource {

    @GET
    public List<Member> getAll() {
        return Member.findAll().list();
    }

    @Query
    public Member getMember(@Name("name") String name) {
        return Member.findByName(name);
    }

    @Query("findByBandName")
    public List<Member> findByBand(@Name("bandName") String bandName) {
        return Member.findByBand(bandName);
    }

    @Mutation
    public String createMember(@Name("name") String name, @Name("instrument") String instrument) {
        return Member.create(name, instrument);
    }

    @Mutation
    public String addBand(@Name("name") String name, @Name("bandName") String bandName) {
        return Member.addBand(name, bandName);
    }
}
