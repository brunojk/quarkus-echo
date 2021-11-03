package org.acme.core.application.factory;

import org.acme.core.application.definition.EchoCore;
import org.acme.core.application.EchoCoreImpl;
import org.acme.core.ports.EchoConfigurationPort;
import org.acme.core.ports.EchoPersistencePort;

public class EchoCoreFactory {

    public static EchoCore create(
        EchoConfigurationPort config,
        EchoPersistencePort persistence
    ) {

        return new EchoCoreImpl(config, persistence);

    }

}
