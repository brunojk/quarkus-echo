package org.acme.adapters.outbounds;

import org.acme.core.domains.Echo;
import org.acme.core.ports.EchoPersistencePort;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DumbEchoPersistenceAdapter implements EchoPersistencePort {

    @Override
    public int save(Echo echo) {
        return 0;
    }

}
