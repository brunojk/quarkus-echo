package org.acme;

import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;

@ApplicationScoped
public class LifeCycle {

    private static final Logger LOG = Logger.getLogger(LifeCycle.class);

    void onStart(@Observes StartupEvent ev) {
        LOG.info("The application is starting...");
    }

    void onStop(@Observes ShutdownEvent ev) {
        LOG.info("The application is stopping...");
    }

}