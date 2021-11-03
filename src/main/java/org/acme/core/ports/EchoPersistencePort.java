package org.acme.core.ports;

import io.smallrye.mutiny.Uni;
import org.acme.core.domains.Echo;

public interface EchoPersistencePort {
    Uni<Integer> save(Echo echo);
}