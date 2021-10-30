package org.acme.core.ports;

import org.acme.core.domains.Echo;

public interface EchoPersistencePort {
    int save(Echo echo);
}