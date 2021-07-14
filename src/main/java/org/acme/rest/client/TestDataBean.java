package org.acme.rest.client;

import io.quarkus.runtime.Startup;
import org.acme.rest.client.entity.Band;
import org.acme.rest.client.entity.Festival;
import org.acme.rest.client.entity.Member;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Startup
@ApplicationScoped
public class TestDataBean {

    @Inject
    public TestDataBean() throws ParseException {
        if (Band.listAll().isEmpty()) {
            Band.create("Die Ärzte", "Punk");
            Band.create("Die Toten Hosen", "Punk");
            Band.create("Donots", "Punk");
        }
        if (Member.listAll().isEmpty()) {
            Member.createWithBand("Bela", "Drums", 1);
            Member.createWithBand("Farin", "Guitar", 1);
            Member.createWithBand("Rod", "Baseguitar", 1);
            Member.createWithBand("Campino", "Singer", 2);
            Member.createWithBand("Vom", "Drums", 2);
            Member.createWithBand("Kuddel", "Guitar", 2);
            Member.createWithBand("Ingo", "Singer", 3);
            Member.createWithBand("Guido", "Guitar", 3);
            Member.create("Andrew", "Drums");
            Member.create("Zach", "Guitar");
        }
        if(Festival.listAll().isEmpty()) {
            Festival.create("Full Force",
                                  new SimpleDateFormat("dd.MM.yyyy").parse("24.06.2022"),
                                  new SimpleDateFormat("dd.MM.yyyy").parse("26.06.2022"));
            Festival.create("Reload",
                                  new SimpleDateFormat("dd.MM.yyyy").parse("18.08.2022"),
                                  new SimpleDateFormat("dd.MM.yyyy").parse("20.08.2022"));
        }
    }

}
