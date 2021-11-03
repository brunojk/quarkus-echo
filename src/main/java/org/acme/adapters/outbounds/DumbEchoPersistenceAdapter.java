package org.acme.adapters.outbounds;

import io.smallrye.mutiny.Uni;
import org.acme.core.domains.Echo;
import org.acme.core.ports.EchoPersistencePort;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DumbEchoPersistenceAdapter implements EchoPersistencePort {

    @Override
    public Uni<Integer> save(Echo echo) {
        return Uni.createFrom().item(0);
    }

}
