package org.acme.core.application.definition;

import io.smallrye.mutiny.Uni;
import org.acme.core.domains.Echo;
import org.acme.core.helpers.UUIDGenerator;

public interface EchoCore {

    Uni<Echo> echo(String word);

    void setUUIDGenerator(UUIDGenerator uuid_generator);

}
