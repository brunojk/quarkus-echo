package org.acme.core.application;

import org.acme.core.application.definition.EchoCore;
import org.acme.core.domains.Echo;
import org.acme.core.helpers.UUIDGenerator;
import org.acme.core.ports.EchoConfigurationPort;
import org.acme.core.ports.EchoPersistencePort;

import java.util.Optional;

public class EchoCoreImpl implements EchoCore {

    private final EchoConfigurationPort config;
    private final EchoPersistencePort persistence;

    private UUIDGenerator uuid_generator;

    public EchoCoreImpl(EchoConfigurationPort config,
                        EchoPersistencePort persistence) {

        this.config = config;
        this.persistence = persistence;

        setUUIDGenerator(UUIDGenerator.getInstance());

    }

    public void setUUIDGenerator(UUIDGenerator uuid_generator) {
        this.uuid_generator = uuid_generator;
    }

    @Override
    public Echo echo(String word) {

        final String tag = Optional.ofNullable(config.getEchoTag()).orElse("quarkus");

        final Echo echo = new Echo(uuid_generator.string(), word, tag);

        persistence.save(echo);

        return echo;

    }
}
