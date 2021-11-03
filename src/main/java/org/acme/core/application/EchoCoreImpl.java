package org.acme.core.application;

import io.smallrye.mutiny.Uni;
import org.acme.core.application.definition.EchoCore;
import org.acme.core.domains.Echo;
import org.acme.core.exceptions.NoopException;
import org.acme.core.helpers.UUIDGenerator;
import org.acme.core.ports.EchoConfigurationPort;
import org.acme.core.ports.EchoPersistencePort;

import java.util.Optional;

public class EchoCoreImpl implements EchoCore {

    private final EchoConfigurationPort config;
    private final EchoPersistencePort persistence;

    private UUIDGenerator uuid_generator;

    public EchoCoreImpl(EchoConfigurationPort config, EchoPersistencePort persistence) {

        this.config = config;
        this.persistence = persistence;

        setUUIDGenerator(UUIDGenerator.getInstance());

    }

    @Override
    public void setUUIDGenerator(UUIDGenerator uuid_generator) {
        this.uuid_generator = uuid_generator;
    }

    @Override
    public Uni<Echo> echo(String word) {

        return Uni.createFrom().item(() -> {

                if( word.equals("noop") )
                    throw new NoopException();

                final String tag = Optional.ofNullable(config.getEchoTag()).orElse("quarkus");

                String uuid = uuid_generator.string();

                return new Echo(uuid, word, tag);

            })
            .onItem().transformToUni(echo -> persistence.save(echo).map(result -> echo));

    }
}
