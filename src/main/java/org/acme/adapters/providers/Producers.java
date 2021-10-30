package org.acme.adapters.providers;

import org.acme.core.application.definition.EchoCore;
import org.acme.core.application.factory.EchoCoreFactory;
import org.acme.core.ports.EchoConfigurationPort;
import org.acme.core.ports.EchoPersistencePort;

import javax.enterprise.context.ApplicationScoped;

public class Producers {

    @ApplicationScoped
    public EchoCore createEchoCore(EchoConfigurationPort echo_configuration_port, EchoPersistencePort echo_persistence_port) {
        return EchoCoreFactory.create(echo_configuration_port, echo_persistence_port);
    }

}
