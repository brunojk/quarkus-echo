package org.acme.adapters.outbounds;

import org.acme.core.ports.EchoConfigurationPort;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DefaultEchoConfigurationAdapter implements EchoConfigurationPort {

    @Override
    public String getEchoTag() {
        return "default_quarkus";
    }

}
