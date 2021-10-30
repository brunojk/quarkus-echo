package org.acme.adapters.inbounds;

import org.acme.adapters.dtos.EchoDTO;
import org.acme.core.application.definition.EchoCore;
import org.acme.core.domains.Echo;
import org.apache.commons.beanutils.BeanUtils;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.lang.reflect.InvocationTargetException;

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
    public EchoDTO echo(@PathParam String word) throws InvocationTargetException, IllegalAccessException {

        final Echo echo = echo_core.echo(word);

        final EchoDTO echo_dto = new EchoDTO();

        BeanUtils.copyProperties(echo_dto, echo);

        return echo_dto;

    }

}