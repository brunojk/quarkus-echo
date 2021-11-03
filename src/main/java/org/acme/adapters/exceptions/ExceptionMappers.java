package org.acme.adapters.exceptions;

import io.vertx.core.json.JsonObject;
import org.acme.core.exceptions.NoopException;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

import javax.ws.rs.core.Response;

public class ExceptionMappers {

    @ServerExceptionMapper
    public RestResponse<JsonObject> mapException(NoopException ex) {
        return RestResponse.status(Response.Status.BAD_REQUEST, new JsonObject().put("result", ex.getMessage()));
    }

}