package org.acme.unit;

import io.smallrye.mutiny.Uni;
import org.acme.core.application.definition.EchoCore;
import org.acme.core.application.factory.EchoCoreFactory;
import org.acme.core.domains.Echo;
import org.acme.core.exceptions.NoopException;
import org.acme.core.helpers.UUIDGenerator;
import org.acme.core.ports.EchoConfigurationPort;
import org.acme.core.ports.EchoPersistencePort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.time.Duration;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

import static java.util.Objects.nonNull;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class EchoUnitTest {

    @BeforeEach
    public void beforeEach() {
        Mockito.clearAllCaches();
    }

    @Test
    public void testEchoCore() {

        // =====================================================
        // Mocking

        final EchoPersistencePort persistence_mock = mock(EchoPersistencePort.class);
        final EchoConfigurationPort configuration_mock = mock(EchoConfigurationPort.class);
        final UUIDGenerator uuid_generator_mock = spy(UUIDGenerator.getInstance());


        // =====================================================
        // Scenario

        final String test_input = "bruno";
        final String config_tag = "core";
        final UUID uuid = UUID.randomUUID();

        final long delay = 5000;

        when(persistence_mock.save(ArgumentMatchers.any(Echo.class)))
            .thenReturn(Uni.createFrom().item(0).onItem().delayIt().by(Duration.ofMillis(delay)));

        when(configuration_mock.getEchoTag()).thenReturn(config_tag);
        when(uuid_generator_mock.generate()).thenReturn(uuid);

        final EchoCore echo_core = EchoCoreFactory.create(configuration_mock, persistence_mock);

        echo_core.setUUIDGenerator(uuid_generator_mock);


        // =====================================================
        // Execution

        final AtomicReference<Throwable> exception_reference = new AtomicReference<>();
        final AtomicReference<Echo> echo_reference = new AtomicReference<>();

        echo_core.echo(test_input)
            .subscribe()
            .with(echo_reference::set, exception_reference::set);


        // =====================================================
        // Await results (if needed)

        await()
            .failFast(() -> nonNull(exception_reference.get()))
            .untilAtomic(echo_reference, notNullValue());

        final Echo echo = echo_reference.get();


        // =====================================================
        // Assertions

        assertThat(echo, instanceOf(Echo.class));

        assertThat(echo.getUUID(), equalTo(uuid.toString()));
        assertThat(echo.getTag(), equalTo(config_tag));
        assertThat(echo.getWord(), equalTo(test_input));


        // =====================================================
        // Verifies touched methods (if needed)

        verify(configuration_mock, times(1)).getEchoTag();

        verify(persistence_mock, times(1))
            .save(any(Echo.class));

        verify(uuid_generator_mock, times(1)).generate();
        verify(uuid_generator_mock, times(1)).string();

    }

    @Test
    public void testEchoCoreFail() throws InterruptedException {

        // =====================================================
        // Mocking

        final EchoPersistencePort persistence_mock = mock(EchoPersistencePort.class);
        final EchoConfigurationPort configuration_mock = mock(EchoConfigurationPort.class);
        final UUIDGenerator uuid_generator_mock = spy(UUIDGenerator.getInstance());


        // =====================================================
        // Scenario

        final String test_input = "noop";
        final String config_tag = "core";
        final UUID uuid = UUID.randomUUID();

        final long delay = 5000;

        when(persistence_mock.save(ArgumentMatchers.any(Echo.class)))
            .thenReturn(Uni.createFrom().item(0).onItem().delayIt().by(Duration.ofMillis(delay)));

        when(configuration_mock.getEchoTag()).thenReturn(config_tag);
        when(uuid_generator_mock.generate()).thenReturn(uuid);

        final EchoCore echo_core = EchoCoreFactory.create(configuration_mock, persistence_mock);

        echo_core.setUUIDGenerator(uuid_generator_mock);


        // =====================================================
        // Execution

        final AtomicReference<Throwable> exception_reference = new AtomicReference<>();
        final AtomicReference<Echo> echo_reference = new AtomicReference<>();

        echo_core.echo(test_input)
            .subscribe()
            .with(echo_reference::set, exception_reference::set);


        // =====================================================
        // Await results (if needed)

        await()
            .failFast(() -> nonNull(echo_reference.get()))
            .untilAtomic(exception_reference, notNullValue());


        // =====================================================
        // Assertions

        Assertions.assertInstanceOf(NoopException.class, exception_reference.get());


        // =====================================================
        // Verifies touched methods (if needed)

        verify(configuration_mock, never()).getEchoTag();

        verify(persistence_mock, never())
                .save(any(Echo.class));

        verify(uuid_generator_mock, never()).generate();
        verify(uuid_generator_mock, never()).string();

    }

}
