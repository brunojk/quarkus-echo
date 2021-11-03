package org.acme.adapters.inbounds;

import io.smallrye.mutiny.Uni;
import org.acme.adapters.dtos.EchoDTO;
import org.acme.core.application.definition.EchoCore;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/echo")
public class RestEchoAdapter {

    private final EchoCore echo_core;

    @Inject
    public RestEchoAdapter(EchoCore echo_core) {
        this.echo_core = echo_core;
    }

    @GET
    @Path("/{word}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<EchoDTO> echo(@PathParam("word") String word) {

        return echo_core.echo(word)
            .onItem().transform((item) -> {

                final EchoDTO echo_dto = new EchoDTO();

                echo_dto.setTag(item.getTag());
                echo_dto.setUuid(item.getUUID());
                echo_dto.setWord(item.getWord());

                return echo_dto;

            });

    }

}