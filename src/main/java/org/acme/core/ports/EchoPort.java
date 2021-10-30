package org.acme.core.ports;

import org.acme.core.domains.Echo;

public interface EchoPort {
    Echo echo(String word);
}
